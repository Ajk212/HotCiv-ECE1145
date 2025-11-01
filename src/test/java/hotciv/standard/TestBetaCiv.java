package hotciv.standard;

import hotciv.framework.*;
import hotciv.strategy.alpha.AlphaCivFactory;
import hotciv.strategy.beta.BetaAgingStrategy;
import hotciv.strategy.alpha.AlphaUnitActionStrategy;
import hotciv.strategy.beta.BetaWinnerStrategy;
import hotciv.strategy.alpha.AlphaWorldLayoutStrategy;
import hotciv.strategy.beta.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestBetaCiv {
    private Game game;
    /** Fixture for alphaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new BetaCivFactory());
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
    public void canAccessUnitAt(){
        Position p1 = new Position(2,0); //position of Red Archer
        Unit testUnit1 = game.getUnitAt(p1); //get unit located at p1

        assertThat(testUnit1, is(notNullValue())); //checks if unit exist

        //Accessing starting Archer
        assertThat(testUnit1.getTypeString(), is("archer"));
        assertThat(testUnit1.getOwner(), is(Player.RED));
        assertThat(testUnit1.getDefensiveStrength(), is(3));
        assertThat(testUnit1.getAttackingStrength(), is(2));

        //Accessing starting legion
        Position p2 = new Position(3,2); //position of Blue legion
        Unit testUnit2 = game.getUnitAt(p2); //get unit located at p2

        assertThat(testUnit2.getTypeString(), is("legion"));
        assertThat(testUnit2.getOwner(), is(Player.BLUE));
        assertThat(testUnit2.getDefensiveStrength(), is(2));
        assertThat(testUnit2.getAttackingStrength(), is(4));

        //Accessing starting settler
        Position p3 = new Position(4,3); //position of Red settler
        Unit testUnit3 = game.getUnitAt(p3); //get unit located at p3

        assertThat(testUnit3.getTypeString(), is("settler"));
        assertThat(testUnit3.getOwner(), is(Player.RED));
        assertThat(testUnit3.getDefensiveStrength(), is(3));
        assertThat(testUnit3.getAttackingStrength(), is(0));
    }

    @Test
    public void canAttackWithUnit(){
        Position p1 = new Position(2,0);
        Position p2 = new Position(3,0);
        assertThat(game.moveUnit(p1, p2), is(true));

        game.endOfTurn();
        game.endOfTurn();

        p1 = new Position(3,0);
        p2 = new Position(3,1);
        assertThat(game.moveUnit(p1, p2), is(true));

        game.endOfTurn();
        game.endOfTurn();

        p1 = new Position(3,1);
        p2 = new Position(3,2);
        assertThat(game.moveUnit(p1, p2), is(true));

        Unit testUnit = game.getUnitAt(p2); //get unit located at battle

        assertThat(testUnit.getTypeString(), is("archer"));
        assertThat(testUnit.getOwner(), is(Player.RED));
        assertThat(testUnit.getDefensiveStrength(), is(3));
        assertThat(testUnit.getAttackingStrength(), is(2));

    }

    @Test
    public void winnerFoundByConquest(){
        //Red City at 1,1 : Blue City at 4,1 | Red unit at 2,0 : Blue unit at 3,2

        //Check winner is null
        assertThat(game.getWinner(), is(nullValue()));

        //Moving red unit to 4,1 each turn
        Position p1 = new Position(2,0);
        Position p2 = new Position(3,0);
        assertThat(game.moveUnit(p1, p2), is(true));

        game.endOfTurn();
        game.endOfTurn();

        p1 = new Position(3,0);
        p2 = new Position(4,0);
        assertThat(game.moveUnit(p1, p2), is(true));

        game.endOfTurn();
        game.endOfTurn();

        //Move red unit onto Blue city to capture
        p1 = new Position(4,0);
        p2 = new Position(4,1);
        assertThat(game.moveUnit(p1, p2), is(true));
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void ageAdvanceBetaCiv(){
        //Starting age test
        assertThat(game, is(notNullValue()));
        assertThat(game.getAge(), is(-4000));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(-3900));

        //Testing 3900BC - 100BC
        for(int i = 0; i < 38; i++){
            game.endOfTurn();
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(-100));

        //Testing 100BC - 1BC
        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getAge(), is(-1));

        //Testing 1BC - 1AD
        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getAge(), is(1));

        //Testing 1AD - 50AD
        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getAge(), is(50));

        //Testing 50AD - 1750
        for(int i = 0; i < 34; i++){
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(1750));

        //Testing 1750 - 1900
        for(int i = 0; i < 6; i++){
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(1900));

        //Testing 1900 - 1970
        for(int i = 0; i < 14; i++){
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(1970));

        //Testing 1970 - 1971
        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getAge(), is(1971));
    }
}
