package operacije.kupac;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import model.Kupac;
import model.Mesto;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

@DisplayName("Testovi za SO KreirajKupacSO")
class KreirajKupacSOTest {

    KreirajKupacSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new KreirajKupacSO();
    }

    @AfterEach
    void tearDown() throws Exception {
        so = null;
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

    @Test
    void testPreduusloviImeNull() {
        Kupac k = new Kupac();
        k.setIme(null);
        k.setPrezime("Markovic");
        k.setBrojLicneKarte("123456789");
        k.setMesto(new Mesto(1, "Beograd"));
        Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju(k, null));
        assertTrue(ex.getMessage().contains("imena"));
    }

    @Test
    void testPreduusloviImePrazno() {
        Kupac k = new Kupac();
        k.setIme("");
        k.setPrezime("Markovic");
        k.setBrojLicneKarte("123456789");
        k.setMesto(new Mesto(1, "Beograd"));
        assertThrows(Exception.class, () -> so.izvrsiOperaciju(k, null));
    }

    @Test
    void testPreduusloviPrezimeNull() {
        Kupac k = new Kupac();
        k.setIme("Marko");
        k.setPrezime(null);
        k.setBrojLicneKarte("123456789");
        k.setMesto(new Mesto(1, "Beograd"));
        Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju(k, null));
        assertTrue(ex.getMessage().contains("prezimena"));
    }

    @Test
    void testPreduusloviPrezimePrazno() {
        Kupac k = new Kupac();
        k.setIme("Marko");
        k.setPrezime("");
        k.setBrojLicneKarte("123456789");
        k.setMesto(new Mesto(1, "Beograd"));
        assertThrows(Exception.class, () -> so.izvrsiOperaciju(k, null));
    }

    @Test
    void testPreduusloviBrojLicneKarteNull() {
        Kupac k = new Kupac();
        k.setIme("Marko");
        k.setPrezime("Markovic");
        k.setBrojLicneKarte(null);
        k.setMesto(new Mesto(1, "Beograd"));
        Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju(k, null));
        assertTrue(ex.getMessage().contains("lične karte"));
    }

    @Test
    void testPreduusloviBrojLicneKartePrazno() {
        Kupac k = new Kupac();
        k.setIme("Marko");
        k.setPrezime("Markovic");
        k.setBrojLicneKarte("");
        k.setMesto(new Mesto(1, "Beograd"));
        assertThrows(Exception.class, () -> so.izvrsiOperaciju(k, null));
    }

    @Test
    void testPreduusloviBrojLicneKartePogresan() {
        Kupac k = new Kupac();
        k.setIme("Marko");
        k.setPrezime("Markovic");
        k.setBrojLicneKarte("123");
        k.setMesto(new Mesto(1, "Beograd"));
        Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju(k, null));
        assertTrue(ex.getMessage().contains("9 cifara"));
    }

    private KreirajKupacSO napraviSOSaMockom(
            DBRepozitorijumGenericki mockBroker,
            Connection mockConnection,
            MockedStatic<DBKonekcija> mockedStatic) throws Exception {
        DBKonekcija mockDBKonekcija = mock(DBKonekcija.class);
        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDBKonekcija);
        when(mockDBKonekcija.getConnection()).thenReturn(mockConnection);

        KreirajKupacSO soSaMockom = new KreirajKupacSO();
        Field brokerField = ApstraktnaGenerickaOperacija.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    @Test
    void testIzvrsiKupacNePostojiUspesnoKreiran() throws Exception {
        Kupac k = new Kupac();
        k.setIme("Marko");
        k.setPrezime("Markovic");
        k.setBrojLicneKarte("123456789");
        k.setMesto(new Mesto(1, "Beograd"));

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false); // Kupac ne postoji u bazi

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            KreirajKupacSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(k, null);

            assertTrue(soSaMockom.getUspesno(), "Kreiranje kupca mora biti uspešno kada kupac ne postoji");
            verify(mockBroker, times(1)).add(k);
        }
    }

    @Test
    void testIzvrsiKupacVecPostojiNeUspesno() throws Exception {
        Kupac k = new Kupac();
        k.setIme("Marko");
        k.setPrezime("Markovic");
        k.setBrojLicneKarte("123456789");
        k.setMesto(new Mesto(1, "Beograd"));

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); 

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            KreirajKupacSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(k, null);

            assertFalse(soSaMockom.getUspesno(), "Kreiranje kupca ne treba da bude uspešno kada kupac već postoji");
            verify(mockBroker, never()).add(k);
        }
    }
}