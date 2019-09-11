package airtravel;

import java.time.Duration;
import java.time.LocalTime;

public abstract class AbstractFlight implements Flight {

    @Override
    public Airport origin() {
        Leg leg = getLeg();

        if (leg == null) {
            throw new NullPointerException("Leg cannot be null");
        }

        return leg.getOrigin();
    }

    @Override
    public Airport destination() {
        Leg leg = getLeg();
        return leg.getDestination();
    }

    @Override
    public LocalTime departureTime() {
        FlightSchedule schedule = getFlightSchedule();
        return schedule.getDepartureTime();
    }

    @Override
    public LocalTime arrivalTime() {
        FlightSchedule schedule = getFlightSchedule();
        return schedule.getArrivalTime();
    }

    @Override
    public boolean isShort(Duration durationMax) {
        FlightSchedule schedule = getFlightSchedule();
        return schedule.isShort(durationMax);
    }
}
