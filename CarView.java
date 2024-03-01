import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CarView extends JFrame implements CarObserver, CarManagementObserver {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    DrawPanel drawPanel;
    public CarView(String title, List<VehicleImage> images) {
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
    public void updateVehicle(String car, int x) {
        drawPanel.moveImage(x, car);
    }

    @Override
    public void actOnCarAdded(VehicleImage vehicleImage) { drawPanel.addImage(vehicleImage);

    }

    @Override
    public void actOnCarRemoved() {drawPanel.removeImage();}
}