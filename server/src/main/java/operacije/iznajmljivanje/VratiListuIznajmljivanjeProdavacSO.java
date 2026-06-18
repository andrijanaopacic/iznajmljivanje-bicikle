package operacije.iznajmljivanje;

import java.util.List;
import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za vracanje liste iznajmljivanja na osnovu imena
 * i/ili prezimena prodavca.
 *
 * @author Andrijana Opacic
 * @see Iznajmljivanje
 */
public class VratiListuIznajmljivanjeProdavacSO extends ApstraktnaGenerickaOperacija {

	/** Lista iznajmljivanja koja odgovaraju zadatom imenu i/ili prezimenu prodavca. */
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
     * Filtrira iznajmljivanja po imenu i/ili prezimenu prodavca (kljuc).
     * Ako kljuc sadrzi dve reci, uzima u obzir oba redosleda ime-prezime.
     * Za svako pronadjeno iznajmljivanje ucitava i njegove stavke.
     *
     * @param objekat objekat tipa {@link Iznajmljivanje} koji se koristi
     *        kao osnova za pretragu
     * @param kljuc ime i/ili prezime prodavca (tipa String) po kome se filtrira
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        String[] imePrezime = ((String) kljuc).strip().split(" ");

        String upit1 = " JOIN prodavac ON prodavac.idProdavac = iznajmljivanje.idProdavac"
                + " JOIN kupac ON kupac.idKupac = iznajmljivanje.idKupac"
                + " JOIN mesto ON mesto.idMesto = kupac.idMesto"
                + " JOIN stavkaiznajmljivanja ON stavkaiznajmljivanja.idIznajmljivanje = iznajmljivanje.idIznajmljivanje"
                + " JOIN bicikla ON bicikla.idBicikla = stavkaiznajmljivanja.idBicikla"
                + " LEFT JOIN biciklazaodrasle ON biciklazaodrasle.idBicikla = stavkaiznajmljivanja.idBicikla"
                + " LEFT JOIN biciklazadecu ON biciklazadecu.idBicikla = stavkaiznajmljivanja.idBicikla"
                + " LEFT JOIN biciklasariksom ON biciklasariksom.idBicikla = stavkaiznajmljivanja.idBicikla";

        if (imePrezime.length == 2) {
            upit1 += " WHERE (prodavac.ime = '" + imePrezime[0] + "' AND prodavac.prezime = '" + imePrezime[1] + "')"
                    + " OR (prodavac.ime = '" + imePrezime[1] + "' AND prodavac.prezime = '" + imePrezime[0] + "')";
        } else {
            upit1 += " WHERE prodavac.ime = '" + imePrezime[0] + "' OR prodavac.prezime = '" + imePrezime[0] + "'";
        }

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