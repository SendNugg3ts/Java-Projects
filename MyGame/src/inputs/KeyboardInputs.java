package inputs;

import main.GameBG;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
    }
    @Override
    public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_W:
                    System.out.println("W");
                    gamePanel.changeyDelta(-5);
                    break;
                case KeyEvent.VK_A:
                    System.out.println("A");
                    gamePanel.changexDelta(-5);
                    break;
                case KeyEvent.VK_S:
                    System.out.println("S");
                    gamePanel.changeyDelta(5);
                    break;
                case KeyEvent.VK_D:
                    System.out.println("D");
                    gamePanel.changexDelta(5);
                    break;
            }
    }
}
