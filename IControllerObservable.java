public interface IControllerObservable {
   public void add(IControllerObserver observer);
   public void remove(IControllerObserver observer);
   public void notifyObserevers(IModelObservable car);

}
