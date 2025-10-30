package hotciv.strategy.alpha;

import hotciv.framework.Player;
import hotciv.framework.Unit;
import hotciv.standard.UnitImpl;
import hotciv.strategy.UnitClassStrategy;

public class DefaultUnitClassStrategy implements UnitClassStrategy {
    public Unit createUnit(String type, Player owner) {
        return switch (type.toLowerCase()) {
            case "archer" -> new UnitImpl("archer", owner, 2, 3, 1);
            case "legion" -> new UnitImpl("legion", owner, 4, 2, 1);
            case "settler" -> new UnitImpl("settler", owner, 0, 3, 1);
            default -> null;
        };
    }
}
