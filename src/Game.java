import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {
    static JFrame frame = new JFrame("Cloud Pursuit");
    static private int amountOfPlayers;

    public static void main(String[] args) {
        startGame();
    }

    private static void startGame(){
        frame.setPreferredSize(new Dimension(900,900));

        JPanel start = new JPanel();

        //button new game with an actionListener
        JButton newGame = new JButton("Start new game");
        start.add(newGame);

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] possibilities = { 2, 3, 4};
                amountOfPlayers = (int)JOptionPane.showInputDialog(frame," How many players?","Select amount of player",JOptionPane.PLAIN_MESSAGE,null, possibilities,"2");
                showBoard();
            }
        });

        //buttons rules and exit game added the panel start
        JButton rules = new JButton("Rules");
        start.add(rules);
        JButton exitGame = new JButton("Exit");
        start.add(exitGame);

        frame.add(start, BorderLayout.CENTER);

        /* display the frame with the components */
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void showBoard( ){
        Board board = new Board();
        frame.add(board, BorderLayout.NORTH);
        SwingUtilities.updateComponentTreeUI(frame);
    }
}