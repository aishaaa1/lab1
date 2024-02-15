import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarRepairShopTest {
    CarRepairShop<Saab95> saab95CarRepairShop;
    Volvo240 volvo;
    Saab95 saab95;
    Saab95 saab951;
    Saab95 saab952;
    Saab95 saab953;
    Scania scania;
    @BeforeEach
    void setUp() {
        saab95CarRepairShop = new CarRepairShop<Saab95>(3);
        volvo = new Volvo240();
        saab95 = new Saab95();
        saab951 = new Saab95();
        saab952 = new Saab95();
        saab953 = new Saab95();
    }
    /*
    A relevant test for leaveCar() is to see if there will be a static compile error when trying to
    leave in the wrong car
     */
    @Test
    void leaveCar() {
        //saab95CarRepairShop.leaveCar(volvo); uncomment this
        saab95CarRepairShop.leaveCar(saab95);
        saab95CarRepairShop.leaveCar(saab952);
        assertEquals(2, saab95CarRepairShop.vehicles.size());
    }
    /*
    We want to get an exception when trying to retrieve a car that is not in repair (1)
    We want to be able to retrieve a car if it's in repair (2)

     */

    @Test
    void repairedCar() {
        //saab95CarRepairShop.repairedCar(saab95); will throw an exception
        saab95CarRepairShop.leaveCar(saab951);
        saab95CarRepairShop.repairedCar(saab951);
        assertEquals(0, saab95CarRepairShop.vehicles.size());

    }
}