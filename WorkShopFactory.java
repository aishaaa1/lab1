import java.awt.*;
/*
This class's responsibility is to create workshop objects
 */
public class WorkShopFactory {
    public WorkShop createVolvoShop(int x, int y) {
        return new WorkShop("pics/VolvoBrand.jpg", new Point(x, y));
    }
}
