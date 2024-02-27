import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VehicleImage {
    private final BufferedImage image;
    private Vehicle vehicle;
    private final Point point;

    public VehicleImage(Vehicle vehicle, Point point)  {
        this.vehicle = vehicle;
        this.point = point;
        try {
            this.image = ImageIO.read(new File("pics/" + this.vehicle.getModelName() + ".jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void moveImage(int x) {
        point.x = x;
    }
    protected void drawImage(Graphics g) {
        g.drawImage(image, point.x, point.y, null);
    }

    public int getImageWidth() { return image.getWidth();}

    public int getX() {return point.x;}
    public String getModelName() {return vehicle.getModelName();}

    /*
    Could be useful for later
     */
    public int getImageHeight() {
        return image.getHeight();
    }
    public void setPosition(Point point) {
        this.point.x = point.x;
        this.point.y = point.y;
    }


}