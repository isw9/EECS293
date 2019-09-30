package airtravel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RouteTimeTest {

    @Test
    // tests isKnown when the time is known
    void isKnownTrue() {
        RouteTime routeTime = new RouteTime(LocalTime.now());
        assertTrue(routeTime.isKnown());
    }

    @Test
    // tests isKnown when the time is not known
    void isKnownFalse() {
        RouteTime routeTime = new RouteTime(null);

        assertFalse(routeTime.isKnown());
    }

    @Test
    // tests getting the time when the time is valid and known
    void getTimeValid() {
        LocalTime localTime = LocalTime.now();
        RouteTime routeTime = new RouteTime(localTime);

        assertEquals(localTime, routeTime.getTime());
    }

    @Test
    // tests getting the time throws an IllegealStateException when the time is null
    void getTimeInvalid() {
        RouteTime routeTime = new RouteTime(null);

        Assertions.assertThrows(IllegalStateException.class, routeTime::getTime);
    }

    @Test
    // tests adding a Duration to a LocalTime
    void plusValid() {
        LocalTime localTime = LocalTime.now();
        RouteTime routeTime = new RouteTime(localTime);
        RouteTime newRouteTime = routeTime.plus(Duration.ofHours(1));

        assertEquals(localTime.plusMinutes(60), newRouteTime.getTime());
    }

    @Test
    // tests adding a Duration to a LocalTime when the original LocalTime is null
    void plusInvalid() {
        RouteTime routeTime = new RouteTime(null);
        RouteTime newRouteTime = routeTime.plus(Duration.ofHours(1));

        assertEquals(RouteTime.UNKNOWN, newRouteTime);
    }

    @Test
    // tests the overridden compareTo method when both RouteTimes have a valid LocalTime
    void compareToValid() {
        RouteTime smallRouteTime = new RouteTime(LocalTime.now());
        RouteTime largeRouteTime = new RouteTime(LocalTime.now().plusMinutes(5));

        int comparison = smallRouteTime.compareTo(largeRouteTime);

        assertEquals(-1, comparison);
    }

    @Test
    // tests the overridden compareTo method when one RouteTime has a null LocalTime
    // (unknown times are basically treated as infinite)
    void compareToInvalidLocalTime() {
        RouteTime smallRouteTime = new RouteTime(LocalTime.now());
        RouteTime largeRouteTime = new RouteTime(null);

        int comparison = smallRouteTime.compareTo(largeRouteTime);

        assertEquals(-1, comparison);
    }

    @Test
    // tests the overridden compareTo method when both RouteTimes have a null LocalTime
    void compareToTwoInvalidLocalTime() {
        RouteTime smallRouteTime = new RouteTime(null);
        RouteTime largeRouteTime = new RouteTime(null);

        int comparison = smallRouteTime.compareTo(largeRouteTime);

        assertEquals(0, comparison);
    }

    @Test
    // tests the overridden compareTo method when both RouteTimes have the same LocalTime
    void compareToValidSameLocalTime() {
        LocalTime time = LocalTime.now();
        RouteTime smallRouteTime = new RouteTime(time);
        RouteTime largeRouteTime = new RouteTime(time);

        int comparison = smallRouteTime.compareTo(largeRouteTime);

        assertEquals(0, comparison);
    }
}