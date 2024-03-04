public abstract class WorkShopState {
    /*
    * This class represents the state of the WorkShop object.
     */
    boolean isRepaired;
    abstract void clickLeaveCar(CarRepairShop<Vehicle> carRepairShop, Vehicle vehicle);
    abstract void clickGetCar(CarRepairShop<Vehicle> carRepairShop, Vehicle vehicle);

}
