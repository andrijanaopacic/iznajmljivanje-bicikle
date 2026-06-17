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

@DisplayName("Testovi za SO VratiListuSviIznajmljivanjeSO")
class VratiListuSviIznajmljivanjeSOTest {

    VratiListuSviIznajmljivanjeSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new VratiListuSviIznajmljivanjeSO();
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

    
    private VratiListuSviIznajmljivanjeSO napraviSOSaMockom(DBRepozitorijumGenericki mockBroker) throws Exception {
        VratiListuSviIznajmljivanjeSO soSaMockom = new VratiListuSviIznajmljivanjeSO();
        Field brokerField = ApstraktnaGenerickaOperacija.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    @Test
    void testIzvrsiVracaSvaIznajmljivanjaSaStavkama() throws Exception {
        Iznajmljivanje parametar = new Iznajmljivanje();

        Prodavac prodavac = new Prodavac(1, "Ana", "Anic", "aanic", "sifra123");
        Kupac kupac = new Kupac(1, "Marko", "Markovic", "123456789", new Mesto(1, "Beograd"));

        Iznajmljivanje iznajmljivanje1 = new Iznajmljivanje();
        iznajmljivanje1.setIdIznajmljivanje(1);
        iznajmljivanje1.setUkupanIznos(1000.0);
        iznajmljivanje1.setProdavac(prodavac);
        iznajmljivanje1.setKupac(kupac);

        Iznajmljivanje iznajmljivanje2 = new Iznajmljivanje();
        iznajmljivanje2.setIdIznajmljivanje(2);
        iznajmljivanje2.setUkupanIznos(2000.0);
        iznajmljivanje2.setProdavac(prodavac);
        iznajmljivanje2.setKupac(kupac);

        List<Iznajmljivanje> svaIznajmljivanja = new ArrayList<>();
        svaIznajmljivanja.add(iznajmljivanje1);
        svaIznajmljivanja.add(iznajmljivanje2);

        BiciklaZaOdrasle bicikla = new BiciklaZaOdrasle();
        bicikla.setIdBicikla(1);

        StavkaIznajmljivanja stavka1 = new StavkaIznajmljivanja(
                bicikla, 1, 1000.0, 500.0,
                LocalDateTime.now(), LocalDateTime.now().plusHours(2),
                2, 0, iznajmljivanje1);
        List<StavkaIznajmljivanja> stavkeZa1 = new ArrayList<>();
        stavkeZa1.add(stavka1);

        StavkaIznajmljivanja stavka2 = new StavkaIznajmljivanja(
                bicikla, 2, 2000.0, 500.0,
                LocalDateTime.now(), LocalDateTime.now().plusHours(4),
                4, 0, iznajmljivanje2);
        List<StavkaIznajmljivanja> stavkeZa2 = new ArrayList<>();
        stavkeZa2.add(stavka2);

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.getAll(eq(parametar), anyString())).thenReturn((List) svaIznajmljivanja);
        when(mockBroker.getAll(any(StavkaIznajmljivanja.class), contains("idIznajmljivanje = 1")))
                .thenReturn((List) stavkeZa1);
        when(mockBroker.getAll(any(StavkaIznajmljivanja.class), contains("idIznajmljivanje = 2")))
                .thenReturn((List) stavkeZa2);

        VratiListuSviIznajmljivanjeSO soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(parametar, null);

        assertNotNull(soSaMockom.getLista());
        assertEquals(2, soSaMockom.getLista().size());

        Iznajmljivanje rezultat1 = soSaMockom.getLista().get(0);
        assertEquals(1, rezultat1.getListaStavkiIznajmljivanja().size());
        assertEquals(stavka1, rezultat1.getListaStavkiIznajmljivanja().get(0));

        Iznajmljivanje rezultat2 = soSaMockom.getLista().get(1);
        assertEquals(1, rezultat2.getListaStavkiIznajmljivanja().size());
        assertEquals(stavka2, rezultat2.getListaStavkiIznajmljivanja().get(0));
    }

    @Test
    void testIzvrsiPraznaListaKadaNemaIznajmljivanja() throws Exception {
        Iznajmljivanje parametar = new Iznajmljivanje();

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.getAll(eq(parametar), anyString())).thenReturn(new ArrayList<>());

        VratiListuSviIznajmljivanjeSO soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(parametar, null);

        assertNotNull(soSaMockom.getLista());
        assertTrue(soSaMockom.getLista().isEmpty());
    }
}