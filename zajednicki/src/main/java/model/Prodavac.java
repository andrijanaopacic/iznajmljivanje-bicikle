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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Predstavlja prodavca koji obradjuje iznajmljivanja bicikli.
 * Sadrzi podatke za prijavu prodavca (korisnicko ime i sifra) i listu
 * termina u kojima je prodavac dostupan.
 *
 * @author Andrijana Opacic
 * @see ProdavacTermin
 * @see Iznajmljivanje
 */
@Getter
@Setter
@NoArgsConstructor
public class Prodavac implements ApstraktniDomenskiObjekat,Serializable {
    
	/** Jedinstveni identifikator prodavca u bazi podataka. */
    private int idProdavac;
    
    /** Ime prodavca. */
    private String ime;
    
    /** Prezime prodavca. */
    private String prezime;
    
    /** Korisnicko ime prodavca koje se koristi za prijavu. */
    private String korisnickoIme;
    
    /** Sifra prodavca koja se koristi za prijavu. */
    private String sifra;
    
    /** Lista termina u kojima je prodavac dostupan. */
    List<ProdavacTermin> prodavacTermini;

    /**
     * Konstruktor koji inicijalizuje sve atribute prodavca ukljucujuci i ID.
     *
     * @param idProdavac jedinstveni identifikator prodavca
     * @param ime ime prodavca
     * @param prezime prezime prodavca
     * @param korisnickoIme korisnicko ime za prijavu
     * @param sifra sifra za prijavu
     */
    public Prodavac(int idProdavac, String ime, String prezime, String korisnickoIme, String sifra) {
        this.idProdavac = idProdavac;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.prodavacTermini = new ArrayList<>();
    }
    
    /**
     * Konstruktor koji inicijalizuje atribute prodavca bez ID-a.
     * Koristi se prilikom kreiranja novog prodavca pre unosa u bazu podataka.
     *
     * @param ime ime prodavca
     * @param prezime prezime prodavca
     * @param korisnickoIme korisnicko ime za prijavu
     * @param sifra sifra za prijavu
     */
    public Prodavac(String ime, String prezime, String korisnickoIme, String sifra) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.prodavacTermini = new ArrayList<>();
    }
    
    /**
     * Konstruktor koji inicijalizuje samo podatke za prijavu prodavca.
     * Koristi se prilikom prijave prodavca u sistem.
     *
     * @param korisnickoIme korisnicko ime za prijavu
     * @param sifra sifra za prijavu
     */
    public Prodavac(String korisnickoIme, String sifra) {
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.prodavacTermini = new ArrayList<>();
    }
    
    /**
     * Vraca tekstualnu reprezentaciju prodavca koja sadrzi ime i prezime.
     *
     * @return string sa imenom i prezimenom prodavca
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    /**
     * Vraca hash kod prodavca racunat na osnovu korisnickog imena i sifre,
     * u skladu sa atributima koje koristi {@link #equals(Object)}.
     *
     * @return hash kod prodavca
     */
    @Override
    public int hashCode() {
        return Objects.hash(korisnickoIme, sifra);
    }

    /**
     * Poredi ovog prodavca sa drugim objektom na osnovu korisnickog imena i sifre,
     * a ne na osnovu jedinstvenog identifikatora. Ovo je specificno za prodavca jer
     * se prijava u sistem vrsi preko korisnickog imena i sifre.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su prodavci istog tipa i imaju isto korisnickoIme i sifru, false ako je
     *         obj null, ako je obj drugog tipa, ili ako se korisnickoIme ili sifra razlikuju
     */
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

    /**
     * Vraca naziv tabele "prodavac" u bazi podataka.
     *
     * @return naziv tabele "prodavac"
     */
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
