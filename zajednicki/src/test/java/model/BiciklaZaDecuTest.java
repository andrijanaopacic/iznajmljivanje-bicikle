package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BiciklaZaDecuTest extends BiciklaTest {

    @Override
    public Bicikla getInstance() {
        return new BiciklaZaDecu();
    }

    @Test
    void testSetVelicinaTockova() {
        ((BiciklaZaDecu) b).setVelicinaTockova(20);
        assertEquals(20, ((BiciklaZaDecu) b).getVelicinaTockova());
    }

    @Test
    void testSetBrojBrzina() {
        ((BiciklaZaDecu) b).setBrojBrzina(6);
        assertEquals(6, ((BiciklaZaDecu) b).getBrojBrzina());
    }

    @Test
    void testSetPomocniTockoviTrue() {
        ((BiciklaZaDecu) b).setPomocniTockovi(true);
        assertTrue(((BiciklaZaDecu) b).isPomocniTockovi());
    }

    @Test
    void testSetPomocniTockovimaFalse() {
        ((BiciklaZaDecu) b).setPomocniTockovi(false);
        assertFalse(((BiciklaZaDecu) b).isPomocniTockovi());
    }

    @Test
    void testToString() {
        assertEquals("Bicikla za decu", b.toString());
    }

    @ParameterizedTest
    @CsvSource({
        "1, 1, true",
        "1, 2, false",
        "2, 1, false"
    })
    void testEquals(int id1, int id2, boolean jednako) {
        BiciklaZaDecu b1 = new BiciklaZaDecu();
        b1.setIdBicikla(id1);
        BiciklaZaDecu b2 = new BiciklaZaDecu();
        b2.setIdBicikla(id2);
        assertEquals(jednako, b1.equals(b2));
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("bicikla JOIN biciklazadecu ON bicikla.idBicikla = biciklazadecu.idBicikla",
                b.vratiNazivTabele());
    }

    @Test
    void testVratiNazivPodTabele() {
        assertEquals("biciklazadecu", b.vratiNazivPodTabele());
    }

    @Test
    void testVratiUpisPodTabeleTrue() {
        ((BiciklaZaDecu) b).setVelicinaTockova(20);
        ((BiciklaZaDecu) b).setBrojBrzina(6);
        ((BiciklaZaDecu) b).setPomocniTockovi(true);
        assertEquals("INSERT INTO biciklazadecu (idBicikla, velicinaTockova, brojBrzina, pomocniTockovi) VALUES (1,20,6,1)",
                b.vratiUpisPodTabele(1));
    }

    @Test
    void testVratiUpisPodTabeleFalse() {
        ((BiciklaZaDecu) b).setVelicinaTockova(20);
        ((BiciklaZaDecu) b).setBrojBrzina(6);
        ((BiciklaZaDecu) b).setPomocniTockovi(false);
        assertEquals("INSERT INTO biciklazadecu (idBicikla, velicinaTockova, brojBrzina, pomocniTockovi) VALUES (1,20,6,0)",
                b.vratiUpisPodTabele(1));
    }

    @Test
    void testVratiIzmenePodTabeleTrue() {
        b.setIdBicikla(1);
        ((BiciklaZaDecu) b).setVelicinaTockova(20);
        ((BiciklaZaDecu) b).setBrojBrzina(6);
        ((BiciklaZaDecu) b).setPomocniTockovi(true);
        assertEquals("UPDATE biciklazadecu SET velicinaTockova=20, brojBrzina=6, pomocniTockovi=1 WHERE idBicikla=1",
                b.vratiIzmenePodTabele());
    }

    @Test
    void testVratiIzmenePodTabeleFalse() {
        b.setIdBicikla(1);
        ((BiciklaZaDecu) b).setVelicinaTockova(20);
        ((BiciklaZaDecu) b).setBrojBrzina(6);
        ((BiciklaZaDecu) b).setPomocniTockovi(false);
        assertEquals("UPDATE biciklazadecu SET velicinaTockova=20, brojBrzina=6, pomocniTockovi=0 WHERE idBicikla=1",
                b.vratiIzmenePodTabele());
    }

    @Test
    void testVratiListu() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("bicikla.idBicikla")).thenReturn(1);
        when(rs.getDouble("bicikla.cenaPoSatu")).thenReturn(500.0);
        when(rs.getDouble("bicikla.cenaPoDanu")).thenReturn(2000.0);
        when(rs.getString("bicikla.marka")).thenReturn("Trek");
        when(rs.getString("bicikla.model")).thenReturn("Kids");
        when(rs.getString("bicikla.boja")).thenReturn("Plava");
        when(rs.getInt("biciklazadecu.velicinaTockova")).thenReturn(20);
        when(rs.getInt("biciklazadecu.brojBrzina")).thenReturn(6);
        when(rs.getBoolean("biciklazadecu.pomocniTockovi")).thenReturn(true);

        List<ApstraktniDomenskiObjekat> lista = b.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(1, lista.size());
        BiciklaZaDecu result = (BiciklaZaDecu) lista.get(0);
        assertEquals(1, result.getIdBicikla());
        assertEquals("Trek", result.getMarka());
        assertEquals(20, result.getVelicinaTockova());
        assertTrue(result.isPomocniTockovi());
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
        when(rs.getString("bicikla.model")).thenReturn("Kids");
        when(rs.getString("bicikla.boja")).thenReturn("Plava");
        when(rs.getInt("biciklazadecu.velicinaTockova")).thenReturn(20);
        when(rs.getInt("biciklazadecu.brojBrzina")).thenReturn(6);
        when(rs.getBoolean("biciklazadecu.pomocniTockovi")).thenReturn(true);

        BiciklaZaDecu result = (BiciklaZaDecu) b.vratiObjekatIzRS(rs);

        assertNotNull(result);
        assertEquals(1, result.getIdBicikla());
        assertEquals("Trek", result.getMarka());
        assertEquals(20, result.getVelicinaTockova());
        assertTrue(result.isPomocniTockovi());
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
        when(rs.getString("bicikla.model")).thenReturn("Kids");
        when(rs.getString("bicikla.boja")).thenReturn("Plava");
        when(rs.getInt("biciklazadecu.velicinaTockova")).thenReturn(20);
        when(rs.getInt("biciklazadecu.brojBrzina")).thenReturn(6);
        when(rs.getBoolean("biciklazadecu.pomocniTockovi")).thenReturn(true);

        Bicikla result = b.citajTrenutniRed(rs);

        assertNotNull(result);
        assertEquals(1, result.getIdBicikla());
        assertEquals("Trek", result.getMarka());
        assertEquals(20, ((BiciklaZaDecu) result).getVelicinaTockova());
        assertTrue(((BiciklaZaDecu) result).isPomocniTockovi());
    }
}