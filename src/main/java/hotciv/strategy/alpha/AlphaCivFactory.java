package hotciv.strategy.alpha;

import hotciv.strategy.*;

public class AlphaCivFactory implements HotCivFactory {
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
        return new AlphaWorldLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new AttackerAlwaysWinsStrategy();
    }

    @Override
    public DieRollingStrategy createDieRollingStrategy() {
        // Not used in AlphaCiv, but required by interface
        return new FixedDieRollingStrategy(1);
    }

}
