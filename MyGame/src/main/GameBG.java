package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static Utilz.Constant.PlayerConstants.*;
import static Utilz.Constant.Directions.*;

public class GameBG extends JPanel {
    private boolean mooving=false;
    private int playerDirection = -1;
    private int playerAction = RUN;
    private int animationTick, animationIndex,animationSpeed = 20;
    private BufferedImage[][] animations = new BufferedImage[11][26];
    private MouseInputs mouseInputs;
    private float xDelta = 0, yDelta = 0;
    private KeyboardInputs keyboardinputs;

    public GameBG() {
        importImg();
        mouseInputs = new MouseInputs(this);
        keyboardinputs = new KeyboardInputs(this);
        setBGSize();
        addKeyListener(keyboardinputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }
    private void importImg() {
        String[] folders = {"1-Idle", "2-Run", "3-JumpAnticipation","4-Jump","5-Fall","6-Ground","7-Hit","8-DeadHit","9-DeadGround","10-DoorIn","11-DoorOut"}; // List of folder names
        int numImages = 26; // Number of images in each folder
        animations = new BufferedImage[folders.length][numImages];

        for (int i = 0; i < folders.length; i++) {
            for (int j = 0; j < numImages; j++) {
                String filePath = "/" + folders[i] + "/" + (j+1) + ".png";
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

    private void setBGSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
    public void setDirection(int direction){
        this.playerDirection = direction;
        mooving = true;
    }
    public void setMooving(boolean moving){
        this.mooving = moving;
    }

    //Draws the images on the screen
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateAnimation();
        setAnimation();
        updatePos();
       g.drawImage(animations[playerAction][animationIndex],(int)xDelta,(int) yDelta,250,200,null);

    }

    private void updatePos() {
        if(mooving){
            switch (playerDirection){
                case LEFT -> xDelta -=10;
                case UP -> yDelta-= 10;
                case RIGHT ->xDelta += 10;
                case DOWN ->  yDelta += 10;
            }
        }
    }

    private void setAnimation() {
        if(mooving){
            playerAction = RUN;
        }else {
            playerAction = IDLE;
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
}
