import javax.swing.*;
import java.awt.*;

public class Frame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    JFrame frame = new JFrame();

    CarView carView = new CarView(WIDTH, HEIGHT);
    DrawPanel drawPanel = new DrawPanel(WIDTH, HEIGHT - 240);
    public Frame(String title) {
        initComponents(title);
    }
    void initComponents(String title) {
        frame.setTitle(title);
        frame.setSize(new Dimension());
        frame.add(drawPanel, BorderLayout.NORTH);
        frame.add(carView, BorderLayout.WEST);
        frame.setVisible(true);
        frame.pack();
    }
    void moveFrame(int x, int y, String modelName) {
        drawPanel.moveImage(x, y , modelName);
    }
    void repaintFrame() {
        drawPanel.repaint();
    }

    public int getWidth() {
        return WIDTH;
    }
}