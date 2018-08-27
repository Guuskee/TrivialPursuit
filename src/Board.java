import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Board extends JPanel {

    private int board_width = 500;
    private BufferedImage img = null;
    //private Tile[][] gameboard;
    static Player[] players ;

    Board(int amountUser){

        //Load the image of the board
        try {
            if (img == null) {
                img = ImageIO.read(new File("src/images/bord Colour volledig (002).jpg"));
            }
        } catch (IOException e) {
            System.out.println("Unexpected Error");
        }

        /* Make array with the amount of player chosen */
        players = new Player[amountUser];
        for(int i = 0; i < players.length; i++ ){
            players[i] = new Player(i + 1);
        }
        players[0].isTurn = true;
        loadImages(); // load the images of the pawns to the corresponding player
//        loadTile();
    }

    @Override
    public void paintComponent (Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 10, 10, board_width, board_width, null);
            for (Player p: players){
                g.drawImage( p.icon, (int)p.posX ,(int) p.posY   , 22, 22 , null);
            }
        }

    @Override
    public Dimension getPreferredSize () {
        return new Dimension(board_width + 20, board_width + 20);
    }

    //load the image of the pawn for each player.
    private void loadImages(){
        try {
            int i = 1;
            for(Player p: players) {
                p.icon = ImageIO.read(new File("src/images/" + (i) + ".png"));
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void loadTile(){
//        gameboard = new Tile[42][];
//        for(int i = 0; i < 42; i++)
//        if(i % 7 == 0){
//            gameboard[i] = new Tile[7];
//            gameboard[i][0] = new Tile(i + 1 );
//
//        } else {
//            gameboard[i] = new Tile[1];
//            gameboard[i][0] = new Tile(i+ 1);
//        }
//    }
}