import org.junit.Test;

import static org.junit.Assert.*;


import java.io.*;
import java.util.Optional;

public class CommandTest {

    // code coverage
    @Test
    public void nominalCaseEquals() {
        Command commandOne = new Command("type", "12");

        Command commandTwo = new Command("type", "12");

        assertTrue(commandOne.equals(commandTwo));
    }

    // branch coverage
    @Test
    public void differentTypesEquals() {
        Command commandOne = new Command("type", "12");

        Command commandTwo = new Command("typeTwo", "12");

        assertFalse(commandOne.equals(commandTwo));
    }

    // branch coverage
    @Test
    public void differentArgumentsEquals() {
        Command commandOne = new Command("type", "12");

        Command commandTwo = new Command("type", "120");

        assertFalse(commandOne.equals(commandTwo));
    }

    // branch coverage
    @Test
    public void differentTypesAndArgumentsEquals() {
        Command commandOne = new Command("type", "12");

        Command commandTwo = new Command("typeTwo", "120");

        assertFalse(commandOne.equals(commandTwo));
    }

    // branch coverage
    @Test
    public void instanceOfFalseEquals() {
        Command commandOne = new Command("type", "12");

        String commandTwo = "command";

        assertFalse(commandOne.equals(commandTwo));
    }

    @Test
    public void testEquals() {
        assertTrue("a" == "a");
    }
}