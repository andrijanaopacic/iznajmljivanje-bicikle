package operacije.iznajmljivanje;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.BiciklaZaOdrasle;
import model.Iznajmljivanje;
import model.Kupac;
import model.Mesto;
import model.Prodavac;
import model.StavkaIznajmljivanja;

@DisplayName("Testovi za SO GenerisiRacunSO")
class GenerisiRacunSOTest {

    GenerisiRacunSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new GenerisiRacunSO();
    }

    @AfterEach
    void tearDown() throws Exception {
        so = null;
        java.io.File fajl = new java.io.File("racuni/racun_999.json");
        if (fajl.exists()) {
            fajl.delete();
        }
    }

    @Test
    void testGetUspesnoPocetnaVrednost() {
        assertFalse(so.getUspesno(), "Početna vrednost atributa uspesno mora biti false");
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
        i.setListaStavkiIznajmljivanja(new ArrayList<>());
        Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju(i, null));
        assertTrue(ex.getMessage().contains("ID iznajmljivanja"));
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
        Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju(i, null));
        assertTrue(ex.getMessage().contains("nema stavki"));
    }

    @Test
    void testPreduusloviListaStavkiPrazna() {
        Iznajmljivanje i = new Iznajmljivanje();
        i.setIdIznajmljivanje(1);
        i.setListaStavkiIznajmljivanja(new ArrayList<>());
        assertThrows(Exception.class, () -> so.izvrsiOperaciju(i, null));
    }

    @Test
    void testIzvrsiUspesnoGenerisanRacun() throws Exception {
        Prodavac prodavac = new Prodavac(1, "Ana", "Anic", "aanic", "sifra123");
        Kupac kupac = new Kupac(1, "Marko", "Markovic", "123456789", new Mesto(1, "Beograd"));

        BiciklaZaOdrasle bicikla = new BiciklaZaOdrasle();
        bicikla.setIdBicikla(1);
        bicikla.setMarka("Trek");
        bicikla.setModel("Marlin");
        bicikla.setCenaPoSatu(500.0);
        bicikla.setCenaPoDanu(2000.0);

        Iznajmljivanje i = new Iznajmljivanje();
        i.setIdIznajmljivanje(999);
        i.setUkupanIznos(1000.0);
        i.setProdavac(prodavac);
        i.setKupac(kupac);

        StavkaIznajmljivanja stavka = new StavkaIznajmljivanja(bicikla, 1, 1000.0, 500.0,LocalDateTime.now(), LocalDateTime.now().plusHours(2), 2, 0, i);

        List<StavkaIznajmljivanja> stavke = new ArrayList<>();
        stavke.add(stavka);
        i.setListaStavkiIznajmljivanja(stavke);

        so.izvrsiOperaciju(i, null);

        assertTrue(so.getUspesno(), "Generisanje računa mora biti uspešno");

        java.io.File fajl = new java.io.File("racuni/racun_999.json");
        assertTrue(fajl.exists(), "Fajl sa računom mora biti kreiran");
    }
}