package hotciv.strategy.alpha;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.strategy.UnitClassStrategy;
import hotciv.strategy.WorldLayoutStrategy;

public class AlphaWorldLayoutStrategy implements WorldLayoutStrategy {
    String[] layout = new String[] {
            "phpppppppppppppp",
            "oppppppppppppppp",
            "ppmppppppppppppp",
            "pppppppppppppppp",
            "pppppppppppppppp",
            "pppppppppppppppp",
            "pppppppppppppppp",
            "pppppppppppppppp",
            "pppppppppppppppp",
            "pppppppppppppppp",
            "pppppppppppppppp",
            "pppppppppppppppp",
            "pppppppppppppppp",
            "pppppppppppppppp",
            "pppppppppppppppp",
            "pppppppppppppppp"
    };
    public void initializeWorld(GameImpl game) {
        game.cityLoc.put(new Position(1,1), new CityImpl(Player.RED));
        game.cityLoc.put(new Position(4,1), new CityImpl(Player.BLUE));

        // set tile terrain types
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            String row = layout[r];
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                char ch = row.charAt(c);
                String terrain = switch (ch) {
                    case 'p' -> GameConstants.PLAINS;
                    case 'o' -> GameConstants.OCEANS;
                    case 'f' -> GameConstants.FOREST;
                    case 'm' -> GameConstants.MOUNTAINS;
                    case 'h' -> GameConstants.HILLS;
                    default -> GameConstants.PLAINS;
                };
                game.tileLoc.put(new Position(r, c), new TileImpl(terrain));
            }
        }

        UnitClassStrategy unitStrategy = game.getUnitClassStrategy();

        game.unitLoc.put(new Position(2,0), unitStrategy.createUnit(GameConstants.ARCHER, Player.RED));
        game.unitLoc.put(new Position(4,3), unitStrategy.createUnit(GameConstants.SETTLER, Player.RED));
        game.unitLoc.put(new Position(3,2), unitStrategy.createUnit(GameConstants.LEGION, Player.BLUE));
    }
}