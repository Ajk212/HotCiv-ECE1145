package hotciv.strategy.epsilon;

import hotciv.strategy.*;
import hotciv.strategy.alpha.AlphaAgingStrategy;
import hotciv.strategy.alpha.AlphaUnitActionStrategy;
import hotciv.strategy.alpha.AlphaWorldLayoutStrategy;

public class EpsilonCivFactory implements HotCivFactory {
    @Override
    public AgingStrategy createAgingStrategy() {
        return new AlphaAgingStrategy();
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new AlphaUnitActionStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new EpsilonWinnerStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new AlphaWorldLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new EpsilonBattleStrategy(createDieRollingStrategy());
    }

    @Override
    public DieRollingStrategy createDieRollingStrategy() {
        return new RandomDieRollingStrategy();
    }
}
