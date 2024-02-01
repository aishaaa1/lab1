import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class CarTransport extends Truck implements MoveFlake {
    private static final int trimFactor = 2;
    private final Deque<Vehicle> cars;
    private final Flake flake = new Flake(MODE.UP);
    public CarTransport(int maxCapacity) {
        super(2, 500, Color.WHITE, "modelName");
        this.cars = new ArrayDeque<>(maxCapacity);
    }
    public Deque<Vehicle> getCars() { return cars;}
    @Override
    protected double speedFactor() {
        return enginePower * 0.01 * trimFactor;
    }

    @Override
    protected void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    @Override
    protected void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
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

