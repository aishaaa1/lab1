public class WorkShopManagementController implements WorkShopManagementStrategy{
    WorkShopModel model;
    public WorkShopManagementController(WorkShopModel model) { this.model = model; }

    @Override
    public void getCar() {
        model.leaveCarAtWorkShop();
    }

    @Override
    public void leaveCar() {
        model.getCarFromWorkShop();
    }
}
