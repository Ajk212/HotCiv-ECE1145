package hotciv.view;

import hotciv.framework.*;

import java.awt.event.MouseEvent;

import minidraw.standard.NullTool;
import minidraw.framework.*;

public class UnitMoveTool extends NullTool {
    private DrawingEditor editor;
    private Game game;
    private Figure draggedFigure;
    private Position originalPosition;
    private int lastX, lastY;

    public UnitMoveTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        // find the figure at mouse position
        Drawing drawing = editor.drawing();
        draggedFigure = drawing.findFigure(x, y);

        // only allow moving of UnitFigures
        if (draggedFigure != null && draggedFigure instanceof UnitFigure) {
            // store the original position for if mvoe is invalid
            originalPosition = GfxConstants.getPositionFromXY(x, y);
            lastX = x;
            lastY = y;
        } else {
            // not a unit figure
            draggedFigure = null;
        }
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        // drag the figure
        if (draggedFigure != null) {
            draggedFigure.moveBy(x - lastX, y - lastY);
            lastX = x;
            lastY = y;
        }
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        if (draggedFigure != null && draggedFigure instanceof UnitFigure) {
            // calculate the new position from mouse coordinates
            Position newPosition = GfxConstants.getPositionFromXY(e.getX(), e.getY());

            // try to move the unit in the game
            boolean moveSuccessful = game.moveUnit(originalPosition, newPosition);

            if (!moveSuccessful) {
                // move was invalid, move the figure back to original position
                int originalX = GfxConstants.getXFromColumn(originalPosition.getColumn());
                int originalY = GfxConstants.getYFromRow(originalPosition.getRow());
                int currentX = draggedFigure.displayBox().x;
                int currentY = draggedFigure.displayBox().y;

                draggedFigure.moveBy(originalX - currentX, originalY - currentY);
            }

            // clear the dragged figure
            draggedFigure = null;
            originalPosition = null;
        }
    }
}
