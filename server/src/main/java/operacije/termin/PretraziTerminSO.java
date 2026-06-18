/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.termin;

import model.Termin;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za pretragu termina dezurstva po identifikatoru.
 * Vraca jedan termin na osnovu prosledjenog ID-a.
 *
 * @author Andrijana Opacic
 * @see Termin
 */
public class PretraziTerminSO extends ApstraktnaGenerickaOperacija{

	/** Pronadjeni termin nakon izvrsavanja operacije. */
    private Termin termin;

    /**
     * Vraca pronadjeni termin.
     *
     * @return objekat tipa {@link Termin} ako postoji, inace null
     */
    public Termin getTermin() {
        return termin;
    }
    
    /**
     * Proverava da li je prosledjeni objekat odgovarajuceg tipa.
     *
     * @param objekat objekat tipa {@link Termin} koji se koristi za pretragu
     * @throws Exception ako objekat nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Termin)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Pretrazuje termin dezurstva po njegovom identifikatoru.
     * Rezultat se cuva u atributu termin.
     *
     * @param objekat objekat tipa {@link Termin} koji sadrzi kriterijum pretrage
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        String upit = " WHERE idTerminDezurstva = " + ((Termin) objekat).getIdTerminDezurstva();
        Termin termin = (Termin) broker.getObject((Termin) objekat, upit);
        this.termin = termin;
    }
    
}
