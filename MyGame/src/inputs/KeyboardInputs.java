package inputs;

import main.GameBG;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Utilz.Constant.Directions.*;
import Utilz.Constant.PlayerConstants.*;

import static Utilz.Constant.Directions.*;

public class KeyboardInputs implements KeyListener {
    private GameBG gamePanel;
    public KeyboardInputs(GameBG gamePanel){
        this.gamePanel = gamePanel;

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                gamePanel.setMooving(false);
                break;
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                gamePanel.setDirection(UP);
            }
            case KeyEvent.VK_A -> {
                gamePanel.setDirection(LEFT);
            }
            case KeyEvent.VK_S -> {
                gamePanel.setDirection(DOWN);
            }
            case KeyEvent.VK_D -> {
                gamePanel.setDirection(RIGHT);
            }
        }
    }
}
