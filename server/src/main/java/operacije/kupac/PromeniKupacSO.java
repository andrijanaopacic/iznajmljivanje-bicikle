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
 * Sistemska operacija za izmenu postojeceg kupca.
 * Pre izmene proverava da li kupac sa istim podacima (ime, prezime,
 * broj licne karte i mesto) vec postoji u bazi.
 *
 * @author Andrijana Opacic
 * @see Kupac
 */
public class PromeniKupacSO extends ApstraktnaGenerickaOperacija{

	/** Indikator uspesnosti izmene kupca. */
    private boolean uspesno = false;
    
    /** Indikator da li kupac sa istim podacima vec postoji u bazi podataka. */
    private boolean postoji = false;

    /**
     * Vraca indikator uspesnosti izmene kupca.
     *
     * @return true ako je kupac uspesno izmenjen, false inace
     */
    public boolean getUspesno() {
        return uspesno;
    }
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i
     * da li kupac sa istim podacima (ime, prezime, broj licne karte
     * i mesto) vec postoji u bazi.
     *
     * @param objekat objekat tipa {@link Kupac} koji se izmenjuje
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
         if (objekat == null || !(objekat instanceof Kupac)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

        try {

            String upit = "SELECT * FROM kupac k JOIN mesto m ON k.idMesto = m.idMesto WHERE k.ime='" + ((Kupac) objekat).getIme() + "' AND k.prezime='" + ((Kupac) objekat).getPrezime() + "' AND k.brojLicneKarte='" + ((Kupac) objekat).getBrojLicneKarte() + "' AND m.naziv='" + ((Kupac) objekat).getMesto().getNaziv() + "'";
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {

                postoji = true;
            }

        } catch (SQLException ex) {
            throw ex;
        }
    }

    /**
     * Azurira kupca preko brokera, samo ako ne postoji drugi kupac sa istim podacima.
     *
     * @param objekat objekat tipa {@link Kupac} koji se izmenjuje
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!postoji) {
            broker.edit((Kupac) objekat);
            uspesno = true;
        }
    }
    
}
