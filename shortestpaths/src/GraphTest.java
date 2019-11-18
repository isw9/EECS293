import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;


public class GraphTest {
    @Test
    public void validGraph() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);

        assertEquals(vertices, graph.getVertices());
        assertEquals(edges, graph.getEdges());
    }

    @Test(expected = NullPointerException.class)
    public void invalidGraphNullVertices() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(null, edges);
    }

    @Test(expected = NullPointerException.class)
    public void invalidGraphNullEdges() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, null);
    }

    @Test
    public void containsTrue() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);

        assertTrue(graph.contains(vertices));
    }

    @Test
    public void containsFalse() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);

        Vertex D = Vertex.of("D");
        vertices.add(D);

        assertFalse(graph.contains(vertices));
    }

    @Test
    public void edgeWeightFF() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);


        Vertex D = Vertex.of("D");
        Vertex E = Vertex.of("E");

        assertTrue(-1.0 == graph.edgeWeight(D, E));
    }

    @Test
    public void edgeWeightFT() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);


        Vertex D = Vertex.of("D");

        assertTrue(-1.0 == graph.edgeWeight(D, A));
    }

    @Test
    public void edgeWeightTF() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);


        Vertex D = Vertex.of("D");

        assertTrue(-1.0 == graph.edgeWeight(A, D));
    }
    @Test
    public void addEdgeContains() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);

        Edge AC = Edge.of(A, C, 5.0);
        graph.addEdge(AC);

        assertTrue(graph.getEdges().contains(AC));
    }

    @Test
    public void addEdgeDoesNotContain() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);

        Vertex D = Vertex.of("D");
        Edge CD = Edge.of(C, D, 3.0);

        graph.addEdge(CD);
        assertFalse(graph.getEdges().contains(CD));
    }

    @Test
    public void edgeWeightValid() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);

        double edgeWeight = graph.edgeWeight(A, B);
        assertTrue(2.0 == edgeWeight);
    }

    @Test
    public void edgeWeightNoSource() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);

        Vertex D = Vertex.of("D");
        double edgeWeight = graph.edgeWeight(D, B);
        assertTrue( -1.0 == edgeWeight);
    }

    @Test(expected = InvalidPathException.class)
    public void validatePathInputSourceEqualsDestination() throws InvalidPathException{
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);


        Graph hook = new Graph();
        Graph.TestHook testHook = hook.new TestHook();
        testHook.validatePathInput(A, A);
    }

    @Test
    public void edgeWeightNoDestination() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);

        Vertex D = Vertex.of("D");
        double edgeWeight = graph.edgeWeight(B, D);
        assertTrue( -1.0 == edgeWeight);
    }

    @Test
    public void edgeWeightNoSourceOrDestination() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);

        Vertex D = Vertex.of("D");
        Vertex E = Vertex.of("E");
        double edgeWeight = graph.edgeWeight(D, E);
        assertTrue( -1.0 == edgeWeight);
    }

    @Test
    public void pathCostLessThanTwoVertices() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);


        List<Vertex> path = new ArrayList<>(Arrays.asList(A));
        double cost = graph.pathCost(path);
        assertTrue( -1.0 == cost);
    }

    @Test
    public void pathCostDoesNotContainVertex() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);


        Vertex D = Vertex.of("D");
        List<Vertex> path = new ArrayList<>(Arrays.asList(A, D));
        double cost = graph.pathCost(path);
        assertTrue( -1.0 == cost);
    }

    @Test
    public void pathCostValidPath() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);


        List<Vertex> path = new ArrayList<>(Arrays.asList(A, B, C));
        double cost = graph.pathCost(path);
        assertTrue( 5.0 == cost);
    }

    @Test
    public void pathCostInvalidPath() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);


        List<Vertex> path = new ArrayList<>(Arrays.asList(A, C, B));
        double cost = graph.pathCost(path);
        assertTrue( -1.0 == cost);
    }

    @Test
    public void removeIfExistingRemove() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);


        Graph hook = new Graph();
        Graph.TestHook testHook = hook.new TestHook();
        testHook.removeIfExistingConnection(A, B);

        assertFalse(graph.getEdges().contains(AB));
    }

    @Test
    public void adjacencyMatrixInvalidEdge() {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);


        Graph hook = new Graph();
        Graph.TestHook testHook = hook.new TestHook();

        Vertex D = Vertex.of("D");
        Edge invalidEdge = Edge.of(D, C, 10.0);
        edges.add(invalidEdge);
        testHook.adjacencyMatrix(vertices, edges);
    }

    @Test
    public void dijkstraValidNominalSimple() throws InvalidPathException {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C));

        Edge AB = Edge.of(A, B, 2.0);
        Edge BC = Edge.of(B, C, 3.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BC));

        Graph graph = Graph.of(vertices, edges);


        List<Vertex> path = graph.minimumCostPath(A, C);

        List<Vertex> expectedPath = new ArrayList<>(Arrays.asList(A, B, C));
        assertEquals(3, path.size());
        assertEquals(expectedPath, path);
    }

    @Test
    public void dijkstraValidSketch() throws InvalidPathException {
        Vertex A = Vertex.of("A");
        Vertex B = Vertex.of("B");
        Vertex C = Vertex.of("C");
        Vertex D = Vertex.of("D");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C, D));

        Edge AB = Edge.of(A, B, 10.0);
        Edge BA = Edge.of(B, A, 2.0);
        Edge AC = Edge.of(A, C, 3.0);
        Edge CB = Edge.of(C, B, 4.0);
        Edge CD = Edge.of(C, D, 8.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BA, AC, CB, CD));

        Graph graph = Graph.of(vertices, edges);


        List<Vertex> ABPath = graph.minimumCostPath(A, B);
        List<Vertex> expectedABPath = new ArrayList<>(Arrays.asList(A, C, B));
        assertEquals(3, ABPath.size());
        assertEquals(expectedABPath, ABPath);


        List<Vertex> BDPath = graph.minimumCostPath(B, D);
        List<Vertex> expectedBDPath = new ArrayList<>(Arrays.asList(B, A, C, D));
        assertEquals(4, BDPath.size());
        assertEquals(expectedBDPath, BDPath);
    }

    @Test(expected = InvalidPathException.class)
    public void dijkstraInvalidSketchNoSourceInGraph() throws InvalidPathException {
        Vertex A = Vertex.of("AA");
        Vertex B = Vertex.of("BB");
        Vertex C = Vertex.of("CC");
        Vertex D = Vertex.of("DD");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C, D));

        Edge AB = Edge.of(A, B, 10.0);
        Edge BA = Edge.of(B, A, 2.0);
        Edge AC = Edge.of(A, C, 3.0);
        Edge CB = Edge.of(C, B, 4.0);
        Edge CD = Edge.of(C, D, 8.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BA, AC, CB, CD));

        Graph graph = Graph.of(vertices, edges);


        Vertex E = Vertex.of("EE");
        List<Vertex> EBPath = graph.minimumCostPath(E, B);
    }

    @Test(expected = InvalidPathException.class)
    public void dijkstraInvalidSketchNoDestinationInGraph() throws InvalidPathException {
        Vertex A = Vertex.of("AA");
        Vertex B = Vertex.of("BB");
        Vertex C = Vertex.of("CC");
        Vertex D = Vertex.of("DD");
        List<Vertex> vertices = new ArrayList<>(Arrays.asList(A, B, C, D));

        Edge AB = Edge.of(A, B, 10.0);
        Edge BA = Edge.of(B, A, 2.0);
        Edge AC = Edge.of(A, C, 3.0);
        Edge CB = Edge.of(C, B, 4.0);
        Edge CD = Edge.of(C, D, 8.0);
        List<Edge> edges = new ArrayList<>(Arrays.asList(AB, BA, AC, CB, CD));

        Graph graph = Graph.of(vertices, edges);


        Vertex E = Vertex.of("EE");
        List<Vertex> BEPath = graph.minimumCostPath(B, E);
    }

    @Test
    public void stressTestLotsSmallGraphs() throws InvalidPathException {
        for (int i = 0; i < 10000; i++) {
            smallStressTest();
        }
    }

    @Test
    public void stressTestBigGraph() throws InvalidPathException {
        Random random = new Random();

        List<Vertex> vertices = new ArrayList<Vertex>();
        int numberVertices = random.nextInt(5000) + 3;
        for (int j = 0; j < numberVertices; j++) {
            String name = Integer.toString(j);
            vertices.add(Vertex.of(name));
        }

        List<Edge> edges = new ArrayList<Edge>();


        for (Vertex source : vertices) {
            for (int k = 0; k < vertices.size(); k++) {
                Vertex dest = vertices.get(k);

                double cost = ThreadLocalRandom.current().nextDouble(0.0, 100.0);

                if (!source.equals(dest)) {
                    edges.add(Edge.of(source, dest, cost));
                }
            }
        }

        Graph graph = Graph.of(vertices, edges);

        Vertex source = vertices.get(random.nextInt(numberVertices - 1));
        Vertex destination = vertices.get(random.nextInt(numberVertices - 1));

        if (source != destination) {

            List<Vertex> path = graph.minimumCostPath(source, destination);
            assertEquals(true, path.size() >= 2);
        }

    }

    private void smallStressTest() throws InvalidPathException {
        Random random = new Random();

        List<Vertex> vertices = new ArrayList<Vertex>();
        int numberVertices = random.nextInt(10) + 3;
        for (int j = 0; j < numberVertices; j++) {
            String name = Integer.toString(j);
            vertices.add(Vertex.of(name));
        }

        List<Edge> edges = new ArrayList<Edge>();


        for (Vertex source : vertices) {
            for (int k = 0; k < vertices.size(); k++) {
                Vertex dest = vertices.get(k);

                double cost = ThreadLocalRandom.current().nextDouble(0.0, 100.0);

                if (!source.equals(dest)) {
                    edges.add(Edge.of(source, dest, cost));
                }
            }
        }



        Graph graph = Graph.of(vertices, edges);

        Vertex source = vertices.get(random.nextInt(numberVertices - 1));
        Vertex destination = vertices.get(random.nextInt(numberVertices - 1));

        if (source != destination) {

            List<Vertex> path = graph.minimumCostPath(source, destination);
            assertEquals(true, path.size() >= 2);
        }
    }
}