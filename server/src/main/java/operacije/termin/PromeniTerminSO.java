/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.termin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import model.Termin;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

/**
 * Sistemska operacija za izmenu termina dezurstva.
 * Pre izmene proverava da li vec postoji drugi termin sa istim nazivom.
 * Izmena se vrsi samo ako ne postoji duplikat.
 *
 * @author Andrijana Opacic
 * @see Termin
 */
public class PromeniTerminSO extends ApstraktnaGenerickaOperacija{
	/** Indikator da li je izmena termina uspesno izvrsena. */
    private boolean uspesno = false;
    
    /** Indikator da li drugi termin sa istim nazivom vec postoji. */
    private boolean postoji = false;
    /**
     * Vraca informaciju o uspesnosti izmene termina.
     *
     * @return true ako je termin uspesno izmenjen, false u suprotnom
     */
    public boolean getUspesno() {
        return uspesno;
    }
    
    /**
     * Proverava da li je prosledjeni objekat odgovarajuceg tipa, da li su vrednosti atributa termina validne 
     * (u skladu sa Jakarta Validation anotacijama definisanim u {@link Termin}), i da li vec postoji
     * drugi termin (sa razlicitim ID-om) sa istim nazivom u bazi.
     *
     * @param objekat objekat tipa {@link Termin} koji se proverava
     * @throws Exception ako objekat nije odgovarajuceg tipa, ako podaci
     *         nisu validni, ili dodje do greske pri radu sa bazom
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Termin)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

        Termin termin = (Termin) objekat;

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Termin>> violations = validator.validate(termin);
        if (!violations.isEmpty()) {
            StringBuilder poruka = new StringBuilder();
            for (ConstraintViolation<Termin> v : violations) {
                poruka.append(v.getMessage()).append(" ");
            }
            throw new Exception(poruka.toString().trim());
        }

        try {
            String upit = "SELECT * FROM termin WHERE naziv='" + ((Termin) objekat).getNaziv()
                    + "' AND idTerminDezurstva != " + ((Termin) objekat).getIdTerminDezurstva();
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
     * Menja podatke termina ukoliko ne postoji drugi termin sa istim nazivom.
     *
     * @param objekat objekat tipa {@link Termin} koji se menja
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske prilikom izmene
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!postoji) {
            broker.edit((Termin) objekat);
            uspesno = true;
        }
    }
    
}