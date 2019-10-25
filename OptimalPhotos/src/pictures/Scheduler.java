
import java.util.ArrayList;
import java.util.List;

public class Scheduler {


    public static List<Location> optimalLocations(List<Location> locations) {
        //Assert that the list of inputted Locations is not empty.
        if (locations.isEmpty()) {
            throw new IllegalArgumentException("Input List of locations cannot be empty");
        }

        //Initialize an empty list maxPriorityScore.
        //Initialize a list predecessor such that predecessor[i] = predecessor(i) as defined above.
        int[] predecessors = seedPredecessors(locations);

        //Initialize an empty list of Locations optimalSchedule
        List<Location> optimalSchedule = new ArrayList<Location>();

        //for each pictures.Location in the input list, inputList(i):
        //  maxPriorityScore[i] = max {maxPriorityScore[i-1], maxPriorityScore[predecessor[i]] + pictures.Location's priority}
        int[] maxPriorityScores = seedMaxPriorityScore(locations, predecessors);


        optimalSchedule = calculateOptimalSchedule(locations, maxPriorityScores, predecessors);


        //return optimalSchedule
        return optimalSchedule;
    }

    private static int[] seedPredecessors(List<Location> locations) {
        int[] predecessors = new int[locations.size() + 1];

        for (int i = 0; i < predecessors.length; i++) {
            predecessors[i] = predecessor(i, locations);
        }

        return predecessors;
    }

    private static int predecessor(int currentLocationIndex, List<Location> locations) {
        if (currentLocationIndex == 0 || currentLocationIndex == 1) {
            return 0;
        }

        Location currentLocation = locations.get(currentLocationIndex - 1);
        for (int i = currentLocationIndex - 2; i >= 0; i--) {
            if (validPredecessor(locations.get(i), currentLocation)) {
                return i + 1;
            }
        }

        return 0;
    }

    private static boolean validPredecessor(Location potentialPredecessor, Location location) {
        return potentialPredecessor.getEndTime() < location.getStartTime();
    }


    private static int[] seedMaxPriorityScore(List<Location> locations, int[] predecessors) {
        int[] maxPriorityScore = new int[locations.size() + 1];
        maxPriorityScore[0] = 0;

        for (int i = 0; i < locations.size(); i++) {
            maxPriorityScore[i + 1] = Math.max(maxPriorityScore[i], maxPriorityScore[predecessors[i + 1]] + locations.get(i).getPriority());
        }

        return maxPriorityScore;
    }

    private static List<Location> calculateOptimalSchedule(List<Location> locations, int[] maxPriorityScore, int[] predecessors) {
        List<Location> optimalSchedule = new ArrayList<Location>();
        // for each pictures.Location in input list (work backwards):
        for (int i = locations.size() - 1; i >= 0; i--) {
            //if maxPriorityScore[i] == maxPriorityScore[i-1]:
            //current pictures.Location is not included, do nothing
            if (maxPriorityScore[i + 1] == maxPriorityScore[i]) {
                //do nothing
            }
            //else if maxPriorityScore[i] == maxPriorityScore[predecessor[i]] + ith pictures.Location's priority:
            //add inputList[i] to optimalSchedule
            //consider the predecessor[i]th location next
            else if (maxPriorityScore[i + 1] == maxPriorityScore[predecessors[i+1]] + locations.get(i).getPriority()) {
                optimalSchedule.add(locations.get(i));
                i = predecessors[i+1];
            }
            //other wise do nothing as the current pictures.Location is not included in the optimal schedule
            //do nothing
        }

        return optimalSchedule;
    }

    /**
     * Inner class that will be used to test the private methods
     */
    public class TestHook {
        public int[] seedPredecessors(List<Location> locations) {
            return Scheduler.this.seedPredecessors(locations);
        }

        public int predecessor(int currentLocationIndex, List<Location> locations) {
            return Scheduler.this.predecessor(currentLocationIndex, locations);
        }

        public boolean validPredecessor(Location potentialPredecessor, Location location) {
            return Scheduler.this.validPredecessor(potentialPredecessor, location);
        }

        public int[] seedMaxPriorityScore(List<Location> locations, int[] predecessors) {
            return Scheduler.this.seedMaxPriorityScore(locations, predecessors);
        }

        public List<Location> calculateOptimalSchedule(List<Location> locations, int[] maxPriorityScore, int[] predecessors) {
            return Scheduler.this.calculateOptimalSchedule(locations, maxPriorityScore, predecessors);
        }

    }

    /**
     * A simple constructor for the sole purpose of running a simple example
     */
    Scheduler() { }
}
