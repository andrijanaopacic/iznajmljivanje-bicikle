/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;
/**
 * Predstavlja modele sistema.
 * @author Andrijana Opacic
 */
public interface ApstraktniDomenskiObjekat extends Serializable {
    
	/**
	 * Vraca naziv tabele iz baze podataka.
	 * @return Naziv tabele iz koje se ucitavaju podaci.
	 */
    public String vratiNazivTabele();
    
    /**
     * Vraca listu sa svim podacima vezanim za taj model.
     * @param rs
     * @return
     * @throws Exception
     */
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;
    
    public String vratiKoloneZaUbacivanje();
    
    public String vratiVrednostiZaUbacivanje();
    
    public String vratiPrimarniKljuc();
    
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception;
    
    public String vratiVrednostiZaIzmenu();
    
}
