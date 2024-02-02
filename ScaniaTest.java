import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ScaniaTest {
    Scania car;

    @BeforeEach
    void setUp() {
        car = new Scania(2, Color.cyan, 234, "Scania");
    }

    @Test
    void raiseFlake() {
        car.currentSpeed = 0;
        car.raise();
        System.out.print(car.getFlake().getAngle());
    }
    @Test
    void lowerFlake() {
    }
    /*
    Want to test whether car stops when the angle is not 0
     */
    @Test
    void move() {
        car.currentSpeed = 10;
        car.getFlake().setAngle(20);
        car.move();
        assertEquals(0, car.currentSpeed);
    }
}