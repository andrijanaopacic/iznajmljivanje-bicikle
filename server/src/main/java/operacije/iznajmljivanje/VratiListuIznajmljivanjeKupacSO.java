package operacije.iznajmljivanje;

import java.util.List;
import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za vracanje liste iznajmljivanja na osnovu imena
 * i/ili prezimena kupca. Ukoliko je prosledjena jedna rec, pretraga se
 * vrsi i po imenu i po prezimenu kupca. Ukoliko su prosledjene dve reci,
 * pretraga uzima u obzir oba moguca redosleda (ime-prezime i prezime-ime).
 *
 * @author Andrijana Opacic
 * @see Iznajmljivanje
 */
public class VratiListuIznajmljivanjeKupacSO extends ApstraktnaGenerickaOperacija {

	/** Lista iznajmljivanja koja odgovaraju zadatom imenu i/ili prezimenu kupca. */
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
     * Proverava preduslove pre vracanja liste iznajmljivanja.
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
     * Izvrsava vracanje liste iznajmljivanja ciji kupac odgovara zadatom
     * imenu i/ili prezimenu (prosledjenom kao kljuc), zajedno sa podacima
     * o prodavcu, kupcu i mestu. Ukoliko kljuc sadrzi dve reci razdvojene
     * razmakom, pretpostavlja se da predstavljaju ime i prezime, i pretraga
     * uzima u obzir oba moguca redosleda. Ukoliko sadrzi samo jednu rec,
     * pretraga se vrsi i po imenu i po prezimenu kupca. Za svako pronadjeno
     * iznajmljivanje, ucitavaju se i sve njegove stavke, vodeci racuna o
     * svim mogucim tipovima bicikle.
     *
     * @param objekat objekat tipa {@link Iznajmljivanje} koji se koristi
     *        kao osnova za pretragu
     * @param kljuc ime i/ili prezime kupca (tipa String) po kome se filtrira
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
            upit1 += " WHERE (kupac.ime = '" + imePrezime[0] + "' AND kupac.prezime = '" + imePrezime[1] + "')"
                    + " OR (kupac.ime = '" + imePrezime[1] + "' AND kupac.prezime = '" + imePrezime[0] + "')";
        } else {
            upit1 += " WHERE kupac.ime = '" + imePrezime[0] + "' OR kupac.prezime = '" + imePrezime[0] + "'";
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