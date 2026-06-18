/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.prodavac;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Prodavac;
import model.ProdavacTermin;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

/**
 * Sistemska operacija za brisanje prodavca.
 * Pre brisanja proverava da li je prodavac povezan sa postojecim iznajmljivanjima.
 * Ukoliko nije u upotrebi, prvo se brisu povezani termini, a zatim sam prodavac.
 *
 * @author Andrijana Opacic
 * @see Prodavac
 */
public class ObrisiProdavacSO extends ApstraktnaGenerickaOperacija {

	/** Indikator da li je prodavac uspesno obrisan. */
    private boolean uspesno = false;
    
    /** Indikator da li je prodavac povezan sa postojecim iznajmljivanjima. */
    private boolean uUpotrebi = false;

    /**
     * Vraca informaciju o uspesnosti brisanja prodavca.
     *
     * @return true ako je prodavac uspesno obrisan, false u suprotnom
     */
    public boolean getUspesno() {
        return uspesno;
    }
    
    /**
     * Proverava ispravnost prosledjenog objekta i proverava da li je
     * prodavac povezan sa postojecim iznajmljivanjima u bazi podataka.
     *
     * @param objekat objekat tipa {@link Prodavac} koji se proverava
     * @throws Exception ako objekat nije odgovarajuceg tipa ili dodje do greske pri radu sa bazom
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Prodavac)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

        try {
            String upit = "SELECT * FROM iznajmljivanje WHERE idProdavac = " + ((Prodavac) objekat).getIdProdavac();
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {
                uUpotrebi = true;
            }
        } catch (SQLException ex) {
            throw ex;
        }

    }

    /**
     * Brise prodavca ukoliko nije povezan sa postojecim iznajmljivanjima.
     * Pre brisanja prodavca uklanjaju se svi njegovi povezani termini.
     *
     * @param objekat objekat tipa {@link Prodavac} koji se brise
     * @param kljuc dodatni parametar operacije
     * @throws Exception ako dodje do greske prilikom brisanja
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!uUpotrebi) {
            for (ProdavacTermin pt : ((Prodavac) objekat).getProdavacTermini()) {
                broker.delete(pt);
            }
            broker.delete((Prodavac) objekat);
            uspesno = true;
        }

    }
    
}
