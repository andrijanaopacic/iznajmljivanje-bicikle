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

class ProdavacTest {

    Prodavac p;
    Validator validator;

    @Mock
    ResultSet rs;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() throws Exception {
        p = new Prodavac();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        p = null;
        closeable.close();
    }

    @Test
    void testSetIdProdavac() {
        p.setIdProdavac(1);
        assertEquals(1, p.getIdProdavac());
    }

    @Test
    void testSetIme() {
        p.setIme("Ana");
        assertEquals("Ana", p.getIme());
    }

    @Test
    void testSetPrezime() {
        p.setPrezime("Anic");
        assertEquals("Anic", p.getPrezime());
    }

    @Test
    void testSetKorisnickoIme() {
        p.setKorisnickoIme("aanic");
        assertEquals("aanic", p.getKorisnickoIme());
    }

    @Test
    void testSetSifra() {
        p.setSifra("sifra123");
        assertEquals("sifra123", p.getSifra());
    }

    @Test
    void testToString() {
        p.setIme("Ana");
        p.setPrezime("Anic");
        assertEquals("Ana Anic", p.toString());
    }

    @Test
    void testEqualsIstiObjekat() {
        assertTrue(p.equals(p));
    }

    @Test
    void testEqualsNullObjekat() {
        assertFalse(p.equals(null));
    }

    @Test
    void testEqualsDrugaKlasa() {
        assertFalse(p.equals(new String()));
    }

    @ParameterizedTest
    @CsvSource({
        "aanic, sifra123, aanic, sifra123, true",
        "aanic, sifra123, bbilic, sifra123, false",
        "aanic, sifra123, aanic, drugaSifra, false",
        "aanic, sifra123, bbilic, drugaSifra, false"
    })
    void testEquals(String korisnickoIme1, String sifra1,
            String korisnickoIme2, String sifra2, boolean jednako) {
        Prodavac p1 = new Prodavac();
        p1.setKorisnickoIme(korisnickoIme1);
        p1.setSifra(sifra1);
        Prodavac p2 = new Prodavac();
        p2.setKorisnickoIme(korisnickoIme2);
        p2.setSifra(sifra2);
        assertEquals(jednako, p1.equals(p2));
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("prodavac", p.vratiNazivTabele());
    }

    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("ime,prezime,korisnickoIme,sifra",
                p.vratiKoloneZaUbacivanje());
    }

    @Test
    void testVratiVrednostiZaUbacivanje() {
        p.setIme("Ana");
        p.setPrezime("Anic");
        p.setKorisnickoIme("aanic");
        p.setSifra("sifra123");
        assertEquals("'Ana','Anic','aanic','sifra123'",
                p.vratiVrednostiZaUbacivanje());
    }

    @Test
    void testVratiPrimarniKljuc() {
        p.setIdProdavac(1);
        assertEquals("prodavac.idProdavac=1", p.vratiPrimarniKljuc());
    }

    @Test
    void testVratiVrednostiZaIzmenu() {
        p.setIme("Ana");
        p.setPrezime("Anic");
        p.setKorisnickoIme("aanic");
        p.setSifra("sifra123");
        assertEquals("ime= 'Ana',prezime = 'Anic',korisnickoIme = 'aanic',sifra = 'sifra123'", p.vratiVrednostiZaIzmenu());
    }

    @Test
    void testVratiListu() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("prodavac.idProdavac")).thenReturn(1);
        when(rs.getString("prodavac.ime")).thenReturn("Ana");
        when(rs.getString("prodavac.prezime")).thenReturn("Anic");
        when(rs.getString("prodavac.korisnickoIme")).thenReturn("aanic");
        when(rs.getString("prodavac.sifra")).thenReturn("sifra123");

        List<ApstraktniDomenskiObjekat> lista = p.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(1, lista.size());

        Prodavac result = (Prodavac) lista.get(0);
        assertEquals(1, result.getIdProdavac());
        assertEquals("Ana", result.getIme());
        assertEquals("Anic", result.getPrezime());
        assertEquals("aanic", result.getKorisnickoIme());
        assertEquals("sifra123", result.getSifra());
    }

    @Test
    void testVratiListuPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        List<ApstraktniDomenskiObjekat> lista = p.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(0, lista.size());
    }

    @Test
    void testVratiObjekatIzRS() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("idProdavac")).thenReturn(1);
        when(rs.getString("ime")).thenReturn("Ana");
        when(rs.getString("prezime")).thenReturn("Anic");
        when(rs.getString("korisnickoIme")).thenReturn("aanic");
        when(rs.getString("sifra")).thenReturn("sifra123");

        Prodavac result = (Prodavac) p.vratiObjekatIzRS(rs);

        assertNotNull(result);
        assertEquals(1, result.getIdProdavac());
        assertEquals("Ana", result.getIme());
        assertEquals("Anic", result.getPrezime());
        assertEquals("aanic", result.getKorisnickoIme());
        assertEquals("sifra123", result.getSifra());
    }

    @Test
    void testVratiObjekatIzRSPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        ApstraktniDomenskiObjekat result = p.vratiObjekatIzRS(rs);

        assertNull(result);
    }
    
    @Test
    void testValidacijaProlaziKadaSuSveVrednostiValidne() {
        p.setIme("Ana");
        p.setPrezime("Anic");
        p.setKorisnickoIme("aanic");
        p.setSifra("sifra123");

        Set<ConstraintViolation<Prodavac>> violations = validator.validate(p);

        assertTrue(violations.isEmpty(), "Ne treba biti violations kada su sve vrednosti validne");
    }

    @Test
    void testValidacijaPraznoIme() {
        p.setIme("");
        p.setPrezime("Anic");
        p.setKorisnickoIme("aanic");
        p.setSifra("sifra123");

        Set<ConstraintViolation<Prodavac>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Ime ne moze biti prazno")));
    }

    @Test
    void testValidacijaImeDuzeOd30Karaktera() {
        p.setIme("OvoJeImeKojeImaViseOdTridesetKaraktera");
        p.setPrezime("Anic");
        p.setKorisnickoIme("aanic");
        p.setSifra("sifra123");

        Set<ConstraintViolation<Prodavac>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("Ime ne moze imati vise od 30 karaktera")));
    }

    @Test
    void testValidacijaPraznoPrezime() {
        p.setIme("Ana");
        p.setPrezime("");
        p.setKorisnickoIme("aanic");
        p.setSifra("sifra123");

        Set<ConstraintViolation<Prodavac>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Prezime ne moze biti prazno")));
    }

    @Test
    void testValidacijaPrezimeDuzeOd30Karaktera() {
        p.setIme("Ana");
        p.setPrezime("OvoJePrezimeKojeImaViseOdTridesetKaraktera");
        p.setKorisnickoIme("aanic");
        p.setSifra("sifra123");

        Set<ConstraintViolation<Prodavac>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Prezime ne moze imati vise od 30 karaktera")));
    }

    @Test
    void testValidacijaPraznoKorisnickoIme() {
        p.setIme("Ana");
        p.setPrezime("Anic");
        p.setKorisnickoIme("");
        p.setSifra("sifra123");

        Set<ConstraintViolation<Prodavac>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Korisnicko ime ne moze biti prazno")));
    }

    @Test
    void testValidacijaKorisnickoImeDuzeOd20Karaktera() {
        p.setIme("Ana");
        p.setPrezime("Anic");
        p.setKorisnickoIme("korisnickoImeKojeImaViseOd20Karaktera");
        p.setSifra("sifra123");

        Set<ConstraintViolation<Prodavac>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Korisnicko ime ne moze imati vise od 20 karaktera")));
    }

    @Test
    void testValidacijaPraznaSifra() {
        p.setIme("Ana");
        p.setPrezime("Anic");
        p.setKorisnickoIme("aanic");
        p.setSifra("");

        Set<ConstraintViolation<Prodavac>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Sifra ne moze biti prazna")));
    }

    @Test
    void testValidacijaSifraDuzaOd20Karaktera() {
        p.setIme("Ana");
        p.setPrezime("Anic");
        p.setKorisnickoIme("aanic");
        p.setSifra("sifraKojaImaViseOd20Karaktera");

        Set<ConstraintViolation<Prodavac>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Sifra ne moze imati vise od 20 karaktera")));
    }
}