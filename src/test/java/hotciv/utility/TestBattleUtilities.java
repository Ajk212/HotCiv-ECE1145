package hotciv.utility;

import hotciv.framework.*;
import hotciv.stub.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestBattleUtilities {
  Game game;

  @Before
  public void setUp() {
    game = new GameStubForBattleTesting();
  }

  @Test
  public void shouldGiveCorrectTerrainFactors() {
    // plains have multiplier 1
    assertThat(BattleUtilities.getTerrainFactor(game, new Position(0,1)), is(1));
    // hills have multiplier 2
    assertThat(BattleUtilities.getTerrainFactor(game, new Position(1,0)), is(2));
    // forest have multiplier 2
    assertThat(BattleUtilities.getTerrainFactor(game, new Position(0,0)), is(2));
    // cities have multiplier 3
    assertThat(BattleUtilities.getTerrainFactor(game, new Position(1,1)), is(3));
  }

  @Test
  public void shouldGiveSum1ForBlueAtP5_5() {
    assertThat(BattleUtilities.getFriendlySupport( game, new Position(5,5), Player.BLUE), is(+1));
  }

  @Test
  public void shouldGiveSum0ForBlueAtP2_4() {
    assertThat(BattleUtilities.getFriendlySupport( game, new Position(2,4), Player.BLUE), is(+0));
  }

  @Test
  public void shouldGiveSum2ForRedAtP2_4() {
    assertThat(BattleUtilities.getFriendlySupport( game, new Position(2,4), Player.RED), is(+2));
  }

  @Test
  public void shouldGiveSum3ForRedAtP2_2() {
    assertThat(BattleUtilities.getFriendlySupport( game, new Position(2,2), Player.RED), is(+3));
  }

  @Test
  public void shouldCalculateCombinedAttackStrength() {
    // Red archer at (2,3): attack=2, support=+2 (units at 3,2 and 3,3), terrain=1 (plains)
    // Combined = (2 + 2) * 1 = 4
    assertThat(BattleUtilities.getCombinedAttackStrength(game, new Position(2,3)), is(4));

    // Red archer at (3,3): attack=2, support=+2 (units at 2,3 and 3,2), terrain=1 (plains)
    // Combined = (2 + 2) * 1 = 4
    assertThat(BattleUtilities.getCombinedAttackStrength(game, new Position(3,3)), is(4));
  }

  @Test
  public void shouldCalculateCombinedDefenseStrength() {
    // Red archer at (2,3): defense=3, support=+2, terrain=1 (plains)
    // Combined = (3 + 2) * 1 = 5
    assertThat(BattleUtilities.getCombinedDefenseStrength(game, new Position(2,3)), is(5));

    // Red archer at (3,3): defense=3, support=+2, terrain=1 (plains)
    // Combined = (3 + 2) * 1 = 5
    assertThat(BattleUtilities.getCombinedDefenseStrength(game, new Position(3,3)), is(5));
  }

  @Test
  public void shouldCalculateCombinedStrengthWithTerrainBonus() {
    // Blue archer at (5,5) on forest: attack=2, support=+1, terrain=2
    // Combined attack = (2 + 1) * 2 = 6
    // Combined defense = (3 + 1) * 2 = 8
    Game gameWithTerrain = new GameStubWithTerrain();
    assertThat(BattleUtilities.getCombinedAttackStrength(gameWithTerrain, new Position(5,5)), is(6));
    assertThat(BattleUtilities.getCombinedDefenseStrength(gameWithTerrain, new Position(5,5)), is(8));
  }
}
