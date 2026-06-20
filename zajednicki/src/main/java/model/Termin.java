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

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Predstavlja termin dezurstva u kojem prodavac moze biti dezuran.
 *
 * @author Andrijana Opacic
 * @see ProdavacTermin
 */
@Getter
@Setter
@NoArgsConstructor
public class Termin implements ApstraktniDomenskiObjekat,Serializable{
    
	/** Jedinstveni identifikator termina dezurstva u bazi podataka. */
    private int idTerminDezurstva;
    
    /** Naziv termina dezurstva, ne sme biti prazan i ne sme imati vise od 30 karaktera. */
    @NotBlank(message = "Naziv termina ne moze biti prazan")
    @Size(max = 30, message = "Naziv termina ne moze imati vise od 30 karaktera")
    private String naziv;
    
    /**
     * Konstruktor koji inicijalizuje naziv termina bez ID-a.
     * Koristi se prilikom kreiranja novog termina pre unosa u bazu podataka.
     *
     * @param naziv naziv termina
     */
    public Termin(String naziv) {
        this.naziv = naziv;
    }

    /**
     * Konstruktor koji inicijalizuje sve atribute termina ukljucujuci i ID.
     *
     * @param idTerminDezurstva jedinstveni identifikator termina
     * @param naziv naziv termina
     */
    public Termin(int idTerminDezurstva, String naziv) {
        this.idTerminDezurstva = idTerminDezurstva;
        this.naziv = naziv;
    }

    /**
     * Vraca tekstualnu reprezentaciju termina koja sadrzi njegov naziv.
     *
     * @return naziv termina
     */
    @Override
    public String toString() {
        return naziv;
    }

    /**
     * Vraca naziv tabele "termin" u bazi podataka.
     *
     * @return naziv tabele "termin"
     */
    @Override
    public String vratiNazivTabele() {
        return "termin";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
          while(rs.next()){
                int id = rs.getInt("termin.idTerminDezurstva");
                String naziv  = rs.getString("termin.naziv");
                Termin termin = new Termin(id, naziv);
                lista.add(termin);
            }
        
        return lista;
    }
    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv+"'";
    }
        

    @Override
    public String vratiPrimarniKljuc() {
        return "termin.idTerminDezurstva=" + idTerminDezurstva;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        try {
            Termin t = null;
            while (rs.next()) {

                int idTerminDezurstva = rs.getInt("termin.idTerminDezurstva");
                String naziv = rs.getString("termin.naziv");
                
                t = new Termin(idTerminDezurstva, naziv);

            }
            return t;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja termina: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='"+naziv+"'";
    }
    
    
}
