package tictactoegame;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;


public class Window extends JFrame {

    //**************************************************
    //  FIELDS
    //**************************************************

    private static final long serialVersionUID = 1L;

    int gameWidth;
    int gameHeight;

    public JFrame gameScreen;

    //**************************************************
    //  CONSTRUCTORS
    //**************************************************

    /**
    * Window class constructor
    *
    * @param width Width of game window

    */

    public Window(int width) {
        gameScreen = new JFrame();
        this.gameWidth = width;
        gameHeight = gameWidth;

        System.out.println("Assigned width: " + this.gameWidth);
        System.out.println("Calculated height: " + this.gameHeight);
    }

    //**************************************************
    //  METHODS
    //**************************************************

    /**
     * Displays initial game state.
     * 
     * @param tiles ArrayList<Tile> containing tiles with their data
     */


    public void displayScreen(ArrayList<Tile> tiles) {
        // INITIALIZE VARIABLES: 
        Dimension d = new Dimension(this.gameWidth, this.gameHeight);
        gameScreen = new JFrame(Game.gameTitle);

        // ADD TILES TO JFRAME:
        for(Tile tile : tiles) {
            gameScreen.add(tile.tileInterface);
        }

        // GAME SCREEN OPTIONS:
        gameScreen.setLayout(new GridLayout(3,3));
        gameScreen.setResizable(false);
        gameScreen.setPreferredSize(d);
        gameScreen.setMinimumSize(d);
        gameScreen.setMaximumSize(d);
        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreen.setLocationRelativeTo(null);
        gameScreen.setVisible(true);
        gameScreen.pack();
    }

}