package operacije.bicikla;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Bicikla;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;
/**
 * Sistemska operacija za brisanje bicikle.
 * Pre brisanja proverava da li je bicikla u upotrebi, odnosno da li
 * postoji stavka iznajmljivanja koja referencira tu biciklu.
 *
 * @author Andrijana Opacic
 * @see Bicikla
 */
public class ObrisiBiciklaSO extends ApstraktnaGenerickaOperacija {
	/** Indikator uspesnosti brisanja bicikle. */
    private boolean uspesno = false;
    
    /** Indikator da li je bicikla u upotrebi, odnosno da li postoji stavka iznajmljivanja koja je referencira. */
    private boolean uUpotrebi = false;
    /**
     * Vraca indikator uspesnosti brisanja bicikle.
     *
     * @return true ako je bicikla uspesno obrisana, false inace
     */
    public boolean getUspesno() {
        return uspesno;
    }
    /**
     * Proverava preduslove pre brisanja bicikle.
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i
     * da li postoji stavka iznajmljivanja koja referencira tu biciklu,
     * odnosno da li je bicikla u upotrebi.
     *
     * @param objekat objekat tipa {@link Bicikla} koji se brise
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Bicikla)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        Bicikla bicikla = (Bicikla) objekat;
        try {
            String upit = "SELECT * FROM bicikla"
                    + " JOIN stavkaiznajmljivanja si ON si.idBicikla = bicikla.idBicikla"
                    + " WHERE bicikla.idBicikla = " + bicikla.getIdBicikla();
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                uUpotrebi = true;
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }
    /**
     * Izvrsava brisanje bicikle iz baze podataka.
     * Bicikla se brise samo ako nije u upotrebi. Prvo se brisu specificni
     * atributi iz podtabele konkretnog tipa bicikle (zbog stranog kljuca),
     * a zatim se brise zajednicki zapis iz tabele "bicikla".
     *
     * @param objekat objekat tipa {@link Bicikla} koji se brise
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!uUpotrebi) {
            Bicikla bicikla = (Bicikla) objekat;
            String upit1 = "DELETE FROM " + bicikla.vratiNazivPodTabele()
                    + " WHERE idBicikla = " + bicikla.getIdBicikla();
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            st.executeUpdate(upit1);
            String upit2 = "DELETE FROM bicikla WHERE idBicikla = " + bicikla.getIdBicikla();
            st.executeUpdate(upit2);
            uspesno = true;
        }
    }
}