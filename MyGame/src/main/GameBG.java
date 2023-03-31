package main;

import javax.swing.*;
import java.awt.*;

public class GameBG extends JPanel {
    public GameBG(){

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.fillRect(100,100,200,50);
    }
}
