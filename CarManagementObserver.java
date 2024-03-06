
public interface CarManagementObserver {
    /*
    * Dedicated observer interface for adding/removing:
    */
    void actOnCarAdded(VehicleImage vehicleImage);
    void actOnCarRemoved();
    void actOnCarRemoved(VehicleImage image);
}
