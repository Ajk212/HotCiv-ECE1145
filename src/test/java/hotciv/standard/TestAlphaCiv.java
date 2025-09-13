package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

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
}
