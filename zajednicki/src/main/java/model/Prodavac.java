/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author HP
 */
public class Prodavac implements ApstraktniDomenskiObjekat,Serializable {
    
    private int idProdavac;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String sifra;
    List<ProdavacTermin> prodavacTermini;

    public Prodavac() {
    }

    public Prodavac(int idProdavac, String ime, String prezime, String korisnickoIme, String sifra) {
        this.idProdavac = idProdavac;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.prodavacTermini = new ArrayList<>();
    }
    
    public Prodavac(String ime, String prezime, String korisnickoIme, String sifra) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.prodavacTermini = new ArrayList<>();
    }
    
    public Prodavac(String korisnickoIme, String sifra) {
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.prodavacTermini = new ArrayList<>();
    }

    public List<ProdavacTermin> getProdavacTermini() {
        return prodavacTermini;
    }

    public void setProdavacTermini(List<ProdavacTermin> prodavacTermini) {
        this.prodavacTermini = prodavacTermini;
    }

    public int getIdProdavac() {
        return idProdavac;
    }

    public void setIdProdavac(int idProdavac) {
        this.idProdavac = idProdavac;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Prodavac other = (Prodavac) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    

    @Override
    public String vratiNazivTabele() {
        return "prodavac";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        while(rs.next()){
                int id = rs.getInt("prodavac.idProdavac");
                String ime  = rs.getString("prodavac.ime");
                String prezime  = rs.getString("prodavac.prezime");
                String korisnickoIme  = rs.getString("prodavac.korisnickoIme");
                String sifra  = rs.getString("prodavac.sifra");
                Prodavac p = new Prodavac(id, ime, prezime, korisnickoIme, sifra);
                lista.add(p);
            }
        
        return lista;
                
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,korisnickoIme,sifra";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "','" + prezime + "','" + korisnickoIme + "','" + sifra + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "prodavac.idProdavac=" + idProdavac;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        Prodavac p = null;
        try {

            while (rs.next()) {

                int idProdavac = rs.getInt("idProdavac");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String korisnickoIme = rs.getString("korisnickoIme");
                String sifra = rs.getString("sifra");
                p = new Prodavac(idProdavac, ime, prezime, korisnickoIme, sifra);
            }

            return p;

        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja prodavca: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime= '" + ime + "',prezime = '" + prezime + "',korisnickoIme = '" + korisnickoIme + "',sifra = '" + sifra + "'";
    }

    

    
    
    
    
    
}
