import java.util.ArrayList;

public class CarApp {

    public static void main(String[] args) {
        // Instance of this class
        // Start a new view and send a reference of self
        ArrayList<Vehicle> cars = new ArrayList<>();
        cars.add(new Volvo240());
        cars.add(new Saab95());
        cars.add(new Scania());
        //ControllerButtons cButtons = new ControllerButtons();
        Frame frame = new Frame("CarSim 1.0");
        CarController cc = new CarController(cars, frame);
        // Start the timer
        cc.startTimer();
    }
}
