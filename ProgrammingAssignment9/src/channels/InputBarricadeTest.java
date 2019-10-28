import org.junit.Test;

import static org.junit.Assert.*;


public class InputBarricadeTest {
    @Test
    public void testWorksOnNominalCases() {
        InputBarricade ib1 = new InputBarricade(1, 2);
        ib1.apply("TO 2\nTHISIS 1");
        assertTrue(ib1.isConnected());

        InputBarricade ib2 = new InputBarricade(123, 456);
        ib2.apply("TO 4");
        ib2.apply("REP 5");
        ib2.apply("REP 6");
        ib2.apply("THISIS 1");
        ib2.apply("REP 2");
        ib2.apply("REP 3");
        assertTrue(ib2.isConnected());
    }

    @Test
    public void testFailsOnInvalidAddresses() {
        InputBarricade ib1 = new InputBarricade(1, 2);
        ib1.apply("TO 3\nTHISIS 3");
        assertFalse(ib1.isConnected());

        InputBarricade ib2 = new InputBarricade(123, 456);
        ib2.apply("TO 4\nREP 5\nREP 7\nTHISIS 2\nREP 2\nREP 3\n");
        assertFalse(ib2.isConnected());
    }

    @Test
    public void testWorksWithBlankLines() {
        InputBarricade ib1 = new InputBarricade(1, 2);
        ib1.apply("\nTO 2\n\n\nTHISIS 1");
        assertTrue(ib1.isConnected());
    }
}