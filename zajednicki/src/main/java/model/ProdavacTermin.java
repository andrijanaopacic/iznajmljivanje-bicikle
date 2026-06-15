/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class ProdavacTermin implements ApstraktniDomenskiObjekat,Serializable{
    
    private Prodavac prodavac;
    private Termin termin;
    private LocalDate datumDezurstva;
    private String smena;

    public ProdavacTermin() {
    }

    public ProdavacTermin(Prodavac prodavac, Termin termin, LocalDate datumDezurstva, String smena) {
        this.prodavac = prodavac;
        this.termin = termin;
        this.datumDezurstva = datumDezurstva;
        this.smena = smena;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }

    


    public LocalDate getDatumDezurstva() {
        return datumDezurstva;
    }

    public void setDatumDezurstva(LocalDate datumDezurstva) {
        this.datumDezurstva = datumDezurstva;
    }

    public String getSmena() {
        return smena;
    }

    public void setSmena(String smena) {
        this.smena = smena;
    }

    @Override
    public String toString() {
        return "ProdavacTermin{" + "datumDezurstva=" + datumDezurstva + ", smena=" + smena + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "prodavactermin";
    }

    @Override
public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
    List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

    while (rs.next()) {
        // Uzimanje podataka iz ResultSet-a
        int idProdavac = rs.getInt("prodavactermin.idProdavac");
        LocalDate datumDezurstva = rs.getDate("prodavactermin.datumDezurstva").toLocalDate();
        String smena = rs.getString("prodavactermin.smena");

        // Kreiranje objekta Prodavac
        String imeProdavac = rs.getString("prodavac.ime");
        String prezimeProdavac = rs.getString("prodavac.prezime");
        String korisnickoIme = rs.getString("prodavac.korisnickoIme");
        String sifra = rs.getString("prodavac.sifra");
        Prodavac prodavac = new Prodavac(idProdavac, imeProdavac, prezimeProdavac, korisnickoIme, sifra);

        // Kreiranje objekta Termin
        int idTermin = rs.getInt("termin.idTerminDezurstva");
        String nazivTermin = rs.getString("termin.naziv");
        Termin termin = new Termin(idTermin, nazivTermin);

        // Kreiranje objekta ProdavacTermin
        ProdavacTermin pt = new ProdavacTermin(prodavac, termin, datumDezurstva, smena);

        // Dodavanje objekta u listu
        lista.add(pt);
    }

    return lista;
}

    
    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idProdavac,idTermin,datumDezurstva,smena";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
           return prodavac.getIdProdavac() + "," + termin.getIdTerminDezurstva() + ",'" + Date.valueOf(datumDezurstva) + "','" + smena + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "prodavactermin.idProdavac=" + prodavac.getIdProdavac() + " AND prodavactermin.idTermin=" + termin.getIdTerminDezurstva() + " AND prodavactermin.datumDezurstva=" + Date.valueOf(datumDezurstva);
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
    
            try {
            ProdavacTermin pt = null;
            while (rs.next()) {

                String smena = rs.getString("prodavactermin.smena");
                LocalDate datumDezurstva = rs.getDate("prodavactermin.datumDezurstva").toLocalDate();
                int idTermin = rs.getInt("prodavactermin.idTermin");
                int idProdavac = rs.getInt("prodavactermin.idProdavac");
                String naziv = rs.getString("termin.naziv");
                String korisnickoIme = rs.getString("prodavac.korisnickoIme");
                String sifra = rs.getString("prodavac.sifra");
                String ime = rs.getString("prodavac.ime");
                String prezime = rs.getString("prodavac.prezime");

                Prodavac p = new Prodavac(idProdavac, ime, prezime, korisnickoIme, sifra);
                Termin t = new Termin(idTermin, naziv);
                pt = new ProdavacTermin(p, t, datumDezurstva, smena);

            }

            return pt;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja prodavac-termin: " + ex.getMessage());
            return null;
        }
    
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "idProdavac = "+prodavac.getIdProdavac() + ",idTerminDezurstva = " + termin.getIdTerminDezurstva() + ",datumDezurstva = '" + Date.valueOf(datumDezurstva) + "',smena = '" + smena + "'";
    }
    
    
}
