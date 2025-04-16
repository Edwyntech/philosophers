package tech.edwyn.philosophers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PhilosopherTest {
    private Philosopher philosopher;
    DinnerTable dinnerTable;

    @BeforeEach
    void initializeTest() {
        philosopher = new Philosopher("Platon");
        dinnerTable = new DinnerTable();
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
        dinnerTable.seat(philosopher);

        // Act
        philosopher.haveDinner();

        // Assert
        assertThat(philosopher.hasEaten()).isTrue();
        assertThat(philosopher.hasThought()).isTrue();
    }

    @Test
    void shouldTakeChopsticksWhenEating() throws InterruptedException {
        dinnerTable.seat(philosopher);

        // Act
        philosopher.eat();

        // Assert
        assertThat(dinnerTable.leftChopstick(philosopher).isAvailable()).isFalse();
        assertThat(dinnerTable.rightChopstick(philosopher).isAvailable()).isFalse();
    }

    @Test
    void shouldNotEatIfLeftChopstickIsNotAvailable() throws InterruptedException {
        dinnerTable.seat(philosopher);
        dinnerTable.takeLeftChopstick(philosopher);

        // Act
        philosopher.eat();

        // Assert
        assertThat(philosopher.hasEaten()).isFalse();
    }

    @Test
    void shouldNotEatIfRightChopstickIsNotAvailable() throws InterruptedException {
        dinnerTable.seat(philosopher);
        dinnerTable.takeRightChopstick(philosopher);

        // Act
        philosopher.eat();

        // Assert
        assertThat(philosopher.hasEaten()).isFalse();
    }

    @Test
    void shouldPutChopstickBackDownAfterDinner() throws InterruptedException {
        dinnerTable.seat(philosopher);

        philosopher.haveDinner();

        assertThat(dinnerTable.chopsticks()).allMatch(Chopstick::isAvailable);
    }
}