package hotciv.strategy.theta;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.strategy.UnitActionStrategy;

import java.util.Objects;

public class ThetaUnitActionStrategy implements UnitActionStrategy {
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
            boolean ownsCity = false;
            City city = game.cityLoc.get(p);

            if(isCityAtLocation) {
                 ownsCity = (city.getOwner() == owner);
            }


            Tile tile = game.getTileAt(p);
            boolean isForest = (tile.getTypeString().equalsIgnoreCase("Forest"));

            if(isCityAtLocation && !ownsCity) {
                if(city.getSize() == 1){
                    game.cityLoc.remove(p);
                }
                else if(city.getSize() > 1){
                    ((CityImpl) city).reduceSize();
                }
            }
            else if(!isCityAtLocation && isForest){
                game.tileLoc.remove(p);
                game.tileLoc.put(p, new TileImpl("Plains"));
            }

        }
    }

}


