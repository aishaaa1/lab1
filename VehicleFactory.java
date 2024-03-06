import java.util.Random;
import java.util.Random.*;
public class VehicleFactory {
    //An array to hold the vehicle classes, <?>: ? wild card  "class of any type"
    private final Class<?>[] vehicleClasses = {Saab95.class, Scania.class,Volvo240.class};

    public Vehicle createRandVehicle(){
        //random index within bounds of vehicleClasses array
        int randIndex = (int) (Math.random() * vehicleClasses.length);
        try {
            return (Vehicle) vehicleClasses[randIndex].newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /*public Vehicle createRandVehicle(){
        Random rand = new Random();
        int i = rand.nextInt(1, 3);
        switch (i) {
            case 1:
                return createVolvo();
            case 2:
                return createSaab();
            case 3:
                return createScania();
        }

        return null;
    }*/
    public Volvo240 createVolvo() { return new Volvo240();}
    public Saab95 createSaab() { return new Saab95(); }
    public Scania createScania() {return new Scania();}

}