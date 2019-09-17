package airtravel;

import java.util.function.BiFunction;

public class FlightPolicy extends AbstractFlight {

    private final Flight flight;

    private final BiFunction<SeatConfiguration, FareClass, SeatConfiguration> policy;

    private FlightPolicy(Flight flight, BiFunction<SeatConfiguration, FareClass, SeatConfiguration> policy) {
        this.flight = flight;
        this.policy = policy;
    }

    //this method needs to create the new policy and replace the flight with the policy at the departure airport
    public FlightPolicy of(Flight flight, BiFunction<SeatConfiguration, FareClass, SeatConfiguration> policy) {
        if (flight == null || policy == null) {
            throw new NullPointerException("Input(s) to FlightPolicy build cannot be null");
        }

        return new FlightPolicy(flight, policy);
    }

    public String getCode() {
        return flight.getCode();
    }

    public Leg getLeg() {
        return flight.getLeg();
    }

    public SeatConfiguration seatsAvailable(FareClass fareClass) {
        flight.seatsAvailable(fareClass);
        return policy.apply(flight.seatsAvailable(fareClass), fareClass);
    }

    @Override
    public FlightSchedule getFlightSchedule() {
        return flight.getFlightSchedule();
    }
}
