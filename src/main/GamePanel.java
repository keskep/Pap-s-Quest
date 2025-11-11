package main;

import entity.Player;
import tile.TileManager;

import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{ // Runnable for the thread

    // Screen Settings
    final int originalTileSize = 16; // 16x16 tiles
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionCheck cCheck = new CollisionCheck(this);
    public Player player = new Player(this, keyH);

      // Set Pap's default position
//    int playerX = 100;
//    int playerY = 100;
//    int playerSpeed = 4;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.lightGray);
        this.setDoubleBuffered(true); // Better rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true); // GamePanel is focused to receive key input
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start(); //Calls run()
    }

    @Override
//    public void run() {
//
//        double drawInterval = 1000000000 / FPS; // Basically (1 second / FPS) = 0.0166 seconds
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while (gameThread != null) {
//
//            // 1 UPDATE
//            update();
//            // 2 DRAW
//            repaint();
//
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime / 1000000 ;// Convert nano to milliseconds
//
//                if(remainingTime < 0) {
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e); // e.printStackTrace();
//            }
//        }
//    }
    public void run() {

        double drawInterval = (double) 1000000000 / FPS; // Target time for one frame (in nanoseconds)
        double delta = 0; // Tracks how much time has passed (in "frames")
        long lastTime = System.nanoTime(); // Stores the timestamp from the previous loop iteration
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) { // Keep looping as long as the game thread is running

            currentTime = System.nanoTime(); // Get the current time in nanoseconds

            delta += (currentTime - lastTime) / drawInterval; // Add the time difference (since last frame) to and divide by drawInterval to express it in "frame units"
            timer += (currentTime - lastTime);
            lastTime = currentTime; // Update lastTime for the next loop

            if (delta >= 1) { // Only update and redraw when enough time has passed for 1 frame
                update();   // Game logic
                repaint();  // Redraw the screen
                delta--;    // Reduce delta by 1 frame
                drawCount++; // Update fps
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount); // Display FPS in console
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update() {

        player.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g); //super is the parent class (JPanel)

        Graphics2D g2 = (Graphics2D) g; // Extend the class with Graphics2D providing more functions and geometry

        tileM.draw(g2);

        player.draw(g2);

        g2.dispose(); // Save memory
    }
}
