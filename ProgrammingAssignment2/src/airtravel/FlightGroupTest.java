package airtravel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightGroupTest {

    @Test
    //tests building a valid FlightGroup
    void validOf() {
        Airport origin = Airport.of("CLE", Duration.ofHours(1));

        FlightGroup group = FlightGroup.of(origin);

        assertEquals("CLE", group.getOrigin().getCode());
        assertEquals(origin, group.getOrigin());
    }

    @Test
    // tests building an FlightGroup when the Airport is null
    void ofInvalidDuration() {
        Airport airport = null;

        Assertions.assertThrows(NullPointerException.class, () -> {
            FlightGroup.of(airport);
        });
    }

    @Test
    // tests adding a Flight to a FlightGroup in the nominal case
    void add() {
        Airport origin = Airport.of("CLE", Duration.ofHours(1));
        Airport destination = Airport.of("ORL", Duration.ofHours(2));
        Leg leg = Leg.of(origin, destination);
        EnumMap<SeatClass, Integer> map = new EnumMap<>(SeatClass.class);
        map.put(SeatClass.BUSINESS, 0);
        SeatConfiguration seatConfiguration = SeatConfiguration.of(map);

        LocalTime arrivalTime = LocalTime.now().plusMinutes(1);
        LocalTime departureTime = LocalTime.now();
        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);

        FlightGroup group = FlightGroup.of(origin);

        Flight flight = SimpleFlight.of("CLE", leg, schedule, seatConfiguration);

        group.add(flight);

        assertEquals(1, group.flightsAtOrAfter(departureTime).size());
    }

    @Test
    // tests removing a Flight from a FlightGroup in the nominal case
    void remove() {
        Airport origin = Airport.of("CLE", Duration.ofHours(1));
        Airport destination = Airport.of("ORL", Duration.ofHours(2));
        Leg leg = Leg.of(origin, destination);

        LocalTime arrivalTime = LocalTime.now().plusMinutes(1);
        LocalTime departureTime = LocalTime.now();
        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);
        EnumMap<SeatClass, Integer> map = new EnumMap<>(SeatClass.class);
        map.put(SeatClass.BUSINESS, 0);
        SeatConfiguration seatConfiguration = SeatConfiguration.of(map);

        FlightGroup group = FlightGroup.of(origin);

        Flight flight = SimpleFlight.of("CLE", leg, schedule, seatConfiguration);

        group.add(flight);

        assertEquals(1, group.flightsAtOrAfter(departureTime).size());

        group.remove(flight);

        assertEquals(0, group.flightsAtOrAfter(departureTime).size());
    }

    @Test
    // tests that only flights on or after a given departure time are returned
    void flightsAtOrAfter() {
        Airport origin = Airport.of("CLE", Duration.ofHours(1));
        Airport destination = Airport.of("ORL", Duration.ofHours(2));
        Leg leg = Leg.of(origin, destination);
        EnumMap<SeatClass, Integer> map = new EnumMap<>(SeatClass.class);
        map.put(SeatClass.BUSINESS, 0);
        SeatConfiguration seatConfiguration = SeatConfiguration.of(map);

        LocalTime arrivalTime = LocalTime.now().plusMinutes(1);
        LocalTime departureTime = LocalTime.now();
        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);

        FlightGroup group = FlightGroup.of(origin);

        Flight flight = SimpleFlight.of("CLE", leg, schedule, seatConfiguration);

        group.add(flight);

        assertEquals(1, group.flightsAtOrAfter(departureTime).size());

        assertEquals(0, group.flightsAtOrAfter(departureTime.plusSeconds(1)).size());
    }

    @Test
    // tests the nominal case of getting the origin airport of a FlightGroup
    void getOrigin() {
        Airport origin = Airport.of("CLE", Duration.ofHours(1));

        FlightGroup group = FlightGroup.of(origin);

        assertEquals(origin, group.getOrigin());
    }
}