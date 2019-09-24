package airtravel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.EnumMap;

import static airtravel.FlightPolicy.*;
import static org.junit.jupiter.api.Assertions.*;

class FlightPolicyTest {

    @Test
    // tests valid FlightPolicy build method
    void of() {
        Flight flight = createSimpleFlight(10, 5, 3);

        FlightPolicy policy = FlightPolicy.of(flight, (seatConfig, fareClassConfig) -> seatConfig);

        assertEquals(flight.getCode(), policy.getCode());
    }

    @Test
    // tests invalid FlightPolicy build method when the Flight is null
    void ofInvalidFlight() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            FlightPolicy.of(null, (seatConfig, fareClassConfig) -> seatConfig);
        });
    }

    @Test
    // tests invalid FlightPolicy build method when the BiFunction is null
    void ofInvalidBiFunction() {
        Flight flight = createSimpleFlight(10, 5, 3);

        Assertions.assertThrows(NullPointerException.class, () -> {
            FlightPolicy.of(flight, null);
        });
    }


    @Test
    // tests getting the code for a flight
    void getCode() {
        FlightPolicy flightPolicy = FlightPolicy.of(createSimpleFlight(10, 5, 3),(seatConfig, fareClassConfig) -> seatConfig);

        assertEquals("CLE", flightPolicy.getCode());
    }

    @Test
    // tests getting the leg for a flight
    void getLeg() {
        FlightPolicy flightPolicy = FlightPolicy.of(createSimpleFlight(10, 5, 3),(seatConfig, fareClassConfig) -> seatConfig);

        assertEquals("ORL", flightPolicy.getLeg().getDestination().getCode());
    }

    @Test
    // tests checking if seats are available
    void seatsAvailableYes() {
        Flight flight = createSimpleFlight(10, 5, 3);

        Flight policy = strict(flight);

        SeatConfiguration seatsAvailable = policy.seatsAvailable(FareClass.of(SeatClass.BUSINESS, 10));

        assertTrue(seatsAvailable.hasSeats());
    }

    @Test
    // tests checking if seats are available
    void seatsAvailableNo() {
        Flight flight = createSimpleFlight(0, 0, 0);

        Flight policy = strict(flight);

        SeatConfiguration seatsAvailable = policy.seatsAvailable(FareClass.of(SeatClass.BUSINESS, 10));

        assertFalse(seatsAvailable.hasSeats());
    }

    @Test
    // tests checking the strict FlightPolicy
    void strictTest() {
        Flight flight = createSimpleFlight(10, 5, 3);

        Flight policy = strict(flight);

        SeatConfiguration seatsAvailable = policy.seatsAvailable(FareClass.of(SeatClass.BUSINESS, 10));

        assertEquals(10, seatsAvailable.seats(SeatClass.BUSINESS));
        assertEquals(0, seatsAvailable.seats(SeatClass.PREMIUM_ECONOMY));
        assertEquals(0, seatsAvailable.seats(SeatClass.ECONOMY));
    }

    @Test
    // tests checking the restrictedDuration FlightPolicy on a long flight
    void restrictedDurationLongFlight() {
        Flight flight = createSimpleFlight(10, 5, 3);

        Flight policy = restrictedDuration(flight, Duration.ofMinutes(5));

        SeatConfiguration seatsAvailable = policy.seatsAvailable(FareClass.of(SeatClass.BUSINESS, 10));

        assertEquals(10, seatsAvailable.seats(SeatClass.BUSINESS));
        assertEquals(5, seatsAvailable.seats(SeatClass.PREMIUM_ECONOMY));
        assertEquals(3, seatsAvailable.seats(SeatClass.ECONOMY));
    }

    @Test
    // tests checking the restrictedDuration FlightPolicy on a short flight
    void restrictedDurationShortFlight() {
        Flight flight = createSimpleFlight(10, 5, 3);

        Flight policy = restrictedDuration(flight, Duration.ofMinutes(15));

        SeatConfiguration seatsAvailable = policy.seatsAvailable(FareClass.of(SeatClass.BUSINESS, 10));

        assertEquals(10, seatsAvailable.seats(SeatClass.BUSINESS));
        assertEquals(0, seatsAvailable.seats(SeatClass.PREMIUM_ECONOMY));
        assertEquals(0, seatsAvailable.seats(SeatClass.ECONOMY));
    }

    @Test
    // tests checking the reserve FlightPolicy
    void reserveTest() {
        Flight flight = createSimpleFlight(10, 5, 3);

        Flight policy = reserve(flight, 2);

        SeatConfiguration seatsAvailable = policy.seatsAvailable(FareClass.of(SeatClass.BUSINESS, 10));

        assertEquals(8, seatsAvailable.seats(SeatClass.BUSINESS));
        assertEquals(3, seatsAvailable.seats(SeatClass.PREMIUM_ECONOMY));
        assertEquals(1, seatsAvailable.seats(SeatClass.ECONOMY));
    }

    @Test
    // tests checking the limited FlightPolicy
    void limitedTest() {
        Flight flight = createSimpleFlight(10, 5, 3);

        Flight policy = limited(flight);

        SeatConfiguration seatsAvailable = policy.seatsAvailable(FareClass.of(SeatClass.ECONOMY, 10));

        assertEquals(0, seatsAvailable.seats(SeatClass.BUSINESS));
        assertEquals(5, seatsAvailable.seats(SeatClass.PREMIUM_ECONOMY));
        assertEquals(3, seatsAvailable.seats(SeatClass.ECONOMY));
    }

    @Test
    // tests checking the upgradeToHighest FlightPolicy
    void upgradeToHighestTest() {
        Flight flight = createSimpleFlight(10, 5, 3);

        Flight policy = upgradeToHighest(flight);

        SeatConfiguration seatsAvailable = policy.seatsAvailable(FareClass.of(SeatClass.ECONOMY, 10));

        assertEquals(10, seatsAvailable.seats(SeatClass.BUSINESS));
        assertEquals(0, seatsAvailable.seats(SeatClass.PREMIUM_ECONOMY));
        assertEquals(3, seatsAvailable.seats(SeatClass.ECONOMY));
    }

    @Test
    // tests checking the spinTheWheel FlightPolicy
    void spinTheWheelLowIdentifier() {
        Flight flight = createSimpleFlight(10, 5, 3);

        Flight policy = spinTheWheel(flight);

        SeatConfiguration seatsAvailable = policy.seatsAvailable(FareClass.of(SeatClass.ECONOMY, 10));

        assertEquals(0, seatsAvailable.seats(SeatClass.BUSINESS));
        assertEquals(0, seatsAvailable.seats(SeatClass.PREMIUM_ECONOMY));
        assertEquals(3, seatsAvailable.seats(SeatClass.ECONOMY));
    }

    @Test
    // tests checking the spinTheWheel FlightPolicy
    void spinTheWheelHighIdentifier() {
        Flight flight = createSimpleFlight(10, 5, 3);

        Flight policy = spinTheWheel(flight);

        SeatConfiguration seatsAvailable = policy.seatsAvailable(FareClass.of(SeatClass.ECONOMY, 102));

        assertEquals(10, seatsAvailable.seats(SeatClass.BUSINESS));
        assertEquals(0, seatsAvailable.seats(SeatClass.PREMIUM_ECONOMY));
        assertEquals(3, seatsAvailable.seats(SeatClass.ECONOMY));
    }

    @Test
    // tests applying multiple FlightPolicies
    void comboFlightPolicy() {
        Flight flight = createSimpleFlight(4, 3, 5);

        Flight policy = limited(reserve(flight, 2));

        SeatConfiguration seatsAvailable = policy.seatsAvailable(FareClass.of(SeatClass.ECONOMY, 100));

        assertEquals(0, seatsAvailable.seats(SeatClass.BUSINESS));
        assertEquals(1, seatsAvailable.seats(SeatClass.PREMIUM_ECONOMY));
        assertEquals(3, seatsAvailable.seats(SeatClass.ECONOMY));
    }

    public static Flight createSimpleFlight(int business, int premiumEconomy, int economy) {
        Airport origin = Airport.of("CLE", Duration.ofMinutes(1));
        Airport destination = Airport.of("ORL", Duration.ofMinutes(2));
        Leg leg = Leg.of(origin, destination);
        EnumMap<SeatClass, Integer> map = new EnumMap<>(SeatClass.class);
        map.put(SeatClass.BUSINESS, business);
        map.put(SeatClass.ECONOMY, economy);
        map.put(SeatClass.PREMIUM_ECONOMY, premiumEconomy);
        SeatConfiguration seatConfiguration = SeatConfiguration.of(map);

        LocalTime arrivalTime = LocalTime.now().plusMinutes(10);
        LocalTime departureTime = LocalTime.now();
        FlightSchedule schedule = FlightSchedule.of(departureTime, arrivalTime);
        return SimpleFlight.of("CLE", leg, schedule, seatConfiguration);
    }
}