package airtravel;

import java.time.Duration;
import java.util.Objects;

public final class FareClass {
    private final int identifier;

    private final SeatClass seatClass;

    private FareClass(SeatClass seatClass, int identifier) {
        this.seatClass = seatClass;
        this.identifier = identifier;
    }

    public static final FareClass of(SeatClass seatClass, int identifier) {
        Objects.requireNonNull(seatClass, "seatClass cannot be null in FareClass build");

        return new FareClass(seatClass, identifier);
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public SeatClass getSeatClass() {
        return this.seatClass;
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
        int result = 1;
        result = 13 * result + getIdentifier();
        return result;
    }
}
