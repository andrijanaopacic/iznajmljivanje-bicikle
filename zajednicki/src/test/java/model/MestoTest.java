package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class MestoTest {

    Mesto m;

    @Mock
    ResultSet rs;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() throws Exception {
        m = new Mesto();
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        m = null;
        closeable.close();
    }
    
    @Test
    void testPodrazumevaniKonstruktor() {
        assertNotNull(m);
    }

    @Test
    void testKonstruktorSaId() {
        Mesto noviMesto = new Mesto(1, "Beograd");
        assertEquals(1, noviMesto.getIdMesto());
        assertEquals("Beograd", noviMesto.getNaziv());
    }

    @Test
    void testKonstruktorBezId() {
        Mesto noviMesto = new Mesto("Beograd");
        assertEquals("Beograd", noviMesto.getNaziv());
    }

    @Test
    void testSetIdMesto() {
        m.setIdMesto(1);
        assertEquals(1, m.getIdMesto());
    }

    @Test
    void testSetNaziv() {
        m.setNaziv("Beograd");
        assertEquals("Beograd", m.getNaziv());
    }

    @Test
    void testToString() {
        m.setNaziv("Beograd");
        assertEquals("Beograd", m.toString());
    }

    @Test
    void testEqualsIstiObjekat() {
        assertTrue(m.equals(m));
    }

    @Test
    void testEqualsNullObjekat() {
        assertFalse(m.equals(null));
    }

    @Test
    void testEqualsDrugaKlasa() {
        assertFalse(m.equals(new String()));
    }

    @ParameterizedTest
    @CsvSource({
        "1, 1, true",
        "1, 2, false",
        "2, 1, false"
    })
    void testEquals(int id1, int id2, boolean jednako) {
        Mesto m1 = new Mesto();
        m1.setIdMesto(id1);
        Mesto m2 = new Mesto();
        m2.setIdMesto(id2);
        assertEquals(jednako, m1.equals(m2));
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("mesto", m.vratiNazivTabele());
    }

    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("naziv", m.vratiKoloneZaUbacivanje());
    }

    @Test
    void testVratiVrednostiZaUbacivanje() {
        m.setNaziv("Beograd");
        assertEquals("'Beograd'", m.vratiVrednostiZaUbacivanje());
    }

    @Test
    void testVratiPrimarniKljuc() {
        m.setIdMesto(1);
        assertEquals("mesto.idMesto=1", m.vratiPrimarniKljuc());
    }

    @Test
    void testVratiVrednostiZaIzmenu() {
        m.setNaziv("Beograd");
        assertEquals("naziv='Beograd'", m.vratiVrednostiZaIzmenu());
    }

    @Test
    void testVratiListu() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("mesto.idMesto")).thenReturn(1);
        when(rs.getString("mesto.naziv")).thenReturn("Beograd");

        List<ApstraktniDomenskiObjekat> lista = m.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(1, lista.size());

        Mesto result = (Mesto) lista.get(0);
        assertEquals(1, result.getIdMesto());
        assertEquals("Beograd", result.getNaziv());
    }

    @Test
    void testVratiListuPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        List<ApstraktniDomenskiObjekat> lista = m.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(0, lista.size());
    }

    @Test
    void testVratiObjekatIzRS() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("mesto.idMesto")).thenReturn(1);
        when(rs.getString("mesto.naziv")).thenReturn("Beograd");

        Mesto result = (Mesto) m.vratiObjekatIzRS(rs);

        assertNotNull(result);
        assertEquals(1, result.getIdMesto());
        assertEquals("Beograd", result.getNaziv());
    }

    @Test
    void testVratiObjekatIzRSPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        ApstraktniDomenskiObjekat result = m.vratiObjekatIzRS(rs);

        assertNull(result);
    }
}