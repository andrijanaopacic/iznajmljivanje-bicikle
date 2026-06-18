/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.prodavac;

import java.util.List;
import model.Prodavac;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za vracanje liste prodavaca prema imenu i prezimenu.
 * Omogucava pretragu prodavaca unosom imena, prezimena ili njihove kombinacije.
 *
 * @author Andrijana Opacic
 * @see Prodavac
 */
public class VratiListuProdavacProdavacSO extends ApstraktnaGenerickaOperacija{

	/** Lista prodavaca koji odgovaraju kriterijumu pretrage. */
    private List<Prodavac> lista;

    /**
     * Vraca listu prodavaca dobijenu nakon izvrsavanja operacije.
     *
     * @return lista prodavaca koji odgovaraju zadatoj pretrazi
     */
    public List<Prodavac> getLista() {
        return lista;
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
     * Izvrsava pretragu prodavaca prema unetom imenu i prezimenu.
     * Formira upit na osnovu unetog teksta i vraca listu odgovarajucih prodavaca.
     *
     * @param objekat objekat tipa {@link Prodavac} koji predstavlja osnovu za pretragu
     * @param kljuc tekst po kome se vrsi pretraga prodavca
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
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
