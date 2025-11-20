package hotciv.stub;

import hotciv.framework.*;

public class EpsilonTestGameStub implements Game {
    private int attackerCombinedStrength;
    private int defenderCombinedStrength;

    public EpsilonTestGameStub(int attackerStrength, int defenderStrength) {
        this.attackerCombinedStrength = attackerStrength;
        this.defenderCombinedStrength = defenderStrength;
    }

    public Unit getUnitAt(Position p) {
        // return a stub unit that has precalculated combined strengths
        if (p.getRow() == 0 && p.getColumn() == 0) {
            return new StubUnitWithStrength(attackerCombinedStrength, 0);
        }
        if (p.getRow() == 1 && p.getColumn() == 1) {
            return new StubUnitWithStrength(0, defenderCombinedStrength);
        }
        return null;
    }

    public Tile getTileAt(Position p) {
        return new StubTileWithType(GameConstants.PLAINS); // terrain factor = 1
    }

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
