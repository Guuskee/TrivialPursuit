
import java.awt.image.BufferedImage;

class Player {
    int pos = 0; // array position integer
    String name; // player name
    int id; // player ID
    boolean isTurn; // True = players turn, False = player waits
    int amtWedges; // amount of wedges
    boolean haveRed, haveYellow, haveGreen, havePurple, havePink, haveBlue;
    BufferedImage icon;
    double posX;
    double posY;
    int nextTurn;

    public Player(int id, int nextTurn){
        posX = (Math.cos(Math.toRadians(pos * 7.5 +3.25))) * 220 +250 ;
        posY = (Math.sin(Math.toRadians(pos * 7.5 +3.25))) * 220 + 250;
        this.id = id;
        //set position to the center of the board
        isTurn= false;
        pos = 0;
        name = "Player"+ id;
        this.nextTurn = nextTurn;
    }

    public void walk(int pos){
        posX =   Math.cos(Math.toRadians(pos * 7.5+3.25)) * 220 + 250 ;
        posY =   Math.sin(Math.toRadians(pos * 7.5+3.25)) * 220 + 250;
        }
    }

