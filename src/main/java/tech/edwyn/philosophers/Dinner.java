package tech.edwyn.philosophers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Dinner {
    private final List<Philosopher> philosophers = new ArrayList<>();
    private final DinnerTable dinnerTable = new DinnerTable();

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
    }
}
