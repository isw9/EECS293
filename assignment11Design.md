Programming Assignment 11 Architecture

For this program, I want to do is to be able to support finding the minimum cost
path (using Dijkstra’s algorithm) between different locations. The locations can be
different types (such as network routers, bus stops, etc) so they will be represented by
a Vertex class.

# Vertex class

This class captures the abstraction of a location. This can be anything from a network router
to a bus stop to a building.

private String name

public Vertex (String name)

public String getName

Override
equals(Object vertex)
  -> returns true if and only if the names of the vertices are the same

# Edge class

This class captures the abstraction of a connection between two different Vertices.
Vertices have the potential to be connected by an Edge. An Edge contains a source
Vertex, a destination Vertex, and a cost that represents how expensive it is to go from
the source Vertex to the destination Vertex. The cost must be non-negative. Closest value
error handling will be used to replace any negative cost with a 0. The Edge class relates
to the Vertex class through containment


private Vertex source
private Vertex destination
private double cost

public constructor (Vertex source, Vertex destination, double cost)
  -> validate cost must be non negative

public Vertex getSource
public Vertex getDestination
public double getCost

Override
equals(Object edge)
  -> returns true if and only if the two vertices are equal and the cost is the same

# Graph class

The Graph class will contain a list of Vertices and a list of Edges. This class captures
the abstraction level of the Edges and Vertices as a whole. It relates to Vertex and Edge
through containment. Note this is a Directed graph as the cost to travel from
Vertex A to Vertex B could be different than the cost to travel from Vertex B to Vertex A.

private int[][] adjacency matrix to represent connections
private List<Vertex> vertices
private List<Edge> edges

public Graph (List<Vertex>, List<Edges>)
  -> will populate the 2d adjacency matrix

public addEdge(source, destination, cost)
 -> adds an Edge with the given cost to the Graph
 -> also populates 2d adjacency matrix

public addVertex(vertexToAdd)
  -> adds a Vertex to the Graph only if a Vertex with that name is not already in the Graph

public int edgeWeight(Vertex source, vertex destination)
  -> gets the edge weight between A and B from the adjacency matrix
  -> returns -1 if there is no connection between A and B

public boolean contains(Vertex vertex)
  -> returns whether the Graph contains the vertex or not


# RouteFinder class

The RouteFinder class will basically be used as a driver to implement Dijkstra’s algorithm.
It has the highest level of abstraction and is used to easily calculate the shortest path.

private Graph graph

public RouteFinder(Graph graph)

public List<Vertex> minimumCostPath(Vertex sourceVertex, Vertex destinationVertex)
                          throws InvalidPathException
  -> will perform Dijkstra’s algorithm and calculate the shortest path to each vertex in the graph
  -> pseudocode for algorithm will be based on Section 14.2 of Algorithm Design and Applications
  -> will return the minimum cost path connecting sourceVertex to destinationVertex
  -> will throw an InvalidPathException if either vertex is not in the graph or if there is
     no valid path between the two vertices

public int costOfPath(List<Vertex>)
  -> returns the total cost of taking this path


#InvalidPathException class

A class used to represent a custom exception

public constructor InvalidPathException(String errorMessage)




# Error handling

My main approach will be to address errors locally. I will use a strategy of closest valid value,
especially as it relates to the edge weight. If a user tries to create an edge with an invalid value,
I will substitute the closest valid value (0) for the edge weight. In the RouteFinder class I will lean
towards favoring correctness over robustness because it is important that the paths it return be correct.

I will also define a custom InvalidPathException that will be thrown if either the sourceVertex
or destinationVertex is not in the Graph or if there is no path between the two vertices.

# Testing

In my approach to testing, I will follow the guidelines discussed earlier in the semester to ensure
full code coverage and branch coverage as well as boundary and stress tests. I have also sketched
a small example of a Graph containing multiple Vertices and Edges. See the attached pdf of drawn out examples for different test cases.

#Similarities and Differences

Although this project is an extension of Programming Assignment 5, I decided to refactor the architecture
because of the lack of generality the original program provided. The original project only focused on
Airplane routes, so I decided to write this program at a higher level of abstraction in order
to give the user more flexibility in how they wanted to use the program.

an Airport is analogous to a Vertex
a Flight is analogous to an Edge
a FlightSchedule is analogous to a Graph as it contains both Vertices and Edges
a RouteFinder is analogous to a RouteFinder
