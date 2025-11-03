package hotciv.standard;

import hotciv.framework.*;
import hotciv.strategy.alpha.AlphaWorldLayoutStrategy;
import hotciv.strategy.gamma.*;
import hotciv.strategy.alpha.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestGammaCiv {
    private Game game;

    /**
     * Fixture for alphaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new GammaCivFactory());
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
        Position p = new Position(1, 1);
        assertThat(game.getCityAt(p), is(notNullValue()));

        City city = game.getCityAt(p);
        assertThat(city.getOwner(), is(Player.RED));
    }

    @Test
    public void shouldHaveBlueCityAtPosition4_1() {
        assertThat(game, is(notNullValue()));
        Position p = new Position(4, 1);
        assertThat(game.getCityAt(p), is(notNullValue()));

        City city = game.getCityAt(p);
        assertThat(city.getOwner(), is(Player.BLUE));
    }

    @Test
    public void canAccessUnitAt() {
        Position p1 = new Position(2, 0); //position of Red Archer
        Unit testUnit1 = game.getUnitAt(p1); //get unit located at p1

        assertThat(testUnit1, is(notNullValue())); //checks if unit exist

        //Accessing starting Archer
        assertThat(testUnit1.getTypeString(), is("archer"));
        assertThat(testUnit1.getOwner(), is(Player.RED));
        assertThat(testUnit1.getDefensiveStrength(), is(3));
        assertThat(testUnit1.getAttackingStrength(), is(2));

        //Accessing starting legion
        Position p2 = new Position(3, 2); //position of Blue legion
        Unit testUnit2 = game.getUnitAt(p2); //get unit located at p2

        assertThat(testUnit2.getTypeString(), is("legion"));
        assertThat(testUnit2.getOwner(), is(Player.BLUE));
        assertThat(testUnit2.getDefensiveStrength(), is(2));
        assertThat(testUnit2.getAttackingStrength(), is(4));

        //Accessing starting settler
        Position p3 = new Position(4, 3); //position of Red settler
        Unit testUnit3 = game.getUnitAt(p3); //get unit located at p3

        assertThat(testUnit3.getTypeString(), is("settler"));
        assertThat(testUnit3.getOwner(), is(Player.RED));
        assertThat(testUnit3.getDefensiveStrength(), is(3));
        assertThat(testUnit3.getAttackingStrength(), is(0));
    }

    @Test
    public void canAttackWithUnit() {
        Position p1 = new Position(2, 0);
        Position p2 = new Position(3, 0);
        assertThat(game.moveUnit(p1, p2), is(true));

        game.endOfTurn();
        game.endOfTurn();

        p1 = new Position(3, 0);
        p2 = new Position(3, 1);
        assertThat(game.moveUnit(p1, p2), is(true));

        game.endOfTurn();
        game.endOfTurn();

        p1 = new Position(3, 1);
        p2 = new Position(3, 2);
        assertThat(game.moveUnit(p1, p2), is(true));

        Unit testUnit = game.getUnitAt(p2); //get unit located at battle

        assertThat(testUnit.getTypeString(), is("archer"));
        assertThat(testUnit.getOwner(), is(Player.RED));
        assertThat(testUnit.getDefensiveStrength(), is(3));
        assertThat(testUnit.getAttackingStrength(), is(2));
    }

    @Test
    public void canActivateArcherPower(){
        Position p = new Position(2, 0);
        Unit testUnit = game.getUnitAt(p);

        assertThat(testUnit, is(notNullValue()));
        assertThat(testUnit.getDefensiveStrength(), is(3));
        assertThat(testUnit.getMoveCount(), is(1));

        game.performUnitActionAt(p);

        assertThat(testUnit.getDefensiveStrength(), is(6));
        assertThat(testUnit.getMoveCount(), is(0));
    }

    @Test
    public void canActivateSettlerPower(){
        Position p = new Position(4, 3);
        Unit testUnit = game.getUnitAt(p);

        assertThat(game.getCityAt(p), is(nullValue()));
        assertThat(testUnit, is(notNullValue()));

        game.performUnitActionAt(p);

        City testCity = game.getCityAt(p);

        assertThat(testCity, is(notNullValue()));
        assertThat(game.getUnitAt(p), is(nullValue()));

        assertThat(testCity.getOwner(), is(Player.RED));

    }
}
