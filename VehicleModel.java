import java.util.ArrayList;
import java.util.List;

public class VehicleModel implements  Actions, Manager {

    public final VehicleFactory factory = new VehicleFactory();
    private final VehicleImageFactory imageFactory = new VehicleImageFactory();
    private final WorkShopFactory workShopFactory = new WorkShopFactory();
    private final WorkShopImage volvoWorkshop = workShopFactory.createVolvoShop(700, 0);
    //need VehicleImages to remove add car maybe
    private final ArrayList<VehicleImage> vehicles = new ArrayList<>();
    private final List<CarObserver> observers = new ArrayList<>();
    private final ArrayList<CarManagementObserver> managementObservers = new ArrayList<>();


    void moveCars(int x){
        for (VehicleImage v : vehicles){
            Vehicle car = v.getVehicle();
            if (notWithinBounds(car, x)) {
                if (inVolvoWorkshopRange(v, volvoWorkshop)) {
                    notifyThisVehicleRemoved(v);
                }
                else {
                    reverseVehicle(car);
                }
            }
            car.move();
        }
        notifyObservers();
    }

    public void reverseVehicle(Vehicle car){
        car.stopEngine();
        car.turnLeft();
        car.turnLeft();
        car.startEngine();
        car.gas(0.5);
    }

    boolean inVolvoWorkshopRange(VehicleImage v, WorkShopImage w){
        return  v.getVehicle() instanceof Volvo240 && v.getY() == w.getY();
    }

    public boolean notWithinBounds(Vehicle v, int width){
        VehicleImage image = imageFactory.createImage(v, v.getPosition().getX(), v.getPosition().getY());
        boolean left = 0 > v.getPosition().getX() && v.isWest();
        boolean right = width < v.getPosition().getX() + image.getImageWidth() && v.isEast();
        return left || right;
    }


    @Override
    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (VehicleImage v : vehicles) {
            v.getVehicle().gas(gas);

        }
    }


    @Override
    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (VehicleImage v : vehicles) {
            v.getVehicle().brake(brake);

        }
    }

    @Override
    public void saabTurboOn() {
        for (VehicleImage v : vehicles) {
            if (v.getVehicle() instanceof hasTurbo) {
                ((Saab95) v.getVehicle()).setTurboOn();
            }
        }
    }

    @Override
    public void saabTurboOff() {
        for (VehicleImage v : vehicles) {
            if (v.getVehicle() instanceof hasTurbo) {
                ((Saab95) v.getVehicle()).setTurboOff();
            }
        }
    }
    @Override
    public void liftBedButton(){
        for (VehicleImage v : vehicles){
            if(v.getVehicle() instanceof MoveFlake){
                ((Scania) v.getVehicle()).raise();
            }
        }

    }
    @Override
    public void lowerBedButton(){
        for(VehicleImage v : vehicles){
            if(v.getVehicle() instanceof MoveFlake){
                ((Scania) v.getVehicle()).lower();
            }
        }
    }
    @Override
    public void startAllCars() {
        for (VehicleImage v : vehicles) {
            v.getVehicle().startEngine();
        }
    }
    @Override
    public void stopAllCars() {
        for (VehicleImage v : vehicles) {
            v.getVehicle().stopEngine();
        }
    }

    @Override
    public void addVehicle(){
        if(vehicles.size() < 6) {
            int positionY = vehicles.size() * 100;
            Vehicle v = factory.createRandVehicle();

            VehicleImage vehicleImage = imageFactory.createImage(v, 0, positionY);
            vehicles.add(vehicleImage);
            notifyCarAdded(vehicleImage);
        }

    }
    @Override
    public void removeVehicle(){
        if(!vehicles.isEmpty()) {
            vehicles.removeLast();
        }
        notifyCarRemoved();
    }

    public void removeThisVehicle(VehicleImage image){
        vehicles.removeFirst();
        notifyThisVehicleRemoved(image);

    }

    public void addObservers(CarObserver ob){
        observers.add(ob);

    }
    void notifyObservers(){
        for (CarObserver ob : observers) {
            ob.updateVehicle();
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
    public void notifyThisVehicleRemoved(VehicleImage v){
        for (CarManagementObserver ob : managementObservers){
            ob.actOnCarRemoved(v);

        }
    }

}
