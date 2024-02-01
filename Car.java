import java.awt.*;

public abstract class Car implements Movable {
    protected static final Direction[] dirs = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
    protected Position position;
    protected Direction direction;
    protected final int nrDoors;
    protected double enginePower;
    protected double currentSpeed;
    protected Color color;
    protected final String modelName;
    public Car (int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.position = new Position(0, 0);
        this.direction = Direction.NORTH;
        stopEngine();
    }
    protected Position getPosition() {
        return position;
    }
    protected Direction getDirection() { return this.direction; }
    protected void currentSpeed(double currentSpeed) {
        if (this.currentSpeed < 0) {
            this.currentSpeed = 0;
        }
        else if (this.currentSpeed > enginePower) {
            this.currentSpeed = enginePower;
        }
        else {
            this.currentSpeed = currentSpeed;
        }
    }
    protected int getNrDoors(){
        return nrDoors;
    }
    protected double getEnginePower(){
        return enginePower;
    }
    protected double getCurrentSpeed(){
        currentSpeed(currentSpeed);
        return currentSpeed;
    }
    public Color getColor(){
        return color;
    }
    protected void setColor(Color clr){
        color = clr;
    }
    protected void setDirection(Direction dir) { direction = dir;}
    protected void setPosition (int x, int y) {
        this.position = new Position(x, y);
    }
    public void startEngine(){
        currentSpeed = 0.1;
    }
    public void stopEngine(){
        currentSpeed = 0;
    }
    protected abstract double speedFactor();
    protected abstract void incrementSpeed(double amount);
    protected abstract void decrementSpeed(double amount);
    protected void gas(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Illegal amount " + amount);
        }
        else {
            int old = (int) getCurrentSpeed();
            incrementSpeed(amount);
            if (old > (int) getCurrentSpeed()) {
                currentSpeed = old;
            }
        }
    }
    protected void brake(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Illegal amount: " + amount);
        }
        else {
            int old = (int) getCurrentSpeed();
            decrementSpeed(amount);
            if ((int) getCurrentSpeed() > old) {
                currentSpeed = old;
            }
        }
    }
    @Override
    public void move() {
        int x = position.getX();
        int y = position.getY();
        int x1 = x;
        int y1 = y;
        startEngine();
        if (direction == Direction.EAST) {
            x1 += (int) getCurrentSpeed();
        }
        if (direction == Direction.WEST) {
            x1 -= (int) getCurrentSpeed();
        }
        if (direction == Direction.NORTH) {
            y1 += (int) getCurrentSpeed();
        }
        if (direction == Direction.SOUTH) {
            y1 -= (int) getCurrentSpeed();
        }
        int amount = (int) Math.sqrt(Math.pow(x1 - x, 2) + Math.pow(y1 - y, 2));
        gas(amount);
        stopEngine();
        position = new Position(x1, y1);
    }
    @Override
    public void turnLeft() {
        direction = getNext(direction, -1);
    }
    @Override
    public void turnRight() {
        direction = getNext(direction, 1);
    }


    private static Direction getNext (Direction dir, int j) {
        int index = 0;
        for (int i = 0; i < dirs.length; i++) {
            if (dir == dirs[i]) {
                index = i;
            }
        }
        index = index + j;
        if (index < 0) {
            index = dirs.length - 1;
        }
        if (index > dirs.length - 1) {
            index = 0;
        }
        int find = Math.max(0, Math.min(index, dirs.length - 1));
        return dirs[find];
    }
}
