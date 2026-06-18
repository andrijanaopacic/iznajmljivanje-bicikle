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
 * Sistemska operacija za prijavu prodavca na sistem.
 * Proverava ispravnost unetih podataka, a zatim pronalazi prodavca
 * na osnovu korisnickog imena i sifre.
 *
 * @author Andrijana Opacic
 * @see Prodavac
 */
public class PrijaviProdavacSO extends ApstraktnaGenerickaOperacija {

	/** Prodavac koji je uspesno prijavljen. */
    private Prodavac prodavac = null;
    
    /**
     * Vraca prodavca koji je uspesno prijavljen.
     *
     * @return prijavljeni prodavac ili null ako prijava nije uspesna
     */
    public Prodavac getProdavac() {
        return prodavac;
    }

    /**
     * Proverava da li je prosledjeni objekat odgovarajuceg tipa
     * i da li su uneti obavezni podaci za prijavu.
     *
     * @param objekat objekat tipa {@link Prodavac} koji sadrzi podatke za prijavu
     * @throws Exception ako objekat nije odgovarajuceg tipa ili nisu uneti potrebni podaci
     */
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

    /**
     * Pronalazi prodavca u bazi podataka na osnovu korisnickog imena i sifre.
     * Ako postoji prodavac sa odgovarajucim podacima, cuva se kao rezultat operacije.
     *
     * @param objekat objekat tipa {@link Prodavac} koji sadrzi podatke za prijavu
     * @param kljuc dodatni parametar operacije
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
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
    
}
