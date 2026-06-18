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
 * Predstavlja kupca koji moze da iznajmi bicikle.
 * Sadrzi osnovne podatke o kupcu i mesto u kojem kupac zivi.
 *
 * @author Andrijana Opacic
 * @see Mesto
 * @see Iznajmljivanje
 */
@Getter
@Setter
@NoArgsConstructor
public class Kupac implements ApstraktniDomenskiObjekat, Serializable {

    /** Jedinstveni identifikator kupca u bazi podataka. */
    private int idKupac;

    /** Ime kupca. */
    private String ime;

    /** Prezime kupca. */
    private String prezime;

    /** Broj licne karte kupca. */
    private String brojLicneKarte;

    /** Mesto u kojem kupac zivi. */
    private Mesto mesto;

    /**
     * Konstruktor koji inicijalizuje sve atribute kupca ukljucujuci i ID.
     *
     * @param idKupac jedinstveni identifikator kupca
     * @param ime ime kupca
     * @param prezime prezime kupca
     * @param brojLicneKarte broj licne karte kupca
     * @param mesto mesto u kojem kupac zivi
     */
    public Kupac(int idKupac, String ime, String prezime, String brojLicneKarte, Mesto mesto) {
        this.idKupac = idKupac;
        this.ime = ime;
        this.prezime = prezime;
        this.brojLicneKarte = brojLicneKarte;
        this.mesto = mesto;
    }

    /**
     * Konstruktor koji inicijalizuje atribute kupca bez ID-a.
     * Koristi se prilikom kreiranja novog kupca pre unosa u bazu podataka.
     *
     * @param ime ime kupca
     * @param prezime prezime kupca
     * @param brojLicneKarte broj licne karte kupca
     * @param mesto mesto u kojem kupac zivi
     */
    public Kupac(String ime, String prezime, String brojLicneKarte, Mesto mesto) {
        this.ime = ime;
        this.prezime = prezime;
        this.brojLicneKarte = brojLicneKarte;
        this.mesto = mesto;
    }

    /**
     * Vraca tekstualnu reprezentaciju kupca koja sadrzi ime i prezime.
     *
     * @return string sa imenom i prezimenom kupca
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    /**
     * Vraca hash kod kupca racunat na osnovu jedinstvenog identifikatora.
     *
     * @return hash kod kupca
     */
    @Override
    public int hashCode() {
        return Objects.hash(idKupac);
    }

    /**
     * Poredi ovog kupca sa drugim objektom na osnovu jedinstvenog identifikatora.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su kupci istog tipa i imaju isti idKupac, false ako je
     *         obj null, ako je obj drugog tipa, ili ako se idKupac razlikuju
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
        final Kupac other = (Kupac) obj;
        return this.idKupac == other.idKupac;
    }

    /**
     * Vraca naziv tabele "kupac" u bazi podataka.
     *
     * @return naziv tabele "kupac"
     */
    @Override
    public String vratiNazivTabele() {
        return "kupac";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("kupac.idKupac");
            String ime = rs.getString("kupac.ime");
            String prezime = rs.getString("kupac.prezime");
            String brojLicneKarte = rs.getString("kupac.brojLicneKarte");
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
            String ime = rs.getString("kupac.ime");
            String prezime = rs.getString("kupac.prezime");
            String brojLicneKarte = rs.getString("kupac.brojLicneKarte");
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