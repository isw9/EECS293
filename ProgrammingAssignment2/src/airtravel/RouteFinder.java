package airtravel;

import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

public final class RouteFinder {

    private final Set<Airport> airports;

    private RouteFinder(Set<Airport> airports) {
        this.airports = airports;
    }

    public static RouteFinder of(Set<Airport> airports) {
        Objects.requireNonNull(airports, "airports cannot be null in RouteFinder build method");

        return new RouteFinder(airports);
    }

    // complexity of 2
    public final RouteNode route(Airport origin, Airport destination, LocalTime departureTime, FareClass fareClass) {
        Objects.requireNonNull(origin, "origin can't be null in route method");
        Objects.requireNonNull(destination, "destination can't be null in route method");
        Objects.requireNonNull(departureTime, "departureTime can't be null in route method");
        Objects.requireNonNull(fareClass, "fareClass can't be null in route method");

        RouteState state = RouteState.of(airports, origin, departureTime);
        RouteNode destinationNode = state.airportNode(destination);

        while (!state.allReached()) {

            RouteNode currentNode = state.closestUnreached();


            if (currentNode.getAirport().equals(destination)) {
                return currentNode;
            }

            destinationNode = findNewDestinationNode(currentNode, fareClass, destinationNode);
        }

        return null;
    }

    private static RouteNode findNewDestinationNode(RouteNode currentNode, FareClass fareClass, RouteNode destinationNode) {
        for (Flight flight : currentNode.availableFlights(fareClass)) {

            RouteTime flightRouteTime = new RouteTime(flight.arrivalTime());
            if (flightRouteTime.compareTo(destinationNode.getArrivalTime()) < 0) {
                destinationNode = RouteNode.of(flight.origin(), flightRouteTime, destinationNode);
            }
        }

        return destinationNode;
    }
}
