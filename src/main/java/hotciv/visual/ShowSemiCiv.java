package hotciv.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.standard.*;
import hotciv.strategy.semi.*;

public class ShowSemiCiv
{
  public static void main(String[] args)
  {
    // create SemiCiv game instance
    Game game = new GameImpl(new SemiCivFactory());

    DrawingEditor editor =
      new MiniDrawApplication( "SemiCiv", new HotCivFactory4(game));
    editor.open();

    // set CompositionTool
    editor.setTool( new CompositionTool(editor, game) );
  }
}

