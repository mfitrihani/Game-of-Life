import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class boardState {
    private int height;
    private int width;
    private String[][] board, tempBoard;

    public boardState(int height, int width) {
        this.height = height;
        this.width = width;
        board = new String[this.height][this.width];
        tempBoard = new String[this.height][this.width];
    }

    public boardState(String[][] board) {
        height = board.length;
        width = board[0].length;
        tempBoard = new String[this.height][this.width];
        this.board = board;
    }

    public boardState(String textFileDirectory) throws IOException {
        board = new String[100][100];
        getFromTextFile(textFileDirectory);
    }

    public void getFromTextFile(String textFileDirectory) throws IOException {
        BufferedReader temp = new BufferedReader(new FileReader(new File(textFileDirectory)));
        StringBuilder build = new StringBuilder();
        String line = temp.readLine();
        while (line != null) {
            build.append(line);
            build.append(" ");
            line = temp.readLine();
        }
        String[] temp2 = build.toString().split(" ");
        String[][] board = new String[temp2.length][temp2[0].length()];
        tempBoard = new String[temp2.length][temp2[0].length()];
        for (int x = 0; x < temp2.length; x++) {
            board[x] = temp2[x].split("");
        }
        height = board.length;
        width = board[0].length;
        for (int x = 0 ; x < height ; x++){
            for (int y =0 ; y< width;y++){
                if (board[x][y]!="#") board[x][y] = " ";
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void allDeathState() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = " ";
            }
        }
    }

    public void allAliveState() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = "#";
            }
        }
    }

    public void customStateToad(){
        board = new String[][]{
                {" "," "," "," "," "," "},
                {" "," "," "," "," "," "},
                {" "," ","#","#","#"," "},
                {" ","#","#","#"," "," "},
                {" "," "," "," "," "," "},
                {" "," "," "," "," "," "},};

        tempBoard = new String[][]{
                {" "," "," "," "," "," "},
                {" "," "," "," "," "," "},
                {" "," ","#","#","#"," "},
                {" ","#","#","#"," "," "},
                {" "," "," "," "," "," "},
                {" "," "," "," "," "," "},};

        height = board.length;
        width = board[0].length;
    }

    public void randomState() {
        String[] state = {"#", " "};

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rnd = new Random().nextInt(state.length);
                board[i][j] = state[rnd];
            }
        }
    }

    public void nextState() {
        for (int i = 0; i < height; i++) {
            if (width >= 0) System.arraycopy(board[i], 0, tempBoard[i], 0, width);
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                calculateState(i, j);
            }
        }

        for (int i = 0; i < height; i++) {
            if (width >= 0) System.arraycopy(tempBoard[i], 0, board[i], 0, width);
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tempBoard[i][j] = null;
            }
        }
    }


    public void calculateState(int yPosition, int xPosition) {
        int totalNeighbor = calculateNeighbor(yPosition, xPosition);
        if (tempBoard[yPosition][xPosition].equals("#")) {
            if (totalNeighbor < 2) {
                tempBoard[yPosition][xPosition] = " ";
            } else if (totalNeighbor == 2 || totalNeighbor == 3) {
                tempBoard[yPosition][xPosition] = "#";
            } else {
                tempBoard[yPosition][xPosition] = " ";
            }
        } else if (tempBoard[yPosition][xPosition].equals(" ") && totalNeighbor == 3) {
            tempBoard[yPosition][xPosition] = "#";
        } else
            tempBoard[yPosition][xPosition] = " ";

    }

    public int calculateNeighbor(int y_position, int x_position) {
        int totalNeighbor = 0;

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if ((x_position + x < 0) || (y_position + y < 0) || (x_position + x > width - 1) || (y_position + y > height - 1) || (x == 0 && y == 0)) {
                    continue;
                }
                if (board[y_position + y][x_position + x].equals("#")) {
                    totalNeighbor++;
                }
            }
        }
        return totalNeighbor;
    }

    public void printBoard() {
        for (String[] x: board){
            for (String y: x){
                System.out.print(y);
            }
            System.out.println();
        }
    }

    public String[][] getBoard() {
        return board;
    }

    public String getPartBoard(int x, int y) {
        return board[x][y];
    }

}
