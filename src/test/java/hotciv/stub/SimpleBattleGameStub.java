package hotciv.stub;

import hotciv.framework.*;

public class SimpleBattleGameStub implements Game {
    public Tile getTileAt(Position p) { return null; }
    public Unit getUnitAt(Position p) { return null; }
    public City getCityAt(Position p) { return null; }
    public Player getPlayerInTurn() { return null; }
    public Player getWinner() { return null; }
    public int getAge() { return 0; }
    public boolean moveUnit(Position from, Position to) { return false; }
    public void endOfTurn() {}
    public void changeWorkForceFocusInCityAt(Position p, String balance) {}
    public void changeProductionInCityAt(Position p, String unitType) {}
    public void performUnitActionAt(Position p) {}

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }
}
