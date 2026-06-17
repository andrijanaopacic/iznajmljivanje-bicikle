package operacije.iznajmljivanje;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

@DisplayName("Testovi za SO PretraziIznajmljivanjeSO")
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
    void testGetIznajmljivanjePocetnaVrednost() {
        assertNull(so.getIznajmljivanje(), "Početna vrednost iznajmljivanja mora biti null");
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

    private PretraziIznajmljivanjeSO napraviSOSaMockom(DBRepozitorijumGenericki mockBroker) throws Exception {
        PretraziIznajmljivanjeSO soSaMockom = new PretraziIznajmljivanjeSO();
        Field brokerField = ApstraktnaGenerickaOperacija.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    @Test
    void testIzvrsiIznajmljivanjePronadjenoSaStavkama() throws Exception {
        Iznajmljivanje parametar = new Iznajmljivanje();
        parametar.setIdIznajmljivanje(1);

        Prodavac prodavac = new Prodavac(1, "Ana", "Anic", "aanic", "sifra123");
        Kupac kupac = new Kupac(1, "Marko", "Markovic", "123456789", new Mesto(1, "Beograd"));

        Iznajmljivanje iznajmljivanjeIzBaze = new Iznajmljivanje();
        iznajmljivanjeIzBaze.setIdIznajmljivanje(1);
        iznajmljivanjeIzBaze.setUkupanIznos(1000.0);
        iznajmljivanjeIzBaze.setProdavac(prodavac);
        iznajmljivanjeIzBaze.setKupac(kupac);

        BiciklaZaOdrasle bicikla = new BiciklaZaOdrasle();
        bicikla.setIdBicikla(1);
        StavkaIznajmljivanja stavka = new StavkaIznajmljivanja(bicikla, 1, 1000.0, 500.0,LocalDateTime.now(), LocalDateTime.now().plusHours(2),2, 0, iznajmljivanjeIzBaze);
        List<StavkaIznajmljivanja> stavke = new ArrayList<>();
        stavke.add(stavka);

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.getObject(eq(parametar), anyString())).thenReturn(iznajmljivanjeIzBaze);
        when(mockBroker.getAll(any(StavkaIznajmljivanja.class), anyString())).thenReturn((List) stavke);

        PretraziIznajmljivanjeSO soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(parametar, null);

        assertNotNull(soSaMockom.getIznajmljivanje());
        assertEquals(1, soSaMockom.getIznajmljivanje().getIdIznajmljivanje());
        assertEquals(1, soSaMockom.getIznajmljivanje().getListaStavkiIznajmljivanja().size());
        assertEquals(stavka, soSaMockom.getIznajmljivanje().getListaStavkiIznajmljivanja().get(0));
    }

    @Test
    void testIzvrsiIznajmljivanjeNijePronadjenoBacaException() throws Exception {
        Iznajmljivanje parametar = new Iznajmljivanje();
        parametar.setIdIznajmljivanje(999);

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.getObject(eq(parametar), anyString())).thenReturn(null);

        PretraziIznajmljivanjeSO soSaMockom = napraviSOSaMockom(mockBroker);

        assertThrows(NullPointerException.class,
                () -> soSaMockom.izvrsiOperaciju(parametar, null));
    }
}