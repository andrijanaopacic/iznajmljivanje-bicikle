package operacije.prodavac;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Prodavac;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

@DisplayName("Testovi za SO VratiListuProdavacProdavacSO")
class VratiListuProdavacProdavacSOTest {

    VratiListuProdavacProdavacSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new VratiListuProdavacProdavacSO();
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

    private VratiListuProdavacProdavacSO napraviSOSaMockom(DBRepozitorijumGenericki mockBroker) throws Exception {
        VratiListuProdavacProdavacSO soSaMockom = new VratiListuProdavacProdavacSO();
        Field brokerField = ApstraktnaGenerickaOperacija.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    @Test
    void testIzvrsiImeIPrezime() throws Exception {
        Prodavac prodavac = new Prodavac(1, "Ana", "Anic", "aanic", "sifra123");
        List<Prodavac> listaProdavaca = new ArrayList<>();
        listaProdavaca.add(prodavac);

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.getAll(any(Prodavac.class), anyString())).thenReturn((List) listaProdavaca);

        VratiListuProdavacProdavacSO soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(new Prodavac(), "Ana Anic");

        assertNotNull(soSaMockom.getLista());
        assertEquals(1, soSaMockom.getLista().size());
        assertEquals(prodavac, soSaMockom.getLista().get(0));
    }

    @Test
    void testIzvrsiSamoJednaRec() throws Exception {
        Prodavac prodavac = new Prodavac(1, "Ana", "Anic", "aanic", "sifra123");
        List<Prodavac> listaProdavaca = new ArrayList<>();
        listaProdavaca.add(prodavac);

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.getAll(any(Prodavac.class), anyString())).thenReturn((List) listaProdavaca);

        VratiListuProdavacProdavacSO soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(new Prodavac(), "Ana");

        assertNotNull(soSaMockom.getLista());
        assertEquals(1, soSaMockom.getLista().size());
    }

    @Test
    void testIzvrsiPraznaListaKadaNemaRezultata() throws Exception {
        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.getAll(any(Prodavac.class), anyString())).thenReturn(new ArrayList<>());

        VratiListuProdavacProdavacSO soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(new Prodavac(), "Nepostojeci Prodavac");

        assertNotNull(soSaMockom.getLista());
        assertTrue(soSaMockom.getLista().isEmpty());
    }
}