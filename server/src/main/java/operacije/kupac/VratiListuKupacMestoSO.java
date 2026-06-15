/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.kupac;

import java.util.List;
import model.Kupac;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class VratiListuKupacMestoSO extends ApstraktnaGenerickaOperacija{

    private List<Kupac> lista;

    public List<Kupac> getLista() {
        return lista;
    }
    
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Kupac)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

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
