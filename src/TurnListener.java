import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.stream.IntStream;

public class TurnListener implements ActionListener {
    private HashMap locationOfPosition = new HashMap();
    private JButton option1 = new JButton();
    private JButton option2 = new JButton();
    private JPanel option_panel = new JPanel();
    private Player p;
    private int tempPos;
    private int tempPos2;
    private int[] diceArray = {2, 5, 9, 12, 16, 19, 23, 26, 30, 33, 37, 40};
    private Board b;


    TurnListener(Player p, int number, JPanel panel, Board b ) {
        this.b = b;
        this.p = p;
        option_panel.add(option1);
        option_panel.add(option2);
        panel.add(option_panel, BorderLayout.CENTER);

        //build the Map that contains the location of the position. Will be using this for walking to the right tile;
        int j = 1;
        locationOfPosition.put(0, 0);
        for (int i = 1; i <= 42; i++) {
            locationOfPosition.put(i, j);
            if (i % 7 == 0) {
                j = j + 1;
            }
            j = j + 1;
        }

        //Calculate the new positions
        //backward option
        tempPos = p.pos - number;
        if (tempPos < 0) {
            tempPos = tempPos + 42;
        }

        //forward option
        tempPos2 = p.pos + number;
        if (tempPos2 >= 42)
            tempPos2 = tempPos2 - 42;

        //show the buttons with the value of the options xxxx Later moet het anders zijn
        option1.setText(String.valueOf(tempPos));
        option2.setText(String.valueOf(tempPos2));
        //Add actionListener to the two buttons
        option1.addActionListener(this);
        option2.addActionListener(this);

    }
    //ActionListener for the buttons
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == option1) {
            option_panel.hide();
            p.pos = tempPos;
            //show the two option on the board;
            int locMemory = (int) locationOfPosition.get(p.pos);
            //walk to this new position
            p.walk(locMemory);
            nextMove(p,b);

        } else if (e.getSource() == option2) {
            option_panel.hide();
            p.pos = tempPos2;
            //show the two option on the board;
            int locMemory = (int) locationOfPosition.get(p.pos);
            //walk to this new position
            p.walk(locMemory);
            nextMove(p,b);
        }
    }

    // check if the tile is a dice tile, or if the player answer the question right
    private Player nextMove(Player p, Board b) {
        boolean contains;
        contains = IntStream.of(diceArray).anyMatch(x -> x == p.pos);
        if (contains)
            p.isTurn = true;
        else {
            //SHOW QUESTION HERE
            String[] possibleValues = {"Yes", "No"};
            int selectedValue = JOptionPane.showOptionDialog(null, "Next Player", "Next",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null, possibleValues, possibleValues[0]);
            if(selectedValue == 0){
                //answer is wrong so its the next players turn
                p.isTurn = false;

                //if its not the last player, set the id to the next player (id = 1 but position = 0 in array!!!)
                if( p.id != b.players.length )
                {
                    b.players[p.id].isTurn = true;
                }
                else // Its the first player's turn
                    b.players[0].isTurn = true;
            }
        }
        return p;
    }
}
