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
 *
 * @author HP
 */
public class ObrisiTerminSO extends ApstraktnaGenerickaOperacija{

    private boolean uspesno = false;
    private boolean uUpotrebi = false;

    public boolean getUspesno() {
        return uspesno;
    }
    
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

    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!uUpotrebi) {
            broker.delete((Termin) objekat);
            uspesno = true;
        }
    }
    
}
