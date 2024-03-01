import javax.swing.*;
import java.awt.*;

public class Frame implements  IControllerObserver{
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    JFrame jframe = new JFrame();
    DrawPanel drawPanel = new DrawPanel(WIDTH, HEIGHT - 240);
    public Frame(String title) {
        initComponents(title);
    }
    void initComponents(String title) {
        jframe.setTitle(title);
        jframe.setSize(new Dimension());
        jframe.add(drawPanel, BorderLayout.NORTH);
        //jframe.add(carview, BorderLayout.WEST);
        jframe.setVisible(true);
        jframe.pack();
    }
    void moveFrame(int x, String modelName) {
        drawPanel.moveImage(x , modelName);
    }
    void repaintFrame() {
        drawPanel.repaint();
    }
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public void update(IModelObservable car) {
        int x = car.getPosition().getX();
        this.moveFrame(x, car.getModelName());
        this.repaintFrame();

    }
    public boolean notWithinBounds(Position p, Direction dir, Vehicle v){
        boolean leftScreen = 0 > p.getX() && Direction.WEST == dir;
        boolean rightScreen = this.getWidth() < p.getX() + this.drawPanel.getVehicleWidth(v.getModelName()) && Direction.EAST == dir;
        return leftScreen || rightScreen;
    }

}