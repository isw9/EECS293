package airtravel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.*;

class SeatConfigurationTest {

    @Test
    // tests building a valid SeatConfiguration using an EnumMap
    void ofValid() {
        EnumMap<SeatClass, Integer> map = new EnumMap<>(SeatClass.class);
        map.put(SeatClass.BUSINESS, 0);

        SeatConfiguration seatConfiguration = SeatConfiguration.of(map);

        assertTrue(seatConfiguration.hasSeats());
    }

    @Test
    // tests building an SeatConfiguration when the EnumMap is null
    void ofInvalidEnumMap() {
        EnumMap<SeatClass, Integer> map = null;
        Assertions.assertThrows(NullPointerException.class, () -> {
            SeatConfiguration.of(map);
        });
    }

    @Test
    // tests building an SeatConfiguration when the SeatConfiguration is null
    void ofInvalidSeatConfiguration() {
        SeatConfiguration seatConfiguration = null;
        Assertions.assertThrows(NullPointerException.class, () -> {
            SeatConfiguration.of(seatConfiguration);
        });
    }

    @Test
    // tests building a valid SeatConfiguration using a SeatConfiguration
    void ofSeatConfigurationValid() {
        SeatConfiguration seatConfiguration = setBasicSeatConfiguration();

        SeatConfiguration seatConfiguration1 = SeatConfiguration.of(seatConfiguration);

        assertTrue(seatConfiguration.seats(SeatClass.BUSINESS) == seatConfiguration1.seats(SeatClass.BUSINESS));
    }

    @Test
    // tests getting the proper number of seats for each SeatClass
    void seats() {
        SeatConfiguration seatConfiguration = setBasicSeatConfiguration();

        assertEquals(8, seatConfiguration.seats(SeatClass.ECONOMY));
        assertEquals(0, seatConfiguration.seats(SeatClass.BUSINESS));
        assertEquals(4, seatConfiguration.seats(SeatClass.PREMIUM_ECONOMY));
    }

    @Test
    // tests setting the proper number of seats for a given SeatClass
    void setSeats() {
        SeatConfiguration seatConfiguration = setBasicSeatConfiguration();

        assertEquals(0, seatConfiguration.seats(SeatClass.BUSINESS));

        seatConfiguration.setSeats(SeatClass.BUSINESS, 10);

        assertEquals(10, seatConfiguration.seats(SeatClass.BUSINESS));
    }

    @Test
    // tests checking if a SeatConfiguration has seats at all
    void hasSeats() {
        SeatConfiguration seatConfiguration = setBasicSeatConfiguration();

        assertTrue(seatConfiguration.hasSeats());

        seatConfiguration.setSeats(SeatClass.PREMIUM_ECONOMY, 0);
        seatConfiguration.setSeats(SeatClass.ECONOMY, 0);

        assertFalse(seatConfiguration.hasSeats());
    }

    public static SeatConfiguration setBasicSeatConfiguration() {
        EnumMap<SeatClass, Integer> map = new EnumMap<>(SeatClass.class);
        map.put(SeatClass.BUSINESS, 0);
        map.put(SeatClass.ECONOMY, 8);
        map.put(SeatClass.PREMIUM_ECONOMY, 4);
        return SeatConfiguration.of(map);
    }
}