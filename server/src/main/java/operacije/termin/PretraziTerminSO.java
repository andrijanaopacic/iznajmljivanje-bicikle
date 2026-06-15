/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.termin;

import model.Termin;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class PretraziTerminSO extends ApstraktnaGenerickaOperacija{

    private Termin termin;

    public Termin getTermin() {
        return termin;
    }
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Termin)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }


    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        String upit = " WHERE idTerminDezurstva = " + ((Termin) objekat).getIdTerminDezurstva();
        Termin termin = (Termin) broker.getObject((Termin) objekat, upit);
        this.termin = termin;
    }
    
}
