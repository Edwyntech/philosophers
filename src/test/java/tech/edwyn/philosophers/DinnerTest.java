package tech.edwyn.philosophers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DinnerTest {

     @Test
     void dinnerShouldInitialize() {
         Dinner dinner = new Dinner();
         assertThat(dinner).isNotNull();
     }

}
