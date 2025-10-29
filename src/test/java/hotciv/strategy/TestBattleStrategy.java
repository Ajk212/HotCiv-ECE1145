package hotciv.strategy;

import hotciv.framework.*;
import hotciv.strategy.alpha.AttackerAlwaysWinsStrategy;
import hotciv.strategy.epsilon.EpsilonBattleStrategy;
import hotciv.stub.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestBattleStrategy {
    private Game stubGame;
    private Position attackerPos;
    private Position defenderPos;

    @Before
    public void setUp() {
        stubGame = new SimpleBattleGameStub();
        attackerPos = new Position(0, 0);
        defenderPos = new Position(1, 1);
    }

    @Test
    public void attackerAlwaysWinsShouldAlwaysReturnTrue() {
        BattleStrategy strategy = new AttackerAlwaysWinsStrategy();

        assertThat(strategy.resolveAttack(stubGame, attackerPos, defenderPos), is(true));
        assertThat(strategy.resolveAttack(stubGame, attackerPos, defenderPos), is(true));
        assertThat(strategy.resolveAttack(stubGame, defenderPos, attackerPos), is(true));
    }

    @Test
    public void attackerShouldWinWithHigherStrength() {
        // Attacker: 10 * 6 = 60
        // Defender: 5 * 1 = 5
        // Attacker wins
        DieRollingStrategy dieRoller = new FixedDieRollingStrategy(6);
        BattleStrategy strategy = new EpsilonBattleStrategy(dieRoller);

        Game game = new EpsilonTestGameStub(10, 5);
        assertThat(strategy.resolveAttack(game, attackerPos, defenderPos), is(true));
    }

    @Test
    public void defenderShouldWinWithHigherStrength() {
        // Attacker: 5 * 1 = 5
        // Defender: 10 * 6 = 60
        // Defender wins
        DieRollingStrategy dieRoller = new TwoRollDieStub(1, 6);
        BattleStrategy strategy = new EpsilonBattleStrategy(dieRoller);

        Game game = new EpsilonTestGameStub(5, 10);
        assertThat(strategy.resolveAttack(game, attackerPos, defenderPos), is(false));
    }

    @Test
    public void equalStrengthShouldTie() {
        // Attacker: 10 * 3 = 30
        // Defender: 10 * 3 = 30
        // Tie - defender wins
        DieRollingStrategy dieRoller = new FixedDieRollingStrategy(3);
        BattleStrategy strategy = new EpsilonBattleStrategy(dieRoller);

        Game game = new EpsilonTestGameStub(10, 10);
        assertThat(strategy.resolveAttack(game, attackerPos, defenderPos), is(false));
    }
}
