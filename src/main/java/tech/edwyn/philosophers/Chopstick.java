package tech.edwyn.philosophers;

public class Chopstick {
    private boolean isAvailable = true;

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void take() {
        if (!this.isAvailable) {
            throw new IllegalStateException("Chopstick is not available and cannot be taken.");
        }
        this.isAvailable = false;
    }

    public void putDown() {
        if (this.isAvailable) {
            throw new IllegalStateException("Chopstick is not taken and cannot be put down.");
        }
        this.isAvailable = true;
    }
}
