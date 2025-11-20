package hotciv.stub;

import hotciv.framework.*;

public class GameStubWithTerrain implements Game {
  public Tile getTileAt(Position p) {
    // (5,5) is forest
    if ( p.getRow() == 5 && p.getColumn() == 5 ) {
      return new StubTile(GameConstants.FOREST, 5, 5);
    }
    return new StubTile(GameConstants.PLAINS, 0, 0);
  }

  public Unit getUnitAt(Position p) {
    // Blue archer at (5,5)
    if ( p.getRow() == 5 && p.getColumn() == 5 ) {
      return new StubUnit(GameConstants.ARCHER, Player.BLUE);
    }
    // Blue unit at (4,4)
    if ( p.getRow() == 4 && p.getColumn() == 4 ) {
      return new StubUnit(GameConstants.ARCHER, Player.BLUE);
    }
    return null;
  }

  public City getCityAt(Position p) { return null; }
  public void changeProductionInCityAt(Position p, String unitType) {}
  public void changeWorkForceFocusInCityAt(Position p, String balance) {}
  public void endOfTurn() {}
  public Player getPlayerInTurn() {return null;}
  public Player getWinner() {return null;}
  public int getAge() { return 0; }
  public boolean moveUnit(Position from, Position to) {return false;}
  public void performUnitActionAt( Position p ) {}

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }
}
