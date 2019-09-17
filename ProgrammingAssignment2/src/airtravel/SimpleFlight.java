package airtravel;

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
        if (code == null || leg == null || flightSchedule == null ||  seatsAvailable == null) {
            throw new NullPointerException("Input value(s) to the SimpleFlight build method cannot be null.");
        }


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
        if (this.leg == null) {
            throw new NullPointerException("Leg cannot be null");
        }
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
