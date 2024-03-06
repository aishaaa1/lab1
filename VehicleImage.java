import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VehicleImage {
    public static ImageFactory factory = new ImageFactory();
    private final BufferedImage image;
    private final Vehicle vehicle;
    private final Point point;


    public VehicleImage(Vehicle vehicle, Point point)  {
        this.vehicle = vehicle;
        this.point = point;
        this.image = factory.createImage("pics/" + this.vehicle.getModelName() + ".jpg");

    }
    public void moveImage(int x) {
        point.x = x;
    }
    protected void drawImage(Graphics g) {
        g.drawImage(image, point.x, point.y, null);
    }

    public int getImageWidth() { return image.getWidth();}

    public int getX() {return point.x;}

    public boolean isSameModelName(String modelName) {return vehicle.getModelName().equals(modelName);}
    public boolean isSameCar(Vehicle other) {return vehicle.equals(other);}
}