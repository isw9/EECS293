package airtravel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AirportTest {

    @Test
    // tests building a valid Airport
    void ofValid() {
        Duration duration = Duration.ofHours(1);
        String code = "CLE";

        Airport airport = Airport.of(code, duration);

        assertEquals(code, airport.getCode());
        assertEquals(duration, airport.getConnectionTimeMin());
    }

    @Test
    // tests building an Airport when the duration is null
    void ofInvalidDuration() {
        Duration duration = null;
        String code = "CLE";

        Assertions.assertThrows(NullPointerException.class, () -> {
            Airport.of(code, duration);
        });
    }

    @Test
    // tests building an Airport when the code is null
    void ofInvalidCode() {
        Duration duration = Duration.ofHours(1);
        String code = null;

        Assertions.assertThrows(NullPointerException.class, () -> {
            Airport.of(code, duration);
        });
    }

    @Test
    // tests adding a flight to the FlightGroup (expected to be false since the SimpleFlight of method does this for us)
    void addFlight() {
        Duration oneHour = Duration.ofHours(1);
        Duration twoHours = Duration.ofHours(2);
        Airport origin = Airport.of("ORI", oneHour);
        Airport destination = Airport.of("DES", twoHours);
        LocalTime departureTime = LocalTime.now();
        LocalTime arrivalTime = LocalTime.now().plusHours(1);
        EnumMap<SeatClass, Integer> map = new EnumMap<>(SeatClass.class);
        map.put(SeatClass.BUSINESS, 0);
        SeatConfiguration seatConfiguration = SeatConfiguration.of(map);

        Leg leg = Leg.of(origin, destination);
        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);
        SimpleFlight simpleFlight = SimpleFlight.of("ORI", leg, schedule, seatConfiguration);

        assertEquals(1, origin.getOutFlights().flightsAtOrAfter(departureTime).size());

        //we expect this to be false because the SimpleFlight of method adds the flight to the flight group
        assertFalse(origin.addFlight(simpleFlight));
    }

    @Test
    // tests removing a flight from its FlightGroup
    void removeFlight() {
        Duration oneHour = Duration.ofHours(1);
        Duration twoHours = Duration.ofHours(2);
        Airport origin = Airport.of("ORI", oneHour);
        Airport destination = Airport.of("DES", twoHours);
        LocalTime departureTime = LocalTime.now();
        LocalTime arrivalTime = LocalTime.now().plusMinutes(1);
        EnumMap<SeatClass, Integer> map = new EnumMap<>(SeatClass.class);
        map.put(SeatClass.BUSINESS, 0);
        SeatConfiguration seatConfiguration = SeatConfiguration.of(map);

        Leg leg = Leg.of(origin, destination);
        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);
        SimpleFlight simpleFlight = SimpleFlight.of("ORI", leg, schedule, seatConfiguration);

        assertEquals(1, origin.getOutFlights().flightsAtOrAfter(departureTime).size());

        origin.removeFlight(simpleFlight);

        assertEquals(0, origin.getOutFlights().flightsAtOrAfter(departureTime).size());
    }

    @Test
    // tests getting the code for an airport
    void getCode() {
        Duration duration = Duration.ofHours(1);
        String code = "CLE";

        Airport airport = Airport.of(code, duration);

        assertEquals(code, airport.getCode());
    }

    @Test
    // tests getting the connection time (in minutes) of an airport
    void getConnectionTimeMin() {
        Duration duration = Duration.ofHours(1);
        String code = "CLE";

        Airport airport = Airport.of(code, duration);

        assertEquals(60, airport.getConnectionTimeMin().toMinutes());
    }

    @Test
    // tests getting the available flights on or after a given time when there is 1
    void availableFlightsValid() {
        Airport origin = Airport.of("ORI", Duration.ofHours(1));
        LocalTime departureTime = LocalTime.now();
        SimpleFlight flight = createSimpleFlight(origin, departureTime, 1);
        FareClass fareClass = FareClass.of(SeatClass.BUSINESS, 100);

        assertEquals(1, origin.availableFlights(departureTime, fareClass).size());
    }

    @Test
    // tests getting the available flights on or after a given time when there is not one because
    // there are no flighs leaving after the departure time
    void availableFlightsInvalidDepartureTime() {
        Airport origin = Airport.of("ORI", Duration.ofHours(1));
        LocalTime departureTime = LocalTime.now();
        SimpleFlight flight = createSimpleFlight(origin, departureTime, 1);
        FareClass fareClass = FareClass.of(SeatClass.BUSINESS, 100);

        assertEquals(0, origin.availableFlights(departureTime.plusMinutes(10), fareClass).size());
    }

    @Test
    // tests getting the available flights on or after a given time when there is not one because
    // there are no seats available for the fare class
    void availableFlightsInvalidNoSeats() {
        Airport origin = Airport.of("ORI", Duration.ofHours(1));
        LocalTime departureTime = LocalTime.now();
        SimpleFlight flight = createSimpleFlight(origin, departureTime, 0);
        FareClass fareClass = FareClass.of(SeatClass.BUSINESS, 100);

        assertEquals(0, origin.availableFlights(departureTime, fareClass).size());
    }

    private SimpleFlight createSimpleFlight(Airport origin, LocalTime departureTime, int numSeats) {
        Duration twoHours = Duration.ofHours(2);
        Airport destination = Airport.of("DES", twoHours);
        LocalTime arrivalTime = LocalTime.now().plusHours(1);
        EnumMap<SeatClass, Integer> map = new EnumMap<>(SeatClass.class);
        map.put(SeatClass.BUSINESS, numSeats);
        SeatConfiguration seatConfiguration = SeatConfiguration.of(map);

        Leg leg = Leg.of(origin, destination);
        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);
        return SimpleFlight.of("ORI", leg, schedule, seatConfiguration);
    }
}