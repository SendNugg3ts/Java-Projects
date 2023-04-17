package Entities;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static Utilz.Constant.PlayerConstants.*;
import static Utilz.helpMethods.*;
import static main.Game.SCALE;

public class MainCharacter extends MainEntity{
    private BufferedImage[][] animations = new BufferedImage[11][26];
    private BufferedImage[] attackAnimation = new BufferedImage[26];
    private boolean moving=false, placingBomb = false;
    private boolean up, down, left, right, jump;
    private int playerAction = RUN;
    private int animationTick, animationIndex,bombAnimationIndex = 0,animationSpeed = 20;
    private float playerSpeed = 2.0f;
    private double bombx, bomby;
    private int[][] lvlData;
    private float xDrawOffset =  10* SCALE;
    private float yDrawOffset =  13*SCALE;
    private float airSpeed = 0f;
    private float gravity = 0.04f * SCALE;
    private float jumpSpeed = -2.5f * SCALE;
    private float fallSpeedAfterCollision = 0.5f * SCALE;
    private boolean inAir = false;

    public MainCharacter(float x, float y, int width, int height){
        super(x,y, width, height);
        importImg();
        initHitbox(x,y,25*SCALE, 27*SCALE);
    }
    public void update(){
        updatePos();
        updateAnimation();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][animationIndex],(int)(hitbox.x - xDrawOffset) ,(int)(hitbox.y - yDrawOffset),width-35, height-35,null);
       // drawHitbox(g);

        if (placingBomb) {
            attackAnimation = animations[11];
            g.drawImage(attackAnimation[bombAnimationIndex], (int) bombx -35, (int) bomby- 40,width, height, null);
            if(bombAnimationIndex == 19) {
                g.clipRect(100,100,250,200);
                placingBomb = false;
            }
            }
        }


    private void updatePos() {
        moving = false;
        if (jump)
            jump();
        if (!left && !right && !inAir)
            return;

        float xSpeed = 0;

        if (left)
            xSpeed -= playerSpeed;

        if (right)
            xSpeed += playerSpeed;

        if (!inAir)
            if (!IsEntityOnFloor(hitbox, lvlData))
                inAir = true;

        if (inAir) {
            if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            } else {
                hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (airSpeed > 0)
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterCollision;
                updateXPos(xSpeed);
            }

        } else
            updateXPos(xSpeed);
        moving = true;
    }

    private void jump() {
        if(inAir){
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y,hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;}
        else{
            hitbox.x = GetEntityXPosNextToWall(hitbox,xSpeed);
        }

    }


    private void setAnimation() {
        if(moving){
            playerAction = RUN;
        }
        else{
            playerAction=IDLE;
        }
    }

    private void updateAnimation() {
        animationTick++;
        if(animationTick >= animationSpeed){
            animationTick =0;
            animationIndex++;
            if(animationIndex >= GetSpriteAmount(playerAction)){
                animationIndex = 0;
            }
            if (placingBomb) {
                bombAnimationIndex++;
                if (bombAnimationIndex >= 20) {
                    bombAnimationIndex = 0;
                }
            }
        }
    }
    private void importImg() {
    String[] folders = {"1-Idle", "2-Run", "3-JumpAnticipation","4-Jump","5-Fall","6-Ground","7-Hit","8-DeadHit","9-DeadGround","10-DoorIn","11-DoorOut","12-BOMB"}; // List of folder names
    int numImages = 26; // Number of images in each folder
    animations = new BufferedImage[folders.length+1][numImages];

    for (int i = 0; i < folders.length; i++) {
        for (int j = 0; j < numImages; j++) {
            String filePath = "/1-MainCharacter/" + folders[i] + "/" + (j+1) + ".png";
            InputStream is = getClass().getResourceAsStream(filePath);
            if (is == null){break;}
            try {
                animations[i][j] = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
    public void loadlvlData(int[][] lvlData){
        this.lvlData = lvlData;
        if(!IsEntityOnFloor(hitbox,lvlData)) {
        inAir=true;}
        }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void PlacingBomb(boolean placingBomb) {
        if (this.placingBomb == placingBomb) {
            return; // Bomb placement state already set, no need to update.
        }
        this.placingBomb = placingBomb;
        bombx = hitbox.x;
        bomby = hitbox.y;
        if (placingBomb) {
            bombAnimationIndex = 0;
        }
    }


    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setJump(boolean jump) {
        this.jump=jump;
    }
}
