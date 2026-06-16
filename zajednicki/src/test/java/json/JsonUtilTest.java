package json;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JsonUtilTest {

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
    void testVratiKursRsdEur() throws Exception {
        double kurs = JsonUtil.vratiKursRsdEur();
        assertTrue(kurs > 0);
    }
}