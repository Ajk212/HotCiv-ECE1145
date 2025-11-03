package hotciv.strategy;

public interface HotCivFactory {
    AgingStrategy createAgingStrategy();
    UnitClassStrategy createUnitClassStrategy();
    UnitActionStrategy createUnitActionStrategy();
    WinnerStrategy createWinnerStrategy();
    WorldLayoutStrategy createWorldLayoutStrategy();
    BattleStrategy createBattleStrategy();
    DieRollingStrategy createDieRollingStrategy();
}
