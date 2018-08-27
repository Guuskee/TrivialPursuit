
import java.awt.image.BufferedImage;

class Player {
    int pos = 0; // array position integer
    String name; // player name
    int id; // player ID
    boolean isTurn; // True = players turn, False = player waits
    int amountWedges; // amount of wedges
    boolean haveRed, haveYellow, haveGreen, havePurple, havePink, haveBlue;
    BufferedImage icon;
    double posX; // the X position of the player on the board
    double posY; // the Y position of the player on the board

    public Player(){}

    Player(int id){
        posX = (Math.cos(Math.toRadians(pos * 7.5 +3.25))) * 220 +250 ;
        posY = (Math.sin(Math.toRadians(pos * 7.5 +3.25))) * 220 +250;
        this.id = id;
        //set position to the center of the board
        isTurn= false;
        pos = 0;
        name = "Player"+ id;
    }

    void walk(int pos){
        posX =   Math.cos(Math.toRadians(pos * 7.5+3.25)) * 220 + 250 ;
        posY =   Math.sin(Math.toRadians(pos * 7.5+3.25)) * 220 + 250;
        }
    }

