public class NotInWorkShopState extends WorkShopState {
    @Override
    void clickLeaveCar(CarRepairShop<Vehicle> carRepairShop, Vehicle vehicle) {
        isRepaired = false;
        carRepairShop.leaveCar(vehicle);
    }

    @Override
    void clickGetCar(CarRepairShop<Vehicle> carRepairShop, Vehicle vehicle) {
        isRepaired = true;
    }
}
