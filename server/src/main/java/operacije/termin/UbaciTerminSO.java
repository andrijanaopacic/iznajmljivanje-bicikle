/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.termin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Termin;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

/**
 * Sistemska operacija za ubacivanje novog termina dezurstva.
 * Pre ubacivanja proverava da li vec postoji termin sa istim nazivom.
 * Termin se dodaje u bazu samo ako ne postoji duplikat.
 *
 * @author Andrijana Opacic
 * @see Termin
 */
public class UbaciTerminSO extends ApstraktnaGenerickaOperacija{

	/** Indikator da li je termin uspesno ubacen u bazu. */
    private boolean uspesno = false;
    
    /** Indikator da li termin sa istim nazivom vec postoji. */
    private boolean postoji = false;

    /**
     * Vraca informaciju o uspesnosti ubacivanja termina.
     *
     * @return true ako je termin uspesno ubacen, false u suprotnom
     */
    public boolean getUspesno() {
        return uspesno;
    }
    
    /**
     * Proverava da li je prosledjeni objekat odgovarajuceg tipa i da li vec postoji termin sa istim nazivom u bazi.
     *
     * @param objekat objekat tipa {@link Termin} koji se ubacuje
     * @throws Exception ako objekat nije odgovarajuceg tipa ili dodje do greske pri radu sa bazom
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
       if (objekat == null || !(objekat instanceof Termin)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

        try {

            String upit = "SELECT * FROM termin WHERE naziv='" + ((Termin) objekat).getNaziv()+ "'";
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
     * Ubacuje novi termin u bazu podataka ukoliko ne postoji duplikat.
     *
     * @param objekat objekat tipa {@link Termin} koji se ubacuje
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske prilikom ubacivanja
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!postoji) {
            broker.add((Termin) objekat);
            uspesno = true;
        }
    }
    
}
