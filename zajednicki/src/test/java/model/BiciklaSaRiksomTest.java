package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BiciklaSaRiksomTest extends BiciklaTest {

    @Override
    public Bicikla getInstance() {
        return new BiciklaSaRiksom();
    }

    @Test
    void testSetBrojSedista() {
        ((BiciklaSaRiksom) b).setBrojSedista(2);
        assertEquals(2, ((BiciklaSaRiksom) b).getBrojSedista());
    }

    @Test
    void testSetTipRikse() {
        ((BiciklaSaRiksom) b).setTipRikse("putnicka");
        assertEquals("putnicka", ((BiciklaSaRiksom) b).getTipRikse());
    }

    @Test
    void testSetMaxNosivost() {
        ((BiciklaSaRiksom) b).setMaxNosivost(100);
        assertEquals(100, ((BiciklaSaRiksom) b).getMaxNosivost());
    }

    @Test
    void testToString() {
        assertEquals("Bicikla sa riksom", b.toString());
    }

    @ParameterizedTest
    @CsvSource({
        "1, 1, true",
        "1, 2, false",
        "2, 1, false"
    })
    void testEquals(int id1, int id2, boolean jednako) {
        BiciklaSaRiksom b1 = new BiciklaSaRiksom();
        b1.setIdBicikla(id1);
        BiciklaSaRiksom b2 = new BiciklaSaRiksom();
        b2.setIdBicikla(id2);
        assertEquals(jednako, b1.equals(b2));
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("bicikla JOIN biciklasariksom ON bicikla.idBicikla = biciklasariksom.idBicikla", b.vratiNazivTabele());
    }

    @Test
    void testVratiNazivPodTabele() {
        assertEquals("biciklasariksom", b.vratiNazivPodTabele());
    }

    @Test
    void testVratiUpisPodTabele() {
        ((BiciklaSaRiksom) b).setBrojSedista(2);
        ((BiciklaSaRiksom) b).setTipRikse("putnicka");
        ((BiciklaSaRiksom) b).setMaxNosivost(100);
        assertEquals("INSERT INTO biciklasariksom (idBicikla, brojSedista, tipRikse, maxNosivost) VALUES (1,2,'putnicka',100)",  b.vratiUpisPodTabele(1));
    }

    @Test
    void testVratiIzmenePodTabele() {
        b.setIdBicikla(1);
        ((BiciklaSaRiksom) b).setBrojSedista(2);
        ((BiciklaSaRiksom) b).setTipRikse("putnicka");
        ((BiciklaSaRiksom) b).setMaxNosivost(100);
        assertEquals("UPDATE biciklasariksom SET brojSedista=2, tipRikse='putnicka', maxNosivost=100 WHERE idBicikla=1",
                b.vratiIzmenePodTabele());
    }

    @Test
    void testVratiListu() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("bicikla.idBicikla")).thenReturn(1);
        when(rs.getDouble("bicikla.cenaPoSatu")).thenReturn(500.0);
        when(rs.getDouble("bicikla.cenaPoDanu")).thenReturn(2000.0);
        when(rs.getString("bicikla.marka")).thenReturn("Trek");
        when(rs.getString("bicikla.model")).thenReturn("Riksa");
        when(rs.getString("bicikla.boja")).thenReturn("Zuta");
        when(rs.getInt("biciklasariksom.brojSedista")).thenReturn(2);
        when(rs.getString("biciklasariksom.tipRikse")).thenReturn("putnicka");
        when(rs.getInt("biciklasariksom.maxNosivost")).thenReturn(100);

        List<ApstraktniDomenskiObjekat> lista = b.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(1, lista.size());
        BiciklaSaRiksom result = (BiciklaSaRiksom) lista.get(0);
        assertEquals(1, result.getIdBicikla());
        assertEquals("Trek", result.getMarka());
        assertEquals(2, result.getBrojSedista());
        assertEquals("putnicka", result.getTipRikse());
        assertEquals(100, result.getMaxNosivost());
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
        when(rs.getString("bicikla.model")).thenReturn("Riksa");
        when(rs.getString("bicikla.boja")).thenReturn("Zuta");
        when(rs.getInt("biciklasariksom.brojSedista")).thenReturn(2);
        when(rs.getString("biciklasariksom.tipRikse")).thenReturn("putnicka");
        when(rs.getInt("biciklasariksom.maxNosivost")).thenReturn(100);

        BiciklaSaRiksom result = (BiciklaSaRiksom) b.vratiObjekatIzRS(rs);

        assertNotNull(result);
        assertEquals(1, result.getIdBicikla());
        assertEquals("Trek", result.getMarka());
        assertEquals(2, result.getBrojSedista());
        assertEquals("putnicka", result.getTipRikse());
        assertEquals(100, result.getMaxNosivost());
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
        when(rs.getString("bicikla.model")).thenReturn("Riksa");
        when(rs.getString("bicikla.boja")).thenReturn("Zuta");
        when(rs.getInt("biciklasariksom.brojSedista")).thenReturn(2);
        when(rs.getString("biciklasariksom.tipRikse")).thenReturn("putnicka");
        when(rs.getInt("biciklasariksom.maxNosivost")).thenReturn(100);

        Bicikla result = b.citajTrenutniRed(rs);

        assertNotNull(result);
        assertEquals(1, result.getIdBicikla());
        assertEquals("Trek", result.getMarka());
        assertEquals(2, ((BiciklaSaRiksom) result).getBrojSedista());
        assertEquals("putnicka", ((BiciklaSaRiksom) result).getTipRikse());
        assertEquals(100, ((BiciklaSaRiksom) result).getMaxNosivost());
    }
}