package hotciv.stub;

import hotciv.framework.*;

public class StubUnitWithStrength implements Unit {
    private int attack;
    private int defense;

    public StubUnitWithStrength(int attack, int defense) {
        this.attack = attack;
        this.defense = defense;
    }

    public String getTypeString() { return GameConstants.ARCHER; }
    public Player getOwner() { return Player.RED; }
    public int getMoveCount() { return 0; }
    public int getDefensiveStrength() { return defense; }
    public int getAttackingStrength() { return attack; }
}
