package alarms;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class InputBarricade {
    public List<String> checkInput(FileBarricade fileBarricade) {
        List<String> lines = fileBarricade.readFile();

        if (lines.size() < 2) {
            return new ArrayList<String>();
        }

        if (!gridSizeValid(lines.get(0))) {
            return new ArrayList<String>();
        }

        List<Integer> gridSizes = gridSizes(lines.get(0));
        int front = gridSizes.get(2) * gridSizes.get(0);
        int side = gridSizes.get(2) * gridSizes.get(1);
        int top = gridSizes.get(1) * gridSizes.get(0);

        List<Integer> expectedCameraFeedSizes = new ArrayList<Integer>(Arrays.asList(front, side, top));

        for (String cameraFeed : lines.subList(1, lines.size())) {
            if (!cameraFeedsValid(cameraFeed, expectedCameraFeedSizes)) {
                return new ArrayList<String>();
            }
        }

        return lines;
    }

    public List<Integer> gridSizes(String gridSizeLine) {
        List<Integer> results = new ArrayList<Integer>();
        String[] gridSizes = gridSizeLine.split(" ");

        for (String gridSize : gridSizes) {
            results.add(Integer.parseInt(gridSize));
        }

        return results;
    }

    private boolean gridSizeValid(String gridSize) {
        String[] gridSizes = gridSize.split(" ");

        if (gridSizes.length != 3) {
            return false;
        }

        for (String gridNumber : gridSizes) {
            try {
                if (Integer.parseInt(gridNumber) <= 0) {
                    return false;
                }
            }
            catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

    private boolean cameraFeedsValid(String camerasFeed, List<Integer> cameraExpectedValues) {
        String[] feeds = camerasFeed.split(" ");
        if (feeds.length != 3) {
            return false;
        }

        for (int i = 0; i < feeds.length; i++) {
            if (feeds[i].length() != cameraExpectedValues.get(i) || !validDataValues(feeds[i])) {
                return false;
            }
        }

        return true;
    }

    private boolean validDataValues(String cameraFeed) {
        String validValuesCameraFeed = validDataValuesCameraFeed(cameraFeed);
        for (char c : validValuesCameraFeed.toCharArray()) {
            int numericValue = Character.getNumericValue(c);
            if (numericValue < 0 || numericValue >= 2) {
                return false;
            }
        }

        return true;
    }

    private String validDataValuesCameraFeed(String cameraFeed) {
        StringBuilder sb = new StringBuilder();

        for (char c : cameraFeed.toCharArray()) {
            int numericValue = Character.getNumericValue(c);
            if (2 <= numericValue && numericValue <= 9) {
                sb.append(1);
            }
            else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * Inner class that will be used to test the private methods
     */
    public class TestHook {

        public boolean gridSizeValid(String gridSize) {
            return InputBarricade.this.gridSizeValid(gridSize);
        }

        public boolean cameraFeedsValid(String camerasFeed, List<Integer> cameraExpectedValues) {
            return InputBarricade.this.cameraFeedsValid(camerasFeed, cameraExpectedValues);
        }

        public boolean validDataValues(String cameraFeed) {
            return InputBarricade.this.validDataValues(cameraFeed);
        }

        public String validDataValuesCameraFeed(String cameraFeed) {
            return InputBarricade.this.validDataValuesCameraFeed(cameraFeed);
        }
    }

    /**
     * A simple constructor for the sole purpose of running a simple example
     */
    InputBarricade() { }
}
