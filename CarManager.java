import java.util.ArrayList;


public class CarManager implements ActionButtons {


    private final ArrayList<Vehicle> cars;

    public CarManager(ArrayList<Vehicle> cars) {
        this.cars = cars;
    }
    @Override
    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }


    @Override
    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars){
            car.brake(brake);
        }
    }

    @Override
    public void saabTurboOn() {
        for (Vehicle v : cars) {
            if (v instanceof hasTurbo) {
                ((Saab95) v).setTurboOn();
            }
        }
    }

    @Override
    public void saabTurboOff() {
        for (Vehicle v : cars) {
            if (v instanceof hasTurbo) {
                ((Saab95) v).setTurboOff();
            }
        }
    }
    @Override
    public void liftBedButton(){
        for (Vehicle v : cars){
            if(v instanceof MoveFlake){
                ((Scania)v).raise();
            }
        }

    }
    @Override
    public void lowerBedButton(){
        for(Vehicle v: cars){
            if(v instanceof MoveFlake){
                ((Scania) v).lower();
            }
        }
    }
    @Override
    public void startAllCars() {
        for (Vehicle v : cars) {
            v.startEngine();
        }
    }
    @Override
    public void stopAllCars() {
        for (Vehicle v : cars) {
            v.stopEngine();
        }
    }



    // ... other methods for car logic (brake, startEngine, etc.)
}

