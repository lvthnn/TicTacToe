package tictactoegame;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;


public class Window extends JFrame {

    private static final long serialVersionUID = 1L;

    int gameWidth;
    int gameHeight; // Set aspect ratio to 16:9

    public JFrame gameScreen;

    public Window(int width) {
        gameScreen = new JFrame();
        this.gameWidth = width;
        gameHeight = gameWidth;

        System.out.println("Assigned width: " + this.gameWidth);
        System.out.println("Calculated height: " + this.gameHeight);
    }

    public void displayScreen(ArrayList<Tile> tiles) {
        // INITIALIZE VARIABLES: 
        Dimension prefGameDimension = new Dimension(this.gameWidth, this.gameHeight);
        gameScreen = new JFrame(Game.gameTitle);

        // ADD TILES TO JFRAME:
        for(Tile tile : tiles) {
            gameScreen.add(tile.tileInterface);
        }

        // GAME SCREEN OPTIONS:
        gameScreen.setLayout(new GridLayout(3,3));
        gameScreen.setResizable(false);
        gameScreen.setPreferredSize(prefGameDimension);
        gameScreen.setMinimumSize(prefGameDimension);
        gameScreen.setMaximumSize(prefGameDimension);
        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreen.setLocationRelativeTo(null);
        gameScreen.setVisible(true);
        gameScreen.pack();
    }

    
}