import java.util.ArrayList;
import java.util.List;

public class VehicleModel implements  ActionButtons, Manager {

    public final static VehicleFactory factory = new VehicleFactory();
    private final static VehicleImageFactory imageFactory = new VehicleImageFactory();
    private final List<Vehicle> cars;
    //need VehicleImages to remove add car maybe
    //private final ArrayList<VehicleImage> vehicleImages;
    private final List<CarObserver> observers = new ArrayList<>();
    private final ArrayList<CarManagementObserver> managementObservers = new ArrayList<>();

    public VehicleModel(ArrayList<Vehicle> cars) {
        this.cars = cars;
        //this.vehicleImages = vehicleImages;
    }



    public void reverseVehicle(Vehicle car){
        car.stopEngine();
        car.turnLeft();
        car.turnLeft();
        car.startEngine();
        car.gas(0.5);
    }


    void moveCars(){
        for (Vehicle car : cars){
            if(notWithinBounds(car)) {
                if (car instanceof Volvo240) {
                    car.stopEngine();
                }
                else {
                    reverseVehicle(car);
                }
            }
            car.move();
        }
        newState();
    }

    public boolean notWithinBounds(Vehicle v){
        boolean leftScreen = 0 > v.getPosition().getX() && Direction.WEST == v.getDirection();
        boolean rightScreen = 800 < v.getPosition().getX() + 100 && Direction.EAST == v.getDirection();
        return leftScreen || rightScreen;
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

    @Override
    public void addVehicle(){
        if(cars.size() < 6) {
            Vehicle v = factory.createRandVehicle();
            cars.add(v);
            int positionY = (cars.size() - 1) * 100;
            notifyCarAdded(imageFactory.createImage(v, 0, positionY));
        }

    }
    @Override
    public void removeVehicle(){
        if(cars.size() - 1 > 1) {
            cars.removeLast();
        } else if (cars.size() == 1) {
            cars.removeFirst();
        }
        notifyCarRemoved();
    }

    public void addObservers(CarObserver ob){
        observers.add(ob);

    }
    void notifyObservers(String car, int x){
        for (CarObserver ob : observers){
            ob.updateVehicle(car, x);
        }
    }

    void newState(){
        for (Vehicle car : cars){
            notifyObservers(car.getModelName(), car.getPosition().getX());
        }
    }

    public void addManagementObserver(CarManagementObserver ob){
        managementObservers.add(ob);
    }
    public void notifyCarAdded(VehicleImage vehicleImage){
        for (CarManagementObserver ob : managementObservers){
            ob.actOnCarAdded(vehicleImage);
        }
    }

    public void notifyCarRemoved(){
        for (CarManagementObserver ob : managementObservers){
            ob.actOnCarRemoved();
        }
    }

}
