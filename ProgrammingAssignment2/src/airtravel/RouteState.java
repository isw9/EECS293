package airtravel;

import java.time.LocalTime;
import java.util.*;

public class RouteState {

    private Map<Airport, RouteNode> airportNode = new HashMap<Airport, RouteNode>();

    private final NavigableSet<RouteNode> unreached = new TreeSet<RouteNode>();

    private Set<RouteNode> reached = new HashSet<RouteNode>();

    private RouteState(Set<Airport> airports, Airport origin, LocalTime departureTime) {
        RouteNode originNode = RouteNode.of(origin, new RouteTime(departureTime), null);
        airportNode.put(origin, originNode);

        for (Airport airport : airports) {
            RouteNode airportRouteNode = RouteNode.of(airport, RouteTime.UNKNOWN, null);
            airportNode.put(airport, airportRouteNode);

            // for each airport in airports, add it's RouteNode to the set of unreached RouteNodes
            unreached.add(airportRouteNode);
        }
    }

    public static RouteState of(Set<Airport> airports, Airport origin, LocalTime departureTime) {
        Objects.requireNonNull(airports, "airports cannot be null in RouteState build");
        Objects.requireNonNull(origin, "origin cannot be null in RouteState build");
        Objects.requireNonNull(departureTime, "departureTime cannot be null in RouteState build");

        return new RouteState(airports, origin, departureTime);
    }

    public final void replaceNode(RouteNode routeNode) {
        Airport airport = routeNode.getAirport();
        airportNode.put(airport, routeNode);
    }

    public final boolean allReached() {
        return unreached.size() == 0;
    }

    public final RouteNode closestUnreached() {
        if (allReached()) {
            throw new NoSuchElementException("All airports have been reached");
        }

        return unreached.first();
    }

    //assume the airport has an entry in RouteState based on HW instructions
    public final RouteNode airportNode(Airport airport) {
        return airportNode.get(airport);
    }

}
