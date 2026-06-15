/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.prodavac;

import java.util.List;
import model.Prodavac;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class VratiListuSviProdavciSO extends ApstraktnaGenerickaOperacija {
    private List<Prodavac> lista;

    public List<Prodavac> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof Prodavac)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        List<Prodavac> lista = broker.getAll((Prodavac) objekat);
        this.lista = lista;
    }
}
