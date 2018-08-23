import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.HashMap;
import java.lang.Math;

public class Game {
    private static JFrame frame = new JFrame("Cloud Pursuit");
    private static JLabel rollResult = new JLabel();
    private static JPanel game_panel = new JPanel();
    private Board board;
    private HashMap locationOfPosition = new HashMap();
    private JButton option1 = new JButton();
    private JButton option2 = new JButton();

    private Game() {
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    private void start(){
        frame.setPreferredSize(new Dimension(900,900));

        JPanel start = new JPanel();

        //button new game with an actionListener
        JButton newGame = new JButton("Start new game");
        start.add(newGame);

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] possibilities = { 2, 3, 4};
                int amountOfPlayers = JOptionPane.showOptionDialog(frame," How many players?","Select amount of player", JOptionPane.DEFAULT_OPTION,1,null,possibilities,"2");

                if (amountOfPlayers != JOptionPane.CLOSED_OPTION) {
                    startGame((Integer) possibilities[amountOfPlayers]);
                    start.setVisible(false);
                }
            }
        });

        //buttons rules and exit game added the panel start
        JButton rules = new JButton("Rules");
        start.add(rules);
        JButton exitGame = new JButton("Exit");
        start.add(exitGame);

        //add panel start to frame
        frame.add(start, BorderLayout.CENTER);

        /* display the frame with the components */
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //build the Map that contains the location of the position. Will be using this for walking to the right tile;
        int j = 1;
        locationOfPosition.put(0, 0);
        for(int i = 1; i<= 42; i++){
            locationOfPosition.put(i, j);
            if (i % 7 == 0){
                j = j+ 1;
            }
            j = j + 1;
        }
    }
    private void startGame(int amount){
        JButton btnDice = new JButton("Roll");
        //.setIcon(new Icon ("images/animated-dice-image-0012.gif"));

        board = new Board(amount);
        frame.add(board, BorderLayout.CENTER);
        game_panel.add(rollResult);
        game_panel.add(btnDice);
        frame.add(game_panel, BorderLayout.EAST);
        SwingUtilities.updateComponentTreeUI(frame);

        btnDice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDiceAndWalk();
            }
        });
    }

    public void rollDiceAndWalk(){
        int number = (int) ((Math.random() * 6) + 1);
        rollResult.setText("Result: " + number);
        for(Player p : Board.players) {
            if (p.isTurn == true) {

                // show all the options; forward and backward
                int newPos = p.pos - number;
                if(newPos<0 )
                    newPos = newPos + 42;

                int newPos2 = p.pos + number;
                if(newPos2>= 42)
                    newPos2 = newPos2 - 42;

                //show the buttons for the options
                option1.setText(String.valueOf(newPos));
                option2.setText(String.valueOf(newPos2));
                game_panel.add(option1);
                game_panel.add(option2);

//                    //set new pos of player
//                    p.pos = p.pos + number;
//                    if (p.pos >  42) // if new pos is bigger than 42, start
//                        p.pos= p.pos - 42;

                //get the location of the position of the player
                int locMemory = (int) locationOfPosition.get(p.pos);

                //walk to this new position
                p.walk(locMemory);


            }
        }
        board.repaint(); // move the player to new position
    }
}