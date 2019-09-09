package airtravel;

public final class Leg {

    private final Airport origin;

    private final Airport destination;

    private Leg(Airport origin, Airport destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public static final Leg of(Airport origin, Airport destination) {
        if (origin == null || destination == null) {
            throw new NullPointerException("Input(s) to Leg build method cannot be null");
        }

        return new Leg(origin, destination);
    }

    public Airport getOrigin() {
        return this.origin;
    }

    public Airport getDestination() {
        return this.destination;
    }
}
