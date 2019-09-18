package airtravel;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.BiFunction;

public class FlightPolicy extends AbstractFlight {

    private final Flight flight;

    private final BiFunction<SeatConfiguration, FareClass, SeatConfiguration> policy;

    private FlightPolicy(Flight flight, BiFunction<SeatConfiguration, FareClass, SeatConfiguration> policy) {
        this.flight = flight;
        this.policy = policy;
    }

    //this method needs to create the new policy and replace the flight with the policy at the departure airport
    public static FlightPolicy of(Flight flight, BiFunction<SeatConfiguration, FareClass, SeatConfiguration> policy) {
        Objects.requireNonNull(flight, "Flight cannot be null in FlightPolicy build");
        Objects.requireNonNull(policy, "Policy cannot be null in FlightPolicy build");

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

    public static final Flight strict(Flight flight) {
        FlightPolicy policy = FlightPolicy.of(flight, (seatConfig, fareClassConfig) -> {
            SeatConfiguration copySeatConfig = SeatConfiguration.of(seatConfig);
            for (SeatClass seatClass : SeatClass.values()) {
                if (seatClass != fareClassConfig.getSeatClass()) {
                    copySeatConfig.setSeats(seatClass, 0);
                }
            }
            return copySeatConfig;
        });

        return policy;
    }

    public static final Flight restrictedDuration(Flight flight, Duration durationMax) {
        if (flight.isShort(durationMax)) {
            return strict(flight);
        }
        else {
            FlightPolicy policy = FlightPolicy.of(flight, (seatConfig, fareClassConfig) -> SeatConfiguration.of(seatConfig));
            return policy;
        }
    }

    public static final Flight reserve(Flight flight, int reserve) {
        FlightPolicy policy = FlightPolicy.of(flight, (seatConfig, fareClassConfig) -> {
            SeatConfiguration copySeatConfig = SeatConfiguration.of(seatConfig);
            for (SeatClass seatClass : SeatClass.values()) {
                int currentSeats = seatConfig.seats(seatClass);
                copySeatConfig.setSeats(seatClass, Math.max(currentSeats - 3, 0));
            }
            return copySeatConfig;
        });

        return policy;
    }

    public static final Flight limited(Flight flight) {
        FlightPolicy policy = FlightPolicy.of(flight, (seatConfig, fareClassConfig) -> {
            SeatConfiguration copySeatConfig = SeatConfiguration.of(seatConfig);
            List<SeatClass> seatClassList = Arrays.asList(SeatClass.values());
            Collections.reverse(seatClassList);
            SeatClass passengerSeatClass = fareClassConfig.getSeatClass();
            boolean isAfterPassengerSeatClass = false;

            for (SeatClass seatClass : seatClassList) {
                if (isAfterPassengerSeatClass) {
                    copySeatConfig.setSeats(seatClass, seatConfig.seats(seatClass));
                    isAfterPassengerSeatClass = false;
                }
                if (seatClass == passengerSeatClass) {
                    copySeatConfig.setSeats(seatClass, seatConfig.seats(seatClass));
                    isAfterPassengerSeatClass = true;
                }
            }
            return copySeatConfig;
        });

        return policy;
    }

    // returns the passenger's current class AND any seats if there are any in the highest class possible
    public static final Flight upgradeToHighest(Flight flight) {
        FlightPolicy policy = FlightPolicy.of(flight, (seatConfig, fareClassConfig) -> {
            SeatConfiguration copySeatConfig = SeatConfiguration.of(seatConfig);
            boolean highestClassPossible = true;

            // iterate from highest SeatClass to lowest
            for (SeatClass seatClass : SeatClass.values()) {
                if (highestClassPossible || seatClass == fareClassConfig.getSeatClass()) {
                    copySeatConfig.setSeats(seatClass, seatConfig.seats(seatClass));
                    highestClassPossible = false;
                }
            }
            return copySeatConfig;
        });

        return policy;
    }

    // spin the wheel -> gives passenger a % chance to upgrade to highest class if there are any empty seats
    // otherwise they stay in their original SeatClass
    public static final Flight spinTheWheel(Flight flight, int upgradeProbability) {
        if (upgradeProbability <= 0) {
            return strict(flight);
        }
        else {
            int probability = upgradeProbability % 100;

            Random r = new Random();
            int randomNumber = r.ints(1, (100 + 1)).findFirst().getAsInt();
            if (randomNumber <= probability) {
                return upgradeToHighest(flight);
            }
        }

        return strict(flight);
    }





    @Override
    public FlightSchedule getFlightSchedule() {
        return flight.getFlightSchedule();
    }
}
