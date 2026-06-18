/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.kupac;

import java.util.List;
import model.Kupac;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za vracanje liste kupaca po nazivu mesta.
 * Ukoliko je naziv mesta prazan, vraca sve kupce.
 *
 * @author Andrijana Opacic
 * @see Kupac
 */
public class VratiListuKupacMestoSO extends ApstraktnaGenerickaOperacija{

	/** Lista kupaca koji odgovaraju zadatom mestu. */
    private List<Kupac> lista;

    /**
     * Vraca listu kupaca dobijenu nakon izvrsavanja operacije.
     *
     * @return lista kupaca koji odgovaraju zadatoj pretrazi
     */
    public List<Kupac> getLista() {
        return lista;
    }
    
    /**
     * Proverava da li je prosledjeni parametar odgovarajuceg tipa.
     *
     * @param objekat objekat tipa {@link Kupac} koji se koristi kao
     *        osnova za pretragu
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Kupac)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Filtrira kupce po nazivu mesta (kljuc). Ako je naziv mesta prazan,
     * vraca sve kupce bez filtriranja.
     *
     * @param objekat objekat tipa {@link Kupac} koji se koristi kao
     *        osnova za pretragu
     * @param kljuc naziv mesta (tipa String) po kome se filtrira
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        String nazivMesta = ((String) kljuc).strip();
        String upit = " JOIN mesto ON kupac.idMesto = mesto.idMesto";
        if (!nazivMesta.isEmpty()) {
            upit += " WHERE mesto.naziv='" + nazivMesta + "'";
        } 

        List<Kupac> lista = broker.getAll((Kupac) objekat, upit);
        this.lista = lista;
    }
    
}
