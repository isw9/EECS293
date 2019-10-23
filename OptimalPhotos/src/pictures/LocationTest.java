

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LocationTest {

    //structured basis, bad data
    @Test(expected = NullPointerException.class)
    public void invalidOf() {
        Location.of(null, 10, 100, 200);
    }

    //structured basis, bad data, boundary
    @Test(expected = IllegalArgumentException.class)
    public void invalidOfNegativePriority() {
        Location.of("Location", -10, 100, 200);
    }

    //structured basis, bad data, boundary
    @Test(expected = IllegalArgumentException.class)
    public void invalidOfNegativeStartTime() {
        Location.of("Location", 10, -100, 200);
    }

    //structured basis, bad data, boundary
    @Test(expected = IllegalArgumentException.class)
    public void invalidOfZeroStartTime() {
        Location.of("Location", 10, 0, 200);
    }

    //structured basis, bad data, boundary
    @Test(expected = IllegalArgumentException.class)
    public void invalidOfNegativeEndTime() {
        Location.of("Location", 10, 0, -100);
    }

    //structured basis, bad data, boundary
    @Test(expected = IllegalArgumentException.class)
    public void invalidOfZeroEndTime() {
        Location.of("Location", 10, 10, 0);
    }

    //structured basis, bad data, boundary
    @Test(expected = IllegalArgumentException.class)
    public void invalidOfStartTimeGreaterEndTime() {
        Location.of("Location", 10, 10, 5);
    }

    //structured basis, bad data, boundary
    @Test(expected = IllegalArgumentException.class)
    public void invalidOfStartTimeEqualEndTime() {
        Location.of("Location", 10, 100, 100);
    }

    //structured basis, good data
    @Test
    public void validOf() {
        Location location = Location.of("Name", 10, 200, 400);

        assertEquals("Name", location.getName());
        assertEquals(10, location.getPriority());
        assertEquals(200, location.getStartTime());
        assertEquals(400, location.getEndTime());
    }
}
