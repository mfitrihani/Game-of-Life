import java.util.Random;

public class boardState {
    int height;
    int width;
    String[][] board;

    public boardState(int height, int width) {
        this.height = height;
        this.width = width;
        board = new String[this.height][this.width];
    }

    public void allDeathState(){
        for(int i = 0; i<height;i++){
            for (int j=0;j<width;j++){
                board[i][j]="-";
            }
        }
    }

    public void allAliveState(){
        for(int i = 0; i<height;i++){
            for (int j=0;j<width;j++){
                board[i][j]="+";
            }
        }
    }

    public void randomState(){
        Random random = new Random();
        String[] state = {"+","-"};

        for(int i = 0; i<height;i++){
            for (int j=0;j<width;j++){
                int rnd = new Random().nextInt(state.length);
                board[i][j]= state[rnd];
            }
        }
    }

    public void printBoard(){
        for(int i = 0; i<height;i++){
            for (int j=0;j<width;j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public String[][] getBoard() {
        return board;
    }
}
