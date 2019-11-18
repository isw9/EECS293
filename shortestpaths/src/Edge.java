import java.util.Objects;

public class Edge {
    private Vertex source;

    private Vertex destination;

    private double cost;


    private Edge(Vertex source, Vertex destination, double cost) {

        this.source = source;
        this.destination = destination;
        this.cost = cost >= 0 ? cost : 0.0;
    }

    public static Edge of(Vertex source, Vertex destination, double cost) {
        Objects.requireNonNull(source, "source Vertex in Edge cannot be null");
        Objects.requireNonNull(destination, "destination Vertex in Edge cannot be null");

        return new Edge(source, destination, cost);
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object otherEdge) {
        if (this == otherEdge) {
            return true;
        }
        if (otherEdge == null || getClass() != otherEdge.getClass()) {
            return false;
        }

        Edge edge = (Edge) otherEdge;
        return equivalentFields(this, edge);
    }

    private static boolean equivalentFields(Edge edge1, Edge edge2) {
        return Double.compare(edge1.getCost(), edge2.getCost()) == 0 &&
                edge1.getSource().equals(edge2.getSource()) &&
                edge1.getDestination().equals(edge2.getDestination());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + getSource().hashCode();
        result = 31 * result + getDestination().hashCode();
        result = 31 * result + (int) Math.round(getCost());
        return result;
    }


    /**
     * Inner class that will be used to test the private methods
     */
    public class TestHook {

        public boolean equivalentFields(Edge edge1, Edge edge2) {
            return Edge.equivalentFields(edge1, edge2);
        }
    }

    /**
     * A simple constructor for the sole purpose of running a simple example
     */
    Edge() { }
}
