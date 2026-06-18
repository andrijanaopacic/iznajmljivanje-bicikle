/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.kupac;

import java.util.List;
import model.Kupac;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za vracanje liste svih kupaca.
 *
 * @author Andrijana Opacic
 * @see Kupac
 */
public class VratiListuSviKupciSO extends ApstraktnaGenerickaOperacija{

	/** Lista svih kupaca*/
    private List<Kupac> lista;

    /**
     * Vraca listu svih kupaca.
     *
     * @return lista svih kupaca 
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
     * Ucitava sve kupce zajedno sa podacima o mestu.
     *
     * @param objekat objekat tipa {@link Kupac} koji se koristi kao
     *        osnova za pretragu
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        String upit = " JOIN mesto ON kupac.idMesto = mesto.idMesto";
        List<Kupac> lista = broker.getAll((Kupac) objekat, upit);
        this.lista = lista;
        
        System.out.println("UPIT: SELECT * FROM KUPAC "+upit);
        System.out.println("BROJ KUPACA: "+lista.size());
    }
    
}
