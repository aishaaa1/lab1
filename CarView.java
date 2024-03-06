import javax.swing.*;


public class CarView extends JFrame implements CarObserver, CarManagementObserver {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    DrawPanel drawPanel;


    public CarView(String title) {
        initComponents(title);
        this.drawPanel = new DrawPanel(WIDTH, HEIGHT - 240);

    }

    void initComponents(String title) {
        this.setTitle(title);
        this.setVisible(true);
    }

    public int getWidth() {
        return WIDTH;
    }

    @Override
    public void updateVehicle() {
        drawPanel.moveImage();
    }

    @Override
    public void actOnCarAdded(VehicleImage vehicleImage) {
        drawPanel.addImage(vehicleImage);
    }

    @Override
    public void actOnCarRemoved() {
        drawPanel.removeImage();
    }
    @Override
    public void actOnCarRemoved(VehicleImage image) {
        drawPanel.removeThisImage(image);
    }



    }
