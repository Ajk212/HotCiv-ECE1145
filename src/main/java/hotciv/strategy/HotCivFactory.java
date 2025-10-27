package hotciv.strategy;

public interface HotCivFactory {
    AgingStrategy createAgingStrategy();
    UnitActionStrategy createUnitActionStrategy();
    WinnerStrategy createWinnerStrategy();
    WorldLayoutStrategy createWorldLayoutStrategy();
    BattleStrategy createBattleStrategy();
    DieRollingStrategy createDieRollingStrategy();
}
