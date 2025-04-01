package tech.edwyn.philosophers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    void shouldNotHaveDinnerWithoutChopsticks() {
        assertThatThrownBy(() -> philosopher.haveDinner()).isInstanceOf(IllegalStateException.class).hasMessage(
                "Cannot eat without chopsticks.");
    }

    @Test
    void shouldHaveDinner() throws InterruptedException {
        philosopher.assignLeftChopstick(new Chopstick());
        philosopher.assignRightChopstick(new Chopstick());
        philosopher.haveDinner();
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
        philosopher.eat();
        assertThat(philosopher.leftChopstick().isAvailable()).isFalse();
        assertThat(philosopher.rightChopstick().isAvailable()).isFalse();
    }
}