import java.util.ArrayList;

public class CarApp {
    public static VehicleFactory factory = new VehicleFactory();
    public static void main(String[] args) {
        // Instance of this class
        // Start a new view and send a reference of self
        ArrayList<Vehicle> cars = new ArrayList<>();
        cars.add(factory.createVolvo());
        cars.add(factory.createSaab());
        cars.add(factory.createScania());
        Frame frame = new Frame("CarSim 1.0");
        CarController cc = new CarController(frame, cars);
        // Start the timer
        cc.startTimer();
    }
}
