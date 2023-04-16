package main;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import javax.swing.*;
import java.awt.*;



public class GameBG extends JPanel {
    private MouseInputs mouseInputs;
    private KeyboardInputs keyboardinputs;
    private Game game;

    public GameBG(Game game) {
        mouseInputs = new MouseInputs(this);
        keyboardinputs = new KeyboardInputs(this);
        setBGSize();
        addKeyListener(keyboardinputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        this.game = game;

    }
    private void setBGSize() {
        Dimension size = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
    //Draws the images on the screen
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }
    public void updateGame() {
    }
    public Game getGame(){
        return game;
    }
}
