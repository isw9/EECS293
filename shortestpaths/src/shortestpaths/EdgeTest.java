package shortestpaths;
import org.junit.Test;

import static org.junit.Assert.*;


public class EdgeTest {
    @Test
    public void validEdge() {
        Vertex source = Vertex.of("source");
        Vertex destination = Vertex.of("destination");

        Edge edge = Edge.of(source, destination, 1.0);
        assertEquals(edge.getSource(), source);
        assertEquals(edge.getDestination(), destination);
        assertTrue(edge.getCost() == 1.0);
    }

    @Test(expected = NullPointerException.class)
    public void sourceNull() {
        Vertex source = null;
        Vertex destination = Vertex.of("destination");

        Edge edge = Edge.of(source, destination, 1.0);
    }

    @Test(expected = NullPointerException.class)
    public void destinationNull() {
        Vertex source = Vertex.of("source");
        Vertex destination = null;

        Edge edge = Edge.of(source, destination, 1.0);
    }

    @Test
    public void costNegative() {
        Vertex source = Vertex.of("source");
        Vertex destination = Vertex.of("destination");

        Edge edge = Edge.of(source, destination, -1.0);

        assertTrue(edge.getCost() == 0.0);
    }

    @Test
    public void equalsSameObject() {
        Vertex source = Vertex.of("source");
        Vertex destination = Vertex.of("destination");

        Edge edge = Edge.of(source, destination, 2.0);

        assertTrue(edge.equals(edge));
    }

    @Test
    public void equalsOtherNull() {
        Vertex source = Vertex.of("source");
        Vertex destination = Vertex.of("destination");

        Edge edge = Edge.of(source, destination, 2.0);

        assertFalse(edge.equals(null));
    }

    @Test
    public void equalsOtherDifferentClass() {
        Vertex source = Vertex.of("source");
        Vertex destination = Vertex.of("destination");

        Edge edge = Edge.of(source, destination, 2.0);

        Object other = new Object();
        assertFalse(edge.equals(other));
    }

    @Test
    public void equalsTrue() {
        Vertex source1 = Vertex.of("source");
        Vertex destination1 = Vertex.of("destination");
        Edge edge1 = Edge.of(source1, destination1, 2.0);

        Vertex source2 = Vertex.of("source");
        Vertex destination2 = Vertex.of("destination");
        Edge edge2 = Edge.of(source2, destination2, 2.0);

        assertTrue(edge1.equals(edge2));
    }

    @Test
    public void equivalentFieldsTTF() {
        Edge hook = new Edge();
        Edge.TestHook testHook = hook.new TestHook();


        Vertex source1 = Vertex.of("source");
        Vertex destination1 = Vertex.of("destination");
        Edge edge1 = Edge.of(source1, destination1, 2.0);

        Vertex source2 = Vertex.of("source");
        Vertex destination2 = Vertex.of("destination");
        Edge edge2 = Edge.of(source2, destination2, 3.0);

        assertFalse(testHook.equivalentFields(edge1, edge2));
    }

    @Test
    public void equivalentFieldsTFT() {
        Edge hook = new Edge();
        Edge.TestHook testHook = hook.new TestHook();


        Vertex source1 = Vertex.of("source");
        Vertex destination1 = Vertex.of("destination");
        Edge edge1 = Edge.of(source1, destination1, 2.0);

        Vertex source2 = Vertex.of("source");
        Vertex destination2 = Vertex.of("destination2");
        Edge edge2 = Edge.of(source2, destination2, 2.0);

        assertFalse(testHook.equivalentFields(edge1, edge2));
    }

    @Test
    public void equivalentFieldsTFF() {
        Edge hook = new Edge();
        Edge.TestHook testHook = hook.new TestHook();


        Vertex source1 = Vertex.of("source");
        Vertex destination1 = Vertex.of("destination");
        Edge edge1 = Edge.of(source1, destination1, 2.0);

        Vertex source2 = Vertex.of("source");
        Vertex destination2 = Vertex.of("destination2");
        Edge edge2 = Edge.of(source2, destination2, 3.0);

        assertFalse(testHook.equivalentFields(edge1, edge2));
    }

    @Test
    public void equivalentFieldsFTT() {
        Edge hook = new Edge();
        Edge.TestHook testHook = hook.new TestHook();


        Vertex source1 = Vertex.of("source");
        Vertex destination1 = Vertex.of("destination");
        Edge edge1 = Edge.of(source1, destination1, 2.0);

        Vertex source2 = Vertex.of("source2");
        Vertex destination2 = Vertex.of("destination");
        Edge edge2 = Edge.of(source2, destination2, 2.0);

        assertFalse(testHook.equivalentFields(edge1, edge2));
    }

    @Test
    public void equivalentFieldsFTF() {
        Edge hook = new Edge();
        Edge.TestHook testHook = hook.new TestHook();


        Vertex source1 = Vertex.of("source");
        Vertex destination1 = Vertex.of("destination");
        Edge edge1 = Edge.of(source1, destination1, 2.0);

        Vertex source2 = Vertex.of("source2");
        Vertex destination2 = Vertex.of("destination");
        Edge edge2 = Edge.of(source2, destination2, 3.0);

        assertFalse(testHook.equivalentFields(edge1, edge2));
    }

    @Test
    public void equivalentFieldsFFT() {
        Edge hook = new Edge();
        Edge.TestHook testHook = hook.new TestHook();


        Vertex source1 = Vertex.of("source");
        Vertex destination1 = Vertex.of("destination");
        Edge edge1 = Edge.of(source1, destination1, 2.0);

        Vertex source2 = Vertex.of("source2");
        Vertex destination2 = Vertex.of("destination2");
        Edge edge2 = Edge.of(source2, destination2, 2.0);

        assertFalse(testHook.equivalentFields(edge1, edge2));
    }

    @Test
    public void equivalentFieldsFFF() {
        Edge hook = new Edge();
        Edge.TestHook testHook = hook.new TestHook();


        Vertex source1 = Vertex.of("source");
        Vertex destination1 = Vertex.of("destination");
        Edge edge1 = Edge.of(source1, destination1, 2.0);

        Vertex source2 = Vertex.of("source2");
        Vertex destination2 = Vertex.of("destination2");
        Edge edge2 = Edge.of(source2, destination2, 3.0);

        assertFalse(testHook.equivalentFields(edge1, edge2));
    }

    @Test
    public void hashCodeValid() {
        Vertex source = Vertex.of("source");
        Vertex destination = Vertex.of("destination");

        Edge edge = Edge.of(source, destination, 1.0);
        edge.hashCode();
    }


}
