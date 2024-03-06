
import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// This panel represents the animated part of the view with the car images.
public class  DrawPanel extends JPanel{
    public final static WorkShopFactory workShopFactory = new WorkShopFactory();
    private final List<VehicleImage> vehicleImages = new ArrayList<>();
    private final Collection<WorkShopImage> workShopImageImages = new ArrayList<>();

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.yellow);

        workShopImageImages.add(workShopFactory.createVolvoShop(700, 0));
    }
    /*
    *Corresponds with the car's modelName, more general compared to before.
    */
    void moveImages() {
        for (VehicleImage vehicleImage : vehicleImages) {
                vehicleImage.moveImage();
        }
        repaint();
    }
    void addImage(VehicleImage vehicleImage){
        vehicleImages.add(vehicleImage);
    }
    void removeImage(){
        vehicleImages.removeLast();
    }
    public void removeThisImage(VehicleImage image) {
        vehicleImages.remove(image);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (VehicleImage vehicleImage : vehicleImages) {
                vehicleImage.drawImage(g);

        }
        for (WorkShopImage workShopImage : workShopImageImages) {
            workShopImage.drawWorkShop(g);
        }

    }

}