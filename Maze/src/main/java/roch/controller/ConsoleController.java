package roch.controller;

import roch.exceptions.OutOfBoundsNodeException;
import roch.model.Maze;
import roch.model.Position;
import roch.view.ConsoleView;

/**
 * The controller class for the console version
 */
public class ConsoleController {

    private Maze maze;
    private final ConsoleView view;

    public ConsoleController() {
        this.view = new ConsoleView();
    }

    public void createMaze(){
        var dimensions = view.askMazeDimensions();
        this.maze = new Maze(dimensions[0], dimensions[1]);
    }

    public void displayMaze(){
        view.displayMaze(this.maze);
    }

    public void generateDFSMaze(int row, int col){
        this.maze.generateDepthFirst(row, col);
    }

    public void resolveMazeWithDFS(Position start, Position finish){
        try{
            maze.findDFSPath(start, finish);
        }catch (OutOfBoundsNodeException e){
            resolveMazeWithDFS(start, view.askPosition("Give a valid destination node position please"));
        }
    }
}
