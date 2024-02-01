import java.awt.*;

public abstract class Vehicle extends Car {
    public Vehicle(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }

    @Override
    protected abstract double speedFactor();
    @Override
    protected abstract void incrementSpeed(double amount);

    @Override
    protected abstract void decrementSpeed(double amount);
}
