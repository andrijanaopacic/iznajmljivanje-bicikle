/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.termin;

import java.util.List;
import model.Termin;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za vracanje liste svih termina dezurstva.
 * Dohvata sve termine iz baze podataka bez dodatnih filtera.
 *
 * @author Andrijana Opacic
 * @see Termin
 */
public class VratiListuSviTerminiSO extends ApstraktnaGenerickaOperacija {

	/** Lista svih termina dobijenih iz baze. */
    private List<Termin> lista;

    /**
     * Vraca listu svih termina.
     *
     * @return lista svih termina iz baze
     */
    public List<Termin> getLista() {
        return lista;
    }
    
    /**
     * Proverava da li je prosledjeni objekat odgovarajuceg tipa.
     *
     * @param objekat objekat tipa {@link Termin}
     * @throws Exception ako objekat nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Termin)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Izvrsava ucitavanje svih termina iz baze podataka.
     *
     * @param objekat objekat tipa {@link Termin} koji se koristi za pretragu
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        
        List<Termin> lista = broker.getAll((Termin) objekat);
        this.lista = lista;
    }
    
}
