package airtravel;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class RouteNode implements Comparable<RouteNode> {

    private final Airport airport;

    private final RouteTime arrivalTime;

    private final RouteNode previous;

    private RouteNode(Airport airport, RouteTime arrivalTime, RouteNode previous) {
        this.airport = airport;
        this.arrivalTime = arrivalTime;
        this.previous = previous;
    }

    public static RouteNode of(Airport airport, RouteTime arrivalTime, RouteNode previous) {
        Objects.requireNonNull(airport, "airport cannot be null in RouteNode build");
        Objects.requireNonNull(arrivalTime, "arrivalTime cannot be null in RouteNode build");

        return new RouteNode(airport, arrivalTime, previous);
    }

    public static RouteNode of(Flight flight, RouteNode previous){
        Objects.requireNonNull(flight, "flight cannot be null in RouteNode build");

        RouteTime flightArrivalTime = new RouteTime(flight.arrivalTime());
        return new RouteNode(flight.destination(), flightArrivalTime, previous);
    }

    public static RouteNode of(Flight flight) {
        Objects.requireNonNull(flight, "flight cannot be null in RouteNode build");

        return new RouteNode(flight.destination(), RouteTime.UNKNOWN, null);
    }

    public Airport getAirport() {
        return this.airport;
    }

    public RouteTime getArrivalTime() {
        return this.arrivalTime;
    }

    public RouteNode getPrevious() {
        return this.previous;
    }

    public final boolean isArrivalTimeKnown() {
        return this.arrivalTime.isKnown();
    }

    public final RouteTime departureTime() {
        RouteTime arrivalTime = this.arrivalTime;

        Duration connectionTime = getAirport().getConnectionTimeMin();

        return arrivalTime.plus(connectionTime);
    }

    public Set<Flight> availableFlights(FareClass fareClass) {
        return airport.availableFlights(departureTime().getTime(), fareClass);
    }

    @Override
    //complexity - 3
    public int compareTo(RouteNode otherRouteNode) {
        RouteTime originalRouteTime = this.getArrivalTime();
        RouteTime otherRouteTime = otherRouteNode.getArrivalTime();

        // if both RouteTimes are known, we can call getTime() safely
        if (originalRouteTime.isKnown() && otherRouteTime.isKnown()) {
            LocalTime originalArrivalTime = this.getArrivalTime().getTime();
            LocalTime otherArrivalTime = otherRouteNode.getArrivalTime().getTime();

            // if the 2 arrival times are the same, break tie based on airport
            if (originalArrivalTime.compareTo(otherArrivalTime) == 0) {
                return compareBasedOnAirport(this, otherRouteNode);
            } else {
                return this.getArrivalTime().compareTo(otherRouteNode.getArrivalTime());
            }
        }
        // one of the RouteTimes is unknown - need to make sure unknown RouteTimes are considered greater than
        // known RouteTimes
        else {
            return unknownRouteTimeCompare(this, otherRouteNode);
        }
    }

    private static int unknownRouteTimeCompare(RouteNode routeNode1, RouteNode routeNode2) {
        RouteTime routeTime1 = routeNode1.getArrivalTime();
        RouteTime routeTime2 = routeNode2.getArrivalTime();

        // if they're both unknown, they need to be sorted based off of airport
        if (!routeTime1.isKnown() && !routeTime2.isKnown()) {
            return compareBasedOnAirport(routeNode1, routeNode2);
        }

        // other wise one of the RouteTimes is known and the other is not known
        // if routeTime1 is known, it needs to come first so we return 1
        return routeTime1.isKnown() ? -1 : 1;
    }

    private static int compareBasedOnAirport(RouteNode routeNode1, RouteNode routeNode2) {
        Airport originalAirport = routeNode1.getAirport();
        Airport otherAirport = routeNode2.getAirport();

        return originalAirport.compareTo(otherAirport);
    }
}
