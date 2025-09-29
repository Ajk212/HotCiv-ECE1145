package hotciv.strategy.alpha;

import hotciv.strategy.AgingStrategy;

public class AlphaAgingStrategy implements AgingStrategy {
    public int calculateNewAge(int currentAge) {
        return currentAge + 100;
    }
}

