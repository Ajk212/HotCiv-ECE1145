package hotciv.strategy;

public class FixedDieRollingStrategy implements DieRollingStrategy {
    private int fixedValue;

    public FixedDieRollingStrategy(int value) {
        this.fixedValue = value;
    }

    @Override
    public int roll() {
        return fixedValue;
    }
}
