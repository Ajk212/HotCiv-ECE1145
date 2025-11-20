package hotciv.standard;

import hotciv.strategy.alpha.*;
import hotciv.framework.*;
import hotciv.stub.ObserverStub;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestGameObserver {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new AlphaCivFactory());
    }

    @Test
    public void observerUpdatedOnUnitMove(){
        ObserverStub observer = new ObserverStub();

        game.addObserver(observer);

        assertThat(observer.worldChangedCalled, is(false));

        Position p1 = new Position(2, 0);
        Position p2 = new Position(2, 1);

        game.moveUnit(p1, p2);

        assertThat(observer.worldChangedCalled, is(true));
    }

    @Test
    public void observerUpdatedOnTurnEnd(){
        ObserverStub observer = new ObserverStub();

        game.addObserver(observer);

        assertThat(observer.turnEndCalled, is(false));

        game.endOfTurn();

        assertThat(observer.turnEndCalled, is(true));
    }

    @Test
    public void observerUpdatedOnTileFocusChange(){
        ObserverStub observer = new ObserverStub();

        game.addObserver(observer);

        assertThat(observer.tileFocusChangeCalled, is(false));

        Position p1 = new Position(2, 0);

        game.setTileFocus(p1);

        assertThat(observer.tileFocusChangeCalled, is(true));
    }

}
