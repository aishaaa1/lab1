import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarRepairShopTest {
    Volvo240 car;
    CarRepairShop<Volvo240> carRepairShop;
    @BeforeEach
    void setUp(){
        car = new Volvo240();
        carRepairShop = new CarRepairShop<Volvo240>(2);
    }

    @Test
    void leaveCar() {
        carRepairShop.cars.add(car);

    }

    @Test
    void repairedCar() {
        carRepairShop.cars.add(car);
        carRepairShop.repairedCar(car);
        System.out.println(carRepairShop.cars.isEmpty());

    }
}