
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CarApp  {

    public static void main(String[] args) {
        // Instance of this class
        // Start a new view and send a reference of self
        WorkShopModel workShopModel = new WorkShopModel(new Volvo240(), new CarRepairShop<>(10));
        VehicleModel model = new VehicleModel();
        CarView frame = new CarView("CarSim 1.0", workShopModel);
/*
        workShopModel.addObservers(frame);
        workShopModel.addManagementObserver(frame);
*/

        //Model changes -> View updates
        workShopModel.addObservers(frame);
        workShopModel.addManagementObserver(frame);
        //WorkShopManagementPanel wm = new WorkShopManagementPanel(new WorkShopManagementController(workShopModel));

        model.addObservers(frame);
        model.addManagementObserver(frame);
        CarController cc = new CarController(model, workShopModel);
        ManagementPanel managementPanel = new ManagementPanel(new CarManagementController(model), new WorkShopManagementController(workShopModel));


        CompositePanel panel = new CompositePanel(cc, managementPanel, frame.drawPanel);
        frame.add(panel);
        frame.pack();

        // Start the timer
        cc.startTimer();
    }

}
