/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.kupac;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Kupac;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

/**
 * Sistemska operacija za brisanje kupca.
 * Pre brisanja proverava da li kupac ima neko iznajmljivanje, odnosno da li je u upotrebi.
 *
 * @author Andrijana Opacic
 * @see Kupac
 */
public class ObrisiKupacSO extends ApstraktnaGenerickaOperacija{

	/** Indikator uspesnosti brisanja kupca. */
    private boolean uspesno;
    
    /** Indikator da li kupac ima neko iznajmljivanje, odnosno da li je u upotrebi. */
    private boolean uUpotrebi = false;

    /**
     * Vraca indikator uspesnosti brisanja kupca.
     *
     * @return true ako je kupac uspesno obrisan, false inace
     */
    public boolean getUspesno() {
        return uspesno;
    }
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i
     * da li kupac ima neko iznajmljivanje.
     *
     * @param objekat objekat tipa {@link Kupac} koji se brise
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Kupac)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        try {

            String upit = "SELECT * FROM iznajmljivanje WHERE idKupac = " + ((Kupac) objekat).getIdKupac();
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
     * Brise kupca preko brokera, samo ako nije u upotrebi.
     *
     * @param objekat objekat tipa {@link Kupac} koji se brise
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!uUpotrebi) {
            broker.delete((Kupac) objekat);
            uspesno = true;
        }
    }
    
}
