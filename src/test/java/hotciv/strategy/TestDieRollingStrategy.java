package hotciv.strategy;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestDieRollingStrategy {

    @Test
    public void fixedDieShouldAlwaysReturnSameValue() {
        DieRollingStrategy fixedDie = new FixedDieRollingStrategy(3);
        assertThat(fixedDie.roll(), is(3));
        assertThat(fixedDie.roll(), is(3));
        assertThat(fixedDie.roll(), is(3));
    }

    @Test
    public void fixedDieShouldReturnChosenValue() {
        DieRollingStrategy die1 = new FixedDieRollingStrategy(1);
        DieRollingStrategy die6 = new FixedDieRollingStrategy(6);

        assertThat(die1.roll(), is(1));
        assertThat(die6.roll(), is(6));
    }

    @Test
    public void randomDieShouldReturnValueBetween1And6() {
        DieRollingStrategy randomDie = new RandomDieRollingStrategy();

        for (int i = 0; i < 100; i++) {
            int roll = randomDie.roll();
            assertTrue(roll >= 1);
            assertTrue(roll <= 6);
        }
    }
}
