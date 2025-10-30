package hotciv.strategy.theta;

import hotciv.framework.*;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.strategy.UnitActionStrategy;

import java.util.Objects;

public class ThetaUnitActionStrategy {
    public void performUnitActionAt(Position p, GameImpl game) {
        Unit unit = game.getUnitAt(p);

        if(unit == null) {
            return;
        }

        String type = unit.getTypeString();

        if(type.equalsIgnoreCase("Archer")) {
            UnitImpl impl = (UnitImpl) unit;
            if(!impl.getFortified()){
                impl.setFortified(true);
                impl.setMoveCount(0);
                impl.setDefensiveStrength(impl.getDefensiveStrength() * 2);
            }
        }
        else if(type.equalsIgnoreCase("Settler")){
            Player owner = unit.getOwner();
            game.addCityAt(p, owner);
            game.removeUnitAt(p);

            return;
        }
        else if (type.equalsIgnoreCase("Legion")) {
            return;
        }
        else if (type.equalsIgnoreCase("UFO")){
            Player owner = unit.getOwner();
            boolean isCityAtLocation = game.cityLoc.containsKey(p);
            City city = game.cityLoc.get(p);
            boolean ownsCity = city.getOwner() == owner;

            if(isCityAtLocation && !ownsCity) {
                if(city.getSize() == 0){
                    game.cityLoc.remove(p);
                }
            }

        }
    }

}


