import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTransportTest {
    CarTransport truck;
    Volvo240 volvo;
    CarTransport test;

    @BeforeEach
    void setUp() {
        truck = new CarTransport();
        volvo = new Volvo240();
        test = new CarTransport();
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
    void lowerFlake() {
    }

    @Test
    void raiseFlake() {

    }

    @Test
    void loadTruck() {
        // truck.loadTruck(test);
        truck.loadTruck(volvo);
        assertEquals(1, truck.getCars().size());


    }

    @Test
    void unloadTruck() {
    }

    @Test
    void isClose() {
        System.out.print(truck.isClose(volvo));
    }

    @Test
    void hasFlake() {
    }
}