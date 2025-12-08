package hotciv.view;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;

import java.awt.*;
import java.awt.event.MouseEvent;

public class CompositionTool extends NullTool
{
    private DrawingEditor editor;
    private Game game;

    private Tool unitMoveTool;
    private Tool setFocusTool;
    private Tool actionTool;
    private Tool endOfTurnTool;

    private Tool activeTool;

    public CompositionTool(DrawingEditor editor, Game game)
    {
        this.editor = editor;
        this.game = game;

        // initialize all tools
        this.unitMoveTool = new UnitMoveTool(editor, game);
        this.setFocusTool = new SetFocusTool(editor, game);
        this.actionTool = new ActionTool(game, editor);
        this.endOfTurnTool = new EndOfTurnTool(game);
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y)
    {
        // determine which tool to use
        activeTool = null;

        // check if clicking on turn shield
        Rectangle shieldArea = new Rectangle(
                GfxConstants.TURN_SHIELD_X,
                GfxConstants.TURN_SHIELD_Y,
                40,
                40
        );
        if (shieldArea.contains(x, y))
        {
            activeTool = endOfTurnTool;
        }

        // check if shift-clicking on a unit
        if (activeTool == null && e.isShiftDown())
        {
            Position pos = GfxConstants.getPositionFromXY(x, y);
            Unit unit = game.getUnitAt(pos);
            if (unit != null)
            {
                activeTool = actionTool;
            }
        }

        // check if clicking on a unit figure for moving
        if (activeTool == null)
        {
            Figure figure = editor.drawing().findFigure(x, y);
            if (figure instanceof UnitFigure)
            {
                activeTool = unitMoveTool;
            }
        }

        // default to set focus tool
        if (activeTool == null)
        {
            activeTool = setFocusTool;
        }

        activeTool.mouseDown(e, x, y);
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y)
    {
        if (activeTool != null)
        {
            activeTool.mouseDrag(e, x, y);
        }
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y)
    {
        if (activeTool != null)
        {
            activeTool.mouseUp(e, x, y);
        }
    }
}

