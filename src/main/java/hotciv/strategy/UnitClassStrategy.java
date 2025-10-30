package hotciv.strategy;

import hotciv.framework.Player;
import hotciv.framework.Unit;

public interface UnitClassStrategy {
    public Unit createUnit(String type, Player owner);
}
