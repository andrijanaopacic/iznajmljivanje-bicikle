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
public class Mesto implements ApstraktniDomenskiObjekat,Serializable {
    
    private int idMesto;
    private String naziv;

    public Mesto() {
    }

    public Mesto(int idMesto, String naziv) {
        this.idMesto = idMesto;
        this.naziv = naziv;
    }
    
    public Mesto(String naziv) {
        this.naziv = naziv;
    }

    public int getIdMesto() {
        return idMesto;
    }

    public void setIdMesto(int idMesto) {
        this.idMesto = idMesto;
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
        final Mesto other = (Mesto) obj;
        return this.idMesto == other.idMesto;
    }

    @Override
    public String vratiNazivTabele() {
        return "mesto";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        while(rs.next()){
                int id = rs.getInt("mesto.idMesto");
                String naziv  = rs.getString("mesto.naziv");
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
