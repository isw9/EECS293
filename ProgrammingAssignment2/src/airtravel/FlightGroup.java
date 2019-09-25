package airtravel;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

public final class FlightGroup {
    private final Airport origin;

    private final NavigableMap<LocalTime, Set<Flight>> flights = new TreeMap<LocalTime, Set<Flight>>();

    private FlightGroup(Airport origin) {
        this.origin = origin;
    }

    public static final FlightGroup of(Airport origin) {
        Objects.requireNonNull(origin, "origin cannot be null in FlightGroup build");

        return new FlightGroup(origin);
    }

    //complexity - 3
    public final boolean add(Flight flight) {
        checkFlightOriginAirport(flight);

        if (flights.containsKey(flight.departureTime())) {
            Set<Flight> set = flights.get(flight.departureTime());

            for (Flight f : set) {
                if (f.destination().getCode().equals(flight.destination().getCode())) {
                    return false;
                }
            }

            // making it here means the set of flights corresponding with the current flight's departure time does not
            // contain a flight going to the same place the current flight is going
            set.add(flight);
            flights.put(flight.departureTime(), set);
            return true;
        }
        else {
            Set<Flight> set = new HashSet<Flight>();
            set.add(flight);
            flights.put(flight.departureTime(), set);
            return true;
        }
    }

    //complexity - 3
    public final boolean remove(Flight flight) {
        checkFlightOriginAirport(flight);

        if (flights.containsKey(flight.departureTime())) {
            Set<Flight> set = flights.get(flight.departureTime());
            for (Flight f : set) {
                if (f.destination().getCode().equals(flight.destination().getCode())) {
                    set.remove(flight);
                    return true;
                }
            }
        }

        return false;
    }

    public void checkFlightOriginAirport(Flight flight) {
        if (!flight.origin().equals(getOrigin())) {
            throw new IllegalArgumentException("Flight's origin airport was not correct. Expected: "
                    + getOrigin().getCode() + " and was actually: " + flight.origin().getCode());

        }
    }

    public final Set<Flight> flightsAtOrAfter(LocalTime departureTime) {
        NavigableMap<LocalTime, Set<Flight>> flightsAtOrAfter = flights.tailMap(departureTime, true);
        Set<Flight> result = new HashSet<Flight>();

        for (Map.Entry<LocalTime, Set<Flight>> entry : flightsAtOrAfter.entrySet()) {
            result.addAll(entry.getValue());

        }

        return result;
    }


    public Airport getOrigin() {
        return this.origin;
    }
}
