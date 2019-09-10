package airtravel;

import java.time.LocalTime;
import java.util.*;

public final class FlightGroup {
    private final Airport origin;

    private final NavigableMap<LocalTime, Flight> flights = new TreeMap<LocalTime, Flight>();

    private FlightGroup(Airport origin) {
        this.origin = origin;
    }

    public static final FlightGroup of(Airport origin) {
        if (origin == null) {
            throw new NullPointerException("Input to FlightGroup build method cannot be null");
        }

        return new FlightGroup(origin);
    }

    public final boolean add(Flight flight) {
        if (!flight.origin().equals(getOrigin())) {
            throw new IllegalArgumentException("Flight's origin airport was not correct. Expected: "
            + getOrigin().getCode() + " and was actually: " + flight.origin().getCode());
        }

        //if the flight group already contains the same departure time, it contains the same flight because
        //we have already checked if the flight is leaving from the same airport
        if (flights.containsKey(flight.departureTime())) {
            flights.put(flight.departureTime(), flight);
            return false;
        }
        else {
            flights.put(flight.departureTime(), flight);
            return true;
        }
    }

    public final boolean remove(Flight flight) {
        if (!flight.origin().equals(getOrigin())) {
            throw new IllegalArgumentException("Flight's origin airport was not correct. Expected: "
                    + getOrigin().getCode() + " and was actually: " + flight.origin().getCode());
        }

        if (flights.containsKey(flight.departureTime())) {
            flights.remove(flight.departureTime());
            return true;
        }

        return false;
    }

    public final Set<Flight> flightsAtOrAfter(LocalTime departureTime) {
        NavigableMap<LocalTime, Flight> flightsAtOrAfter = flights.tailMap(departureTime, true);

        return new HashSet<Flight>(flightsAtOrAfter.values());
    }


    public Airport getOrigin() {
        return this.origin;
    }
}
