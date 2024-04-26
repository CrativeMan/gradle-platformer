package io.crative.platformer.levels;

import io.crative.platformer.main.Game;
import io.crative.platformer.utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static io.crative.platformer.main.Game.TILES_SIZE;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private Level level1;

    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprites();
        level1 = new Level(LoadSave.GetLevelData());
    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];
        for(int j = 0; j < 4; j++){
            for(int i = 0; i < 12; i ++) {
                int index = j*12+i;
                levelSprite[index] = img.getSubimage(i*32, j*32, 32, 32);
            }
        }
    }

    public void draw(Graphics g) {
        for(int j = 0; j < Game.TILES_IN_HEIGHT; j++){
            for(int k = 0; k < Game.TILES_IN_WIDTH; k++){
                int index = level1.getSpriteIndex(k, j);
                g.drawImage(levelSprite[index], TILES_SIZE*k, TILES_SIZE*j, TILES_SIZE, TILES_SIZE, null);
            }
        }
    }

    public void update() {

    }

    public Level getCurrentLevel() {
        return level1;
    }

}
