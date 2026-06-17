package operacije.kupac;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Kupac;
import model.Mesto;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

@DisplayName("Testovi za SO PretraziKupacSO")
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
    void testGetKupacPocetnaVrednost() {
        assertNull(so.getKupac(), "Početna vrednost kupca mora biti null");
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

   
    private PretraziKupacSO napraviSOSaMockom(DBRepozitorijumGenericki mockBroker) throws Exception {
        PretraziKupacSO soSaMockom = new PretraziKupacSO();
        Field brokerField = ApstraktnaGenerickaOperacija.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    @Test
    void testIzvrsiKupacPronadjen() throws Exception {
        Kupac trazeniKupac = new Kupac();
        trazeniKupac.setIdKupac(1);

        Kupac kupacIzBaze = new Kupac(1, "Marko", "Markovic", "123456789", new Mesto(1, "Beograd"));

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.getObject(eq(trazeniKupac), anyString())).thenReturn(kupacIzBaze);

        PretraziKupacSO soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(trazeniKupac, null);

        assertEquals(kupacIzBaze, soSaMockom.getKupac(), "Kupac mora biti pronađen i postavljen");
    }

    @Test
    void testIzvrsiKupacNijePronadjen() throws Exception {
        Kupac trazeniKupac = new Kupac();
        trazeniKupac.setIdKupac(999);

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.getObject(eq(trazeniKupac), anyString())).thenReturn(null);

        PretraziKupacSO soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(trazeniKupac, null);

        assertNull(soSaMockom.getKupac(), "Kupac mora biti null kada nije pronađen u bazi");
    }
}