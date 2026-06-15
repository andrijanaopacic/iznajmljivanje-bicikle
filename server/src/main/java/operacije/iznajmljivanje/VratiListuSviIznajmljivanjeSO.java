package operacije.iznajmljivanje;

import java.util.List;
import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;
import operacije.ApstraktnaGenerickaOperacija;

public class VratiListuSviIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

    private List<Iznajmljivanje> lista;

    public List<Iznajmljivanje> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Iznajmljivanje)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

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