import java.awt.*;

public class Volvo240 extends Vehicle {
    private final static double trimFactor = 1.25;

    public Volvo240 (){
        super(4, Color.black, 240, "Volvo");

    }

    @Override
    public double speedFactor() {
        return this.enginePower * 0.01 * trimFactor;
    }


    @Override
    public String getModelName() {
        return "Volvo240";
    }
}
