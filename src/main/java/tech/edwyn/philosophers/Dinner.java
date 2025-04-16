package tech.edwyn.philosophers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Dinner {
    private final List<Philosopher> philosophers = new ArrayList<>();
    private final DinnerTable dinnerTable = new DinnerTable();
    private final Duration duration;
    private LocalDateTime dinnerEndDateTime;

    public Dinner(Duration duration) {
        this.duration = duration;
    }

    public List<Philosopher> philosophers() {
        return philosophers;
    }

    public void add(String... philosopherNames) {
        Stream.of(philosopherNames).map(Philosopher::new).forEach(this.philosophers::add);
    }

    public DinnerTable table() {
        return dinnerTable;
    }

    public void start() {
        this.dinnerTable.seat(this.philosophers.toArray(new Philosopher[0]));
        dinnerEndDateTime = LocalDateTime.now().plus(duration);
        this.philosophers.forEach(philosopher -> philosopher.haveDinner(dinnerEndDateTime));
    }

    public boolean isOver() {
        LocalDateTime now = LocalDateTime.now();
        return dinnerEndDateTime != null && now.isAfter(dinnerEndDateTime);
    }

    public Duration duration() {
        return this.duration;
    }
}
