package alarms;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void alarmStatusAssignmentExample() {
        AlarmStatus status = Main.alarmStatus("TestFiles/MainTest1.txt");

        assertEquals(AlarmStatus.SILENT, status);
    }

    @Test
    public void alarmStatusNonSquare() {
        AlarmStatus status = Main.alarmStatus("TestFiles/MainTest2.txt");

        assertEquals(AlarmStatus.SILENT, status);
    }

    @Test
    public void alarmStatusFrontAirplane() {
        AlarmStatus status = Main.alarmStatus("TestFiles/MainTest3.txt");

        assertEquals(AlarmStatus.SILENT, status);
    }

    @Test
    public void alarmStatusTopCameraShift() {
        AlarmStatus status = Main.alarmStatus("TestFiles/MainTest4.txt");

        assertEquals(AlarmStatus.SILENT, status);
    }

    @Test
    public void alarmStatusContainersFall() {
        AlarmStatus status = Main.alarmStatus("TestFiles/MainTest5.txt");

        assertEquals(AlarmStatus.TRIGGERED, status);
    }

    @Test
    public void alarmStatusManyContainersFall() {
        AlarmStatus status = Main.alarmStatus("TestFiles/MainTest6.txt");

        assertEquals(AlarmStatus.TRIGGERED, status);
    }

    @Test
    public void alarmStatusPlaneSide() {
        AlarmStatus status = Main.alarmStatus("TestFiles/MainTest7.txt");

        assertEquals(AlarmStatus.SILENT, status);
    }

    @Test
    public void alarmStatusTooManyMatrices() {
        AlarmStatus status = Main.alarmStatus("TestFiles/MainTest8.txt");

        assertEquals(AlarmStatus.INVALID, status);
    }

    @Test
    public void alarmStatusNegativeInput() {
        AlarmStatus status = Main.alarmStatus("TestFiles/MainTest9.txt");

        assertEquals(AlarmStatus.INVALID, status);
    }

    @Test
    public void alarmStatusInvalidDimensions() {
        AlarmStatus status = Main.alarmStatus("TestFiles/MainTest10.txt");

        assertEquals(AlarmStatus.INVALID, status);
    }

    @Test
    public void alarmStatusMissingGridSize() {
        AlarmStatus status = Main.alarmStatus("TestFiles/MainTest11.txt");

        assertEquals(AlarmStatus.INVALID, status);
    }

    @Test
    public void mainPathNotFound() {
        Main main = new Main();
        Main.main(new String[]{"junk"});
    }

    @Test
    public void mainPathFound() {
        Main.main(new String[]{"TestFiles/MainTest11.txt"});
    }

    @Test
    public void mainInvalidLength() {
        Main.main(new String[]{"TestFiles/MainTest11.txt", "123"});
    }
}