package tech.edwyn.philosophers;

public class Chopstick {
    private boolean isAvailable = true;

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void take() {
        this.isAvailable = false;
    }

    public void putDown() {
        this.isAvailable = true;
    }
}
