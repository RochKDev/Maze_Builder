package roch.view;


import roch.model.Maze;
import roch.model.Position;
import roch.model.Square;

import java.util.Scanner;


public class ConsoleView implements View {

    private final Scanner scanner = new Scanner(System.in);

    private void displayLine(Maze maze, int line) {
        for (int i = 0; i < maze.getColumns(); i++) {
            var current = maze.getNodes().get("square" + line + i);
            try {
                if (maze.findIfConnected(current, maze.getNodes().get("square" + (line - 1) + i ))) {// if the node is connected from above
                    System.out.print("+   +");
                } else {
                    System.out.print("+---+");
                }
            } catch (Exception e) {
                // non existent node.
                System.out.print("+---+");
            }
        }
        System.out.println();

        for (int i = 0; i < maze.getColumns(); i++) {
            var current = maze.getNodes().get("square" + line + i );
            try {
                if (maze.findIfConnected(current, maze.getNodes().get("square" + line + (i - 1)))) {// if the node is connected from left
                    System.out.print(" ");
                } else {
                    System.out.print("|");
                }
            } catch (Exception e) {
                //non existent node
                System.out.print("|");
            }
            if (maze.getPath().contains(current)){
                System.out.print(" X ");
            }else {
                System.out.print("   ");
            }


            try {
                if (maze.findIfConnected(current, maze.getNodes().get("square" + line + (i + 1)))) {// if the node is connected from right
                    System.out.print(" ");
                } else {
                    System.out.print("|");
                }
            } catch (Exception e) {
                // non existent node
                System.out.print("|");
            }
        }
        System.out.println();
    }

    @Override
    public void displayMaze(Maze maze) {
        for (int i = 0; i < maze.getRows(); i++) {
            displayLine(maze, i);
        }
        for (int i = 0; i < maze.getRows(); i++) {
            System.out.print("+---+");
        }
    }

    @Override
    public int[] askMazeDimensions() {
        int[] dimensions = new int[2];
        System.out.println("What are the dimensions of the maze you want to build?");
        System.out.println("Enter the dimensions for the length");
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("Please enter a valid number");
        }
        dimensions[0] = scanner.nextInt();

        System.out.println("Enter the dimensions for the height");
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("Please enter a valid number");
        }
        dimensions[1] = scanner.nextInt();
        return dimensions;
    }

    @Override
    public void errorMessage(String errorMessage){
        System.out.println(errorMessage);
    }

    @Override
    public Position askPosition(String message){
        System.out.println(message);
        System.out.println("Enter the row of the position please (starts at 0)");
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("Please enter a valid number");
        }
        int row = scanner.nextInt();

        System.out.println("Enter the column of the position please (starts at 0)");
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("Please enter a valid number");
        }
        int column = scanner.nextInt();

        return new Position(row, column);
    }
}
