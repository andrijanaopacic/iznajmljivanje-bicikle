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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Predstavlja dezurstvo prodavca u odredjenom terminu i na odredjeni datum.
 * Povezuje {@link Prodavac} i {@link Termin} sa konkretnim datumom i smenom dezurstva.
 *
 * @author Andrijana Opacic
 * @see Prodavac
 * @see Termin
 */
@Getter
@Setter
@NoArgsConstructor
public class ProdavacTermin implements ApstraktniDomenskiObjekat,Serializable{
    
	/** Prodavac koji je dezuran. */
    private Prodavac prodavac;
    
    /** Termin u kojem je prodavac dezuran. */
    private Termin termin;
    
    /** Datum dezurstva prodavca. */
    private LocalDate datumDezurstva;
    
    /** Smena u kojoj je prodavac dezuran. */
    private String smena;


    /**
     * Konstruktor koji inicijalizuje sve atribute dezurstva prodavca.
     *
     * @param prodavac prodavac koji je dezuran
     * @param termin termin u kojem je prodavac dezuran
     * @param datumDezurstva datum dezurstva
     * @param smena smena dezurstva
     */
    public ProdavacTermin(Prodavac prodavac, Termin termin, LocalDate datumDezurstva, String smena) {
        this.prodavac = prodavac;
        this.termin = termin;
        this.datumDezurstva = datumDezurstva;
        this.smena = smena;
    }

    /**
     * Vraca tekstualnu reprezentaciju dezurstva prodavca sa datumom i smenom.
     *
     * @return string sa datumom i smenom dezurstva
     */
    @Override
    public String toString() {
        return "ProdavacTermin{" + "datumDezurstva=" + datumDezurstva + ", smena=" + smena + '}';
    }

    /**
     * Vraca naziv tabele "prodavactermin" u bazi podataka.
     *
     * @return naziv tabele "prodavactermin"
     */
    @Override
    public String vratiNazivTabele() {
        return "prodavactermin";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
    List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

    while (rs.next()) {
        int idProdavac = rs.getInt("prodavactermin.idProdavac");
        LocalDate datumDezurstva = rs.getDate("prodavactermin.datumDezurstva").toLocalDate();
        String smena = rs.getString("prodavactermin.smena");

        String imeProdavac = rs.getString("prodavac.ime");
        String prezimeProdavac = rs.getString("prodavac.prezime");
        String korisnickoIme = rs.getString("prodavac.korisnickoIme");
        String sifra = rs.getString("prodavac.sifra");
        Prodavac prodavac = new Prodavac(idProdavac, imeProdavac, prezimeProdavac, korisnickoIme, sifra);

        int idTermin = rs.getInt("termin.idTerminDezurstva");
        String nazivTermin = rs.getString("termin.naziv");
        Termin termin = new Termin(idTermin, nazivTermin);

        ProdavacTermin pt = new ProdavacTermin(prodavac, termin, datumDezurstva, smena);

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
