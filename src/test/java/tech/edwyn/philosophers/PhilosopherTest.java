package tech.edwyn.philosophers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void shouldNotHaveDinnerWithoutChopsticks() {
        assertThatThrownBy(() -> philosopher.haveDinner()).isInstanceOf(IllegalStateException.class).hasMessage(
                "Cannot have dinner without table.");
    }

    @Test
    void shouldSeatAtTable() {
        DinnerTable dinnerTable = new DinnerTable();

        // Act
        philosopher.seatAt(dinnerTable);

        // Assert
        assertThat(philosopher.dinnerTable()).isNotNull();
    }

    @Test
    void shouldHaveChopsticks() {
        philosopher.assignLeftChopstick(new Chopstick());
        philosopher.assignRightChopstick(new Chopstick());
        assertThat(philosopher.leftChopstick()).isNotNull();
        assertThat(philosopher.rightChopstick()).isNotNull();
    }

    @Test
    void shouldHaveDinner() {
        DinnerTable dinnerTable = new DinnerTable();
        dinnerTable.seat(philosopher);

        // Act
        philosopher.haveDinner();

        // Assert
        assertThat(philosopher.hasEaten()).isTrue();
        assertThat(philosopher.hasThought()).isTrue();
    }

    @Test
    void shouldTakeChopsticksWhenEating() throws InterruptedException {
        DinnerTable dinnerTable = new DinnerTable();
        dinnerTable.seat(philosopher);

        // Act
        philosopher.eat();

        // Assert
        assertThat(dinnerTable.leftChopstick(philosopher).isAvailable()).isFalse();
        assertThat(dinnerTable.rightChopstick(philosopher).isAvailable()).isFalse();
    }

    @Test
    void shouldNotEatIfLeftChopstickIsNotAvailable() throws InterruptedException {
        DinnerTable dinnerTable = new DinnerTable();
        dinnerTable.seat(philosopher);
        Chopstick leftChopstick = dinnerTable.leftChopstick(philosopher);
        leftChopstick.take();

        // Act
        philosopher.eat();

        // Assert
        assertThat(philosopher.hasEaten()).isFalse();
    }

    @Test
    void shouldNotEatIfRightChopstickIsNotAvailable() throws InterruptedException {
        DinnerTable dinnerTable = new DinnerTable();
        dinnerTable.seat(philosopher);
        Chopstick rightChopstick = dinnerTable.rightChopstick(philosopher);
        rightChopstick.take();

        // Act
        philosopher.eat();

        // Assert
        assertThat(philosopher.hasEaten()).isFalse();
    }
}