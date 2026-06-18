/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.prodavac;

import java.util.List;
import model.Prodavac;
import model.ProdavacTermin;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za pretragu prodavca prema identifikatoru.
 * Nakon pronalaska prodavca, ucitavaju se i njegovi povezani termini.
 *
 * @author Andrijana Opacic
 * @see Prodavac
 */
public class PretraziProdavacSO extends ApstraktnaGenerickaOperacija {

	/** Pronadjeni prodavac nakon izvrsavanja operacije. */
    private Prodavac prodavac;

    /**
     * Vraca pronadjenog prodavca.
     *
     * @return objekat tipa {@link Prodavac} ako postoji, null u suprotnom
     */
    public Prodavac getProdavac() {
        return prodavac;
    }

    /**
     * Proverava da li je prosledjeni objekat odgovarajuceg tipa.
     *
     * @param objekat objekat tipa {@link Prodavac} koji se koristi za pretragu
     * @throws Exception ako objekat nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Prodavac)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

    }

    /**
     * Pretrazuje prodavca prema njegovom identifikatoru.
     * Nakon pronalaska prodavca, ucitava listu njegovih termina.
     *
     * @param objekat objekat tipa {@link Prodavac} koji sadrzi kriterijum pretrage
     * @param kljuc dodatni parametar operacije
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        String upit1 = " WHERE idProdavac = " + ((Prodavac) objekat).getIdProdavac();
        Prodavac prodavac = (Prodavac) broker.getObject(objekat, upit1);

        if (prodavac != null) {
            String upit2 = " JOIN prodavac ON prodavac.idProdavac = prodavactermin.idProdavac JOIN termin ON termin.idTerminDezurstva = prodavactermin.idTermin WHERE prodavac.idProdavac = " + ((Prodavac) objekat).getIdProdavac();
            List<ProdavacTermin> prodavacTermini = (List<ProdavacTermin>) broker.getAll(new ProdavacTermin(), upit2);
            prodavac.setProdavacTermini(prodavacTermini);
        }
        this.prodavac = prodavac;
    }
}
