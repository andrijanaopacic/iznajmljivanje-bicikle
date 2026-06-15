/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.prodavac;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Prodavac;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

/**
 *
 * @author HP
 */
public class KreirajProdavacSO extends ApstraktnaGenerickaOperacija {

    private boolean uspesno = false;
    private boolean postoji = false;

    public boolean getUspesno() {
        return uspesno;
    }
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Prodavac)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        try {
            String upit = "SELECT * FROM prodavac WHERE ime = '" + ((Prodavac) objekat).getIme() + "' AND prezime = '" + ((Prodavac) objekat).getPrezime() + "' AND korisnickoIme = '" + ((Prodavac) objekat).getKorisnickoIme() + "' AND sifra = '" + ((Prodavac) objekat).getSifra() + "'";
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
            broker.add((Prodavac) objekat);

            uspesno = true;
        }
    }
    
}
