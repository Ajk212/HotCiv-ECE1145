package hotciv.strategy.zeta;

import hotciv.strategy.*;
import hotciv.strategy.alpha.*;

public class ZetaCivFactory implements HotCivFactory {
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
        return new ZetaWinnerStrategy();
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
        return new FixedDieRollingStrategy(1);
    }
}
