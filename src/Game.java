import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Game {
    private static JFrame frame = new JFrame("Cloud Pursuit");
    private static JLabel rollResult = new JLabel();
    private static JLabel playerTurn = new JLabel();
    private static JPanel game_panel = new JPanel();
    private Board board;

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
    }
    //----------------------------------------------------------------------------------------------------------
    private void startGame(int amount){
        JButton btnDice = new JButton("Roll");
        //.setIcon(new Icon ("images/animated-dice-image-0012.gif"));
        board = new Board(amount);
        frame.add(board, BorderLayout.CENTER);
        game_panel.add(playerTurn);
        game_panel.add(rollResult);
        game_panel.add(btnDice);
        frame.add(game_panel, BorderLayout.EAST);
        SwingUtilities.updateComponentTreeUI(frame);

        btnDice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });
    }
    //-------------------------------------------------------------------------------------------------------------
    private void rollDice(){
        boolean contains;
        int number = (int) ((Math.random() * 6) + 1);
        rollResult.setText("Result: " + number);
        for(Player p : Board.players) {
            if (p.isTurn) {
                playerTurn.setText(p.name);
                // player will walk after selecting a direction
                TurnListener t = new TurnListener(p, number, game_panel, board);

            }
            board.repaint(); // move the player to new positio
//
        }
    }
}


//show Q
//if answer = right
// p.isTurn = true;
//if tile = big
//if player doesnt have that tile
//give player that tile
// else:
//do nothing
//else if  answer is wrong;
//