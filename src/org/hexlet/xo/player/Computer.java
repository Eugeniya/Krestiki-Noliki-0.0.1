package org.hexlet.xo.player;

import org.hexlet.xo.AI.*;
import org.hexlet.xo.field.*;
import org.hexlet.xo.exception.*;

/**
 * Date: 18.09.13
 * Time: 23:32
 */
public class Computer extends Player{
    private  AI artificialIntelligence;

    //создаем инстанс АИ
    public Computer(CellState figure) {
        super("Computer", figure);
    }

    //создаем инстанс АИ
    public Computer(String name, CellState figure) {
        super(name, figure);
    }

    // TODO: refactor  (maybe needs to create factory to calculate move.. ala.. return MoveFactory.calculateNext(PlayerAI))
    //короче архитектура пока дерьмо.. надо переделывать, в бине делать такую бизнес логику не по фень-шую даже самому
    // индусскому
    //функция хода игрока (в нашем случае компьютера)
    public CellInfo doMove(Field gameField) throws AIException {
        //this.setGameField(gameField);
        //this.setCalculatingMoveSign(calculatingMoveSign);
        return artificialIntelligence.calculatingMove(gameField);

    }

    //добавялем АИ
    public void createAI(Field gameField, Difficulty difficulty) {
        this.artificialIntelligence = new AI(difficulty, getFigure());
    }

    @Override
    public CellInfo onMove(int x, int y) {
        return new CellInfo(x, y, getFigure());  //To change body of implemented methods use File | Settings | File Templates.
    }

}
