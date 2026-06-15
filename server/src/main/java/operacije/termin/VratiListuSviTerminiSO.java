/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.termin;

import java.util.List;
import model.Termin;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class VratiListuSviTerminiSO extends ApstraktnaGenerickaOperacija {

    private List<Termin> lista;

    public List<Termin> getLista() {
        return lista;
    }
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Termin)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        
        List<Termin> lista = broker.getAll((Termin) objekat);
        this.lista = lista;
    }
    
}
