

import java.util.Objects;

public class Location {

    private int priority;

    private String name;

    private int startTime;

    private int endTime;

    private Location(String name, int priority, int startTime, int endTime) {
        this.name = name;
        this.priority = priority;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Location of(String name, int priority, int startTime, int endTime) {
        Objects.requireNonNull(name, "name can't be null in pictures.Location");
        if (priority < 0) {
            throw new IllegalArgumentException("Priority can't be less than 0");
        }

        if (startTime <= 0) {
            throw new IllegalArgumentException("startTime must be greater than 0");
        }

        if (endTime <= 0) {
            throw new IllegalArgumentException("endTime must be greater than 0");
        }

        if (startTime >= endTime) {
            throw new IllegalArgumentException("startTime must come before endTime");
        }

        return new Location(name, priority, startTime, endTime);
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getEndTime() {
        return this.endTime;
    }

    public int getPriority() {
        return this.priority;
    }

    public String getName() {
        return this.name;
    }
}
