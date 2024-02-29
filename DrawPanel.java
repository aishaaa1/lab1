import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
// This panel represents the animated part of the view with the car images.
public class  DrawPanel extends JPanel{
    /*
    Creates the images needed
     */
    private final static VehicleImageFactory factory = new VehicleImageFactory();
    public final static WorkShopFactory workShopFactory = new WorkShopFactory();
    private final Collection<VehicleImage> vehicleImages = new ArrayList<>();
    private final Collection<WorkShop> workShopImages = new ArrayList<>();

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.yellow);
        workShopImages.add(workShopFactory.createVolvoShop(700, 0));
        vehicleImages.add(factory.createVolvoImage(0,0));
        vehicleImages.add(factory.createSaabImage(0, 200));
        vehicleImages.add(factory.createScaniaImage(0, 300));
    }
    /*
    Corresponds with the car's modelName, more general compared to before.
    */
    void moveImage(int x, String modelName) {
        for (VehicleImage vehicleImage : vehicleImages) {
            if (vehicleImage.isSameModelName(modelName)){
                vehicleImage.moveImage(x);
            }
        }
    }
    int getVehicleWidth(String modelName) {
        for (VehicleImage vehicleImage : vehicleImages) {
            if (vehicleImage.isSameModelName(modelName)) {
                return vehicleImage.getImageWidth();
            }
        }
        return -1;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (VehicleImage vehicleImage : vehicleImages) {
            vehicleImage.drawImage(g);
        }
        for (WorkShop workShop : workShopImages) {
            workShop.drawWorkShop(g);
        }
    }
}