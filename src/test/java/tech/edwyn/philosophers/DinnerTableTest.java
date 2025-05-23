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
}