import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class CarTransport extends Truck implements MoveFlake {
    private static int maxCapacity;
    private final Deque<Vehicle> cars = new ArrayDeque<>(5);
    private final Flake flake = new Flake(MODE.UP);
    public CarTransport() {
        super(2, 500, Color.WHITE, "modelName");
    }
    public Deque<Vehicle> getCars() { return cars;}
    @Override
    protected double speedFactor() {
        return 0;
    }

    @Override
    protected void incrementSpeed(double amount) {
    }

    @Override
    protected void decrementSpeed(double amount) {
    }

    @Override
    boolean hasFlake() {
        return true;
    }

    @Override
    public void lowerFlake() {
        if(getCurrentSpeed() != 0){
            throw new IllegalArgumentException("The car is moving");
        }
        flake.downFlake();
    }

    @Override
    public void raiseFlake() {
        flake.upFlake();
    }

    public void loadTruck(Vehicle car) {
        if(isClose(car) && flake.getMode() == MODE.DOWN && this.hasFlake()) {
            cars.push(car);
            car.setPosition(this.position.getX(), this.position.getY());
        }
    }
    public void unloadTruck(){
        if (flake.getMode() == MODE.DOWN) {
            cars.pop().setPosition(this.position.getX() + 2, this.position.getY() + 2);
        }
    }
    public boolean isClose(Vehicle car) {
        return Math.abs(this.getPosition().getX() - 2) >= Math.abs(car.getPosition().getX())
                && Math.abs(this.getPosition().getY() - 2) >= Math.abs(car.getPosition().getY());
    }


}

