
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CarApp  {
    private static  VehicleFactory vehicleFactory = new VehicleFactory();
    private static VehicleImageFactory vehicleImageFactory = new VehicleImageFactory();
    public static void main(String[] args) {
        // Instance of this class
        // Start a new view and send a reference of self

        VehicleImage volvoImage = vehicleImageFactory.createVolvoImage(0,0);
        Volvo240 volvo240 = vehicleFactory.createVolvo();
        VehicleModel model = new VehicleModel(volvo240);
        CarView frame = new CarView("CarSim 1.0", volvoImage);


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
