package main;

public class Game implements Runnable{
    private Thread thread;
    private Window gamewindow;
    private GameBG gamebg;
    private final int GAME_FPS= 120;
    public Game(){
        gamebg = new GameBG();
        gamewindow = new Window(gamebg);
        gamebg.setFocusable(true);
        gamebg.requestFocus();
        startGameLoop();

    }
    private void startGameLoop(){
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        double timePerframe = 1_000_000_000.0/ GAME_FPS;
        long lastFrame = System.nanoTime();
        long now;
        int frames = 0;
        long lastCheck = System.currentTimeMillis();
        while (true) {
            now = System.nanoTime();
            if (now - lastFrame >= timePerframe) {
                gamebg.repaint();
                lastFrame = now;
                frames++;
            }
            //Frames Counter
            if(System.currentTimeMillis() - lastCheck >=1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS count: "+frames);
                frames=0;
            }
        }

    }
}
