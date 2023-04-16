package Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static Utilz.Constant.PlayerConstants.*;
import static main.Game.SCALE;

public class Bomb extends MainEntity {
    private BufferedImage[] bombAnimation = new BufferedImage[20];
    private int animationTick, animationIndex,animationSpeed = 10;
    private boolean active = false;
    private boolean exploded = false;
    private float initialX, initialY;
    private float explosionRadius = 100;
    private long detonationTime;

    public Bomb(float x, float y, int width, int height){
        super(x,y, width, height);
        importImg();
    }

    public void update(){
        updateAnimation();
    }

    public void render(Graphics g){
        g.drawImage(bombAnimation[animationIndex], (int) x, (int) y, (int) (64 * SCALE), (int) (40 * SCALE), null);
    }

    private void updateAnimation() {
        animationTick++;
        if(animationTick >= animationSpeed){
            animationTick =0;
            animationIndex++;
            if(animationIndex >= bombAnimation.length){
                animationIndex = 0;
            }
        }
    }

    private void importImg() {
        for (int i = 0; i < 20; i++) {
            String filePath = "/1-MainCharacter/12-BOMB" + (i+1) + ".png";
            InputStream is = getClass().getResourceAsStream(filePath);
            if (is == null){break;}
            try {
                bombAnimation[i] = ImageIO.read(is);
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
    public void drop(float playerX, float playerY) {
        // Set the bomb's position to the player's position
        setPosition(playerX, playerY);

        // Set the bomb to active
        active = true;

        // Reset the animation
        reset();
    }
    public boolean hasExploded() {
        return exploded;
    }
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void reset() {
        // Reset the animation
        animationTick = 0;
        animationIndex = 0;

        // Set the bomb to inactive
        active = false;

        // Set the explosion flag to false
        exploded = false;
    }
}
