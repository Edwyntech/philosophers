package tech.edwyn.philosophers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;


class DinnerTableTest {

    private DinnerTable dinnerTable;
    private Philosopher platon;
    private Philosopher aristote;
    private Philosopher hegel;

    @BeforeEach
    public void initTest() {
        dinnerTable = new DinnerTable();
        platon = new Philosopher("Platon");
        aristote = new Philosopher("Aristote");
        hegel = new Philosopher("Hegel");
    }

    @Test
    void shouldHaveOneChopstick() {
        dinnerTable.addChopstick();
        assertThat(dinnerTable.chopsticks()).hasSize(1);
    }

    @Test
    void shouldWelcomeOnePhilosopher() {
        dinnerTable.seat(platon);
        assertThat(dinnerTable.chopsticks()).hasSize(2);
        assertThat(dinnerTable.guests()).hasSize(1);
    }

    @Test
    void shouldWelcomeTwoPhilosophers() {
        dinnerTable.seat(platon, aristote);
        assertThat(dinnerTable.chopsticks()).hasSize(2);
        assertThat(dinnerTable.guests()).hasSize(2);
    }

    @Test
    void shouldWelcomeManyPhilosophers() {
        dinnerTable.seat(platon, aristote, hegel);
        assertThat(dinnerTable.chopsticks()).hasSize(3);
        assertThat(dinnerTable.guests()).hasSize(3);
    }

    @Test
    void shouldProvideLeftChopstick() {
        dinnerTable.seat(platon);

        assertThat(dinnerTable.leftChopstick(platon)).isEqualTo(dinnerTable.chopsticks().getFirst());
    }

    @Test
    void shouldFireExceptionWhenNonSeatedGuestsAskForLeftChopstick() {
        assertThatException().isThrownBy(() -> dinnerTable.leftChopstick(platon)).isInstanceOf(
                IllegalStateException.class).withMessage("Guest is not seated and cannot get left chopstick.");
    }

    @Test
    void shouldProvideRightChopstick() {
        dinnerTable.seat(platon);

        assertThat(dinnerTable.rightChopstick(platon)).isEqualTo(dinnerTable.chopsticks().getLast());
    }

    @Test
    void shouldProvideRightChopstickToLastGuest() {
        dinnerTable.seat(platon, aristote, hegel);

        assertThat(dinnerTable.rightChopstick(hegel)).isEqualTo(dinnerTable.chopsticks().getFirst());
    }

    @Test
    void shouldFireExceptionWhenNonSeatedGuestsAskForRightChopstick() {
        assertThatException().isThrownBy(() -> dinnerTable.rightChopstick(platon)).isInstanceOf(
                IllegalStateException.class).withMessage("Guest is not seated and cannot get right chopstick.");
    }

    @Test
    void shouldDistributeChopsticksWhenSeatingOnePhilosopher() {
        // Act
        dinnerTable.seat(platon);

        // Assert
        assertThat(platon.leftChopstick()).isEqualTo(dinnerTable.chopsticks().getFirst());
        assertThat(platon.rightChopstick()).isEqualTo(dinnerTable.chopsticks().getLast());
    }

    @Test
    void shouldDistributeChopsticksWhenSeatingTwoPhilosopher() {
        // Act
        dinnerTable.seat(platon, aristote);

        // Assert
        assertThat(platon.leftChopstick()).isEqualTo(dinnerTable.chopsticks().getFirst());
        assertThat(platon.rightChopstick()).isEqualTo(dinnerTable.chopsticks().getLast());
        assertThat(aristote.leftChopstick()).isEqualTo(dinnerTable.chopsticks().getLast());
        assertThat(aristote.rightChopstick()).isEqualTo(dinnerTable.chopsticks().getFirst());
    }

    @Test
    void shouldDistributeChopsticksWhenSeatingManyPhilosopher() {
        // Act
        dinnerTable.seat(platon, aristote, hegel);

        // Assert
        assertThat(platon.leftChopstick()).isEqualTo(dinnerTable.chopsticks().getFirst());
        assertThat(platon.rightChopstick()).isEqualTo(dinnerTable.chopsticks().get(1));
        assertThat(aristote.leftChopstick()).isEqualTo(dinnerTable.chopsticks().get(1));
        assertThat(aristote.rightChopstick()).isEqualTo(dinnerTable.chopsticks().getLast());
        assertThat(hegel.leftChopstick()).isEqualTo(dinnerTable.chopsticks().getLast());
        assertThat(hegel.rightChopstick()).isEqualTo(dinnerTable.chopsticks().getFirst());
    }
}