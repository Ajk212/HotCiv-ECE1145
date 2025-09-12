package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;
import java.util.Map;

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

    public Player playerInTurn = Player.RED;
    public int worldAge = -4000;
    public Map<Position, CityImpl> cityLoc;
    public Map<Position, TileImpl> tileLoc;

    public GameImpl() {
        cityLoc = new HashMap<>();
        tileLoc = new HashMap<>();

        cityLoc.put(new Position(1,1), new CityImpl(Player.RED));
        cityLoc.put(new Position(4,1), new CityImpl(Player.BLUE));
        
        tileLoc.put(new Position(0,1), new TileImpl(GameConstants.OCEANS));
    }

  public Tile getTileAt( Position p ) { 
      return tileLoc.get(p); 
  }
  public Unit getUnitAt( Position p ) { return null; }

  public City getCityAt(Position p) {
      return cityLoc.get(p);
  }

  public Player getPlayerInTurn() {
      return playerInTurn;
  }

  public Player getWinner() { return null; }

  public int getAge() {
      return worldAge;
  }

  public boolean moveUnit( Position from, Position to ) {
    return false;
  }

  public void endOfTurn() {
      playerInTurn = (playerInTurn == Player.RED) ? Player.BLUE : Player.RED;

      if (playerInTurn == Player.RED) {
          endOfRound();
      }
  }

  public void endOfRound() {
      // TODO restore all units' move counts
      // TODO produce food and production in all cities
      // TODO produce units in all cities (if enough production)
      // TODO increase population size in all cities (if enough food)

      // increment the world age
      worldAge += 100;
  }


  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {}
}
