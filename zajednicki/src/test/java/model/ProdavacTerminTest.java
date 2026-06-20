package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

class ProdavacTerminTest {

    ProdavacTermin pt;
    Validator validator;

    @Mock
    ResultSet rs;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() throws Exception {
        pt = new ProdavacTermin();
        closeable = MockitoAnnotations.openMocks(this);
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @AfterEach
    void tearDown() throws Exception {
        pt = null;
        closeable.close();
    }

    @Test
    void testSetProdavac() {
        Prodavac p = new Prodavac(1, "Ana", "Anic", "aanic", "sifra123");
        pt.setProdavac(p);
        assertEquals(p, pt.getProdavac());
    }

    @Test
    void testSetTermin() {
        Termin t = new Termin(1, "Jutarnja");
        pt.setTermin(t);
        assertEquals(t, pt.getTermin());
    }

    @Test
    void testSetDatumDezurstva() {
        LocalDate datum = LocalDate.of(2025, 10, 10);
        pt.setDatumDezurstva(datum);
        assertEquals(datum, pt.getDatumDezurstva());
    }

    @Test
    void testSetSmena() {
        pt.setSmena("jutarnja");
        assertEquals("jutarnja", pt.getSmena());
    }

    @Test
    void testToString() {
        LocalDate datum = LocalDate.of(2025, 10, 10);
        pt.setDatumDezurstva(datum);
        pt.setSmena("jutarnja");

        String str = pt.toString();
        assertTrue(str.contains("2025-10-10"));
        assertTrue(str.contains("jutarnja"));
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("prodavactermin", pt.vratiNazivTabele());
    }

    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("idProdavac,idTermin,datumDezurstva,smena",  pt.vratiKoloneZaUbacivanje());
    }

    @Test
    void testVratiVrednostiZaUbacivanje() {
        Prodavac p = new Prodavac(1, "Ana", "Anic", "aanic", "sifra123");
        Termin t = new Termin(1, "Jutarnja");
        LocalDate datum = LocalDate.of(2025, 10, 10);

        pt.setProdavac(p);
        pt.setTermin(t);
        pt.setDatumDezurstva(datum);
        pt.setSmena("jutarnja");

        assertEquals("1,1,'2025-10-10','jutarnja'", pt.vratiVrednostiZaUbacivanje());
    }

    @Test
    void testVratiPrimarniKljuc() {
        Prodavac p = new Prodavac(1, "Ana", "Anic", "aanic", "sifra123");
        Termin t = new Termin(1, "Jutarnja");
        LocalDate datum = LocalDate.of(2025, 10, 10);

        pt.setProdavac(p);
        pt.setTermin(t);
        pt.setDatumDezurstva(datum);

        assertEquals("prodavactermin.idProdavac=1 AND prodavactermin.idTermin=1 AND prodavactermin.datumDezurstva=2025-10-10", pt.vratiPrimarniKljuc());
    }

    @Test
    void testVratiVrednostiZaIzmenu() {
        Prodavac p = new Prodavac(1, "Ana", "Anic", "aanic", "sifra123");
        Termin t = new Termin(1, "Jutarnja");
        LocalDate datum = LocalDate.of(2025, 10, 10);

        pt.setProdavac(p);
        pt.setTermin(t);
        pt.setDatumDezurstva(datum);
        pt.setSmena("jutarnja");

        assertEquals("idProdavac = 1,idTerminDezurstva = 1,datumDezurstva = '2025-10-10',smena = 'jutarnja'", pt.vratiVrednostiZaIzmenu());
    }

    @Test
    void testVratiListu() throws Exception {
        LocalDate datum = LocalDate.of(2025, 10, 10);

        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("prodavactermin.idProdavac")).thenReturn(1);
        when(rs.getDate("prodavactermin.datumDezurstva"))
                .thenReturn(Date.valueOf(datum));
        when(rs.getString("prodavactermin.smena")).thenReturn("jutarnja");
        when(rs.getString("prodavac.ime")).thenReturn("Ana");
        when(rs.getString("prodavac.prezime")).thenReturn("Anic");
        when(rs.getString("prodavac.korisnickoIme")).thenReturn("aanic");
        when(rs.getString("prodavac.sifra")).thenReturn("sifra123");
        when(rs.getInt("termin.idTerminDezurstva")).thenReturn(1);
        when(rs.getString("termin.naziv")).thenReturn("Jutarnja");

        List<ApstraktniDomenskiObjekat> lista = pt.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(1, lista.size());

        ProdavacTermin result = (ProdavacTermin) lista.get(0);
        assertEquals(datum, result.getDatumDezurstva());
        assertEquals("jutarnja", result.getSmena());
        assertEquals("Ana", result.getProdavac().getIme());
        assertEquals("Jutarnja", result.getTermin().getNaziv());
    }

    @Test
    void testVratiListuPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        List<ApstraktniDomenskiObjekat> lista = pt.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(0, lista.size());
    }

    @Test
    void testVratiObjekatIzRS() throws Exception {
        LocalDate datum = LocalDate.of(2025, 10, 10);

        when(rs.next()).thenReturn(true, false);
        when(rs.getString("prodavactermin.smena")).thenReturn("jutarnja");
        when(rs.getDate("prodavactermin.datumDezurstva"))
                .thenReturn(Date.valueOf(datum));
        when(rs.getInt("prodavactermin.idTermin")).thenReturn(1);
        when(rs.getInt("prodavactermin.idProdavac")).thenReturn(1);
        when(rs.getString("termin.naziv")).thenReturn("Jutarnja");
        when(rs.getString("prodavac.korisnickoIme")).thenReturn("aanic");
        when(rs.getString("prodavac.sifra")).thenReturn("sifra123");
        when(rs.getString("prodavac.ime")).thenReturn("Ana");
        when(rs.getString("prodavac.prezime")).thenReturn("Anic");

        ProdavacTermin result = (ProdavacTermin) pt.vratiObjekatIzRS(rs);

        assertNotNull(result);
        assertEquals(datum, result.getDatumDezurstva());
        assertEquals("jutarnja", result.getSmena());
        assertEquals("Ana", result.getProdavac().getIme());
        assertEquals("Jutarnja", result.getTermin().getNaziv());
    }

    @Test
    void testVratiObjekatIzRSPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        ApstraktniDomenskiObjekat result = pt.vratiObjekatIzRS(rs);

        assertNull(result);
    }
    
    @Test
    void testValidacijaProlaziKadaSuSveVrednostiValidne() {
        Prodavac p = new Prodavac(1, "Ana", "Anic", "aanic", "sifra123");
        Termin t = new Termin(1, "Jutarnja");
        LocalDate datum = LocalDate.of(2025, 10, 10);

        pt.setProdavac(p);
        pt.setTermin(t);
        pt.setDatumDezurstva(datum);
        pt.setSmena("jutarnja");

        Set<ConstraintViolation<ProdavacTermin>> violations = validator.validate(pt);

        assertTrue(violations.isEmpty(), "Ne treba biti violations kada su sve vrednosti validne");
    }

    @Test
    void testValidacijaProdavacNull() {
        Termin t = new Termin(1, "Jutarnja");
        LocalDate datum = LocalDate.of(2025, 10, 10);

        pt.setProdavac(null);
        pt.setTermin(t);
        pt.setDatumDezurstva(datum);
        pt.setSmena("jutarnja");

        Set<ConstraintViolation<ProdavacTermin>> violations = validator.validate(pt);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Prodavac ne moze biti null")));
    }

    @Test
    void testValidacijaTerminNull() {
        Prodavac p = new Prodavac(1, "Ana", "Anic", "aanic", "sifra123");
        LocalDate datum = LocalDate.of(2025, 10, 10);

        pt.setProdavac(p);
        pt.setTermin(null);
        pt.setDatumDezurstva(datum);
        pt.setSmena("jutarnja");

        Set<ConstraintViolation<ProdavacTermin>> violations = validator.validate(pt);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Termin ne moze biti null")));
    }

    @Test
    void testValidacijaDatumDezurstvaNull() {
        Prodavac p = new Prodavac(1, "Ana", "Anic", "aanic", "sifra123");
        Termin t = new Termin(1, "Jutarnja");

        pt.setProdavac(p);
        pt.setTermin(t);
        pt.setDatumDezurstva(null);
        pt.setSmena("jutarnja");

        Set<ConstraintViolation<ProdavacTermin>> violations = validator.validate(pt);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Datum dezurstva ne moze biti null")));
    }

    @Test
    void testValidacijaPraznaSmena() {
        Prodavac p = new Prodavac(1, "Ana", "Anic", "aanic", "sifra123");
        Termin t = new Termin(1, "Jutarnja");
        LocalDate datum = LocalDate.of(2025, 10, 10);

        pt.setProdavac(p);
        pt.setTermin(t);
        pt.setDatumDezurstva(datum);
        pt.setSmena("");

        Set<ConstraintViolation<ProdavacTermin>> violations = validator.validate(pt);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Smena ne moze biti prazna")));
    }
    
    @Test
    void testValidacijaProdavacNevalidanZbogPraznogImena() {
        Prodavac p = new Prodavac(1, "", "Anic", "aanic", "sifra123");
        Termin t = new Termin(1, "Jutarnja");
        LocalDate datum = LocalDate.of(2025, 10, 10);

        pt.setProdavac(p);
        pt.setTermin(t);
        pt.setDatumDezurstva(datum);
        pt.setSmena("jutarnja");

        Set<ConstraintViolation<ProdavacTermin>> violations = validator.validate(pt);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Ime ne moze biti prazno")));
    }

    @Test
    void testValidacijaTerminNevalidanZbogPraznogNaziva() {
        Prodavac p = new Prodavac(1, "Ana", "Anic", "aanic", "sifra123");
        Termin t = new Termin(1, "");
        LocalDate datum = LocalDate.of(2025, 10, 10);

        pt.setProdavac(p);
        pt.setTermin(t);
        pt.setDatumDezurstva(datum);
        pt.setSmena("jutarnja");

        Set<ConstraintViolation<ProdavacTermin>> violations = validator.validate(pt);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Naziv termina ne moze biti prazan")));
    }
}