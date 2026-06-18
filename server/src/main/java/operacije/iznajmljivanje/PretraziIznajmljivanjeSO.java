package operacije.iznajmljivanje;

import java.util.List;
import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za pretragu iznajmljivanja po jedinstvenom
 * identifikatoru. Pored osnovnih podataka o iznajmljivanju (prodavac, kupac,
 * mesto), ucitava i sve stavke koje pripadaju tom iznajmljivanju.
 *
 * @author Andrijana Opacic
 * @see Iznajmljivanje
 * @see StavkaIznajmljivanja
 */
public class PretraziIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

	/** Pronadjeno iznajmljivanje, zajedno sa svojim stavkama, nakon izvrsavanja operacije. */
    private Iznajmljivanje iznajmljivanje;

    /**
     * Vraca pronadjeno iznajmljivanje.
     *
     * @return pronadjeno iznajmljivanje sa popunjenom listom stavki
     */
    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

    /**
     * Proverava preduslove pre pretrage iznajmljivanja.
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param objekat objekat tipa {@link Iznajmljivanje} koje se trazi
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Iznajmljivanje)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Izvrsava pretragu iznajmljivanja preko brokera na osnovu ID-a
     * iznajmljivanja, ucitavajuci podatke o prodavcu, kupcu i mestu.
     * Zatim ucitava sve stavke koje pripadaju pronadjenom iznajmljivanju,
     * vodeci racuna o svim mogucim tipovima bicikle, i postavlja ih u
     * listu stavki iznajmljivanja.
     *
     * @param objekat objekat tipa {@link Iznajmljivanje} koje se trazi
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        String upit1 = " JOIN prodavac ON prodavac.idProdavac = iznajmljivanje.idProdavac"
                + " JOIN kupac ON iznajmljivanje.idKupac = kupac.idKupac"
                + " JOIN mesto ON mesto.idMesto = kupac.idMesto"
                + " WHERE iznajmljivanje.idIznajmljivanje = "
                + ((Iznajmljivanje) objekat).getIdIznajmljivanje();

        Iznajmljivanje i = (Iznajmljivanje) broker.getObject((Iznajmljivanje) objekat, upit1);

        String upit2 = " JOIN bicikla ON bicikla.idBicikla = stavkaiznajmljivanja.idBicikla"
                + " LEFT JOIN biciklazaodrasle ON biciklazaodrasle.idBicikla = stavkaiznajmljivanja.idBicikla"
                + " LEFT JOIN biciklazadecu ON biciklazadecu.idBicikla = stavkaiznajmljivanja.idBicikla"
                + " LEFT JOIN biciklasariksom ON biciklasariksom.idBicikla = stavkaiznajmljivanja.idBicikla"
                + " WHERE stavkaiznajmljivanja.idIznajmljivanje = " + i.getIdIznajmljivanje();

        List<StavkaIznajmljivanja> stavke = broker.getAll(new StavkaIznajmljivanja(), upit2);
        i.setListaStavkiIznajmljivanja(stavke);
        this.iznajmljivanje = i;
    }
}