package operacije.iznajmljivanje;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PretraziIznajmljivanjeSOTest {

	PretraziIznajmljivanjeSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new PretraziIznajmljivanjeSO();
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
    void testPreduusloviPogrešanTip() {
        assertThrows(Exception.class, () -> so.izvrsiOperaciju("pogrešan tip", null));
    }

}
