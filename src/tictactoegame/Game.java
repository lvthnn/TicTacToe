package tictactoegame;

import java.util.Arrays;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.lang.IndexOutOfBoundsException;

public class Game implements ActionListener {

    // EDITION 1.00

    // Application instance fields
    public static int runMode;
    public static String gameTitle, gameAuthor;
    public static Window gameWindow;
    public static Game TicTacToeGame;


    public static ArrayList<Tile> gameTiles = new ArrayList<Tile>();
    public static ArrayList<JButton> buttonIndex = new ArrayList<JButton>();
    
    private ArrayList<Tile> playerOneSelectedTiles = new ArrayList<Tile>();
    private ArrayList<Tile> playerTwoSelectedTiles = new ArrayList<Tile>();

    // Private fields
    private boolean secondPlayerTurn;
    private char markSymbol;
    private Color gameTextColor;

    // Game instance fields:
    public String[] playerIDs = new String[2];

    public Game(Window window, String title, String author) {
        Game.gameTitle = title;
        Game.gameWindow = window;
        Game.gameAuthor = author;
    }
     public static void main(String[] args) {
        gameWindow = new Window(800);
        TicTacToeGame = new Game(gameWindow, "TIC TAC TOE", "Kári Hlynsson");
        TicTacToeGame.startGame(gameWindow);
    }

    public void startGame(Window windowToRun) {
        for(int i = 0; i <= 8; i++) {
            int instance = i;
            String nameOfTile = getInstanceHandle(i);
            gameTiles.add(new Tile(instance, nameOfTile, new JButton(), ' '));
            // System.out.println("Generated new Tile object with parameters: \n" +
            //                    "tileNumber: " + instance + "\n" +
            //                    "tileName: " + nameOfTile);
        }
        for(Tile tile : gameTiles) {
            (tile.tileInterface).addActionListener(this);
            buttonIndex.add(tile.tileInterface);
        }
        windowToRun.displayScreen(gameTiles);
    }

    public String getInstanceHandle(int num) {
        return "TileNum".concat(Integer.toString(num));
    }

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


        (gameTiles.get(buttonPressedNum)).updateTile(markSymbol, gameTextColor, TicTacToeGame);

        System.out.println("\n-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\nVALUE OF ALL TILES IN BOARD:");
        for(Tile tile : gameTiles) {
            System.out.println(tile.tileName + ": " + tile.tileValue);
        }

        if(!secondPlayerTurn) {
            playerOneSelectedTiles.add(gameTiles.get(buttonPressedNum));
            checkSelections(playerOneSelectedTiles);
        } else {
            playerTwoSelectedTiles.add(gameTiles.get(buttonPressedNum));
            checkSelections(playerTwoSelectedTiles);
        }
    }

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
                
                System.out.println("\nCHECKING TILE " + currentTile.tileNumber);

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

                    System.out.println("\n   VALUE OF CHECKED TILES (check mode" + i + "): ");
                    System.out.println("   " + currentTile.tileName + " (current): " + currentTile.tileValue);
                    System.out.println("   " + gameTiles.get(currentTile.tileNumber + tileDistance).tileName + ": " + gameTiles.get(currentTile.tileNumber + tileDistance).tileValue);
                    System.out.println("   " + gameTiles.get(currentTile.tileNumber - tileDistance).tileName + ": " + gameTiles.get(currentTile.tileNumber - tileDistance).tileValue);    

                    try {
                        if(
                            currentTile.tileValue == gameTiles.get(currentTile.tileNumber + tileDistance).tileValue
                        &&  currentTile.tileValue == gameTiles.get(currentTile.tileNumber - tileDistance).tileValue
                        ) {
                            System.out.println("Victory!");
                            TicTacToeGame.playerVictory();
                        }
                    } catch(IndexOutOfBoundsException e) {

                    }
                }
            }
        } 
    }

    public void playerVictory() {
        int winningPlayer;
        if(secondPlayerTurn) {
            winningPlayer = 1;
        } else {
            winningPlayer = 2;
        }
        for(Tile gameTile : gameTiles) {
            gameTile.updateTileVictory(winningPlayer, gameTile.tileInterface);
        }
    }
}
