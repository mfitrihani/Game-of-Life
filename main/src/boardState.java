import java.util.Random;

public class boardState {
    int height; //y
    int width; //x
    String[][] board; //[x][y]

    public boardState(int height, int width) {
        this.height = height;
        this.width = width;
        board = new String[this.height][this.width];
    }

    public boardState(String[][] board) {
        this.board = board;
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
                board[i][j]=calculateState(i,j);
            }
        }
    }


    public String calculateState(int yPosition, int xPosition){
        int totalNeighbor = calculateNeighbor(yPosition,xPosition);
        String nextState = "?";
        if(board[yPosition][xPosition].equals("#")){
            System.out.println("true");
            if(totalNeighbor<2){
                nextState = " ";
            }
            else if(totalNeighbor==2||totalNeighbor==3){
                nextState = "#";
            }
            else if(totalNeighbor>3){
                nextState = " ";
            }
        }
        else if (board[yPosition][xPosition].equals(" ")&&totalNeighbor==3){
            nextState = "#";
        }
        else
            nextState = " ";

        return nextState;
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
