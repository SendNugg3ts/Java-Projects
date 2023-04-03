package main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

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
         screen.addWindowFocusListener(new WindowFocusListener() {
             @Override
             public void windowGainedFocus(WindowEvent e) {
             }
             @Override
             public void windowLostFocus(WindowEvent e) {
                 gameBG.getGame().windowFocusLost();
             }
         });


    }
}
