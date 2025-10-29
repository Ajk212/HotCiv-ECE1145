package hotciv.stub;

import hotciv.framework.*;

public class StubUnit implements Unit {
  private String type; private Player owner;
  public StubUnit(String type, Player owner) {
    this.type = type; this.owner = owner;
  }
  public String getTypeString() { return type; }
  public Player getOwner() { return owner; }
  public int getMoveCount() { return 0; }
  public int getDefensiveStrength() {
    // archer has defense 3
    if (type.equals(GameConstants.ARCHER)) return 3;
    return 0;
  }
  public int getAttackingStrength() {
    // archer has attack 2
    if (type.equals(GameConstants.ARCHER)) return 2;
    return 0;
  }
}
