import static org.junit.Assert.*;

import org.junit.Test;

public class LogStateTest {
    @Test
    public void testWorksInNominalCase() {
        LogState ls = new LogState(12, 2);
        ls.applyCommand(new Command("TO", "1"));
        ls.applyCommand(new Command("REP", "2"));
        ls.applyCommand(new Command("THISIS", "2"));
        assertTrue(ls.isConnected());
    }

    @Test
    public void falseNonRepCase() {
        LogState ls = new LogState(32, 2);
        ls.applyCommand(new Command("TO", "3"));
        ls.applyCommand(new Command("REP", "2"));
        ls.applyCommand(new Command("Junk", "2"));
        assertFalse(ls.isConnected());
    }
}
