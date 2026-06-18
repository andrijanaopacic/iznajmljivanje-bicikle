package operacije.bicikla;

import java.util.List;
import model.Bicikla;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za vracanje liste bicikli istog konkretnog tipa
 * kao prosledjeni parametar (npr. ako je prosledjena {@link model.BiciklaZaOdrasle},
 * vraca sve bicikle za odrasle).
 *
 * @author Andrijana Opacic
 * @see Bicikla
 */
public class VratiListuBiciklaBiciklaSO extends ApstraktnaGenerickaOperacija {

    /** Lista bicikli dobijena nakon izvrsavanja operacije. */
    private List<Bicikla> lista;

    /**
     * Vraca listu bicikli dobijenu nakon izvrsavanja operacije.
     *
     * @return lista bicikli istog tipa kao prosledjeni parametar
     */
    public List<Bicikla> getLista() {
        return lista;
    }

    /**
     * Proverava preduslove pre vracanja liste bicikli.
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param objekat objekat tipa {@link Bicikla} koji odredjuje konkretan
     *        tip bicikli koje se vracaju
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Bicikla)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Izvrsava vracanje liste svih bicikli istog konkretnog tipa kao
     * prosledjeni parametar, preko brokera.
     *
     * @param objekat objekat tipa {@link Bicikla} koji odredjuje konkretan
     *        tip bicikli koje se vracaju
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        List<Bicikla> lista = broker.getAll((Bicikla) objekat, "");
        this.lista = lista;
    }
}