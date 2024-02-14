import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class CarTransport extends Vehicle implements MoveFlake  {
    private final Ramp ramp;
    private final Deque<Vehicle> transport;
    public CarTransport() {
        super(2, Color.WHITE, 500, "Krone");
        this.transport = new ArrayDeque<>();
        this.ramp = new Ramp(MODE.UP);
    }

    public Deque<Vehicle> getTransport() {return transport;}
    public Ramp getRamp() {return ramp;}
    @Override
    public void lower() {
        if (getCurrentSpeed() != 0) {
            throw new IllegalArgumentException("The car is moving");
        }
        ramp.downRamp();
    }
    @Override
    public void raise() {
        ramp.upRamp();
    }

    public void loadTruck(Vehicle car) {
        if (car instanceof MoveFlake) { throw new IllegalArgumentException("Can't load a truck");}
        if(isClose(car) && ramp.getMode() == MODE.DOWN) {
            this.transport.push(car);
            car.setPosition(this.position.getX(), this.position.getY());
        }
    }
    public void unloadTruck(){
        if (ramp.getMode() == MODE.DOWN) {
            transport.pop().setPosition(this.position.getX() + 2, this.position.getY() + 2);
        }
    }
    public boolean isClose(Vehicle car) {
        return Math.abs(this.position.getX() - 2) >= Math.abs(car.position.getX())
                && Math.abs(this.position.getY() - 2) >= Math.abs(car.position.getY());
    }

    @Override
    public String getModelName() {
        return null;
    }
}
