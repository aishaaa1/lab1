import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController implements ActionButtons, HasButtons{
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)

    //Buttons
    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    JSpinner gasSpinner;
    int gasAmount=0;

    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());


    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania(2, Color.red,80,"Scania"));

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    // We link the buttons to actions in the constructor.
    public CarController(){
        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);

        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });


        addActionListenerWithFunction(startButton, () -> startAllCars());
        addActionListenerWithFunction(stopButton,()-> stopAllCars());
        addActionListenerWithFunction(gasButton, () -> gas(gasAmount));
        addActionListenerWithFunction(brakeButton, () -> brake(gasAmount));
        addActionListenerWithFunction(turboOnButton, () -> saabTurboOn());
        addActionListenerWithFunction(turboOffButton, () -> saabTurboOff());
        addActionListenerWithFunction(lowerBedButton, () -> lowerBedButton());
        addActionListenerWithFunction(liftBedButton, () -> liftBedButton());

    }
    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                if(notWithinBounds(car.getPosition(), car.getDirection())) {
                    if (car instanceof Volvo240) {
                        car.stopEngine();
                    }
                    else {
                        reverseVehicle(car);
                    }
                }
                car.move();
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());
                frame.drawPanel.moveit(x, y, car.getModelName());
                //repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
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

    public boolean notWithinBounds(Position p, Direction dir){
        boolean leftScreen = 0 > p.getX() && Direction.WEST == dir;
        boolean rightScreen = frame.getWidth() < p.getX() + frame.drawPanel.getVehicleWidth() && Direction.EAST == dir;
        return leftScreen || rightScreen;
    } 

    // Calls the gas method for each car once
    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }


     public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars){
            car.brake(brake);
        }
    }

      public void saabTurboOn() {
        for (Vehicle v : cars) {
            if (v instanceof hasTurbo) {
                ((Saab95) v).setTurboOn();
            }
        }
    }

    public void saabTurboOff() {
        for (Vehicle v : cars) {
            if (v instanceof hasTurbo) {
                ((Saab95) v).setTurboOff();
            }
        }
    }
     public void liftBedButton(){
        for (Vehicle v : cars){
            if(v instanceof MoveFlake){
                ((Scania)v).raise();
            }
        }

    }
    public void lowerBedButton(){
        for(Vehicle v: cars){
            if(v instanceof MoveFlake){
                ((Scania) v).lower();
            }
        }
    }
    public void startAllCars() {
        for (Vehicle v : cars) {
            v.startEngine();
        }
    }
    public void stopAllCars() {
        for (Vehicle v : cars) {
            v.stopEngine();
        }
    }

    // Action Listeners
    public static void addActionListenerWithFunction(AbstractButton button, Runnable func) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                func.run();
            }
        });
    }

}
