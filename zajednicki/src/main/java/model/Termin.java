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

/**
 *
 * @author HP
 */
public class Termin implements ApstraktniDomenskiObjekat,Serializable{
    
    private int idTerminDezurstva;
    private String naziv;

    public Termin() {
    }
    
    public Termin(String naziv) {
        this.naziv = naziv;
    }

    public Termin(int idTerminDezurstva, String naziv) {
        this.idTerminDezurstva = idTerminDezurstva;
        this.naziv = naziv;
    }

    public int getIdTerminDezurstva() {
        return idTerminDezurstva;
    }

    public void setIdTerminDezurstva(int idTerminDezurstva) {
        this.idTerminDezurstva = idTerminDezurstva;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }

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
