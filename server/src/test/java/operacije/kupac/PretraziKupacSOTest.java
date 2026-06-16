package operacije.kupac;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Kupac;

class PretraziKupacSOTest {

    PretraziKupacSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new PretraziKupacSO();
    }

    @AfterEach
    void tearDown() throws Exception {
        so = null;
    }

    @Test
    void testPreduusloviNull() {
        assertThrows(Exception.class, () -> so.izvrsiOperaciju(null, null));
    }

    @Test
    void testPreduusloviPogresanTip() {
        assertThrows(Exception.class, () -> so.izvrsiOperaciju("pogrešan tip", null));
    }
}