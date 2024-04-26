package io.crative.platformer.gamestates;

import io.crative.platformer.main.Game;

public class State {
    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public Game getGame(){
        return game;
    }
}
