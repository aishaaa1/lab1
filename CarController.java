import javax.swing.*;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController implements IModelObserver,IControllerObservable   {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)

    private final CarManager carManager;
    private final ControllerButtons cButtons;
    //private final Frame frame;
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer = new Timer(delay, new TimerListener());


    // The frame that represents this instance View of the MVC patter
    // A list of cars, modify if needed
    private final ArrayList<Vehicle> cars;


    public CarController (Frame frame, ArrayList<Vehicle> cars){
        // this.frame = frame;
        this.cars = cars;
        this.carManager = new CarManager(cars);
        this.cButtons = new ControllerButtons();
        //cButtons.addObserver(this);

    }


    /* CarApp needs to call the startTimer method to start timer. */
    public void startTimer() {
        this.timer.start();
    }

    @Override
    public void update(IModelObservable car) {
        this.notifyObserevers(car);
    }
    ArrayList <IControllerObserver> observers ;

    @Override
    public void add(IControllerObserver observer) {
        observers.add(observer);
    }

    @Override
    public void remove(IControllerObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserevers(IModelObservable car) {
        for(IControllerObserver observer : observers){
            observer.update(car);
        }

    }


    public void takeAction(Actions action) {
        switch (action) {
            case Actions.GAS:
                carManager.gas(cButtons.getGasAmount());
                break;
            case Actions.BRAKE:
                carManager.brake(cButtons.getGasAmount());
                break;
            case Actions.START:
                carManager.startAllCars();
                break;
            case Actions.STOP:
                carManager.stopAllCars();
                break;
            case Actions.LIFT:
                carManager.liftBedButton();
                break;
            case Actions.LOWER:
                carManager.lowerBedButton();
                break;
            case Actions.TURBOOFF:
                carManager.saabTurboOff();
                break;
            case Actions.TURBOON:
                carManager.saabTurboOn();
                break;
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
                // frame.moveFrame(x, car.getModelName());
                // frame.repaintFrame();

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
        CarView frame = null;
        boolean rightScreen = frame.getWidth() < p.getX() + frame.drawPanel.getVehicleWidth(v.getModelName()) && Direction.EAST == dir;
        return leftScreen || rightScreen;
    }


}
