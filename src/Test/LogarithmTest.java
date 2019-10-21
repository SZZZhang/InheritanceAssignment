package Test;

import Functions.Logarithm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogarithmTest {
    Logarithm log = new Logarithm(-1, 2,0);

    @Test
    void testToString() {
    }

    @Test
    void testVal() {
        assertEquals(-0.477121254719662, log.val(300));
    }

    @Test
    void undefined() {
    }
}