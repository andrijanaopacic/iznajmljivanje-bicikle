/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Kupac implements ApstraktniDomenskiObjekat,Serializable{
    
    private int idKupac;
    private String ime;
    private String prezime;
    private String brojLicneKarte;
    private Mesto mesto;

    public Kupac() {
    }

    public Kupac(int idKupac, String ime, String prezime, String brojLicneKarte, Mesto mesto) {
        this.idKupac = idKupac;
        this.ime = ime;
        this.prezime = prezime;
        this.brojLicneKarte = brojLicneKarte;
        this.mesto = mesto;
    }
    
    public Kupac(String ime, String prezime, String brojLicneKarte, Mesto mesto) {
        this.ime = ime;
        this.prezime = prezime;
        this.brojLicneKarte = brojLicneKarte;
        this.mesto = mesto;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    
    
    

    public int getIdKupac() {
        return idKupac;
    }

    public void setIdKupac(int idKupac) {
        this.idKupac = idKupac;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojLicneKarte() {
        return brojLicneKarte;
    }

    public void setBrojLicneKarte(String brojLicneKarte) {
        this.brojLicneKarte = brojLicneKarte;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kupac other = (Kupac) obj;
        return this.idKupac == other.idKupac;
    }

    @Override
    public String vratiNazivTabele() {
        return "kupac";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        while(rs.next()){
                int id = rs.getInt("kupac.idKupac");
                String ime  = rs.getString("kupac.ime");
                String prezime  = rs.getString("kupac.prezime");
                String brojLicneKarte  = rs.getString("kupac.brojLicneKarte");
                int idMesto = rs.getInt("kupac.idMesto");
                String naziv = rs.getString("mesto.naziv");
                Mesto mesto = new Mesto(idMesto, naziv);
                Kupac k = new Kupac(id, ime, prezime, brojLicneKarte, mesto);
                lista.add(k);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,brojLicneKarte,idMesto";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "','" + prezime + "','" + brojLicneKarte + "'," + mesto.getIdMesto();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kupac.idKupac=" + idKupac;
    }
        

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
            if (rs.next()) {
                int id = rs.getInt("kupac.idKupac");
                String ime  = rs.getString("kupac.ime");
                String prezime  = rs.getString("kupac.prezime");
                String brojLicneKarte  = rs.getString("kupac.brojLicneKarte");
                int idMesto = rs.getInt("kupac.idMesto");
                String nazivMesta = rs.getString("mesto.naziv");
                Mesto mesto = new Mesto(idMesto, nazivMesta);
                return new Kupac(id, ime, prezime, brojLicneKarte, mesto);
            }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime = '" + ime + "',prezime = '" + prezime + "',brojLicneKarte = '" + brojLicneKarte + "',idMesto = " + mesto.getIdMesto();
    }
    
    
    
}
