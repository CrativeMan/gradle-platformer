package io.crative.platformer.entities;

import io.crative.platformer.animation.PlayerAnimations;
import io.crative.platformer.gamestates.GameState;
import io.crative.platformer.main.Game;

import java.awt.*;

import static io.crative.platformer.utilz.HelpMethods.*;

public class Player extends Entity {
    private final PlayerAnimations animator;
    private boolean left, right, jump, attacking;
    private int[][] lvlData;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;

    //* Jumping and gravity
    private float airSpeed = 0f;
    private float gravity = 0.02f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;

    

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        animator = new PlayerAnimations();
        initHitbox(x, y, 20*Game.SCALE, 28*Game.SCALE);
    }

    public void update(){
        updatePos();
        animator.updateAnimationTick();
        animator.setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(animator.getAnimations()[animator.getPlayerAction()][animator.getAniIndex()],
                (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset),  width, height, null);
        drawHitbox(g);
    }

    private void updatePos() {
        animator.setMoving(false);
        if(jump)
            jump();
        if(!left && !right && !inAir)
            return;

        float xSpeed = 0;

        float playerSpeed = 1.6f * Game.SCALE;
        if(left)
            xSpeed -= playerSpeed;
        if(right)
            xSpeed += playerSpeed;
        if(!inAir) {
            if(!_isEntityOnFloor(hitbox, lvlData)) {
                inAir = true;
                animator.setInAir(true);
            }
        }

        if(inAir){
            if(_canMoveHere(hitbox.x, hitbox.y+airSpeed, hitbox.width, hitbox.height, lvlData)){
                hitbox.y += airSpeed;
                animator.setAirSpeed(airSpeed += gravity);
                airSpeed += gravity;
                updateXPos(xSpeed);
            } else {
                hitbox.y = _getEntityYPosHitFloorCeiling(hitbox, airSpeed);
                if(airSpeed > 0)
                    resetInAir();
                else {
                    animator.setAirSpeed(fallSpeedAfterCollision);
                    airSpeed = fallSpeedAfterCollision;
                }
                updateXPos(xSpeed);
            }
        } else
            updateXPos(xSpeed);

        animator.setMoving(true);
    }

    private void jump() {
        if(inAir)
            return;
        inAir = true;
        animator.setInAir(true);
        animator.setAirSpeed(jumpSpeed);
        airSpeed = jumpSpeed;
    }

    private void updateXPos(float xSpeed) {
        if(_canMoveHere(hitbox.x+xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)){
            hitbox.x += xSpeed;
        } else {
            hitbox.x = _getEntityXPosNextToWall(hitbox, xSpeed);
        }
    }

    public void loadLevelData(int[][] lvlData){
        this.lvlData = lvlData;
    }

    public void resetDirBooleans() {
        left = right = false;
    }

    private void resetInAir() {
        inAir = false;
        animator.setInAir(false);
        animator.setAirSpeed(0);
        airSpeed = 0;
    }

    public void switchToMenu() {
        resetDirBooleans();
        setJump(false);
        GameState.state = GameState.MENU;
    }

    // Getter and setter
    public void setRight(boolean right) {this.right = right;}
    public void setLeft(boolean left) {this.left = left;}
    public void setJump(boolean jump) {this.jump = jump;}
    public void setAttacking(boolean attacking) {this.attacking = attacking;}
    public PlayerAnimations getAnimator(){return animator;}
}
