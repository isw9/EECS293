package airtravel;

import java.util.Objects;

public final class Leg {

    private final Airport origin;

    private final Airport destination;

    private Leg(Airport origin, Airport destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public static final Leg of(Airport origin, Airport destination) {
        Objects.requireNonNull(origin, "origin can't be null in the Leg build method");
        Objects.requireNonNull(destination, "destination can't be null in the Leg build method");

        return new Leg(origin, destination);
    }

    public Airport getOrigin() {
        return this.origin;
    }

    public Airport getDestination() {
        return this.destination;
    }
}
