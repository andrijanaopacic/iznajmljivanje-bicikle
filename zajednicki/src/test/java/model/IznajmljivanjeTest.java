package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class IznajmljivanjeTest {

    Iznajmljivanje i;
    Kupac kupac;
    Prodavac prodavac;

    @Mock
    ResultSet rs;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() throws Exception {
        kupac = new Kupac(1, "Marko", "Markovic", "123456", new Mesto(1, "Beograd"));
        prodavac = new Prodavac(1, "Ana", "Anic", "aanic", "sifra");
        i = new Iznajmljivanje();
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        i = null;
        kupac = null;
        prodavac = null;
        closeable.close();
    }

    @Test
    void testSetIdIznajmljivanje() {
        i.setIdIznajmljivanje(1);
        assertEquals(1, i.getIdIznajmljivanje());
    }

    @Test
    void testSetUkupanIznos() {
        i.setUkupanIznos(5000.0);
        assertEquals(5000.0, i.getUkupanIznos());
    }

    @Test
    void testSetKupac() {
        i.setKupac(kupac);
        assertEquals(kupac, i.getKupac());
    }

    @Test
    void testSetProdavac() {
        i.setProdavac(prodavac);
        assertEquals(prodavac, i.getProdavac());
    }

    @Test
    void testSetListaStavkiIznajmljivanja() {
        List<StavkaIznajmljivanja> stavke = new ArrayList<>();
        i.setListaStavkiIznajmljivanja(stavke);
        assertEquals(stavke, i.getListaStavkiIznajmljivanja());
    }

    @Test
    void testToString() {
        i.setIdIznajmljivanje(1);
        i.setUkupanIznos(5000.0);
        i.setKupac(kupac);
        i.setProdavac(prodavac);
        i.setListaStavkiIznajmljivanja(new ArrayList<>());

        String str = i.toString();

        assertTrue(str.contains("1"));
        assertTrue(str.contains("5000.0"));
    }

    @Test
    void testEqualsIstiObjekat() {
        assertTrue(i.equals(i));
    }

    @Test
    void testEqualsNullObjekat() {
        assertFalse(i.equals(null));
    }

    @Test
    void testEqualsDrugaKlasa() {
        assertFalse(i.equals(new String()));
    }

    @ParameterizedTest
    @CsvSource({
        "1, 1, true",
        "1, 2, false",
        "2, 1, false"
    })
    void testEquals(int id1, int id2, boolean jednako) {
        Iznajmljivanje i1 = new Iznajmljivanje();
        i1.setIdIznajmljivanje(id1);
        Iznajmljivanje i2 = new Iznajmljivanje();
        i2.setIdIznajmljivanje(id2);
        assertEquals(jednako, i1.equals(i2));
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("iznajmljivanje", i.vratiNazivTabele());
    }

    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("ukupanIznos,idProdavac,idKupac", i.vratiKoloneZaUbacivanje());
    }

    @Test
    void testVratiVrednostiZaUbacivanje() {
        i.setUkupanIznos(5000.0);
        i.setKupac(kupac);
        i.setProdavac(prodavac);
        assertEquals("5000.0,1,1", i.vratiVrednostiZaUbacivanje());
    }

    @Test
    void testVratiPrimarniKljuc() {
        i.setIdIznajmljivanje(1);
        assertEquals("iznajmljivanje.idIznajmljivanje=1", i.vratiPrimarniKljuc());
    }

    @Test
    void testVratiVrednostiZaIzmenu() {
        i.setUkupanIznos(5000.0);
        i.setKupac(kupac);
        i.setProdavac(prodavac);
        assertEquals("ukupanIznos=5000.0,idProdavac=1,idKupac=1", i.vratiVrednostiZaIzmenu());
    }

    @Test
    void testVratiListu() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("iznajmljivanje.idIznajmljivanje")).thenReturn(1);
        when(rs.getDouble("iznajmljivanje.ukupanIznos")).thenReturn(5000.0);
        when(rs.getInt("kupac.idKupac")).thenReturn(1);
        when(rs.getString("kupac.ime")).thenReturn("Marko");
        when(rs.getString("kupac.prezime")).thenReturn("Markovic");
        when(rs.getString("kupac.brojLicneKarte")).thenReturn("123456");
        when(rs.getInt("mesto.idMesto")).thenReturn(1);
        when(rs.getString("mesto.naziv")).thenReturn("Beograd");
        when(rs.getInt("prodavac.idProdavac")).thenReturn(1);
        when(rs.getString("prodavac.ime")).thenReturn("Ana");
        when(rs.getString("prodavac.prezime")).thenReturn("Anic");
        when(rs.getString("prodavac.korisnickoIme")).thenReturn("aanic");
        when(rs.getString("prodavac.sifra")).thenReturn("sifra");

        List<ApstraktniDomenskiObjekat> lista = i.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(1, lista.size());

        Iznajmljivanje result = (Iznajmljivanje) lista.get(0);
        assertEquals(1, result.getIdIznajmljivanje());
        assertEquals(5000.0, result.getUkupanIznos());
        assertEquals("Marko", result.getKupac().getIme());
        assertEquals("Ana", result.getProdavac().getIme());
    }

    @Test
    void testVratiListuPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        List<ApstraktniDomenskiObjekat> lista = i.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(0, lista.size());
    }

    @Test
    void testVratiObjekatIzRS() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("iznajmljivanje.idIznajmljivanje")).thenReturn(1);
        when(rs.getDouble("iznajmljivanje.ukupanIznos")).thenReturn(5000.0);
        when(rs.getInt("iznajmljivanje.idKupac")).thenReturn(1);
        when(rs.getString("kupac.ime")).thenReturn("Marko");
        when(rs.getString("kupac.prezime")).thenReturn("Markovic");
        when(rs.getString("kupac.brojLicneKarte")).thenReturn("123456");
        when(rs.getInt("kupac.idMesto")).thenReturn(1);
        when(rs.getString("mesto.naziv")).thenReturn("Beograd");
        when(rs.getInt("iznajmljivanje.idProdavac")).thenReturn(1);
        when(rs.getString("prodavac.ime")).thenReturn("Ana");
        when(rs.getString("prodavac.prezime")).thenReturn("Anic");
        when(rs.getString("prodavac.korisnickoIme")).thenReturn("aanic");
        when(rs.getString("prodavac.sifra")).thenReturn("sifra");

        Iznajmljivanje result = (Iznajmljivanje) i.vratiObjekatIzRS(rs);

        assertNotNull(result);
        assertEquals(1, result.getIdIznajmljivanje());
        assertEquals(5000.0, result.getUkupanIznos());
        assertEquals("Marko", result.getKupac().getIme());
        assertEquals("Ana", result.getProdavac().getIme());
    }

    @Test
    void testVratiObjekatIzRSPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        ApstraktniDomenskiObjekat result = i.vratiObjekatIzRS(rs);

        assertNull(result);
    }
}