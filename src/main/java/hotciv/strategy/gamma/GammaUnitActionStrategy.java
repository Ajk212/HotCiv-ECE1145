package hotciv.strategy.gamma;

import hotciv.framework.*;
import java.util.Objects;

import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.strategy.UnitActionStrategy;


public class GammaUnitActionStrategy implements UnitActionStrategy {
    public void performUnitActionAt(Position p, GameImpl game) {
        Unit unit = game.getUnitAt(p);

        if(unit == null) {
            return;
        }

        String type = unit.getTypeString();

        if(type.equalsIgnoreCase(GameConstants.ARCHER)) {
            UnitImpl impl = (UnitImpl) unit;
            if(!impl.getFortified()){
                impl.setFortified(true);
                impl.setMoveCount(0);
                impl.setDefensiveStrength(impl.getDefensiveStrength() * 2);
            }
        }
        else if(type.equalsIgnoreCase(GameConstants.SETTLER)){
            Player owner = unit.getOwner();
            game.addCityAt(p, owner);
            game.removeUnitAt(p);

            return;
        }
        else if (Objects.equals(unit.getTypeString(), "legion")) {
            return;
        }
    }

}

