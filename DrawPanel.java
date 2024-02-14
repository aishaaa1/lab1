import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// This panel represents the animated part of the view with the car images.

public class  DrawPanel extends JPanel{


    // Just a single image, TODO: Generalize
    //BufferedImage volvoImage;

    // To keep track of a single car's position
    //Point volvoPoint = new Point();
    //private Image Vehicle;
    List<Image> vehicles = new ArrayList<>();
    private Image volvoImage;
    private Image saabImage;
    private Image scaniaImage;
    Point volvoPoint = new Point();
    Point scaniaPoint = new Point(0,100);
    Point saabPoint = new Point(0, 200);

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300, 300);

    // TODO: Make this general for all cars
    void moveit(int x, int y, String modelName) {
        if (modelName.equals("Volvo240")) {
            volvoPoint.x = x;
            volvoPoint.y = y;
        }
        if (modelName.equals("Saab95")) {
            saabPoint.x = x;
            saabPoint.y = y+200;
        }
        if (modelName.equals("Scania")) {
            scaniaPoint.x = x;
            scaniaPoint.y = y+100;
        }

       /* for (Image v : vehicles) {
            try {
                if (v.getModelName().equals("Saab95")) {
                    v = new Image(ImageIO.read(new File(modelName+".jpg")), modelName, new Point(x,y));
                }
                if (v.getModelName().equals("Volvo240")) {
                    v = new Image(ImageIO.read(new File(modelName+".jpg")), modelName, new Point(x,y));
                }
                if (v.getModelName().equals("Scania")) {
                    v = new Image(ImageIO.read(new File(modelName+".jpg")), modelName, new Point(x,y));
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }*/


    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.yellow);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            //volvoImage = new Image(ImageIO.read(new File("Volvo240.jpg")), volvoPoint);

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            volvoImage = new Image(ImageIO.read(new File("pics/Volvo240.jpg")), "Volvo240", volvoPoint);
            saabImage = new Image(ImageIO.read(new File ("pics/Saab95.jpg")), "Saab95", saabPoint);
            scaniaImage = new Image(ImageIO.read(new File("pics/Scania.jpg")), "Scania", scaniaPoint);
        //volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));



        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }



    public int getVehicleWidth() {return volvoImage.getImage().getWidth();}

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(volvoImage.getImage(), (int) volvoImage.getPosition().getX(), (int) volvoImage.getPosition().getY(), null);
        g.drawImage(saabImage.getImage(),(int) saabImage.getPosition().getX(),(int) saabImage.getPosition().getY(),null);
        g.drawImage(scaniaImage.getImage(), (int) scaniaImage.getPosition().getX(),(int) scaniaImage.getPosition().getY(),null);
    ///see javadoc for more info on the parameters
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}
