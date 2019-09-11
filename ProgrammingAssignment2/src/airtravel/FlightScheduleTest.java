package airtravel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FlightScheduleTest {

    @Test
    // tests building a valid Flight Schedule
    void ofValid() {
        LocalTime departureTime = LocalTime.now();
        LocalTime arrivalTime = LocalTime.now().plusHours(1);

        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);

        assertEquals(departureTime, schedule.getDepartureTime());
        assertEquals(arrivalTime, schedule.getArrivalTime());
    }

    @Test
    // tests building a FlightSchedule when DepartureTime is null
    void ofInvalidDepartureTime() {
        LocalTime arrivalTime = null;
        LocalTime departureTime = LocalTime.now().plusHours(1);

        Assertions.assertThrows(NullPointerException.class, () -> {
            FlightSchedule.of(departureTime, arrivalTime);
        });
    }

    @Test
    // tests building a FlightSchedule when ArrivalTime is null
    void ofInvalidArrivalTime() {
        LocalTime arrivalTime = LocalTime.now();
        LocalTime departureTime = null;

        Assertions.assertThrows(NullPointerException.class, () -> {
            FlightSchedule.of(departureTime, arrivalTime);
        });
    }

    @Test
    // tests building a FlightSchedule when DepartureTime is after ArrivalTime
    void ofArrivalTimeBeforeDepartureTime() {
        LocalTime departureTime = LocalTime.now().plusHours(1);
        LocalTime arrivalTime = LocalTime.now();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FlightSchedule.of(departureTime, arrivalTime);
        });
    }

    @Test
    // tests whether the length of a Flight Schedule is short (true case)
    void isShortTrue() {
        Duration maxDuration = Duration.ofHours(2);

        LocalTime departureTime = LocalTime.now();
        LocalTime arrivalTime = LocalTime.now().plusMinutes(1);

        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);

        assertTrue(schedule.isShort(maxDuration));
    }

    @Test
    // tests whether the length of a Flight Schedule is short (false case)
    void isShortFalse() {
        Duration maxDuration = Duration.ofHours(1);

        LocalTime departureTime = LocalTime.now();
        LocalTime arrivalTime = LocalTime.now().plusHours(2);

        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);

        assertFalse(schedule.isShort(maxDuration));
    }

    @Test
    // tests getting the departure time
    void getDepartureTime() {
        LocalTime departureTime = LocalTime.now();
        LocalTime arrivalTime = LocalTime.now().plusHours(1);

        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);

        assertEquals(departureTime, schedule.getDepartureTime());
    }

    @Test
    // tests getting the arrival time
    void getArrivalTime() {
        LocalTime departureTime = LocalTime.now();
        LocalTime arrivalTime = LocalTime.now().plusHours(1);

        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);

        assertEquals(arrivalTime, schedule.getArrivalTime());
    }
}