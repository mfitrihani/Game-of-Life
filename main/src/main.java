
public class main {

    public static void main(String args[]){
        //test
        String[][] initialState1 = {{" "," "," "},{" "," "," "},{" "," "," "}};
        String[][] expectedNextState1 = {{" "," "," "},{" "," "," "},{" "," "," "}};
        String[][] initialState2 = {{" "," ","#"},{" ","#","#"},{" "," "," "}};
        String[][] expectedNextState2 = {{" ","#","#"},{" ","#","#"},{" "," "," "}};


        //test1
        boardState test1 = new boardState(initialState1);
        test1.nextState();
        if (checkEquality(expectedNextState1,test1.getBoard())){
            System.out.println("success");
        }
        else
            System.out.print("failure");

        //test2
        boardState test2 = new boardState(initialState2);
        test2.nextState();
        if (checkEquality(expectedNextState2,test2.getBoard())){
            System.out.println("success");
        }
        else
            System.out.print("failure");
    }


    public static Boolean checkEquality(String[][] x, String[][] y){
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++){
                if (!(x[i][j].equals(y[i][j]))){
                    System.out.println("coordinate:"+i+" "+j+"\nValue:"+y[i][j]);
                    return false;
                }
            }
        }
        return true;
    }
}
