package tech.edwyn.philosophers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DinnerTest {

    private Dinner dinner;

    @BeforeEach
    public void extracted() {
        dinner = new Dinner();
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


}
