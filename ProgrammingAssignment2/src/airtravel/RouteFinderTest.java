package airtravel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RouteFinderTest {

    @Test
    // tests the valid case of creating a RouteFinder
    void ofValid() {
        Set<Airport> set = new HashSet<Airport>();
        set.add(Airport.of("OLR", Duration.ofMinutes(10)));
        set.add(Airport.of("LAX", Duration.ofMinutes(20)));
        set.add(Airport.of("JFK", Duration.ofMinutes(30)));

        RouteFinder routeFinder =  RouteFinder.of(set);

        assertNotNull(routeFinder);
    }

    @Test
    // tests the case of creating a RouteFinder when the set is null
    void ofInvalid() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            RouteFinder.of(null);
        });
    }

    @Test
    // tests finding a valid route
    void route() {

        Airport origin = Airport.of("CLE", Duration.ofMinutes(10));
        Airport destination = Airport.of("ORL", Duration.ofMinutes(20));
        LocalTime departureTime = LocalTime.now();
        FareClass fareClass = FareClass.of(SeatClass.BUSINESS, 100);

        Set<Airport> set = new HashSet<Airport>();
        set.add(Airport.of("LAX", Duration.ofMinutes(30)));
        set.add(Airport.of("SFO", Duration.ofMinutes(40)));

        RouteFinder routeFinder = RouteFinder.of(set);
    }
}