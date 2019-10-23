

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SchedulerTest {

    //structured basis
    //good data with multiple locations
    @Test
    public void validOptimalLocations() {
        List<Location> locations = new ArrayList<Location>();
        Location locationOne = Location.of("Guardiansssssss of Transportation", 20, 480, 660);
        Location locationTwo = Location.of("Tower City", 40, 540, 780);
        Location locationThree = Location.of("West Side Market", 40, 720, 840);
        Location locationFour = Location.of("Severance Hall", 20, 900, 1020);
        Location locationFive = Location.of("Museum of Arts", 70, 600, 1080);
        Location locationSix = Location.of("Glennan", 10, 960, 1140);


        locations.add(locationOne);
        locations.add(locationTwo);
        locations.add(locationThree);
        locations.add(locationFour);
        locations.add(locationFive);
        locations.add(locationSix);
        List<Location> optimalLocations = Scheduler.optimalLocations(locations);

        assertEquals(3, optimalLocations.size());
        assertTrue(optimalLocations.contains(locationFour));
        assertTrue(optimalLocations.contains(locationThree));
        assertTrue(optimalLocations.contains(locationOne));
    }

    //structured basis
    // good data with one location
    @Test
    public void validOptimalLocationsOneLocation() {
        List<Location> locations = new ArrayList<Location>();
        Location locationOne = Location.of("location one", 10, 20, 500);

        locations.add(locationOne);
        List<Location> optimalLocations = Scheduler.optimalLocations(locations);

        assertEquals(1, optimalLocations.size());
        assertTrue(optimalLocations.contains(locationOne));
    }

    //structured basis
    //bad data (empty list of locations)
    @Test(expected = IllegalArgumentException.class)
    public void invalidOptimalScheduleEmptyList() {
        List<Location> locations = new ArrayList<Location>();

        Scheduler.optimalLocations(locations);
    }

    //structured basis -> for loop is executed only once
    //bad data (empty list of locations)
    @Test
    public void seedPredecessorsEmptyLocations() {
        Scheduler scheduler = new Scheduler();
        Scheduler.TestHook testHook = scheduler.new TestHook();

        List<Location> locations = new ArrayList<Location>();

        int[] predecessors = testHook.seedPredecessors(locations);

        assertEquals(1, predecessors.length);
        assertEquals(0, predecessors[0]);
    }

    //structured basis -> for loop is executed
    // good data (nominal case)
    @Test
    public void seedPredecessorsNominal() {
        Scheduler scheduler = new Scheduler();
        Scheduler.TestHook testHook = scheduler.new TestHook();

        List<Location> locations = new ArrayList<Location>();
        Location locationOne = Location.of("Guardianss of Transportation", 20, 480, 660);
        Location locationTwo = Location.of("Tower City", 40, 540, 780);
        Location locationThree = Location.of("West Side Market", 40, 720, 840);
        Location locationFour = Location.of("Severance Hall", 20, 900, 1020);
        Location locationFive = Location.of("Museum of Arts", 70, 600, 1080);
        Location locationSix = Location.of("Glennan", 10, 960, 1140);


        locations.add(locationOne);
        locations.add(locationTwo);
        locations.add(locationThree);
        locations.add(locationFour);
        locations.add(locationFive);
        locations.add(locationSix);
        int[] predecessors = testHook.seedPredecessors(locations);

        int[] expectedPredecessors = {0, 0, 0, 1, 3, 0, 3};
        for (int i = 0; i < predecessors.length; i++) {
            assertEquals(expectedPredecessors[i], predecessors[i]);
        }
    }

    //structured basis -> index is zero
    @Test
    public void predecessorZeroIndex() {
        Scheduler scheduler = new Scheduler();
        Scheduler.TestHook testHook = scheduler.new TestHook();

        List<Location> locations = new ArrayList<Location>();

        int predecessorIndex = testHook.predecessor(0, locations);

        assertEquals(0, predecessorIndex);
    }

    //structured basis -> index is one
    @Test
    public void predecessorOneIndex() {
        Scheduler scheduler = new Scheduler();
        Scheduler.TestHook testHook = scheduler.new TestHook();

        List<Location> locations = new ArrayList<Location>();

        int predecessorIndex = testHook.predecessor(1, locations);

        assertEquals(0, predecessorIndex);
    }

    //structured basis -> for loop first evaluation has a valid predecessor
    //boundary
    @Test
    public void predecessorFirstLocationIsValid() {
        Scheduler scheduler = new Scheduler();
        Scheduler.TestHook testHook = scheduler.new TestHook();

        List<Location> locations = new ArrayList<Location>();
        Location locationOne = Location.of("one", 10, 100, 200);
        Location locationTwo = Location.of("two", 20, 210, 400);
        locations.add(locationOne);
        locations.add(locationTwo);

        int predecessorIndex = testHook.predecessor(2, locations);

        assertEquals(1, predecessorIndex);
    }

    //structured basis -> for loop first evaluation does not have a valid predecessor
    //boundary
    @Test
    public void predecessorFirstLocationIsNotValid() {
        Scheduler scheduler = new Scheduler();
        Scheduler.TestHook testHook = scheduler.new TestHook();

        List<Location> locations = new ArrayList<Location>();
        Location locationOne = Location.of("one", 10, 100, 200);
        Location locationTwo = Location.of("two", 20, 210, 400);
        Location locationThree = Location.of("three", 30, 250, 410);
        locations.add(locationOne);
        locations.add(locationTwo);
        locations.add(locationThree);

        int predecessorIndex = testHook.predecessor(3, locations);

        assertEquals(1, predecessorIndex);
    }

    //structured basis -> for loop is evaluated completely and no predecessor is found
    @Test
    public void predecessorNoneValid() {
        Scheduler scheduler = new Scheduler();
        Scheduler.TestHook testHook = scheduler.new TestHook();

        List<Location> locations = new ArrayList<Location>();
        Location locationOne = Location.of("one", 10, 100, 270);
        Location locationTwo = Location.of("two", 20, 210, 400);
        Location locationThree = Location.of("three", 30, 250, 410);
        locations.add(locationOne);
        locations.add(locationTwo);
        locations.add(locationThree);

        int predecessorIndex = testHook.predecessor(3, locations);

        assertEquals(0, predecessorIndex);
    }

    //structured basis -> true case
    // boundary
    @Test
    public void isValidPredecessorYes() {
        Scheduler scheduler = new Scheduler();
        Scheduler.TestHook testHook = scheduler.new TestHook();

        List<Location> locations = new ArrayList<Location>();
        Location potentialPredecessor = Location.of("one", 10, 100, 270);
        Location locationTwo = Location.of("two", 20, 271, 400);
        locations.add(potentialPredecessor);
        locations.add(locationTwo);

        boolean isValid = testHook.validPredecessor(potentialPredecessor, locationTwo);

        assertTrue(isValid);
    }

    //structured basis -> start time and end time are exactly the same
    // boundary
    @Test
    public void isValidPredecessorEquslTimes() {
        Scheduler scheduler = new Scheduler();
        Scheduler.TestHook testHook = scheduler.new TestHook();

        List<Location> locations = new ArrayList<Location>();
        Location potentialPredecessor = Location.of("one", 10, 100, 270);
        Location locationTwo = Location.of("two", 20, 270, 400);
        locations.add(potentialPredecessor);
        locations.add(locationTwo);

        boolean isValid = testHook.validPredecessor(potentialPredecessor, locationTwo);

        assertFalse(isValid);
    }

    //structured basis -> false case
    // boundary
    @Test
    public void isValidPredecessorNo() {
        Scheduler scheduler = new Scheduler();
        Scheduler.TestHook testHook = scheduler.new TestHook();

        List<Location> locations = new ArrayList<Location>();
        Location potentialPredecessor = Location.of("one", 10, 100, 270);
        Location locationTwo = Location.of("two", 20, 269, 400);
        locations.add(potentialPredecessor);
        locations.add(locationTwo);

        boolean isValid = testHook.validPredecessor(potentialPredecessor, locationTwo);

        assertFalse(isValid);
    }

    //structured basis
    // bad data, empty list of Locations
    @Test
    public void seedMaxPriorityScoreEmptyListLocations() {
        Scheduler scheduler = new Scheduler();
        Scheduler.TestHook testHook = scheduler.new TestHook();

        List<Location> locations = new ArrayList<Location>();
        int[] predecessors = {0};

        int[] maxPriorityScores = testHook.seedMaxPriorityScore(locations, predecessors);

        assertEquals(1, maxPriorityScores.length);
        assertEquals(0, maxPriorityScores[0]);
    }

    //structured basis -> 2 Locations where both are included
    // good data nominal case where both locations are included
    @Test
    public void seedMaxPriorityScoreMultipleLocations() {
        Scheduler scheduler = new Scheduler();
        Scheduler.TestHook testHook = scheduler.new TestHook();

        List<Location> locations = new ArrayList<Location>();
        Location locationOne = Location.of("one", 10, 100, 270);
        Location locationTwo = Location.of("two", 20, 271, 400);
        locations.add(locationOne);
        locations.add(locationTwo);

        int[] predecessors = testHook.seedPredecessors(locations);

        int[] maxPriorityScores = testHook.seedMaxPriorityScore(locations, predecessors);

        assertEquals(3, maxPriorityScores.length);
        assertEquals(0, maxPriorityScores[0]);
        assertEquals(10, maxPriorityScores[1]);
        assertEquals(30, maxPriorityScores[2]);
    }

    //structured basis -> 2 Locations where 1st location is included
    // good data nominal case where 1st location is included
    @Test
    public void seedMaxPriorityScoreMultipleLocationsFirstIncluded() {
        Scheduler scheduler = new Scheduler();
        Scheduler.TestHook testHook = scheduler.new TestHook();

        List<Location> locations = new ArrayList<Location>();
        Location locationOne = Location.of("one", 40, 100, 270);
        Location locationTwo = Location.of("two", 20, 269, 400);
        locations.add(locationOne);
        locations.add(locationTwo);

        int[] predecessors = testHook.seedPredecessors(locations);

        int[] maxPriorityScores = testHook.seedMaxPriorityScore(locations, predecessors);

        assertEquals(3, maxPriorityScores.length);
        assertEquals(0, maxPriorityScores[0]);
        assertEquals(40, maxPriorityScores[1]);
        assertEquals(40, maxPriorityScores[2]);
    }

    //structured basis -> 2 Locations where 2nd location is included
    // good data nominal case where 2nd location is included
    @Test
    public void seedMaxPriorityScoreMultipleLocationsSecondIncluded() {
        Scheduler scheduler = new Scheduler();
        Scheduler.TestHook testHook = scheduler.new TestHook();

        List<Location> locations = new ArrayList<Location>();
        Location locationOne = Location.of("one", 10, 100, 270);
        Location locationTwo = Location.of("two", 20, 269, 400);
        locations.add(locationOne);
        locations.add(locationTwo);

        int[] predecessors = testHook.seedPredecessors(locations);

        int[] maxPriorityScores = testHook.seedMaxPriorityScore(locations, predecessors);

        assertEquals(3, maxPriorityScores.length);
        assertEquals(0, maxPriorityScores[0]);
        assertEquals(10, maxPriorityScores[1]);
        assertEquals(20, maxPriorityScores[2]);
    }

    //structured basis -> Last Location is not included (first if statement is true)
    @Test
    public void calculateOptimalScheduleLastNotIncluded() {
        Scheduler scheduler = new Scheduler();
        Scheduler.TestHook testHook = scheduler.new TestHook();

        List<Location> locations = new ArrayList<Location>();
        Location locationOne = Location.of("one", 10, 100, 270);
        Location locationTwo = Location.of("two", 20, 269, 400);
        Location locationThree = Location.of("three", 4, 390, 500);
        locations.add(locationOne);
        locations.add(locationTwo);
        locations.add(locationThree);

        int[] predecessors = testHook.seedPredecessors(locations);

        int[] maxPriorityScores = testHook.seedMaxPriorityScore(locations, predecessors);

        List<Location> optimalLocations = testHook.calculateOptimalSchedule(locations, maxPriorityScores, predecessors);

        assertEquals(1, optimalLocations.size());
        assertFalse(optimalLocations.contains(locationThree));
    }

    //structured basis -> Last Location is included (first if statement is false, second is true)
    @Test
    public void calculateOptimalScheduleLastIsIncluded() {
        Scheduler scheduler = new Scheduler();
        Scheduler.TestHook testHook = scheduler.new TestHook();

        List<Location> locations = new ArrayList<Location>();
        Location locationOne = Location.of("one", 10, 100, 270);
        Location locationTwo = Location.of("two", 20, 269, 400);
        Location locationThree = Location.of("three", 40, 390, 500);
        locations.add(locationOne);
        locations.add(locationTwo);
        locations.add(locationThree);

        int[] predecessors = testHook.seedPredecessors(locations);

        int[] maxPriorityScores = testHook.seedMaxPriorityScore(locations, predecessors);

        List<Location> optimalLocations = testHook.calculateOptimalSchedule(locations, maxPriorityScores, predecessors);

        assertEquals(2, optimalLocations.size());
        assertTrue(optimalLocations.contains(locationThree));
    }

    //structured basis -> Last Location is not included (first if statement is false, second is false)
    @Test
    public void calculateOptimalScheduleLastIsIncludedIfStatementsFalse() {
        Scheduler scheduler = new Scheduler();
        Scheduler.TestHook testHook = scheduler.new TestHook();

        List<Location> locations = new ArrayList<Location>();
        Location locationOne = Location.of("one", 10, 100, 270);
        Location locationTwo = Location.of("two", 20, 271, 400);
        Location locationThree = Location.of("three", 25, 269, 500);
        locations.add(locationOne);
        locations.add(locationTwo);
        locations.add(locationThree);

        int[] predecessors = testHook.seedPredecessors(locations);

        int[] maxPriorityScores = testHook.seedMaxPriorityScore(locations, predecessors);

        List<Location> optimalLocations = testHook.calculateOptimalSchedule(locations, maxPriorityScores, predecessors);

        assertEquals(2, optimalLocations.size());
        assertFalse(optimalLocations.contains(locationThree));
    }

    // bad data (empty list of Locations)
    @Test
    public void calculateOptimalScheduleEmptyLocations() {
        Scheduler scheduler = new Scheduler();
        Scheduler.TestHook testHook = scheduler.new TestHook();

        List<Location> locations = new ArrayList<Location>();

        int[] predecessors = testHook.seedPredecessors(locations);

        int[] maxPriorityScores = testHook.seedMaxPriorityScore(locations, predecessors);

        List<Location> optimalLocations = testHook.calculateOptimalSchedule(locations, maxPriorityScores, predecessors);

        assertEquals(0, optimalLocations.size());
    }

    // good data (single Location)
    @Test
    public void calculateOptimalScheduleSingleLocation() {
        Scheduler scheduler = new Scheduler();
        Scheduler.TestHook testHook = scheduler.new TestHook();

        List<Location> locations = new ArrayList<Location>();
        Location locationOne = Location.of("one", 10, 100, 270);
        locations.add(locationOne);

        int[] predecessors = testHook.seedPredecessors(locations);

        int[] maxPriorityScores = testHook.seedMaxPriorityScore(locations, predecessors);

        List<Location> optimalLocations = testHook.calculateOptimalSchedule(locations, maxPriorityScores, predecessors);

        assertEquals(1, optimalLocations.size());
        assertTrue(locations.contains(locationOne));
    }
}
