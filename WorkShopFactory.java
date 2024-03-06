import java.awt.*;
/*
This class's responsibility is to create workshop objects
 */
public class WorkShopFactory {
    public WorkShopImage createVolvoShop(int x, int y) {
        return new WorkShopImage("pics/VolvoBrand.jpg", new Point(x, y));
    }
}