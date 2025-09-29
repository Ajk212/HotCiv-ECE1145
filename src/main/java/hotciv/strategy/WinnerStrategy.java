package hotciv.strategy;

import hotciv.framework.Game;
import hotciv.framework.Player;

public interface WinnerStrategy {
    Player getWinner(Game game);
}