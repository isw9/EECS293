package airtravel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LegTest {

    @Test
    // test the simple case of creating a leg with valid inputs
    void validOf() {
        Duration oneHour = Duration.ofHours(1);
        Duration twoHours = Duration.ofHours(2);
        Airport origin = Airport.of("ORI", oneHour);
        Airport destination = Airport.of("DES", twoHours);

        Leg leg = Leg.of(origin, destination);

        assertEquals(destination, leg.getDestination());
    }

    @Test
    // test a NullPointerException is thrown when the origin is null
    void nullOriginOf() {
        Duration twoHours = Duration.ofHours(2);
        Airport origin = null;
        Airport destination = Airport.of("DES", twoHours);

        Assertions.assertThrows(NullPointerException.class, () -> {
            Leg.of(origin, destination);
        });
    }

    @Test
    // test a NullPointerException is thrown when the destination is null
    void nullDestinationOf() {
        Duration twoHours = Duration.ofHours(2);
        Airport origin = Airport.of("ORI", twoHours);
        Airport destination = null;

        Assertions.assertThrows(NullPointerException.class, () -> {
            Leg.of(origin, destination);
        });
    }

    @Test
    // tests getting the origin leg in the valid case
    void getOriginValid() {
        Duration oneHour = Duration.ofHours(1);
        Duration twoHours = Duration.ofHours(2);
        Airport origin = Airport.of("ORI", oneHour);
        Airport destination = Airport.of("DES", twoHours);

        Leg leg = Leg.of(origin, destination);

        assertEquals(leg.getOrigin().getCode(), "ORI");
    }

    @Test
    // tests getting the destination leg in the valid case
    void getDestinationValid() {
        Duration oneHour = Duration.ofHours(1);
        Duration twoHours = Duration.ofHours(2);
        Airport origin = Airport.of("ORI", oneHour);
        Airport destination = Airport.of("DES", twoHours);

        Leg leg = Leg.of(origin, destination);

        assertEquals(leg.getDestination().getCode(), "DES");
    }
}