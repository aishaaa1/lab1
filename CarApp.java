
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CarApp  {
    public final static VehicleFactory factory = new VehicleFactory();
    private final static VehicleImageFactory imageFactory = new VehicleImageFactory();
    public static void main(String[] args) {
        // Instance of this class
        // Start a new view and send a reference of self
        ArrayList<Vehicle> cars = new ArrayList<>();
        cars.add(factory.createVolvo());
        cars.add(factory.createSaab());
        cars.add(factory.createScania());

        List<VehicleImage> vehicleImages = new ArrayList<>();
        vehicleImages.add(imageFactory.createVolvoImage(0,0));
        vehicleImages.add(imageFactory.createSaabImage(0, 100));
        vehicleImages.add(imageFactory.createScaniaImage(0, 200));

        VehicleModel model = new VehicleModel(cars);
        CarView frame = new CarView("CarSim 1.0", vehicleImages);

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
