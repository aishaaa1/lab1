import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController implements CarObserver   {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)

    private final CarManager carManager;
    private final ControllerButtons cButtons;
    private final Frame frame;
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer = new Timer(delay, new TimerListener());


    // The frame that represents this instance View of the MVC patter
    // A list of cars, modify if needed
    private final ArrayList<Vehicle> cars;


    public CarController(ArrayList<Vehicle> cars, Frame frame){
        this.cars = cars;
        this.carManager = new CarManager(cars);
        this.cButtons = new ControllerButtons();
        this.frame = frame;

        cButtons.addObserver(this);

    }


    /* CarApp needs to call the startTimer method to start timer. */
    public void startTimer() {
        this.timer.start();
    }

    @Override
    public void notifyObservers(Actions action) {
        switch (action) {
            case GAS -> carManager.gas(cButtons.getGasAmount());
            case BRAKE -> carManager.brake(cButtons.getGasAmount());
            case START -> carManager.startAllCars();
            case STOP -> carManager.stopAllCars();
            case LIFT -> carManager.liftBedButton();
            case LOWER -> carManager.lowerBedButton();
            case TURBOOFF -> carManager.saabTurboOff();
            case TURBOON -> carManager.saabTurboOn();
        }
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                if(notWithinBounds(car.getPosition(), car.getDirection(), car)) {
                    if (car instanceof Volvo240) {
                        car.stopEngine();
                    }
                    else {
                        reverseVehicle(car);
                    }
                }
                car.move();
                int x = car.getPosition().getX();
                frame.moveFrame(x, car.getModelName());
                frame.repaintFrame();
            }
        }
    }

    public void reverseVehicle(Vehicle car){
        car.stopEngine();
        car.turnLeft();
        car.turnLeft();
        car.startEngine();
        car.gas(0.5);
    }

    public boolean notWithinBounds(Position p, Direction dir, Vehicle v){
        boolean leftScreen = 0 > p.getX() && Direction.WEST == dir;
        boolean rightScreen = frame.getWidth() < p.getX() + frame.drawPanel.getVehicleWidth(v.getModelName()) && Direction.EAST == dir;
        return leftScreen || rightScreen;
    }


}
