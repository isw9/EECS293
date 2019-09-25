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
        RouteTime previousArrivalTime = new RouteTime(LocalTime.now());
        RouteNode previousRouteNode = RouteNode.of(previousAirport, previousArrivalTime, null);

        Airport airport = Airport.of("ORL", Duration.ofMinutes(20));
        RouteTime arrivalTime = new RouteTime(LocalTime.now().plusMinutes(5));
        RouteNode routeNode = RouteNode.of(airport, arrivalTime, previousRouteNode);

        assertEquals(airport, routeNode.getAirport());
        assertEquals(arrivalTime, routeNode.getArrivalTime());
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
        RouteTime previousArrivalTime = new RouteTime(LocalTime.now());
        RouteNode previousRouteNode = RouteNode.of(previousAirport, previousArrivalTime, null);

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
    // tests the 3rd build method when no flight is provided
    void invalidOf3() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            RouteNode.of(null);
        });
    }

    @Test
    // tests a valid getAirport setup
    void getAirportValid() {
        Airport airport = Airport.of("CLE", Duration.ofMinutes(10));
        RouteTime arrivalTime = new RouteTime(LocalTime.now());

        RouteNode routeNode = RouteNode.of(airport, arrivalTime, null);

        assertEquals(airport, routeNode.getAirport());
    }

    @Test
    // tests a valid getArrivalTime setup
    void getArrivalTimeValid() {
        Airport airport = Airport.of("CLE", Duration.ofMinutes(10));
        RouteTime arrivalTime = new RouteTime(LocalTime.now());

        RouteNode routeNode = RouteNode.of(airport, arrivalTime, null);

        assertEquals(arrivalTime, routeNode.getArrivalTime());
    }

    @Test
    // tests getting previous when there is no previous
    void getPreviousNone() {
        Airport airport = Airport.of("CLE", Duration.ofMinutes(10));
        RouteTime arrivalTime = new RouteTime(LocalTime.now());

        RouteNode routeNode = RouteNode.of(airport, arrivalTime, null);

        assertNull(routeNode.getPrevious());
    }

    @Test
    // tests getting previous when there is a previous
    void getPreviousYes() {
        Airport previousAirport = Airport.of("CLE", Duration.ofMinutes(10));
        RouteTime previousArrivalTime = new RouteTime(LocalTime.now());
        RouteNode previousRouteNode = RouteNode.of(previousAirport, previousArrivalTime, null);

        Airport airport = Airport.of("ORL", Duration.ofMinutes(20));
        RouteTime arrivalTime = new RouteTime(LocalTime.now().plusMinutes(5));
        RouteNode routeNode = RouteNode.of(airport, arrivalTime, previousRouteNode);

        assertEquals(previousRouteNode, routeNode.getPrevious());
    }

    @Test
    // tests getting the departureTime
    void departureTime() {
        Duration connectionTime = Duration.ofMinutes(10);
        Airport airport = Airport.of("CLE", connectionTime);
        RouteTime arrivalTime = new RouteTime(LocalTime.now());

        RouteNode routeNode = RouteNode.of(airport, arrivalTime, null);

        assertEquals(arrivalTime.plus(connectionTime).getTime(), routeNode.departureTime().getTime());
    }

    @Test
    // tests if the arrival time is known when it is not
    void isArrivalTimeKnownNo() {
        Flight flight = createSimpleFlight(1, 1, 1);

        RouteNode routeNode = RouteNode.of(flight);

        assertFalse(routeNode.isArrivalTimeKnown());
    }

    @Test
    // tests if the arrival time is known when it is
    void isArrivalTimeKnownYes() {
        Airport airport = Airport.of("CLE", Duration.ofMinutes(10));
        RouteTime arrivalTime = new RouteTime(LocalTime.now());

        RouteNode routeNode = RouteNode.of(airport, arrivalTime, null);

        assertTrue(routeNode.isArrivalTimeKnown());
    }

    @Test
    // tests the overridden compare to method in the valid case of different arrival times
    void compareToValid() {
        Airport airport1 = Airport.of("CLE", Duration.ofMinutes(10));
        RouteTime arrivalTime1 = new RouteTime(LocalTime.now());
        RouteNode routeNode1 = RouteNode.of(airport1, arrivalTime1, null);

        Airport airport2 = Airport.of("ORL", Duration.ofMinutes(20));
        RouteTime arrivalTime2 = new RouteTime(LocalTime.now().plusMinutes(5));
        RouteNode routeNode2 = RouteNode.of(airport2, arrivalTime2, null);

        assertEquals(-1, routeNode1.compareTo(routeNode2));
    }

    @Test
    // tests the overridden compare to method in the valid case of the same arrival time
    void compareToValidSameArrivalTime() {
        Airport airport1 = Airport.of("CLE", Duration.ofMinutes(10));
        RouteTime arrivalTime = new RouteTime(LocalTime.now());
        RouteNode routeNode1 = RouteNode.of(airport1, arrivalTime, null);

        Airport airport2 = Airport.of("ORL", Duration.ofMinutes(20));
        RouteNode routeNode2 = RouteNode.of(airport2, arrivalTime, null);

        assertEquals(airport1.compareTo(airport2), routeNode1.compareTo(routeNode2));
    }

}