import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFactory {
    public BufferedImage createImage(String pic) {
        try {
            return ImageIO.read(new File(pic));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
