package tech.edwyn.philosophers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DinnerTable {
    private final List<Chopstick> chopsticks = new ArrayList<>();
    private final List<Philosopher> guests = new ArrayList<>();

    public List<Chopstick> chopsticks() {
        return this.chopsticks;
    }

    public void addChopstick() {
        this.chopsticks.add(new Chopstick());
    }

    public void seat(Philosopher... philosophers) {
        this.guests.addAll(Arrays.asList(philosophers));
        if (!this.guests.isEmpty()) {
            while (this.chopsticks.size() < 2 || this.chopsticks.size() < this.guests.size()) {
                addChopstick();
            }
        }
    }

    public List<Philosopher> guests() {
        return this.guests;
    }
}
