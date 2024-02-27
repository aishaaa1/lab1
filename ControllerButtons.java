import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class ControllerButtons extends JFrame implements HasButtons{

    ArrayList<CarObserver> observers = new ArrayList<>();
    JPanel gasPanel = new JPanel();
    JPanel controlPanel = new JPanel();
    JLabel gasLabel = new JLabel();
    JSpinner gasSpinner;
    private int gasAmount=0;
    private static final int X = 800;
    private static final int Y = 240;

    public ControllerButtons() {
        initComponents();

    }
    public void addObserver(CarObserver observer) {
        observers.add(observer);
    }
    public int getGasAmount() {
        return this.gasAmount;
    }

    void initComponents() {
        this.setPreferredSize(new Dimension(X, Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.pack();
        setVisible(true);


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

        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        this.add(controlPanel, BorderLayout.EAST);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(stopButton);

        startButton.addActionListener(e -> {
            for (CarObserver observer : observers) {
                observer.notifyObservers("start");
            }
        });
        stopButton.addActionListener(e -> {
            for (CarObserver observer : observers) {
                observer.notifyObservers("stop");
            }
        });
        gasButton.addActionListener(e -> {
            for (CarObserver observer : observers) {
                observer.notifyObservers("gas");
            }
        });
        brakeButton.addActionListener(e -> {
            for (CarObserver observer : observers) {
                observer.notifyObservers("brake");
            }
        });

        turboOnButton.addActionListener(e -> {
            for (CarObserver observer : observers) {
                observer.notifyObservers("turboOn");
            }
        });

        turboOffButton.addActionListener(e -> {
            for (CarObserver observer : observers) {
                observer.notifyObservers("turboOff");
            }
        });

        lowerBedButton.addActionListener(e -> {
            for (CarObserver observer : observers) {
                observer.notifyObservers("lowerBed");
            }
        });

        liftBedButton.addActionListener(e -> {
            for (CarObserver observer : observers) {
                observer.notifyObservers("liftBed");
            }
        });


    }


}
