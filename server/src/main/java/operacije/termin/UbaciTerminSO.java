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
public class UbaciTerminSO extends ApstraktnaGenerickaOperacija{

    private boolean uspesno = false;
    private boolean postoji = false;

    public boolean getUspesno() {
        return uspesno;
    }
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
       if (objekat == null || !(objekat instanceof Termin)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

        try {

            String upit = "SELECT * FROM termin WHERE naziv='" + ((Termin) objekat).getNaziv()+ "'";
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {
                postoji = true;

            }

        } catch (SQLException ex) {
            throw ex;
        }

    }

    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!postoji) {
            broker.add((Termin) objekat);
            uspesno = true;
        }
    }
    
}
