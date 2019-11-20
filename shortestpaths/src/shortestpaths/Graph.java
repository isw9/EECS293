package shortestpaths;
import java.util.*;

public class Graph {
    private double[][] adjacencyMatrix;

    private static Hashtable<String, Integer> vertexNameMapping = new Hashtable<String, Integer>();

    private List<Vertex> vertices;

    private static List<Edge> edges;


    public Graph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;

        populateVertexNameMapping(vertices);
        this.adjacencyMatrix = adjacencyMatrix(vertices, edges);
    }

    public static Graph of(List<Vertex> vertices, List<Edge> edges) {
        Objects.requireNonNull(vertices, "list of vertices supplied to Graph can't be null");
        Objects.requireNonNull(edges, "list of edges supplied to Graph can't be null");

        return new Graph(vertices, edges);
    }


    public static boolean contains(List<Vertex> vertices) {
        for (Vertex vertex : vertices) {
            if (!vertexNameMapping.containsKey(vertex.getName())) {
                return false;
            }
        }

        return true;
    }

    public void addEdge(Edge edge) {
        List<Vertex> vertices = new ArrayList<Vertex>(Arrays.asList(edge.getSource(), edge.getDestination()));
        if (contains(vertices)) {

            removeIfExistingConnection(edge.getSource(), edge.getDestination());
            edges.add(edge);


            double cost = edge.getCost();

            String source = edge.getSource().getName();
            int sourceIndex = vertexNameMapping.get(source);
            String destination = edge.getDestination().getName();
            int destinationIndex = vertexNameMapping.get(destination);

            adjacencyMatrix[sourceIndex][destinationIndex] = cost;
        }
    }

    public double edgeWeight(Vertex sourceVertex, Vertex destinationVertex) {
        int sourceIndex = vertexNameMapping.getOrDefault(sourceVertex.getName(), -1);
        int destinationIndex = vertexNameMapping.getOrDefault(destinationVertex.getName(), -1);

        if (sourceIndex == -1 || destinationIndex == -1) {
            return -1.0;
        }

        return adjacencyMatrix[sourceIndex][destinationIndex];
    }

    public List<Vertex> minimumCostPath(Vertex source, Vertex destination) throws InvalidPathException {
        Hashtable<Vertex, Double> distanceTable = new Hashtable<>();
        Hashtable<Vertex, Vertex> previousTable = new Hashtable<>();
        HashSet<Vertex> unvisitedVertices = new HashSet<Vertex>();


        validatePathInput(source, destination);
        distanceTable = initializeDistanceTable();
        unvisitedVertices = initializeUnvisitedVertices();


        distanceTable.put(source, 0.0);

        while (!unvisitedVertices.isEmpty()) {

            Vertex minDistanceVertex = minDistanceFromTable(distanceTable, unvisitedVertices);

            unvisitedVertices.remove(minDistanceVertex);

            List<Vertex> validNeighbors = validNeighbors(minDistanceVertex, unvisitedVertices);

            for (Vertex neighbor : validNeighbors) {
                double edgeWeight = edgeWeight(minDistanceVertex, neighbor);

                double alternateRouteCost = distanceTable.get(minDistanceVertex) + edgeWeight;

                if (alternateRouteCost < distanceTable.get(neighbor)) {
                    distanceTable.put(neighbor, alternateRouteCost);
                    previousTable.put(neighbor, minDistanceVertex);
                }
            }
        }

        return shortestPath(source, destination, previousTable);
    }

    private Hashtable<Vertex, Double> initializeDistanceTable() {
        Hashtable<Vertex, Double> distanceTable = new Hashtable<Vertex, Double>();
        for (Vertex vertex : getVertices()) {
            distanceTable.put(vertex, Double.POSITIVE_INFINITY);
        }

        return distanceTable;
    }

    private HashSet<Vertex> initializeUnvisitedVertices() {
        HashSet<Vertex> unvisitedVertices = new HashSet<Vertex>();

        for (Vertex vertex : getVertices()) {
            unvisitedVertices.add(vertex);
        }

        return unvisitedVertices;
    }

    private List<Vertex> shortestPath(Vertex source, Vertex destination, Hashtable<Vertex, Vertex> previousTable) {
        List<Vertex> path = new ArrayList<Vertex>();
        Vertex target = destination;
        while (target != source) {

            path.add(previousTable.get(target));
            target = previousTable.get(target);
        }

        Collections.reverse(path);
        path.add(destination);

        return path;
    }

    private static void validatePathInput(Vertex source, Vertex destination) throws InvalidPathException {
        List<Vertex> inputVertices = new ArrayList<>(Arrays.asList(source, destination));

        if (source.equals(destination)) {
            throw new InvalidPathException("source and destination vertex can't be the same");
        }

        if (!contains(inputVertices)) {
            throw new InvalidPathException("vertex " + source.getName() + " or " + destination.getName() + " is not in the graph");
        }
    }

    private List<Vertex> validNeighbors(Vertex source, HashSet<Vertex> unvisitedVertices) {
        List<Vertex> validNeighbors = new ArrayList<Vertex>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(source) && unvisitedVertices.contains(edge.getDestination())) {
                validNeighbors.add(edge.getDestination());
            }
        }

        return validNeighbors;
    }

    private Vertex minDistanceFromTable(Hashtable<Vertex, Double> distanceTable, HashSet<Vertex> unvisited) {
        double minSoFar = Double.POSITIVE_INFINITY;
        Vertex minVertexSoFar = null;

        for (Map.Entry<Vertex, Double> entry : distanceTable.entrySet()) {
            if (unvisited.contains(entry.getKey()) && entry.getValue() < minSoFar) {
                minVertexSoFar = entry.getKey();
            }
        }

        return minVertexSoFar;
    }


    public double pathCost(List<Vertex> vertices) {
        double total = 0.0;
        if (vertices.size() < 2 || !contains(vertices)) {
            return -1.0;
        }

        for (int i = 1; i < vertices.size(); i++) {
            Vertex source = vertices.get(i-1);
            Vertex destination = vertices.get(i);

            int sourceIndex = vertexNameMapping.get(source.getName());
            int destinationIndex = vertexNameMapping.get(destination.getName());

            double cost = adjacencyMatrix[sourceIndex][destinationIndex];

            if (cost < 0) {
                return -1.0;
            }

            total += cost;
         }

        return total;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    private void populateVertexNameMapping(List<Vertex> vertices) {
        int index = 0;
        for (Vertex vertex : vertices) {
            if (!vertexNameMapping.containsKey(vertex.getName())) {
                vertexNameMapping.put(vertex.getName(), index);
                index++;
            }
        }
    }

    private static double[][] adjacencyMatrix(List<Vertex> vertices, List<Edge> edges) {
        int matrixSize = vertices.size();

        double[][] adjacencyMatrix = new double[matrixSize][matrixSize];
        adjacencyMatrix = negativeValueAdjacencyMatrix(adjacencyMatrix);


        for (Edge edge : edges) {
            List<Vertex> currentVertices = new ArrayList<Vertex>(Arrays.asList(edge.getSource(), edge.getDestination()));
            if (contains(currentVertices)) {

                Vertex source = edge.getSource();
                Vertex destination = edge.getDestination();
                int sourceIndex = vertexNameMapping.getOrDefault(source.getName(), -1);
                int destinationIndex = vertexNameMapping.getOrDefault(destination.getName(), -1);

                adjacencyMatrix[sourceIndex][destinationIndex] = edge.getCost();
            }
        }

        return adjacencyMatrix;
    }

    private static double[][] negativeValueAdjacencyMatrix(double[][] adjacencyMatrix) {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                adjacencyMatrix[i][j] = -1.0;
            }
        }

        return adjacencyMatrix;
    }

    private static void removeIfExistingConnection(Vertex source, Vertex destination) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(source) && edge.getDestination().equals((destination))) {
                edges.remove(edge);
            }
        }
    }

    /**
     * Inner class that will be used to test the private methods
     */
    public class TestHook {

        public void removeIfExistingConnection(Vertex vertex1, Vertex vertex2) {
            Graph.removeIfExistingConnection(vertex1, vertex2);
        }

        public double[][] adjacencyMatrix(List<Vertex> vertices, List<Edge> edges) {
            return Graph.adjacencyMatrix(vertices, edges);
        }

        public void validatePathInput(Vertex source, Vertex destination) throws InvalidPathException {
            Graph.validatePathInput(source, destination);
        }
    }

    /**
     * A simple constructor for the sole purpose of running a simple example
     */
    Graph() { }

}
