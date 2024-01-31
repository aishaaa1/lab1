public class Flake {
    private int angle;
    private MODE mode;
    public Flake (int angle) {
        this.angle = angle;
    }
    public Flake (MODE mode) {this.mode = mode; }

    public int getAngle() {
        return angle;
    }
    public MODE getMode() { return mode; } // to be used in Car Transport
    public void setAngle(int angle) {
        this.angle = angle;
        if (!isWithinRange()) {
            throw new IllegalArgumentException("Is not within range: " + angle);
        }
    }
    public void upFlake() { this.mode = MODE.UP; }
    public void downFlake() {this.mode = MODE.DOWN;}
    boolean isWithinRange() {
        return this.angle >= 0 && this.angle <= 70;
    }
    void increaseAngle() {
        if (isWithinRange() && angle != 70) {
            angle++;
        }
    }
    void decreaseAngle() {
        if (isWithinRange() && angle != 0) {
            angle--;
        }
    }

}
