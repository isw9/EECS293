package airtravel;

import java.time.Duration;

public final class Airport implements Comparable<Airport> {

    private final String code;

    private final Duration connectionTimeMin;

    private final FlightGroup outFlights = FlightGroup.of(this);

    private Airport(String code, Duration connectionTimeMin) {
        this.code = code;
        this.connectionTimeMin = connectionTimeMin;
    }

    public static final Airport of(String code, Duration connectionTimeMin) {
        if (code == null || connectionTimeMin == null) {
            throw new NullPointerException("Input(s) to Airport build method cannot be null");
        }


        return new Airport(code, connectionTimeMin);
    }

    public final boolean addFlight(Flight flight) {
        return outFlights.add(flight);
    }

    public boolean removeFlight(Flight flight) {
        return outFlights.remove(flight);
    }

    public String getCode() {
        return this.code;
    }

    public Duration getConnectionTimeMin() {
        return this.connectionTimeMin;
    }

    public FlightGroup getOutFlights() {
        return this.outFlights;
    }

    @Override
    public boolean equals(Object otherAirport) {
        if (otherAirport == null) {
            return false;
        }

        if (this.getClass() != otherAirport.getClass()) {
            return false;
        }

        Airport otherAirportAsAirport = (Airport) otherAirport;

        return otherAirportAsAirport.getCode().equals(this.getCode());
    }

    @Override
    public int hashCode() {
        int runningAsciiValue = 0;

        for (char c : this.getCode().toCharArray()) {
            runningAsciiValue += (int) c;
        }

        return runningAsciiValue / 11;
    }

    @Override
    public int compareTo(Airport otherAirport) {
        return getCode().equals(otherAirport.getCode()) ? 0 : 1;
    }

    @Override
    public String toString() {
        return getCode();
    }
}
