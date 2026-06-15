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
public class VratiListuProdavacProdavacSO extends ApstraktnaGenerickaOperacija{

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
        String[] imePrezime = ((String) kljuc).strip().split(" ");
        String upit = "";
        if (imePrezime.length == 2) {
            upit += " WHERE (ime='" + imePrezime[0] + "' AND prezime='" + imePrezime[1] + "') OR (ime='" + imePrezime[1] + "' AND prezime='" + imePrezime[0] + "') ORDER BY idProdavac";
        } else {
            upit += " WHERE ime='" + imePrezime[0] + "' OR prezime='" + imePrezime[0] + "' ORDER BY idProdavac";
        }

        List<Prodavac> lista = broker.getAll((Prodavac) objekat, upit);
        this.lista = lista;

    }
    
}
