package airtravel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RouteStateTest {

    @Test
    // tests the valid case of creating a RouteState
    void ofValid() {
        Set<Airport> set = new HashSet<Airport>();
        set.add(Airport.of("OLR", Duration.ofMinutes(10)));
        set.add(Airport.of("LAX", Duration.ofMinutes(20)));
        set.add(Airport.of("JFK", Duration.ofMinutes(30)));

        Airport origin = Airport.of("CLE", Duration.ofMinutes(10));
        LocalTime departureTime = LocalTime.now();

        RouteState routeState = RouteState.of(set, origin, departureTime);

        assertFalse(routeState.allReached());
    }

    @Test
    // tests creating a RouteState when the Airport Set is null
    void ofInvalidNullAirportSet() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            RouteState.of(null, Airport.of("CLE", Duration.ofMinutes(10)), LocalTime.now());
        });
    }

    @Test
    // tests creating a RouteState when the Airport is null
    void ofInvalidNullAirport() {
        Set<Airport> set = new HashSet<Airport>();
        set.add(Airport.of("OLR", Duration.ofMinutes(10)));
        set.add(Airport.of("LAX", Duration.ofMinutes(20)));
        set.add(Airport.of("JFK", Duration.ofMinutes(30)));

        Assertions.assertThrows(NullPointerException.class, () -> {
            RouteState.of(set, null, LocalTime.now());
        });
    }

    @Test
    // tests creating a RouteState when the DepartureTime is null
    void ofInvalidNullDepartureTime() {
        Set<Airport> set = new HashSet<Airport>();
        set.add(Airport.of("OLR", Duration.ofMinutes(10)));
        set.add(Airport.of("LAX", Duration.ofMinutes(20)));
        set.add(Airport.of("JFK", Duration.ofMinutes(30)));

        Assertions.assertThrows(NullPointerException.class, () -> {
            RouteState.of(set, Airport.of("CLE", Duration.ofMinutes(10)), null);
        });
    }

    @Test
    // tests the allReached() method is false when not everything has been reached
    void allReachedValid() {
        Set<Airport> set = new HashSet<Airport>();
        set.add(Airport.of("OLR", Duration.ofMinutes(10)));
        set.add(Airport.of("LAX", Duration.ofMinutes(20)));
        set.add(Airport.of("JFK", Duration.ofMinutes(30)));

        Airport origin = Airport.of("CLE", Duration.ofMinutes(10));
        LocalTime departureTime = LocalTime.now();

        RouteState routeState = RouteState.of(set, origin, departureTime);

        assertFalse(routeState.allReached());
    }

    @Test
    // tests getting the closestUnreached RouteNode
    void closestUnreached() {
        Set<Airport> set = new HashSet<Airport>();
        set.add(Airport.of("OLR", Duration.ofMinutes(10)));
        set.add(Airport.of("LAX", Duration.ofMinutes(20)));
        Airport expectedAiport = Airport.of("JFK", Duration.ofMinutes(30));
        set.add(expectedAiport);

        Airport origin = Airport.of("CLE", Duration.ofMinutes(10));
        LocalTime departureTime = LocalTime.now();

        RouteState routeState = RouteState.of(set, origin, departureTime);

        RouteNode routeNode = routeState.closestUnreached();

        assertEquals(expectedAiport, routeNode.getAirport());
    }

    @Test
    // tests setting the RouteNode for an Airport
    void airportNode() {
        Set<Airport> set = new HashSet<Airport>();
        set.add(Airport.of("OLR", Duration.ofMinutes(10)));
        set.add(Airport.of("LAX", Duration.ofMinutes(20)));
        set.add(Airport.of("JFK", Duration.ofMinutes(30)));

        Airport origin = Airport.of("CLE", Duration.ofMinutes(10));
        LocalTime departureTime = LocalTime.now();

        RouteState routeState = RouteState.of(set, origin, departureTime);

        RouteNode routeNode = routeState.airportNode(origin);

        assertEquals(origin, routeNode.getAirport());
    }
}