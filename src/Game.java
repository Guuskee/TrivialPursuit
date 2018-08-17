import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.lang.Math;

public class Game {
    static JFrame frame = new JFrame("Cloud Pursuit");
    static private int amountOfPlayers = 0 ;
    private static AbstractButton btnDice;
    private static JLabel rollResult = new JLabel();
    private static JPanel game_panel = new JPanel();
    Board board;
    int x = -1;


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
                int amountOfPlayers = JOptionPane.showOptionDialog(frame," How many players?","Select amount of player",JOptionPane.PLAIN_MESSAGE,1,null,possibilities,"2");

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
    private void startGame(int amount){
        btnDice  = new JButton("Roll") ;
        //.setIcon(new Icon ("images/animated-dice-image-0012.gif"));

        board = new Board(amount);
        frame.add(board, BorderLayout.CENTER);
        game_panel.add(rollResult);
        game_panel.add(btnDice);
        frame.add(game_panel, BorderLayout.EAST);
        SwingUtilities.updateComponentTreeUI(frame);

        JLabel rollresult = new JLabel();
        btnDice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });
    }

    public void rollDice(){
        int number = (int) ((Math.random() * 6) + 1);
        rollResult.setText("Result: " + number);
        walk();
        // need number for actually walking too.
    }

    public void walk(){
        for(Player p : Board.players) {
            if (p.isTurn == true) {
                x = x + 1;
                p.posX = (Math.cos(Math.toRadians(x * 7.5))) * 250 +250 ;
                p.posY = (Math.sin(Math.toRadians(x * 7.5))) * 250 + 250;
            }
        }
        board.repaint();
    }

}