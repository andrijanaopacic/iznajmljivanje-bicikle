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
 *
 * @author HP
 */
public class ObrisiKupacSO extends ApstraktnaGenerickaOperacija{

    private boolean uspesno;
    private boolean uUpotrebi = false;

    public boolean getUspesno() {
        return uspesno;
    }
    
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

    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!uUpotrebi) {
            broker.delete((Kupac) objekat);
            uspesno = true;
        }
    }
    
}
