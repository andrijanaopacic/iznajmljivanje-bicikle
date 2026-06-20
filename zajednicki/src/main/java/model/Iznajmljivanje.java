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
import java.util.Objects;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Predstavlja iznajmljivanje bicikli koje je kupac napravio kod prodavca.
 * Sadrzi listu stavki iznajmljivanja koje opisuju koje bicikle su iznajmljene
 * i u kom periodu, kao i ukupan iznos za sve stavke.
 *
 * @author Andrijana Opacic
 * @see StavkaIznajmljivanja
 * @see Kupac
 * @see Prodavac
 */
@Getter
@Setter
@NoArgsConstructor
public class Iznajmljivanje implements ApstraktniDomenskiObjekat,Serializable{
    
	/** Jedinstveni identifikator iznajmljivanja u bazi podataka. */
    private int idIznajmljivanje;
    
    /** Ukupan iznos za sve stavke iznajmljivanja, mora biti veci od nule. */
    @Positive(message = "Ukupan iznos mora biti veci od nule")
    private double ukupanIznos;
    
    /** Lista stavki koje opisuju iznajmljene bicikle u okviru ovog iznajmljivanja, ne sme biti null ili prazna. */
    @NotEmpty(message = "Lista stavki ne moze biti prazna")
    private List<StavkaIznajmljivanja> listaStavkiIznajmljivanja;
    
    /** Kupac koji je napravio iznajmljivanje, ne sme biti null. */
    @NotNull(message = "Kupac ne moze biti null")
    private Kupac kupac;
    
    /** Prodavac koji je obradio iznajmljivanje, ne sme biti null. */
    @NotNull(message = "Prodavac ne moze biti null")
    private Prodavac prodavac;


    /**
     * Konstruktor koji inicijalizuje sve atribute iznajmljivanja.
     *
     * @param idIznajmljivanje jedinstveni identifikator iznajmljivanja
     * @param ukupanIznos ukupan iznos za sve stavke iznajmljivanja
     * @param listaStavkiIznajmljivanja lista stavki iznajmljivanja
     * @param kupac kupac koji je napravio iznajmljivanje
     * @param prodavac prodavac koji je obradio iznajmljivanje
     */
    public Iznajmljivanje(int idIznajmljivanje, double ukupanIznos, List<StavkaIznajmljivanja> listaStavkiIznajmljivanja, Kupac kupac, Prodavac prodavac) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.ukupanIznos = ukupanIznos;
        this.listaStavkiIznajmljivanja = listaStavkiIznajmljivanja;
        this.kupac = kupac;
        this.prodavac = prodavac;
    }

    /**
     * Vraca hash kod iznajmljivanja racunat na osnovu jedinstvenog identifikatora.
     *
     * @return hash kod iznajmljivanja
     */
    @Override
    public int hashCode() {
        return Objects.hash(idIznajmljivanje);
    }

    /**
     * Poredi ovo iznajmljivanje sa drugim objektom na osnovu jedinstvenog identifikatora.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su iznajmljivanja istog tipa i imaju isti idIznajmljivanje, false ako je
     *         obj null, ako je obj drugog tipa, ili ako se idIznajmljivanje razlikuju
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
        final Iznajmljivanje other = (Iznajmljivanje) obj;
        return this.idIznajmljivanje == other.idIznajmljivanje;
    }

    /**
     * Vraca tekstualnu reprezentaciju iznajmljivanja sa svim atributima.
     *
     * @return string sa svim atributima iznajmljivanja
     */
    @Override
    public String toString() {
        return "Iznajmljivanje{" + "idIznajmljivanje=" + idIznajmljivanje + ", ukupanIznos=" + ukupanIznos + ", listaStavkiIznajmljivanja=" + listaStavkiIznajmljivanja + ", kupac=" + kupac + ", prodavac=" + prodavac + '}';
    }

    /**
     * Vraca naziv tabele "iznajmljivanje" u bazi podataka.
     *
     * @return naziv tabele "iznajmljivanje"
     */
    @Override
    public String vratiNazivTabele() {
        return "iznajmljivanje";
    }

    /**
     * Vraca listu iznajmljivanja kreiranih na osnovu podataka iz ResultSet-a.
     * Svako iznajmljivanje se kreira sa praznom listom stavki - stavke se
     * ucitavaju posebno, odvojenim upitom.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return lista iznajmljivanja kreiranih iz ResultSet-a, sa praznim listama stavki
     * @throws Exception ako dodje do greske pri citanju podataka iz ResultSet-a
     */
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

    /**
     * Vraca jedno iznajmljivanje kreirano na osnovu podataka iz ResultSet-a.
     * Iznajmljivanje se kreira sa praznom listom stavki - stavke se
     * ucitavaju posebno, odvojenim upitom.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return objekat iznajmljivanja kreiran iz ResultSet-a sa praznom listom stavki,
     *         ili null ako dodje do greske pri citanju podataka
     * @throws Exception ako dodje do greske pri citanju podataka iz ResultSet-a
     */
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
