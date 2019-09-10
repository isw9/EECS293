package airtravel;

import java.time.Duration;
import java.time.LocalTime;

public final class FlightSchedule {

    private final LocalTime departureTime;

    private final LocalTime arrivalTime;

    private FlightSchedule(LocalTime departureTime, LocalTime arrivalTime) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public static final FlightSchedule of(LocalTime departureTime, LocalTime arrivalTime) {
        if (departureTime == null || arrivalTime == null) {
            throw new NullPointerException("Input(s) to the FlightSchedule build method cannot be null");
        }
        else if (arrivalTime.isBefore(departureTime)) {
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
