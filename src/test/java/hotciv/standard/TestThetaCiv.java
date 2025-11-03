package hotciv.standard;

import hotciv.framework.*;
import hotciv.strategy.theta.ThetaCivFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TestThetaCiv {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new ThetaCivFactory());
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
    public void canProduceUFO() {
        Position p1 = new Position(1, 1); //position of Red City
        City redCity = game.getCityAt(p1); //get city located at p1

        game.changeProductionInCityAt(p1, "ufo");

        assertThat(redCity.getProduction(), is("ufo"));

        for(int i = 1; i <= 20; i++){
            game.endOfTurn();
        }

        assertThat(game.getUnitAt(p1), is(notNullValue()));

        Unit ufo = game.getUnitAt(p1);
        assertThat(ufo.getMoveCount(), is(2));
        assertThat(ufo.getDefensiveStrength(), is(8));
        assertThat(ufo.getAttackingStrength(), is(1));


    }

    @Test
    public void canAttackWithUFO() {
        Position p1 = new Position(1, 1); //position of Red City
        City redCity = game.getCityAt(p1); //get city located at p1

        game.changeProductionInCityAt(p1, "ufo");

        assertThat(redCity.getProduction(), is("ufo"));

        for(int i = 1; i <= 20; i++){
            game.endOfTurn();
        }

        assertThat(game.getUnitAt(p1), is(notNullValue()));

        Position p2 = new Position(1, 2);

        assertThat(game.moveUnit(p1, p2), is(true));

        p1 = new Position(1, 2);
        p2 = new Position(2, 2);

        assertThat(game.moveUnit(p1, p2), is(true));

        game.endOfTurn();
        game.endOfTurn();

        p1 = new Position(2, 2);
        p2 = new Position(3, 2);
        assertThat(game.moveUnit(p1, p2), is(true));

        Unit testUnit = game.getUnitAt(p2); //get unit located at battle

        assertThat(testUnit.getTypeString(), is("ufo"));
        assertThat(testUnit.getOwner(), is(Player.RED));
        assertThat(testUnit.getDefensiveStrength(), is(8));
        assertThat(testUnit.getAttackingStrength(), is(1));
    }


    @Test
    public void canTravelTwiceUFO(){
        Position p1 = new Position(1, 1); //position of Red City
        City redCity = game.getCityAt(p1); //get city located at p1

        game.changeProductionInCityAt(p1, "ufo");

        assertThat(redCity.getProduction(), is("ufo"));

        for(int i = 1; i <= 20; i++){
            game.endOfTurn();
        }

        assertThat(game.getUnitAt(p1), is(notNullValue()));

        Unit ufo = game.getUnitAt(p1);

        Position p2 = new Position(1, 2);

        assertThat(game.moveUnit(p1, p2), is(true));

        p1 = new Position(1, 2);
        p2 = new Position(2, 2);

        assertThat(game.moveUnit(p1, p2), is(true));

        game.endOfTurn();
        game.endOfTurn();

        p1 = new Position(2, 2);
        p2 = new Position(3, 2);
        assertThat(game.moveUnit(p1, p2), is(true));

        Unit testUnit = game.getUnitAt(p2);
        assertThat(testUnit.getTypeString(), is("ufo"));
    }

    @Test
    public void canFlyOverCityWithoutCapture(){
        Position p1 = new Position(1, 1); //position of Red City
        City redCity = game.getCityAt(p1); //get city located at p1

        assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.BLUE));

        game.changeProductionInCityAt(p1, "ufo");

        assertThat(redCity.getProduction(), is("ufo"));

        for(int i = 1; i <= 20; i++){
            game.endOfTurn();
        }

        assertThat(game.getUnitAt(p1), is(notNullValue()));

        Position p2 = new Position(2, 1);

        assertThat(game.moveUnit(p1, p2), is(true));

        p1 = new Position(2, 1);
        p2 = new Position(3, 1);

        assertThat(game.moveUnit(p1, p2), is(true));

        game.endOfTurn();
        game.endOfTurn();

        p1 = new Position(3, 1);
        p2 = new Position(4, 1);
        assertThat(game.moveUnit(p1, p2), is(true));

        Unit testUnit = game.getUnitAt(p2);

        assertThat(testUnit.getTypeString(), is("ufo"));
        assertThat(testUnit.getOwner(), is(Player.RED));
        assertThat(game.getCityAt(p2).getOwner(), is(Player.BLUE));
    }

    @Test
    public void canAbductFromCity(){
        Position p1 = new Position(1, 1); //position of Red City
        City redCity = game.getCityAt(p1); //get city located at p1
        assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.BLUE));

        game.changeProductionInCityAt(p1, "ufo");

        assertThat(redCity.getProduction(), is("ufo"));

        for(int i = 1; i <= 20; i++){
            game.endOfTurn();
        }

        assertThat(game.getUnitAt(p1), is(notNullValue()));

        Position p2 = new Position(2, 1);

        assertThat(game.moveUnit(p1, p2), is(true));

        p1 = new Position(2, 1);
        p2 = new Position(3, 1);

        assertThat(game.moveUnit(p1, p2), is(true));

        game.endOfTurn();
        game.endOfTurn();

        p1 = new Position(3, 1);
        p2 = new Position(4, 1);
        assertThat(game.moveUnit(p1, p2), is(true));

        Unit testUnit = game.getUnitAt(p2);

        assertThat(testUnit.getTypeString(), is("ufo"));
        assertThat(testUnit.getOwner(), is(Player.RED));
        assertThat(game.getCityAt(p2).getOwner(), is(Player.BLUE));
        assertThat(game.getCityAt(p2).getSize(), is(1));

        game.performUnitActionAt(p2);

       assertNull(game.getCityAt(p2));
       assertThat(game.getUnitAt(p2), is(notNullValue()));
    }

    @Test
    public void canChangeTerrainFromForestToPlains(){
        Position p1 = new Position(1, 1); //position of Red City
        Position p2 = new Position(1, 2);
        City redCity = game.getCityAt(p1); //get city located at p1

        GameImpl testGame = (GameImpl) game;
        testGame.tileLoc.put(p2, new TileImpl("forest"));

        assertThat(game.getTileAt(p2).getTypeString(), is("forest"));

        game.changeProductionInCityAt(p1, "ufo");

        assertThat(redCity.getProduction(), is("ufo"));

        for(int i = 1; i <= 20; i++){
            game.endOfTurn();
        }

        assertThat(game.getUnitAt(p1), is(notNullValue()));


        game.moveUnit(p1, p2);

        Unit testUnit = game.getUnitAt(p2);

        assertThat(testUnit.getTypeString(), is("ufo"));
        game.performUnitActionAt(p2);

        assertThat(game.getTileAt(p2).getTypeString(), is("Plains"));

    }

    @Test
    public void noEffectOnOtherTerrain(){
        Position p1 = new Position(1, 1); //position of Red City
        Position p2 = new Position(1, 0);
        Position p3 = new Position(0, 1);
        Position p4 = new Position(2, 2);
        City redCity = game.getCityAt(p1); //get city located at p1


        assertThat(game.getTileAt(p2).getTypeString(), is("ocean"));
        assertThat(game.getTileAt(p3).getTypeString(), is("hills"));
        assertThat(game.getTileAt(p4).getTypeString(), is("mountain"));

        game.changeProductionInCityAt(p1, "ufo");


        for(int i = 1; i <= 20; i++){
            game.endOfTurn();
        }

        assertThat(game.getUnitAt(p1), is(notNullValue()));


        game.moveUnit(p1, p2);

        Unit testUnit = game.getUnitAt(p2);

        assertThat(testUnit.getTypeString(), is("ufo"));
        game.performUnitActionAt(p2);

        assertThat(game.getTileAt(p2).getTypeString(), is("ocean"));

        game.moveUnit(p2, p1);

        game.endOfTurn();
        game.endOfTurn();

        game.moveUnit(p1, p3);
        game.performUnitActionAt(p3);
        assertThat(game.getTileAt(p3).getTypeString(), is("hills"));

        game.moveUnit(p3, p1);

        game.endOfTurn();
        game.endOfTurn();

        game.moveUnit(p1, new Position(2, 1));
        game.moveUnit(new Position(2,1), p4);

        game.performUnitActionAt(p4);

        assertThat(game.getTileAt(p4).getTypeString(), is("mountain"));

    }
}
