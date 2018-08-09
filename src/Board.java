import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Board extends JPanel {

    int board_width = 100;
    BufferedImage img = null;

    public Board(){
        try {
            if (img == null) {
                img = ImageIO.read(new File("images/bord Colourbordcorrect.jpg"));
            }
        } catch (IOException e) {
            System.out.println("Unexpected Error");
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 10, 10, board_width, board_width, null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(board_width + 20, board_width + 20);
    }

 /*   private void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
    }*/


}
