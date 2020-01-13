import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class boardState {
    boardGui testGUI;
    private Boolean[][] board, previousBoard;

    public boardState(int width, int height) {
        this.board = new Boolean[height][width];
        this.previousBoard = new Boolean[height][width];

    }

    public boardState(Boolean[][] customBoard) {
        this.board = customBoard;
        this.previousBoard = customBoard;
    }

    public boardState(String textFilePath) throws IOException {
        BufferedReader temp = new BufferedReader(new FileReader(textFilePath));
        StringBuilder stringBuilder = new StringBuilder();
        String line = temp.readLine();
        while (line != null) {
            stringBuilder.append(line).append(" ");
            line = temp.readLine();
        }
        String[] temp1 = stringBuilder.toString().split(" ");
        String[][] tempBoard = new String[temp1.length][temp1[0].length()];
        for (int x = 0; x < temp1.length; x++) {
            tempBoard[x] = temp1[x].split("");
        }

        this.board = new Boolean[tempBoard.length][tempBoard[0].length];

        for (int x = 0; x < tempBoard.length; x++) {
            for (int y = 0; y < tempBoard[0].length; y++) {
                board[x][y] = tempBoard[x][y].equals("*");
            }
        }
        previousBoard = new Boolean[tempBoard.length][tempBoard[0].length];
    }

    public void render() {
        testGUI = new boardGui(board);
        testGUI.setLayout(null);
        //play button
        JButton play = new JButton();
        play.addActionListener(e -> play());
        play.setBorder(null);
        play.setLocation(0, 10);
        play.setText("Play");
        play.setSize(50, 20);
        testGUI.add(play);
        //next button
        JButton next = new JButton();
        next.addActionListener(e -> {
            nextState();
            testGUI.repaint();
        });
        next.setBorder(null);
        next.setLocation(60,10);
        next.setText("Next");
        next.setSize(50, 20);
        testGUI.add(next);

        JFrame frame = new JFrame();
        frame.getContentPane().add(testGUI);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public void play() {
        new Timer(100, e -> {
            nextState();
            testGUI.repaint();
        }).start();
    }

    public void undo() {
        for (int x = 0; x < board.length; x++) {
            board[x] = Arrays.copyOf(previousBoard[x], previousBoard[x].length);
            Arrays.fill(previousBoard[x], null);
        }
    }

    public void nextState() {
        //copy board to previous board
        for (int x = 0; x < board.length; x++)
            previousBoard[x] = Arrays.copyOf(board[x], board[x].length);
        //next state
        Boolean[][] tempBoard = new Boolean[board.length][board[0].length];
        for (int x = 0; x < tempBoard.length; x++) {
            for (int y = 0; y < tempBoard[0].length; y++) {
                tempBoard[x][y] = calculateState(y, x);
            }
        }
        //save
        for (int x = 0; x < board.length; x++) {
            board[x] = Arrays.copyOf(tempBoard[x], tempBoard[x].length);
        }
    }

    public Boolean calculateState(int width, int height) {
        int totalNeighbor = calculateNeighbor(width, height);
        if (board[height][width]) {
            return totalNeighbor == 2 || totalNeighbor == 3;
        } else {
            return totalNeighbor == 3;
        }
    }

    public int calculateNeighbor(int width, int height) {
        int neighbor = 0;
        for (int y = -1; y < 2; y++) {
            for (int x = -1; x < 2; x++) {
                if ((y == 0) && (x == 0)) {
                } else if (((x + width) < 0) || ((y + height) < 0)) {
                } else if ((x + width) >= board[0].length || (y + height) >= board.length) {
                } else if (board[y + height][x + width])
                    neighbor++;
            }
        }
        return neighbor;
    }

    public void randomState() {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                board[x][y] = new Random().nextBoolean();
            }
        }
        for (int x = 0; x < board.length; x++) {
            previousBoard[x] = Arrays.copyOf(board[x], board[x].length);
        }
    }

    public void allAliveState() {
        for (int x = 0; x < board.length; x++) {
            Arrays.fill(board[x], true);
            Arrays.fill(previousBoard[x], true);
        }
    }

    public void allDeathState() {
        for (int x = 0; x < board.length; x++) {
            Arrays.fill(board[x], false);
            Arrays.fill(previousBoard[x], false);
        }
    }

    public void printBoard() {
        for (Boolean[] s : board) {
            System.out.println(Arrays.toString(s));
        }
        System.out.println();
        System.out.println("________________________________________");
        for (Boolean[] s : previousBoard) {
            System.out.println(Arrays.toString(s));
        }
        System.out.println();
    }

    public int getWidth() {
        return board[0].length;
    }

    public int getHeight() {
        return board.length;
    }
}
