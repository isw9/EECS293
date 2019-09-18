package airtravel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class FareClassTest {

    @Test
    // tests building a valid FareClass
    void validOf() {
        FareClass fareClass = FareClass.of(SeatClass.BUSINESS, 1);

        assertEquals(1, fareClass.getIdentifier());
    }

    @Test
    // tests building an FareClass when the SeatClass is null
    void ofInvalidSeatClass() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            FareClass.of(null, 1);
        });
    }

    @Test
    // tests getting the identifier of a FareClass
    void getIdentifier() {
        FareClass fareClass = FareClass.of(SeatClass.BUSINESS, 1);
        FareClass fareClass2 = FareClass.of(SeatClass.ECONOMY, 2);

        assertEquals(1, fareClass.getIdentifier());
        assertEquals(2, fareClass2.getIdentifier());
    }

    @Test
    // tests that two FareClasses are equal based on their identifier
    void equals() {
        FareClass fareClass = FareClass.of(SeatClass.BUSINESS, 1);
        FareClass fareClass2 = FareClass.of(SeatClass.ECONOMY, 1);

        assertTrue(fareClass.equals(fareClass2));
    }
}