import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JPanel implements ActionListener {
    private JButton[][] buttons;
    private char currentPlayer;
    private JLabel statusLabel;

    public TicTacToe() {
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 50));
                buttons[i][j].addActionListener(this);
                gridPanel.add(buttons[i][j]);
            }
        }

        statusLabel = new JLabel("X's turn");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);
        frame.setVisible(true);

        currentPlayer = 'X';
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.getText().equals("")) {
            button.setText(Character.toString(currentPlayer));
            if (checkWin()) {
                statusLabel.setText(currentPlayer + " wins!");
                disableButtons();
            } else if (checkDraw()) {
                statusLabel.setText("It's a draw!");
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                statusLabel.setText(currentPlayer + "'s turn");
            }
        }
    }

    private boolean checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(currentPlayer + "") &&
                buttons[i][1].getText().equals(currentPlayer + "") &&
                buttons[i][2].getText().equals(currentPlayer + "")) {
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (buttons[0][i].getText().equals(currentPlayer + "") &&
                buttons[1][i].getText().equals(currentPlayer + "") &&
                buttons[2][i].getText().equals(currentPlayer + "")) {
                return true;
            }
        }
        // Check diagonals
        if (buttons[0][0].getText().equals(currentPlayer + "") &&
            buttons[1][1].getText().equals(currentPlayer + "") &&
            buttons[2][2].getText().equals(currentPlayer + "")) {
            return true;
        }
        if (buttons[0][2].getText().equals(currentPlayer + "") &&
            buttons[1][1].getText().equals(currentPlayer + "") &&
            buttons[2][0].getText().equals(currentPlayer + "")) {
            return true;
        }
        return false;
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}

