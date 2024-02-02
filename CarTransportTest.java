import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CarTransportTest {
    CarTransport car;
    CarTransport test;
    Saab95 saab;
    @BeforeEach
    void setUp() {
        car = new CarTransport();
        test = new CarTransport();
        saab = new Saab95();
    }
  /*We want to see that we can't lower the ramp if the truck is moving. It should show compile error
  */
    @Test
    void lower() {
        car.currentSpeed = 0;
        car.lower();
        assertEquals(MODE.DOWN, car.getRamp().getMode());
    }

    @Test
    void raise() {

    }

    /*
    Should be able to load a vehicle, and the car should have the same position as the truck.
     */

    @Test
    void loadTruck() {
        saab.setPosition(2,2);
        test.getRamp().downRamp();
        test.loadTruck(saab);
        assertEquals(1, test.getTransport().size());
        boolean sameX = saab.position.getX() == test.position.getX();
        boolean sameY = saab.position.getY() == test.position.getY(); 
        assertTrue(sameX);
        assertTrue(sameY);
    }
    /*
    Similar concept
     */
    @Test
    void unloadTruck() {
    }
    /*
    We want to see if the car is close enough to the car transport, in this case abs value should be 2.
     */
    @Test
    void isClose() {
        saab.setPosition(0,0);
        assertTrue(test.isClose(saab));

    }
}