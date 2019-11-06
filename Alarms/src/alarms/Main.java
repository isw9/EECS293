package alarms;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("invalid");
        }
        else {
            alarmStatus(args[0]);

        }
    }

    public static AlarmStatus alarmStatus(String filePath) {
        FileBarricade fileBarricade = new FileBarricade(filePath);
        InputBarricade inputBarricade = new InputBarricade();
        AlarmStatus status = AlarmStatus.INVALID;
        List<String> input = inputBarricade.checkInput(fileBarricade);

        if (input.size() == 0 ) {
            System.out.println("invalid");
        }
        else {
            List<Integer> gridSizes = inputBarricade.gridSizes(input.get(0));
            int x = gridSizes.get(0);
            int y = gridSizes.get(1);
            int z = gridSizes.get(2);
            status = AlarmDriver.alarmStatus(input.subList(1, input.size()), x, y, z);

            if (status == AlarmStatus.SILENT) {
                System.out.println("false");
            }
            else {
                System.out.println("true");
            }
        }

        return status;
    }
}
