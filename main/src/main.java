import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        boardState test = new boardState("Resources/3-engine Cordership rake.txt");
        test.render();
    }
}
