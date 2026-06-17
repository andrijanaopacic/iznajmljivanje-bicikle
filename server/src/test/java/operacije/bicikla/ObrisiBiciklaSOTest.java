package operacije.bicikla;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import model.BiciklaZaOdrasle;
import repozitorijum.db.DBKonekcija;

@DisplayName("Testovi za SO ObrisiBiciklaSO")
class ObrisiBiciklaSOTest {

    ObrisiBiciklaSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new ObrisiBiciklaSO();
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

    
    private ObrisiBiciklaSO napraviSOSaMockom(
            Connection mockConnection,
            MockedStatic<DBKonekcija> mockedStatic) throws Exception {
        DBKonekcija mockDBKonekcija = mock(DBKonekcija.class);
        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDBKonekcija);
        when(mockDBKonekcija.getConnection()).thenReturn(mockConnection);

        return new ObrisiBiciklaSO();
    }

    @Test
    void testIzvrsiBiciklaNijeUUpotrebiUspesnoObrisana() throws Exception {
        BiciklaZaOdrasle bicikla = new BiciklaZaOdrasle();
        bicikla.setIdBicikla(1);

        Connection mockConnection = mock(Connection.class);
        Statement mockStatementPreduslovi = mock(Statement.class);
        ResultSet mockRsPreduslovi = mock(ResultSet.class);
        when(mockRsPreduslovi.next()).thenReturn(false); 

        Statement mockStatementIzvrsi = mock(Statement.class);

        when(mockConnection.createStatement())
                .thenReturn(mockStatementPreduslovi)
                .thenReturn(mockStatementIzvrsi);
        when(mockStatementPreduslovi.executeQuery(anyString())).thenReturn(mockRsPreduslovi);

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            ObrisiBiciklaSO soSaMockom = napraviSOSaMockom(mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(bicikla, null);

            assertTrue(soSaMockom.getUspesno(), "Brisanje bicikle mora biti uspešno kada nije u upotrebi");
            verify(mockStatementIzvrsi, times(1)).executeUpdate(contains("biciklazaodrasle"));
            verify(mockStatementIzvrsi, times(1))
                    .executeUpdate("DELETE FROM bicikla WHERE idBicikla = 1");
        }
    }

    @Test
    void testIzvrsiBiciklaJeUUpotrebiNeUspesno() throws Exception {
        BiciklaZaOdrasle bicikla = new BiciklaZaOdrasle();
        bicikla.setIdBicikla(1);

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockRs = mock(ResultSet.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(true, false); 

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            ObrisiBiciklaSO soSaMockom = napraviSOSaMockom(mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(bicikla, null);

            assertFalse(soSaMockom.getUspesno(), "Brisanje ne treba da bude uspešno kada je bicikla u upotrebi");
            verify(mockStatement, never()).executeUpdate(anyString());
        }
    }
}