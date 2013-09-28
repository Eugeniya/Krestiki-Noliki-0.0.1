package org.hexlet.xo.player;

import org.hexlet.xo.field.CellInfo;
import org.hexlet.xo.field.CellState;

/**
 * Date: 18.09.13
 * Time: 23:32
 */
public class Human extends Player{

    //private final CellState figure;

    public Human(CellState figure) {
        super("Human",figure);
    }

    public Human(String name, CellState figure) {
        super(name, figure);
    }

    @Override
    public CellInfo onMove(int x, int y) {
        return new CellInfo(x, y, getFigure());  //To change body of implemented methods use File | Settings | File Templates.
    }
}
