
public class main {

    public static void main(String args[]){
        boardState test = new boardState(6,20);
        test.allAliveState();
        test.printBoard();
        test.nextState();
        test.printBoard();

        for (int i =0; i<=5; i++){
            for (int j =0 ;j<20;j++){
                System.out.print(test.calculateNeighbor(j,i));
            }
            System.out.println();
        }
    }
}
