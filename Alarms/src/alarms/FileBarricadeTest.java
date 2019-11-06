package alarms;

import org.junit.Test;


public class FileBarricadeTest {

    @Test
    public void readFileNotFound() {
        FileBarricade fileBarricade = new FileBarricade("fake.txt");
        fileBarricade.readFile();
    }
}