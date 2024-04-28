package io.crative.platformer.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import io.crative.platformer.utilz.LoadSave;
import static io.crative.platformer.utilz.Constants.UI.PauseButtons.*;

public class SoundButton extends PauseButtons{
    private BufferedImage[][] soundImgs;
    private boolean mouseOver, mousePressed;
    private boolean muted;

    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadSoundImgs();
    }

    private void loadSoundImgs(){
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SOUND_BUTTON_ATLAS);
        soundImgs = new BufferedImage[2][3];
        for(int j = 0; j < soundImgs.length; j ++)
            for(int i = 0; i < soundImgs[j].length; i++)
                soundImgs[j][i] = temp.getSubimage(i * SB_SIZE_DEFAULT, j * SB_SIZE_DEFAULT, SB_SIZE_DEFAULT, SB_SIZE_DEFAULT);
    }

    public void update(){
    }

    public void render(Graphics g){
        g.drawImage(soundImgs[0][0], x, y, width, height, null);   
    }
}
