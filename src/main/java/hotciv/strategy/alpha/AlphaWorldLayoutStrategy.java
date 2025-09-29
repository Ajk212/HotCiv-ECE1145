package hotciv.strategy.alpha;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.strategy.WorldLayoutStrategy;

public class AlphaWorldLayoutStrategy implements WorldLayoutStrategy {
    public void initializeWorld(GameImpl game) {
        game.cityLoc.put(new Position(1,1), new CityImpl(Player.RED));
        game.cityLoc.put(new Position(4,1), new CityImpl(Player.BLUE));

        game.tileLoc.put(new Position(0,1), new TileImpl(GameConstants.OCEANS));

        game.unitLoc.put(new Position(2,0), new UnitImpl(GameConstants.ARCHER, Player.RED));
        game.unitLoc.put(new Position(4,3), new UnitImpl(GameConstants.SETTLER, Player.RED));
        game.unitLoc.put(new Position(3,2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
    }
}