/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.kupac;

import model.Kupac;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class PretraziKupacSO extends ApstraktnaGenerickaOperacija{

    private Kupac kupac;

    public Kupac getKupac() {
        return kupac;
    }
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Kupac)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        String upit = " JOIN mesto ON kupac.idMesto = mesto.idMesto WHERE kupac.idKupac = " + ((Kupac) objekat).getIdKupac();
        Kupac kupac = (Kupac) broker.getObject((Kupac) objekat, upit);
        this.kupac = kupac;
    }
    
}
