package tech.edwyn.philosophers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PhilosopherTest {
    private Philosopher philosopher;
    DinnerTable dinnerTable;

    @BeforeEach
    void initializeTest() {
        philosopher = new Philosopher("Platon", Duration.ofMillis(50));
        dinnerTable = new DinnerTable();
    }

    @Test
    void shouldNotHaveDinnerWhenInitialized() {
        assertThat(philosopher.hasEaten()).isFalse();
        assertThat(philosopher.hasThought()).isFalse();
    }

    @Test
    void shouldNotHaveDinnerWithoutChopsticks() {
        assertThatThrownBy(() -> philosopher.haveDinner(LocalDateTime.now())).isInstanceOf(IllegalStateException.class)
                .hasMessage(
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
    void shouldHaveDinner() {
        dinnerTable.seat(philosopher);

        // Act
        philosopher.haveDinner(LocalDateTime.now().plus(Duration.ofMillis(100)));

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
    void shouldPutChopstickBackDownAfterDinner() {
        dinnerTable.seat(philosopher);

        philosopher.haveDinner(LocalDateTime.now());

        assertThat(dinnerTable.chopsticks()).allMatch(Chopstick::isAvailable);
    }

    @Test
    void shouldNotEatIfDinnerIsOver() {
        dinnerTable.seat(philosopher);

        // Act
        philosopher.haveDinner(LocalDateTime.now());

        // Assert
        assertThat(philosopher.hasEaten()).isFalse();
    }

    @Test
    void shouldHaveZeroEatenDurationIfNotEaten() {
        assertThat(philosopher.totalDurationEating()).isEqualTo(Duration.ZERO);
    }

    @Test
    void shouldHaveNonZeroEatenAmountIfHasEaten() {
        dinnerTable.seat(philosopher);

        // Act
        philosopher.haveDinner(LocalDateTime.now().plus(Duration.ofMillis(50)));

        // Assert
        assertThat(philosopher.totalDurationEating()).isGreaterThan(Duration.ZERO);
    }

    @Test
    void timeHavingDinnerShouldActuallyPass() {
        dinnerTable.seat(philosopher);
        LocalDateTime dinnerStartDatetime = LocalDateTime.now();

        philosopher.haveDinner(LocalDateTime.now().plus(Duration.ofMillis(1)));

        Duration timeHavingDinner = Duration.between(dinnerStartDatetime, LocalDateTime.now());
        assertThat(philosopher.totalDurationEating()).isLessThanOrEqualTo(timeHavingDinner);
    }
}
