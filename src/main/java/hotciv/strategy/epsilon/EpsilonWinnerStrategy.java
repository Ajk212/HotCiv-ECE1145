package hotciv.strategy.epsilon;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;
import hotciv.strategy.WinnerStrategy;

/**
 * EpsilonCiv winner strategy: First player to win 3 attacks wins the game.
 */
public class EpsilonWinnerStrategy implements WinnerStrategy {
    @Override
    public Player getWinner(Game game) {
        GameImpl gameImpl = (GameImpl) game;

        if (gameImpl.getAttacksWon(Player.RED) >= 3) {
            return Player.RED;
        }
        if (gameImpl.getAttacksWon(Player.BLUE) >= 3) {
            return Player.BLUE;
        }

        return null;
    }

    @Override
    public void onAttackWon(Game game, Player attacker) {
        GameImpl gameImpl = (GameImpl) game;
        gameImpl.incrementAttacksWon(attacker);
    }
}
