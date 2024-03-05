public class VolvoWorkShopState implements WorkShopState {

    @Override
    public boolean inWorkShopRange(Vehicle v, WorkShop w, int width) {
        return v.getPosition().getX() + width == w.getX() && v.getPosition().getY() == w.getY();
    }
}
