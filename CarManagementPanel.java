import javax.swing.*;
import java.awt.*;

public class CarManagementPanel extends JPanel {

    /* This is the context class that holds a reference to a
       strategy object and delegates tasks to it.
     */
    /* CarManagementPanel holds the UI elements (JButtons for add/remove and associated listeners)
    * This class maintains a reference to a "car management" strategy.
    * The panel only delegates the actions to the chosen strategy(CarManagementController in our case)
    */

    private final CarManagementStrategy cManager;


    public CarManagementPanel(CarManagementStrategy cc) {
        this.cManager = cc;
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2,1));
        //JButtons for add/remove car with appropriate action listeners
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");

        controlPanel.setPreferredSize(new Dimension(100, 200));
        this.add(controlPanel, BorderLayout.WEST);
        controlPanel.setBackground(Color.BLUE);

        controlPanel.add(addButton, 0);
        controlPanel.add(removeButton, 1);

        addButton.addActionListener(e -> cManager.addVehicle());

        removeButton.addActionListener(e -> cManager.removeVehicle());

    }

    // Setters and getters for Strategy if needed
}
