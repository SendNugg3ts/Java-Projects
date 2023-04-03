package main;

import Entities.MainCharacter;

import java.awt.*;

public class Game implements Runnable {
    private Thread thread;
    private Window gamewindow;
    private GameBG gamebg;
    private final int GAME_FPS = 120;
    private final int GAME_UPS = 200;
    private MainCharacter player;

    public Game() {
        initClasses();
        gamebg = new GameBG(this);
        gamewindow = new Window(gamebg);
        gamebg.setFocusable(true);
        gamebg.requestFocus();
        startGameLoop();
    }

    private void initClasses() {
        player = new MainCharacter(200, 200);
    }

    private void startGameLoop() {
        thread = new Thread(this);
        thread.start();
    }

    public void update() {
        player.update();
    }

    public void render(Graphics g) {
        player.render(g);

    }

    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / GAME_FPS;
        double timePerUpdate = 1_000_000_000.0 / GAME_UPS;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;

        long lastCheck = System.currentTimeMillis();

        double deltaUpdates = 0;
        double deltaFrames = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaUpdates += (currentTime - previousTime) / timePerUpdate;
            deltaFrames += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaUpdates >= 1) {
                update();
                updates++;
                deltaUpdates--;
            }
            if (deltaFrames >= 1) {
                gamebg.repaint();
                frames++;
                deltaFrames--;
            }

            //Frames Counter
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS count: " + frames + " | UPS count: " + updates);
                frames = 0;
                updates = 0;
            }
        }

    }
    public MainCharacter getPlayer() {
         return player;
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }
}