Programming Assignment 11 Architecture

For this program, I want to do is to be able to support finding the minimum cost
path (using Dijkstra’s algorithm) between different locations. The locations can be
different types (such as network routers, bus stops, etc) so they will be represented by
a Vertex class.

# Vertex class
This class captures the abstraction of a location. This can be anything from a network router
to a bus stop to a building.

private String name

public constructor (String name)

public String getName



# Edge class
This class captures the abstraction of a connection between two different Vertices.
Vertices have the potential to be connected by an Edge. An Edge contains a source
Vertex, a destination Vertex, and a cost that represents how expensive it is to go from
the source Vertex to the destination Vertex. The cost must be non-negative. Closest value
error handling will be used to replace any negative cost with a 0.


private Vertex source
private Vertex destination
private double cost

public constructor (Vertex source, Vertex destination, double cost)
  -> validate cost must be non negative

public Vertex getSource
public Vertex getDestination
public double getCost


# Graph class

The Graph class will contain a list of Vertices and a list of Edges. This class captures
the abstraction level of the Edges and Vertices as a whole. It relates to Vertex and Edge
through containment.

private int[][] adjacency matrix to represent connections
private List<Vertex> vertices
private List<Edge> edges

public constructor (List<Vertex>, List of Edges)
  -> will populate the 2d adjacency matrix

public addEdge(source, destination, cost)
 -> adds an Edge with the given cost to the Graph
 -> also populates 2d adjacency matrix

public addVertex(vertexToAdd)
  -> adds a Vertex to the Graph

public int edgeWeight(Vertex A, vertex B)
  -> gets the edge weight between A and B from the adjacency matrix
  -> returns -1 if there is no connection between A and B


# PathCalculator class

The PathCalculator class will basically be used as a driver to implement Dijkstra’s algorithm.
It has the highest level of abstraction and is used to easily calculate the shortest path.

private Graph graph

public constructor(Graph graph)

public List<List<Vertex>> dijkstrasShortestPath(Vertex sourceVertex)
  -> will perform Dijkstra’s algorithm and calculate the shortest path to each vertex in the graph
  -> pseudocode for algorithm will be based on Section 14.2 of Algorithm Design and Applications
  -> will return the minimum cost path connecting sourceVertex to each Vertex in the Graph

public int costOfPath(List<Vertex>)
  -> returns the total cost of taking this path




# Error handling

My main approach will be to address errors locally. I will use a strategy of closest valid value,
especially as it relates to the edge weight. If a user tries to create an edge with an invalid value,
I will substitute the closest valid value (0) for the edge weight. In the PathCalculator class I will lean
towards favoring correctness over robustness because it is important that the paths it return be correct.

# Testing

In my approach to testing, I will follow the guidelines discussed earlier in the semester to ensure
full code coverage and branch coverage as well as boundary and stress tests. I will also sketch out a
couple of full examples on paper using Dijkstra’s algorithm to make sure that the PathCalculator
implements this correctly.
