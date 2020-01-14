import javax.swing.*;
import java.awt.*;
import java.beans.Transient;

public class boardGui extends JPanel {
    private int height;
    private int width;
    private Boolean[][] board;
    private int generationCounter=0;
    JButton Play;
    JButton nextButton;
    JSlider speedSlider;
    private JLabel speedLabel;

    public boardGui(Boolean[][] board){
        this.board = board;
        this.height = board.length;
        this.width = board[0].length;
        super.add(Play);
        super.add(nextButton);
        super.add(speedSlider);
        super.add(speedLabel);
    }

    @Override
    @Transient
    public Dimension getPreferredSize() {
        return new Dimension(width * 4, height * 4+30);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color gColor = g.getColor();

        g.drawString("Generation: " + generationCounter++, 0, 10);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (board[j][i]) {
                    g.setColor(Color.red);
                    g.fillRect(i * 1, j * 1+30, 1, 1);
                }
            }
        }

        g.setColor(gColor);
    }
}

