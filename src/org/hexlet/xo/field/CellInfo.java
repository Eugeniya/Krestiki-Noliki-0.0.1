package org.hexlet.xo.field;

public class CellInfo {
    public final int X;
    public final int Y;
    public final CellState figure;

    public CellInfo(int x, int y, CellState cellState) {
        X = x;
        Y = y;
        figure = cellState;
    }


}
