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
public class KreirajKupacSO extends ApstraktnaGenerickaOperacija {
    private boolean uspesno = false;
    private boolean postoji = false;

    public boolean getUspesno() {
        return uspesno;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof Kupac)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        
        Kupac k = (Kupac) parametar;
        if(k.getIme() == null || k.getIme().isEmpty()){
            throw new Exception("Greška prilikom unosa imena.");
        }
        if(k.getPrezime()== null || k.getPrezime().isEmpty()){
            throw new Exception("Greška prilikom unosa prezimena.");
        }
        if (k.getBrojLicneKarte() == null || k.getBrojLicneKarte().isEmpty()) {
            throw new Exception("Greška prilikom unosa broja lične karte.");
        }
        if (!k.getBrojLicneKarte().matches("\\d{9}")) {
            throw new Exception("Broj lične karte mora sadržati 9 cifara.");
        }

        try {

            String upit = "SELECT * FROM kupac k JOIN mesto m ON k.idMesto = m.idMesto WHERE k.ime='" + ((Kupac) parametar).getIme() + "' AND k.prezime='" + ((Kupac) parametar).getPrezime() + "' AND k.brojLicneKarte='" + ((Kupac) parametar).getBrojLicneKarte() + "' AND m.naziv='" + ((Kupac) parametar).getMesto().getNaziv() + "'";
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
            broker.add((Kupac) objekat);
            uspesno = true;
        }
    }
}
