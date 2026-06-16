package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

abstract class BiciklaTest {

    Bicikla b;

    @Mock
    ResultSet rs;

    AutoCloseable closeable;

    public abstract Bicikla getInstance();

    @BeforeEach
    void setUp() throws Exception {
        b = getInstance();
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        b = null;
        closeable.close();
    }

    @Test
    void testSetCenaPoSatu() {
        b.setCenaPoSatu(500.0);
        assertEquals(500.0, b.getCenaPoSatu());
    }

    @Test
    void testSetCenaPoDanu() {
        b.setCenaPoDanu(2000.0);
        assertEquals(2000.0, b.getCenaPoDanu());
    }

    @Test
    void testSetMarka() {
        b.setMarka("Trek");
        assertEquals("Trek", b.getMarka());
    }

    @Test
    void testSetModel() {
        b.setModel("Marlin");
        assertEquals("Marlin", b.getModel());
    }

    @Test
    void testSetBoja() {
        b.setBoja("Crvena");
        assertEquals("Crvena", b.getBoja());
    }

    @Test
    void testSetIdBicikla() {
        b.setIdBicikla(1);
        assertEquals(1, b.getIdBicikla());
    }

    @Test
    void testEqualsIstiObjekat() {
        assertTrue(b.equals(b));
    }

    @Test
    void testEqualsNullObjekat() {
        assertFalse(b.equals(null));
    }

    @Test
    void testEqualsDrugaKlasa() {
        assertFalse(b.equals(new String()));
    }

    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("cenaPoSatu,cenaPoDanu,marka,model,boja", b.vratiKoloneZaUbacivanje());
    }

    @Test
    void testVratiVrednostiZaUbacivanje() {
        b.setCenaPoSatu(500.0);
        b.setCenaPoDanu(2000.0);
        b.setMarka("Trek");
        b.setModel("Marlin");
        b.setBoja("Crvena");
        assertEquals("500.0,2000.0,'Trek','Marlin','Crvena'",  b.vratiVrednostiZaUbacivanje());
    }

    @Test
    void testVratiPrimarniKljuc() {
        b.setIdBicikla(5);
        assertEquals("bicikla.idBicikla=5", b.vratiPrimarniKljuc());
    }

    @Test
    void testVratiVrednostiZaIzmenu() {
        b.setCenaPoSatu(500.0);
        b.setCenaPoDanu(2000.0);
        b.setMarka("Trek");
        b.setModel("Marlin");
        b.setBoja("Crvena");
        assertEquals("cenaPoSatu=500.0, cenaPoDanu=2000.0, marka='Trek', model='Marlin', boja='Crvena'", b.vratiVrednostiZaIzmenu());
    }
}