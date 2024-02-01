import java.awt.*;

public abstract class Truck extends Car {

    public Truck(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }
    @Override
    protected abstract double speedFactor();

    @Override
    protected abstract void incrementSpeed(double amount);

    @Override
    protected abstract void decrementSpeed(double amount);

    abstract boolean hasFlake();
}
