package alarms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class InputBarricadeTest {

    @Test
    public void lessThanTwoLinesCheckInput() {
        AlarmStatus status = AlarmStatus.SILENT;
        FileBarricade fileBarricade = new FileBarricade("TestFiles/InputBarricadeTest1.txt");

        InputBarricade inputBarricade = new InputBarricade();

        List<String> inputResults = inputBarricade.checkInput(fileBarricade);

        assertEquals(0, inputResults.size());
    }

    @Test
    public void gridLineInvalidCheckInput() {
        FileBarricade fileBarricade = new FileBarricade("TestFiles/InputBarricadeTest2.txt");

        InputBarricade inputBarricade = new InputBarricade();

        List<String> inputResults = inputBarricade.checkInput(fileBarricade);

        assertEquals(0, inputResults.size());
    }

    @Test
    public void cameraFeedInvalidCheckInput() {
        FileBarricade fileBarricade = new FileBarricade("TestFiles/InputBarricadeTest3.txt");

        InputBarricade inputBarricade = new InputBarricade();

        List<String> inputResults = inputBarricade.checkInput(fileBarricade);

        assertEquals(0, inputResults.size());
    }

    @Test
    public void cameraFeedValidCheckInput() {
        FileBarricade fileBarricade = new FileBarricade("TestFiles/InputBarricadeTest4.txt");

        InputBarricade inputBarricade = new InputBarricade();

        List<String> inputResults = inputBarricade.checkInput(fileBarricade);

        assertEquals(3, inputResults.size());
    }


    @Test
    public void gridSizeValidLengthNot3() {
        InputBarricade inputBarricade = new InputBarricade();
        InputBarricade.TestHook testHook = inputBarricade.new TestHook();

        boolean result = testHook.gridSizeValid("3 4 4 4");

        assertFalse(result);
    }

    @Test
    public void gridSizeValidNegativeNumber() {
        InputBarricade inputBarricade = new InputBarricade();
        InputBarricade.TestHook testHook = inputBarricade.new TestHook();

        boolean result = testHook.gridSizeValid("3 4 -4");

        assertFalse(result);
    }

    @Test
    public void validDataValuesBoundary() {
        InputBarricade inputBarricade = new InputBarricade();
        InputBarricade.TestHook testHook = inputBarricade.new TestHook();

        boolean result1 = testHook.validDataValues("a-1'~!@#$%^&");
        boolean result2 = testHook.validDataValues(Character.toString(Character.MAX_VALUE));


        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void cameraFeedsValidLengthNot3() {
        InputBarricade inputBarricade = new InputBarricade();
        InputBarricade.TestHook testHook = inputBarricade.new TestHook();

        boolean result = testHook.cameraFeedsValid("100 000 000 000", new ArrayList<Integer>(Arrays.asList(3, 3, 3)));

        assertFalse(result);
    }

    @Test
    public void cameraFeedsValidDataLengthBad() {
        InputBarricade inputBarricade = new InputBarricade();
        InputBarricade.TestHook testHook = inputBarricade.new TestHook();

        boolean result = testHook.cameraFeedsValid("44444 000 000", new ArrayList<Integer>(Arrays.asList(3, 3, 3)));

        assertFalse(result);
    }

    @Test
    public void validDataValuesCameraFeedReplace5() {
        InputBarricade inputBarricade = new InputBarricade();
        InputBarricade.TestHook testHook = inputBarricade.new TestHook();
        String input = "000111234";

        String result = testHook.validDataValuesCameraFeed(input);

        assertEquals("000111111", result);
    }
}