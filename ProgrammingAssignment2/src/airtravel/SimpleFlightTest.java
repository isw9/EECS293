package airtravel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleFlightTest {

    @Test
    // tests building a valid SimpleFlight
    void validOf() {
        Airport origin = Airport.of("CLE", Duration.ofHours(1));
        Airport destination = Airport.of("ORL", Duration.ofHours(2));
        Leg leg = Leg.of(origin, destination);

        LocalTime departureTime = LocalTime.now().plusHours(1);
        LocalTime arrivalTime = LocalTime.now();
        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);

        Flight flight = SimpleFlight.of("CLE", leg, schedule);

        assertEquals(flight.getCode(), "CLE");
    }

    @Test
    // tests building a SimpleFlight when the code is null
    void nullCodeOf() {
        Airport origin = Airport.of("CLE", Duration.ofHours(1));
        Airport destination = Airport.of("ORL", Duration.ofHours(2));
        Leg leg = Leg.of(origin, destination);

        LocalTime departureTime = LocalTime.now().plusHours(1);
        LocalTime arrivalTime = LocalTime.now();
        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);

        Assertions.assertThrows(NullPointerException.class, () -> {
            SimpleFlight.of(null, leg, schedule);
        });
    }

    @Test
    // tests building a SimpleFlight when the code is null
    void nullLegOf() {
        LocalTime departureTime = LocalTime.now().plusHours(1);
        LocalTime arrivalTime = LocalTime.now();
        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);

        Assertions.assertThrows(NullPointerException.class, () -> {
            SimpleFlight.of("CLE", null, schedule);
        });
    }

    @Test
    // tests building a SimpleFlight when the schedule is null
    void nullScheduleOf() {
        Airport origin = Airport.of("CLE", Duration.ofHours(1));
        Airport destination = Airport.of("ORL", Duration.ofHours(2));
        Leg leg = Leg.of(origin, destination);

        Assertions.assertThrows(NullPointerException.class, () -> {
            SimpleFlight.of("CLE", leg, null);
        });
    }

    @Test
    // tests getting the Code from a SimpleFlight
    void getCode() {
        Airport origin = Airport.of("CLE", Duration.ofHours(1));
        Airport destination = Airport.of("ORL", Duration.ofHours(2));
        Leg leg = Leg.of(origin, destination);

        LocalTime departureTime = LocalTime.now().plusHours(1);
        LocalTime arrivalTime = LocalTime.now();
        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);

        Flight flight = SimpleFlight.of("CLE", leg, schedule);

        assertEquals("CLE", flight.getCode());
    }

    @Test
    // tests getting the Leg from a SimpleFlight
    void getLeg() {
        Airport origin = Airport.of("CLE", Duration.ofHours(1));
        Airport destination = Airport.of("ORL", Duration.ofHours(2));
        Leg leg = Leg.of(origin, destination);

        LocalTime departureTime = LocalTime.now().plusHours(1);
        LocalTime arrivalTime = LocalTime.now();
        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);

        Flight flight = SimpleFlight.of("CLE", leg, schedule);

        assertEquals(leg, flight.getLeg());
    }

    @Test
    // tests getting the flight schedule from a SimpleFlight
    void getFlightSchedule() {
        Airport origin = Airport.of("CLE", Duration.ofHours(1));
        Airport destination = Airport.of("ORL", Duration.ofHours(2));
        Leg leg = Leg.of(origin, destination);

        LocalTime departureTime = LocalTime.now().plusHours(1);
        LocalTime arrivalTime = LocalTime.now();
        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);

        Flight flight = SimpleFlight.of("CLE", leg, schedule);

        assertEquals(schedule, flight.getFlightSchedule());
    }
}