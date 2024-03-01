public interface IModelObservable {
    public void add(IModelObserver observer);
    public void remove (IModelObserver observer);
    public void notifyObserevers();
    public Position getPosition();
    public String getModelName();

}
