/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Iznajmljivanje implements ApstraktniDomenskiObjekat,Serializable{
    
    private int idIznajmljivanje;
    private double ukupanIznos;
    private List<StavkaIznajmljivanja> listaStavkiIznajmljivanja;
    private Kupac kupac;
    private Prodavac prodavac;

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(int idIznajmljivanje, double ukupanIznos, List<StavkaIznajmljivanja> listaStavkiIznajmljivanja, Kupac kupac, Prodavac prodavac) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.ukupanIznos = ukupanIznos;
        this.listaStavkiIznajmljivanja = listaStavkiIznajmljivanja;
        this.kupac = kupac;
        this.prodavac = prodavac;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    

    public List<StavkaIznajmljivanja> getListaStavkiIznajmljivanja() {
        return listaStavkiIznajmljivanja;
    }

    public void setListaStavkiIznajmljivanja(List<StavkaIznajmljivanja> listaStavkiIznajmljivanja) {
        this.listaStavkiIznajmljivanja = listaStavkiIznajmljivanja;
    }
    
    

    public int getIdIznajmljivanje() {
        return idIznajmljivanje;
    }

    public void setIdIznajmljivanje(int idIznajmljivanje) {
        this.idIznajmljivanje = idIznajmljivanje;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
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
        final Iznajmljivanje other = (Iznajmljivanje) obj;
        return this.idIznajmljivanje == other.idIznajmljivanje;
    }

    
    
    

    @Override
    public String toString() {
        return "Iznajmljivanje{" + "idIznajmljivanje=" + idIznajmljivanje + ", ukupanIznos=" + ukupanIznos + 
                ", listaStavkiIznajmljivanja=" + listaStavkiIznajmljivanja + ", kupac=" + kupac + ", prodavac=" + prodavac + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "iznajmljivanje";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int idIznajmljivanje = rs.getInt("iznajmljivanje.idIznajmljivanje");
            double ukupanIznos = rs.getDouble("iznajmljivanje.ukupanIznos");
            int kupacID = rs.getInt("kupac.idKupac");
            int prodavacID = rs.getInt("prodavac.idProdavac");

            String imeKupac = rs.getString("kupac.ime");
            String prezimeKupac = rs.getString("kupac.prezime");
            String brojLicneKarte = rs.getString("kupac.brojLicneKarte");

            String imeProdavac = rs.getString("prodavac.ime");
            String prezimeProdavac = rs.getString("prodavac.prezime");
            String korisnickoIme = rs.getString("prodavac.korisnickoIme");
            String sifra = rs.getString("prodavac.sifra");

            int idMesto = rs.getInt("mesto.idMesto");
            String nazivMesta = rs.getString("mesto.naziv");

            Mesto mesto = new Mesto(idMesto, nazivMesta);
            Kupac kupac = new Kupac(kupacID, imeKupac, prezimeKupac, brojLicneKarte, mesto);
            Prodavac prodavac = new Prodavac(prodavacID, imeProdavac, prezimeProdavac, korisnickoIme, sifra);

            Iznajmljivanje iznajmljivanje = new Iznajmljivanje(idIznajmljivanje, ukupanIznos, new ArrayList<>(), kupac, prodavac);
            lista.add(iznajmljivanje);
        }

        return lista;
    }



    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ukupanIznos,idProdavac,idKupac";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return ukupanIznos + "," + prodavac.getIdProdavac() + "," + kupac.getIdKupac();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "iznajmljivanje.idIznajmljivanje=" +idIznajmljivanje;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        try {
        Iznajmljivanje iznajmljivanje = null;

        while (rs.next()) {
                int idIznajmljivanje = rs.getInt("iznajmljivanje.idIznajmljivanje");
                double ukupanIznos = rs.getDouble("iznajmljivanje.ukupanIznos");

                int kupacID = rs.getInt("iznajmljivanje.idKupac");
                String imeKupac = rs.getString("kupac.ime");
                String prezimeKupac = rs.getString("kupac.prezime");
                String brojLicneKarte = rs.getString("kupac.brojLicneKarte");
                int idMesto = rs.getInt("kupac.idMesto");
                String nazivMesta = rs.getString("mesto.naziv");

                int prodavacID = rs.getInt("iznajmljivanje.idProdavac");
                String imeProdavac = rs.getString("prodavac.ime");
                String prezimeProdavac = rs.getString("prodavac.prezime");
                String korisnickoIme = rs.getString("prodavac.korisnickoIme");
                String sifra = rs.getString("prodavac.sifra");

                Mesto mesto = new Mesto(idMesto, nazivMesta);
                Kupac kupac = new Kupac(kupacID, imeKupac, prezimeKupac, brojLicneKarte, mesto);
                Prodavac prodavac = new Prodavac(prodavacID, imeProdavac, prezimeProdavac, korisnickoIme, sifra);

                iznajmljivanje = new Iznajmljivanje(idIznajmljivanje, ukupanIznos, new ArrayList<>(), kupac, prodavac);
            }

            return iznajmljivanje;
        } catch (Exception ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja iznajmljivanja: " + ex.getMessage());
            return null;
        }
    }



    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ukupanIznos="+ukupanIznos + ",idProdavac=" + prodavac.getIdProdavac() + ",idKupac=" + kupac.getIdKupac();
    }

    
    
    
}
