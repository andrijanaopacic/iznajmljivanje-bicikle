package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import jakarta.validation.ConstraintViolation;

class BiciklaZaOdrasleTest extends BiciklaTest {

    @Override
    public Bicikla getInstance() {
        return new BiciklaZaOdrasle();
    }

    @Test
    void testSetVelicinaTockova() {
        ((BiciklaZaOdrasle) b).setVelicinaTockova(26);
        assertEquals(26, ((BiciklaZaOdrasle) b).getVelicinaTockova());
    }

    @Test
    void testSetBrojBrzina() {
        ((BiciklaZaOdrasle) b).setBrojBrzina(21);
        assertEquals(21, ((BiciklaZaOdrasle) b).getBrojBrzina());
    }

    @Test
    void testToString() {
        assertEquals("Bicikla za odrasle", b.toString());
    }

    @ParameterizedTest
    @CsvSource({
        "1, 1, true",
        "1, 2, false",
        "2, 1, false"
    })
    void testEquals(int id1, int id2, boolean jednako) {
        BiciklaZaOdrasle b1 = new BiciklaZaOdrasle();
        b1.setIdBicikla(id1);
        BiciklaZaOdrasle b2 = new BiciklaZaOdrasle();
        b2.setIdBicikla(id2);
        assertEquals(jednako, b1.equals(b2));
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("bicikla JOIN biciklazaodrasle ON bicikla.idBicikla = biciklazaodrasle.idBicikla",
                b.vratiNazivTabele());
    }

    @Test
    void testVratiNazivPodTabele() {
        assertEquals("biciklazaodrasle", b.vratiNazivPodTabele());
    }

    @Test
    void testVratiUpisPodTabele() {
        ((BiciklaZaOdrasle) b).setVelicinaTockova(26);
        ((BiciklaZaOdrasle) b).setBrojBrzina(21);
        assertEquals("INSERT INTO biciklazaodrasle (idBicikla, velicinaTockova, brojBrzina) VALUES (1,26,21)",
                b.vratiUpisPodTabele(1));
    }

    @Test
    void testVratiIzmenePodTabele() {
        b.setIdBicikla(1);
        ((BiciklaZaOdrasle) b).setVelicinaTockova(26);
        ((BiciklaZaOdrasle) b).setBrojBrzina(21);
        assertEquals("UPDATE biciklazaodrasle SET velicinaTockova=26, brojBrzina=21 WHERE idBicikla=1",
                b.vratiIzmenePodTabele());
    }

    @Test
    void testVratiListu() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("bicikla.idBicikla")).thenReturn(1);
        when(rs.getDouble("bicikla.cenaPoSatu")).thenReturn(500.0);
        when(rs.getDouble("bicikla.cenaPoDanu")).thenReturn(2000.0);
        when(rs.getString("bicikla.marka")).thenReturn("Trek");
        when(rs.getString("bicikla.model")).thenReturn("Marlin");
        when(rs.getString("bicikla.boja")).thenReturn("Crvena");
        when(rs.getInt("biciklazaodrasle.velicinaTockova")).thenReturn(26);
        when(rs.getInt("biciklazaodrasle.brojBrzina")).thenReturn(21);

        List<ApstraktniDomenskiObjekat> lista = b.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(1, lista.size());
        BiciklaZaOdrasle result = (BiciklaZaOdrasle) lista.get(0);
        assertEquals(1, result.getIdBicikla());
        assertEquals("Trek", result.getMarka());
        assertEquals(26, result.getVelicinaTockova());
        assertEquals(21, result.getBrojBrzina());
    }

    @Test
    void testVratiListuPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);
        List<ApstraktniDomenskiObjekat> lista = b.vratiListu(rs);
        assertNotNull(lista);
        assertEquals(0, lista.size());
    }

    @Test
    void testVratiObjekatIzRS() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("bicikla.idBicikla")).thenReturn(1);
        when(rs.getDouble("bicikla.cenaPoSatu")).thenReturn(500.0);
        when(rs.getDouble("bicikla.cenaPoDanu")).thenReturn(2000.0);
        when(rs.getString("bicikla.marka")).thenReturn("Trek");
        when(rs.getString("bicikla.model")).thenReturn("Marlin");
        when(rs.getString("bicikla.boja")).thenReturn("Crvena");
        when(rs.getInt("biciklazaodrasle.velicinaTockova")).thenReturn(26);
        when(rs.getInt("biciklazaodrasle.brojBrzina")).thenReturn(21);

        BiciklaZaOdrasle result = (BiciklaZaOdrasle) b.vratiObjekatIzRS(rs);

        assertNotNull(result);
        assertEquals(1, result.getIdBicikla());
        assertEquals("Trek", result.getMarka());
        assertEquals(26, result.getVelicinaTockova());
        assertEquals(21, result.getBrojBrzina());
    }

    @Test
    void testVratiObjekatIzRSPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);
        assertNull(b.vratiObjekatIzRS(rs));
    }

    @Test
    void testCitajTrenutniRed() throws Exception {
        when(rs.getInt("bicikla.idBicikla")).thenReturn(1);
        when(rs.getDouble("bicikla.cenaPoSatu")).thenReturn(500.0);
        when(rs.getDouble("bicikla.cenaPoDanu")).thenReturn(2000.0);
        when(rs.getString("bicikla.marka")).thenReturn("Trek");
        when(rs.getString("bicikla.model")).thenReturn("Marlin");
        when(rs.getString("bicikla.boja")).thenReturn("Crvena");
        when(rs.getInt("biciklazaodrasle.velicinaTockova")).thenReturn(26);
        when(rs.getInt("biciklazaodrasle.brojBrzina")).thenReturn(21);

        Bicikla result = b.citajTrenutniRed(rs);

        assertNotNull(result);
        assertEquals(1, result.getIdBicikla());
        assertEquals("Trek", result.getMarka());
        assertEquals(26, ((BiciklaZaOdrasle) result).getVelicinaTockova());
        assertEquals(21, ((BiciklaZaOdrasle) result).getBrojBrzina());
    }
    
    @Override
    @Test
    void testValidacijaProlaziKadaSuSveVrednostiValidne() {
        b.setCenaPoSatu(500.0);
        b.setCenaPoDanu(2000.0);
        b.setMarka("Trek");
        b.setModel("Marlin");
        b.setBoja("Crvena");
        ((BiciklaZaOdrasle) b).setVelicinaTockova(26);
        ((BiciklaZaOdrasle) b).setBrojBrzina(21);

        Set<ConstraintViolation<Bicikla>> violations = validator.validate(b);

        assertTrue(violations.isEmpty(), "Ne treba biti violations kada su sve vrednosti validne");
    }

    @Test
    void testValidacijaVelicinaTockovaNula() {
        b.setCenaPoSatu(500.0);
        b.setCenaPoDanu(2000.0);
        b.setMarka("Trek");
        b.setModel("Marlin");
        b.setBoja("Crvena");
        ((BiciklaZaOdrasle) b).setVelicinaTockova(0);
        ((BiciklaZaOdrasle) b).setBrojBrzina(21);

        Set<ConstraintViolation<Bicikla>> violations = validator.validate(b);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Velicina tockova mora biti veca od nule")));
    }

    @Test
    void testValidacijaBrojBrzinaNula() {
        b.setCenaPoSatu(500.0);
        b.setCenaPoDanu(2000.0);
        b.setMarka("Trek");
        b.setModel("Marlin");
        b.setBoja("Crvena");
        ((BiciklaZaOdrasle) b).setVelicinaTockova(26);
        ((BiciklaZaOdrasle) b).setBrojBrzina(0);

        Set<ConstraintViolation<Bicikla>> violations = validator.validate(b);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Broj brzina mora biti veci od nule")));
    }
    
    @Test
    void testValidacijaBrojBrzinaVeciOd30() {
        b.setCenaPoSatu(500.0);
        b.setCenaPoDanu(2000.0);
        b.setMarka("Trek");
        b.setModel("Marlin");
        b.setBoja("Crvena");
        ((BiciklaZaOdrasle) b).setVelicinaTockova(26);
        ((BiciklaZaOdrasle) b).setBrojBrzina(31);

        Set<ConstraintViolation<Bicikla>> violations = validator.validate(b);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Broj brzina ne moze biti veci od 30")));
    }
}