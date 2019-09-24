package airtravel;

import java.util.Objects;

public final class SimpleFlight extends AbstractFlight {

    private final String code;

    private final Leg leg;

    private final FlightSchedule flightSchedule;

    private final SeatConfiguration seatsAvailable;

    private SimpleFlight(String code, Leg leg, FlightSchedule flightSchedule, SeatConfiguration seatsAvailable) {
        this.code = code;
        this.leg = leg;
        this.flightSchedule = flightSchedule;
        this.seatsAvailable = seatsAvailable;
    }

    public static final SimpleFlight of(String code, Leg leg, FlightSchedule flightSchedule, SeatConfiguration seatsAvailable) {
        Objects.requireNonNull(code, "code cannot be null in SimpleFlight build");
        Objects.requireNonNull(leg, "leg cannot be null in SimpleFlight build");
        Objects.requireNonNull(flightSchedule, "flightSchedule cannot be null in SimpleFlight build");
        Objects.requireNonNull(seatsAvailable, "seatsAvailable cannot be null in SimpleFlight build");

        SimpleFlight flight = new SimpleFlight(code, leg, flightSchedule, seatsAvailable);
        leg.getOrigin().addFlight(flight);

        return flight;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public Leg getLeg() {
        Objects.requireNonNull(this.leg, "leg cannot be null when calling getLeg() in SimpleFlight");
        return this.leg;
    }

    public FlightSchedule getFlightSchedule() {
        return this.flightSchedule;
    }

    //right now instructions say to just return all available seats not taking into account FareClass
    public SeatConfiguration seatsAvailable(FareClass fareClass) {
        return seatsAvailable;
    }
}
