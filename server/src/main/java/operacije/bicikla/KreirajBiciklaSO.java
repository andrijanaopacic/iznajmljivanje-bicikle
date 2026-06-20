package operacije.bicikla;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import model.Bicikla;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;
/**
 * Sistemska operacija za kreiranje nove bicikle.
 * Pre kreiranja proverava da li su vrednosti atributa bicikle validne, i da li
 * bicikla sa istim atributima (marka, model, boja, cena po satu i cena po danu)
 * vec postoji u bazi podataka.
 *
 * @author Andrijana Opacic
 * @see Bicikla
 */
public class KreirajBiciklaSO extends ApstraktnaGenerickaOperacija {
	/** Indikator uspesnosti kreiranja bicikle. */
    private boolean uspesno = false;
    
    /** Indikator da li bicikla sa istim atributima vec postoji u bazi podataka. */
    private boolean postoji = false;
    /**
     * Vraca indikator uspesnosti kreiranja bicikle.
     *
     * @return true ako je bicikla uspesno kreirana, false inace
     */
    public boolean getUspesno() {
        return uspesno;
    }
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa, da li su vrednosti atributa bicikle validne 
     * (u skladu sa Jakarta Validation anotacijama definisanim u {@link Bicikla}), i da li bicikla sa istim atributima 
     * (marka, model, boja, cena po satu i cena po danu) vec postoji u bazi podataka.
     *
     * @param objekat objekat tipa {@link Bicikla} koji se kreira
     * @throws Exception ako parametar nije odgovarajuceg tipa, ili ako
     *         vrednosti atributa nisu validne
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Bicikla)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        Bicikla bicikla = (Bicikla) objekat;

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Bicikla>> violations = validator.validate(bicikla);
        if (!violations.isEmpty()) {
            StringBuilder poruka = new StringBuilder();
            for (ConstraintViolation<Bicikla> v : violations) {
                poruka.append(v.getMessage()).append(" ");
            }
            throw new Exception(poruka.toString().trim());
        }

        try {
            String upit = "SELECT * FROM bicikla"
                    + " WHERE marka='" + bicikla.getMarka()
                    + "' AND model='" + bicikla.getModel()
                    + "' AND boja='" + bicikla.getBoja()
                    + "' AND cenaPoSatu=" + bicikla.getCenaPoSatu()
                    + " AND cenaPoDanu=" + bicikla.getCenaPoDanu();
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
     * Izvrsava kreiranje bicikle u bazi podataka.
     * Bicikla se kreira samo ako ne postoji u bazi podataka. Prvo se
     * upisuju zajednicki atributi u tabelu "bicikla", zatim se preuzima
     * automatski generisani ID i koristi za upis specificnih atributa
     * u odgovarajucu podtabelu konkretnog tipa bicikle.
     *
     * @param objekat objekat tipa {@link Bicikla} koji se kreira
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!postoji) {
            Bicikla bicikla = (Bicikla) objekat;
            String upit1 = "INSERT INTO bicikla (cenaPoSatu, cenaPoDanu, marka, model, boja) VALUES ("
                    + bicikla.getCenaPoSatu() + "," + bicikla.getCenaPoDanu()
                    + ",'" + bicikla.getMarka() + "','" + bicikla.getModel()
                    + "','" + bicikla.getBoja() + "')";
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            st.executeUpdate(upit1, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            int id = -1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            bicikla.setIdBicikla(id);
            String upit2 = bicikla.vratiUpisPodTabele(id);
            st.executeUpdate(upit2);
            uspesno = true;
        }
    }
}