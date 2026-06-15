/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.mesto;

import java.util.List;
import model.Mesto;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class VratiListuSviMestoSO extends ApstraktnaGenerickaOperacija{

    private List<Mesto> lista;

    public List<Mesto> getLista() {
        return lista;
    }
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Mesto)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        List<Mesto> lista = broker.getAll((Mesto) objekat);
        this.lista = lista;
    }
    
}
