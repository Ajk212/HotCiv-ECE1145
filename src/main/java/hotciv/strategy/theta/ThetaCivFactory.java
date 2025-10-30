package hotciv.strategy.theta;

import hotciv.strategy.*;
import hotciv.strategy.alpha.AlphaAgingStrategy;
import hotciv.strategy.alpha.AlphaWinnerStrategy;
import hotciv.strategy.alpha.AlphaWorldLayoutStrategy;
import hotciv.strategy.alpha.AttackerAlwaysWinsStrategy;
import hotciv.strategy.gamma.GammaUnitActionStrategy;

public class ThetaCivFactory implements HotCivFactory {
    @Override
    public AgingStrategy createAgingStrategy() {
        return new AlphaAgingStrategy();
    }

    @Override
    public UnitClassStrategy createUnitClassStrategy() {return new ThetaUnitClassStrategy(); }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new GammaUnitActionStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new AlphaWinnerStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new AlphaWorldLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new AttackerAlwaysWinsStrategy();
    }

    @Override
    public DieRollingStrategy createDieRollingStrategy() {
        // Not used in GammaCiv
        return new FixedDieRollingStrategy(1);
    }
}
