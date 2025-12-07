package hotciv.view;

import hotciv.framework.Game;
import hotciv.framework.Position;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class ActionTool extends NullTool {
    private Game game;
    private DrawingEditor editor;
    private Position fromPOS;


    public ActionTool(Game game, DrawingEditor editor) {
        this.game = game;
        this.editor = editor;
    }

    @Override
    public void mouseDown(MouseEvent event, int x, int y){
        fromPOS = GfxConstants.getPositionFromXY(x, y);
        game.setTileFocus(fromPOS);
        boolean isUnitAt = (game.getUnitAt(fromPOS) != null);

        if(event.isShiftDown() && isUnitAt){
            game.performUnitActionAt(fromPOS);
        }

    }
}
