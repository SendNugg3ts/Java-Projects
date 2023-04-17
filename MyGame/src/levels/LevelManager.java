package levels;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;

    public LevelManager(Game game){
        this.game = game;
        //levelSprite = importImg();
        importOutsideSprites();
        levelOne = new Level(GetLevelData());

    }

    private void importOutsideSprites() {
        BufferedImage img = importImg("teste");
        levelSprite = new BufferedImage[48];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 12; j++){
                int index = i * 12 + j;
                levelSprite[index] = img.getSubimage(j*32, i* 32, 32, 32);
            }
        }

    }

    public void draw(Graphics g) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
            for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
                int index = levelOne.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
    }
    public void update(){

    }
    public static BufferedImage importImg(String image) {
        BufferedImage tiles = null;
        InputStream is = LevelManager.class.getResourceAsStream("/1-MainCharacter/"+ image +".png");
                try {
                    tiles = ImageIO.read(is);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return tiles;
    }
    public static int[][] GetLevelData(){
        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img = importImg("level_one_data");
        for(int i = 0; i < img.getHeight(); i++){
            for(int j= 0;j < img.getWidth(); j++ ){
                Color color = new Color(img.getRGB(j,i));
                int value = color.getRed();
                if (value >= 48)
                    value = 0;
                lvlData[i][j]= value;
            }
        }
        return lvlData;
    }
    public Level getCurrentLevel(){
        return levelOne;
    }
}


