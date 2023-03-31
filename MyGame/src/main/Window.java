package main;

import javax.swing.*;

public class Window extends JFrame {
    private JFrame screen;
    public Window(GameBG gameBG){

         screen = new JFrame();
         screen.setSize(500,500);
         screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         screen.add(gameBG);
         screen.setVisible(true);


    }
}
