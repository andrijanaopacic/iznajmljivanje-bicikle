package operacije.iznajmljivanje;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import model.BiciklaZaOdrasle;
import model.Iznajmljivanje;
import model.Kupac;
import model.Mesto;
import model.Prodavac;
import model.StavkaIznajmljivanja;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

@DisplayName("Testovi za SO KreirajIznajmljivanjeSO")
class KreirajIznajmljivanjeSOTest {

    KreirajIznajmljivanjeSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new KreirajIznajmljivanjeSO();
    }

    @AfterEach
    void tearDown() throws Exception {
        so = null;
        java.io.File fajl = new java.io.File("racuni/racun_777.json");
        if (fajl.exists()) {
            fajl.delete();
        }
    }

    @Test
    void testGetUspesnoPocetnaVrednost() {
        assertFalse(so.getUspesno(), "Početna vrednost atributa uspesno mora biti false");
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

    private KreirajIznajmljivanjeSO napraviSOSaMockom(DBRepozitorijumGenericki mockBroker, Connection mockConnection, MockedStatic<DBKonekcija> mockedStatic) throws Exception {
        DBKonekcija mockDBKonekcija = mock(DBKonekcija.class);
        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDBKonekcija);
        when(mockDBKonekcija.getConnection()).thenReturn(mockConnection);

        KreirajIznajmljivanjeSO soSaMockom = new KreirajIznajmljivanjeSO();
        Field brokerField = ApstraktnaGenerickaOperacija.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    @Test
    void testIzvrsiIznajmljivanjeNePostojiUspesnoKreirano() throws Exception {
        Prodavac prodavac = new Prodavac(1, "Ana", "Anic", "aanic", "sifra123");
        Kupac kupac = new Kupac(1, "Marko", "Markovic", "123456789", new Mesto(1, "Beograd"));

        BiciklaZaOdrasle bicikla = new BiciklaZaOdrasle();
        bicikla.setIdBicikla(1);
        bicikla.setMarka("Trek");
        bicikla.setModel("Marlin");
        bicikla.setCenaPoSatu(500.0);
        bicikla.setCenaPoDanu(2000.0);

        Iznajmljivanje i = new Iznajmljivanje();
        i.setUkupanIznos(1000.0);
        i.setProdavac(prodavac);
        i.setKupac(kupac);

        LocalDateTime vremeOd = LocalDateTime.now();
        LocalDateTime vremeDo = vremeOd.plusHours(2);
        StavkaIznajmljivanja stavka = new StavkaIznajmljivanja(bicikla, 1, 1000.0, 500.0, vremeOd, vremeDo, 2, 0, i);
        List<StavkaIznajmljivanja> stavke = new ArrayList<>();
        stavke.add(stavka);
        i.setListaStavkiIznajmljivanja(stavke);

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        doNothing().when(mockBroker).add(any());

        Connection mockConnection = mock(Connection.class);
        Statement mockStatementPreduslovi = mock(Statement.class);
        ResultSet mockRsPreduslovi = mock(ResultSet.class);
        when(mockRsPreduslovi.next()).thenReturn(false); 

        Statement mockStatementIzvrsi = mock(Statement.class);
        ResultSet mockRsIzvrsi = mock(ResultSet.class);
        when(mockRsIzvrsi.next()).thenReturn(true, false); 
        when(mockRsIzvrsi.getInt("idIznajmljivanje")).thenReturn(777);

        when(mockConnection.createStatement()).thenReturn(mockStatementPreduslovi).thenReturn(mockStatementIzvrsi);
        when(mockStatementPreduslovi.executeQuery(anyString())).thenReturn(mockRsPreduslovi);
        when(mockStatementIzvrsi.executeQuery(anyString())).thenReturn(mockRsIzvrsi);

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            KreirajIznajmljivanjeSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(i, null);

            assertTrue(soSaMockom.getUspesno(), "Kreiranje iznajmljivanja mora biti uspešno");
            assertEquals(777, i.getIdIznajmljivanje());
            verify(mockBroker, times(1)).add(i);
            verify(mockBroker, times(1)).add(stavka);
        }
    }
    
    @Test
    void testIzvrsiIznajmljivanjeNevalidanUkupanIznosOdbijeno() throws Exception {
        Prodavac prodavac = new Prodavac(1, "Ana", "Anic", "aanic", "sifra123");
        Kupac kupac = new Kupac(1, "Marko", "Markovic", "123456789", new Mesto(1, "Beograd"));

        BiciklaZaOdrasle bicikla = new BiciklaZaOdrasle();
        bicikla.setIdBicikla(1);
        bicikla.setMarka("Trek");
        bicikla.setModel("Marlin");
        bicikla.setCenaPoSatu(500.0);
        bicikla.setCenaPoDanu(2000.0);

        Iznajmljivanje i = new Iznajmljivanje();
        i.setUkupanIznos(0.0);
        i.setProdavac(prodavac);
        i.setKupac(kupac);

        LocalDateTime vremeOd = LocalDateTime.now();
        LocalDateTime vremeDo = vremeOd.plusHours(2);
        StavkaIznajmljivanja stavka = new StavkaIznajmljivanja(bicikla, 1, 1000.0, 500.0, vremeOd, vremeDo, 2, 0, i);
        List<StavkaIznajmljivanja> stavke = new ArrayList<>();
        stavke.add(stavka);
        i.setListaStavkiIznajmljivanja(stavke);

        Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju(i, null));

        assertTrue(ex.getMessage().contains("Ukupan iznos mora biti veci od nule"));
    }
}