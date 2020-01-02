import javax.swing.*;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        boardState test = new boardState(50,50);
        test.randomState();
        test.printBoard();
        boardGui testGUI = new boardGui(test.getBoard(),test.getHeight(),test.getWidth());

        JFrame frame = new JFrame();
        frame.getContentPane().add(testGUI);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        new Timer(100, e -> {
            test.nextState();
            testGUI.repaint();
        }).start();

    }


}
