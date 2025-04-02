package tech.edwyn.philosophers;

public class Philosopher {
    private final String name;
    private boolean hasEaten;
    private boolean hasThought;
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;

    public Philosopher(String name) {
        this.name = name;
    }

    public void haveDinner() {
        if (this.leftChopstick == null || this.rightChopstick == null) {
            throw new IllegalStateException("Cannot have dinner without chopsticks.");
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
        this.rightChopstick.putDown();
        this.leftChopstick.putDown();
    }

    public boolean hasEaten() {
        return this.hasEaten;
    }

    public boolean hasThought() {
        return this.hasThought;
    }

    public Chopstick leftChopstick() {
        return this.leftChopstick;
    }

    public void assignLeftChopstick(Chopstick chopstick) {
        this.leftChopstick = chopstick;
    }

    public void assignRightChopstick(Chopstick chopstick) {
        this.rightChopstick = chopstick;
    }

    public Chopstick rightChopstick() {
        return this.rightChopstick;
    }

    public void eat() throws InterruptedException {
        if (leftChopstick.isAvailable() && rightChopstick.isAvailable()) {
            this.leftChopstick.take();
            this.rightChopstick.take();
            System.out.printf("[%s]: I am eating.", this.name);
            Thread.sleep(50);
            this.hasEaten = true;
        }
    }
}
