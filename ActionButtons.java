public interface ActionButtons {
     void gas(int amount);

    void brake(int amount) ;

    void saabTurboOn() ;

    void saabTurboOff();
    void liftBedButton();
    void lowerBedButton();

    void startAllCars() ;

    void stopAllCars();
    void reverseVehicle(Vehicle car);
    boolean notWithinBounds(Position p, Direction dir);

}
