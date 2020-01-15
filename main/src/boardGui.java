import javax.swing.*;
import java.awt.*;
import java.beans.Transient;

public class boardGui extends JPanel {
    private int height;
    private int width;
    private Boolean[][] board;
    int generationCounter=0;
    JButton Play;
    JButton nextButton;
    JSlider speedSlider;
    private JLabel speedLabel;
    JLabel speedViewer;
    JButton undoButton;
    private int sizePreference =1 ;

    public boardGui(Boolean[][] board){
        this.board = board;
        this.height = board.length;
        this.width = board[0].length;
        add(Play);
        add(nextButton);
        add(undoButton);
        add(speedLabel);
        add(speedSlider);
        add(speedViewer);
        autoResize();
    }

    private void autoResize() {
        sizePreference = 500/Math.max(width, height);
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

        g.drawString("Generation: " + generationCounter, 0, 10);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (board[j][i]) {
                    g.setColor(Color.red);
                    g.fillRect(i * sizePreference, j * sizePreference+30, sizePreference, sizePreference);
                }
            }
        }

        g.setColor(gColor);
    }
}

