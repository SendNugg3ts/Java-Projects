package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;


public class GameBG extends JPanel {
    private MouseInputs mouseInputs;
    private int xDelta = 0, yDelta =0;
    private KeyboardInputs keyboardinputs;
    public GameBG(){
        mouseInputs = new MouseInputs();
        keyboardinputs = new KeyboardInputs();
        addKeyListener(keyboardinputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.fillRect(100+ xDelta,100+yDelta,200,50);
    }
}
