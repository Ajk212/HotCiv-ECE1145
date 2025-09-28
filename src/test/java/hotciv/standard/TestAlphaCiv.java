package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/** Skeleton class for AlphaCiv test cases

    Updated Oct 2015 for using Hamcrest matchers

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

public class TestAlphaCiv {
    private Game game;

    /** Fixture for alphaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl();
    }

    @Test
    public void shouldHaveWorldSizeOf16x16() {
        assertThat(GameConstants.WORLDSIZE, is(16));
    }

    @Test
    public void shouldStartGameAtAge4000BC() {
        assertThat(game, is(notNullValue()));
        assertThat(game.getAge(), is(-4000));
    }

    @Test
    public void shouldIncrementAgeBy100AtEndOfRound() {
        assertThat(game, is(notNullValue()));
        assertThat(game.getAge(), is(-4000));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(-3900));
    }

    @Test
    public void ageShouldNotIncrementUntilEndOfRound() {
        assertThat(game, is(notNullValue()));
        assertThat(game.getAge(), is(-4000));
        game.endOfTurn();
        assertThat(game.getAge(), is(-4000));
    }

    @Test
    public void shouldHaveOceanTileAtPosition0_1() {
        assertThat(game, is(notNullValue()));
        Position p = new Position(0,1);
        assertThat(game.getTileAt(p), is(notNullValue()));

        Tile tile = game.getTileAt(p);
        assertThat(tile.getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void shouldHaveRedCityAtPosition1_1() {
        assertThat(game, is(notNullValue()));
        Position p = new Position(1,1);
        assertThat(game.getCityAt(p), is(notNullValue()));

        City city = game.getCityAt(p);
        assertThat(city.getOwner(), is(Player.RED));
    }

    @Test
    public void shouldHaveBlueCityAtPosition4_1() {
        assertThat(game, is(notNullValue()));
        Position p = new Position(4,1);
        assertThat(game.getCityAt(p), is(notNullValue()));

        City city = game.getCityAt(p);
        assertThat(city.getOwner(), is(Player.BLUE));
    }

    @Test
    public void shouldAlwaysHaveCityPopulationSizeOf1() {
        City city = new CityImpl(Player.RED);
        assertThat(city.getSize(), is(1));
    }

    @Test
    public void shouldBeRedAsStartingPlayer() {
        assertThat(game, is(notNullValue()));
        assertThat(game.getPlayerInTurn(), is(Player.RED));
    }

    @Test
    public void shouldChangeTurnFromRedToBlueOnEndOfTurn() {
        assertThat(game, is(notNullValue()));
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    }

    @Test
    public void shouldChangeTurnFromBlueToRedOnEndOfTurn() {
        assertThat(game, is(notNullValue()));
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.BLUE));
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.RED));
    }

    @Test
    public void shouldHaveRedWinAt3000BC() {
        assertThat(game, is(notNullValue()));

        // advance game from 4000 BC to 3000 BC
        for (int i = 0; i < 20; i++) { // 20 turns = 10 rounds = 1000 years
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(-3000));
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void canAccessUnitAt(){
      Position p1 = new Position(2,0); //position of Red Archer
      Unit testUnit1 = game.getUnitAt(p1); //get unit located at p1

      assertThat(testUnit1, is(notNullValue())); //checks if unit exist

      //Accessing starting Archer
      assertThat(testUnit1.getTypeString(), is("archer"));
      assertThat(testUnit1.getOwner(), is(Player.RED));
      assertThat(testUnit1.getDefensiveStrength(), is(1));
      assertThat(testUnit1.getAttackingStrength(), is(1));

      //Accessing starting legion
      Position p2 = new Position(3,2); //position of Blue legion
      Unit testUnit2 = game.getUnitAt(p2); //get unit located at p2

      assertThat(testUnit2.getTypeString(), is("legion"));
      assertThat(testUnit2.getOwner(), is(Player.BLUE));
      assertThat(testUnit2.getDefensiveStrength(), is(1));
      assertThat(testUnit2.getAttackingStrength(), is(1));

      //Accessing starting settler
      Position p3 = new Position(4,3); //position of Red settler
      Unit testUnit3 = game.getUnitAt(p3); //get unit located at p3

      assertThat(testUnit3.getTypeString(), is("settler"));
      assertThat(testUnit3.getOwner(), is(Player.RED));
      assertThat(testUnit3.getDefensiveStrength(), is(1));
      assertThat(testUnit3.getAttackingStrength(), is(1));
  }

    @Test
    public void canUseUnitAbility(){

        Position p1 = new Position(2,0);
        Position p2 = new Position(3,2);
        Position p3 = new Position(4,3);
        Position p4 = new Position(5,5);
        game.performUnitActionAt(p1);
        game.performUnitActionAt(p2);
        game.performUnitActionAt(p3);
        game.performUnitActionAt(p4);
    }

    @Test
    public void canMoveUnit(){
      Position p1 = new Position(2,0);
      Position p2 = new Position(3,0);
      assertThat(game.moveUnit(p1, p2), is(true));

      p1 = new Position(3,0);
      p2 = new Position(3,1);
      assertThat(game.moveUnit(p1, p2), is(true));

      p1 = new Position(3,1);
      p2 = new Position(3,3);
      assertThat(game.moveUnit(p1, p2), is(false));
  }

    @Test
    public void canAttackWithUnit(){
      Position p1 = new Position(2,0);
      Position p2 = new Position(3,0);
      assertThat(game.moveUnit(p1, p2), is(true));

      p1 = new Position(3,0);
      p2 = new Position(3,1);
      assertThat(game.moveUnit(p1, p2), is(true));

      p1 = new Position(3,1);
      p2 = new Position(3,2);
      assertThat(game.moveUnit(p1, p2), is(true));

      Unit testUnit = game.getUnitAt(p2); //get unit located at battle

      assertThat(testUnit.getTypeString(), is("archer"));
      assertThat(testUnit.getOwner(), is(Player.RED));
      assertThat(testUnit.getDefensiveStrength(), is(1));
      assertThat(testUnit.getAttackingStrength(), is(1));

  }

    @Test
    public void canAccessCity(){
        Position p1 = new Position(1,1);
        City testCity = game.getCityAt(p1);

        //Check if city exists at given position
        assertThat(testCity, is(notNullValue()));

        //Check who owns city
        assertThat(testCity.getOwner(), is(Player.RED));

        //Check size of city
        assertThat(testCity.getSize(), is(1));

        //Check treasury value
        assertThat(testCity.getTreasury(), is(0));

        //check starting production type of city
        assertThat(testCity.getProduction(),  is("archer"));


    }

    @Test
    public void testProductionGrowth(){
        Position p1 = new Position(1,1);
        Position p2 = new Position(4,1);
        City testCity1 = game.getCityAt(p1);
        City testCity2 = game.getCityAt(p2);

        //Check treasury value
        assertThat(testCity1.getTreasury(), is(0));
        assertThat(testCity2.getTreasury(), is(0));

        game.endOfTurn();
        game.endOfTurn();

        assertThat(testCity1.getTreasury(), is(6));
        assertThat(testCity2.getTreasury(), is(6));

        game.endOfTurn();
        game.endOfTurn();


        assertThat(testCity1.getTreasury(), is(2));
        assertThat(testCity2.getTreasury(), is(2));
    }

    @Test
    public void canChangeCityProduction(){
        Position p1 = new Position(1,1);
        Position p2 = new Position(4,1);
        City testCity1 = game.getCityAt(p1);
        City testCity2 = game.getCityAt(p2);

        assertThat(testCity1.getProduction(), is("archer"));
        assertThat(testCity2.getProduction(), is("archer"));

        game.changeProductionInCityAt(p2, "settler");
        assertThat(testCity2.getProduction(), is("settler"));
    }

    @Test
    public void cityCanProduceUnits(){
        Position p1 = new Position(1,1);

        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getUnitAt(p1), is(notNullValue()));
    }

    @Test
    public void canChangeCityWorkforce(){
        Position p1 = new Position(1,1);
        City testCity = game.getCityAt(p1);

        assertThat(testCity.getWorkforceFocus(), is("food"));
        game.changeWorkForceFocusInCityAt(p1, "production");
        assertThat(testCity.getWorkforceFocus(), is("production"));
    }

}
