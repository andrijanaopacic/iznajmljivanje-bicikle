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

@DisplayName("Testovi za SO KreirajBiciklaSO")
class KreirajBiciklaSOTest {

    KreirajBiciklaSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new KreirajBiciklaSO();
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

    
    private KreirajBiciklaSO napraviSOSaMockom(
            Connection mockConnection,
            MockedStatic<DBKonekcija> mockedStatic) throws Exception {
        DBKonekcija mockDBKonekcija = mock(DBKonekcija.class);
        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDBKonekcija);
        when(mockDBKonekcija.getConnection()).thenReturn(mockConnection);

        return new KreirajBiciklaSO();
    }

    @Test
    void testIzvrsiBiciklaNePostojiUspesnoKreirana() throws Exception {
        BiciklaZaOdrasle bicikla = new BiciklaZaOdrasle();
        bicikla.setMarka("Trek");
        bicikla.setModel("Marlin");
        bicikla.setBoja("Crvena");
        bicikla.setCenaPoSatu(500.0);
        bicikla.setCenaPoDanu(2000.0);
        bicikla.setVelicinaTockova(26);
        bicikla.setBrojBrzina(21);

        Connection mockConnection = mock(Connection.class);

        Statement mockStatementPreduslovi = mock(Statement.class);
        ResultSet mockRsPreduslovi = mock(ResultSet.class);
        when(mockRsPreduslovi.next()).thenReturn(false); 

        Statement mockStatementIzvrsi = mock(Statement.class);
        ResultSet mockGeneratedKeys = mock(ResultSet.class);
        when(mockGeneratedKeys.next()).thenReturn(true);
        when(mockGeneratedKeys.getInt(1)).thenReturn(5);
        when(mockStatementIzvrsi.getGeneratedKeys()).thenReturn(mockGeneratedKeys);

        when(mockConnection.createStatement())
                .thenReturn(mockStatementPreduslovi)
                .thenReturn(mockStatementIzvrsi);
        when(mockStatementPreduslovi.executeQuery(anyString())).thenReturn(mockRsPreduslovi);

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            KreirajBiciklaSO soSaMockom = napraviSOSaMockom(mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(bicikla, null);

            assertTrue(soSaMockom.getUspesno(), "Kreiranje bicikle mora biti uspešno kada bicikla ne postoji");
            assertEquals(5, bicikla.getIdBicikla());
            verify(mockStatementIzvrsi, times(1)).executeUpdate(anyString(), eq(Statement.RETURN_GENERATED_KEYS));
            verify(mockStatementIzvrsi, times(1)).executeUpdate(contains("biciklazaodrasle"));
        }
    }

    @Test
    void testIzvrsiBiciklaVecPostojiNeUspesno() throws Exception {
        BiciklaZaOdrasle bicikla = new BiciklaZaOdrasle();
        bicikla.setMarka("Trek");
        bicikla.setModel("Marlin");
        bicikla.setBoja("Crvena");
        bicikla.setCenaPoSatu(500.0);
        bicikla.setCenaPoDanu(2000.0);
        bicikla.setVelicinaTockova(26);
        bicikla.setBrojBrzina(21);

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockRs = mock(ResultSet.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(true, false);

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            KreirajBiciklaSO soSaMockom = napraviSOSaMockom(mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(bicikla, null);

            assertFalse(soSaMockom.getUspesno(), "Kreiranje ne treba da bude uspešno kada bicikla već postoji");
            verify(mockStatement, never()).executeUpdate(anyString(), eq(Statement.RETURN_GENERATED_KEYS));
        }
    }
    
    @Test
    void testIzvrsiBiciklaNevalidniPodaciOdbijena() throws Exception {
        BiciklaZaOdrasle bicikla = new BiciklaZaOdrasle();
        bicikla.setMarka("");
        bicikla.setModel("Marlin");
        bicikla.setBoja("Crvena");
        bicikla.setCenaPoSatu(500.0);
        bicikla.setCenaPoDanu(2000.0);
        bicikla.setVelicinaTockova(26);
        bicikla.setBrojBrzina(21);

        Exception ex = assertThrows(Exception.class, () -> so.izvrsiOperaciju(bicikla, null));

        assertTrue(ex.getMessage().contains("Marka ne moze biti prazna"));
    }
    
    
}