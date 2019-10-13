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
        Objects.requireNonNull(name, "name can't be null in Location");
        Objects.requireNonNull(priority, "priority can't be null in Location");
        Objects.requireNonNull(startTime, "startTime can't be null in Location");
        Objects.requireNonNull(endTime, "endTime can't be null in Location");

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
