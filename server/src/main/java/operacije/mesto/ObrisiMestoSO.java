/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.mesto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Bicikla;
import model.Mesto;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

/**
 * Sistemska operacija za brisanje mesta.
 * Pre brisanja proverava da li je mesto u upotrebi, odnosno da li
 * postoji kupac koja referencira to mesto.
 *
 * @author Andrijana Opacic
 * @see Mesto
 */
public class ObrisiMestoSO extends ApstraktnaGenerickaOperacija{

	/** Indikator uspesnosti brisanja mesta. */
    private boolean uspesno = false;
    
    /** Indikator da li je mesto u upotrebi, odnosno da li postoji kupac koji ga referencira. */
    private boolean uUpotrebi = false;

    /**
     * Vraca indikator uspesnosti brisanja mesta.
     *
     * @return true ako je mesto uspesno obrisana, false inace
     */
    public boolean getUspesno() {
        return uspesno;
    }
    
    /**
     * Proverava preduslove pre brisanja mesta.
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i
     * da li postoji kupac koja referencira to mesto,
     * odnosno da li je mesto u upotrebi.
     *
     * @param objekat objekat tipa {@link Mesto} koji se brise
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
       if (objekat == null || !(objekat instanceof Mesto)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        try {

            String upit = "SELECT * FROM kupac WHERE idMesto = " + ((Mesto) objekat).getIdMesto();
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
     * Izvrsava brisanje mesta iz baze podataka.
     * Mesto se brise samo ako nije u upotrebi. 
     * 
     * @param objekat objekat tipa {@link Mesto} koji se brise
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!uUpotrebi) {
            broker.delete((Mesto) objekat);
            uspesno = true;
        }
    }
    
}
