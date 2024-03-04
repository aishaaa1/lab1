import java.util.ArrayList;
import java.util.List;

public class WorkShopModel {
    Vehicle vehicle;
    CarRepairShop<Vehicle> carRepairShop;
    /*
    State pattern, an object can have different states.
    In this case if the vehicle is in repair or not
     */
    WorkShopState state;
    List<WorkShopObserver> workShopObservers = new ArrayList<>();
    List<WorkShopManagementObserver> managementObservers = new ArrayList<>();
    public WorkShopModel(Vehicle vehicle, CarRepairShop<Vehicle> carRepairShop) {
        this.vehicle = vehicle;
        this.carRepairShop = carRepairShop;
        this.state = new NotInWorkShopState();
    }
    public void changeState(WorkShopState state) {
        this.state = state;
        notifyWorkShopObservers(this.state);
    }
    public void leaveCarAtWorkShop() {
        state.clickLeaveCar(carRepairShop, vehicle);
        notifyLeaveCar();
    }
    public void getCarFromWorkShop() {
        state.clickGetCar(carRepairShop, vehicle);
        notifyGetCar();
    }
    void addObservers(WorkShopObserver observer) { workShopObservers.add(observer); }

    void notifyWorkShopObservers(WorkShopState state) {
        for (WorkShopObserver ob : workShopObservers) {
            ob.updateView(state);
        }
    }

    void addManagementObserver(WorkShopManagementObserver ob) { managementObservers.add(ob); }

    public void notifyLeaveCar (){
        for (WorkShopManagementObserver ob : managementObservers){
            ob.actOnLeaveCarToWorkShop();
        }
    }

    public void notifyGetCar(){
        for (WorkShopManagementObserver ob : managementObservers){
            ob.actOnGetCarFromWorkShop();
        }
    }

}

