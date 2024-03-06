import java.awt.*;
public class Saab95 extends Vehicle implements hasTurbo {
    private boolean turboOn;
    public Saab95(){
        super (2, Color.red, 250, "Saab95");
        turboOn = false;
    }
    public void setTurboOn(){
        turboOn = true;
    }
    public void setTurboOff(){
        turboOn = false;
    }
    @Override
    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }

    @Override
    public String getModelName() {
        return "Saab95";
    }


}
