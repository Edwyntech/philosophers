package tech.edwyn.philosophers;

public class Philosopher {
    private final String name;
    private boolean hasEaten;
    private boolean hasThought;

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
}
