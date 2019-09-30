package airtravel;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public final class RouteTime implements Comparable<RouteTime> {

    private final LocalTime routeTime;

    public static final RouteTime UNKNOWN = new RouteTime(null);

    public RouteTime(LocalTime routeTime) {

        this.routeTime = routeTime;
    }

    public boolean isKnown() {
        return routeTime != null;
    }

    public LocalTime getTime() {
        if (!isKnown()) {
            throw new IllegalStateException("Route time is not known");
        }

        return routeTime;
    }

    public RouteTime plus(Duration duration) {
        Objects.requireNonNull(duration, "duration cannot be null in plus method");
        if (!isKnown()) {
            return UNKNOWN;
        }

        LocalTime time = getTime();
        return new RouteTime(time.plusSeconds(duration.getSeconds()));
    }

    //complexity = 2
    @Override
    public int compareTo(RouteTime alternateRouteTime) {
        Objects.requireNonNull(alternateRouteTime, "other RouteTime object cannot be null in overridden" +
                "compareTo method");
        boolean thisIsUnknown = !isKnown();
        boolean otherIsUnknown = !alternateRouteTime.isKnown();
        int booleanComparison = Boolean.compare(thisIsUnknown, otherIsUnknown);

        if (booleanComparison != 0) {
            return booleanComparison;
        }

        // if we reach this point, the times are either both known or both unknown
        // if thisIsUnknown is true, the times for both RouteTimes are unknown so return 0 because we can't compare them
        // if thisIsUnknown is false, use the LocalTime compareTo method
        return thisIsUnknown ? 0 : this.routeTime.compareTo(alternateRouteTime.routeTime);
    }
}
