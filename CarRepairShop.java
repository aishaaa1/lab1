import java.util.ArrayList;
import java.util.List;

public class CarRepairShop <T extends Vehicle> {
    private final int maxCapacity;
    final List<T> vehicles;
    public CarRepairShop(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        vehicles = new ArrayList<>(maxCapacity);
    }

    public void leaveCar(T v) {
        if (vehicles.contains(v)) {
            throw new IllegalArgumentException("It's already in repair");
        }
        if (vehicles.size() >= maxCapacity) {
            throw new IllegalArgumentException("Unfortunately we are full at the moment");
        }
        vehicles.add(v);
    }
    public void repairedCar (T v) {
        if (vehicles.contains(v)) {
            vehicles.remove(v);
        }
        else throw new IllegalArgumentException("This car is not in repair");
    }



















}
