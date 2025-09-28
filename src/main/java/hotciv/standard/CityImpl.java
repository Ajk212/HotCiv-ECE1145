package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.City;

import java.util.Objects;

public class CityImpl implements City {
    protected Player owner;
    protected int treasury;
    protected String productionType;
    protected String workforceFocus;
    protected int productionCost;

    CityImpl(Player owner)
    {
        this.owner = owner;
        this.treasury = 0;
        this.workforceFocus = "food";
        this.productionType = "archer";
        this.productionCost = 10;
    }
    
    public Player getOwner() {
        return owner;
    }
    
    public int getSize() {
        return 1;
    }
    
    public int getTreasury() {
        return treasury;
    }
    
    public String getProduction() {
        return productionType;
    }
    
    public String getWorkforceFocus() {
        return workforceFocus;
    }
}
