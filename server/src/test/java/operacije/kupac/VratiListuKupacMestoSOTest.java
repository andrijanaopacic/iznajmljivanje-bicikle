package operacije.kupac;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Kupac;
import model.Mesto;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

@DisplayName("Testovi za SO VratiListuKupacMestoSO")
class VratiListuKupacMestoSOTest {

    VratiListuKupacMestoSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new VratiListuKupacMestoSO();
    }

    @AfterEach
    void tearDown() throws Exception {
        so = null;
    }

    @Test
    void testGetListaPocetnaVrednost() {
        assertNull(so.getLista(), "Početna vrednost liste mora biti null");
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

    /**
     * Pomocna metoda koja injektuje mock broker u SO objekat preko refleksije.
     */
    private VratiListuKupacMestoSO napraviSOSaMockom(DBRepozitorijumGenericki mockBroker) throws Exception {
        VratiListuKupacMestoSO soSaMockom = new VratiListuKupacMestoSO();
        Field brokerField = ApstraktnaGenerickaOperacija.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    @Test
    void testIzvrsiSaNazivomMestaListaPronadjena() throws Exception {
        Kupac parametar = new Kupac();

        Kupac kupacIzBaze = new Kupac(1, "Marko", "Markovic", "123456789", new Mesto(1, "Beograd"));
        List<Kupac> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(kupacIzBaze);

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.getAll(eq(parametar), anyString())).thenReturn((List) ocekivanaLista);

        VratiListuKupacMestoSO soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(parametar, "Beograd");

        assertNotNull(soSaMockom.getLista());
        assertEquals(1, soSaMockom.getLista().size());
        assertEquals(kupacIzBaze, soSaMockom.getLista().get(0));
    }

    @Test
    void testIzvrsiPraznoMestoVracaSveKupce() throws Exception {
        Kupac parametar = new Kupac();

        Kupac kupac1 = new Kupac(1, "Marko", "Markovic", "123456789", new Mesto(1, "Beograd"));
        Kupac kupac2 = new Kupac(2, "Ana", "Anic", "987654321", new Mesto(2, "Novi Sad"));
        List<Kupac> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(kupac1);
        ocekivanaLista.add(kupac2);

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.getAll(eq(parametar), anyString())).thenReturn((List) ocekivanaLista);

        VratiListuKupacMestoSO soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(parametar, "");

        assertNotNull(soSaMockom.getLista());
        assertEquals(2, soSaMockom.getLista().size());
    }

    @Test
    void testIzvrsiListaPraznaKadaNijePronadjeno() throws Exception {
        Kupac parametar = new Kupac();

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.getAll(eq(parametar), anyString())).thenReturn(new ArrayList<>());

        VratiListuKupacMestoSO soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(parametar, "NepostojeceMesto");

        assertNotNull(soSaMockom.getLista());
        assertTrue(soSaMockom.getLista().isEmpty());
    }
}