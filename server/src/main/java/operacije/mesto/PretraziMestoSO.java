/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.mesto;

import model.Mesto;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class PretraziMestoSO extends ApstraktnaGenerickaOperacija{

    private Mesto mesto;

    public Mesto getMesto() {
        return mesto;
    }
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Mesto)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        String upit = " WHERE idMesto = " + ((Mesto) objekat).getIdMesto();
        Mesto mesto = (Mesto) broker.getObject((Mesto) objekat, upit);
        this.mesto = mesto;
    }
    
}
