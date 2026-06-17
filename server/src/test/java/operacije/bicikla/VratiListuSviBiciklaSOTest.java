package operacije.bicikla;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.BiciklaSaRiksom;
import model.BiciklaZaDecu;
import model.BiciklaZaOdrasle;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

@DisplayName("Testovi za SO VratiListuSviBiciklaSO")
class VratiListuSviBiciklaSOTest {

    VratiListuSviBiciklaSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new VratiListuSviBiciklaSO();
    }

    @AfterEach
    void tearDown() throws Exception {
        so = null;
    }

    @Test
    void testGetListaPocetnaVrednost() {
        assertNull(so.getLista(), "Početna vrednost liste mora biti null");
    }

    private VratiListuSviBiciklaSO napraviSOSaMockom(DBRepozitorijumGenericki mockBroker) throws Exception {
        VratiListuSviBiciklaSO soSaMockom = new VratiListuSviBiciklaSO();
        Field brokerField = ApstraktnaGenerickaOperacija.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    @Test
    void testIzvrsiVracaSvaTriTipaBicikla() throws Exception {
        BiciklaZaOdrasle biciklaOdrasle = new BiciklaZaOdrasle();
        biciklaOdrasle.setIdBicikla(1);
        biciklaOdrasle.setMarka("Trek");

        BiciklaZaDecu biciklaDecu = new BiciklaZaDecu();
        biciklaDecu.setIdBicikla(2);
        biciklaDecu.setMarka("Kids");

        BiciklaSaRiksom biciklaRiksa = new BiciklaSaRiksom();
        biciklaRiksa.setIdBicikla(3);
        biciklaRiksa.setMarka("Riksa");

        List<BiciklaZaOdrasle> listaOdrasle = new ArrayList<>();
        listaOdrasle.add(biciklaOdrasle);

        List<BiciklaZaDecu> listaDecu = new ArrayList<>();
        listaDecu.add(biciklaDecu);

        List<BiciklaSaRiksom> listaRiksa = new ArrayList<>();
        listaRiksa.add(biciklaRiksa);

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.getAll(any(BiciklaZaOdrasle.class), eq(""))).thenReturn((List) listaOdrasle);
        when(mockBroker.getAll(any(BiciklaZaDecu.class), eq(""))).thenReturn((List) listaDecu);
        when(mockBroker.getAll(any(BiciklaSaRiksom.class), eq(""))).thenReturn((List) listaRiksa);

        VratiListuSviBiciklaSO soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(null, null);

        assertNotNull(soSaMockom.getLista());
        assertEquals(3, soSaMockom.getLista().size());
        assertTrue(soSaMockom.getLista().contains(biciklaOdrasle));
        assertTrue(soSaMockom.getLista().contains(biciklaDecu));
        assertTrue(soSaMockom.getLista().contains(biciklaRiksa));
    }

    @Test
    void testIzvrsiPraznaListaKadaNemaBicikala() throws Exception {
        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.getAll(any(BiciklaZaOdrasle.class), eq(""))).thenReturn(new ArrayList<>());
        when(mockBroker.getAll(any(BiciklaZaDecu.class), eq(""))).thenReturn(new ArrayList<>());
        when(mockBroker.getAll(any(BiciklaSaRiksom.class), eq(""))).thenReturn(new ArrayList<>());

        VratiListuSviBiciklaSO soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(null, null);

        assertNotNull(soSaMockom.getLista());
        assertTrue(soSaMockom.getLista().isEmpty());
    }
}