/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.termin;

import java.util.List;
import model.Termin;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za vracanje liste termina dezurstva prema nazivu.
 * Ako je unet naziv, vrsi se filtriranje po nazivu, u suprotnom se vracaju svi termini.
 *
 * @author Andrijana Opacic
 * @see Termin
 */
public class VratiListuTerminTerminSO extends ApstraktnaGenerickaOperacija{

	/** Lista termina dobijenih nakon izvrsavanja operacije. */
    private List<Termin> lista;

    /**
     * Vraca listu termina.
     *
     * @return lista termina koja odgovara kriterijumu pretrage
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
     * Izvrsava pretragu termina prema nazivu.
     * Ako je naziv prosledjen, vrsi filtriranje, inace vraca sve termine.
     *
     * @param objekat objekat tipa {@link Termin} koji se koristi za pretragu
     * @param kljuc termin koji sadrzi naziv kao kriterijum pretrage (ako postoji)
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        String upit = "";
        if (((Termin) kljuc).getNaziv() != null) {

            upit += " WHERE termin.naziv = '" + ((Termin) kljuc).getNaziv()+ "'";

        }
        List<Termin> lista = broker.getAll((Termin) objekat, upit);
        this.lista = lista;
    }
    
}
