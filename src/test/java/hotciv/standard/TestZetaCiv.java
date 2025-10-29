package hotciv.standard;

import hotciv.framework.*;
import hotciv.strategy.zeta.ZetaCivFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestZetaCiv {
    private Game game;
    private GameImpl gameImpl;

    @Before
    public void setUp() {
        game = new GameImpl(new ZetaCivFactory());
        gameImpl = (GameImpl) game;
    }

    @Test
    public void shouldUseBetaRulesInRound10() {
        // advance to round 10
        for (int i = 0; i < 10; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(gameImpl.getRoundNumber(), is(10));

        // should not have winner yet
        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void shouldUseBetaRulesInRound20() {
        // advance to round 20
        for (int i = 0; i < 20; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(gameImpl.getRoundNumber(), is(20));

        // should not have winner yet
        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void shouldDoWinnerByConquestBeforeRound21() {
        // advance to round 15
        for (int i = 0; i < 15; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        // RED captures BLUE city
        Position blueCityPos = new Position(4, 1);
        City blueCity = game.getCityAt(blueCityPos);
        if (blueCity != null) {
            ((CityImpl) blueCity).owner = Player.RED;
        }

        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void shouldUseEpsilonRulesAfterRound20() {
        // advance to round 21
        for (int i = 0; i < 21; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(gameImpl.getRoundNumber(), is(21));

        // simulate 3 attacks won by RED
        gameImpl.incrementAttacksWon(Player.RED);
        gameImpl.incrementAttacksWon(Player.RED);
        gameImpl.incrementAttacksWon(Player.RED);

        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void shouldNotWinBySuccessfulAttacksBeforeRound21() {
        GameImpl gameImpl = (GameImpl) game;

        // advance to round 5
        for (int i = 0; i < 5; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(gameImpl.getRoundNumber(), is(5));

        // simulate 3 attacks won by RED
        gameImpl.incrementAttacksWon(Player.RED);
        gameImpl.incrementAttacksWon(Player.RED);
        gameImpl.incrementAttacksWon(Player.RED);

        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void shouldTransitionFromBetaToEpsilonAtRound21() {
        // advance to round 20
        for (int i = 0; i < 20; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(gameImpl.getRoundNumber(), is(20));
        assertThat(game.getWinner(), is(nullValue()));

        // advance to round 21
        game.endOfTurn();
        game.endOfTurn();

        assertThat(gameImpl.getRoundNumber(), is(21));

        // simulate 3 attacks won by RED
        gameImpl.incrementAttacksWon(Player.RED);
        gameImpl.incrementAttacksWon(Player.RED);
        gameImpl.incrementAttacksWon(Player.RED);

        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void shouldCountAttacksOnlyAfterRound20() {
        // advance to round 20
        for (int i = 0; i < 20; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(gameImpl.getRoundNumber(), is(20));


        // advance to round 21
        game.endOfTurn();
        game.endOfTurn();

        assertThat(gameImpl.getRoundNumber(), is(21));

        //TODO
    }
}
