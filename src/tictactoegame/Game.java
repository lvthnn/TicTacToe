package tictactoegame;

import java.util.Arrays;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.lang.IndexOutOfBoundsException;

public class Game implements ActionListener {

    //**************************************************
    //  FIELDS
    //**************************************************

    public static int runMode;  // Not implemented
    public static String gameTitle, gameAuthor;
    public static Window gameWindow;
    public static Game TicTacToeGame;

    public static ArrayList<Tile> gameTiles = new ArrayList<Tile>();
    public static ArrayList<JButton> buttonIndex = new ArrayList<JButton>();
    
    private ArrayList<Tile> playerOneSelectedTiles = new ArrayList<Tile>(); // P1 selectionPool
    private ArrayList<Tile> playerTwoSelectedTiles = new ArrayList<Tile>(); // P2 selectionPool

    private boolean secondPlayerTurn;           // Informs us which player's turn it is
    private char markSymbol;                    // Mark symbol of players in playing board: X or O?
    private Color gameTextColor;                // Foreground color of tiles

    public String[] playerIDs = new String[2];

    //**************************************************
    //  CONSTRUCTORS
    //**************************************************

    /**
    * Game class constructor
    *
    * @param window The window object (see Window.java) that is responsible for rendering game UI
    * @param title The window title
    * @param author Game author
    */
    
    public Game(Window window, String title, String author) {
        Game.gameTitle = title;
        Game.gameWindow = window;
        Game.gameAuthor = author;
      
    }

    //**************************************************
    //  METHODS
    //**************************************************

    public static void main(String[] args) {
        gameWindow = new Window(800);
        TicTacToeGame = new Game(gameWindow, "TIC TAC TOE", "Kári Hlynsson");
        TicTacToeGame.startGame(gameWindow);
    }

    /**
     * Starts a new game session.
     * 
     * @param window The instantiated window object for respective Game class
     */

    public void startGame(Window window) {
  
        for(int i = 0; i <= 8; i++) {
            int instance = i;
            String nameOfTile = getInstanceHandle(i);
            gameTiles.add(new Tile(instance, nameOfTile, new JButton(), ' '));
        }

        for(Tile tile : gameTiles) {
            (tile.tileInterface).addActionListener(this);
            buttonIndex.add(tile.tileInterface);
        }

        window.displayScreen(gameTiles);

    }

    /**
     * Returns string representation of tile in playing board
     * 
     * @param num Number of tile in playing board (left->right, top->bottom),
     * i.e.
     *                      ____________
     *                     | 1 | 2 | 3 |
     *                     | 4 | 5 | 6 |
     *                     |_7_|_8_|_9_|
     *  
     * @return String representation tile X in the format 'TileNumX'.
     */

    public String getInstanceHandle(int num) {
        return "TileNum".concat(Integer.toString(num));
    }

    /**
     *  Adds symbol to tile once mouseclick has been registered
     * 
     *  @param event ActionEvent resulting from player mouseclick
     */

    public void actionPerformed(ActionEvent event) {
        int buttonPressedNum = buttonIndex.indexOf(event.getSource());

        if(!secondPlayerTurn) {
            secondPlayerTurn = true;
            gameTextColor = Color.RED;
            markSymbol = 'X';
        } else {
            secondPlayerTurn = false;
            gameTextColor = Color.BLUE;
            markSymbol = 'O';
        }

        gameTiles.get(buttonPressedNum).updateTile(markSymbol, gameTextColor, TicTacToeGame);

        if(!secondPlayerTurn) {
            playerOneSelectedTiles.add(gameTiles.get(buttonPressedNum));
            checkSelections(playerOneSelectedTiles);
        } else {
            playerTwoSelectedTiles.add(gameTiles.get(buttonPressedNum));
            checkSelections(playerTwoSelectedTiles);
        }
    }

    /**
     *  Checks whether any combination of respective user tile selections match victory criteria.
     * 
     *  @param selectionPool An arraylist of Tile objects which have been selected by each player.
     *  Note that selectionPool is NOT an arraylist with all selected tiles, only the tiles selected
     *  by one of the players.
     */

    public void checkSelections(ArrayList<Tile> selectionPool) {
        ArrayList<Integer> matchingCases = new ArrayList<Integer>(Arrays.asList(1,3,4,5,7));
        for (Tile tile : selectionPool) {
            Tile currentTile = tile;
            int tileDistance = 3;
            int numChecks = 1;

            if(matchingCases.contains(currentTile.tileNumber) && currentTile.tileNumber - tileDistance >= 0) {
                if(currentTile.tileNumber == matchingCases.get(2)) {
                    numChecks = 3;
                } else if(currentTile.tileNumber == matchingCases.get(0) || 
                        currentTile.tileNumber == matchingCases.get(4)) {
                    numChecks = 1;
                } else if(currentTile.tileNumber == matchingCases.get(1) ||
                        currentTile.tileNumber == matchingCases.get(3)) {
                    numChecks = 0;
                }
                
                for(int i = 0; i <= numChecks; i++) {
                    switch(i) {
                        case 0:
                            tileDistance = 3;
                            break;
                        case 1:
                            tileDistance = 1;
                            break;
                        case 2:
                            tileDistance = 4;
                            break;
                        case 3:
                            tileDistance = 2;
                            break;
                    }

                    try {
                        if(
                            currentTile.tileValue == gameTiles.get(currentTile.tileNumber + tileDistance).tileValue
                        &&  currentTile.tileValue == gameTiles.get(currentTile.tileNumber - tileDistance).tileValue
                        ) {
                            int winningPlayer = TicTacToeGame.playerVictory();
                            System.out.println("Player " + winningPlayer + " victory!");
                        }
                    } catch(IndexOutOfBoundsException e) {

                    }
                }
            }
        } 
    }

    /*
     * How to proceed once one of the players' selection pools matches victory conditions.
     */

    public int playerVictory() {
        int winningPlayer;
        if(secondPlayerTurn) { winningPlayer = 1; } 
        else { winningPlayer = 2; }

        for(Tile gameTile : gameTiles) {
            gameTile.updateTileVictory(winningPlayer, gameTile.tileInterface);
        }

        return winningPlayer;
    }
}
