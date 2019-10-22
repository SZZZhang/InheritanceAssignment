package Test;

import Functions.Quadratic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuadraticTest {
    Quadratic quadratic = new Quadratic(1, 2, 3, 4);
    Quadratic quadratic1 = new Quadratic(-1, 2, 10, 4);
    @Test
    void testGetArea(){
        //assertEquals(5774.53399999984, quadratic.getArea(-20,20));
        assertEquals((5760-128*Math.sqrt(11))/3 + 72*Math.sqrt(11), quadratic1.getArea(-10, 20));
    }

    @Test
    void testVal(){
        assertEquals(-9.703, quadratic1.val(0.45));
    }

    @Test
    void testGetSlope(){
        assertEquals(3.2, quadratic1.getSlope(3.4));
    }
}