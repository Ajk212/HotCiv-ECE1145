package hotciv.standard;

import hotciv.framework.*;

public class TranscribingGame implements Game {

    private Game decoratedGame;
    private boolean transcriptionEnabled;

    public TranscribingGame(Game game) {
        this.decoratedGame = game;
        this.transcriptionEnabled = true;
    }

    public void setTranscriptionEnabled(boolean enabled) {
        this.transcriptionEnabled = enabled;
    }

    public boolean isTranscriptionEnabled() {
        return transcriptionEnabled;
    }

    @Override
    public Tile getTileAt(Position p) {
        return decoratedGame.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return decoratedGame.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return decoratedGame.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        return decoratedGame.getPlayerInTurn();
    }

    @Override
    public Player getWinner() {
        return decoratedGame.getWinner();
    }

    @Override
    public int getAge() {
        return decoratedGame.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        if (!transcriptionEnabled) {
            return decoratedGame.moveUnit(from, to);
        }

        MoveContext context = captureMoveContext(from, to);
        boolean moveSuccessful = decoratedGame.moveUnit(from, to);

        if (moveSuccessful && context.movingUnit != null) {
            transcribeBattleIfOccurred(context, from, to);
            transcribeMovement(context, from, to);
            transcribeCityCaptureIfOccurred(context, to);
        }

        return moveSuccessful;
    }

    private MoveContext captureMoveContext(Position from, Position to) {
        Unit movingUnit = decoratedGame.getUnitAt(from);
        Unit destinationUnit = decoratedGame.getUnitAt(to);
        City destinationCity = decoratedGame.getCityAt(to);
        Player cityOwnerBeforeMove = (destinationCity != null) ? destinationCity.getOwner() : null;

        return new MoveContext(movingUnit, destinationUnit, cityOwnerBeforeMove);
    }

    private void transcribeBattleIfOccurred(MoveContext context, Position from, Position to) {
        if (!wasBattle(context)) {
            return;
        }

        Player attacker = context.movingUnit.getOwner();
        Player defender = context.destinationUnit.getOwner();
        String attackerType = capitalizeUnitType(context.movingUnit.getTypeString());
        String defenderType = capitalizeUnitType(context.destinationUnit.getTypeString());

        System.out.println(attacker + "'s " + attackerType + " at " + formatPosition(from) +
                         " attacks " + defender + "'s " + defenderType + " at " + formatPosition(to));

        boolean attackerWon = decoratedGame.getUnitAt(to) != null;
        if (attackerWon) {
            System.out.println(attacker + "'s " + attackerType + " wins");
        } else {
            System.out.println(defender + "'s " + defenderType + " wins");
        }
    }

    private void transcribeMovement(MoveContext context, Position from, Position to) {
        Player player = context.movingUnit.getOwner();
        String unitType = capitalizeUnitType(context.movingUnit.getTypeString());
        System.out.println(player + " moves " + unitType + " from " +
                         formatPosition(from) + " to " + formatPosition(to));
    }

    private void transcribeCityCaptureIfOccurred(MoveContext context, Position to) {
        City cityAfterMove = decoratedGame.getCityAt(to);
        if (cityAfterMove == null || context.cityOwnerBeforeMove == null) {
            return;
        }

        Player newOwner = cityAfterMove.getOwner();
        if (context.cityOwnerBeforeMove != newOwner) {
            System.out.println(newOwner + " captures city at " + formatPosition(to));
        }
    }

    private boolean wasBattle(MoveContext context) {
        return context.destinationUnit != null &&
               context.destinationUnit.getOwner() != context.movingUnit.getOwner();
    }

    private static class MoveContext {
        final Unit movingUnit;
        final Unit destinationUnit;
        final Player cityOwnerBeforeMove;

        MoveContext(Unit movingUnit, Unit destinationUnit, Player cityOwnerBeforeMove) {
            this.movingUnit = movingUnit;
            this.destinationUnit = destinationUnit;
            this.cityOwnerBeforeMove = cityOwnerBeforeMove;
        }
    }

    @Override
    public void endOfTurn() {
        if (transcriptionEnabled) {
            Player player = decoratedGame.getPlayerInTurn();
            System.out.println(player + " ends turn");
        }
        decoratedGame.endOfTurn();
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        if (transcriptionEnabled) {
            Player player = decoratedGame.getPlayerInTurn();
            String focusName = translateFocusName(balance);
            System.out.println(player + " changes workforce focus in city at " +
                             formatPosition(p) + " to " + focusName + " focus");
        }
        decoratedGame.changeWorkForceFocusInCityAt(p, balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        if (transcriptionEnabled) {
            Player player = decoratedGame.getPlayerInTurn();
            System.out.println(player + " changes production in city at " +
                             formatPosition(p) + " to " + capitalizeUnitType(unitType));
        }
        decoratedGame.changeProductionInCityAt(p, unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        if (transcriptionEnabled) {
            Player player = decoratedGame.getPlayerInTurn();
            Unit unit = decoratedGame.getUnitAt(p);
            if (unit != null) {
                System.out.println(player + " performs action with " +
                                 capitalizeUnitType(unit.getTypeString()) + " at " + formatPosition(p));
            }
        }
        decoratedGame.performUnitActionAt(p);
    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }

    private String formatPosition(Position p) {
        return "(" + p.getRow() + "," + p.getColumn() + ")";
    }

    private String capitalizeUnitType(String unitType) {
        if (unitType == null || unitType.isEmpty()) {
            return unitType;
        }
        return unitType.substring(0, 1).toUpperCase() + unitType.substring(1);
    }

    private String translateFocusName(String balance) {
        if (GameConstants.productionFocus.equals(balance)) {
            return "production";
        } else if (GameConstants.foodFocus.equals(balance)) {
            return "food";
        }
        return balance;
    }
}
