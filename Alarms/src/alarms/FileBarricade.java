package alarms;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileBarricade {
    private String fileName;

    FileBarricade(String fileName) {
        this.fileName = fileName;
    }

    public List<String> readFile() {
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("/src/alarms/").concat(fileName);
        File file = new File(filePath);
        List<String> cameraShots = new ArrayList<String>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                cameraShots.add(currentLine);
            }
        }
        catch (IOException e){
            System.err.println("File " + filePath + " could not be opened");
            return new ArrayList<String>();
        }

        return cameraShots;
    }
}
