package hotciv.standard;

import hotciv.framework.*;
import hotciv.strategy.epsilon.EpsilonCivFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestEpsilonCiv {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new EpsilonCivFactory());
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

        // simulate 3 attacks won by RED
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
