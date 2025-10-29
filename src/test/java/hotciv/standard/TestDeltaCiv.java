package hotciv.standard;

import hotciv.framework.*;
import hotciv.strategy.alpha.AlphaAgingStrategy;
import hotciv.strategy.alpha.AlphaUnitActionStrategy;
import hotciv.strategy.alpha.AlphaWinnerStrategy;
import hotciv.strategy.delta.DeltaCivFactory;
import hotciv.strategy.delta.DeltaWorldLayoutStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class TestDeltaCiv {
    private Game game;

    /** Fixture for DeltaCiv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new DeltaCivFactory());
    }

    @Test
    public void shouldHaveWorldSizeOf16x16() {
        assertThat(GameConstants.WORLDSIZE, is(16));
    }

    @Test
    public void shouldHaveRedCityAtPosition8_12() {
        assertThat(game, is(notNullValue()));
        Position p = new Position(8,12);
        assertThat(game.getCityAt(p), is(notNullValue()));

        City city = game.getCityAt(p);
        assertThat(city.getOwner(), is(Player.RED));
    }

    @Test
    public void shouldHaveBlueCityAtPosition4_5() {
        assertThat(game, is(notNullValue()));
        Position p = new Position(4,5);
        assertThat(game.getCityAt(p), is(notNullValue()));

        City city = game.getCityAt(p);
        assertThat(city.getOwner(), is(Player.BLUE));
    }

    // spot test 16 terrain types
    @Test
    public void shouldBeOceansAt0_0() {
        Position p = new Position(0,0);
        Tile tile = game.getTileAt(p);
        assertThat(tile.getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void shouldBeMountainsAt0_5() {
        Position p = new Position(0,5);
        Tile tile = game.getTileAt(p);
        assertThat(tile.getTypeString(), is(GameConstants.MOUNTAINS));
    }

    @Test
    public void shouldBePlainsAt0_7() {
        Position p = new Position(0,7);
        Tile tile = game.getTileAt(p);
        assertThat(tile.getTypeString(), is(GameConstants.PLAINS));
    }

    @Test
    public void shouldBeHillsAt1_3() {
        Position p = new Position(1,3);
        Tile tile = game.getTileAt(p);
        assertThat(tile.getTypeString(), is(GameConstants.HILLS));
    }

    @Test
    public void shouldBeForestAt1_10() {
        Position p = new Position(1,10);
        Tile tile = game.getTileAt(p);
        assertThat(tile.getTypeString(), is(GameConstants.FOREST));
    }

    @Test
    public void shouldBeMountainsAt2_6() {
        Position p = new Position(2,6);
        Tile tile = game.getTileAt(p);
        assertThat(tile.getTypeString(), is(GameConstants.MOUNTAINS));
    }

    @Test
    public void shouldBeOceansAt2_10() {
        Position p = new Position(2,10);
        Tile tile = game.getTileAt(p);
        assertThat(tile.getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void shouldBeMountainsAt3_4() {
        Position p = new Position(3,4);
        Tile tile = game.getTileAt(p);
        assertThat(tile.getTypeString(), is(GameConstants.MOUNTAINS));
    }

    @Test
    public void shouldBeHillsAt4_8() {
        Position p = new Position(4,8);
        Tile tile = game.getTileAt(p);
        assertThat(tile.getTypeString(), is(GameConstants.HILLS));
    }

    @Test
    public void shouldBeForestAt5_2() {
        Position p = new Position(5,2);
        Tile tile = game.getTileAt(p);
        assertThat(tile.getTypeString(), is(GameConstants.FOREST));
    }

    @Test
    public void shouldBeOceansAt6_6() {
        Position p = new Position(6,6);
        Tile tile = game.getTileAt(p);
        assertThat(tile.getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void shouldBeHillsAt7_10() {
        Position p = new Position(7,10);
        Tile tile = game.getTileAt(p);
        assertThat(tile.getTypeString(), is(GameConstants.HILLS));
    }

    @Test
    public void shouldBeMountainsAt7_13() {
        Position p = new Position(7,13);
        Tile tile = game.getTileAt(p);
        assertThat(tile.getTypeString(), is(GameConstants.MOUNTAINS));
    }

    @Test
    public void shouldBeForestAt8_13() {
        Position p = new Position(8,13);
        Tile tile = game.getTileAt(p);
        assertThat(tile.getTypeString(), is(GameConstants.FOREST));
    }

    @Test
    public void shouldBeForestAt9_1() {
        Position p = new Position(9,1);
        Tile tile = game.getTileAt(p);
        assertThat(tile.getTypeString(), is(GameConstants.FOREST));
    }

    @Test
    public void shouldBeOceansAt10_9() {
        Position p = new Position(10,9);
        Tile tile = game.getTileAt(p);
        assertThat(tile.getTypeString(), is(GameConstants.OCEANS));
    }
}
