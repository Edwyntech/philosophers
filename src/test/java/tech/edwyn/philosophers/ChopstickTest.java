package tech.edwyn.philosophers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChopstickTest {


    private Chopstick chopstick;

    @BeforeEach
    public void initTest() {
        chopstick = new Chopstick();
    }

    @Test
    void shouldBeAvailableAtInitialization() {
        assertThat(chopstick.isAvailable()).isTrue();
    }

    @Test
    void shouldNotBeAvailableWhenTaken() {
        chopstick.take();
        assertThat(chopstick.isAvailable()).isFalse();
    }

    @Test
    void shouldBeAvailableAgainWhenPutDown() {
        chopstick.take();
        chopstick.putDown();
        assertThat(chopstick.isAvailable()).isTrue();
    }

    @Test
    void shouldErrorOutIfTakenWhenNotAvailable() {
        chopstick.take();
        assertThatThrownBy(() -> chopstick.take()).isInstanceOf(IllegalStateException.class).hasMessage(
                "Chopstick is not available and cannot be taken.");
    }

    @Test
    void shouldErrorOutIfPutDownWhenAvailable() {
        assertThatThrownBy(() -> chopstick.putDown()).isInstanceOf(IllegalStateException.class).hasMessage(
                "Chopstick is not taken and cannot be put down.");
    }
}