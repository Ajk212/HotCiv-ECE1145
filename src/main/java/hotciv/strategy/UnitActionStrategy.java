package hotciv.strategy;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public interface UnitActionStrategy {
    void performUnitActionAt(Position p, GameImpl game);
}