package hotciv.strategy;

import java.util.Random;

public class RandomDieRollingStrategy implements DieRollingStrategy {
    private Random random;

    public RandomDieRollingStrategy() {
        this.random = new Random();
    }

    @Override
    public int roll() {
        return random.nextInt(6) + 1;
    }
}
