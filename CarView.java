import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class CarView extends JFrame{
    private static final int X = 800;
    private static final int Y = 800;

    // The controller member
    CarController carC;


    DrawPanel drawPanel = new DrawPanel(X, Y-240);

    JPanel controlPanel = new JPanel();

    JPanel gasPanel = new JPanel();

    int gasAmount = 0;

    JLabel gasLabel = new JLabel("Amount of gas");



    // Constructor
    public CarView(String framename, CarController cc){
        this.carC = cc;
        initComponents(framename);
    }

    public int getWidth(){return X;}

    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand how these methods and components work
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(carC.gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(carC.gasButton, 0);
        controlPanel.add(carC.turboOnButton, 1);
        controlPanel.add(carC.liftBedButton, 2);
        controlPanel.add(carC.brakeButton, 3);
        controlPanel.add(carC.turboOffButton, 4);
        controlPanel.add(carC.lowerBedButton, 5);
        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        carC.startButton.setBackground(Color.blue);
        carC.startButton.setForeground(Color.green);
        carC.startButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(carC.startButton);


        carC.stopButton.setBackground(Color.red);
        carC.stopButton.setForeground(Color.black);
        carC.stopButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(carC.stopButton);

        // This actionListener is for the gas button only
        // TODO: Create more for each component as necessary

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}