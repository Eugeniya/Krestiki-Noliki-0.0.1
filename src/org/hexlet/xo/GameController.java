package org.hexlet.xo;

import org.hexlet.xo.exception.CellNotAvailableException;
import org.hexlet.xo.exception.InvalidCellCoordinatesException;
import org.hexlet.xo.field.CellInfo;
import org.hexlet.xo.AI.*;
import org.hexlet.xo.field.*;
import org.hexlet.xo.player.*;

public class GameController {

    private static final int DEFAULT_FIELD_SIZE = 3;

    private Player playerOne;

    private Player playerTwo;

    private Player currentPlayer;

    private FieldController fieldController;
    private GameStateListener listener;

    public GameController() {
        fieldController = new FieldController();
    }

    private boolean isAvailable(CellInfo cellInfo) throws InvalidCellCoordinatesException {
        return fieldController.isAvailable(cellInfo);
    }

    private boolean figureChecking(Player playerOne, Player playerTwo) {
        if (playerOne.getFigure() == playerTwo.getFigure() || playerOne.getFigure() == CellState.EMPTY
                || playerTwo.getFigure() == CellState.EMPTY) {
            return true;
        }
        return false;
    }

    public int getSize() {
        return fieldController.getFieldSize();
    }

    public Field getField() {
        return fieldController.getField();
    }

    public Player currentPlayer() {

        return currentPlayer;
    }

    public void nextTurn(CellInfo cellInfo) throws InvalidCellCoordinatesException, CellNotAvailableException {
        isAvailable(cellInfo);
        fieldController.setCell(cellInfo);
        if(fieldController.checkWin(cellInfo.figure))
            listener.onWin(currentPlayer);
        else if(fieldController.getEmptyCellsCount() == 0)
            listener.onDraw();
        switchPlayers();
    }

   /* public void abortGame() {
        fieldController.
    }*/

    public void startGame(Player playerOne, Player playerTwo, GameStateListener listener) {
        startGame(playerOne,playerTwo,DEFAULT_FIELD_SIZE,listener);

    }

    public void startGame(Player playerOne, Player playerTwo, int size, GameStateListener listener) {
        if (!figureChecking(playerOne, playerTwo)) {}//stub
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.listener = listener;
        switchPlayers();
        fieldController.createField(size);
    }

    private void switchPlayers() {
        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
    }

}