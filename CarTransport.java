import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class CarTransport extends Car implements MoveFlake, CanBeLoaded {
    private final Deque<Car> cars = new ArrayDeque<>(5);
    private final Flake flake = new Flake(MODE.UP);
    public CarTransport(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }
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

    public void loadTruck(Car car) {
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
    public boolean isClose(Car car) {
        return this.getPosition().getX() - 2 == car.getPosition().getX()
                && this.getPosition().getY() - 2 == car.getPosition().getY();
    }

    @Override
    public boolean hasFlake() {
        return true;
    }
}

//canbeloaded interface för alla bilar som kan lastas
