import java.util.Arrays;
import java.util.Random;

public class boardState {
    int height;
    int width;
    String[][] board,tempBoard;

    public boardState(int height, int width) {
        this.height = height;
        this.width = width;
        board = new String[this.height][this.width];
        tempBoard = new String[this.height][this.width];
    }

    public boardState(String[][] board) {
        this.board = board;
        tempBoard = new String[this.height][this.width];
    }

    public void allDeathState(){
        for(int i = 0; i<height;i++){
            for (int j=0;j<width;j++){
                board[i][j]=" ";
            }
        }
    }

    public void allAliveState(){
        for(int i = 0; i<height;i++){
            for (int j=0;j<width;j++){
                board[i][j]="#";
            }
        }
    }

    public void randomState(){
        String[] state = {"#"," "};

        for(int i = 0; i<height;i++){
            for (int j=0;j<width;j++){
                int rnd = new Random().nextInt(state.length);
                board[i][j]= state[rnd];
            }
        }
    }

    public void nextState(){
        for(int i = 0; i<height;i++){
            for (int j=0;j<width;j++){
                tempBoard[i][j]=board[i][j];
            }
        }

        for(int i = 0; i<height;i++){
            for (int j=0;j<width;j++){
                calculateState(i,j);
            }
        }
        for(int i = 0; i<height;i++){
            for (int j=0;j<width;j++){
                tempBoard[i][j]=null;
            }
        }
    }


    public void calculateState(int yPosition, int xPosition){
        int totalNeighbor = calculateNeighbor(yPosition,xPosition);
        if(tempBoard[yPosition][xPosition].equals("#")){
            if(totalNeighbor<2){
                tempBoard[yPosition][xPosition]=" ";
            }
            else if(totalNeighbor==2||totalNeighbor==3){
                tempBoard[yPosition][xPosition]= "#";
            }
            else if(totalNeighbor>3){
                tempBoard[yPosition][xPosition]= " ";
            }
        }
        else if (tempBoard[yPosition][xPosition].equals(" ")&&totalNeighbor==3){
            tempBoard[yPosition][xPosition]= "#";
        }
        else
            tempBoard[yPosition][xPosition]= " ";

    }

    public int calculateNeighbor(int y_position,int x_position){
        int totalNeighbor=0;

        for (int x = -1;x<=1;x++){
            for (int y = -1;y<=1;y++){
                if ((x_position+x<0)||(y_position+y<0)||(x_position+x>width-1)||(y_position+y>height-1)||(x==0&&y==0)){
                    continue;
                 }
                if (board[y_position+y][x_position+x].equals("#")){
                    totalNeighbor++;
                }
            }
        }
        return totalNeighbor;
    }

    public void printBoard(){
        for(int i = 0; i<height;i++){
            for (int j=0;j<width;j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println("------------------------------");
    }

    public String[][] getBoard() {
        return board;
    }

    public String getPartBoard(int x,int y) {
        return board[x][y];
    }

}
