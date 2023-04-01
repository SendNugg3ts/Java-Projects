package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;


public class GameBG extends JPanel {
    private Color color = Color.magenta;
    private MouseInputs mouseInputs;
    private float xDelta = 0, yDelta =0;
    private float xDir = 1f, yDir = 1f;
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

    }
    public void changeyDelta(int y){
        this.yDelta += y;

    }
    public void setRectPos(int x, int y){
        this.xDelta = x;
        this.yDelta = y;

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        updateRect();
        g.setColor(color);
        g.fillRect((int)xDelta,(int)yDelta,200,50);
    }
//Makes the rectangle change color when he hits a wall as well as movement
    private void updateRect() {
        xDelta += xDir;
        if(xDelta > 400 || xDelta <0){
            color = Color.blue;
            xDir *= -1;
        }
        yDelta += yDir;
        if(yDelta > 400 || yDelta <0){
            color = Color.magenta;
            yDir *= -1;
        }
    }
}
