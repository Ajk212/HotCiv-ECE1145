package hotciv.strategy.epsilon;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.strategy.BattleStrategy;
import hotciv.strategy.DieRollingStrategy;
import hotciv.utility.BattleUtilities;

public class EpsilonBattleStrategy implements BattleStrategy {
    private DieRollingStrategy dieRoller;

    public EpsilonBattleStrategy(DieRollingStrategy dieRoller) {
        this.dieRoller = dieRoller;
    }

    @Override
    public boolean resolveAttack(Game game, Position attacker, Position defender) {
        int A = BattleUtilities.getCombinedAttackStrength(game, attacker);
        int D = BattleUtilities.getCombinedDefenseStrength(game, defender);
        int d1 = dieRoller.roll();
        int d2 = dieRoller.roll();

        return (A * d1) > (D * d2);
    }
}
