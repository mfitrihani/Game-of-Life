public class boardState {
    int height;
    int width;
    String[][] board;

    public boardState(int height, int width) {
        this.height = height;
        this.width = width;
        board = new String[this.height][this.width];
    }

    public String[][] allDeathState(){
        for(int i = 0; i<height;i++){
            for (int j=0;j<width;j++){
                board[i][j]="-";
            }
        }
        return board;
    }

    public String[][] allAliveState(){
        for(int i = 0; i<height;i++){
            for (int j=0;j<width;j++){
                board[i][j]="-";
            }
        }
        return board;
    }
}
