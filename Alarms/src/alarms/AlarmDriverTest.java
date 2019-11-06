package alarms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class AlarmDriverTest {
    @Test
    public void differentSizesSameFeed() {
        AlarmDriver driver = new AlarmDriver();

        String feed = "000100101101111 00000101010111011111 111101111111000100101101111";
        List<String> cameraFeeds = new ArrayList<String>();
        cameraFeeds.add(feed);
        cameraFeeds.add(feed);
        cameraFeeds.add(feed);
        cameraFeeds.add(feed);
        cameraFeeds.add(feed);
        cameraFeeds.add(feed);

        AlarmStatus status = AlarmDriver.alarmStatus(cameraFeeds, 3, 4, 5);
        assertEquals(AlarmStatus.SILENT, status);
    }

    @Test
    public void possibleHorizontalShiftLeft() {
        AlarmDriver driver = new AlarmDriver();
        AlarmDriver.TestHook testHook = driver.new TestHook();

        int [][] previousFrame = new int[2][3];
        int [][] currentFrame = new int[2][3];

        //previous
        //1 0 1
        //1 1 0

        //current (shifted left)
        //0 1 0
        //1 0 0


        //top row
        previousFrame[0][0] = 1;
        previousFrame[0][1] = 0;
        previousFrame[0][2] = 1;

        //bottom row
        previousFrame[1][0] = 1;
        previousFrame[1][1] = 1;
        previousFrame[1][2] = 0;

        //top row
        currentFrame[0][0] = 0;
        currentFrame[0][1] = 1;
        currentFrame[0][2] = 0;

        //bottom row
        currentFrame[1][0] = 1;
        currentFrame[1][1] = 0;
        currentFrame[1][2] = 0;

        boolean result = testHook.possibleHorizontalShift(currentFrame, previousFrame);

        assertTrue(result);
    }

    @Test
    public void possibleHorizontalShiftRight() {
        AlarmDriver driver = new AlarmDriver();
        AlarmDriver.TestHook testHook = driver.new TestHook();

        int [][] previousFrame = new int[2][3];
        int [][] currentFrame = new int[2][3];

        //previous
        //1 0 1
        //1 1 0

        //current (shifted right)
        //0 1 0
        //0 1 1

        //top row
        previousFrame[0][0] = 1;
        previousFrame[0][1] = 0;
        previousFrame[0][2] = 1;

        //bottom row
        previousFrame[1][0] = 1;
        previousFrame[1][1] = 1;
        previousFrame[1][2] = 0;

        //top row
        currentFrame[0][0] = 0;
        currentFrame[0][1] = 1;
        currentFrame[0][2] = 0;

        //bottom row
        currentFrame[1][0] = 0;
        currentFrame[1][1] = 1;
        currentFrame[1][2] = 1;

        boolean result = testHook.possibleHorizontalShift(currentFrame, previousFrame);

        assertTrue(result);
    }

    @Test
    public void possibleVerticalShiftDown() {
        AlarmDriver driver = new AlarmDriver();
        AlarmDriver.TestHook testHook = driver.new TestHook();

        int [][] previousFrame = new int[2][3];
        int [][] currentFrame = new int[2][3];

        //previous
        //1 0 1
        //1 1 0

        //current (shifted down)
        //0 0 0
        //1 0 1

        //top row
        previousFrame[0][0] = 1;
        previousFrame[0][1] = 0;
        previousFrame[0][2] = 1;

        //bottom row
        previousFrame[1][0] = 1;
        previousFrame[1][1] = 1;
        previousFrame[1][2] = 0;

        //top row
        currentFrame[0][0] = 0;
        currentFrame[0][1] = 0;
        currentFrame[0][2] = 0;

        //bottom row
        currentFrame[1][0] = 1;
        currentFrame[1][1] = 0;
        currentFrame[1][2] = 1;

        boolean result = testHook.possibleVerticalShift(currentFrame, previousFrame);

        assertTrue(result);
    }

    @Test
    public void possibleVerticalShiftUp() {
        AlarmDriver driver = new AlarmDriver();
        AlarmDriver.TestHook testHook = driver.new TestHook();

        int [][] previousFrame = new int[2][3];
        int [][] currentFrame = new int[2][3];

        //previous
        //1 0 1
        //1 1 0

        //current (shifted up)
        //1 1 0
        //0 0 0

        //top row
        previousFrame[0][0] = 1;
        previousFrame[0][1] = 0;
        previousFrame[0][2] = 1;

        //bottom row
        previousFrame[1][0] = 1;
        previousFrame[1][1] = 1;
        previousFrame[1][2] = 0;

        //top row
        currentFrame[0][0] = 1;
        currentFrame[0][1] = 1;
        currentFrame[0][2] = 0;

        //bottom row
        currentFrame[1][0] = 0;
        currentFrame[1][1] = 0;
        currentFrame[1][2] = 0;

        boolean result = testHook.possibleVerticalShift(currentFrame, previousFrame);

        assertTrue(result);
    }

    @Test
    public void possibleShiftHorizontalAndVerticalTrueTrue() {
        AlarmDriver driver = new AlarmDriver();
        AlarmDriver.TestHook testHook = driver.new TestHook();

        boolean result = testHook.possibleShift("000000000", "000000000", 3, 3);

        assertTrue(result);
    }

    @Test
    public void possibleShiftHorizontalFalseAndVerticalTrue() {
        AlarmDriver driver = new AlarmDriver();
        AlarmDriver.TestHook testHook = driver.new TestHook();

        boolean result = testHook.possibleShift("111111000", "111111111", 3, 3);

        assertTrue(result);
    }

    @Test
    public void possibleShiftHorizontalAndVerticalFalseFalse() {
        AlarmDriver driver = new AlarmDriver();
        AlarmDriver.TestHook testHook = driver.new TestHook();

        boolean result = testHook.possibleShift("111111111", "000000000", 3, 3);

        assertFalse(result);
    }

}