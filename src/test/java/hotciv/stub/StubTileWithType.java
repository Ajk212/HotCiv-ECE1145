package hotciv.stub;

import hotciv.framework.*;

public class StubTileWithType implements Tile {
    private String type;

    public StubTileWithType(String type) {
        this.type = type;
    }

    public String getTypeString() { return type; }
}
