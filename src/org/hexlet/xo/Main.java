package org.hexlet.xo;

import org.hexlet.xo.AI.Difficulty;
import org.hexlet.xo.UI.Console;
import org.hexlet.xo.exception.*;
import org.hexlet.xo.field.CellState;
import org.hexlet.xo.player.*;
import org.hexlet.xo.field.*;
import org.hexlet.xo.*;

import javax.print.attribute.standard.JobHoldUntil;

/**
 * Date: 18.09.13
 * Time: 23:08
 */
public class Main {
    public static void main(String[] args) {

        GameController game = new GameController();
        Human human = new Human(CellState.X);
        Computer computer = new Computer(CellState.O);
        GameStateListener listener = new Console();

        game.startGame(human, computer, listener);
        computer.createAI(game.getField(), Difficulty.EASY);
        try {
            game.nextTurn(game.currentPlayer().onMove(2, 0));
            game.nextTurn(computer.doMove(game.getField()));

            game.nextTurn(game.currentPlayer().onMove(2, 1));
            game.nextTurn(computer.doMove(game.getField()));

            game.nextTurn(game.currentPlayer().onMove(0, 0));
            game.nextTurn(computer.doMove(game.getField()));

            game.nextTurn(game.currentPlayer().onMove(1, 2));
            game.nextTurn(computer.doMove(game.getField()));


        }
        catch (CellNotAvailableException e)
        {
            System.out.println(e.getMessage());
        }
        catch (InvalidCellCoordinatesException e)
        {
            System.out.println(e.getMessage());
        }
        catch (AIException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
