import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    private BufferedImage image;
    private Point position;
    private String modelName;
    public Image(BufferedImage image, String modelName, Point position){
        this.image = image;
        this.modelName = modelName;
        this.position = position;
    }

    public String getModelName(){return this.modelName;}

    public void UpdateImage(Image v, String modelName, Point p) {
        try {
            v = new Image(ImageIO.read(new File(modelName+".jpg")), modelName, p);
            /*this.carImage.add(new Image(ImageIO.read(new File(modelName+".jpg")),
                    p));
             */
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean notImage() {
        return image != null && position != null;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Point getPosition() {
        return position;
    }

}