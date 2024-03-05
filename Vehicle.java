import java.awt.*;

public abstract class Vehicle implements Movable {
    private static final Direction[] dirs = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
    protected Position position;
    public Direction direction;
    private final int nrDoors;
    public final double enginePower;
    public double currentSpeed;
    private Color color;
    public String modelName;
    private Loadable loadable;
    public Vehicle(int nrDoors, Color color, double enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.position = new Position(0,0);
        this.direction = Direction.EAST;
        this.loadable = Loadable.FALSE;

        stopEngine();
    }
    protected Position getPosition() {
        return this.position;
    }
    protected String getModelName(){return this.modelName;} // Get model name was not defined here
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
    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }
    public double getCurrentSpeed(){
        return currentSpeed;
    }
    public Color getColor(){
        return color;
    }
    public void setColor(Color clr){
        color = clr;
    }
    public void setPosition (int x, int y) {this.position = new Position(x, y);}
    public void startEngine(){
        currentSpeed = 0.1;
    }
    public void stopEngine(){
        currentSpeed = 0;
    }

    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

     protected double speedFactor() {
        return enginePower * 0.01 * 1.54;
    }

    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }
    protected void gas(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Illegal amount " + amount);
        }
        else {
            double old =  getCurrentSpeed(); // it was integer I made it to doubles beacuse in Car controller gas is diveded by 100
            incrementSpeed(amount);
            if (old > getCurrentSpeed()) {
                currentSpeed = old;
            }
        }
    }
    protected void brake(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Illegal amount: " + amount);
        }
        else {
            double old =  getCurrentSpeed(); // Since the gas is divided by 100 I decided to make it double
            decrementSpeed(amount);
            if ( getCurrentSpeed() > old) {
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
        //startEngine(); // If we call this we reset the speed of the vehicle to 0.1 which is not good
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
        /*gas(amount);  We do not need to change the gas value in the move
        stopEngine();*/  // This is again not useful because we stop the car everytime the move function is called
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
    @Override
    public String toString() {
        return "The car is a " + this.modelName + " of " + this.getClass();
    }

    public void setDirection(Direction direction) {
    }
}
