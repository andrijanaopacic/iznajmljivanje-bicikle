/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.prodavac;

import java.util.List;
import model.Prodavac;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za vracanje liste svih prodavaca.
 *
 * @author Andrijana Opacic
 * @see Prodavac
 */ 
public class VratiListuSviProdavciSO extends ApstraktnaGenerickaOperacija {
	 
	 /** Lista svih prodavaca dobijenih nakon izvrsavanja operacije. */
    private List<Prodavac> lista;

    /**
     * Vraca listu svih prodavaca.
     *
     * @return lista svih prodavaca
     */
    public List<Prodavac> getLista() {
        return lista;
    }

    /**
     * Proverava da li je prosledjeni objekat odgovarajuceg tipa.
     *
     * @param parametar objekat tipa {@link Prodavac}
     * @throws Exception ako objekat nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof Prodavac)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Izvrsava ucitavanje svih prodavaca iz baze podataka.
     *
     * @param objekat objekat tipa {@link Prodavac} koji se koristi za pretragu
     * @param kljuc dodatni parametar operacije
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        List<Prodavac> lista = broker.getAll((Prodavac) objekat);
        this.lista = lista;
    }
}
