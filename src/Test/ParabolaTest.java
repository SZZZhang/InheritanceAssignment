package Test;

import Functions.Parabola;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParabolaTest {
    Parabola p1 = new Parabola(1, 2, 3);
    Parabola p2 = new Parabola(1, 0, 0);

    @Test
    void testToString() {
        System.out.println(p1);
        System.out.println(p2);
    }

    @Test
    void val() {
        System.out.println(p1.val(8.0));
    }

    @Test
    void getArea() {
    }

    @Test
    void getSlope() {
    }
}