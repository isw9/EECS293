package airtravel;

import java.time.Duration;

public class FareClass {
    private final int identifier;

    private final SeatClass seatClass;

    private FareClass(SeatClass seatClass, int identifier) {
        this.seatClass = seatClass;
        this.identifier = identifier;
    }

    public static final FareClass of(SeatClass seatClass, int identifier) {
        if (seatClass == null) {
            throw new NullPointerException("Input to FareClass build method cannot be null");
        }


        return new FareClass(seatClass, identifier);
    }

    public int getIdentifier() {
        return this.identifier;
    }

    @Override
    public boolean equals(Object otherFareClass) {
        if (otherFareClass == null) {
            return false;
        }

        if (this.getClass() != otherFareClass.getClass()) {
            return false;
        }

        return ((FareClass) otherFareClass).getIdentifier() == this.getIdentifier();
    }

    @Override
    public int hashCode() {
        return getIdentifier() / 11;
    }
}
