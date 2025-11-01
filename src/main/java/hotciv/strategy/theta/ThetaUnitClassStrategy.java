package hotciv.strategy.theta;

import hotciv.framework.Player;
import hotciv.framework.Unit;
import hotciv.standard.UnitImpl;
import hotciv.strategy.UnitClassStrategy;

public class ThetaUnitClassStrategy implements UnitClassStrategy {
    public Unit createUnit(String type, Player owner) {
        return switch(type.toLowerCase()){
            case "archer" -> new UnitImpl("archer", owner, 2, 3, 1, 10, false);
            case "legion" -> new UnitImpl("legion", owner, 4, 2, 1, 15, false);
            case "settler" -> new UnitImpl("settler", owner, 0, 3, 1, 30, false);
            case "ufo" -> new UnitImpl("ufo", owner, 1, 8, 2, 60, true);
            default -> null;
        };

    }
    public boolean canProduceUnit(String unitType){
        return switch(unitType.toLowerCase()){
            case "archer", "legion", "settler", "ufo" -> true;
            default -> false;
        };
    }

    public int getProductionCost(String unitType){
        return switch(unitType.toLowerCase()){
            case "archer" -> 10;
            case "legion" -> 15;
            case "settler" -> 30;
            case "ufo" -> 60;
            default -> 0;
        };
    }
}
