package json;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import model.BiciklaZaOdrasle;
import model.Iznajmljivanje;
import model.Kupac;
import model.Mesto;
import model.Prodavac;
import model.StavkaIznajmljivanja;

@DisplayName("Testovi za klasu JsonUtil")
class JsonUtilTest {

    private static final String TEST_PUTANJA_RACUN = "test_generisani_racun.json";

    @AfterEach
    void tearDown() {
        new File(TEST_PUTANJA_RACUN).delete();
    }

    @Test
    void testDeserijalizujRacun() throws Exception {
        String putanja = "src/test/resources/test_racun.json";
        String racun = JsonUtil.deserijalizujRacun(putanja);
        assertNotNull(racun);
        assertTrue(racun.contains("=== RAČUN ==="));
        assertTrue(racun.contains("ID iznajmljivanja: 1"));
        assertTrue(racun.contains("Ana Anic"));
        assertTrue(racun.contains("Marko Markovic"));
        assertTrue(racun.contains("123456789"));
        assertTrue(racun.contains("Beograd"));
        assertTrue(racun.contains("Trek"));
        assertTrue(racun.contains("Marlin"));
        assertTrue(racun.contains("5000.0 RSD"));
        assertTrue(racun.contains("42.55 EUR"));
    }

    @Test
    void testDeserijalizujRacunNePostoji() {
        assertThrows(Exception.class, () -> JsonUtil.deserijalizujRacun("src/test/resources/nepostoji.json"));
    }

    @Test
    @Timeout(10)
    void testVratiKursRsdEur() throws Exception {
        double kurs = JsonUtil.vratiKursRsdEur();
        assertTrue(kurs > 0, "Kurs mora biti pozitivan broj");
    }

    @Test
    void testSerijalizujRacunFajlKreiran() throws Exception {
        Iznajmljivanje i = napraviTestnoIznajmljivanje(501);

        JsonUtil.serijalizujRacun(i, TEST_PUTANJA_RACUN);

        assertTrue(new File(TEST_PUTANJA_RACUN).exists(), "JSON fajl racuna mora biti kreiran");
    }

    @Test
    void testSerijalizujDeserijalizujRacunIstiPodaci() throws Exception {
        Iznajmljivanje i = napraviTestnoIznajmljivanje(502);

        JsonUtil.serijalizujRacun(i, TEST_PUTANJA_RACUN);
        String racun = JsonUtil.deserijalizujRacun(TEST_PUTANJA_RACUN);

        assertNotNull(racun, "Deserijalizovan racun ne sme biti null");
        assertTrue(racun.contains("502"));
        assertTrue(racun.contains("Ana"));
        assertTrue(racun.contains("Marko"));
        assertTrue(racun.contains("Trek"));
        assertTrue(racun.contains("1000.0 RSD"));
    }

    @Test
    void testSerijalizujRacunPraznaListaStavki() throws Exception {
        Iznajmljivanje i = new Iznajmljivanje();
        i.setIdIznajmljivanje(503);
        i.setUkupanIznos(0.0);
        i.setProdavac(new Prodavac(1, "Ana", "Anic", "aanic", "sifra123"));
        i.setKupac(new Kupac(1, "Marko", "Markovic", "123456789", new Mesto(1, "Beograd")));
        i.setListaStavkiIznajmljivanja(new ArrayList<>());

        assertDoesNotThrow(() -> JsonUtil.serijalizujRacun(i, TEST_PUTANJA_RACUN), "Ne sme biti greske pri serijalizaciji iznajmljivanja sa praznom listom stavki");
        assertTrue(new File(TEST_PUTANJA_RACUN).exists());
    }

    
    private Iznajmljivanje napraviTestnoIznajmljivanje(int id) {
        Prodavac prodavac = new Prodavac(1, "Ana", "Anic", "aanic", "sifra123");
        Kupac kupac = new Kupac(1, "Marko", "Markovic", "123456789", new Mesto(1, "Beograd"));

        BiciklaZaOdrasle bicikla = new BiciklaZaOdrasle();
        bicikla.setIdBicikla(1);
        bicikla.setMarka("Trek");
        bicikla.setModel("Marlin");
        bicikla.setCenaPoSatu(500.0);
        bicikla.setCenaPoDanu(2000.0);

        Iznajmljivanje i = new Iznajmljivanje();
        i.setIdIznajmljivanje(id);
        i.setUkupanIznos(1000.0);
        i.setProdavac(prodavac);
        i.setKupac(kupac);

        StavkaIznajmljivanja stavka = new StavkaIznajmljivanja(bicikla, 1, 1000.0, 500.0, LocalDateTime.now(), LocalDateTime.now().plusHours(2), 2, 0, i);
        List<StavkaIznajmljivanja> stavke = new ArrayList<>();
        stavke.add(stavka);
        i.setListaStavkiIznajmljivanja(stavke);

        return i;
    }
}