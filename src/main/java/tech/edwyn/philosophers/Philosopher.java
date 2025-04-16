package tech.edwyn.philosophers;

import java.time.LocalDateTime;

public class Philosopher {
    private final String name;
    private boolean hasEaten;
    private boolean hasThought;
    private DinnerTable dinnerTable;

    public Philosopher(String name) {
        this.name = name;
    }

    public void haveDinner(LocalDateTime dinnerEndDateTime) {
        if (this.dinnerTable == null) {
            throw new IllegalStateException("Cannot have dinner without table.");
        }
        try {
            this.eat();
            this.putChopsticksDown();
            think();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void think() throws InterruptedException {
        this.hasThought = true;
        System.out.printf("[%s]: I am thinking.", this.name);
        Thread.sleep(50);
    }

    private void putChopsticksDown() {
        this.dinnerTable.putDownLeftChopstick(this);
        this.dinnerTable.putDownRightChopstick(this);
    }

    public boolean hasEaten() {
        return this.hasEaten;
    }

    public boolean hasThought() {
        return this.hasThought;
    }

    public void eat() throws InterruptedException {
        if (chopsticksAreAvailable()) {
            this.dinnerTable.takeLeftChopstick(this);
            this.dinnerTable.takeRightChopstick(this);
            System.out.printf("[%s]: I am eating.", this.name);
            Thread.sleep(50);
            this.hasEaten = true;
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
}
