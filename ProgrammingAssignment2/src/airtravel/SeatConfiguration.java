package airtravel;

import java.util.EnumMap;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class SeatConfiguration {

    private final EnumMap<SeatClass, Integer> seats;
    Set<Integer> set = new TreeSet<Integer>();

    private SeatConfiguration(EnumMap<SeatClass, Integer> seats) {
        this.seats = seats;
    }

    public static final SeatConfiguration of(EnumMap<SeatClass, Integer> seats) {
        Objects.requireNonNull(seats, "Seats cannot be null in SeatConfiguration build");

        return new SeatConfiguration(seats);
    }

    public static final SeatConfiguration of(SeatConfiguration seatConfiguration) {
        Objects.requireNonNull(seatConfiguration, "seatConfiguration cannot be null in SeatConfiguration build");

        return new SeatConfiguration(seatConfiguration.seats);
    }

    public final int seats(SeatClass seatClass) {
        int seats = this.seats.getOrDefault(seatClass, 0);

        return seats < 0 ? 0 : seats;
    }

    public final int setSeats(SeatClass seatClass, int seats) {
        int previouslyAvailableSeats = seats(seatClass);

        this.seats.put(seatClass, seats);

        return previouslyAvailableSeats;
    }

    public final boolean hasSeats() {
        Set<SeatClass> seatClasses = seats.keySet();
        for (SeatClass seatClass : seatClasses) {
            if (seats(seatClass) > 0) {
                return true;
            }
        }
        return false;
    }

}
