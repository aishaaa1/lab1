public class VehicleFactory {
    //An array to hold the vehicle classes, <?>: ? wild card  "class of any type"
    private final Class<?>[] vehicleClasses = {Volvo240.class, Saab95.class, Scania.class};

    public Vehicle createRandVehicle(){
        //random index within bounds of vehicleClasses array
        int randIndex = (int) (Math.random() * vehicleClasses.length);
        try {
            return (Vehicle) vehicleClasses[randIndex].newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public Volvo240 createVolvo() { return new Volvo240(); }
    public Saab95 createSaab() { return new Saab95(); }
    public Scania createScania() { return new Scania(); }

}