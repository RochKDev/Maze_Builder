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
        searchAlgos = Set.of("dfs", "astar (a*)");
    }

    /**
     * Starts the Maze builder application.
     */
    public void start(){
        view.displayWelcomeMessage();
        createMaze();
        String wantedAlgorithm = askForAlgo("Please choose a generative algorithm.", true);
        generateChosenMaze(wantedAlgorithm);
        wantedAlgorithm = askForAlgo("Please choose a search algorithm.", false);
        resolveWithChosenAlgo(wantedAlgorithm);
        displayMaze();
    }

    /**
     * Asks the user for dimensions of the maze and creates it.
     */
    private void createMaze(){
        var dimensions = view.askMazeDimensions();
        this.maze = new Maze(dimensions[0], dimensions[1]);
    }

    /**
     * Displays the maze.
     */
    public void displayMaze(){
        view.displayMaze(this.maze);
    }

    /**
     * Generates a maze with the Depth First Search Algorithm.
     */
    private void generateDFSMaze(){
        Position pos = view.askPosition("Please chose the position where to start the generation of the maze.");
        while(!this.maze.contains(pos)){
            pos = view.askPosition("Please enter a position contained in the maze!");
        }
        this.maze.generateDepthFirst(pos.getRow(), pos.getColumn());
    }

    /**
     * Starts resolving the maze with the Depth First Search Algorithm.
     * @param start the starting position of the searching algorithm.
     * @param finish the destination of the searching algorithm.
     */
    private void resolveMazeWithDFS(Position start, Position finish){
        try{
            maze.findDFSPath(start, finish);
        }catch (OutOfBoundsNodeException e){
            resolveMazeWithDFS(start, view.askPosition("Give a valid node position please"));
        }
    }

    /**
     * Asks the user for an algorithm type.
     * @param message the message to display.
     * @param type false if you want to ask for a search algorithm, true if you want a generative algorithm.
     * @return the wanted algorithm.
     */
    private String askForAlgo(String message, boolean type){
        view.displayMessage(message);
        view.displayMessage("You can choose from : ");
        if(type){
            for(String algo : generateAlgos){
                view.displayMessage(algo);
            }
        }else{
            for(String algo : searchAlgos){
                view.displayMessage(algo);
            }
        }

        String wantedAlgorithm = view.askForString("Enter the wanted algorithm : ");
        wantedAlgorithm = wantedAlgorithm.toLowerCase().replaceAll("\\s+", "");

        if (type) {
            while(!generateAlgos.contains(wantedAlgorithm)){
                wantedAlgorithm = view.askForString("Please select a valid algorithm from the list.");
                wantedAlgorithm = wantedAlgorithm.toLowerCase().replaceAll("\\s+", "");
            }
        }else {
            while(!searchAlgos.contains(wantedAlgorithm)){
                wantedAlgorithm = view.askForString("Please select a valid algorithm from the list.");
                wantedAlgorithm = wantedAlgorithm.toLowerCase().replaceAll("\\s+", "");
            }
        }

        System.out.println("Chosen algorithm : " + wantedAlgorithm);
        return wantedAlgorithm;
    }

    /**
     * Generates a maze with the given algorithm.
     * @param generateAlgo the wanted algorithm.
     */
    private void generateChosenMaze(String generateAlgo){
        switch (generateAlgo){
            case "dfs" : {
                generateDFSMaze();
            }
        }
    }

    /**
     * Resolves the maze with the chosen algorithm.
     * @param resolveAlgo the chosen algorithm.
     */
    private void resolveWithChosenAlgo(String resolveAlgo){
        switch (resolveAlgo){
            case "dfs" : {
                Position start = view.askPosition("Enter the starting position of the resolving algorithm : ");
                while(!this.maze.contains(start)){
                    view.displayMessage("Please enter a valid source position.");
                    start = view.askPosition("Enter the starting position of the resolving algorithm : ");
                }
                Position end = view.askPosition("Enter the destination of the resolving algorithm : ");
                while(!this.maze.contains(end)){
                    view.displayMessage("Please enter a valid destination position.");
                    end = view.askPosition("Enter the destination of the resolving algorithm : ");
                }
                resolveMazeWithDFS(start, end);
            }
            case "astar" : {
                //TODO start astar
            }
            case "a*" :{
                //TODO start astar
            }
        }
    }
}
