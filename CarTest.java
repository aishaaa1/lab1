import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    Volvo240 volvo;

    @BeforeEach
    void setUp () {
        volvo = new Volvo240();
    }
    @Test
    void getPosition() {

    }
    @Test
    void getDirection() {

    }
    @Test
    void getNrDoors() {
    }

    @Test
    void getEnginePower() {
    }

    @Test
    void getCurrentSpeed() {

    }

    @Test
    void getColor() {

    }

    @Test
    void setColor() {

    }

    @Test
    void startEngine() {

    }



    @Test
    void stopEngine() {

    }

    @Test
    void speedFactor() {
    }

    @Test
    void incrementSpeed() {
        /*
        We already check for this in gas()
         */

    }

    @Test
    void decrementSpeed() {
        /*
        We already check for this in brake()
         */
    }

    /*
    What we want from the gas method is that after method call we don't want the current speed
    to decrease. So we know the difference should be negative (or zero depending on if the previous gas is
    at the upper limit as we can't gas more)
    */
    @Test
    void gas() {
        int test = (int) volvo.getCurrentSpeed();
        volvo.gas(0.3);
        boolean difference = test - volvo.getCurrentSpeed() <= 0;
        assertTrue(difference);
    }

    /*
    We don't want the current speed to be higher than previous one after this method call,
    so the difference should be positive (or equal to zero depending on if the previous speed was zero)
     */
    @Test
    void brake() {
        int test = (int) volvo.getCurrentSpeed();
        volvo.brake(233);
        boolean difference = test - volvo.getCurrentSpeed() >= 0;
        assertTrue(difference);

    }
    /*
    move() should update the car's position, as x and y coordinate should increase/decrease with the
    current speed, so we save the previous coordinates, and we want the new coordinates to
    equal the previous ones + current speed (depending on the direction!)
    */
    @Test
    void move() {
        volvo.turnRight();
        int x = volvo.getPosition().getX();
        volvo.move();
        assertEquals(x + volvo.getCurrentSpeed(), volvo.getPosition().getX());

        volvo.setDirection(Direction.NORTH);
        int y = volvo.getPosition().getY();
        volvo.move();
        assertEquals(y + volvo.getCurrentSpeed(), volvo.getPosition().getY());
    }

    @Test
    void turnRight() {
        volvo.setDirection(Direction.WEST);
        volvo.turnRight();
        volvo.turnRight();
        assertEquals(Direction.EAST, volvo.getDirection());
    }
    @Test
    void turnLeft() {
        /*
        Not important to test, will yield the correct result
         */
    }
}