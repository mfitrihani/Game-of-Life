import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main {

    public static void main(String[] args) {
        boardState test = new boardState(3,3);
        test.allAliveState();

        boardGui testGUI = new boardGui(test.getBoard());

        JFrame frame = new JFrame();
        frame.getContentPane().add(testGUI);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //test.nextState();
                testGUI.repaint();
            }
        }).start();
    }


}
