package operacije.mesto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Mesto;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

@DisplayName("Testovi za SO PretraziMestoSO")
class PretraziMestoSOTest {

    PretraziMestoSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new PretraziMestoSO();
    }

    @AfterEach
    void tearDown() throws Exception {
        so = null;
    }

    @Test
    void testGetMestoPocetnaVrednost() {
        assertNull(so.getMesto(), "Početna vrednost mesta mora biti null");
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

    private PretraziMestoSO napraviSOSaMockom(DBRepozitorijumGenericki mockBroker) throws Exception {
        PretraziMestoSO soSaMockom = new PretraziMestoSO();
        Field brokerField = ApstraktnaGenerickaOperacija.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    @Test
    void testIzvrsiMestoPronadjeno() throws Exception {
        Mesto trazenoMesto = new Mesto();
        trazenoMesto.setIdMesto(1);

        Mesto mestoIzBaze = new Mesto(1, "Beograd");

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.getObject(eq(trazenoMesto), anyString())).thenReturn(mestoIzBaze);

        PretraziMestoSO soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(trazenoMesto, null);

        assertEquals(mestoIzBaze, soSaMockom.getMesto(), "Mesto mora biti pronadjeno i postavljeno");
    }

    @Test
    void testIzvrsiMestoNijePronadjeno() throws Exception {
        Mesto trazenoMesto = new Mesto();
        trazenoMesto.setIdMesto(999);

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.getObject(eq(trazenoMesto), anyString())).thenReturn(null);

        PretraziMestoSO soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(trazenoMesto, null);

        assertNull(soSaMockom.getMesto(), "Mesto mora biti null kada nije pronadjeno u bazi");
    }
}