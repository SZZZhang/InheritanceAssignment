package Test;

import Functions.Cubic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CubicTest {
    Cubic cubic = new Cubic(1, 0, 0, 0,0.0);
    Cubic cubic2 = new Cubic(-0.35, -0.25, -0.5, -1.0,3);

    @Test
    public void testGetArea() {
        assertEquals(598.006124999976, cubic.getArea(2, 7));
    }

    @Test
    public void testGetVal() {
        assertEquals(8, cubic.val(2));
    }

    @Test
    void testToString() {
        assertEquals("0.35x^3 + 0.25x^2 - 0.5x - 1.0", cubic.toString());
        assertEquals("-0.35(x - 3.0)^3 - 0.25(x - 3.0)^2 - 0.5(x - 3.0) - 1.0", cubic2.toString());
    }
}