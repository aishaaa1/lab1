import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Saab95Test {
    Saab95 saab;
    @BeforeEach
    void setUp() {
        saab = new Saab95();
    }
    @Test
    void speedFactor() {}
    /*
    We want the current speed to not be higher than the previous one, to make sure it actually "brakes"
    We do it simply by looking at the difference, if it's positive then we know it works.
    Because of the modifications of brake and gas methods, we know certain values cannot be passed
     */
    @Test
    void brake() {
        int test = (int) saab.getCurrentSpeed();
        saab.brake(1);
        boolean difference1 = test - saab.getCurrentSpeed() >= 0;
        assertTrue(difference1);
    }
    /*
    Same thinking as the previous one
     */
    @Test
    void gas() {
        int test = (int) saab.getCurrentSpeed();
        saab.gas(0.3);
        boolean difference = test - saab.getCurrentSpeed() <= 0;
        assertTrue(difference);
    }
}