package io.crative.platformer.main;

import io.crative.platformer.inputs.KeyboardInputs;
import io.crative.platformer.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

import static io.crative.platformer.main.Game.GAME_WIDTH;
import static io.crative.platformer.main.Game.GAME_HEIGHT;

public class GamePanel extends JPanel {
    private final Game game;

    public GamePanel(Game game) {
        MouseInputs mouseInputs = new MouseInputs(this);
        this.game = game;

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("Size: " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.render(g);
    }

    public Game getGame() {
        return game;
    }
}
