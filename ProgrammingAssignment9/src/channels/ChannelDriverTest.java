import org.junit.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ChannelDriverTest {
    // code coverage
    @Test
    public void mainNominalCase() {
        String testInstructions = "123\n" +
                "45\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "REP 2\n" +
                "REP 3\n" +
                "TO 1\n" +
                "REP 2\n" +
                "REP 3\n" +
                "THISIS 4\n" +
                "REP 5";

        ByteArrayInputStream testIn;
        testIn = new ByteArrayInputStream(testInstructions.getBytes());
        System.setIn(testIn);
        ChannelDriver.main(new String[0]);
    }

    // code coverage
    @Test
    public void testConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<ChannelDriver> constructor = ChannelDriver.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    //code coverage
    @Test
    public void callStatusOfNominalCase() {

        String testInstructions = "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "REP 2\n" +
                "REP 3\n" +
                "TO 1\n" +
                "REP 2\n" +
                "REP 3\n" +
                "THISIS 4\n" +
                "REP 5";


        InputStream stream = new ByteArrayInputStream(testInstructions.getBytes(StandardCharsets.UTF_8));
        ChannelDriver.callStatusOf(stream, 123, 45);

//        assertEquals("true", result);
    }

    // branch coverage
    @Test
    public void callStatusReturnEarlyTrue() {

        String testInstructions = "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "REP 2\n" +
                "REP 3\n" +
                "TO 1\n" +
                "REP 2\n" +
                "REP 3\n" +
                "THISIS 4\n" +
                "REP 5\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "REP 2\n" +
                "REP 3\n" +
                "TO 1\n" +
                "REP 2\n" +
                "REP 3\n" +
                "THISIS 4\n" +
                "REP 5";
        InputStream stream = new ByteArrayInputStream(testInstructions.getBytes(StandardCharsets.UTF_8));
        ChannelDriver.callStatusOf(stream, 123, 45);

//        assertEquals("true", result);
    }

    //branch coverage
    @Test
    public void falseNoDestinationDetermined() {

        String testInstructions = "REP 2\n" +
                "REP 3\n" +
                "THISIS 4\n" +
                "REP 5\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "GARBLED";
        InputStream stream = new ByteArrayInputStream(testInstructions.getBytes(StandardCharsets.UTF_8));
        ChannelDriver.callStatusOf(stream, 123, 45);

//        assertEquals("false - destination address does not match", result);
    }

    //branch coverage
    @Test
    public void trueLongToInstructions() {

        String testInstructions = "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "REP 2\n" +
                "REP 3\n" +
                "TO 1\n" +
                "REP 2\n" +
                "REP 3\n" +
                "THISIS 4\n" +
                "REP 5";

        InputStream stream = new ByteArrayInputStream(testInstructions.getBytes(StandardCharsets.UTF_8));
        ChannelDriver.callStatusOf(stream, 123, 45);

//        assertEquals("true", result);
    }

    //branch coverage
    @Test
    public void truePiecedTogether() {

        String testInstructions = "REP 2\n" +
                "REP 3\n" +
                "THISIS 4\n" +
                "REP 5\n" +
                "TO 1\n" +
                "REP 2\n" +
                "REP 3\n" +
                "THISIS 4";

        InputStream stream = new ByteArrayInputStream(testInstructions.getBytes(StandardCharsets.UTF_8));
        ChannelDriver.callStatusOf(stream, 123, 45);
//
//        assertEquals("true", result);
    }

    //branch coverage
    @Test
    public void tabsCallingSequence() {
        System.out.println("AHHHHHHH");
        String testInstructions = "TO 1\n" +
                "REP 2\tREP 3\n" +
                "TO 1\n" +
                "REP 2\n" +
                "REP 3\n" +
                "THISIS 4\tREP 5\n";

        InputStream stream = new ByteArrayInputStream(testInstructions.getBytes(StandardCharsets.UTF_8));
        ChannelDriver.callStatusOf(stream, 123, 45);

//        assertEquals("true", result);
    }

    //branch coverage
    @Test
    public void trueGarbled() {
        String testInstructions = "TO 1\n" +
                "TO 1\n" +
                "REP 2\n" +
                "REP 3\n" +
                "TO 1\n" +
                "REP 2\n" +
                "REP 3\n" +
                "THISIS 4\n" +
                "REP 5\n" +
                "TO 1 \n" +
                "GARBLED";

        InputStream stream = new ByteArrayInputStream(testInstructions.getBytes(StandardCharsets.UTF_8));
        ChannelDriver.callStatusOf(stream, 123, 45);

//        assertEquals("true", result);
    }

    //branch coverage
    @Test
    public void falseNeitherAddress() {
        String testInstructions = "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "TO 1\n" +
                "REP 2\n" +
                "Why wouldn't you increase the transmission power, so that I can hear what you are saying?\n" +
                "TO 1\n" +
                "REP 2\n" +
                "REP 3\n" +
                "THISIS UNREADABLE\n" +
                "REP 5\n" +
                "TO 1\n" +
                "TO 1";

        InputStream stream = new ByteArrayInputStream(testInstructions.getBytes(StandardCharsets.UTF_8));
        ChannelDriver.callStatusOf(stream, 123, 45);
//
//        assertEquals("false - source address does not match", result);
    }

}