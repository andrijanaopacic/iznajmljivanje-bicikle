package operacije.bicikla;

import java.util.ArrayList;
import java.util.List;
import model.Bicikla;
import model.BiciklaZaDecu;
import model.BiciklaZaOdrasle;
import model.BiciklaSaRiksom;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za vracanje liste svih bicikli u sistemu,
 * bez obzira na konkretan tip. Objedinjuje bicikle za odrasle,
 * bicikle za decu i bicikle sa riksom u jednu listu.
 *
 * @author Andrijana Opacic
 * @see BiciklaZaOdrasle
 * @see BiciklaZaDecu
 * @see BiciklaSaRiksom
 */
public class VratiListuSviBiciklaSO extends ApstraktnaGenerickaOperacija {

    /** Lista svih bicikli, svih konkretnih tipova, dobijena nakon izvrsavanja operacije. */
    private List<Bicikla> lista;

    /**
     * Vraca listu svih bicikli dobijenu nakon izvrsavanja operacije.
     *
     * @return lista svih bicikli, svih konkretnih tipova
     */
    public List<Bicikla> getLista() {
        return lista;
    }

    /**
     * Ova operacija nema preduslove - moze se izvrsiti bez obzira na
     * prosledjeni parametar.
     *
     * @param objekat nije koriscen u ovoj operaciji
     * @throws Exception ne baca se u ovoj implementaciji
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    /**
     * Izvrsava vracanje liste svih bicikli preko brokera, objedinjujuci
     * bicikle za odrasle, bicikle za decu i bicikle sa riksom u jednu listu.
     *
     * @param objekat nije koriscen u ovoj operaciji
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        lista = new ArrayList<>();
        lista.addAll(broker.getAll(new BiciklaZaOdrasle(), ""));
        lista.addAll(broker.getAll(new BiciklaZaDecu(), ""));
        lista.addAll(broker.getAll(new BiciklaSaRiksom(), ""));
    }
}