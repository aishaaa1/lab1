import java.awt.*;

public class Scania extends Truck implements MoveFlake{
    private final static double trimFactor = 2;
    private final Flake flake;

    public Scania() {
        super(2, 500, Color.BLUE, "Scania24");
        this.flake = new Flake(0);
    }

    public Flake getFlake() { return flake; }
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
    public void raiseFlake() {
        if (getCurrentSpeed() != 0) {
            throw new IllegalArgumentException("Vehicle is moving");
        }
        flake.increaseAngle();
    }
    @Override
    public void lowerFlake() {
        if (getCurrentSpeed() != 0) {
            throw new IllegalArgumentException("Vehicle is moving");
        }
        flake.decreaseAngle();
    }
    @Override
    public void move(){
        if (flake.getAngle() != 0) {
            stopEngine();
        }
        else {
            super.move();
        }
    }

}
