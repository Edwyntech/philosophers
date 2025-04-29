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
        this.guests.forEach(philosopher -> philosopher.seatAt(this));
    }

    private boolean needMoreChopsticks() {
        return this.chopsticks.size() < 2 || this.chopsticks.size() < this.guests.size();
    }

    public List<Philosopher> guests() {
        return this.guests;
    }

    public Chopstick leftChopstick(Philosopher philosopher) {
        int index = this.guests.indexOf(philosopher);
        if (index < 0) {
            throw new IllegalStateException("Guest is not seated and cannot get left chopstick.");
        }
        return this.chopsticks.get(index);
    }

    public Chopstick rightChopstick(Philosopher philosopher) {
        int guestIndex = this.guests.indexOf(philosopher);
        if (guestIndex < 0) {
            throw new IllegalStateException("Guest is not seated and cannot get right chopstick.");
        }
        int chopstickIndex = guestIndex + 1;
        return chopstickIndex == this.chopsticks.size() ? this.chopsticks.getFirst() : this.chopsticks.get(
                chopstickIndex);
    }

    public void takeLeftChopstick(Philosopher philosopher) {
        this.leftChopstick(philosopher).take();
    }

    public void takeRightChopstick(Philosopher philosopher) {
        this.rightChopstick(philosopher).take();
    }

    public void putDownRightChopstick(Philosopher philosopher) {
        this.rightChopstick(philosopher).putDown();
    }

    public void putDownLeftChopstick(Philosopher philosopher) {
        this.leftChopstick(philosopher).putDown();
    }
}
