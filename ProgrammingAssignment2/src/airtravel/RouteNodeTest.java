package airtravel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;

import static airtravel.FlightPolicyTest.createSimpleFlight;
import static org.junit.jupiter.api.Assertions.*;

class RouteNodeTest {

    @Test
        // tests the build method when Airport and RouteTime are provided and known but previous RouteNode is not
    void validOfNoPrevious() {
        Airport airport = Airport.of("CLE", Duration.ofMinutes(10));
        RouteTime routeTime = new RouteTime(LocalTime.now());

        RouteNode routeNode = RouteNode.of(airport, routeTime, null);

        assertEquals(airport, routeNode.getAirport());
        assertEquals(routeTime, routeNode.getArrivalTime());
        assertNull(routeNode.getPrevious());
    }

    @Test
    // tests the build method when Airport, RouteTime, and previus RouteNode are provided and known
    void validOfYesPrevious() {
        Airport previousAirport = Airport.of("CLE", Duration.ofMinutes(10));
        RouteTime previousRouteTime = new RouteTime(LocalTime.now());
        RouteNode previousRouteNode = RouteNode.of(previousAirport, previousRouteTime, null);

        Airport airport = Airport.of("ORL", Duration.ofMinutes(20));
        RouteTime routeTime = new RouteTime(LocalTime.now().plusMinutes(5));
        RouteNode routeNode = RouteNode.of(airport, routeTime, previousRouteNode);

        assertEquals(airport, routeNode.getAirport());
        assertEquals(routeTime, routeNode.getArrivalTime());
        assertEquals(previousRouteNode, routeNode.getPrevious());
    }

    @Test
    // tests the build method wjen Airport is null
    void invalidOfNullAirport() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            RouteNode.of(null, new RouteTime(LocalTime.now()), null);
        });
    }

    @Test
    // tests the build method wjen RouteTime is null
    void invalidOfNullRouteTime() {
        Airport airport = Airport.of("CLE", Duration.ofMinutes(10));
        Assertions.assertThrows(NullPointerException.class, () -> {
            RouteNode.of(airport, null, null);
        });
    }

    @Test
    // tests the 2nd build method when there is no previous RouteNode
    void validof2NoPrevious() {
        Flight flight = createSimpleFlight(1, 1, 1);
        RouteNode routeNode = RouteNode.of(flight, null);

        assertEquals(flight.destination(), routeNode.getAirport());
        assertEquals(flight.arrivalTime(), routeNode.getArrivalTime().getTime());
    }

    @Test
    // tests the 2nd build method when there is a previous RouteNode
    void validof2YesPrevious() {
        Airport previousAirport = Airport.of("CLE", Duration.ofMinutes(10));
        RouteTime previousRouteTime = new RouteTime(LocalTime.now());
        RouteNode previousRouteNode = RouteNode.of(previousAirport, previousRouteTime, null);

        Flight flight = createSimpleFlight(1, 1, 1);
        RouteNode routeNode = RouteNode.of(flight, previousRouteNode);

        assertEquals(flight.destination(), routeNode.getAirport());
        assertEquals(flight.arrivalTime(), routeNode.getArrivalTime().getTime());
        assertEquals(previousRouteNode, routeNode.getPrevious());
    }

    @Test
    // tests the 2nd build method when there is not a flight
    void invalidOf2NullFlight() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            RouteNode.of(null, null);
        });
    }

    @Test
    // tests the 3rd build method
    void validOf3() {
        Flight flight = createSimpleFlight(1, 1, 1);
        
        RouteNode routeNode = RouteNode.of(flight);

        assertFalse(routeNode.isArrivalTimeKnown());
        assertEquals(flight.getLeg().getDestination().getCode(), routeNode.getAirport().getCode());
    }

    @Test
    void getAirport() {
    }

    @Test
    void getArrivalTime() {
    }

    @Test
    void getPrevious() {
    }

    @Test
    void isArrivalTimeKnown() {
    }

    @Test
    void departureTime() {
    }

    @Test
    void availableFlights() {
    }

    @Test
    void compareTo() {
    }

//    private RouteNode buildRouteNode(String airportCode, RouteNode previousNode) {
//
//    }
}