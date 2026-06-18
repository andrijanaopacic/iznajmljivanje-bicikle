/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.kupac;

import model.Kupac;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za pretragu kupca po jedinstvenom identifikatoru.
 *
 * @author Andrijana Opacic
 * @see Kupac
 */
public class PretraziKupacSO extends ApstraktnaGenerickaOperacija{

	/** Pronadjeni kupac nakon izvrsavanja operacije. */
    private Kupac kupac;

    /**
     * Vraca pronadjenog kupca.
     *
     * @return pronadjeni kupac, ili null ako kupac nije pronadjen
     */
    public Kupac getKupac() {
        return kupac;
    }
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param objekat objekat tipa {@link Kupac} koji se trazi
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Kupac)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Trazi kupca preko brokera na osnovu ID-a kupca, ucitavajuci i podatke
     * o mestu.
     *
     * @param objekat objekat tipa {@link Kupac} koji se trazi
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        String upit = " JOIN mesto ON kupac.idMesto = mesto.idMesto WHERE kupac.idKupac = " + ((Kupac) objekat).getIdKupac();
        Kupac kupac = (Kupac) broker.getObject((Kupac) objekat, upit);
        this.kupac = kupac;
    }
    
}
