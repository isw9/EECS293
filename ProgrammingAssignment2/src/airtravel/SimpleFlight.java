package airtravel;

public final class SimpleFlight extends AbstractFlight {

    private final String code;

    private final Leg leg;

    private final FlightSchedule flightSchedule;

    private SimpleFlight(String code, Leg leg, FlightSchedule flightSchedule) {
        this.code = code;
        this.leg = leg;
        this.flightSchedule = flightSchedule;
    }

    public static final SimpleFlight of(String code, Leg leg, FlightSchedule flightSchedule) {
        if (code == null || leg == null || flightSchedule == null) {
            throw new NullPointerException("Input value(s) to the SimpleFlight build method cannot be null.");
        }


        SimpleFlight flight = new SimpleFlight(code, leg, flightSchedule);
        leg.getOrigin().addFlight(flight);

        return flight;
    }

    public String getCode() {
        return this.code;
    }

    public Leg getLeg() {
        if (this.leg == null) {
            throw new NullPointerException("Leg cannot be null");
        }
        return this.leg;
    }

    public FlightSchedule getFlightSchedule() {
        return this.flightSchedule;
    }
}
