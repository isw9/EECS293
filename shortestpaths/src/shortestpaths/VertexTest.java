package shortestpaths;
import org.junit.Test;

import static org.junit.Assert.*;


public class VertexTest {
    @Test
    public void validVertex() {
        Vertex vertex = Vertex.of("test name");

        assertEquals("test name", vertex.getName());
    }

    @Test(expected = NullPointerException.class)
    public void invalidVertex() {
        Vertex vertex = Vertex.of(null);
    }

    @Test
    public void equalsSameObject() {
        Vertex vertex = Vertex.of("test name");

        assertTrue(vertex.equals(vertex));
    }

    @Test
    public void equalsOtherNull() {
        Vertex vertex = Vertex.of("test name");

        assertFalse(vertex.equals(null));
    }

    @Test
    public void equalsOtherDifferentClass() {
        Vertex vertex = Vertex.of("test name");
        Object other = new Object();
        assertFalse(vertex.equals(other));
    }

    @Test
    public void equalsSameName() {
        Vertex vertex = Vertex.of("test name");
        Vertex vertex2 = Vertex.of("test name");

        assertTrue(vertex.equals(vertex2));
    }

    @Test
    public void hashCodeValid() {
        Vertex vertex = Vertex.of("test name");
        vertex.hashCode();
    }

}
