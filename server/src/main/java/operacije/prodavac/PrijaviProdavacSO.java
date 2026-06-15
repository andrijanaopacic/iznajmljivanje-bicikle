/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.prodavac;

import java.util.ArrayList;
import java.util.List;
import model.Prodavac;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class PrijaviProdavacSO extends ApstraktnaGenerickaOperacija {

    private Prodavac prodavac = null;

    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
       if(objekat == null || !(objekat instanceof Prodavac)){
           throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
       }
       
       Prodavac p = (Prodavac) objekat;
       if(p.getKorisnickoIme() == null || p.getKorisnickoIme().trim().isEmpty()){
            throw new Exception("Greška prilikom unosa korisničkog imena.");
       }
        if(p.getSifra()  == null  || p.getSifra().trim().isEmpty()){
            throw new Exception("Greška prilikom unosa šifre.");
        }
    }

    @Override
    public void izvrsi(Object objekat, Object kljuc) throws Exception {
        List<Prodavac> sviProdavci = new ArrayList<>();
                
        sviProdavci = broker.getAll((Prodavac) objekat,null);
        
        System.out.println("KLASA LOGIN OPERACIJA SO " +sviProdavci);
        
        for (Prodavac p : sviProdavci){
          if(p.getKorisnickoIme().equals(((Prodavac)objekat).getKorisnickoIme()) 
                  && p.getSifra().equals(((Prodavac)objekat).getSifra())){
              prodavac = p;
              return;
          }
        }
        prodavac = null;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }
    
}
