package operacije.iznajmljivanje;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Iznajmljivanje;
import model.Prodavac;

@DisplayName("Testovi za SO PrikaziRacunSO")
class PrikaziRacunSOTest {

    PrikaziRacunSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new PrikaziRacunSO();
    }

    @AfterEach
    void tearDown() throws Exception {
        so = null;
        java.io.File fajl = new java.io.File("racuni/racun_888.json");
        if (fajl.exists()) {
            fajl.delete();
        }
    }

    @Test
    void testGetRacunPocetnaVrednost() {
        assertNull(so.getRacun(), "Početna vrednost računa mora biti null");
    }

    @Test
    void testPreduusloviNull() {
        Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju(null, null));
        assertTrue(ex.getMessage().contains("odgovarajućeg tipa"));
    }

    @Test
    void testPreduusloviPogresanTip() {
        Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju("pogrešan tip", null));
        assertTrue(ex.getMessage().contains("odgovarajućeg tipa"));
    }

    @Test
    void testPreduusloviIdIznajmljivanjaNeIspravan() {
        Iznajmljivanje i = new Iznajmljivanje();
        i.setIdIznajmljivanje(0);
        Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju(i, null));
        assertTrue(ex.getMessage().contains("ID iznajmljivanja"));
    }

    @Test
    void testPreduusloviIdIznajmljivanjaNegativno() {
        Iznajmljivanje i = new Iznajmljivanje();
        i.setIdIznajmljivanje(-1);
        assertThrows(Exception.class, () -> so.izvrsiOperaciju(i, null));
    }

    @Test
    void testIzvrsiRacunNePostojiVracaNull() throws Exception {
        Iznajmljivanje i = new Iznajmljivanje();
        i.setIdIznajmljivanje(888);

        so.izvrsiOperaciju(i, null);

        assertNull(so.getRacun(), "Racun mora biti null kada fajl ne postoji");
    }

    @Test
    void testIzvrsiRacunPostojiUspesnoProcitan() throws Exception {
        Prodavac prodavac = new model.Prodavac(1, "Ana", "Anic", "aanic", "sifra123");
        model.Kupac kupac = new model.Kupac(1, "Marko", "Markovic", "123456789", new model.Mesto(1, "Beograd"));

        model.BiciklaZaOdrasle bicikla = new model.BiciklaZaOdrasle();
        bicikla.setIdBicikla(1);
        bicikla.setMarka("Trek");
        bicikla.setModel("Marlin");
        bicikla.setCenaPoSatu(500.0);
        bicikla.setCenaPoDanu(2000.0);

        Iznajmljivanje i = new Iznajmljivanje();
        i.setIdIznajmljivanje(888);
        i.setUkupanIznos(1000.0);
        i.setProdavac(prodavac);
        i.setKupac(kupac);

        model.StavkaIznajmljivanja stavka = new model.StavkaIznajmljivanja(
                bicikla, 1, 1000.0, 500.0,
                java.time.LocalDateTime.now(), java.time.LocalDateTime.now().plusHours(2),
                2, 0, i);
        java.util.List<model.StavkaIznajmljivanja> stavke = new java.util.ArrayList<>();
        stavke.add(stavka);
        i.setListaStavkiIznajmljivanja(stavke);

        GenerisiRacunSO generisiSO = new GenerisiRacunSO();
        generisiSO.izvrsiOperaciju(i, null);

        so.izvrsiOperaciju(i, null);

        assertNotNull(so.getRacun(), "Racun mora biti procitan kada fajl postoji");
        assertTrue(so.getRacun().contains("=== RAČUN ==="));
        assertTrue(so.getRacun().contains("888"));
    }
}