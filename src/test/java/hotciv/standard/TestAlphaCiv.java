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

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game, is(notNullValue()));
    // TODO: reenable the assert below to get started...
    //assertThat(game.getPlayerInTurn(), is(Player.RED));
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



  /* REMOVE ME. Not a test of HotCiv, just an example of what
      matchers the hamcrest library has...
  @Test
  public void shouldDefinetelyBeRemoved() {
    // Matching null and not null values
    // 'is' require an exact match
    String s = null;
    assertThat(s, is(nullValue()));
    s = "Ok";
    assertThat(s, is(notNullValue()));
    assertThat(s, is("Ok"));

    // If you only validate substrings, use containsString
    assertThat("This is a dummy test", containsString("dummy"));

    // Match contents of Lists
    List<String> l = new ArrayList<String>();
    l.add("Bimse");
    l.add("Bumse");
    // Note - ordering is ignored when matching using hasItems
    assertThat(l, hasItems(new String[] {"Bumse","Bimse"}));

    // Matchers may be combined, like is-not
    assertThat(l.get(0), is(not("Bumse")));
  }
  */
}
