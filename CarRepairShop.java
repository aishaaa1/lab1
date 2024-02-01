import java.util.ArrayList;
import java.util.List;

public class CarRepairShop <Car> {
    private int maxCapacity;
    final List<Car> cars;
    public CarRepairShop (int maxCapacity) {
        this.maxCapacity = maxCapacity;
        cars = new ArrayList<>(maxCapacity);
    }

    public void leaveCar (Car car) {
        cars.add(car);
    }
    public void repairedCar (Car car){
        if(cars.contains(car)){
            cars.remove(car);
            System.out.println(car.toString());
        }
        else throw new IllegalArgumentException("This car is not in the list.");
    }

}
