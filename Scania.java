import java.awt.*;

public class Scania extends Vehicle implements MoveFlake {
    private final Flake flake;
    public Scania() {
        super(2, Color.black, 270, "Scania");
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
