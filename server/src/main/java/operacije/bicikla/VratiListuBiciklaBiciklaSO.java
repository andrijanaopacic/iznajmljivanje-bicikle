package operacije.bicikla;

import java.util.List;
import model.Bicikla;
import operacije.ApstraktnaGenerickaOperacija;

public class VratiListuBiciklaBiciklaSO extends ApstraktnaGenerickaOperacija {

    private List<Bicikla> lista;

    public List<Bicikla> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Bicikla)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        // vratiNazivTabele() vraca "bicikla JOIN biciklazaodrasle ON ..."
        // pa broker.getAll automatski radi JOIN
        List<Bicikla> lista = broker.getAll((Bicikla) objekat, "");
        this.lista = lista;
    }
}