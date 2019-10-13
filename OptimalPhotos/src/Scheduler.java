import java.util.ArrayList;
import java.util.List;

public class Scheduler {


    public static List<Location> optimalLocations(List<Location> locations) {
        //Assert that the list of inputted Locations is not empty.
        if (locations.size() == 0) {
            throw new IllegalArgumentException("Input List of locations cannot be empty");
        }

        //Initialize an empty list maxPriorityScore.
        int[] maxPriorityScore = new int[locations.size() + 1];
        maxPriorityScore[0] = 0;

        //Initialize a list predecessor such that predecessor[i] = predecessor(i) as defined above.
        int[] predecessors = new int[locations.size() + 1];

        for (int i = 0; i < predecessors.length; i++) {
            predecessors[i] = predecessor(i, locations);
        }

        //Initialize an empty list of Locations optimalSchedule
        List<Location> optimalSchedule = new ArrayList<Location>();

        //for each Location in the input list, inputList(i):
        //  maxPriorityScore[i] = max {maxPriorityScore[i-1], maxPriorityScore[predecessor[i]] + Location's priority}

        for (int i = 0; i < locations.size(); i++) {
            maxPriorityScore[i + 1] = Math.max(maxPriorityScore[i], maxPriorityScore[predecessors[i + 1]] + locations.get(i).getPriority());
        }


        // for each Location in input list (work backwards):
        for (int i = locations.size() - 1; i >= 0; i--) {
            //if maxPriorityScore[i] == maxPriorityScore[i-1]:
                //current Location is not included, do nothing
            if (maxPriorityScore[i + 1] == maxPriorityScore[i]) {
                //do nothing
            }
            //else if maxPriorityScore[i] == maxPriorityScore[predecessor[i]] + ith Location's priority:
            //add inputList[i] to optimalSchedule
            //consider the predecessor[i]th location next
            else if (maxPriorityScore[i + 1] == maxPriorityScore[predecessors[i+1]] + locations.get(i).getPriority()) {
                optimalSchedule.add(locations.get(i));
                i = predecessors[i+1];
            }
            //other wise do nothing as the current Location is not included in the optimal schedule
            else {
                //do nothing
            }
        }

        //return optimalSchedule
        return optimalSchedule;
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
        if (potentialPredecessor.getEndTime() < location.getStartTime()) {
            return true;
        }

        return false;
    }
}
