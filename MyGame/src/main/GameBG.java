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
        mouseInputs = new MouseInputs(this);
        keyboardinputs = new KeyboardInputs(this);
        addKeyListener(keyboardinputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }
    public void changexDelta(int x){
        this.xDelta += x;
        repaint();
    }
    public void changeyDelta(int y){
        this.yDelta += y;
        repaint();
    }
    public void setRectPos(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
        repaint();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.fillRect(xDelta,yDelta,200,50);
    }
}
