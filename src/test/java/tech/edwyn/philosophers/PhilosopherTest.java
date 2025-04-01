package tech.edwyn.philosophers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PhilosopherTest {
    private Philosopher philosopher;

    @BeforeEach
    void initializeTest() {
        philosopher = new Philosopher("Platon");
    }

    @Test
    void shouldNotHaveDinnerWhenInitialized() {
        assertThat(philosopher.hasEaten()).isFalse();
        assertThat(philosopher.hasThought()).isFalse();
    }

    @Test
    void shouldHaveDinner() {
        philosopher.haveDinner();
        assertThat(philosopher.hasEaten()).isTrue();
        assertThat(philosopher.hasThought()).isTrue();
    }

    @Test
    void shouldSeatAtTable() {
        DinnerTable dinnerTable = new DinnerTable();
        philosopher.seatAt(dinnerTable);
        assertThat(philosopher.isSeatedAt()).isEqualTo(dinnerTable);
    }

    @Test
    void shouldHaveChopsticks() {
        philosopher.assignLeftChopstick(new Chopstick());
        philosopher.assignRightChopstick(new Chopstick());
        assertThat(philosopher.leftChopstick()).isNotNull();
        assertThat(philosopher.rightChopstick()).isNotNull();
    }
}