/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.termin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Termin;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

/**
 * Sistemska operacija za brisanje termina dezurstva.
 * Pre brisanja proverava da li je termin povezan sa nekim prodavcem.
 * Termin se brise samo ukoliko nije u upotrebi.
 *
 * @author Andrijana Opacic
 * @see Termin
 */
public class ObrisiTerminSO extends ApstraktnaGenerickaOperacija{

	/** Indikator da li je termin uspesno obrisan. */
    private boolean uspesno = false;
    
    /** Indikator da li je termin povezan sa nekim prodavcem. */
    private boolean uUpotrebi = false;

    /**
     * Vraca informaciju o uspesnosti brisanja termina.
     *
     * @return true ako je termin uspesno obrisan, false u suprotnom
     */
    public boolean getUspesno() {
        return uspesno;
    }
    
    /**
     * Proverava da li je prosledjeni objekat odgovarajuceg tipa.
     * Takodje proverava da li je termin povezan sa nekim prodavcem preko veze prodavac-termin.
     *
     * @param objekat objekat tipa {@link Termin} koji se proverava
     * @throws Exception ako objekat nije odgovarajuceg tipa ili dodje do greske pri radu sa bazom
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Termin)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        try {

            String upit = "SELECT * FROM prodavactermin pt JOIN termin t ON t.idTerminDezurstva = pt.idTermin WHERE t.idTerminDezurstva = " + ((Termin) objekat).getIdTerminDezurstva();
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
     * Brise termin ukoliko nije povezan sa prodavcima.
     *
     * @param objekat objekat tipa {@link Termin} koji se brise
     * @param kljuc dodatni parametar operacije
     * @throws Exception ako dodje do greske prilikom brisanja
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!uUpotrebi) {
            broker.delete((Termin) objekat);
            uspesno = true;
        }
    }
    
}
