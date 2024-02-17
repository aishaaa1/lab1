import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VehicleImage {
    private final BufferedImage image;
    public Vehicle vehicle;
    private final Point imagePoint;
    public VehicleImage(Vehicle vehicle, Point imagePoint) {
        this.vehicle = vehicle;
        this.imagePoint = imagePoint;
        try {
            this.image = ImageIO.read(new File("pics/" + this.vehicle.getModelName() + ".jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void moveImage(int x, int y) {
        imagePoint.x += x;
        imagePoint.y += y;
    }
    protected void drawImage(Graphics g) {
        g.drawImage(image, imagePoint.x, imagePoint.y, null);
    }
    public int getImageWidth() { return image.getWidth(); }

    public int getX() {return imagePoint.x;}
    public int getY() {return imagePoint.y;}

}