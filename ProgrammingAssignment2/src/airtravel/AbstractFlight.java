package airtravel;

import java.time.Duration;
import java.time.LocalTime;

public abstract class AbstractFlight implements Flight {

    @Override
    public Airport origin() {
        return getLeg().getOrigin();
    }

    @Override
    public Airport destination() {
        return getLeg().getDestination();
    }

    @Override
    public LocalTime departureTime() {
        return getFlightSchedule().getDepartureTime();
    }

    @Override
    public LocalTime arrivalTime() {
        return getFlightSchedule().getArrivalTime();
    }

    @Override
    public boolean isShort(Duration durationMax) {
        return getFlightSchedule().isShort(durationMax);
    }

    @Override
    public boolean hasSeats(FareClass fareClass) {
        return seatsAvailable(fareClass).set.size() > 0;
    }
}
