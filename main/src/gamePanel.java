import javax.swing.*;
import java.awt.*;

public class gamePanel extends JPanel {
    Boolean[][] board;
    JPanel gameBoard;

    public gamePanel(Boolean[][] board){
        add(gameBoard);
        this.board = board;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color gColor = g.getColor();

        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i]) {
                    g.setColor(Color.white);
                    g.fillRect(i , j , 4, 4);
                }
            }
        }

        g.setColor(gColor);
    }
}
