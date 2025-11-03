package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {

    protected String unitType;
    protected Player owner;
    protected int attackingStrength;
    protected int defensiveStrength;
    protected boolean fortified;
    protected int totalMovement;
    protected int movement;
    protected int productionCost;
    protected boolean unrestrictedMovement;

    public UnitImpl(String unitType, Player owner, int attackingStrength, int defensiveStrength, int movement, int productionCost, boolean unrestrictedMovement) {
        this.unitType = unitType;
        this.owner = owner;
        this.attackingStrength = attackingStrength;
        this.defensiveStrength = defensiveStrength;
        this.fortified = false;
        this.totalMovement = movement;
        this.movement = movement;
        this.productionCost = productionCost;
        this.unrestrictedMovement = unrestrictedMovement;

    }

    public String getTypeString() { return unitType;}
    public Player getOwner() { return owner;}
    public int getMoveCount(){ return movement;}
    public int getDefensiveStrength(){ return defensiveStrength;}
    public int getAttackingStrength(){ return attackingStrength;}
    public void setMoveCount(int movement){ this.movement=movement;}
    public void setDefensiveStrength(int defensiveStrength){ this.defensiveStrength=defensiveStrength; }
    public void setFortified(boolean fortified){ this.fortified=fortified;}
    public boolean getFortified(){ return fortified;}
    public int getProductionCost(){return productionCost;}
    public void resetMoveCount(){ this.movement = totalMovement;}
}

