package tech.edwyn.philosophers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DinnerTest {

    @Test
    void shouldHaveOnePhilosopher() {
        Dinner dinner = new Dinner();
        dinner.add("Platon");
        assertThat(dinner.philosophers()).hasSize(1);
    }

    @Test
    void shouldHaveManyPhilosophers() {
        Dinner dinner = new Dinner();
        dinner.add("Platon", "Aristote", "Hegel");
        assertThat(dinner.philosophers()).hasSize(3);
    }
}
