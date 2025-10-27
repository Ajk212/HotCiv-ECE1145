package hotciv.strategy.zeta;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;
import hotciv.strategy.WinnerStrategy;
import hotciv.strategy.beta.BetaWinnerStrategy;
import hotciv.strategy.epsilon.EpsilonWinnerStrategy;

public class ZetaWinnerStrategy implements WinnerStrategy {
    private WinnerStrategy betaStrategy;
    private WinnerStrategy epsilonStrategy;

    public ZetaWinnerStrategy() {
        this.betaStrategy = new BetaWinnerStrategy();
        this.epsilonStrategy = new EpsilonWinnerStrategy();
    }

    @Override
    public Player getWinner(Game game) {
        GameImpl gameImpl = (GameImpl) game;

        // transition at round 20
        if (gameImpl.getRoundNumber() <= 20) {
            // use Beta rules
            return betaStrategy.getWinner(game);
        } else {
            // use Epsilon rules
            return epsilonStrategy.getWinner(game);
        }
    }
}
