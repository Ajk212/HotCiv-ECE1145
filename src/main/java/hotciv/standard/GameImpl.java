package hotciv.standard;
import java.util.HashMap;
import java.util.Map;

import hotciv.framework.*;

/** Skeleton implementation of HotCiv.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

public class GameImpl implements Game {

  public Map<Position, Unit> unitLoc;

  public GameImpl(){
      //HashMap to store location of units
      unitLoc = new HashMap<>();

      //Add red starting archer
      unitLoc.put(new Position(2,0), new UnitImple(GameConstants.ARCHER, Player.RED, 1,1));

      //Add red starting settler
      unitLoc.put(new Position(4,3), new UnitImple(GameConstants.SETTLER, Player.RED, 1,1));

      //Add blue starting legion
      unitLoc.put(new Position(3,2), new UnitImple(GameConstants.LEGION, Player.BLUE, 1, 1));
  }

  public Tile getTileAt( Position p ) { return null; }
  public Unit getUnitAt( Position p ) {
      return unitLoc.get(p);
  }
  public City getCityAt( Position p ) { return null; }
  public Player getPlayerInTurn() { return null; }
  public Player getWinner() { return null; }
  public int getAge() { return 0; }
  public boolean moveUnit( Position from, Position to ) {

      int rowDiff = Math.abs(from.getRow() - to.getRow());
      int colDiff = Math.abs(from.getColumn() - to.getColumn());

      if (unitLoc.containsKey(from) && (rowDiff + colDiff <= 1)){
          Unit temp = unitLoc.get(from);
          unitLoc.remove(from);

          if(unitLoc.containsKey(to)){
              unitLoc.remove(to);
              System.out.println("Destination Unit Defeated");
          }

          unitLoc.put(to, temp);
          return true;
      } else if (rowDiff + colDiff > 1 || rowDiff + colDiff < 0) {
          System.out.println("Invalid Selection, Move Denied");
      }
      return false;
  }
  public void endOfTurn() {}
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {}

  public class UnitImple implements Unit{
      private String unitType;
      private Player owner;
      private int attackingStrength;
      private int defensiveStrength;

      public UnitImple(String unitType, Player owner, int attackingStrength, int defensiveStrength) {
          this.unitType = unitType;
          this.owner = owner;
          this.attackingStrength = attackingStrength;
          this.defensiveStrength = defensiveStrength;
      }

      public String getTypeString() { return unitType;}
      public Player getOwner() { return owner;}
      public int getMoveCount(){ return 1;}
      public int getDefensiveStrength(){ return 1;};
      public int getAttackingStrength(){ return 1;}
  }
}
