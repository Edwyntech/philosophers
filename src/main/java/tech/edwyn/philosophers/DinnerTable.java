package tech.edwyn.philosophers;

import java.util.ArrayList;
import java.util.List;

public class DinnerTable {
    private final List<Chopstick> chopsticks = new ArrayList<>();

    public List<Chopstick> chopsticks() {
        return this.chopsticks;
    }

    public void addChopstick() {
        this.chopsticks.add(new Chopstick());
    }

    public void seat(Philosopher philosopher) {
        philosopher.seatAt(this);
    }
}
