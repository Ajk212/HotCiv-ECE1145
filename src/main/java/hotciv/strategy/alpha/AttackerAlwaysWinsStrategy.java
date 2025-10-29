package hotciv.strategy.alpha;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.strategy.BattleStrategy;

public class AttackerAlwaysWinsStrategy implements BattleStrategy {
    @Override
    public boolean resolveAttack(Game game, Position attacker, Position defender) {
        return true;
    }
}
