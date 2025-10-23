package hotciv.strategy.beta;

import hotciv.strategy.*;
import hotciv.strategy.alpha.AlphaUnitActionStrategy;
import hotciv.strategy.alpha.AlphaWorldLayoutStrategy;


public class BetaCivFactory implements HotCivFactory {
    @Override
    public AgingStrategy createAgingStrategy() {
        return new BetaAgingStrategy();
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new AlphaUnitActionStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new BetaWinnerStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new AlphaWorldLayoutStrategy();
    }

}
