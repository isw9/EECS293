package airtravel;

import java.time.Duration;
import java.time.LocalTime;

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
        if (!isKnown()) {
            return UNKNOWN;
        }

        LocalTime time = getTime();
        return new RouteTime(time.plusSeconds(duration.getSeconds()));
    }

    //complexity = 4
    @Override
    public int compareTo(RouteTime alternateRouteTime) {
        boolean thisIsUnknown = !isKnown();
        boolean otherIsUnknown = !alternateRouteTime.isKnown();

        // if neither RouteTime is known, return 0
        if (thisIsUnknown && otherIsUnknown) {
            return 0;
        }
        // check if the only original RouteTime is unknown
        else if (thisIsUnknown) {
            return 1;
        }
        // check if the only alternate RouteTime is unknown
        else if (otherIsUnknown){
            return -1;
        }
        else {
            return this.routeTime.compareTo(alternateRouteTime.routeTime);
        }
    }
}
