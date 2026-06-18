/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.kupac;

import java.util.List;
import model.Kupac;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za vracanje liste kupaca na osnovu imena i/ili
 * prezimena, bez obzira na velika i mala slova.
 *
 * @author Andrijana Opacic
 * @see Kupac
 */
public class VratiListuKupacKupacSO extends ApstraktnaGenerickaOperacija{

	/** Lista kupaca koji odgovaraju zadatom imenu i/ili prezimenu. */
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
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
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
     * Filtrira kupce po imenu i/ili prezimenu (kljuc), bez obzira na velika
     * i mala slova. Ako kljuc sadrzi dve reci, uzima u obzir oba redosleda
     * ime-prezime.
     *
     * @param objekat objekat tipa {@link Kupac} koji se koristi kao
     *        osnova za pretragu
     * @param kljuc ime i/ili prezime kupca (tipa String) po kome se filtrira
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        String upit = " JOIN mesto ON kupac.idMesto = mesto.idMesto";
        String[] imePrezime = ((String) kljuc).strip().split(" ");
        if (imePrezime.length == 2) {
            upit += " WHERE (LOWER(ime)=LOWER('" + imePrezime[0] + "') AND LOWER(prezime)=LOWER('" + imePrezime[1] + "')) OR (LOWER(ime)=LOWER('" + imePrezime[1] + "') AND LOWER(prezime)=LOWER('" + imePrezime[0] + "')) ";
        } else {
            upit += " WHERE LOWER(ime)=LOWER('" + imePrezime[0] + "') OR LOWER(prezime)=LOWER('" + imePrezime[0] + "')";
        }

        List<Kupac> lista = broker.getAll((Kupac) objekat, upit);
        this.lista = lista;
    }
    
}
