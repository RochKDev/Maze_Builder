package roch.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Square {
    private boolean visited = false;
    private final Position pos;

    /**
     * The constructor of the maze square.
     * @param pos the position of the square.
     */
    public Square(Position pos){
        this.pos = pos;
    }

    /**
     * The getter of the visited attribute.
     * @return the visited attribute.
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Getter of the position of the square.
     * @return the position of the square in the maze.
     */
    public Position getPos() {
        return pos;
    }

    /**
     * Marks the square as visited.
     */
    public void markVisited(){this.visited = true;}
}
