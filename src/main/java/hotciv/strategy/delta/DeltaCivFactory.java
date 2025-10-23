package hotciv.strategy.delta;

import hotciv.strategy.*;
import hotciv.strategy.alpha.AlphaAgingStrategy;
import hotciv.strategy.alpha.AlphaUnitActionStrategy;
import hotciv.strategy.alpha.AlphaWinnerStrategy;
import hotciv.strategy.alpha.AlphaWorldLayoutStrategy;

public class DeltaCivFactory implements HotCivFactory {
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
        return new AlphaWinnerStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new DeltaWorldLayoutStrategy();
    }

}