import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Board extends JPanel {

    private int board_width = 500;
    private String img_file = "src/images/bord Colourbordcorrect.jpg";
    private BufferedImage img = null;

    BufferedImage[] icons;

    public Board() {
        try {
            if (img == null) {
                img = ImageIO.read(new File(img_file));
            }
        } catch (IOException e) {
            System.out.println("Unexpected Error");
        }
    }

    public Board(int amountUser){
        try {
            if (img == null) {
                img = ImageIO.read(new File(img_file));
            }
        } catch (IOException e) {
            System.out.println("Unexpected Error");
        }

        icons = new BufferedImage[amountUser];
        //loadImages(amountUser);

        //Make array with the amount of player
        Player[] players = new Player[amountUser];
        for(int i = 0; i < amountUser; i++ ){
            players[i].id = i + 1; // the center of the board
        }
        players[0].isTurn = true;

    }

    @Override
    public void paintComponent (Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 10, 10, board_width, board_width, null);
           /* int plus = 5;
            for(BufferedImage i: icons){
                g.drawImage(i, 10 + plus, 10 + plus,5,5,null);
                plus += 5;
            }*/
        }

    @Override
    public Dimension getPreferredSize () {
        return new Dimension(board_width + 20, board_width + 20);
    }

    //
//    public void loadImages( int amount){
////        for(int i=0;i<amount;i++){
////            int j = i + 1 ;
////            try {
////                icons[i] = ImageIO.read(new File("images/"+j+".png"));
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////
////        }
////    }
}

 /*   private void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
    }*/


