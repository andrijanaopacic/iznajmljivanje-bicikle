package operacije.termin;

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

import model.Termin;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

@DisplayName("Testovi za SO PromeniTerminSO")
class PromeniTerminSOTest {

    PromeniTerminSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new PromeniTerminSO();
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

    private PromeniTerminSO napraviSOSaMockom(
            DBRepozitorijumGenericki mockBroker,
            Connection mockConnection,
            MockedStatic<DBKonekcija> mockedStatic) throws Exception {
        DBKonekcija mockDBKonekcija = mock(DBKonekcija.class);
        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDBKonekcija);
        when(mockDBKonekcija.getConnection()).thenReturn(mockConnection);

        PromeniTerminSO soSaMockom = new PromeniTerminSO();
        Field brokerField = ApstraktnaGenerickaOperacija.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    @Test
    void testIzvrsiTerminNePostojiUspesnoIzmenjen() throws Exception {
        Termin termin = new Termin(1, "Jutarnja");

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        doNothing().when(mockBroker).edit(any());

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockRs = mock(ResultSet.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(false);

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            PromeniTerminSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(termin, null);

            assertTrue(soSaMockom.getUspesno(), "Izmena termina mora biti uspešna kada ne postoji duplikat");
            verify(mockBroker, times(1)).edit(termin);
        }
    }

    @Test
    void testIzvrsiIstiNazivIstiTerminUspesnoIzmenjen() throws Exception {
        Termin termin = new Termin(1, "Jutarnja");

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        doNothing().when(mockBroker).edit(any());

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockRs = mock(ResultSet.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(false);

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            PromeniTerminSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(termin, null);

            assertTrue(soSaMockom.getUspesno(), "Izmena mora biti uspešna i kada naziv ostaje isti, jer se sopstveni ID izuzima iz provere");
            verify(mockBroker, times(1)).edit(termin);
        }
    }

    @Test
    void testIzvrsiDrugiTerminSaIstimNazivomNeUspesno() throws Exception {
        Termin termin = new Termin(2, "Jutarnja");

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockRs = mock(ResultSet.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(true, false);

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            PromeniTerminSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(termin, null);

            assertFalse(soSaMockom.getUspesno(), "Izmena ne treba da bude uspešna kada drugi termin sa istim nazivom već postoji");
            verify(mockBroker, never()).edit(any());
        }
    }
}