package alarms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class AlarmDriver {

    public static AlarmStatus alarmStatus(List<String> cameraFeeds, int x, int y, int z) {

        //front camera -> z by x (z rows, x columns)
        //side camera -> z by y (z rows, y columns)
        //top camera -> y by x (y rows, x columns)

        List<Integer> rowSizeData = new ArrayList<Integer>(Arrays.asList(z, z, y));
        List<Integer> columnSizeData = new ArrayList<Integer>(Arrays.asList(x, y, x));

        HashSet<String> cameraData = new HashSet<String>(cameraFeeds);

        if (cameraData.size() == 1) {
            return AlarmStatus.SILENT;
        }


        for (int i = 1; i < cameraFeeds.size(); i++) {
            String currentCameraFrame = cameraFeeds.get(i);
            String previousCameraFrame = cameraFeeds.get(i - 1);

            if (!currentCameraFrame.equals(previousCameraFrame)) {
                if (!knownIssue(currentCameraFrame, previousCameraFrame, rowSizeData, columnSizeData)) {
                    return AlarmStatus.TRIGGERED;
                }
            }
        }
        // there are feeds that are different
        // for each feed that is different than the one before
        // can it be explained by 1) airplane 2) camera shifting


        return AlarmStatus.SILENT;
    }

    private static boolean knownIssue(String currentFrame, String previousFrame, List<Integer> rowSizeData, List<Integer> columnSizeData) {
        String[] currentSplits = currentFrame.split(" ");
        String[] previousSplits = previousFrame.split(" ");

        boolean possibleAirplane = possibleAirplane(currentFrame, previousFrame);
        boolean possibleShift = false;

        for (int i = 0; i < 3; i++) {
            if (!currentSplits[i].equals(previousSplits[i])) {
                if (possibleShift(currentSplits[i], previousSplits[i], rowSizeData.get(i), columnSizeData.get(i))) {
                    possibleShift = true;
                }
            }
        }

        return possibleAirplane || possibleShift;
    }

    private static boolean possibleShift(String currentCameraFeed, String previousCameraFeed, int rows, int columns) {
        int[][] currentFrame = new int[rows][columns];
        int[][] previousFrame = new int[rows][columns];

        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                currentFrame[i][j] = Character.getNumericValue(currentCameraFeed.charAt(index));
                previousFrame[i][j] = Character.getNumericValue(previousCameraFeed.charAt(index));
                index++;
            }
        }

        return possibleHorizontalShift(currentFrame, previousFrame) || possibleVerticalShift(currentFrame, previousFrame);

    }

    private static boolean possibleHorizontalShift(int[][] currentFrame, int[][] previousFrame) {
        return possibleLeftShift(currentFrame, previousFrame) || possibleRightShift(currentFrame, previousFrame);
    }

    private static boolean possibleLeftShift(int[][] currentFrame, int[][] previousFrame) {
        int[][] possibleLeftShift = new int[previousFrame.length][previousFrame[0].length];

        for (int i = 0; i < previousFrame.length; i++) {
            for (int j = 0; j < previousFrame[i].length; j++) {

                if (j == previousFrame[i].length - 1) {
                    possibleLeftShift[i][j] = 0;
                }
                else {
                    possibleLeftShift[i][j] = previousFrame[i][j + 1];
                }
            }
        }

        return framesEqual(currentFrame, possibleLeftShift);
    }



    private static boolean possibleRightShift(int[][] currentFrame, int[][] previousFrame) {
        int[][] possibleRightShift = new int[previousFrame.length][previousFrame[0].length];

        for (int i = 0; i < previousFrame.length; i++) {
            for (int j = 0; j < previousFrame[i].length; j++) {
                if (j == 0) {
                    possibleRightShift[i][j] = 0;
                }
                else {
                    possibleRightShift[i][j] = previousFrame[i][j - 1];
                }
            }
        }

        return framesEqual(currentFrame, possibleRightShift);
    }

    private static boolean framesEqual(int[][] currentFrame, int[][] otherFrame) {
//        printFrame(currentFrame);
//        printFrame(otherFrame);
        for (int i = 0; i < currentFrame.length; i++) {
            for (int j = 0; j < currentFrame[i].length; j++) {
                if (currentFrame[i][j] != otherFrame[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean possibleVerticalShift(int[][] currentFrame, int[][] previousFrame) {
        return possibleUpShift(currentFrame, previousFrame) || possibleDownShift(currentFrame, previousFrame);
    }

    private static boolean possibleUpShift(int[][] currentFrame, int[][] previousFrame) {
        int[][] possibleUpShift = new int[previousFrame.length][previousFrame[0].length];

        for (int i = 0; i < previousFrame.length; i++) {
            for (int j = 0; j < previousFrame[i].length; j++) {
                if (i == previousFrame.length - 1) {
                    possibleUpShift[i][j] = 0;
                }
                else {
                    possibleUpShift[i][j] = previousFrame[i + 1][j];
                }
            }
        }

        return framesEqual(currentFrame, possibleUpShift);
    }

    private static boolean possibleDownShift(int[][] currentFrame, int[][] previousFrame) {
        int[][] possibleDownShift = new int[previousFrame.length][previousFrame[0].length];

        for (int i = 0; i < previousFrame.length; i++) {
            for (int j = 0; j < previousFrame[i].length; j++) {
                if (i == 0) {
                    possibleDownShift[i][j] = 0;
                }
                else {
                    possibleDownShift[i][j] = previousFrame[i - 1][j];
                }
            }
        }

        return framesEqual(currentFrame, possibleDownShift);
    }

    private static boolean possibleAirplane(String currentFrame, String previousFrame) {
        String[] currentSplits = currentFrame.split(" ");
        String[] previousSplits = previousFrame.split(" ");

        int cumulativePossibleAirplanes = 0;

        for (int i = 0; i < currentSplits.length; i++) {

            cumulativePossibleAirplanes += possibleAirplaneInView(currentSplits[i], previousSplits[i]);
        }

        return cumulativePossibleAirplanes == 1;
    }

    private static int possibleAirplaneInView(String currentView, String previousView) {
        int numDifference = 0;
        for (int i = 0; i < currentView.length(); i++) {
            if (singleOneSingleZero(currentView.charAt(i), previousView.charAt(i))) {
                numDifference++;
            }
        }

        return numDifference;
    }

    private static boolean singleOneSingleZero(char first, char second) {
        return (first == '1' && second == '0') || (first == '0' && second == '1');
    }

    /**
     * Inner class that will be used to test the private methods
     */
    public class TestHook {

        public boolean possibleAirplane(String currentFrame, String previousFrame) {
            return AlarmDriver.possibleAirplane(currentFrame, previousFrame);
        }

        public boolean possibleVerticalShift(int[][] currentFrame, int[][] previousFrame) {
            return AlarmDriver.possibleVerticalShift(currentFrame, previousFrame);
        }

        public boolean possibleHorizontalShift(int[][] currentFrame, int[][] previousFrame) {
            return AlarmDriver.possibleHorizontalShift(currentFrame, previousFrame);
        }

        public boolean possibleShift(String currentCameraFeed, String previousCameraFeed, int rows, int columns) {
            return AlarmDriver.possibleShift(currentCameraFeed, previousCameraFeed, rows, columns);
        }

        public boolean knownIssue(String currentFrame, String previousFrame, List<Integer> rowSizeData, List<Integer> columnSizeData) {
            return AlarmDriver.knownIssue(currentFrame, previousFrame, rowSizeData, columnSizeData);
        }

    }

    /**
     * A simple constructor for the sole purpose of running a simple example
     */
    AlarmDriver() { }
}
