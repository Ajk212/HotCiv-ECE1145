package hotciv.strategy;

import hotciv.framework.Game;
import hotciv.framework.Position;

public interface UnitActionStrategy {
    void performUnitActionAt(Position p, Game game);
}