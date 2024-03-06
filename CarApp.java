
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CarApp  {

    public static void main (String[] args) {
        // Instance of this class
        // Start a new view and send a reference of self

        VehicleModel model = new VehicleModel();
        CarView frame = new CarView("CarSim 1.0");

        //Model changes -> View updates
        model.addObservers(frame);
        model.addManagementObserver(frame);

        CarController cc = new CarController(model);
        CarManagementPanel cm = new CarManagementPanel(new CarManagementController(model));


        CompositePanel panel = new CompositePanel(cc, cm, frame.drawPanel);
        frame.add(panel);
        frame.pack();


        // Start the timer
        cc.startTimer();
    }

}
