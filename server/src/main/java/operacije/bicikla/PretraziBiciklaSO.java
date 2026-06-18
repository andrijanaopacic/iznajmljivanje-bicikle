package operacije.bicikla;

import model.Bicikla;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za pretragu bicikle po jedinstvenom identifikatoru.
 *
 * @author Andrijana Opacic
 * @see Bicikla
 */
public class PretraziBiciklaSO extends ApstraktnaGenerickaOperacija {

	 /** Pronadjena bicikla nakon izvrsavanja operacije. */
    private Bicikla bicikla;

    /**
     * Vraca pronadjenu biciklu.
     *
     * @return pronadjena bicikla, ili null ako bicikla nije pronadjena
     */
    public Bicikla getBicikla() {
        return bicikla;
    }

    /**
     * Proverava preduslove pre pretrage bicikle.
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i
     * da li je ID bicikle ispravan (veci od 0).
     *
     * @param objekat objekat tipa {@link Bicikla} koji se trazi
     * @throws Exception ako parametar nije odgovarajuceg tipa, ili ako
     *         ID bicikle nije ispravan
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Bicikla)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        Bicikla bicikla = (Bicikla) objekat;
        if (bicikla.getIdBicikla() <= 0) {
            throw new Exception("ID bicikle nije ispravan.");
        }
    }

    /**
     * Izvrsava pretragu bicikle preko brokera na osnovu ID-a bicikle.
     *
     * @param objekat objekat tipa {@link Bicikla} koji se trazi
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        Bicikla bicikla = (Bicikla) objekat;
        String upit = " WHERE bicikla.idBicikla = " + bicikla.getIdBicikla();
        this.bicikla = (Bicikla) broker.getObject(bicikla, upit);
    }
}