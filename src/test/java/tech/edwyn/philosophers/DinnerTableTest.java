package tech.edwyn.philosophers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class DinnerTableTest {

    @Test
    void shouldHaveChopsticks() {
        DinnerTable dinnerTable = new DinnerTable();
        dinnerTable.addChopstick();
        assertThat(dinnerTable.chopsticks()).hasSize(1);
    }

}