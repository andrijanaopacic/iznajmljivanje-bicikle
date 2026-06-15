/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.prodavac;

import java.util.List;
import model.Prodavac;
import model.Termin;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class VratiListuProdavacTerminSO extends ApstraktnaGenerickaOperacija{

    private List<Prodavac> lista;

    public List<Prodavac> getLista() {
        return lista;
    }
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Prodavac)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

    }

    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        String upit = " JOIN prodavactermin ON prodavac.idProdavac = prodavactermin.idProdavac JOIN termin ON termin.idTerminDezurstva= prodavactermin.idTermin WHERE termin.idTerminDezurstva = " + ((Termin) kljuc).getIdTerminDezurstva()+ " ORDER BY prodavac.idProdavac";
        List<Prodavac> listaSvihprodavaca = broker.getAll((Prodavac) objekat, upit);
        this.lista = listaSvihprodavaca;
    }
    
}
