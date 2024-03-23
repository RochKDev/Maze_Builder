import roch.controller.ConsoleController;
import roch.model.Position;


public class App {
    public static void main(String[] args) {
        ConsoleController controller = new ConsoleController();
        controller.createMaze();
        controller.generateDFSMaze(0,0);
        controller.resolveMazeWithDFS(new Position(0,0), new Position(19,19));
        controller.displayMaze();
    }
}
