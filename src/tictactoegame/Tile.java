package tictactoegame;


import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class Tile {

    public String tileName;
    public JButton tileInterface;
    public char tileValue;
    public int tileNumber;
    public ActionListener getAction;

    Color buttonBg = new Color(154,231,252);
    Color textCol = Color.RED;
    Color buttonPOne = new Color(245, 66, 66);
    Color buttonPTwo = new Color(13, 0, 255);

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

    public void updateTile(char symbolToUpdate, Color textColor, Game game) {
        this.tileValue = symbolToUpdate;
        this.tileInterface.setText(String.valueOf(this.tileValue));
        this.tileInterface.setForeground(textColor);
        this.tileInterface.setEnabled(false);
    }

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