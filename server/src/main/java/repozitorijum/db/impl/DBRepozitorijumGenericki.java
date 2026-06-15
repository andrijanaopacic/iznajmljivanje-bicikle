/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repozitorijum.db.impl;

import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ApstraktniDomenskiObjekat;
import repozitorijum.db.DBKonekcija;
import repozitorijum.db.DBRepozitorijum;

/**
 *
 * @author HP
 */
public class DBRepozitorijumGenericki implements DBRepozitorijum<ApstraktniDomenskiObjekat> {

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        if(uslov != null){
            upit += uslov;
        }
        System.out.println(upit);
        
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        
        lista = param.vratiListu(rs);
        
        rs.close();
        st.close();
        
        return lista;
    }

    @Override
    public void add(ApstraktniDomenskiObjekat param) throws Exception {
       
        String upit = "INSERT INTO " + param.vratiNazivTabele() + " (" + param.vratiKoloneZaUbacivanje()+
                ") VALUES (" + param.vratiVrednostiZaUbacivanje() + ")";
        
        System.out.println(upit);
        
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
        
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "UPDATE " + param.vratiNazivTabele() + " SET " +
                param.vratiVrednostiZaIzmenu()  + " WHERE " + param.vratiPrimarniKljuc();
        System.out.println(upit);
        
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param) throws Exception {
       
        String upit = "DELETE FROM " + param.vratiNazivTabele() + " WHERE " + param.vratiPrimarniKljuc();
        System.out.println(upit);
        
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
        
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll() {
     
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        //TODO
        
        
        return lista;
    }
    
    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param) throws Exception {
        if (DBKonekcija.getInstance().getConnection() == null) {
            System.err.println("Upozorenje: Konekcija nije uspostavljena.");
        } else {
            List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

            String upit = "SELECT * FROM " + param.vratiNazivTabele();
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            lista = param.vratiListu(rs);
            rs.close();
            st.close();
            return lista;
        }
        return null;
    }

    @Override
    public ApstraktniDomenskiObjekat getObject(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        ApstraktniDomenskiObjekat objekat = null;
        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        if (uslov != null) {
            upit += uslov;
        }
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();

        ResultSet rs = st.executeQuery(upit);
        objekat = param.vratiObjekatIzRS(rs);
        rs.close();
        st.close();
        return objekat;
    }

    @Override
    public ApstraktniDomenskiObjekat getObject(ApstraktniDomenskiObjekat param) throws Exception {
        ApstraktniDomenskiObjekat objekat = null;
        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();

        ResultSet rs = st.executeQuery(upit);
        objekat = param.vratiObjekatIzRS(rs);
        rs.close();
        st.close();
        return objekat;
    }
    
    
}
