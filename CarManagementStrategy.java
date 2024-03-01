public interface CarManagementStrategy {
    /*
    * Strategy interface for adding and removing Vehicles
    * interface defines methods that all concrete strategies must implement.
    * */
    void addVehicle();
    void removeVehicle();
}
