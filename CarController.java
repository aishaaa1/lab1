import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController  {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();

    // A list of workshops?
    CarRepairShop<Volvo240> volvoWorkshop;

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();
        cc.volvoWorkshop = new CarRepairShop<Volvo240>(1);

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania(2, Color.red,190,"Scania"));

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {

                if(notWithinBounds(car.getPosition(), car.getDirection())) {
                    if (car instanceof Volvo240) {car.stopEngine();}
                    else {reverseVehicle(car);}
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

    void reverseVehicle(Vehicle car){
        car.stopEngine();
        car.turnLeft();
        car.turnLeft();
        car.startEngine();
        car.gas(0.5);
    }

    boolean notWithinBounds(Position p, Direction dir){
        boolean leftScreen = 0 > p.getX() && Direction.WEST == dir;
        boolean rightScreen = frame.getWidth() < p.getX() + frame.drawPanel.getVehicleWidth() && Direction.EAST == dir;
        return leftScreen || rightScreen;
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars){
            car.brake(brake);
        }
    }

    void saabTurboOn() {
        for (Vehicle v : cars) {
            if (v instanceof hasTurbo) {
                ((Saab95) v).setTurboOn();
            }
        }
    }

    void saabTurboOff() {
        for (Vehicle v : cars) {
            if (v instanceof hasTurbo) {
                ((Saab95) v).setTurboOff();
            }
        }
    }
    void liftBedButton(){
        for (Vehicle v : cars){
            if(v instanceof MoveFlake){
                ((Scania)v).raise();
            }
        }

    }
    void lowerBedButton(){
        for(Vehicle v: cars){
            if(v instanceof MoveFlake){
                ((Scania) v).lower();
            }
        }
    }
    void startAllCars() {
        for (Vehicle v : cars) {
            v.startEngine();
        }
    }
    void stopAllCars() {
        for (Vehicle v : cars) {
            v.stopEngine();
        }
    }
    void leaveCarAtWorkshop(){
        for (Vehicle v : cars){
            if (v instanceof  Volvo240 ){
                volvoWorkshop.leaveCar((Volvo240) v);
                v.stopEngine();

            }
        }
    }
    void retrieveCarFromWorkshop() {
        for (Vehicle v : cars) {
            if (v instanceof Volvo240 && volvoWorkshop.isCarRepaired((Volvo240) v)) {
                v.startEngine();
            }
        }
    }
}
