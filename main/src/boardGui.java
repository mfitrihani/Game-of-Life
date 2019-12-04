import javax.swing.*;
import java.awt.*;
import java.beans.Transient;

public class boardGui extends JPanel {
    private int height;
    private int width;
    private String[][] board;

    public boardGui(String[][] board,int height,int width){
        this.board = board;
        this.height = height;
        this.width = width;
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
                if (board[j][i].equals("#")) {
                    g.setColor(Color.red);
                    g.fillRect(i * 4, j * 4, 4, 4);
                }
            }
        }

        g.setColor(gColor);
    }
}
