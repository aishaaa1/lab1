
public class CarManagementController implements CarManagementStrategy {
    /*
     * Implement  of concrete car management strategies:
     * CarManagementController  implements CarManagementStrategy, it uses the existing VehicleModel
     * for adding/removing cars in a basic manner.*/
    VehicleModel model;

    public CarManagementController(VehicleModel model){
        this.model = model;
    }

    @Override
    public void addVehicle() {
        model.addVehicle();
    }

    @Override
    public void removeVehicle() {model.removeVehicle();}
}
