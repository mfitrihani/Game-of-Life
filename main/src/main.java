public class main {

    public static void main(String[] args) {
        boardState test = new boardState(3,3);
        test.allAliveState();
        test.printBoard();
        test.nextState();
        test.printBoard();
    }

}
