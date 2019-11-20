package shortestpaths;
import java.util.Objects;

public class Vertex {
    private String name;

    private Vertex(String name) {

        this.name = name;
    }

    public static Vertex of(String name) {
        Objects.requireNonNull(name, "name in Vertex cannot be null");

        return new Vertex(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object otherVertex) {
        if (this == otherVertex) {
            return true;
        }

        if (otherVertex == null || getClass() != otherVertex.getClass()) {
            return false;
        }

        Vertex vertex = (Vertex) otherVertex;
        return name.equals(vertex.name);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + getName().hashCode();
        return result;
    }

}
