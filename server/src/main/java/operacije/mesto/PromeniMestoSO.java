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
import model.Mesto;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

/**
 * Sistemska operacija za izmenu postojeceg mesta.
 * Pre izmene proverava da li neko drugo mesto (sa razlicitim ID-om) vec ima isti naziv.
 *
 * @author Andrijana Opacic
 * @see Mesto
 */
public class PromeniMestoSO extends ApstraktnaGenerickaOperacija{

	/** Indikator uspesnosti izmene mesta. */
    private boolean uspesno = false;
    
    /** Indikator da li drugo mesto sa istim nazivom vec postoji u bazi podataka. */
    private boolean postoji = false;

    /**
     * Vraca indikator uspesnosti izmene mesta.
     *
     * @return true ako je mesto uspesno izmenjeno, false inace
     */
    public boolean getUspesno() {
        return uspesno;
    }
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa, da li su vrednosti atributa mesta validne 
     * (u skladu sa Jakarta Validation anotacijama definisanim u {@link Mesto}), i da li neko drugo mesto
     * (razlicitog ID-a) vec ima iste naziv.
     *
     * @param objekat objekat tipa {@link Mesto} koji se izmenjuje
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
            String upit = "SELECT * FROM mesto WHERE naziv = '"+((Mesto)objekat).getNaziv()+"'";
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
     * Izvrsava izmenu mesta u bazi podataka.
     * Mesto se izmenjuje samo ako ne postoji drugo mesto sa istim nazivom.
     *
     * @param objekat objekat tipa {@link Mesto} koji se izmenjuje
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!postoji) {
            broker.edit((Mesto) objekat);
            uspesno = true;
        }
    }
    
}
