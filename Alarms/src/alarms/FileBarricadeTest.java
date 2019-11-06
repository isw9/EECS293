package alarms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileBarricadeTest {

    @Test
    public void readFileNotFound() {
        FileBarricade fileBarricade = new FileBarricade("fake.txt");
        fileBarricade.readFile();
    }

    @Test
    public void readFileValid() {
        FileBarricade fileBarricade = new FileBarricade("TestFiles/MainTest4.txt");
        List<String> fileContents = fileBarricade.readFile();
        List<String> expectedResults = new ArrayList<String>();

        expectedResults.add("3 4 5");
        expectedResults.add("000100101101111 00000101010111011111 111101111111");
        expectedResults.add("000100101101111 00000101010111011111 111101111111");
        expectedResults.add("000100101101111 00000101010111011111 110010110110");
        expectedResults.add("000100101101111 00000101010111011111 110010110110");
        expectedResults.add("000100101101111 00000101010111011111 110010110110");
        expectedResults.add("000100101101111 00000101010111011111 110010110110");
        expectedResults.add("000100101101111 00000101010111011111 110010110110");


        for (int i = 0; i < expectedResults.size(); i++) {
            assertEquals(expectedResults.get(0), fileContents.get(0));
        }
    }
}