package hotciv.standard;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

// this is a hotfix for release 2.1!

public class GameImpl implements Game {

    public Player playerInTurn = Player.RED;
    public int worldAge = -4000;
    public Map<Position, CityImpl> cityLoc;
    public Map<Position, TileImpl> tileLoc;
    //HashMap to store location of units
    public Map<Position, Unit> unitLoc;
    public final int productionValue = 6;
  
    public GameImpl() {
        cityLoc = new HashMap<>();
        tileLoc = new HashMap<>();
        unitLoc = new HashMap<>();

        cityLoc.put(new Position(1,1), new CityImpl(Player.RED));
        cityLoc.put(new Position(4,1), new CityImpl(Player.BLUE));
     
        tileLoc.put(new Position(0,1), new TileImpl(GameConstants.OCEANS));
      
        //Add red starting archer
        unitLoc.put(new Position(2,0), new UnitImpl(GameConstants.ARCHER, Player.RED));

        //Add red starting settler
        unitLoc.put(new Position(4,3), new UnitImpl(GameConstants.SETTLER, Player.RED));

        //Add blue starting legion
        unitLoc.put(new Position(3,2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
    }

  public Tile getTileAt( Position p ) { 
      return tileLoc.get(p); 
  }
  public Unit getUnitAt( Position p ) {
      return unitLoc.get(p);
  }

  public City getCityAt(Position p) {
      return cityLoc.get(p);
  }

  public Player getPlayerInTurn() {
      return playerInTurn;
  }

  public Player getWinner() {
      if (getAge() >= -3000) {
          return Player.RED;
      }
      return null;
  }

  public int getAge() {
      return worldAge;
  }

  public boolean moveUnit( Position from, Position to ) {

      int rowDiff = Math.abs(from.getRow() - to.getRow());
      int colDiff = Math.abs(from.getColumn() - to.getColumn());

      if (unitLoc.containsKey(from) && (rowDiff + colDiff <= 1)){
          Unit temp = unitLoc.get(from);
          unitLoc.remove(from);

          if(unitLoc.containsKey(to)){
              unitLoc.remove(to);
              //System.out.println("Destination Unit Defeated");
          }

          unitLoc.put(to, temp);
          return true;
      } else if (rowDiff + colDiff > 1 || rowDiff + colDiff < 0) {
          System.out.println("Invalid Selection, Move Denied");
      }
      return false;
  }

  public void endOfTurn() {
      playerInTurn = (playerInTurn == Player.RED) ? Player.BLUE : Player.RED;
      //TODO double check end of turn logic for active player


      if (playerInTurn == Player.RED) {
          endOfRound();
      }
  }

  public void endOfRound() {
      // TODO restore all units' move counts



      //Iterate over each active city
      for(Map.Entry<Position, CityImpl> entry : cityLoc.entrySet()) {
          Position cityPos = entry.getKey();
          CityImpl city = entry.getValue();

          // TODO produce food in all cities

          //  increase production in all cities
          city.treasury += productionValue;

          //produce units in all cities (if enough production)
          if(city.treasury >= city.productionCost){
              produceUnit(city, cityPos);
          }

          // TODO increase population size in all cities (if enough food)

      }


      // increment the world age
      worldAge += 100;

      if(worldAge >= 3000){
          getWinner();
      }
  }

  public void produceUnit(CityImpl city, Position cityPos){
      Unit newUnit = new UnitImpl(city.productionType, city.owner);
      if(!unitLoc.containsKey(cityPos)){
          unitLoc.put(cityPos, newUnit);
          System.out.println("Unit Spawned at: " + cityPos.getRow() + " " + cityPos.getColumn());
      }
      else{
          Position unitPos = findAvailableSpawnLocation(cityPos);
          if(unitPos == null){
              System.out.println("Invalid Spawn Location");
          }
          else{
              System.out.println("Unit Spawned at: " + unitPos.getRow() + " " + unitPos.getColumn());
              unitLoc.put(unitPos, newUnit);
          }
      }
      city.treasury -= city.productionCost;
  }

    //Helper function to find free space when producing unit
    private Position findAvailableSpawnLocation(Position p) {
        //Directions around a given space
        int[][] directions = {
                {0, -1},  // North
                {1, -1},  // NE
                {1, 0},   // East
                {1, 1},   // SE
                {0, 1},   // South
                {-1, 1},  // SW
                {-1, 0},  // West
                {-1, -1}  // NW
        };

        //Iterate over directions to find free space
        for (int[] d : directions) {
            Position availableSpace = new Position(p.getRow() + d[1], p.getColumn() + d[0]);
            //If free space found, return position
            if (!unitLoc.containsKey(availableSpace)) {
                return availableSpace;
            }
        }
        //return null if no free space found
        return null;
    }

  public void changeWorkForceFocusInCityAt( Position p, String balance ) {

      //TODO add workforce balance value changes for production and food
      if(!cityLoc.containsKey(p)){
          System.out.println("---- ERROR: Invalid City Location ----");
          return;
      }
      if(!(balance.equals("food") || balance.equals("production"))){
          System.out.println("---- ERROR: Invalid City Balance Type ----");
          return;
      }

      CityImpl city = cityLoc.get(p);
      city.workforceFocus = balance;

  }
  public void changeProductionInCityAt( Position p, String unitType ) {
      if(!(unitType.equals("settler") || unitType.equals("legion") || unitType.equals("archer"))){
          System.out.println("---- ERROR: Invalid Unit Production Type ----");
          return;
      }
      if(!cityLoc.containsKey(p)){
          System.out.println("---- ERROR: Invalid City Location ----");
          return;
      }

      CityImpl city = cityLoc.get(p);
      city.productionType = unitType;

      int cost;

      if(unitType.equals("settler")){
          cost = 30;
      }
      else if(unitType.equals("legion")){
          cost = 15;
      }
      else{
          cost = 10;
      }

      city.productionCost = cost;

  }

  public void performUnitActionAt( Position p ) {

      if(unitLoc.containsKey(p)){
          Unit testUnit = getUnitAt(p);
          if(Objects.equals(testUnit.getTypeString(), "archer")){
              System.out.println("No associated ability");
              return;
          }
          else if(Objects.equals(testUnit.getTypeString(), "settler")){
              //System.out.println("Using associated ability: Build City");
              //Call function to perform action later
              return;
          }
          else if(Objects.equals(testUnit.getTypeString(), "legion")){
              //System.out.println("Using associated ability: Fortify");
              //Call function to perform action later//Call function to perform action later
              return;
          }
      }
      System.out.println("No unit at selected position");

  }



}
