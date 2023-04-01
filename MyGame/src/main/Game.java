package main;

public class Game {
    private Window gamewindow;
    private GameBG gamebg;
    public Game(){
        gamebg = new GameBG();
        gamewindow = new Window(gamebg);
        gamebg.setFocusable(true);
        gamebg.requestFocus();

    }
}
