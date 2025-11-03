package hotciv.strategy.delta;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.strategy.WorldLayoutStrategy;

public class DeltaWorldLayoutStrategy implements WorldLayoutStrategy {
    String[] layout = new String[] {
            "oooppmpppppooooo",
            "oophhppppfffppoo",
            "opppppmpppoooppo",
            "oppmmmppppoopppp",
            "oooppppphhppppoo",
            "opfppfppppphhppo",
            "ooopppoooooooooo",
            "opppppoppphppmoo",
            "opppppopphppcfoo",
            "pfffppppopffpppp",
            "ppppppppoooppppp",
            "oppmmmppppoooooo",
            "ooppppppffppppoo",
            "oooopppppppppooo",
            "ooppphhppooooooo",
            "ooooopppppppppoo"
    };

    public void initializeWorld(GameImpl game) {
        // set cities
        game.cityLoc.put(new Position(8,12), new CityImpl(Player.RED));
        game.cityLoc.put(new Position(4,5), new CityImpl(Player.BLUE));

        // set tile terrain types
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            String row = layout[r];
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                char ch = row.charAt(c);
                String terrain;
                switch (ch) {
                    case 'p':
                        terrain = GameConstants.PLAINS;
                        break;
                    case 'o':
                        terrain = GameConstants.OCEANS;
                        break;
                    case 'f':
                        terrain = GameConstants.FOREST;
                        break;
                    case 'm':
                        terrain = GameConstants.MOUNTAINS;
                        break;
                    case 'h':
                        terrain = GameConstants.HILLS;
                        break;
                    default:
                        terrain = GameConstants.PLAINS;
                        break;
                }
                game.tileLoc.put(new Position(r, c), new TileImpl(terrain));
            }
        }


    }
}
