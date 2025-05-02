package tech.edwyn.philosophers;

import java.time.Duration;
import java.time.LocalDateTime;

public class Philosopher {
    private final String name;
    private final Duration eatDuration;
    private boolean hasThought;
    private DinnerTable dinnerTable;
    private Duration totalDurationEating = Duration.ZERO;

    public Philosopher(String name) {
        this.name = name;
        this.eatDuration = Duration.ZERO;
    }

    public Philosopher(String name, Duration eatDuration) {
        this.name = name;
        this.eatDuration = eatDuration;
    }

    public void haveDinner(LocalDateTime dinnerEndDateTime) {
        if (this.dinnerTable == null) {
            throw new IllegalStateException("Cannot have dinner without table.");
        }
        if (dinnerEndDateTime.isAfter(LocalDateTime.now())) {
            try {
                this.eat();
                think();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.putChopsticksDown();
        }
    }

    public void think() throws InterruptedException {
        this.hasThought = true;
        System.out.printf("\n[%s]: I am thinking.%n", this.name);
    }

    private void putChopsticksDown() {
        this.dinnerTable.putDownLeftChopstick(this);
        this.dinnerTable.putDownRightChopstick(this);
    }

    public boolean hasEaten() {
        return totalDurationEating.isPositive();
    }

    public boolean hasThought() {
        return this.hasThought;
    }

    public void eat() throws InterruptedException {
        if (chopsticksAreAvailable()) {
            this.dinnerTable.takeLeftChopstick(this);
            this.dinnerTable.takeRightChopstick(this);
            System.out.printf("\n[%s]: I am eating.", this.name);
            Thread.sleep(eatDuration);
            totalDurationEating = totalDurationEating.plus(eatDuration);
        }
    }

    private boolean chopsticksAreAvailable() {
        return this.dinnerTable.leftChopstick(this).isAvailable() && this.dinnerTable.rightChopstick(
                this).isAvailable();
    }

    public DinnerTable dinnerTable() {
        return dinnerTable;
    }

    public void seatAt(DinnerTable dinnerTable) {
        this.dinnerTable = dinnerTable;
    }

    public Duration totalDurationEating() {
        return totalDurationEating;
    }
}
