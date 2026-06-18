package operacije.iznajmljivanje;

import java.util.List;
import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za vracanje liste iznajmljivanja na osnovu zadatog
 * ID-a iznajmljivanja. Iako po nazivu i logici filtrira po jednom ID-u,
 * vraca listu (umesto jednog objekta) jer prati isti generic obrazac kao
 * ostale "VratiListu" operacije.
 *
 * @author Andrijana Opacic
 * @see Iznajmljivanje
 */
public class VratiListuIznajmljivanjeIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

	/** Lista iznajmljivanja dobijena nakon izvrsavanja operacije. */
    private List<Iznajmljivanje> lista;

    /**
     * Vraca listu iznajmljivanja dobijenu nakon izvrsavanja operacije.
     *
     * @return lista iznajmljivanja, sa popunjenim listama stavki
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
     * Izvrsava vracanje liste iznajmljivanja sa zadatim ID-jem (prosledjenim
     * kao kljuc), zajedno sa podacima o prodavcu, kupcu i mestu. Za svako
     * pronadjeno iznajmljivanje, ucitavaju se i sve njegove stavke, vodeci
     * racuna o svim mogucim tipovima bicikle.
     *
     * @param objekat objekat tipa {@link Iznajmljivanje} koji se koristi
     *        kao osnova za pretragu
     * @param kljuc ID iznajmljivanja (tipa int) po kome se filtrira
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        String upit1 = " JOIN prodavac ON prodavac.idProdavac = iznajmljivanje.idProdavac"
                + " JOIN kupac ON kupac.idKupac = iznajmljivanje.idKupac"
                + " JOIN mesto ON mesto.idMesto = kupac.idMesto"
                + " JOIN stavkaiznajmljivanja ON stavkaiznajmljivanja.idIznajmljivanje = iznajmljivanje.idIznajmljivanje"
                + " JOIN bicikla ON bicikla.idBicikla = stavkaiznajmljivanja.idBicikla"
                + " LEFT JOIN biciklazaodrasle ON biciklazaodrasle.idBicikla = stavkaiznajmljivanja.idBicikla"
                + " LEFT JOIN biciklazadecu ON biciklazadecu.idBicikla = stavkaiznajmljivanja.idBicikla"
                + " LEFT JOIN biciklasariksom ON biciklasariksom.idBicikla = stavkaiznajmljivanja.idBicikla"
                + " WHERE iznajmljivanje.idIznajmljivanje = " + (int) kljuc;

        List<Iznajmljivanje> lista = broker.getAll((Iznajmljivanje) objekat, upit1);

        for (Iznajmljivanje iznajmljivanje : lista) {
            String upit2 = " JOIN bicikla ON bicikla.idBicikla = stavkaiznajmljivanja.idBicikla"
                    + " LEFT JOIN biciklazaodrasle ON biciklazaodrasle.idBicikla = stavkaiznajmljivanja.idBicikla"
                    + " LEFT JOIN biciklazadecu ON biciklazadecu.idBicikla = stavkaiznajmljivanja.idBicikla"
                    + " LEFT JOIN biciklasariksom ON biciklasariksom.idBicikla = stavkaiznajmljivanja.idBicikla"
                    + " WHERE stavkaiznajmljivanja.idIznajmljivanje = " + iznajmljivanje.getIdIznajmljivanje();
            List<StavkaIznajmljivanja> stavke = broker.getAll(new StavkaIznajmljivanja(), upit2);
            iznajmljivanje.setListaStavkiIznajmljivanja(stavke);
        }

        this.lista = lista;
    }
}