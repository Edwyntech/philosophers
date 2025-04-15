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
                "Cannot have dinner without chopsticks.");
    }

    @Test
    void shouldHaveDinner() throws InterruptedException {
        philosopher.assignLeftChopstick(new Chopstick());
        philosopher.assignRightChopstick(new Chopstick());

        // Act
        philosopher.haveDinner();

        // Assert
        assertThat(philosopher.hasEaten()).isTrue();
        assertThat(philosopher.hasThought()).isTrue();
    }

    @Test
    void shouldHaveChopsticks() {
        philosopher.assignLeftChopstick(new Chopstick());
        philosopher.assignRightChopstick(new Chopstick());
        assertThat(philosopher.leftChopstick()).isNotNull();
        assertThat(philosopher.rightChopstick()).isNotNull();
    }

    @Test
    void shouldTakeChopsticksWhenEating() throws InterruptedException {
        philosopher.assignLeftChopstick(new Chopstick());
        philosopher.assignRightChopstick(new Chopstick());

        // Act
        philosopher.eat();

        // Assert
        assertThat(philosopher.leftChopstick().isAvailable()).isFalse();
        assertThat(philosopher.rightChopstick().isAvailable()).isFalse();
    }

    @Test
    void shouldNotEatIfLeftChopstickIsNotAvailable() throws InterruptedException {
        Chopstick chopstick = new Chopstick();
        chopstick.take();
        philosopher.assignLeftChopstick(chopstick);

        // Act
        philosopher.eat();

        // Assert
        assertThat(philosopher.hasEaten()).isFalse();
    }

    @Test
    void shouldNotEatIfRightChopstickIsNotAvailable() throws InterruptedException {
        Chopstick leftChopstick = new Chopstick();
        Chopstick rightChopstick = new Chopstick();
        rightChopstick.take();
        philosopher.assignLeftChopstick(leftChopstick);
        philosopher.assignRightChopstick(rightChopstick);

        // Act
        philosopher.eat();

        // Assert
        assertThat(philosopher.hasEaten()).isFalse();
    }

    @Test
    void shouldKnowTheTable() {
        DinnerTable dinnerTable = new DinnerTable();

        // Act
        Philosopher platon = new Philosopher("Platon", dinnerTable);

        // Assert
        assertThat(platon.dinnerTable()).isNotNull();
    }
}