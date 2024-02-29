import java.awt.*;
import java.awt.image.BufferedImage;
/*
This class represents the workshop object
 */
public class WorkShop {
    /*
    Static variable, doesn't belong to the instance but to the whole class
     */
    private static final ImageFactory factory = new ImageFactory();
    private final BufferedImage workShop;
    private final Point point;
    public WorkShop(String pic, Point point) {
        workShop = factory.createImage(pic);
        this.point = point;
    }
    public Point getPoint() { return point; }
    public void drawWorkShop (Graphics g) {
        g.drawImage(workShop, point.x, point.y, null);
    }




}
