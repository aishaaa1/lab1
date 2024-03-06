import java.awt.*;

public abstract class Vehicle implements Movable {
    protected enum Direction {
        NORTH, EAST, SOUTH, WEST;
        private static final Direction[] dirs = values();
        public Direction turnLeft() {return dirs[(this.ordinal() + 1) % dirs.length];}
        public Direction turnRight() {return dirs[(this.ordinal() - 1) % dirs.length];}
    }
    protected Position position;
    private Direction direction;
    private final int nrDoors;
    public final double enginePower;
    public double currentSpeed;
    private Color color;
    public String modelName;

    public Vehicle(int nrDoors, Color color, double enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.position = new Position(0,0);
        this.direction = Direction.EAST; // This was always null therefore I added a default direction
        stopEngine();
    }
    protected Position getPosition() {
        return this.position;
    }
    protected int getY() {return this.position.getY();}
    protected int getX() {return this.position.getX();}
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
        if (direction == Direction.EAST) {
            x += (int) currentSpeed;
        }
        if (direction == Direction.WEST) {
            x -= (int) currentSpeed;
        }
        setPosition(x, position.getY());
    }

    @Override
    public void turnLeft() {
        direction = direction.turnLeft();
    }
    @Override
    public void turnRight() {
        direction = direction.turnRight();
    }
    public boolean isWest() {
        return direction == Direction.WEST;
    }
    public boolean isEast() {
        return direction == Direction.EAST;
    }

}

