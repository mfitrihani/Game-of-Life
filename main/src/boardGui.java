import javax.swing.*;
import java.awt.*;
import java.beans.Transient;

public class boardGui extends JPanel {
    int height;
    int width;
    String[][] board;

    public boardGui(String[][] board){
        this.board = board;
        this.height = board[0].length;
        this.width = board.length;
    }

    @Override
    @Transient
    public Dimension getPreferredSize() {
        return new Dimension(width * 4, height * 4);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color gColor = g.getColor();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (board[i][j].equals("#")) {
                    g.setColor(Color.red);
                    g.fillRect(j * 4, i * 4, 4, 4);
                }
            }
        }

        g.setColor(gColor);
    }
}
