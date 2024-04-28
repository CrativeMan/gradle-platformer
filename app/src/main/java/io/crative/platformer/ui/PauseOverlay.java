package io.crative.platformer.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import io.crative.platformer.main.Game;
import io.crative.platformer.utilz.LoadSave;
import static io.crative.platformer.utilz.Constants.UI.PauseButtons.SB_SIZE;

public class PauseOverlay {
    private BufferedImage bgImg;
    private int bgX, bgY, bgWidth, bgHeight;
    private SoundButton musicButton, sfxButton;

    public PauseOverlay(){
        loadBackground();
        createSoundButtons();
    }

    private void loadBackground(){
        bgImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        bgWidth = (int) (bgImg.getWidth() * Game.SCALE);
        bgHeight = (int) (bgImg.getHeight() * Game.SCALE);
        bgX = (int) (Game.GAME_WIDTH / 2 - bgWidth / 2);
        bgY = (int) (33 * Game.SCALE);
    }

    private void createSoundButtons(){
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (146 * Game.SCALE);
        int sfxY = (int) (195 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, SB_SIZE, SB_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SB_SIZE, SB_SIZE);
    }

    public void update() {
    }

    public void render(Graphics g){
        // Background
        g.drawImage(bgImg, bgX, bgY, bgWidth, bgHeight, null);
        
        // Sound buttons
        musicButton.render(g);
        sfxButton.render(g);
    }

    // Input handling
    public void mouseDragged(){}
    public void mousePressed(){}
    public void mouseReleased(){}
    public void mouseMoved(){}
}


