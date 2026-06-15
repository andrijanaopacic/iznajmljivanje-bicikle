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
 *
 * @author HP
 */
public class PretraziProdavacSO extends ApstraktnaGenerickaOperacija {

    private Prodavac prodavac;

    public Prodavac getProdavac() {
        return prodavac;
    }

    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Prodavac)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

    }

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
