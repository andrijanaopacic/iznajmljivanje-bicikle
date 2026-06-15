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
 *
 * @author HP
 */
public class ObrisiProdavacSO extends ApstraktnaGenerickaOperacija {

    private boolean uspesno = false;
    private boolean uUpotrebi = false;

    public boolean getUspesno() {
        return uspesno;
    }
    
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
