package org.hexlet.xo.UI;

import org.hexlet.xo.*;
import org.hexlet.xo.player.Player;

public class Console implements GameStateListener{
    @Override
    public void onWin(Player player) {
        System.out.println(player.getName() + " is winner" );
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onDraw() {
        System.out.println("Draw");
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
