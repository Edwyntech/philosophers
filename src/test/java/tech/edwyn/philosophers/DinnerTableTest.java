package tech.edwyn.philosophers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class DinnerTableTest {

    private DinnerTable dinnerTable;
    private Philosopher platon;

    @BeforeEach
    public void initTest() {
        dinnerTable = new DinnerTable();
        platon = new Philosopher("Platon");
    }

    @Test
    void shouldHaveOneChopstick() {
        dinnerTable.addChopstick();
        assertThat(dinnerTable.chopsticks()).hasSize(1);
    }

    @Test
    void shouldWelcomeOnePhilosopher() {
        dinnerTable.seat(platon);
        assertThat(platon.isSeatedAt()).isEqualTo(dinnerTable);
    }
}