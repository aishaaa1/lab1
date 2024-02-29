import java.awt.*;
/*
This class's responsibility is to generate the images for the vehicles
 */
public class VehicleImageFactory {
    public VehicleImage createVolvoImage(int x, int y) {
        return new VehicleImage(new Volvo240(), new Point(x, y));
    }
    public VehicleImage createSaabImage(int x, int y) {
        return new VehicleImage(new Saab95(), new Point(x, y));
    }

    public VehicleImage createScaniaImage(int x, int y) {
        return new VehicleImage(new Scania(), new Point(x, y));
    }
}
