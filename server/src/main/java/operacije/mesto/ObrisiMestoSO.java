/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.mesto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Mesto;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

/**
 *
 * @author HP
 */
public class ObrisiMestoSO extends ApstraktnaGenerickaOperacija{

    private boolean uspesno = false;
    private boolean uUpotrebi = false;

    public boolean getUspesno() {
        return uspesno;
    }
    
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

    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!uUpotrebi) {
            broker.delete((Mesto) objekat);
            uspesno = true;
        }
    }
    
}
