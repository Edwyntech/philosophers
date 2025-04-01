package tech.edwyn.philosophers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChopstickTest {

    @Test
    void shouldBeAvailableAtInitialization() {
        Chopstick chopstick = new Chopstick();
        assertThat(chopstick.isAvailable()).isTrue();
    }

    @Test
    void shouldNotBeAvailableWhenTaken() {
        Chopstick chopstick = new Chopstick();
        chopstick.take();
        assertThat(chopstick.isAvailable()).isFalse();
    }

    @Test
    void shouldBeAvailableAgainWhenPutDown() {
        Chopstick chopstick = new Chopstick();
        chopstick.take();
        chopstick.putDown();
        assertThat(chopstick.isAvailable()).isTrue();
    }

}