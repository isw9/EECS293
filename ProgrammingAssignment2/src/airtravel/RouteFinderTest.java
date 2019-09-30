package airtravel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.EnumMap;
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
        Airport middle = Airport.of("SFO", Duration.ofMinutes(40));
        Airport destination = Airport.of("ORL", Duration.ofMinutes(20));
        LocalTime departureTime = LocalTime.now();
        FareClass fareClass = FareClass.of(SeatClass.BUSINESS, 100);

        Set<Airport> set = new HashSet<Airport>();
        Airport LAX = Airport.of("LAX", Duration.ofMinutes(30));
        RouteNode laxNode = RouteNode.of(LAX, new RouteTime(LocalTime.now()), null);
        set.add(LAX);
        set.add(Airport.of("SFO", Duration.ofMinutes(40)));

        // build first flight
        Leg leg = Leg.of(origin, middle);
        EnumMap<SeatClass, Integer> map = new EnumMap<>(SeatClass.class);
        map.put(SeatClass.BUSINESS, 10);
        SeatConfiguration seatConfiguration = SeatConfiguration.of(map);
        LocalTime arrivalTimeFlightOne = LocalTime.now().plusMinutes(10);
        LocalTime departureTimeFlightOne = LocalTime.now().plusMinutes(5);
        FlightSchedule schedule = FlightSchedule.of(departureTimeFlightOne, arrivalTimeFlightOne);
        Flight flight1 = SimpleFlight.of("flight1", leg, schedule, seatConfiguration);

        // build second flight
        Leg secondLeg = Leg.of(middle, destination);
        LocalTime arrivalTimeFlightTwo = LocalTime.now().plusMinutes(50);
        LocalTime departureTimeFlightTwo = LocalTime.now().plusMinutes(55);
        FlightSchedule scheduleTwo = FlightSchedule.of(arrivalTimeFlightTwo, departureTimeFlightTwo);
        Flight flight2 = SimpleFlight.of("flight2", secondLeg, scheduleTwo, seatConfiguration);

        RouteFinder routeFinder = RouteFinder.of(set);
    }
}