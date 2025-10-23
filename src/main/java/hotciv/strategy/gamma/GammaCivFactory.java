package hotciv.strategy.gamma;

import hotciv.strategy.*;
import hotciv.strategy.alpha.AlphaAgingStrategy;
import hotciv.strategy.alpha.AlphaWinnerStrategy;
import hotciv.strategy.alpha.AlphaWorldLayoutStrategy;

public class GammaCivFactory implements HotCivFactory {
    @Override
    public AgingStrategy createAgingStrategy() {
        return new AlphaAgingStrategy();
    }

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

}
