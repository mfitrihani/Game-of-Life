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
    private int sizePreference =1 ;

    public boardGui(Boolean[][] board){
        this.board = board;
        this.height = board.length;
        this.width = board[0].length;
        super.add(Play);
        super.add(nextButton);
        super.add(speedSlider);
        super.add(speedLabel);
        autoResize();
    }

    private void autoResize() {
        if (width<=50||height<=50)
            sizePreference = 8;
        if (width>=100||height>=100)
            sizePreference = 5;
        if (width>=200||height>=200)
            sizePreference = 4;
        if (width>=300||height>=300)
            sizePreference = 3;
        if (width>=400||height>=400)
            sizePreference = 2;
        if (width>=500||height>=500)
            sizePreference = 1 ;
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
                    g.fillRect(i * sizePreference, j * sizePreference+30, sizePreference, sizePreference);
                }
            }
        }

        g.setColor(gColor);
    }
}

