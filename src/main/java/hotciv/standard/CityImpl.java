package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.City;

public class CityImpl implements City {
    protected Player owner;
    protected int treasury;

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
        return 0;
    }
    
    public String getProduction() {
        return null;
    }
    
    public String getWorkforceFocus() {
        return null;
    }
}
