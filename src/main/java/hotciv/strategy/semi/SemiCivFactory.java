package hotciv.strategy.semi;

import hotciv.strategy.*;
import hotciv.strategy.alpha.DefaultUnitClassStrategy;
import hotciv.strategy.beta.BetaAgingStrategy;
import hotciv.strategy.delta.DeltaWorldLayoutStrategy;
import hotciv.strategy.epsilon.EpsilonBattleStrategy;
import hotciv.strategy.epsilon.EpsilonWinnerStrategy;
import hotciv.strategy.gamma.GammaUnitActionStrategy;

public class SemiCivFactory implements HotCivFactory {
    @Override
    public AgingStrategy createAgingStrategy() {
        return new BetaAgingStrategy();
    }

    @Override
    public UnitClassStrategy createUnitClassStrategy() {return new DefaultUnitClassStrategy();}

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new GammaUnitActionStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new EpsilonWinnerStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new DeltaWorldLayoutStrategy();
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
