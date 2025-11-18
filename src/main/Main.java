package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x closes the game
        window.setResizable(false); // Static resolution
        window.setTitle("Pap's Quest");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // Set window size to fit preferred size of GamePanel

        window.setLocationRelativeTo(null); // Place in the center of the screen
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
