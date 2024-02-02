public class Flake {
    private int angle;
    public Flake (int angle) {
        this.angle = angle;
    }
    public int getAngle() {
        return angle;
    }
    public void setAngle(int angle) {
        this.angle = angle;
        if (!isWithinRange()) {
            throw new IllegalArgumentException("Is not within range: " + angle);
        }
    }
    boolean isWithinRange() {
        return this.angle >= 0 && this.angle <= 70;
    }
    void increaseAngle() {
        if (this.isWithinRange() && angle != 70) {
            angle++;
        }
    }
    void decreaseAngle() {
        if (this.isWithinRange() && angle != 0) {
            angle--;
        }
    }
}
