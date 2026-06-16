package operacije.kupac;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Kupac;
import model.Mesto;

class KreirajKupacSOTest {

    KreirajKupacSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new KreirajKupacSO();
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

    @Test
    void testPreduusloviImeNull() {
        Kupac k = new Kupac();
        k.setIme(null);
        k.setPrezime("Markovic");
        k.setBrojLicneKarte("123456789");
        k.setMesto(new Mesto(1, "Beograd"));
        assertThrows(Exception.class, () -> so.izvrsiOperaciju(k, null));
    }

    @Test
    void testPreduusloviImePrazno() {
        Kupac k = new Kupac();
        k.setIme("");
        k.setPrezime("Markovic");
        k.setBrojLicneKarte("123456789");
        k.setMesto(new Mesto(1, "Beograd"));
        assertThrows(Exception.class, () -> so.izvrsiOperaciju(k, null));
    }

    @Test
    void testPreduusloviPrezimeNull() {
        Kupac k = new Kupac();
        k.setIme("Marko");
        k.setPrezime(null);
        k.setBrojLicneKarte("123456789");
        k.setMesto(new Mesto(1, "Beograd"));
        assertThrows(Exception.class, () -> so.izvrsiOperaciju(k, null));
    }

    @Test
    void testPreduusloviPrezimePrazno() {
        Kupac k = new Kupac();
        k.setIme("Marko");
        k.setPrezime("");
        k.setBrojLicneKarte("123456789");
        k.setMesto(new Mesto(1, "Beograd"));
        assertThrows(Exception.class, () -> so.izvrsiOperaciju(k, null));
    }

    @Test
    void testPreduusloviBrojLicneKarteNull() {
        Kupac k = new Kupac();
        k.setIme("Marko");
        k.setPrezime("Markovic");
        k.setBrojLicneKarte(null);
        k.setMesto(new Mesto(1, "Beograd"));
        assertThrows(Exception.class, () -> so.izvrsiOperaciju(k, null));
    }

    @Test
    void testPreduusloviBrojLicneKartePrazno() {
        Kupac k = new Kupac();
        k.setIme("Marko");
        k.setPrezime("Markovic");
        k.setBrojLicneKarte("");
        k.setMesto(new Mesto(1, "Beograd"));
        assertThrows(Exception.class, () -> so.izvrsiOperaciju(k, null));
    }

    @Test
    void testPreduusloviBrojLicneKartePogresan() {
        Kupac k = new Kupac();
        k.setIme("Marko");
        k.setPrezime("Markovic");
        k.setBrojLicneKarte("123");
        k.setMesto(new Mesto(1, "Beograd"));
        assertThrows(Exception.class, () -> so.izvrsiOperaciju(k, null));
    }
}