package tech.edwyn.philosophers;

public class Philosopher {
    private final String name;
    private boolean hasEaten;
    private boolean hasThought;
    private DinnerTable dinnerTable;

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

    public DinnerTable isSeatedAt() {
        return this.dinnerTable;
    }

    public void seatAt(DinnerTable dinnerTable) {
        this.dinnerTable = dinnerTable;
    }
}
