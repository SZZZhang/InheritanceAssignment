package Test;

import Functions.Arc;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArcTest {

    Arc arc = new Arc(3,-3,-5);

    @Test
    void testToString() {
        assertEquals("sqrt(3^2 - x^2)", arc.toString());
    }
}