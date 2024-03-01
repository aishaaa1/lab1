import javax.swing.*;
import java.awt.*;

public class CompositePanel extends JPanel {

    public CompositePanel(CarController cc, DrawPanel drawPanel){

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setLayout(new BorderLayout());
        add(drawPanel, BorderLayout.NORTH);
        add(cc, BorderLayout.WEST);
    }

}