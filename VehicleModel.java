import java.util.ArrayList;
import java.util.List;

public class VehicleModel implements  ActionButtons, Manager {

    public final static VehicleFactory factory = new VehicleFactory();
    private final static VehicleImageFactory imageFactory = new VehicleImageFactory();
    private final List<Vehicle> cars = new ArrayList<>();
    //need VehicleImages to remove add car maybe
    //private final ArrayList<VehicleImage> vehicleImages = new ArrayList<>();
    private final List<CarObserver> observers = new ArrayList<>();
    private final ArrayList<CarManagementObserver> managementObservers = new ArrayList<>();

    private final WorkShopFactory workShopFactory = new WorkShopFactory();
    private final WorkShop workShop =  workShopFactory.createVolvoShop(700, 0);
    private static final int CAR_WIDTH = 100;
    private static final int WIDTH = 800;

    public VehicleModel(Vehicle v){
        v.setLoadable();
        cars.add(v);
        //vehicleImages.add(image);
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
                if (Loadable.TRUE == car.getLoadable()) {
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
        boolean rightScreen = WIDTH < v.getPosition().getX() + CAR_WIDTH && Direction.EAST == v.getDirection();
        return leftScreen || rightScreen;
    }

    public boolean inWorkShopRange(Vehicle v, WorkShop w) {
        return v.getPosition().getY() == w.getY();
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
            int positionY = cars.size()  * 100;

            Vehicle v = factory.createRandVehicle();
            cars.add(v);
            VehicleImage image = imageFactory.createImage(v, 0, positionY);
            //vehicleImages.add(image);

            notifyCarAdded(image);
        }

    }
    @Override
    public void removeVehicle(){
        if(cars.size() > 1) {
            cars.removeLast();
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
