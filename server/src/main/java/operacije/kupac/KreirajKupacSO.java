/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.kupac;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import model.Kupac;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

/**
 * Sistemska operacija za kreiranje novog kupca.
 * Validira osnovne podatke kupca i proverava da li kupac sa istim podacima vec postoji u bazi.
 *
 * @author Andrijana Opacic
 * @see Kupac
 */
public class KreirajKupacSO extends ApstraktnaGenerickaOperacija {
	
	 /** Indikator uspesnosti kreiranja kupca. */
    private boolean uspesno = false;
    
    /** Indikator da li kupac sa istim podacima vec postoji u bazi podataka. */
    private boolean postoji = false;

    /**
     * Vraca indikator uspesnosti kreiranja kupca.
     *
     * @return true ako je kupac uspesno kreiran, false inace
     */
    public boolean getUspesno() {
        return uspesno;
    }

    /**
     * Validira vrednosti atributa kupca (ime, prezime, broj licne karte i mesto, u skladu sa Jakarta Validation anotacijama definisanim u
     * {@link Kupac}), i proverava da li kupac sa istim podacima vec postoji u bazi.
     *
     * @param parametar objekat tipa {@link Kupac} koji se kreira
     * @throws Exception ako parametar nije odgovarajuceg tipa, ili ako
     *         podaci nisu validni
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof Kupac)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        
        Kupac k = (Kupac) parametar;

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Kupac>> violations = validator.validate(k);
        if (!violations.isEmpty()) {
            StringBuilder poruka = new StringBuilder();
            for (ConstraintViolation<Kupac> v : violations) {
                poruka.append(v.getMessage()).append(" ");
            }
            throw new Exception(poruka.toString().trim());
        }

        try {
            String upit = "SELECT * FROM kupac k JOIN mesto m ON k.idMesto = m.idMesto WHERE k.ime='" + ((Kupac) parametar).getIme() + "' AND k.prezime='" + ((Kupac) parametar).getPrezime() + "' AND k.brojLicneKarte='" + ((Kupac) parametar).getBrojLicneKarte() + "' AND m.naziv='" + ((Kupac) parametar).getMesto().getNaziv() + "'";
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
     * Dodaje kupca u bazu preko brokera, samo ako ne postoji vec.
     *
     * @param objekat objekat tipa {@link Kupac} koji se kreira
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!postoji) {
            broker.add((Kupac) objekat);
            uspesno = true;
        }
    }
}
