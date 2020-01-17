import javax.swing.*;

public class boardGui{
    private int height;
    private int width;
    private Boolean[][] state;
    int generationCounter = 0;
    JButton Play;
    JButton nextButton;
    JSlider speedSlider;
    private JLabel speedLabel;
    JLabel speedViewer;
    JButton undoButton;
    private JPanel mainUI;
    private int sizePreference = 1;

    public boardGui(Boolean[][] state){
        this.state= state;
        this.height = state.length;
        this.width = state[0].length;
        autoResize();
        //add to frame
        JFrame main = new JFrame();
        main.add(mainUI);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLocationByPlatform(true);
        main.setVisible(true);
        main.pack();
    }

    private void autoResize() {
        sizePreference = 500/Math.max(width, height);
        if (sizePreference<1) sizePreference = 1;
    }


//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Color gColor = g.getColor();
//
//        g.drawString("Generation: " + generationCounter, 0, 10);
//        for (int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//                if (mainUI[j][i]) {
//                    g.setColor(Color.white);
//                    g.fillRect(i * sizePreference, j * sizePreference+70, sizePreference, sizePreference);
//                }
//            }
//        }
//
//        g.setColor(gColor);
//    }
}

