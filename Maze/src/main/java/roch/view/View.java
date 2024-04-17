package roch.view;

import roch.model.Maze;
import roch.model.Position;
import roch.model.Square;

import java.util.Map;

public interface View {


    /**
     * Displays the maze.
     * @param maze the maze to display.
     */
    public void displayMaze(Maze maze);

    /**
     * Asks the user for the dimensions of the maze.
     * @return a tab of 2 integers, the first one represents the rows and the second one the columns.
     */
    public int[] askMazeDimensions();

    /**
     * Displays an error message.
     * @param Message the message to display.
     */
    public void displayMessage(String Message);

    /**
     * Asks a user for a position with the given message.
     * @param message the message to ask.
     * @return the position entered by the user.
     */
    public Position askPosition(String message);

    /**
     * Displays a welcome message to the console.
     */
    public void displayWelcomeMessage();

    public String askForString(String message);


}
