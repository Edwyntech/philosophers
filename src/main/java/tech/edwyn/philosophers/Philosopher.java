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
        this.hasEaten = true;
        this.hasThought = true;
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
        this.leftChopstick.take();
        this.rightChopstick.take();
        System.out.printf("[%s]: I am eating.", this.name);
        Thread.sleep(50);
    }
}
