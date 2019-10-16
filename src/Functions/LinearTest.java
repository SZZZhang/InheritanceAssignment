package Functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinearTest {

    Linear lin = new Linear(1, 0, 0);

    @Test
    public void testToString() {
        assertEquals("1(x - 0) + 0", lin.toString());
    }

    @Test
    void val() {
    }

    @Test
    void undefined() {
    }

    @Test
    void getArea() {
    }

    @Test
    void getSlope() {
    }

    @Test
    void draw() {
    }
}