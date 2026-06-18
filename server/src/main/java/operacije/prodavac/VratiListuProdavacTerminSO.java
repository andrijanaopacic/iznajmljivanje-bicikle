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
 * Sistemska operacija za vracanje liste prodavaca prema terminu.
 * Pronalazi sve prodavce koji su povezani sa zadatim terminom dezurstva.
 *
 * @author Andrijana Opacic
 * @see Prodavac
 */
public class VratiListuProdavacTerminSO extends ApstraktnaGenerickaOperacija{

	/** Lista prodavaca koji imaju zadati termin. */
    private List<Prodavac> lista;

    /**
     * Vraca listu prodavaca dobijenu nakon izvrsavanja operacije.
     *
     * @return lista prodavaca povezanih sa zadatim terminom
     */
    public List<Prodavac> getLista() {
        return lista;
    }
    
    /**
     * Proverava da li je prosledjeni objekat odgovarajuceg tipa.
     *
     * @param objekat objekat tipa {@link Prodavac} koji predstavlja osnovu pretrage
     * @throws Exception ako objekat nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Prodavac)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

    }

    /**
     * Izvrsava pretragu prodavaca koji su povezani sa zadatim terminom.
     * Koristi vezu izmedju prodavca, termina i tabele prodavactermin kako bi pronasao odgovarajuce prodavce.
     *
     * @param objekat objekat tipa {@link Prodavac} koji se koristi za pretragu
     * @param kljuc objekat tipa {@link Termin} koji predstavlja kriterijum pretrage
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        String upit = " JOIN prodavactermin ON prodavac.idProdavac = prodavactermin.idProdavac JOIN termin ON termin.idTerminDezurstva= prodavactermin.idTermin WHERE termin.idTerminDezurstva = " + ((Termin) kljuc).getIdTerminDezurstva()+ " ORDER BY prodavac.idProdavac";
        List<Prodavac> listaSvihprodavaca = broker.getAll((Prodavac) objekat, upit);
        this.lista = listaSvihprodavaca;
    }
    
}
