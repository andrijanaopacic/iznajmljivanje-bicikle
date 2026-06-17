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

@DisplayName("Testovi za SO VratiListuKupacKupacSO")
class VratiListuKupacKupacSOTest {

    VratiListuKupacKupacSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new VratiListuKupacKupacSO();
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

   
    private VratiListuKupacKupacSO napraviSOSaMockom(DBRepozitorijumGenericki mockBroker) throws Exception {
        VratiListuKupacKupacSO soSaMockom = new VratiListuKupacKupacSO();
        Field brokerField = ApstraktnaGenerickaOperacija.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    @Test
    void testIzvrsiImePrezimeListaPronadjena() throws Exception {
        Kupac parametar = new Kupac();

        Kupac kupacIzBaze = new Kupac(1, "Marko", "Markovic", "123456789", new Mesto(1, "Beograd"));
        List<Kupac> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(kupacIzBaze);

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.getAll(eq(parametar), anyString())).thenReturn((List) ocekivanaLista);

        VratiListuKupacKupacSO soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(parametar, "Marko Markovic");

        assertNotNull(soSaMockom.getLista());
        assertEquals(1, soSaMockom.getLista().size());
        assertEquals(kupacIzBaze, soSaMockom.getLista().get(0));
    }

    @Test
    void testIzvrsiSamoImeListaPronadjena() throws Exception {
        Kupac parametar = new Kupac();

        Kupac kupacIzBaze = new Kupac(1, "Marko", "Markovic", "123456789", new Mesto(1, "Beograd"));
        List<Kupac> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(kupacIzBaze);

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.getAll(eq(parametar), anyString())).thenReturn((List) ocekivanaLista);

        VratiListuKupacKupacSO soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(parametar, "Marko");

        assertNotNull(soSaMockom.getLista());
        assertEquals(1, soSaMockom.getLista().size());
    }

    @Test
    void testIzvrsiListaPraznaKadaNijePronadjeno() throws Exception {
        Kupac parametar = new Kupac();

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.getAll(eq(parametar), anyString())).thenReturn(new ArrayList<>());

        VratiListuKupacKupacSO soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(parametar, "Nepostojeci Kupac");

        assertNotNull(soSaMockom.getLista());
        assertTrue(soSaMockom.getLista().isEmpty());
    }
}