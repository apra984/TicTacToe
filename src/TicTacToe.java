import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
    private Container pane;
    private String currentPlayer;
    private boolean hasWinner;
    private JButton[][] board;

    public TicTacToe() {
        super();
        pane = getContentPane();
        pane.setLayout(new GridLayout(3, 3));
        setTitle("Tic-Tac-Toe Game");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        currentPlayer = "X";
        board = new JButton[3][3];
        hasWinner = false;
        initializeBoard();
        initializeMenuBar();

    }

    private void initializeMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });
        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(newGame);
        menu.add(quit);
        menuBar.add(menu);
        setJMenuBar(menuBar);

    }

    private void resetBoard() {
        currentPlayer = "X";
        hasWinner = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText("");
            }
        }
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton();
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
                board[i][j] = button;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (((JButton) e.getSource()).getText().equals("")
                                && !hasWinner) {
                            button.setText(currentPlayer);
                            hasWinner();
                            togglePlayer();
                        }
                    }
                });
                pane.add(button);
            }
        }
    }

    private void togglePlayer() {
        if (currentPlayer.equals("X")) {
            currentPlayer = "O";
        } else {
            currentPlayer = "X";
        }
    }

    private void hasWinner() {
        //check vertical match
        if (board[0][0].getText().equals(currentPlayer) && board[1][0].getText().equals(currentPlayer)
                && board[2][0].getText().equals(currentPlayer)) {
            displayWinner();
        } else if (board[0][1].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer)
                && board[2][1].getText().equals(currentPlayer)) {
            displayWinner();
        } else if (board[0][2].getText().equals(currentPlayer) && board[1][2].getText().equals(currentPlayer)
                && board[2][2].getText().equals(currentPlayer)) {
            displayWinner();
            //check horizontal match
        } else if (board[0][0].getText().equals(currentPlayer) && board[0][1].getText().equals(currentPlayer)
                && board[0][2].getText().equals(currentPlayer)) {
            displayWinner();
        } else if (board[1][0].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer)
                && board[1][2].getText().equals(currentPlayer)) {
            displayWinner();
        } else if (board[2][0].getText().equals(currentPlayer) && board[2][1].getText().equals(currentPlayer)
                && board[2][2].getText().equals(currentPlayer)) {
            displayWinner();
        }
        //check diagonal match
        else if (board[0][0].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer)
                && board[2][2].getText().equals(currentPlayer)) {
            displayWinner();
        } else if (board[0][2].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer)
                && board[2][0].getText().equals(currentPlayer)) {
            displayWinner();
        }
        //check draw
        else if (!board[0][0].getText().isEmpty() && !board[0][1].getText().isEmpty()
                && !board[0][2].getText().isEmpty() && !board[1][0].getText().isEmpty()
                && !board[1][1].getText().isEmpty() && !board[1][2].getText().isEmpty()
                && !board[2][0].getText().isEmpty() && !board[2][1].getText().isEmpty()
                && !board[2][2].getText().isEmpty() && !hasWinner)
            JOptionPane.showMessageDialog(null, "It's a Tie !");
    }

    private void displayWinner() {
        JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won!");
        hasWinner = true;
    }
}
