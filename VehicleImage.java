
import java.awt.*;
import java.awt.image.BufferedImage;


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

    public Vehicle getVehicle(){
        return this.vehicle;
    }


    public void moveImage() {
        point.x = this.getVehicle().getX();

    }

    protected void drawImage(Graphics g) {
        g.drawImage(image, point.x, point.y, null);
    }

    public int getImageWidth() { return image.getWidth();}



    public int getX() {return point.x;}
    public int getY() {return point.y;}

    //public boolean isSameModelName(String modelName) {return vehicle.getModelName().equals(modelName);}
}