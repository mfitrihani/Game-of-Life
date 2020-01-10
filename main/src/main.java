import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        boardState test = new boardState(50,50);
        test.randomState();
        test.render();
    }


}
