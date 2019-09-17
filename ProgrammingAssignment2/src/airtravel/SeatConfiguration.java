package airtravel;

import javax.print.attribute.standard.NumberUp;
import java.util.EnumMap;
import java.util.Set;
import java.util.TreeSet;

public class SeatConfiguration {

    private final EnumMap<SeatClass, Integer> seats;
    Set<Integer> set = new TreeSet<Integer>();

    private SeatConfiguration(EnumMap<SeatClass, Integer> seats) {
        this.seats = seats;
    }

    public static final SeatConfiguration of(EnumMap<SeatClass, Integer> seats) {
        if (seats == null) {
            throw new NullPointerException("Seats cannot be null");
        }

        return new SeatConfiguration(seats);
    }

    //2nd build method???
//    public static final SeatConfiguration of(EnumMap<SeatClass, Integer> seats) {
//        if (seats == null) {
//            throw new NullPointerException("Seats cannot be null");
//        }
//
//        return new SeatConfiguration(seats);
//    }

    public final int seats(SeatClass seatClass) {
        if (this.seats.containsKey(seatClass) && this.seats.get(seatClass) > 0) {
            return this.seats.get(seatClass);
        }

        return 0;
    }

    public final int setSeats(SeatClass seatClass, int seats) {
        int previouslyAvailableSeats = seats(seatClass);

        this.seats.put(seatClass, seats);

        return previouslyAvailableSeats;
    }

    public final boolean hasSeats(SeatClass seatClass) {
        return seats(seatClass) > 0;
    }

}
