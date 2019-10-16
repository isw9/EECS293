package pictures;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LocationTest {

    @Test(expected = NullPointerException.class)
    public void invalidOf() {
        Location.of(null, 10, 100, 200);
    }

    @Test
    public void validOf() {
        Location location = Location.of("Name", 10, 200, 400);

        assertEquals("Name", location.getName());
    }
}