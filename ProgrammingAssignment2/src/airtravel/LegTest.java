package airtravel;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class LegTest {

    @Test
    void of() {
        // test the simple case of creating a leg with valid inputs
        Duration oneHour = Duration.ofHours(1);
        Duration twoHours = Duration.ofHours(2);
        Airport origin = Airport.of("OriginCode", oneHour);
        Airport destination = Airport.of("DestinationCode", twoHours);

        Leg leg = Leg.of(origin, destination);

        assertEquals(destination, leg.getDestination());


    }

    @Test
    void getOrigin() {
    }

    @Test
    void getDestination() {
    }
}