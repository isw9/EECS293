package airtravel;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public final class FlightSchedule {

    private final LocalTime departureTime;

    private final LocalTime arrivalTime;

    private FlightSchedule(LocalTime departureTime, LocalTime arrivalTime) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public static final FlightSchedule of(LocalTime departureTime, LocalTime arrivalTime) {
        Objects.requireNonNull(departureTime, "departureTime in FlightSchedule build can't be null");
        Objects.requireNonNull(arrivalTime, "departureTime in FlightSchedule build can't be null");
        if (arrivalTime.isBefore(departureTime)) {
            throw new IllegalArgumentException("Arrival time cannot come before the departure time");
        }

        return new FlightSchedule(departureTime, arrivalTime);
    }

    public boolean isShort(Duration durationMax) {
        Duration flightDuration = Duration.between(getDepartureTime(), getArrivalTime());
        return flightDuration.getSeconds() <= durationMax.getSeconds();
    }

    public LocalTime getDepartureTime() {
        return this.departureTime;
    }

    public LocalTime getArrivalTime() {
        return this.arrivalTime;
    }
}
