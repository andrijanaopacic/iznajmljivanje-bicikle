/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Predstavlja mesto koje se koristi za odredjivanje prebivalista kupca.
 *
 * @author Andrijana Opacic
 * @see Kupac
 */
@Getter
@Setter
@NoArgsConstructor
public class Mesto implements ApstraktniDomenskiObjekat, Serializable {

    /** Jedinstveni identifikator mesta u bazi podataka. */
    private int idMesto;

    /** Naziv mesta. */
    private String naziv;

    /**
     * Konstruktor koji inicijalizuje sve atribute mesta ukljucujuci i ID.
     *
     * @param idMesto jedinstveni identifikator mesta
     * @param naziv naziv mesta
     */
    public Mesto(int idMesto, String naziv) {
        this.idMesto = idMesto;
        this.naziv = naziv;
    }

    /**
     * Konstruktor koji inicijalizuje naziv mesta bez ID-a.
     * Koristi se prilikom kreiranja novog mesta pre unosa u bazu podataka.
     *
     * @param naziv naziv mesta
     */
    public Mesto(String naziv) {
        this.naziv = naziv;
    }

    /**
     * Vraca tekstualnu reprezentaciju mesta koja sadrzi naziv mesta.
     *
     * @return naziv mesta
     */
    @Override
    public String toString() {
        return naziv;
    }

    /**
     * Vraca hash kod mesta racunat na osnovu jedinstvenog identifikatora.
     *
     * @return hash kod mesta
     */
    @Override
    public int hashCode() {
        return Objects.hash(idMesto);
    }

    /**
     * Poredi ovo mesto sa drugim objektom na osnovu jedinstvenog identifikatora.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su mesta istog tipa i imaju isti idMesto, false ako je
     *         obj null, ako je obj drugog tipa, ili ako se idMesto razlikuju
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
        final Mesto other = (Mesto) obj;
        return this.idMesto == other.idMesto;
    }

    /**
     * Vraca naziv tabele "mesto" u bazi podataka.
     *
     * @return naziv tabele "mesto"
     */
    @Override
    public String vratiNazivTabele() {
        return "mesto";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("mesto.idMesto");
            String naziv = rs.getString("mesto.naziv");
            Mesto mesto = new Mesto(id, naziv);
            lista.add(mesto);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "mesto.idMesto=" + idMesto;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        try {
            Mesto mesto = null;

            while (rs.next()) {
                int id = rs.getInt("mesto.idMesto");
                String naziv = rs.getString("mesto.naziv");

                mesto = new Mesto(id, naziv);
            }

            return mesto;
        } catch (Exception ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja mesta: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "'";
    }

}