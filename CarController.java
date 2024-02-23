import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController  {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)

    JPanel gasPanel = new JPanel();
    JPanel controlPanel = new JPanel();
    JLabel gasLabel = new JLabel();

    //Buttons
    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");
    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    JSpinner gasSpinner;
    int gasAmount=0;

    private static final int X = 800;
    private static final int Y = 240;
    Frame frame;


    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());


    // The frame that represents this instance View of the MVC patter
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController("CarView 1.0");

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    // We link the buttons to actions in the constructor.
    public CarController(String title){
        initFrame(title);
        initButtons();

        addActionListenerWithFunction(startButton, () -> startAllCars());
        addActionListenerWithFunction(stopButton,()-> stopAllCars());
        addActionListenerWithFunction(gasButton, () -> gas(gasAmount));
        addActionListenerWithFunction(brakeButton, () -> brake(gasAmount));
        addActionListenerWithFunction(turboOnButton, () -> saabTurboOn());
        addActionListenerWithFunction(turboOffButton, () -> saabTurboOff());
        addActionListenerWithFunction(lowerBedButton, () -> lowerBedButton());
        addActionListenerWithFunction(liftBedButton, () -> liftBedButton());

        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        setVisible(true);
    }
    void initFrame(String title) {
        this.setTitle(title);
        this.setPreferredSize(new Dimension(X, Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    }

    void initButtons() {

        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
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

    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                if(notWithinBounds(car.getPosition(), car.getDirection(), car)) {
                    if (car instanceof Volvo240) {
                        car.stopEngine();
                    }
                    else {
                        reverseVehicle(car);
                    }
                }
                car.move();
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());
                frame.drawPanel.moveImage(x, y, car.getModelName());
                //repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    public void reverseVehicle(Vehicle car){
        car.stopEngine();
        car.turnLeft();
        car.turnLeft();
        car.startEngine();
        car.gas(0.5);
    }

    public boolean notWithinBounds(Position p, Direction dir, Vehicle v){
        boolean leftScreen = 0 > p.getX() && Direction.WEST == dir;
        boolean rightScreen = frame.getWidth() < p.getX() + frame.drawPanel.getVehicleWidth(v.getModelName()) && Direction.EAST == dir;
        return leftScreen || rightScreen;
    }

    // Calls the gas method for each car once
    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }


    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars){
            car.brake(brake);
        }
    }

    public void saabTurboOn() {
        for (Vehicle v : cars) {
            if (v instanceof hasTurbo) {
                ((Saab95) v).setTurboOn();
            }
        }
    }

    public void saabTurboOff() {
        for (Vehicle v : cars) {
            if (v instanceof hasTurbo) {
                ((Saab95) v).setTurboOff();
            }
        }
    }
    public void liftBedButton(){
        for (Vehicle v : cars){
            if(v instanceof MoveFlake){
                ((Scania)v).raise();
            }
        }

    }
    public void lowerBedButton(){
        for(Vehicle v: cars){
            if(v instanceof MoveFlake){
                ((Scania) v).lower();
            }
        }
    }
    public void startAllCars() {
        for (Vehicle v : cars) {
            v.startEngine();
        }
    }
    public void stopAllCars() {
        for (Vehicle v : cars) {
            v.stopEngine();
        }
    }

    // Action Listeners
    public static void addActionListenerWithFunction(AbstractButton button, Runnable func) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                func.run();
            }
        });
    }
}
