package hotciv.standard;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import hotciv.framework.*;
import hotciv.strategy.*;
import hotciv.strategy.alpha.*;


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
    public final int productionValue = 6;

    public Map<Position, CityImpl> cityLoc;
    public Map<Position, TileImpl> tileLoc;
    public Map<Position, Unit> unitLoc;

    private AgingStrategy agingStrategy;
    private UnitActionStrategy unitActionStrategy;
    private WinnerStrategy winnerStrategy;
    private WorldLayoutStrategy worldLayoutStrategy;

    public GameImpl(AgingStrategy as, UnitActionStrategy uas, WinnerStrategy ws, WorldLayoutStrategy wls) {
        this.agingStrategy = as;
        this.winnerStrategy = ws;
        this.unitActionStrategy = uas;
        this.worldLayoutStrategy = wls;

        cityLoc = new HashMap<>();
        tileLoc = new HashMap<>();
        unitLoc = new HashMap<>();

        // Delegate world initialization
        worldLayoutStrategy.initializeWorld(this);
    }

  public Tile getTileAt( Position p ) { 
      return tileLoc.get(p); 
  }
  public Unit getUnitAt( Position p ) {
      return unitLoc.get(p);
  }

  public void removeUnitAt( Position p) {
        unitLoc.remove(p);
  }

  public City getCityAt(Position p) {
      return cityLoc.get(p);
  }

  public void addCityAt(Position pos, Player owner) {
        cityLoc.put(pos, new CityImpl(owner));
  }

    public Player getPlayerInTurn() {
      return playerInTurn;
  }

  public Player getWinner() {
      return winnerStrategy.getWinner(this);
  }

  public int getAge() {
      return worldAge;
  }

    public boolean moveUnit( Position from, Position to ) {

        int rowDiff = Math.abs(from.getRow() - to.getRow());
        int colDiff = Math.abs(from.getColumn() - to.getColumn());

        if (unitLoc.containsKey(from) && (rowDiff + colDiff <= 1)){
            Unit movingUnit = unitLoc.get(from);
            unitLoc.remove(from);

            if(unitLoc.containsKey(to)){
                unitLoc.remove(to);
                //System.out.println("Destination Unit Defeated");
            }

            //Unit capturing city
            if(cityLoc.containsKey(to)){
                CityImpl city = cityLoc.get(to);
                if(city.getOwner() != movingUnit.getOwner()){
                    city.owner =  movingUnit.getOwner();
                }
            }

            unitLoc.put(to, movingUnit);
            return true;
        } else if (rowDiff + colDiff > 1 || rowDiff + colDiff < 0) {
            System.out.println("Invalid Selection, Move Denied");
        }
        return false;
    }

  public void endOfTurn() {
      playerInTurn = (playerInTurn == Player.RED) ? Player.BLUE : Player.RED;

      //add production to city
      for(Map.Entry<Position, CityImpl> entry : cityLoc.entrySet()) {
          CityImpl city = entry.getValue();
          if(city.getOwner().equals(playerInTurn)){
              city.treasury += productionValue;
          }
      }

      if (playerInTurn == Player.RED) {
          endOfRound();
      }
  }

  public void endOfRound() {
      // TODO restore all units' move counts
      // TODO produce food and production in all cities
      // TODO increase population size in all cities (if enough food)
      //Iterate over each active city
      for(Map.Entry<Position, CityImpl> entry : cityLoc.entrySet()) {
          Position cityPos = entry.getKey();
          CityImpl city = entry.getValue();


          //  increase production in all cities
          city.treasury += productionValue;

          //produce units in all cities (if enough production)
          if (city.treasury >= city.productionCost) {
              produceUnit(city, cityPos);
          }
      }
      // increment the world age
      worldAge = agingStrategy.calculateNewAge(worldAge);
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

  }
  public void performUnitActionAt( Position p ) {
      unitActionStrategy.performUnitActionAt(p, this);
  }


}
