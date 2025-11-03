package hotciv.strategy.delta;

import hotciv.strategy.*;
import hotciv.strategy.alpha.*;

public class DeltaCivFactory implements HotCivFactory {
    @Override
    public AgingStrategy createAgingStrategy() {
        return new AlphaAgingStrategy();
    }

    @Override
    public UnitClassStrategy createUnitClassStrategy() {return new DefaultUnitClassStrategy();}

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new AlphaUnitActionStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new AlphaWinnerStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new DeltaWorldLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new AttackerAlwaysWinsStrategy();
    }

    @Override
    public DieRollingStrategy createDieRollingStrategy() {
        // Not used in DeltaCiv
        return new FixedDieRollingStrategy(1);
    }

}