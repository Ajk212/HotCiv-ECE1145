package hotciv.view;

import hotciv.framework.Game;
import hotciv.framework.Position;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class SetFocusTool extends NullTool {
    private DrawingEditor editor;
    private Game game;
    private Position pos;

    public SetFocusTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y){
        pos = GfxConstants.getPositionFromXY(x, y);

        if (pos != null){
            game.setTileFocus(pos);
        }
    }


}
