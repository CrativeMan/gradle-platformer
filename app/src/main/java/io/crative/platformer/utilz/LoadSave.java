package io.crative.platformer.utilz;

import io.crative.platformer.main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";
    public static final String MENU_BUTTON_ATLAS = "button_atlas.png";
    public static final String MENU_BACKGROUND = "menu_background.png";
    public static final String PAUSE_BACKGROUND = "pause_menu.png";
    public static final String SOUND_BUTTON_ATLAS = "sound_button.png";
    public static final String URM_BUTTON_ATLAS = "urm_buttons.png";
    public static final String VOLUME_BUTTON_ATLAS = "volume_buttons.png";


    public static BufferedImage GetSpriteAtlas(String fileName){
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public static int[][] GetLevelData(){
        int[][] lvlData= new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);

        for(int j = 0; j < img.getHeight(); j++){
            for(int k = 0; k < img.getWidth(); k++){
                Color c = new Color(img.getRGB(k, j));
                int value = c.getRed();
                if(value >= 48)
                    value = 0;
                lvlData[j][k] = value;
            }
        }
        return lvlData;
    }
}
