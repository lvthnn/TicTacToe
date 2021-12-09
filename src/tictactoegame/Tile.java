package tictactoegame;


import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class Tile {

    //**************************************************
    //  FIELDS
    //**************************************************

    public String tileName;
    public JButton tileInterface;
    public char tileValue;
    public int tileNumber;
    public ActionListener getAction;

    Color buttonBg = new Color(154,231,252);
    Color textCol = Color.RED;
    Color buttonPOne = new Color(245, 66, 66);
    Color buttonPTwo = new Color(13, 0, 255);

    //**************************************************
    //  CONSTRUCTORS
    //**************************************************

   /**
    * Tile class constructor
    *
    * @param number Index of tile in playing board (see Game.java)
    * @param handle String representation of tile
    * @param button JButton object instance for event handling
    * @param value Contains player data (symbols)
    */

    public Tile(int number, String handle, JButton button, char value) {
        this.tileNumber = number;
        this.tileName = handle;
        this.tileInterface = button;
        this.tileValue = value;

        button.setBackground(buttonBg);
        button.setFocusable(false);
        button.setText(String.valueOf(tileValue));
        button.setVisible(true);
        button.setFont(new Font("Arial", Font.BOLD, 40));
    }

    //**************************************************
    //  METHODS
    //**************************************************

    /**
     * Updates tile displayed text and foreground color. Also disables it.
     * 
     * @param symbolToUpdate Which user symbol ('X' or 'O') to write into the button
     * @param textColor Which player color to assign to picked tile
     * @param game Game class
     */

    public void updateTile(char symbolToUpdate, Color textColor, Game game) {
        this.tileValue = symbolToUpdate;
        this.tileInterface.setText(String.valueOf(this.tileValue));
        this.tileInterface.setForeground(textColor);
        this.tileInterface.setEnabled(false);
    }

    /**
     * Disables all tiles. Call when either player has won the other.
     * 
     * @param player Winning player
     * @param button JButton :|
     */

    public void updateTileVictory(int player, JButton button) {
        if(player == 1) {
            button.setBackground(buttonPOne);
            button.setEnabled(false);
        } else {
            button.setBackground(buttonPTwo);
            button.setEnabled(false);
        }
    }

}