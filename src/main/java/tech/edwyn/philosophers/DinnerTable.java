package tech.edwyn.philosophers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
            while (needMoreChopsticks()) {
                addChopstick();
            }
        }
        distributeChopsticks();
    }

    private boolean needMoreChopsticks() {
        return this.chopsticks.size() < 2 || this.chopsticks.size() < this.guests.size();
    }

    private void distributeChopsticks() {
        IntStream.range(0, this.guests.size()).forEach(this::distributeLeftAndRightChopsticks);
    }

    private void distributeLeftAndRightChopsticks(int index) {
        int rightChopstickIndex = index + 1 > this.chopsticks.size() - 1 ? 0 : index + 1;
        guests.get(index).assignLeftChopstick(this.chopsticks.get(index));
        guests.get(index).assignRightChopstick(this.chopsticks.get(rightChopstickIndex));
    }

    public List<Philosopher> guests() {
        return this.guests;
    }
}
