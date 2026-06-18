/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.mesto;

import java.util.List;

import model.BiciklaSaRiksom;
import model.BiciklaZaDecu;
import model.BiciklaZaOdrasle;
import model.Kupac;
import model.Mesto;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za vracanje liste svih mesta u sistemu.
 *
 * @author Andrijana Opacic
 * @see Mesto
 */
public class VratiListuSviMestoSO extends ApstraktnaGenerickaOperacija{

	/** Lista svih mesta*/
    private List<Mesto> lista;

    /**
     * Vraca listu svih mesta dobijenu nakon izvrsavanja operacije.
     *
     * @return lista svih mesta, svih konkretnih tipova
     */
    public List<Mesto> getLista() {
        return lista;
    }
    
    /**
     * Proverava da li je prosledjeni parametar odgovarajuceg tipa.
     *
     * @param objekat objekat tipa {@link Mesto} koji se koristi kao
     *        osnova za pretragu
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Mesto)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Ucitava sva mesta.
     *
     * @param objekat objekat tipa {@link Mesto} koji se koristi kao
     *        osnova za pretragu
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        List<Mesto> lista = broker.getAll((Mesto) objekat);
        this.lista = lista;
    }
    
}
