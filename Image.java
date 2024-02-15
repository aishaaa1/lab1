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