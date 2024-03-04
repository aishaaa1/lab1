import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CarView extends JFrame implements CarObserver, CarManagementObserver, WorkShopObserver, WorkShopManagementObserver {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    DrawPanel drawPanel;
    WorkShopModel workShopModel;

    public CarView(String title, WorkShopModel workShopModel) {
        initComponents(title);
        this.workShopModel = workShopModel;
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
    public void updateVehicle(String car, int x) {
        drawPanel.moveImage(x, car);
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
    public void updateView(WorkShopState state) {
        if (state instanceof NotInWorkShopState) {
            workShopModel.leaveCarAtWorkShop();

        }
        else if (state instanceof InWorkShopState){
            workShopModel.getCarFromWorkShop();

        }
    }

    //TODO
    /*
    * How to remove the vehicles from the draw panel
     */
    @Override
    public void actOnLeaveCarToWorkShop() {

    }

    @Override
    public void actOnGetCarFromWorkShop() {

    }
}