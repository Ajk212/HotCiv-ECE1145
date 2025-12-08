package hotciv.view;

import hotciv.framework.Game;
import minidraw.standard.NullTool;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EndOfTurnTool extends NullTool
{
    private Game game;

    public EndOfTurnTool(Game game)
    {
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y)
    {
        // define rectangle around shield pos for clicking
        Rectangle shieldArea = new Rectangle(
                GfxConstants.TURN_SHIELD_X,
                GfxConstants.TURN_SHIELD_Y,
                40,
                40
        );

        if (shieldArea.contains(x, y))
        {
            game.endOfTurn();
        }
    }
}

