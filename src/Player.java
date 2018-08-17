import javax.swing.*;
import java.awt.image.BufferedImage;

public class Player {
    int pos; // array position integer
    String name; // player name
    int id; // player ID
    boolean isTurn; // True = players turn, False = player waits
    int amtWedges; // amount of wedges
    boolean haveRed, haveYellow, haveGreen, havePurple, havePink, haveBlue;
    BufferedImage icon;
    double posX;
    double posY;

    public Player(int id){
        posX = 250;
        posY = 250;
        this.id = id;
        //set position to the center of the board
        isTurn= false;
        pos = 0;
        name = "Player"+ id;
    }
}
