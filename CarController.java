import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController extends JPanel implements HasButtons{
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final VehicleModel model;
    JPanel gasPanel = new JPanel();
    JPanel controlPanel = new JPanel();
    JLabel gasLabel = new JLabel();
    JSpinner gasSpinner;
    private int gasAmount = 0;
    private static final int X = 800;
    //private static final int Y = 240;
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer = new Timer(delay, new TimerListener());


    // The frame that represents this instance View of the MVC patter

    public CarController (VehicleModel model){
        this.model = model;
        initComponents();
    }

    /* CarApp needs to call the startTimer method to start timer. */
    public void startTimer() {
        this.timer.start();
    }

    /* Each step the TimerListener moves all the model in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                model.moveCars(X);
        }
    }

    void initComponents() {

        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(e -> gasAmount = (int) ((JSpinner)e.getSource()).getValue());

        gasPanel.setLayout(new BorderLayout());

        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);

        controlPanel.setPreferredSize(new Dimension((X/2), 200));
        this.add(controlPanel, BorderLayout.EAST);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X/6-20,200));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X/6-20,200));
        this.add(stopButton);

        startButton.addActionListener(e -> model.startAllCars());

        stopButton.addActionListener(e -> model.stopAllCars());

        gasButton.addActionListener(e -> model.gas(gasAmount));

        brakeButton.addActionListener(e -> model.brake(gasAmount));

        turboOnButton.addActionListener(e -> model.saabTurboOn());

        turboOffButton.addActionListener(e -> model.saabTurboOff());

        lowerBedButton.addActionListener(e -> model.lowerBedButton());

        liftBedButton.addActionListener(e -> model.liftBedButton());

    }



}
