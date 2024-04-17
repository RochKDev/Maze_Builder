package roch.controller;

import roch.exceptions.OutOfBoundsNodeException;
import roch.model.Maze;
import roch.model.Position;
import roch.view.ConsoleView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The controller class for the console version
 */
public class ConsoleController {

    private Maze maze;
    private final ConsoleView view;
    private final Set<String> generateAlgos;
    private final Set<String> searchAlgos;

    public ConsoleController() {
        this.view = new ConsoleView();
        generateAlgos = Set.of("dfs");
        searchAlgos = Set.of("dfs", "astar", "a*");
    }

    public void start(){
        view.displayWelcomeMessage();
        createMaze();
        String wantedAlgorithm = askForGenerateAlgo();
        generateChosenMaze(wantedAlgorithm);
        resolveMazeWithDFS(new Position(0,0), new Position(19,19));
        displayMaze();
    }

    private void createMaze(){
        var dimensions = view.askMazeDimensions();
        this.maze = new Maze(dimensions[0], dimensions[1]);
    }

    public void displayMaze(){
        view.displayMaze(this.maze);
    }

    private void generateDFSMaze(){
        Position pos = view.askPosition("Please chose the position where to start the generation of the maze.");
        while(!this.maze.contains(pos)){
            pos = view.askPosition("Please enter a position contained in the maze!");
        }
        this.maze.generateDepthFirst(pos.getRow(), pos.getColumn());
    }

    private void resolveMazeWithDFS(Position start, Position finish){
        try{
            maze.findDFSPath(start, finish);
        }catch (OutOfBoundsNodeException e){
            resolveMazeWithDFS(start, view.askPosition("Give a valid destination node position please"));
        }
    }
    private String askForGenerateAlgo(){
        view.displayMessage("Which algorithm would you like to use to create your maze?");
        view.displayMessage("You can choose from : ");
        for(String algo : generateAlgos){
            view.displayMessage(algo);
        }
        String wantedAlgorithm = view.askForString("Enter the wanted algorithm : ");
        wantedAlgorithm = wantedAlgorithm.toLowerCase().replaceAll("\\s+", "");

        while(!generateAlgos.contains(wantedAlgorithm)){
            wantedAlgorithm = view.askForString("Please select a valid algorithm from the list.");
            wantedAlgorithm = wantedAlgorithm.toLowerCase().replaceAll("\\s+", "");
        }
        System.out.println("Chosen algorithm : " + wantedAlgorithm);
        return wantedAlgorithm;
    }

    private void generateChosenMaze(String generateAlgo){
        switch (generateAlgo){
            case "dfs" : {
                generateDFSMaze();
            }
        }
    }
}
