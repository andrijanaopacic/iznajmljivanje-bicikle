/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.prodavac;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import model.Prodavac;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

/**
 * Sistemska operacija za kreiranje novog prodavca.
 * Pre dodavanja prodavca u bazu proverava da li prodavac sa istim podacima vec postoji.
 *
 * @author Andrijana Opacic
 * @see Prodavac
 */
public class KreirajProdavacSO extends ApstraktnaGenerickaOperacija {

	 /** Indikator da li je prodavac uspesno kreiran. */
    private boolean uspesno = false;
    
    /** Indikator da li prodavac sa prosledjenim podacima vec postoji. */
    private boolean postoji = false;

    /**
     * Vraca informaciju o uspesnosti kreiranja prodavca.
     *
     * @return true ako je prodavac uspesno dodat, false incae
     */
    public boolean getUspesno() {
        return uspesno;
    }
    
    /**
     * Proverava da li je prosledjen objekat odgovarajuceg tipa, da li su vrednosti atributa prodavca validne 
     * (u skladu sa Jakarta Validation anotacijama definisanim u {@link Prodavac}), i proverava da li
     * prodavac vec postoji u bazi podataka na osnovu imena, prezimena, korisnickog imena i šifre.
     *
     * @param objekat objekat tipa {@link Prodavac} koji se proverava
     * @throws Exception ako objekat nije odgovarajuceg tipa, ako podaci
     *         nisu validni, ili dođe do greske pri radu sa bazom
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Prodavac)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

        Prodavac p = (Prodavac) objekat;

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Prodavac>> violations = validator.validate(p);
        if (!violations.isEmpty()) {
            StringBuilder poruka = new StringBuilder();
            for (ConstraintViolation<Prodavac> v : violations) {
                poruka.append(v.getMessage()).append(" ");
            }
            throw new Exception(poruka.toString().trim());
        }

        try {
            String upit = "SELECT * FROM prodavac WHERE ime = '" + ((Prodavac) objekat).getIme() + "' AND prezime = '" + ((Prodavac) objekat).getPrezime() + "' AND korisnickoIme = '" + ((Prodavac) objekat).getKorisnickoIme() + "' AND sifra = '" + ((Prodavac) objekat).getSifra() + "'";
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
     * Kreira novog prodavca ukoliko prodavac sa istim podacima ne postoji.
     *
     * @param objekat objekat tipa {@link Prodavac} koji se dodaje
     * @param kljuc dodatni parametar operacije
     * @throws Exception ako dodje do greske prilikom dodavanja
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!postoji) {
            broker.add((Prodavac) objekat);

            uspesno = true;
        }
    }
    
}
