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

class TerminTest {

    Termin t;
    Validator validator;

    @Mock
    ResultSet rs;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() throws Exception {
        t = new Termin();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        t = null;
        closeable.close();
    }

    @Test
    void testSetIdTerminDezurstva() {
        t.setIdTerminDezurstva(1);
        assertEquals(1, t.getIdTerminDezurstva());
    }

    @Test
    void testSetNaziv() {
        t.setNaziv("Jutarnja");
        assertEquals("Jutarnja", t.getNaziv());
    }

    @Test
    void testToString() {
        t.setNaziv("Jutarnja");
        assertEquals("Jutarnja", t.toString());
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("termin", t.vratiNazivTabele());
    }

    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("naziv", t.vratiKoloneZaUbacivanje());
    }

    @Test
    void testVratiVrednostiZaUbacivanje() {
        t.setNaziv("Jutarnja");
        assertEquals("'Jutarnja'", t.vratiVrednostiZaUbacivanje());
    }

    @Test
    void testVratiPrimarniKljuc() {
        t.setIdTerminDezurstva(1);
        assertEquals("termin.idTerminDezurstva=1", t.vratiPrimarniKljuc());
    }

    @Test
    void testVratiVrednostiZaIzmenu() {
        t.setNaziv("Jutarnja");
        assertEquals("naziv='Jutarnja'", t.vratiVrednostiZaIzmenu());
    }

    @Test
    void testVratiListu() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("termin.idTerminDezurstva")).thenReturn(1);
        when(rs.getString("termin.naziv")).thenReturn("Jutarnja");

        List<ApstraktniDomenskiObjekat> lista = t.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(1, lista.size());

        Termin result = (Termin) lista.get(0);
        assertEquals(1, result.getIdTerminDezurstva());
        assertEquals("Jutarnja", result.getNaziv());
    }

    @Test
    void testVratiListuPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        List<ApstraktniDomenskiObjekat> lista = t.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(0, lista.size());
    }

    @Test
    void testVratiObjekatIzRS() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("termin.idTerminDezurstva")).thenReturn(1);
        when(rs.getString("termin.naziv")).thenReturn("Jutarnja");

        Termin result = (Termin) t.vratiObjekatIzRS(rs);

        assertNotNull(result);
        assertEquals(1, result.getIdTerminDezurstva());
        assertEquals("Jutarnja", result.getNaziv());
    }

    @Test
    void testVratiObjekatIzRSPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        ApstraktniDomenskiObjekat result = t.vratiObjekatIzRS(rs);

        assertNull(result);
    }
    
    @Test
    void testValidacijaProlaziKadaSuSveVrednostiValidne() {
        t.setNaziv("Jutarnja");

        Set<ConstraintViolation<Termin>> violations = validator.validate(t);

        assertTrue(violations.isEmpty(), "Ne treba biti violations kada su sve vrednosti validne");
    }

    @Test
    void testValidacijaPrazanNaziv() {
        t.setNaziv("");

        Set<ConstraintViolation<Termin>> violations = validator.validate(t);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Naziv termina ne moze biti prazan")));
    }

    @Test
    void testValidacijaNullNaziv() {
        t.setNaziv(null);

        Set<ConstraintViolation<Termin>> violations = validator.validate(t);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Naziv termina ne moze biti prazan")));
    }

    @Test
    void testValidacijaNazivDuziOd30Karaktera() {
        t.setNaziv("OvoJeNazivTerminaKojiImaViseOdTridesetKaraktera");

        Set<ConstraintViolation<Termin>> violations = validator.validate(t);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Naziv termina ne moze imati vise od 30 karaktera")));
    }

}