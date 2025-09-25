package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {

    protected String unitType;
    protected Player owner;
    protected int attackingStrength;
    protected int defensiveStrength;


    UnitImpl(String unitType, Player owner) {
        this.unitType = unitType;
        this.owner = owner;

        if(unitType.equalsIgnoreCase("ARCHER")){
            this.attackingStrength = 2;
            this.defensiveStrength = 3;
        }
        else if(unitType.equalsIgnoreCase("SETTLER")){
            this.attackingStrength = 0;
            this.defensiveStrength = 3;
        }
        else if(unitType.equalsIgnoreCase("LEGION")){
            this.attackingStrength = 4;
            this.defensiveStrength = 2;
        }
        else{
            this.attackingStrength = 1;
            this.defensiveStrength = 1;
        }

    }

    public String getTypeString() { return unitType;}
    public Player getOwner() { return owner;}
    public int getMoveCount(){ return 1;}
    public int getDefensiveStrength(){ return 1;}
    public int getAttackingStrength(){ return 1;}
}

