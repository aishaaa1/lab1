import javax.swing.*;
import java.awt.*;

public class Frame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    JFrame frame = new JFrame();
    ControllerButtons cButtons;

    DrawPanel drawPanel = new DrawPanel(WIDTH, HEIGHT - 240);
    public Frame(String title) {
        //this.cButtons = cButtons;
        initComponents(title);
    }
    void initComponents(String title) {

        frame.setTitle(title);
        frame.setSize(new Dimension());
        frame.add(drawPanel, BorderLayout.NORTH);
        //frame.add(cButtons, BorderLayout.WEST);
        frame.setVisible(true);
        frame.pack();
        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        // Make sure the frame exits when "x" is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void moveFrame(int x, String modelName) {
        drawPanel.moveImage(x, modelName);
    }
    void repaintFrame() {
        drawPanel.repaint();
    }

    public int getWidth() {
        return WIDTH;
    }
}