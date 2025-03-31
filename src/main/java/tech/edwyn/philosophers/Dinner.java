package tech.edwyn.philosophers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Dinner {
    private final List<Philosopher> philosophers = new ArrayList<>();


    public List<Philosopher> philosophers() {
        return philosophers;
    }


    public void add(String... philosopherNames) {
        Stream.of(philosopherNames).map(Philosopher::new).forEach(this.philosophers::add);
    }
}
