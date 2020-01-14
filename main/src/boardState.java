import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class boardState {
    private ArrayList<Boolean[][]> previousBoards = new ArrayList<>(1000);
    private boardGui testGUI;
    private int speed = 100;
    private Boolean[][] board;

    public boardState(int width, int height) {
        this.board = new Boolean[height][width];
    }

    public boardState(Boolean[][] customBoard) {
        this.board = customBoard;
    }

    public boardState(String textFilePath) throws IOException {
        String extension = textFilePath.substring(textFilePath.lastIndexOf('.') + 1);
        if (extension.equals("txt"))
            readTextFile(textFilePath);
        if (extension.equals("rle"))
            readRleFile(textFilePath);
    }

    private void readRleFile(String textFilePath) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFilePath));
        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();
        //write file to stringBuilder
        while (line!=null){
            stringBuilder.append(line);
            line = bufferedReader.readLine();
        }
        //break into array of lines
        String[] temp = stringBuilder.toString().split("\\$");
        //find max width
        int max = 0, control = 0;
        for (String s : temp){
            String[] splitNumber = s.split("\\D+");
            for (String x : splitNumber){
                if (!x.equals(""))
                    control+=Integer.parseInt(x);
            }
            if (control>max)
                max = control;
            control = 0;
        }
        //split digits and characters
        ArrayList<Integer> temporaryDigit = new ArrayList<>();
        ArrayList<String> temporaryCharacter = new ArrayList<>();
        String[][] characters = new String[temp.length][max];
        Integer[][] digits = new Integer[temp.length][max];
        for (int x = 0 ; x < temp.length ; x++){
            String [] splitCharacters = temp[x].split("\\d");
            for (int y = 0; y < splitCharacters.length ; y++){
                if (!splitCharacters[y].equals(""))
                    temporaryCharacter.add(splitCharacters[y]);
            }
            characters[x] = temporaryCharacter.toArray(new String[0]);
            temporaryCharacter.clear();
            String[] splitNumber = temp[x].split("\\D+");
            for (int y = 0; y < splitNumber.length ; y++){
                if (!splitNumber[y].equals(""))
                    temporaryDigit.add(Integer.parseInt(splitNumber[y]));
            }
            digits[x] = temporaryDigit.toArray(new Integer[0]);
            temporaryDigit.clear();
        }
        //write into temporary board
        String[][] tempBoard = new String[temp.length][max];
        control = 0;
        for (int x = 0 ; x < digits.length ; x++){
            for (int y = 0 ; y < digits[x].length ; y++){
                for (int z = 0 ; z<digits[x][y] ; z++){

                }
            }
        }

        for (String[] s : characters){
            System.out.println(Arrays.toString(s));
        }
        System.out.println(max);
        System.out.println(Arrays.toString(temp));
    }

    private void readTextFile(String textFilePath) throws IOException {
        BufferedReader temp = new BufferedReader(new FileReader(textFilePath));
        StringBuilder stringBuilder = new StringBuilder();
        String line = temp.readLine();
        int max = 0;
        while (line != null) {
            if (line.length() > max)
                max = line.length();
            stringBuilder.append(line).append(" ");
            line = temp.readLine();
        }
        String[] temp1 = stringBuilder.toString().split(" ");
        String[][] tempBoard = new String[temp1.length][max];
        for (int x = 0; x < temp1.length; x++) {
            for (int y = 0; y < temp1[x].length(); y++) {
                tempBoard[x][y] = String.valueOf(temp1[x].charAt(y));
            }
        }
        for (int x = 0; x < tempBoard.length; x++) {
            for (int y = 0; y < tempBoard[0].length; y++) {
                if (tempBoard[x][y] == null)
                    tempBoard[x][y] = ".";
            }
        }

        this.board = new Boolean[tempBoard.length][tempBoard[0].length];

        for (int x = 0; x < tempBoard.length; x++) {
            for (int y = 0; y < tempBoard[0].length; y++) {
                board[x][y] = tempBoard[x][y].equals("O");
            }
        }
    }

    public void render() {
        testGUI = new boardGui(board);
        //play button
        testGUI.Play.addActionListener(e -> play());
        //next button
        testGUI.nextButton.addActionListener(e -> {
            testGUI.generationCounter++;
            nextState();
            testGUI.repaint();
        });
        //undo button
        testGUI.undoButton.addActionListener(e -> {
            if (previousBoards.size() < 1) {
                JOptionPane.showMessageDialog(testGUI, "Previous state does not exist");
            } else {
                testGUI.generationCounter--;
                undo();
                testGUI.repaint();
            }
        });
        //speed slider
        testGUI.speedSlider.addChangeListener(e -> changeSpeed(testGUI.speedSlider.getValue()));

        JFrame frame = new JFrame();
        frame.getContentPane().add(testGUI);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public void changeSpeed(int speed) {
        this.speed = speed;
        //speed viewer
        testGUI.speedViewer.setText(speed + " ms");
    }

    public void play() {
        new Timer(speed, e -> {
            testGUI.generationCounter++;
            nextState();
            testGUI.repaint();
        }).start();
    }

    public void undo() {
        Boolean[][] temp = previousBoards.get(previousBoards.size() - 1);
        for (int x = 0; x < board.length; x++) {
            this.board[x] = Arrays.copyOf(temp[x], temp[x].length);
        }
        previousBoards.remove(previousBoards.size() - 1);
    }

    public void nextState() {
        previousBoards.add(board.clone());
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
                if ((y == 0) && (x == 0) || (((x + width) < 0) || (y + height < 0)) || (x + width) >= board[0].length || (y + height) >= board.length) {
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
    }

    public void allAliveState() {
        for (int x = 0; x < board.length; x++) {
            Arrays.fill(board[x], true);
        }
    }

    public void allDeathState() {
        for (int x = 0; x < board.length; x++) {
            Arrays.fill(board[x], false);
        }
    }

    public void printBoard() {
        for (Boolean[] s : board) {
            System.out.println(Arrays.toString(s));
        }
        System.out.println("________________________________________");
    }
}
