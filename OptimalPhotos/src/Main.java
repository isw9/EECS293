import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
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
        Scheduler.optimalLocations(locations);
    }
}
