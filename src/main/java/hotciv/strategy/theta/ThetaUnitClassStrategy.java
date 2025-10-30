package hotciv.strategy.theta;

import hotciv.framework.Player;
import hotciv.framework.Unit;
import hotciv.standard.UnitImpl;
import hotciv.strategy.UnitClassStrategy;

public class ThetaUnitClassStrategy implements UnitClassStrategy {
    public Unit createUnit(String type, Player owner) {
        return switch (type.toLowerCase()) {
            case "archer" -> new UnitImpl("archer", owner, 2, 3, 1);
            case "legion" -> new UnitImpl("legion", owner, 4, 2, 1);
            case "settler" -> new UnitImpl("settler", owner, 0, 3, 1);
            case "ufo" -> new UnitImpl("ufo", owner, 1, 8, 2);
            default -> null;
        };

    }
}
