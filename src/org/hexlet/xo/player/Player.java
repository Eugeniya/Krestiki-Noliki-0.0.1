package org.hexlet.xo.player;

import org.hexlet.xo.field.CellInfo;
import org.hexlet.xo.field.CellState;

public abstract class Player {
    private final String name;
    private final CellState figure;

    protected Player(String name, CellState figure) {
        this.name = name;
        this.figure = figure;
    }

    public CellState getFigure(){
        return this.figure;
    }

    public String getName(){
        return this.name;
    }

    public abstract CellInfo onMove (int x, int y);
 }
