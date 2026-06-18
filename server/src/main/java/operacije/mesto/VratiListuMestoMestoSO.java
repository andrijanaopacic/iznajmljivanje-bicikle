/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.mesto;

import java.util.List;

import model.Bicikla;
import model.Kupac;
import model.Mesto;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za pretragu i vracanje liste mesta prema nazivu mesta.
 *
 * @author Andrijana Opacic
 * @see Mesto
 */
public class VratiListuMestoMestoSO extends ApstraktnaGenerickaOperacija{

	/** Lista mesta koja odgovaraju zadatom nazivu. */
    private List<Mesto> lista;

    /**
     * Vraca listu mesta dobijenu nakon izvrsavanja operacije.
     *
     * @return lista mesta koji odgovaraju zadatoj pretrazi
     */
    public List<Mesto> getLista() {
        return lista;
    }
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
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
     * Izvrsava pretragu mesta prema prosledjenom nazivu.
     *
     * @param objekat objekat tipa {@link Mesto} koji se koristi kao
     *        osnova za pretragu
     * @param kljuc objekat tipa {@link Mesto} koji sadrzi kriterijum pretrage
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        String upit = "";

        String naziv = ((Mesto) kljuc).getNaziv();

        if (naziv != null && !naziv.trim().isEmpty()) {
            upit += " WHERE mesto.naziv = '" + naziv.trim() + "'";
        }

        List<Mesto> lista = broker.getAll((Mesto) objekat, upit);
        this.lista = lista;
}

    
}
