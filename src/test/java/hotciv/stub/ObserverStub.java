package hotciv.stub;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class ObserverStub implements GameObserver {

    public boolean worldChangedCalled = false;
    public boolean turnEndCalled = false;
    public boolean tileFocusChangeCalled = false;


    public void worldChangedAt(Position pos){
        worldChangedCalled = true;
    }


    public void turnEnds(Player nextPlayer, int age){
        turnEndCalled = true;
    }


    public void tileFocusChangedAt(Position position){
        tileFocusChangeCalled = true;
    }
}
