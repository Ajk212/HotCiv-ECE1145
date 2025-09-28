package hotciv.strategy.alpha;

import java.util.Objects;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.strategy.UnitActionStrategy;

public class AlphaUnitActionStrategy implements UnitActionStrategy {
    public void performUnitActionAt(Position p, Game game) {
        Unit unit = game.getUnitAt(p);
        if (unit != null) {
            if (Objects.equals(unit.getTypeString(), "archer")) {
                System.out.println("No associated ability");
                return;
            }
            else if (Objects.equals(unit.getTypeString(), "settler")) {
                return;
            }
            else if (Objects.equals(unit.getTypeString(), "legion")) {
                return;
            }
        }
        System.out.println("No unit at selected position");
    }
}