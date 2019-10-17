package Test;

import Functions.Cubic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CubicTest {
    Cubic cubic = new Cubic(1, 0, 0, 0,0);

    @Test
    public void testGetArea() {
        assertEquals(598.006124999976, cubic.getArea(2, 7));
    }

    @Test
    public void testGetVal() {
        assertEquals(8, cubic.val(2));
    }
}