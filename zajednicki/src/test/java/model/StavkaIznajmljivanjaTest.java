package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

class StavkaIznajmljivanjaTest {

    StavkaIznajmljivanja stavka;
    BiciklaZaOdrasle bicikla;
    Iznajmljivanje iznajmljivanje;
    Validator validator;

    @Mock
    ResultSet rs;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() throws Exception {
        stavka = new StavkaIznajmljivanja();
        bicikla = new BiciklaZaOdrasle();
        iznajmljivanje = new Iznajmljivanje();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        stavka = null;
        bicikla = null;
        iznajmljivanje = null;
        closeable.close();
    }

    @Test
    void testPodrazumevaniKonstruktor() {
        assertNotNull(stavka);
    }

    @Test
    void testSetIdStavkaIznajmljivanja() {
        stavka.setIdStavkaIznajmljivanja(1);
        assertEquals(1, stavka.getIdStavkaIznajmljivanja());
    }

    @Test
    void testSetIznos() {
        stavka.setIznos(1000.0);
        assertEquals(1000.0, stavka.getIznos());
    }

    @Test
    void testSetCena() {
        stavka.setCena(500.0);
        assertEquals(500.0, stavka.getCena());
    }

    @Test
    void testSetBrojSati() {
        stavka.setBrojSati(2);
        assertEquals(2, stavka.getBrojSati());
    }

    @Test
    void testSetBrojDana() {
        stavka.setBrojDana(1);
        assertEquals(1, stavka.getBrojDana());
    }

    @Test
    void testSetVremeOd() {
        LocalDateTime vremeOd = LocalDateTime.of(2025, 10, 10, 10, 0, 0);
        stavka.setVremeOd(vremeOd);
        assertEquals(vremeOd, stavka.getVremeOd());
    }

    @Test
    void testSetVremeDo() {
        LocalDateTime vremeDo = LocalDateTime.of(2025, 10, 10, 12, 0, 0);
        stavka.setVremeDo(vremeDo);
        assertEquals(vremeDo, stavka.getVremeDo());
    }

    @Test
    void testSetBicikla() {
        bicikla.setIdBicikla(1);
        stavka.setBicikla(bicikla);
        assertEquals(bicikla, stavka.getBicikla());
    }

    @Test
    void testSetIznajmljivanje() {
        iznajmljivanje.setIdIznajmljivanje(1);
        stavka.setIznajmljivanje(iznajmljivanje);
        assertEquals(iznajmljivanje, stavka.getIznajmljivanje());
    }

    @Test
    void testKonstruktorBrojSati() {
        bicikla.setIdBicikla(1);
        bicikla.setCenaPoSatu(500.0);
        bicikla.setCenaPoDanu(2000.0);
        iznajmljivanje.setIdIznajmljivanje(1);

        LocalDateTime vremeOd = LocalDateTime.of(2025, 10, 10, 10, 0, 0);
        LocalDateTime vremeDo = LocalDateTime.of(2025, 10, 10, 12, 0, 0);

        StavkaIznajmljivanja s = new StavkaIznajmljivanja(bicikla, 1, 0, 0, vremeOd, vremeDo, 2, 0, iznajmljivanje);

        assertEquals(500.0, s.getCena());
        assertEquals(1000.0, s.getIznos());
        assertEquals(2, s.getBrojSati());
        assertEquals(0, s.getBrojDana());
    }

    @Test
    void testKonstruktorBrojDana() {
        bicikla.setIdBicikla(1);
        bicikla.setCenaPoSatu(500.0);
        bicikla.setCenaPoDanu(2000.0);
        iznajmljivanje.setIdIznajmljivanje(1);

        LocalDateTime vremeOd = LocalDateTime.of(2025, 10, 10, 10, 0, 0);
        LocalDateTime vremeDo = LocalDateTime.of(2025, 10, 12, 10, 0, 0);

        StavkaIznajmljivanja s = new StavkaIznajmljivanja(bicikla, 1, 0, 0, vremeOd, vremeDo, 0, 2, iznajmljivanje);

        assertEquals(2000.0, s.getCena());
        assertEquals(4000.0, s.getIznos());
        assertEquals(0, s.getBrojSati());
        assertEquals(2, s.getBrojDana());
    }

    @Test
    void testKonstruktorBezIdBrojSati() {
        bicikla.setIdBicikla(1);
        bicikla.setCenaPoSatu(500.0);
        bicikla.setCenaPoDanu(2000.0);
        iznajmljivanje.setIdIznajmljivanje(1);

        LocalDateTime vremeOd = LocalDateTime.of(2025, 10, 10, 10, 0, 0);
        LocalDateTime vremeDo = LocalDateTime.of(2025, 10, 10, 12, 0, 0);

        StavkaIznajmljivanja s = new StavkaIznajmljivanja(bicikla, 0, 0, vremeOd, vremeDo, 2, 0, iznajmljivanje);

        assertEquals(0, s.getIdStavkaIznajmljivanja());
        assertEquals(500.0, s.getCena());
        assertEquals(1000.0, s.getIznos());
        assertEquals(2, s.getBrojSati());
        assertEquals(0, s.getBrojDana());
        assertEquals(bicikla, s.getBicikla());
        assertEquals(iznajmljivanje, s.getIznajmljivanje());
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("stavkaiznajmljivanja", stavka.vratiNazivTabele());
    }

    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("iznos,cena,vremeOd,vremeDo,brojSati,brojDana,idBicikla,idIznajmljivanje", stavka.vratiKoloneZaUbacivanje());
    }

    @Test
    void testVratiVrednostiZaUbacivanje() {
        LocalDateTime vremeOd = LocalDateTime.of(2025, 10, 10, 10, 0, 0);
        LocalDateTime vremeDo = LocalDateTime.of(2025, 10, 10, 12, 0, 0);

        bicikla.setIdBicikla(1);
        iznajmljivanje.setIdIznajmljivanje(1);

        stavka.setIznos(1000.0);
        stavka.setCena(500.0);
        stavka.setVremeOd(vremeOd);
        stavka.setVremeDo(vremeDo);
        stavka.setBrojSati(2);
        stavka.setBrojDana(0);
        stavka.setBicikla(bicikla);
        stavka.setIznajmljivanje(iznajmljivanje);

        assertEquals("1000.0,500.0,'" + Timestamp.valueOf(vremeOd) + "','" + Timestamp.valueOf(vremeDo) + "',2,0,1,1", stavka.vratiVrednostiZaUbacivanje());
    }

    @Test
    void testVratiPrimarniKljuc() {
        iznajmljivanje.setIdIznajmljivanje(1);
        stavka.setIdStavkaIznajmljivanja(1);
        stavka.setIznajmljivanje(iznajmljivanje);

        assertEquals("stavkaiznajmljivanja.idIznajmljivanje=1 AND stavkaiznajmljivanja.idStavkaIznajmljivanja=1", stavka.vratiPrimarniKljuc());
    }

    @Test
    void testVratiVrednostiZaIzmenu() {
        LocalDateTime vremeOd = LocalDateTime.of(2025, 10, 10, 10, 0, 0);
        LocalDateTime vremeDo = LocalDateTime.of(2025, 10, 10, 12, 0, 0);

        bicikla.setIdBicikla(1);

        stavka.setIznos(1000.0);
        stavka.setCena(500.0);
        stavka.setVremeOd(vremeOd);
        stavka.setVremeDo(vremeDo);
        stavka.setBrojSati(2);
        stavka.setBrojDana(0);
        stavka.setBicikla(bicikla);

        assertEquals("iznos=1000.0, cena=500.0, vremeOd='" + Timestamp.valueOf(vremeOd) + "', vremeDo='" + Timestamp.valueOf(vremeDo) + "', brojSati=2, brojDana=0, idBicikla=1", stavka.vratiVrednostiZaIzmenu());
    }

    @Test
    void testVratiListu() throws Exception {
        LocalDateTime vremeOd = LocalDateTime.of(2025, 10, 10, 10, 0, 0);
        LocalDateTime vremeDo = LocalDateTime.of(2025, 10, 10, 12, 0, 0);

        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("stavkaiznajmljivanja.idStavkaIznajmljivanja")).thenReturn(1);
        when(rs.getDouble("stavkaiznajmljivanja.iznos")).thenReturn(1000.0);
        when(rs.getDouble("stavkaiznajmljivanja.cena")).thenReturn(500.0);
        when(rs.getTimestamp("stavkaiznajmljivanja.vremeOd"))
                .thenReturn(Timestamp.valueOf(vremeOd));
        when(rs.getTimestamp("stavkaiznajmljivanja.vremeDo"))
                .thenReturn(Timestamp.valueOf(vremeDo));
        when(rs.getInt("stavkaiznajmljivanja.brojSati")).thenReturn(2);
        when(rs.getInt("stavkaiznajmljivanja.brojDana")).thenReturn(0);
        when(rs.getInt("biciklazaodrasle.idBicikla")).thenReturn(1);
        when(rs.wasNull()).thenReturn(false);
        when(rs.getInt("bicikla.idBicikla")).thenReturn(1);
        when(rs.getDouble("bicikla.cenaPoSatu")).thenReturn(500.0);
        when(rs.getDouble("bicikla.cenaPoDanu")).thenReturn(2000.0);
        when(rs.getString("bicikla.marka")).thenReturn("Trek");
        when(rs.getString("bicikla.model")).thenReturn("Marlin");
        when(rs.getString("bicikla.boja")).thenReturn("Crvena");
        when(rs.getInt("biciklazaodrasle.velicinaTockova")).thenReturn(26);
        when(rs.getInt("biciklazaodrasle.brojBrzina")).thenReturn(21);

        List<ApstraktniDomenskiObjekat> lista = stavka.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(1, lista.size());

        StavkaIznajmljivanja result = (StavkaIznajmljivanja) lista.get(0);
        assertEquals(1, result.getIdStavkaIznajmljivanja());
        assertEquals(1000.0, result.getIznos());
        assertEquals(500.0, result.getCena());
        assertEquals(2, result.getBrojSati());
        assertEquals(0, result.getBrojDana());
        assertNotNull(result.getBicikla());
    }

    @Test
    void testVratiListuPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        List<ApstraktniDomenskiObjekat> lista = stavka.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(0, lista.size());
    }
    
    @Test
    void testValidacijaProlaziKadaSuSveVrednostiValidne() {
        bicikla.setIdBicikla(1);
        bicikla.setCenaPoSatu(500.0);
        bicikla.setCenaPoDanu(2000.0);
        iznajmljivanje.setIdIznajmljivanje(1);

        LocalDateTime vremeOd = LocalDateTime.of(2025, 10, 10, 10, 0, 0);
        LocalDateTime vremeDo = LocalDateTime.of(2025, 10, 10, 12, 0, 0);

        StavkaIznajmljivanja s = new StavkaIznajmljivanja(bicikla, 1, 0, 0, vremeOd, vremeDo, 2, 0, iznajmljivanje);

        Set<ConstraintViolation<StavkaIznajmljivanja>> violations = validator.validate(s);

        assertTrue(violations.isEmpty(), "Ne treba biti violations kada su sve vrednosti validne");
    }

    @Test
    void testValidacijaBiciklaNull() {
        iznajmljivanje.setIdIznajmljivanje(1);
        LocalDateTime vremeOd = LocalDateTime.of(2025, 10, 10, 10, 0, 0);
        LocalDateTime vremeDo = LocalDateTime.of(2025, 10, 10, 12, 0, 0);

        stavka.setBicikla(null);
        stavka.setVremeOd(vremeOd);
        stavka.setVremeDo(vremeDo);
        stavka.setIznajmljivanje(iznajmljivanje);

        Set<ConstraintViolation<StavkaIznajmljivanja>> violations = validator.validate(stavka);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Bicikla ne moze biti null")));
    }

    @Test
    void testValidacijaVremeOdUBuducnosti() {
        bicikla.setIdBicikla(1);
        iznajmljivanje.setIdIznajmljivanje(1);
        LocalDateTime vremeOd = LocalDateTime.now().plusDays(1);
        LocalDateTime vremeDo = LocalDateTime.now().plusDays(2);

        stavka.setBicikla(bicikla);
        stavka.setVremeOd(vremeOd);
        stavka.setVremeDo(vremeDo);
        stavka.setIznajmljivanje(iznajmljivanje);

        Set<ConstraintViolation<StavkaIznajmljivanja>> violations = validator.validate(stavka);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Vreme pocetka ne moze biti u buducnosti")));
    }

    @Test
    void testValidacijaVremeDoUBuducnosti() {
        bicikla.setIdBicikla(1);
        iznajmljivanje.setIdIznajmljivanje(1);
        LocalDateTime vremeOd = LocalDateTime.of(2025, 10, 10, 10, 0, 0);
        LocalDateTime vremeDo = LocalDateTime.now().plusDays(1);

        stavka.setBicikla(bicikla);
        stavka.setVremeOd(vremeOd);
        stavka.setVremeDo(vremeDo);
        stavka.setIznajmljivanje(iznajmljivanje);

        Set<ConstraintViolation<StavkaIznajmljivanja>> violations = validator.validate(stavka);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Vreme zavrsetka ne moze biti u buducnosti")));
    }

    @Test
    void testValidacijaIznajmljivanjeNull() {
        bicikla.setIdBicikla(1);
        LocalDateTime vremeOd = LocalDateTime.of(2025, 10, 10, 10, 0, 0);
        LocalDateTime vremeDo = LocalDateTime.of(2025, 10, 10, 12, 0, 0);

        stavka.setBicikla(bicikla);
        stavka.setVremeOd(vremeOd);
        stavka.setVremeDo(vremeDo);
        stavka.setIznajmljivanje(null);

        Set<ConstraintViolation<StavkaIznajmljivanja>> violations = validator.validate(stavka);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Iznajmljivanje ne moze biti null")));
    }
}