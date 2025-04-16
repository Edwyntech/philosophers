package tech.edwyn.philosophers;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.*;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Awaitility.with;

class DinnerTest {

    private Dinner dinner;

    @BeforeEach
    public void setUp() {
        dinner = new Dinner(Duration.ofMillis(200));
    }

    @Test
    void shouldHaveOnePhilosopher() {
        dinner.add("Platon");
        assertThat(dinner.philosophers()).hasSize(1);
    }

    @Test
    void shouldHaveManyPhilosophers() {
        dinner.add("Platon", "Aristote", "Hegel");
        assertThat(dinner.philosophers()).hasSize(3);
    }

    @Test
    void shouldHaveATable() {
        assertThat(dinner.table()).isNotNull();
    }

    @Test
    void shouldHaveEmptyTableBeforeStart() {
        assertThat(dinner.table().guests()).isEmpty();
    }

    @Test
    void shouldSeatGuestsWhenStarts() {
        dinner.add("Platon", "Aristote", "Hegel");

        dinner.start();
        assertThat(dinner.philosophers()).allMatch(philosopher -> philosopher.dinnerTable() == dinner.table());
    }

    @Test
    void shouldHaveGuestsAndChopsticksWhenStarts() {
        dinner.add("Platon", "Aristote", "Hegel");
        dinner.start();
        assertThat(dinner.table().guests()).hasSize(3);
        assertThat(dinner.table().chopsticks()).hasSize(3);
    }

    @Test
    void philosophersShouldEatDuringDinner() {
        dinner.add("Platon", "Aristote", "Hegel");
        dinner.start();
        assertThat(dinner.philosophers()).allMatch(Philosopher::hasEaten);
    }

    @Test
    void philosophersShouldThinkDuringDinner() {
        dinner.add("Platon", "Aristote", "Hegel");
        dinner.start();
        assertThat(dinner.philosophers()).allMatch(Philosopher::hasThought);
    }

    @Test
    void shouldEnd() throws InterruptedException {
        dinner.start();
        await()
                .atMost(Duration.ofMillis(300))
                .untilAsserted(() -> assertThat(dinner.isOver()).isTrue());
    }

    @Test
    void shouldHaveADuration() {
        assertThat(dinner.duration()).isNotNull();
    }

    @Test
    void shouldNotBeOverIfDurationIsNotElapsed() {
        Dinner dinner = new Dinner(Duration.ofMillis(600));
        dinner.start();
        await()
                .during(Duration.ofMillis(400))
                .pollInterval(Duration.ofMillis(50))
                .atMost(Duration.ofMillis(600))
                .untilAsserted(() -> assertThat(dinner.isOver()).isFalse());
    }
}
