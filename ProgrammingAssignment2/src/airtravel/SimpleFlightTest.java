package airtravel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleFlightTest {

    @Test
    // tests building a valid SimpleFlight
    void validOf() {
        Airport origin = Airport.of("CLE", Duration.ofHours(1));
        Airport destination = Airport.of("ORL", Duration.ofHours(2));
        Leg leg = Leg.of(origin, destination);
        EnumMap<SeatClass, Integer> map = new EnumMap<>(SeatClass.class);
        map.put(SeatClass.BUSINESS, 0);
        SeatConfiguration seatConfiguration = SeatConfiguration.of(map);

        LocalTime arrivalTime = LocalTime.now().plusMinutes(1);
        LocalTime departureTime = LocalTime.now();
        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);

        Flight flight = SimpleFlight.of("CLE", leg, schedule, seatConfiguration);

        assertEquals(flight.getCode(), "CLE");
    }

    @Test
    // tests building a SimpleFlight when the code is null
    void nullCodeOf() {
        Airport origin = Airport.of("CLE", Duration.ofHours(1));
        Airport destination = Airport.of("ORL", Duration.ofHours(2));
        Leg leg = Leg.of(origin, destination);
        EnumMap<SeatClass, Integer> map = new EnumMap<>(SeatClass.class);
        map.put(SeatClass.BUSINESS, 0);
        SeatConfiguration seatConfiguration = SeatConfiguration.of(map);

        LocalTime arrivalTime = LocalTime.now().plusMinutes(1);
        LocalTime departureTime = LocalTime.now();
        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);

        Assertions.assertThrows(NullPointerException.class, () -> {
            SimpleFlight.of(null, leg, schedule, seatConfiguration);
        });
    }

    @Test
    // tests building a SimpleFlight when the code is null
    void nullLegOf() {
        LocalTime arrivalTime = LocalTime.now().plusMinutes(1);
        LocalTime departureTime = LocalTime.now();
        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);
        EnumMap<SeatClass, Integer> map = new EnumMap<>(SeatClass.class);
        map.put(SeatClass.BUSINESS, 0);
        SeatConfiguration seatConfiguration = SeatConfiguration.of(map);

        Assertions.assertThrows(NullPointerException.class, () -> {
            SimpleFlight.of("CLE", null, schedule, seatConfiguration);
        });
    }

    @Test
    // tests building a SimpleFlight when the schedule is null
    void nullScheduleOf() {
        Airport origin = Airport.of("CLE", Duration.ofHours(1));
        Airport destination = Airport.of("ORL", Duration.ofHours(2));
        Leg leg = Leg.of(origin, destination);
        EnumMap<SeatClass, Integer> map = new EnumMap<>(SeatClass.class);
        map.put(SeatClass.BUSINESS, 0);
        SeatConfiguration seatConfiguration = SeatConfiguration.of(map);

        Assertions.assertThrows(NullPointerException.class, () -> {
            SimpleFlight.of("CLE", leg, null, seatConfiguration);
        });
    }

    @Test
    // tests getting the Code from a SimpleFlight
    void getCode() {
        Airport origin = Airport.of("CLE", Duration.ofHours(1));
        Airport destination = Airport.of("ORL", Duration.ofHours(2));
        Leg leg = Leg.of(origin, destination);
        EnumMap<SeatClass, Integer> map = new EnumMap<>(SeatClass.class);
        map.put(SeatClass.BUSINESS, 0);
        SeatConfiguration seatConfiguration = SeatConfiguration.of(map);

        LocalTime arrivalTime = LocalTime.now().plusMinutes(1);
        LocalTime departureTime = LocalTime.now();
        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);

        Flight flight = SimpleFlight.of("CLE", leg, schedule, seatConfiguration);

        assertEquals("CLE", flight.getCode());
    }

    @Test
    // tests getting the Leg from a SimpleFlight
    void getLeg() {
        Airport origin = Airport.of("CLE", Duration.ofHours(1));
        Airport destination = Airport.of("ORL", Duration.ofHours(2));
        Leg leg = Leg.of(origin, destination);
        EnumMap<SeatClass, Integer> map = new EnumMap<>(SeatClass.class);
        map.put(SeatClass.BUSINESS, 0);
        SeatConfiguration seatConfiguration = SeatConfiguration.of(map);

        LocalTime arrivalTime = LocalTime.now().plusMinutes(1);
        LocalTime departureTime = LocalTime.now();
        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);

        Flight flight = SimpleFlight.of("CLE", leg, schedule, seatConfiguration);

        assertEquals(leg, flight.getLeg());
    }

    @Test
    // tests getting the flight schedule from a SimpleFlight
    void getFlightSchedule() {
        Airport origin = Airport.of("CLE", Duration.ofHours(1));
        Airport destination = Airport.of("ORL", Duration.ofHours(2));
        Leg leg = Leg.of(origin, destination);
        EnumMap<SeatClass, Integer> map = new EnumMap<>(SeatClass.class);
        map.put(SeatClass.BUSINESS, 0);
        SeatConfiguration seatConfiguration = SeatConfiguration.of(map);

        LocalTime arrivalTime = LocalTime.now().plusMinutes(1);
        LocalTime departureTime = LocalTime.now();
        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);

        Flight flight = SimpleFlight.of("CLE", leg, schedule, seatConfiguration);

        assertEquals(schedule, flight.getFlightSchedule());
    }
}