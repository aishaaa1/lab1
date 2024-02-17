import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// This panel represents the animated part of the view with the car images.

public class   DrawPanel extends JPanel{


    private final static BufferedImage volvoWorkshopImage;
    static {
        try {
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private final static Point volvoWorkshopPoint = new Point(700, 0);
    private final List<VehicleImage> vehicleImages = new ArrayList<>();


    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.yellow);

        vehicleImages.add(new VehicleImage(new Volvo240(), new Point()));
        vehicleImages.add(new VehicleImage(new Saab95(), new Point(0, 200)));
        vehicleImages.add(new VehicleImage(new Scania(), new Point(0,100)));

    }

    /*
    Corresponds with the car's modelName, more general compared to before.
    */
    void moveit(int x, int y, String modelName) {
        for (VehicleImage vehicleImage : vehicleImages) {
            if (vehicleImage.getModelName().equals(modelName)){
                vehicleImage.moveImage(x, y);
            }
        }
    }
    int getVehicleWidth() {return volvoWorkshopImage.getWidth();}
    /*
    To check whether the image is moving out of frame
     */
    boolean isMovingOutOfFrame(VehicleImage image) {
        return image.getX() + image.getImageWidth() < 0 || image.getX() + image.getImageWidth() > getWidth();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (VehicleImage vehicleImage : vehicleImages) {
            vehicleImage.drawImage(g);
        }
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}