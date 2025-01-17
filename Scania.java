import java.awt.*;

public class Scania extends Vehicle implements MoveFlake {
    private final Flake flake;
    public Scania(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
        this.flake = new Flake(0);
    }

    public Flake getFlake() {return flake;}

    @Override
    public void raise() {
        if (getCurrentSpeed() != 0) {
            throw new IllegalArgumentException("Vehicle is moving");
        }
        flake.increaseAngle();
    }
    @Override
    public void lower() {
        if (getCurrentSpeed() != 0) {
            throw new IllegalArgumentException("Vehicle is moving");
        }
        flake.decreaseAngle();
    }
    @Override
    public void move() {
        if (flake.getAngle() != 0) {
            stopEngine();
        }
        else {
            super.move();
        }
    }

    @Override
    public String getModelName() {
        return "Scania";
    }
}
