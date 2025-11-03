package hotciv.standard;

import hotciv.framework.*;
import hotciv.strategy.semi.SemiCivFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestSemiCiv {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new SemiCivFactory());
    }

    @Test
    public void shouldHaveRedCityAtDeltaPosition() {
        // Delta layout has Red city at (8,12)
        Position p = new Position(8, 12);
        City city = game.getCityAt(p);
        assertThat(city, is(notNullValue()));
        assertThat(city.getOwner(), is(Player.RED));
    }

    @Test
    public void shouldHaveBlueCityAtDeltaPosition() {
        // Delta layout has Blue city at (4,5)
        Position p = new Position(4, 5);
        City city = game.getCityAt(p);
        assertThat(city, is(notNullValue()));
        assertThat(city.getOwner(), is(Player.BLUE));
    }

    @Test
    public void shouldHaveCorrectTerrainFromDeltaLayout() {
        assertThat(game.getTileAt(new Position(0, 0)).getTypeString(), is(GameConstants.OCEANS));
        assertThat(game.getTileAt(new Position(0, 4)).getTypeString(), is(GameConstants.PLAINS));
        assertThat(game.getTileAt(new Position(0, 5)).getTypeString(), is(GameConstants.MOUNTAINS));
        assertThat(game.getTileAt(new Position(1, 3)).getTypeString(), is(GameConstants.HILLS));
        assertThat(game.getTileAt(new Position(1, 9)).getTypeString(), is(GameConstants.FOREST));
        assertThat(game.getTileAt(new Position(3, 3)).getTypeString(), is(GameConstants.MOUNTAINS));
        assertThat(game.getTileAt(new Position(3, 10)).getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void shouldStartGameAtAge4000BC() {
        assertThat(game.getAge(), is(-4000));
    }

    @Test
    public void shouldAdvanceAgeUsingBetaAlgorithm() {
        // -4000 to -3900
        assertThat(game.getAge(), is(-4000));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(-3900));

        // 3900BC - 100BC
        for (int i = 0; i < 38; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(-100));

        // 100BC - 1BC
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(-1));

        // 1BC - 1AD
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(1));

        // 1AD - 50AD
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(50));

        // 50AD - 1750
        for (int i = 0; i < 34; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(1750));

        // 1750 - 1900
        for (int i = 0; i < 6; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(1900));

        // 1900 - 1970
        for (int i = 0; i < 14; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(1970));

        // 1970+
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(1971));

        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(1972));
    }


    @Test
    public void settlerCanBuildCity() {
        GameImpl gameImpl = (GameImpl) game;

        // place a settler
        Position settlerPos = new Position(5, 5);
        gameImpl.unitLoc.put(settlerPos, new UnitImpl(GameConstants.SETTLER, Player.RED));

        // verify no city exists
        assertThat(game.getCityAt(settlerPos), is(nullValue()));

        // perform settler action
        game.performUnitActionAt(settlerPos);

        // verify city is made and settler is removed
        City newCity = game.getCityAt(settlerPos);
        assertThat(newCity, is(notNullValue()));
        assertThat(newCity.getOwner(), is(Player.RED));
        assertThat(game.getUnitAt(settlerPos), is(nullValue()));
    }

    @Test
    public void archerCanFortify() {
        GameImpl gameImpl = (GameImpl) game;

        // place an archer
        Position archerPos = new Position(6, 6);
        UnitImpl archer = new UnitImpl(GameConstants.ARCHER, Player.RED);
        gameImpl.unitLoc.put(archerPos, archer);

        int originalDefense = archer.getDefensiveStrength();
        assertThat(archer.getFortified(), is(false));

        // perform fortify action
        game.performUnitActionAt(archerPos);

        // verify archer is fortified
        assertThat(archer.getFortified(), is(true));
        assertThat(archer.getDefensiveStrength(), is(originalDefense * 2));
        assertThat(archer.getMoveCount(), is(0));
    }

    @Test
    public void shouldStartWithNoWinner() {
        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void shouldHaveRedWinAfter3SuccessfulAttacks() {
        GameImpl gameImpl = (GameImpl) game;

        // simulate 3 attacks won by RED
        gameImpl.incrementAttacksWon(Player.RED);
        gameImpl.incrementAttacksWon(Player.RED);
        gameImpl.incrementAttacksWon(Player.RED);

        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void shouldHaveBlueWinAfter3SuccessfulAttacks() {
        GameImpl gameImpl = (GameImpl) game;

        // simulate 3 attacks won by BLUE
        gameImpl.incrementAttacksWon(Player.BLUE);
        gameImpl.incrementAttacksWon(Player.BLUE);
        gameImpl.incrementAttacksWon(Player.BLUE);

        assertThat(game.getWinner(), is(Player.BLUE));
    }

    @Test
    public void shouldTrackAttackWinsForBothPlayers() {
        GameImpl gameImpl = (GameImpl) game;

        // RED wins 2 attacks
        gameImpl.incrementAttacksWon(Player.RED);
        gameImpl.incrementAttacksWon(Player.RED);

        // BLUE wins 1 attack
        gameImpl.incrementAttacksWon(Player.BLUE);

        assertThat(game.getWinner(), is(nullValue()));
        assertThat(gameImpl.getAttacksWon(Player.RED), is(2));
        assertThat(gameImpl.getAttacksWon(Player.BLUE), is(1));
    }
}
