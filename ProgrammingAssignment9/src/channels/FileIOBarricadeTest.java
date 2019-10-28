import org.junit.Test;

import java.io.FileInputStream;
import static org.junit.Assert.*;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class FileIOBarricadeTest {

    @Test
    public void worksNominalCase() {
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

        FileIOBarricade fileIOBarricade = new FileIOBarricade(stream);

        assertFalse(fileIOBarricade.nextLine().isEmpty());
    }

    @Test
    public void ioExceptionInputStream() {
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



        InputStream stream = null;

        FileIOBarricade fileIOBarricade = new FileIOBarricade(stream);

        String nextLine = fileIOBarricade.nextLine();

        assertEquals(nextLine, "");
    }
}
