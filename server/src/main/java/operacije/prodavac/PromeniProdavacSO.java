/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.prodavac;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import model.Prodavac;
import model.ProdavacTermin;
import model.Termin;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

/**
 *
 * @author HP
 */
public class PromeniProdavacSO extends ApstraktnaGenerickaOperacija {
    
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

        int brTer = ((Prodavac) objekat).getProdavacTermini().size();

        int brIstih = 0;
        String upit = "SELECT * FROM prodavac JOIN prodavactermin ON prodavac.idProdavac = prodavactermin.idProdavac JOIN termin ON termin.idTerminDezurstva = prodavactermin.idTermin WHERE prodavac.ime = '" + ((Prodavac) objekat).getIme() + "' AND prodavac.prezime = '" + ((Prodavac) objekat).getPrezime() + "' AND prodavac.korisnickoIme = '" + ((Prodavac) objekat).getKorisnickoIme() + "' AND prodavac.sifra = '" + ((Prodavac) objekat).getSifra() + "' ORDER BY prodavac.idProdavac";
        System.out.println(upit);
        Statement s = DBKonekcija.getInstance().getConnection().createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            String naziv = rs.getString("termin.naziv");
            Termin t = new Termin(naziv);
            LocalDate datumDezurstva = rs.getDate("datumDezurstva").toLocalDate();
            String smena = rs.getString("smena");

            ProdavacTermin pt = new ProdavacTermin(((Prodavac) objekat), t, datumDezurstva, smena);
            if (((Prodavac) objekat).getProdavacTermini().contains(pt)) {
                brIstih++;
            }
        }
        System.out.println("istii" + brIstih);
        System.out.println("brTer" + brTer);
        if (brIstih == brTer) {
            postoji = true;

        }

    }

    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!postoji) {
            broker.edit((Prodavac) objekat);
            String upit = "DELETE FROM prodavactermin WHERE idProdavac=" + ((Prodavac) objekat).getIdProdavac();
            Statement s = DBKonekcija.getInstance().getConnection().createStatement();
            s.executeUpdate(upit);
            s.close();

            for (ProdavacTermin pt : ((Prodavac) objekat).getProdavacTermini()) {
                pt.setProdavac((Prodavac) objekat);
                broker.add(pt);
            }

            uspesno = true;

        }
    }
}
