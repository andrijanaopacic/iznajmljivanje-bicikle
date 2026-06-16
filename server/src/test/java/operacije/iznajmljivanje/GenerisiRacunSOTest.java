package operacije.iznajmljivanje;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Iznajmljivanje;
import model.Kupac;
import model.Mesto;
import model.Prodavac;
import model.StavkaIznajmljivanja;

class GenerisiRacunSOTest {

    GenerisiRacunSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new GenerisiRacunSO();
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

    @Test
    void testPreduusloviIdIznajmljivanjaNeIspravan() {
        Iznajmljivanje i = new Iznajmljivanje();
        i.setIdIznajmljivanje(0);
        i.setListaStavkiIznajmljivanja(new ArrayList<>());
        assertThrows(Exception.class, () -> so.izvrsiOperaciju(i, null));
    }

    @Test
    void testPreduusloviIdIznajmljivanjaNegativno() {
        Iznajmljivanje i = new Iznajmljivanje();
        i.setIdIznajmljivanje(-1);
        i.setListaStavkiIznajmljivanja(new ArrayList<>());
        assertThrows(Exception.class, () -> so.izvrsiOperaciju(i, null));
    }

    @Test
    void testPreduusloviListaStavkiNull() {
        Iznajmljivanje i = new Iznajmljivanje();
        i.setIdIznajmljivanje(1);
        i.setListaStavkiIznajmljivanja(null);
        assertThrows(Exception.class, () -> so.izvrsiOperaciju(i, null));
    }

    @Test
    void testPreduusloviListaStavkiPrazna() {
        Iznajmljivanje i = new Iznajmljivanje();
        i.setIdIznajmljivanje(1);
        i.setListaStavkiIznajmljivanja(new ArrayList<>());
        assertThrows(Exception.class, () -> so.izvrsiOperaciju(i, null));
    }
}