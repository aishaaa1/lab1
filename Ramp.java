public class Ramp {
    private MODE mode;

    public Ramp (MODE mode) {
        this.mode = mode;
    }

    public MODE getMode() {
        return mode;
    }

    public void upRamp() {
        this.mode = MODE.UP;
    }

    public void downRamp() {
        this.mode = MODE.DOWN;
    }

}
