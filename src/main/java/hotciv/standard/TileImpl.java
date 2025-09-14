package hotciv.standard;

import hotciv.framework.Tile;

public class TileImpl implements Tile {
    protected String tileType;

    TileImpl(String tileType) {
        this.tileType = tileType;
    }
    
    public String getTypeString() {
        return tileType;
    }
}