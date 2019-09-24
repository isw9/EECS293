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
    public int compareTo(RouteNode otherRouteNode) {
        // if the 2 arrival times are the same, break tie based on airport
        LocalTime originalArrivalTime = this.getArrivalTime().getTime();
        LocalTime otherArrivalTime = otherRouteNode.getArrivalTime().getTime();

        if (originalArrivalTime.compareTo(otherArrivalTime) == 0) {
            Airport originalAirport = this.getAirport();
            Airport otherAirport = otherRouteNode.getAirport();

            return originalAirport.compareTo(otherAirport);
        }
        else {
            return this.getArrivalTime().compareTo(otherRouteNode.getArrivalTime());
        }
    }
}
