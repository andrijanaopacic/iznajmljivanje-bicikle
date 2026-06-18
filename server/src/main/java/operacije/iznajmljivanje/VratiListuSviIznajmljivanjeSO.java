package operacije.iznajmljivanje;

import java.util.List;
import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za vracanje liste svih iznajmljivanja u sistemu.
 *
 * @author Andrijana Opacic
 * @see Iznajmljivanje
 */
public class VratiListuSviIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

	/** Lista svih iznajmljivanja. */
    private List<Iznajmljivanje> lista;

    /**
     * Vraca listu svih iznajmljivanja dobijenu nakon izvrsavanja operacije.
     *
     * @return lista svih iznajmljivanja, sa popunjenim listama stavki
     */
    public List<Iznajmljivanje> getLista() {
        return lista;
    }

    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param objekat objekat tipa {@link Iznajmljivanje} koji se koristi
     *        kao osnova za pretragu
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Iznajmljivanje)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Ucitava sva iznajmljivanja zajedno sa podacima o prodavcu, kupcu i
     * mestu, i za svako ucitava i njegove stavke.
     *
     * @param objekat objekat tipa {@link Iznajmljivanje} koji se koristi
     *        kao osnova za pretragu
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        String upit1 = " JOIN prodavac ON prodavac.idProdavac = iznajmljivanje.idProdavac"
                + " JOIN kupac ON iznajmljivanje.idKupac = kupac.idKupac"
                + " JOIN mesto ON mesto.idMesto = kupac.idMesto";

        List<Iznajmljivanje> lista = broker.getAll((Iznajmljivanje) objekat, upit1);

        for (Iznajmljivanje i : lista) {
            String upit2 = " JOIN bicikla ON bicikla.idBicikla = stavkaiznajmljivanja.idBicikla"
                    + " LEFT JOIN biciklazaodrasle ON biciklazaodrasle.idBicikla = stavkaiznajmljivanja.idBicikla"
                    + " LEFT JOIN biciklazadecu ON biciklazadecu.idBicikla = stavkaiznajmljivanja.idBicikla"
                    + " LEFT JOIN biciklasariksom ON biciklasariksom.idBicikla = stavkaiznajmljivanja.idBicikla"
                    + " WHERE stavkaiznajmljivanja.idIznajmljivanje = " + i.getIdIznajmljivanje();
            List<StavkaIznajmljivanja> stavke = broker.getAll(new StavkaIznajmljivanja(), upit2);
            i.setListaStavkiIznajmljivanja(stavke);
        }

        this.lista = lista;
    }
}