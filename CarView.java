import javax.swing.*;
import java.util.Collection;

public class CarView extends JFrame implements CarObserver {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    DrawPanel drawPanel;
    public CarView(String title, Collection<VehicleImage> images) {
        initComponents(title);
        this.drawPanel = new DrawPanel(WIDTH, HEIGHT - 240, images);

    }
    void initComponents(String title) {
        this.setTitle(title);
        this.setVisible(true);
    }

    public int getWidth() {
        return WIDTH;
    }

    @Override
    public void update(String car, int x) {
        drawPanel.moveImage(x, car);
    }
}