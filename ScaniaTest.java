import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScaniaTest {
    Scania scania;
    @BeforeEach
    void setUp() {
        scania = new Scania();
    }

    @Test
    void speedFactor() {
    }

    @Test
    void incrementSpeed() {
    }

    @Test
    void decrementSpeed() {
    }

    @Test
    void decreaseFlake() {
    }

    @Test
    void increaseFlake() {
        scania.currentSpeed = 0;
        scania.raiseFlake();
        System.out.print(scania.getFlake().getAngle());
    }

    @Test
    void move() {
        /*int v = scania.getFlake().getAngle();
         v = 30;
         scania.currentSpeed = 30;
         scania.move();
         System.out.print(scania.getCurrentSpeed());

         */
        scania.currentSpeed = 1;
        scania.move();
        System.out.print(scania.getPosition().getX());
    }


}