package hotciv.strategy.alpha;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.strategy.WinnerStrategy;

public class AlphaWinnerStrategy implements WinnerStrategy {
    public Player getWinner(Game game) {
        if (game.getAge() >= -3000) {
            return Player.RED;
        }
        return null;
    }
}