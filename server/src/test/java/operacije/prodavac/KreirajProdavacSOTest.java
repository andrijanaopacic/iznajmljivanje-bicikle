package operacije.prodavac;

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

import model.Prodavac;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

@DisplayName("Testovi za SO KreirajProdavacSO")
class KreirajProdavacSOTest {

    KreirajProdavacSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new KreirajProdavacSO();
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

    private KreirajProdavacSO napraviSOSaMockom(
            DBRepozitorijumGenericki mockBroker,
            Connection mockConnection,
            MockedStatic<DBKonekcija> mockedStatic) throws Exception {
        DBKonekcija mockDBKonekcija = mock(DBKonekcija.class);
        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDBKonekcija);
        when(mockDBKonekcija.getConnection()).thenReturn(mockConnection);

        KreirajProdavacSO soSaMockom = new KreirajProdavacSO();
        Field brokerField = ApstraktnaGenerickaOperacija.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    @Test
    void testIzvrsiProdavacNePostojiUspesnoKreiran() throws Exception {
        Prodavac prodavac = new Prodavac(1, "Ana", "Anic", "aanic", "sifra123");

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        doNothing().when(mockBroker).add(any());

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockRs = mock(ResultSet.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(false);

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            KreirajProdavacSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(prodavac, null);

            assertTrue(soSaMockom.getUspesno(), "Kreiranje prodavca mora biti uspešno kada prodavac ne postoji");
            verify(mockBroker, times(1)).add(prodavac);
        }
    }

    @Test
    void testIzvrsiProdavacVecPostojiNeUspesno() throws Exception {
        Prodavac prodavac = new Prodavac(1, "Ana", "Anic", "aanic", "sifra123");

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockRs = mock(ResultSet.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(true, false);

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            KreirajProdavacSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(prodavac, null);

            assertFalse(soSaMockom.getUspesno(), "Kreiranje ne treba da bude uspešno kada prodavac već postoji");
            verify(mockBroker, never()).add(any());
        }
    }
    
    @Test
    void testIzvrsiProdavacNevalidnoImeOdbijeno() throws Exception {
        Prodavac prodavac = new Prodavac(1, "", "Anic", "aanic", "sifra123");

        Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju(prodavac, null));

        assertTrue(ex.getMessage().contains("Ime ne moze biti prazno"));
    }
}