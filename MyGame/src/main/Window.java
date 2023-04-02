package main;

import javax.swing.*;

public class Window extends JFrame {
    private JFrame screen;
    public Window(GameBG gameBG){

         screen = new JFrame();

         screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         screen.add(gameBG);
         screen.setLocationRelativeTo(null);
         screen.setResizable(false);
         screen.pack();
         screen.setVisible(true);


    }
}
