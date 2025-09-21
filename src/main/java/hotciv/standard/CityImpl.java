package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.City;

import java.util.Objects;

public class CityImpl implements City {
    protected Player owner;
    protected int treasury;
    protected String productionType;

    CityImpl(Player owner)
    {
        this.owner = owner;
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
        return null;
    }
}
