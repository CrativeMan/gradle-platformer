package io.crative.platformer.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import io.crative.platformer.gamestates.GameState;
import io.crative.platformer.utilz.LoadSave;
import static io.crative.platformer.utilz.Constants.UI.Buttons.*;

public class MenuButton {
    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = B_WIDTH / 2;
    private GameState state;
    private BufferedImage[] imgs;
    private boolean mouseOver = false, mousePressed = false;
    private Rectangle bounds;

    public MenuButton(int xPos, int yPos, int rowIndex, GameState state){
        this.yPos = yPos;
        this.xPos = xPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImages();
        initBounds();
    }
    
    private void loadImages(){
        imgs = new BufferedImage[3];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTON_ATLAS);

        for(int i = 0; i < imgs.length; i++){
            imgs[i] = temp.getSubimage(i*B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT); 
        }
    }

    public void initBounds(){
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
    }

    public void render(Graphics g) {
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);  
    }

    public void update() {
        index = 0;
        if(mouseOver)
            index = 1;
        if(mousePressed)
            index = 2;
    }
    
    public void switchGamestate(){
        GameState.state = state;
    }

    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }

    // Getter and setter
    public void setMouseOver(boolean mouseOver){
        this.mouseOver = mouseOver;
    }
    public void setMousePressed(boolean mousePressed){
        this.mousePressed = mousePressed;
    }
    public boolean isMousePressed(){
        return mousePressed;
    }
    public Rectangle getBounds(){
        return bounds;
    }
}
