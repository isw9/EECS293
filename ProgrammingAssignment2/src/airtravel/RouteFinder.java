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

    // complexity of 4
    public final RouteNode route(Airport origin, Airport destination, LocalTime departureTime, FareClass fareClass) {
        RouteState state = RouteState.of(airports, origin, departureTime);
        RouteNode destinationNode = state.airportNode(destination);

        while (!state.allReached()) {
            RouteNode currentNode = state.closestUnreached();

            if (currentNode.getAirport().equals(destination)) {
                return currentNode;
            }

            for (Flight flight : currentNode.availableFlights(fareClass)) {

                RouteTime flightRouteTime = new RouteTime(flight.arrivalTime());
                if (flightRouteTime.compareTo(destinationNode.getArrivalTime()) < 0) {
                    destinationNode = RouteNode.of(flight.origin(), flightRouteTime, destinationNode);
                }
            }
        }

        return null;
    }
}
