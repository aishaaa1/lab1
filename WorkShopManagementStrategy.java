public interface WorkShopManagementStrategy {
    /*
     * Strategy interface for leaving and retrieving Vehicles from workshop
     * interface defines methods that all concrete strategies must implement.
     * */
    void getCar();
    void leaveCar();
}
