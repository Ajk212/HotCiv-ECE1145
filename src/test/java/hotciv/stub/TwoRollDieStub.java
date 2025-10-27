package hotciv.stub;

import hotciv.strategy.DieRollingStrategy;

public class TwoRollDieStub implements DieRollingStrategy {
    private int firstRoll;
    private int secondRoll;
    private boolean isFirst = true;

    public TwoRollDieStub(int firstRoll, int secondRoll) {
        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
    }

    @Override
    public int roll() {
        if (isFirst) {
            isFirst = false;
            return firstRoll;
        } else {
            isFirst = true;
            return secondRoll;
        }
    }
}
