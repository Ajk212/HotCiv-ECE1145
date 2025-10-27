package hotciv.strategy;

import hotciv.framework.Game;
import hotciv.framework.Position;

public interface BattleStrategy {
    boolean resolveAttack(Game game, Position attacker, Position defender);
}
