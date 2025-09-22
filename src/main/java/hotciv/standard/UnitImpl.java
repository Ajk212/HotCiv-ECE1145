package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {

    protected String unitType;
    protected Player owner;
    protected int attackingStrength;
    protected int defensiveStrength;


    UnitImpl(String unitType, Player owner, int attackingStrength, int defensiveStrength) {
        this.unitType = unitType;
        this.owner = owner;
        this.attackingStrength = attackingStrength;
        this.defensiveStrength = defensiveStrength;
    }

    public String getTypeString() { return unitType;}
    public Player getOwner() { return owner;}
    public int getMoveCount(){ return 1;}
    public int getDefensiveStrength(){ return 1;}
    public int getAttackingStrength(){ return 1;}
}

