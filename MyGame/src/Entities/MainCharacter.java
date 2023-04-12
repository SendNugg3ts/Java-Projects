package Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


import static Utilz.Constant.PlayerConstants.*;

public class MainCharacter extends MainEntity{
    private BufferedImage[][] animations = new BufferedImage[11][26];
    private BufferedImage[] attackAnimation = new BufferedImage[26];
    private boolean mooving=false, placingBomb = false;
    private boolean up, down, left, right;
    private int playerAction = RUN;
    private int animationTick, animationIndex,animationSpeed = 10;
    private float playerSpeed = 2.0f;
    private double bombx, bomby;
    public MainCharacter(float x, float y){
        super(x,y);
        importImg();
    }
    public void update(){
        updatePos();
        updateAnimation();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][animationIndex],(int)x,(int) y,250,200,null);

        if (placingBomb) {
            attackAnimation[animationIndex] = animations[11][animationIndex];
            g.drawImage(attackAnimation[animationIndex], (int) bombx, (int) bomby, 250, 200, null);
            if(animationIndex == attackAnimation.length-1) {
                placingBomb = false;
                g.clipRect(100,100,250,200);
            }
            }
        }


    private void updatePos() {
        mooving=false;
            if(left && !right){
                x -= playerSpeed;
                mooving=true;
            }else if(right && !left){
                x += playerSpeed;
                mooving=true;
            }
            if(up && !down){
                y -= playerSpeed;
                mooving=true;
            }else if (!up && down){
                y+= playerSpeed;
                mooving=true;
            }
        }


    private void setAnimation() {
        if(mooving){
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
    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void PlacingBomb(boolean placingBomb){
        if(this.placingBomb){
        this.placingBomb = false;}
        else{this.placingBomb =placingBomb;
        bombx=x;
        bomby=y;}
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
}
