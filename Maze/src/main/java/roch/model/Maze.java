package roch.model;

import roch.exceptions.OutOfBoundsNodeException;

import java.util.*;

public class Maze {
    private final int rows;
    private final int columns;
    private final Map<String,Square> nodes = new HashMap<>();
    private final WeightedGraph<Square> graph = new WeightedGraph<>();
    Stack<Square> visited = new Stack<>();
    private List<Square> path;

    /**
     * The constructor of the maze.
     * @param rows the number of rows of the maze.
     * @param columns the number of columns of the maze.
     */
    public Maze(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
    }

    /**
     * Generates a maze using the Depth first search method starting at the given position.
     * @param row the row where to start.
     * @param col the column where to start.
     */
    public void generateDepthFirst(int row, int col){

        for (int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                nodes.put("square" + i + j, new Square(new Position(i,j)));
            }
        }
        Square currentSquare;
        Random rand = new Random();

        this.nodes.get("square" + row + col).markVisited();
        visited.push(this.nodes.get("square" + row + col));

        while(!visited.empty()){
            currentSquare = visited.pop();
            var cur_neighbours = notVisitedNeighbours(currentSquare);
            if(!cur_neighbours.isEmpty()){
                visited.push(currentSquare);
                int rand_index = rand.nextInt(cur_neighbours.size() );
                var candidateSquare = cur_neighbours.get(rand_index);
                candidateSquare.markVisited();
                graph.addEdge(currentSquare, candidateSquare);
                visited.push(candidateSquare);
            }
        }
    }

    /**
     * Searches for not visited neighbours, if they exist.
     * @return a list of all the non visited neighbours.
     */
    private ArrayList<Square> notVisitedNeighbours(Square square){

        ArrayList<Square> notVisited = new ArrayList<>();
        try {
            Position next = square.getPos().next(Direction.UP);
            var neighbour = this.nodes.get("square" + next.getRow() + next.getColumn());
            if(!neighbour.isVisited()){
                notVisited.add(neighbour);
            }
        }catch (Exception e){
            // no neighbour here.
        }
        try {
            Position next = square.getPos().next(Direction.DOWN);
            var neighbour = this.nodes.get("square" + next.getRow() + next.getColumn());
            if(!neighbour.isVisited()){
                notVisited.add(neighbour);
            }
        }catch (Exception e){
            // no neighbour here.
        }
        try {
            Position next = square.getPos().next(Direction.LEFT);
            var neighbour = this.nodes.get("square" + next.getRow() + next.getColumn());
            if(!neighbour.isVisited()){
                notVisited.add(neighbour);
            }
        }catch (Exception e){
            // no neighbour here.
        }
        try {
            Position next = square.getPos().next(Direction.RIGHT);
            var neighbour = this.nodes.get("square" + next.getRow() + next.getColumn());
            if(!neighbour.isVisited()){
                notVisited.add(neighbour);
            }
        }catch (Exception e){
            // no neighbour here.
        }
        return notVisited;
    }

    /**
     * Searches for a path between the node at the source position and the node at the destination position.
     * If there is no path possible, it will be null.
     * @param sourcePosition the position of the source node.
     * @param destinationPosition the position of the destination node.
     * @throws OutOfBoundsNodeException when the destination node is not in the maze.
     */
    public void findDFSPath(Position sourcePosition, Position destinationPosition) throws OutOfBoundsNodeException {
        if(destinationPosition.getRow() > this.rows || destinationPosition.getColumn() > this.columns){
            throw new OutOfBoundsNodeException("The destination node isn't in the maze");
        }
        if(sourcePosition.getRow() > this.rows || sourcePosition.getColumn() > this.columns){
            throw new OutOfBoundsNodeException("The source node isn't in the maze");
        }
        var sourceNode = this.nodes.get("square" + sourcePosition.getRow() + sourcePosition.getColumn());
        var destinationNode = this.nodes.get("square" + destinationPosition.getRow() + destinationPosition.getColumn());

        this.path = PathDFS(sourceNode, destinationNode);
    }

    /**
     * Checks if a path exists between the source node and destination node.
     * @param src the source node.
     * @param dest the destination node.
     * @return a list of nodes that represents the path between the two nodes,
     * null if there isn't any.
     */
    private List<Square> PathDFS(Square src, Square dest) {
        HashMap<Square, Boolean> visited = new HashMap<>();
        List<Square> path = new ArrayList<>();

        if (dfsHelper(src, dest, visited, path)) {
            return path;
        } else {
            return null;  // No path found
        }
    }

    /**
     * Helper function to find the path between two nodes.
     * @param source_node the source node.
     * @param dest_node the destination node.
     * @param visited a map of the visited nodes.
     * @param path the path between two nodes.
     * @return true if a path is found, false otherwise.
     */
    private boolean dfsHelper(Square source_node, Square dest_node, HashMap<Square, Boolean> visited, List<Square> path) {
        if (source_node == dest_node) {
            path.add(source_node);
            return true;
        }

        visited.put(source_node, true);
        path.add(source_node);

        for (Edge<Square> edge : graph.getAdj().get(source_node)) {
            Square neighbour = edge.getConnectedNode();
            if (!visited.containsKey(neighbour) && dfsHelper(neighbour, dest_node, visited, path)) {
                return true;
            }
        }

        path.remove(path.size() - 1);  // Remove the last node if no path is found from this node
        return false;
    }

    public void findAStarPath(Position sourcePosition, Position destinationPosition) throws OutOfBoundsNodeException {
        
    }
    public boolean contains(Position pos){
        return ((pos.getRow() >= 0 && pos.getRow() < this.rows) && (pos.getColumn() >= 0 && pos.getColumn() < this.columns));
    }
    public boolean findIfConnected(Square node1, Square node2){
        return graph.hasEdge(node1, node2);
    }

    public Map<String, Square> getNodes() {
        return nodes;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<Square> getPath() {
        return path;
    }
}
