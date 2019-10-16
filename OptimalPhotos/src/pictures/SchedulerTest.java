package pictures;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SchedulerTest {

    @Test
    public void optimalLocations() {
        List<Location> locations = new ArrayList<Location>();
        Location one = Location.of("Guardians of Transportation", 20, 480, 660);
        Location two = Location.of("Tower City", 40, 540, 780);
        Location three = Location.of("West Side Market", 40, 720, 840);
        Location four = Location.of("Severance Hall", 20, 900, 1020);
        Location five = Location.of("Museum of Arts", 70, 600, 1080);
        Location six = Location.of("Glennan", 10, 960, 1140);


        locations.add(one);
        locations.add(two);
        locations.add(three);
        locations.add(four);
        locations.add(five);
        locations.add(six);
        List<Location> optimalLocations = Scheduler.optimalLocations(locations);

        assertEquals(3, optimalLocations.size());
        assertTrue(optimalLocations.contains(four));
        assertTrue(optimalLocations.contains(three));
        assertTrue(optimalLocations.contains(one));
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidOptimalScheduleEmptyList() {
        List<Location> locations = new ArrayList<Location>();

        Scheduler.optimalLocations(locations);
    }
}
