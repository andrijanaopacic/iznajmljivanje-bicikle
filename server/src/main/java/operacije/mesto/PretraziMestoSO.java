/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.mesto;

import model.Bicikla;
import model.Mesto;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za pretragu mesta po jedinstvenom identifikatoru.
 *
 * @author Andrijana Opacic
 * @see Mesto
 */
public class PretraziMestoSO extends ApstraktnaGenerickaOperacija{

	 /** Pronadjeno mesto nakon izvrsavanja operacije. */
    private Mesto mesto;

    /**
     * Vraca pronadjeno mesto.
     *
     * @return pronadjeno mesto, ili null ako mesto nije pronadjeno
     */
    public Mesto getMesto() {
        return mesto;
    }
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param objekat objekat tipa {@link Mesto} koji se trazi
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Mesto)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Izvrsava pretragu mesta preko brokera na osnovu ID-a mesta.
     *
     * @param objekat objekat tipa {@link Mesto} koji se trazi
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        String upit = " WHERE idMesto = " + ((Mesto) objekat).getIdMesto();
        Mesto mesto = (Mesto) broker.getObject((Mesto) objekat, upit);
        this.mesto = mesto;
    }
    
}
