package hotciv.utility;

import hotciv.framework.*;

import java.util.Iterator;

public class BattleUtilities {

  public static int getTerrainFactor(Game game, Position position) {
    // cities overrule underlying terrain
    if ( game.getCityAt(position) != null ) { return 3; }
    Tile t = game.getTileAt(position);
    if ( t.getTypeString() == GameConstants.FOREST ||
            t.getTypeString() == GameConstants.HILLS ) {
      return 2;
    }
    return 1;
  }

  public static int getFriendlySupport(Game game, Position position, Player player) {
    Iterator<Position> neighborhood = Utility.get8neighborhoodIterator(position);
    Position p;
    int support = 0;
    while ( neighborhood.hasNext() ) {
      p = neighborhood.next();
      if ( game.getUnitAt(p) != null &&
              game.getUnitAt(p).getOwner() == player ) {
        support++;
      }
    }
    return support;
  }

  public static int getCombinedAttackStrength(Game game, Position position) {
    Unit unit = game.getUnitAt(position);
    if (unit == null) return 0;

    int baseStrength = unit.getAttackingStrength();
    int support = getFriendlySupport(game, position, unit.getOwner());
    int terrainFactor = getTerrainFactor(game, position);

    return (baseStrength + support) * terrainFactor;
  }

  public static int getCombinedDefenseStrength(Game game, Position position) {
    Unit unit = game.getUnitAt(position);
    if (unit == null) return 0;

    int baseStrength = unit.getDefensiveStrength();
    int support = getFriendlySupport(game, position, unit.getOwner());
    int terrainFactor = getTerrainFactor(game, position);

    return (baseStrength + support) * terrainFactor;
  }
}
