/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.mesto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import model.Bicikla;
import model.Kupac;
import model.Mesto;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

/**
 * Sistemska operacija za kreiranje novog mesta.
 * Proverava da li mesto sa tim nazivom vec postoji u bazi.
 *
 * @author Andrijana Opacic
 * @see Mesto
 */
public class KreirajMestoSO extends ApstraktnaGenerickaOperacija{

	/** Indikator uspesnosti kreiranja mesta. */
    private boolean uspesno = false;
    
    /** Indikator da li mesto sa istim nazivom vec postoji u bazi podataka. */
    private boolean postoji = false;

    /**
     * Vraca indikator uspesnosti kreiranja mesta.
     *
     * @return true ako je mesto uspesno kreirana, false inace
     */
    public boolean getUspesno() {
        return uspesno;
    }
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa, da li su vrednosti atributa mesta validne 
     * (u skladu sa Jakarta Validation anotacijama definisanim u {@link Mesto}), i da li mesto sa istim
     * nazivom vec postoji u bazi podataka.
     *
     * @param objekat objekat tipa {@link Mesto} koji se kreira
     * @throws Exception ako parametar nije odgovarajuceg tipa, ili ako
     *         podaci nisu validni
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Mesto)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

        Mesto m = (Mesto) objekat;

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Mesto>> violations = validator.validate(m);
        if (!violations.isEmpty()) {
            StringBuilder poruka = new StringBuilder();
            for (ConstraintViolation<Mesto> v : violations) {
                poruka.append(v.getMessage()).append(" ");
            }
            throw new Exception(poruka.toString().trim());
        }

        try {
            String upit = "SELECT * FROM mesto WHERE naziv = '" + ((Mesto) objekat).getNaziv() + "'";
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                postoji = true;
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    /**
     * Izvrsava kreiranje mesta u bazi podataka.
     * Mesto se kreira samo ako ne postoji u bazi podataka. 
     *
     * @param objekat objekat tipa {@link Mesto} koji se kreira
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!postoji) {
            broker.add((Mesto) objekat);
            uspesno = true;
        }
    }
    
}
