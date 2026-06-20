package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

class KupacTest {

    Kupac k;
    Validator validator;

    @Mock
    ResultSet rs;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() throws Exception {
        k = new Kupac();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        k = null;
        closeable.close();
    }

    @Test
    void testSetIdKupac() {
        k.setIdKupac(1);
        assertEquals(1, k.getIdKupac());
    }

    @Test
    void testSetIme() {
        k.setIme("Marko");
        assertEquals("Marko", k.getIme());
    }

    @Test
    void testSetPrezime() {
        k.setPrezime("Markovic");
        assertEquals("Markovic", k.getPrezime());
    }

    @Test
    void testSetBrojLicneKarte() {
        k.setBrojLicneKarte("123456789");
        assertEquals("123456789", k.getBrojLicneKarte());
    }

    @Test
    void testSetMesto() {
        Mesto m = new Mesto(1, "Beograd");
        k.setMesto(m);
        assertEquals(m, k.getMesto());
    }

    @Test
    void testToString() {
        k.setIme("Marko");
        k.setPrezime("Markovic");
        assertEquals("Marko Markovic", k.toString());
    }

    @Test
    void testEqualsIstiObjekat() {
        assertTrue(k.equals(k));
    }

    @Test
    void testEqualsNullObjekat() {
        assertFalse(k.equals(null));
    }

    @Test
    void testEqualsDrugaKlasa() {
        assertFalse(k.equals(new String()));
    }

    @ParameterizedTest
    @CsvSource({
        "1, 1, true",
        "1, 2, false",
        "2, 1, false"
    })
    void testEquals(int id1, int id2, boolean jednako) {
        Kupac k1 = new Kupac();
        k1.setIdKupac(id1);
        Kupac k2 = new Kupac();
        k2.setIdKupac(id2);
        assertEquals(jednako, k1.equals(k2));
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("kupac", k.vratiNazivTabele());
    }

    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("ime,prezime,brojLicneKarte,idMesto", k.vratiKoloneZaUbacivanje());
    }

    @Test
    void testVratiVrednostiZaUbacivanje() {
        k.setIme("Marko");
        k.setPrezime("Markovic");
        k.setBrojLicneKarte("123456789");
        k.setMesto(new Mesto(1, "Beograd"));
        assertEquals("'Marko','Markovic','123456789',1", k.vratiVrednostiZaUbacivanje());
    }

    @Test
    void testVratiPrimarniKljuc() {
        k.setIdKupac(1);
        assertEquals("kupac.idKupac=1", k.vratiPrimarniKljuc());
    }

    @Test
    void testVratiVrednostiZaIzmenu() {
        k.setIme("Marko");
        k.setPrezime("Markovic");
        k.setBrojLicneKarte("123456789");
        k.setMesto(new Mesto(1, "Beograd"));
        assertEquals("ime = 'Marko',prezime = 'Markovic',brojLicneKarte = '123456789',idMesto = 1", k.vratiVrednostiZaIzmenu());
    }

    @Test
    void testVratiListu() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("kupac.idKupac")).thenReturn(1);
        when(rs.getString("kupac.ime")).thenReturn("Marko");
        when(rs.getString("kupac.prezime")).thenReturn("Markovic");
        when(rs.getString("kupac.brojLicneKarte")).thenReturn("123456789");
        when(rs.getInt("kupac.idMesto")).thenReturn(1);
        when(rs.getString("mesto.naziv")).thenReturn("Beograd");

        List<ApstraktniDomenskiObjekat> lista = k.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(1, lista.size());

        Kupac result = (Kupac) lista.get(0);
        assertEquals(1, result.getIdKupac());
        assertEquals("Marko", result.getIme());
        assertEquals("Markovic", result.getPrezime());
        assertEquals("123456789", result.getBrojLicneKarte());
        assertEquals("Beograd", result.getMesto().getNaziv());
    }

    @Test
    void testVratiListuPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        List<ApstraktniDomenskiObjekat> lista = k.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(0, lista.size());
    }

    @Test
    void testVratiObjekatIzRS() throws Exception {
        when(rs.next()).thenReturn(true);
        when(rs.getInt("kupac.idKupac")).thenReturn(1);
        when(rs.getString("kupac.ime")).thenReturn("Marko");
        when(rs.getString("kupac.prezime")).thenReturn("Markovic");
        when(rs.getString("kupac.brojLicneKarte")).thenReturn("123456789");
        when(rs.getInt("kupac.idMesto")).thenReturn(1);
        when(rs.getString("mesto.naziv")).thenReturn("Beograd");

        Kupac result = (Kupac) k.vratiObjekatIzRS(rs);

        assertNotNull(result);
        assertEquals(1, result.getIdKupac());
        assertEquals("Marko", result.getIme());
        assertEquals("Markovic", result.getPrezime());
        assertEquals("123456789", result.getBrojLicneKarte());
        assertEquals("Beograd", result.getMesto().getNaziv());
    }

    @Test
    void testVratiObjekatIzRSPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        ApstraktniDomenskiObjekat result = k.vratiObjekatIzRS(rs);

        assertNull(result);
    }

    @Test
    void testPodrazumevaniKonstruktor() {
        assertNotNull(k);
    }

    @Test
    void testKonstruktorSaId() {
        Mesto mesto = new Mesto(1, "Beograd");
        Kupac noviKupac = new Kupac(1, "Marko", "Markovic", "123456789", mesto);
        assertEquals(1, noviKupac.getIdKupac());
        assertEquals("Marko", noviKupac.getIme());
        assertEquals("Markovic", noviKupac.getPrezime());
        assertEquals("123456789", noviKupac.getBrojLicneKarte());
        assertEquals(mesto, noviKupac.getMesto());
    }

    @Test
    void testKonstruktorBezId() {
        Mesto mesto = new Mesto(1, "Beograd");
        Kupac noviKupac = new Kupac("Marko", "Markovic", "123456789", mesto);
        assertEquals("Marko", noviKupac.getIme());
        assertEquals("Markovic", noviKupac.getPrezime());
        assertEquals("123456789", noviKupac.getBrojLicneKarte());
        assertEquals(mesto, noviKupac.getMesto());
    }

    @Test
    void testValidacijaProlaziKadaSuSveVrednostiValidne() {
        k.setIme("Marko");
        k.setPrezime("Markovic");
        k.setBrojLicneKarte("123456789");
        k.setMesto(new Mesto(1, "Beograd"));

        Set<ConstraintViolation<Kupac>> violations = validator.validate(k);

        assertTrue(violations.isEmpty(), "Ne treba biti violations kada su sve vrednosti validne");
    }

    @Test
    void testValidacijaPraznoIme() {
        k.setIme("");
        k.setPrezime("Markovic");
        k.setBrojLicneKarte("123456789");
        k.setMesto(new Mesto(1, "Beograd"));

        Set<ConstraintViolation<Kupac>> violations = validator.validate(k);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Ime ne moze biti prazno")));
    }

    @Test
    void testValidacijaPraznoPrezime() {
        k.setIme("Marko");
        k.setPrezime("");
        k.setBrojLicneKarte("123456789");
        k.setMesto(new Mesto(1, "Beograd"));

        Set<ConstraintViolation<Kupac>> violations = validator.validate(k);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Prezime ne moze biti prazno")));
    }

    @Test
    void testValidacijaBrojLicneKarteNull() {
        k.setIme("Marko");
        k.setPrezime("Markovic");
        k.setBrojLicneKarte(null);
        k.setMesto(new Mesto(1, "Beograd"));

        Set<ConstraintViolation<Kupac>> violations = validator.validate(k);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Broj licne karte ne moze biti null")));
    }

    @Test
    void testValidacijaBrojLicneKarteNeispravanFormat() {
        k.setIme("Marko");
        k.setPrezime("Markovic");
        k.setBrojLicneKarte("123456");
        k.setMesto(new Mesto(1, "Beograd"));

        Set<ConstraintViolation<Kupac>> violations = validator.validate(k);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Broj licne karte mora sadrzati 9 cifara")));
    }

    @Test
    void testValidacijaMestoNull() {
        k.setIme("Marko");
        k.setPrezime("Markovic");
        k.setBrojLicneKarte("123456789");
        k.setMesto(null);

        Set<ConstraintViolation<Kupac>> violations = validator.validate(k);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Mesto ne moze biti null")));
    }
}