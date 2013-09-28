package org.hexlet.xo;

import org.hexlet.xo.player.Player;

public interface GameStateListener {
    void onWin(Player player);
    void onDraw();
}
