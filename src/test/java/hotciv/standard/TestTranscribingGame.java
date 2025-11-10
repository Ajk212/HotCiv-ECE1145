package hotciv.standard;

import hotciv.framework.*;
import hotciv.strategy.alpha.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestTranscribingGame {
    private Game game;
    private TranscribingGame transcribingGame;

    // for capturing system output
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        Game baseGame = new GameImpl(new AlphaCivFactory());
        transcribingGame = new TranscribingGame(baseGame);
        game = transcribingGame;

        // redirect System.out to capture transcript output
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        // restore System.out
        System.setOut(originalOut);
    }

    @Test
    public void shouldTranscribeSimpleMovement() {
        // RED has an archer at (2,0) in AlphaCiv
        Position from = new Position(2, 0);
        Position to = new Position(2, 1);

        game.moveUnit(from, to);

        String output = outContent.toString();
        assertThat(output, containsString("RED moves Archer from (2,0) to (2,1)"));
    }

    @Test
    public void shouldTranscribeBattleAndMovement() {
        // RED has archer at (2,0), BLUE has legion at (3,2)
        game.endOfTurn(); // Switch to BLUE
        game.moveUnit(new Position(3, 2), new Position(2, 2)); // Move to (2,2)
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(2, 2), new Position(2, 1)); // Move to (2,1) adjacent to archer

        outContent.reset();

        game.endOfTurn();
        game.moveUnit(new Position(2, 0), new Position(2, 1));

        String output = outContent.toString();

        // should show the attack and result
        assertTrue(output.contains("RED's Archer at (2,0) attacks BLUE's Legion at (2,1)"));
        assertTrue(output.contains("wins"));
        assertTrue(output.contains("moves Archer from (2,0) to (2,1)"));
    }

    @Test
    public void shouldTranscribeCityCapture() {
        game.endOfTurn();

        // Move BLUE's legion from (3,2) toward RED's city
        game.moveUnit(new Position(3, 2), new Position(2, 2));
        game.endOfTurn();
        game.endOfTurn();

        outContent.reset();

        game.moveUnit(new Position(2, 2), new Position(1, 2));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(1, 2), new Position(1, 1));

        String output = outContent.toString();

        // should show city capture
        assertTrue(output.contains("BLUE captures city at (1,1)"));
    }

    @Test
    public void shouldTranscribeEndOfTurn() {
        game.endOfTurn();

        String output = outContent.toString();
        assertTrue(output.contains("RED ends turn"));
    }

    @Test
    public void shouldTranscribeChangeProduction() {
        Position cityPos = new Position(1, 1);
        game.changeProductionInCityAt(cityPos, GameConstants.LEGION);

        String output = outContent.toString();
        assertTrue(output.contains("RED changes production in city at (1,1) to Legion"));
    }

    @Test
    public void shouldTranscribeChangeWorkforceFocus() {
        Position cityPos = new Position(1, 1);
        game.changeWorkForceFocusInCityAt(cityPos, GameConstants.productionFocus);

        String output = outContent.toString();
        assertTrue(output.contains("RED changes workforce focus in city at (1,1) to production focus"));
    }

    @Test
    public void shouldTranscribePerformUnitAction() {
        Position unitPos = new Position(2, 0); // RED's archer
        game.performUnitActionAt(unitPos);

        String output = outContent.toString();
        assertThat(output, containsString("RED performs action with Archer at (2,0)"));
    }

    @Test
    public void shouldNotTranscribeWhenDisabled() {
        // disable transcription
        transcribingGame.setTranscriptionEnabled(false);

        assertFalse(transcribingGame.isTranscriptionEnabled());

        // perform an action
        game.moveUnit(new Position(2, 0), new Position(2, 1));

        String output = outContent.toString();

        // output should not contain transcript
        assertFalse(output.contains("RED moves Archer"));
    }

    @Test
    public void shouldReEnableTranscription() {
        // disable transcription
        transcribingGame.setTranscriptionEnabled(false);
        game.moveUnit(new Position(2, 0), new Position(2, 1));

        outContent.reset(); // clear output

        // re-enable transcription
        transcribingGame.setTranscriptionEnabled(true);
        game.endOfTurn();

        String output = outContent.toString();
        assertTrue(output.contains("RED ends turn"));
    }
}
