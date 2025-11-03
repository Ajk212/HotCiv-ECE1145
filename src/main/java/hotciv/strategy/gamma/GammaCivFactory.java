package hotciv.strategy.gamma;

import hotciv.strategy.*;
import hotciv.strategy.alpha.*;

public class GammaCivFactory implements HotCivFactory {
    @Override
    public AgingStrategy createAgingStrategy() {
        return new AlphaAgingStrategy();
    }

    @Override
    public UnitClassStrategy createUnitClassStrategy() {return new DefaultUnitClassStrategy();}

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
