public class InWorkShopState extends WorkShopState {

    @Override
    void clickLeaveCar(CarRepairShop<Vehicle> carRepairShop, Vehicle vehicle) {
        isRepaired = false;
    }

    @Override
    void clickGetCar(CarRepairShop<Vehicle> carRepairShop, Vehicle vehicle) {
        isRepaired = true;
        carRepairShop.repairedCar(vehicle);
    }


}
