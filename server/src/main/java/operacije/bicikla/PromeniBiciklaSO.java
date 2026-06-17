package operacije.bicikla;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Bicikla;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

/**
 * Sistemska operacija za izmenu postojece bicikle.
 * Pre izmene proverava da li neka druga bicikla (sa razlicitim ID-om)
 * vec ima iste atribute (marka, model, boja, cena po satu i cena po danu).
 *
 * @author Andrijana Opacic
 * @see Bicikla
 */
public class PromeniBiciklaSO extends ApstraktnaGenerickaOperacija {

	/** Indikator uspesnosti izmene bicikle. */
    private boolean uspesno = false;
    
    /** Indikator da li druga bicikla sa istim atributima vec postoji u bazi podataka. */
    private boolean postoji = false;

    /**
     * Vraca indikator uspesnosti izmene bicikle.
     *
     * @return true ako je bicikla uspesno izmenjena, false inace
     */
    public boolean getUspesno() {
        return uspesno;
    }

    /**
     * Proverava preduslove pre izmene bicikle.
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i
     * da li neka druga bicikla (razlicitog ID-a) vec ima iste atribute
     * (marka, model, boja, cena po satu i cena po danu).
     *
     * @param objekat objekat tipa {@link Bicikla} koji se izmenjuje
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
                    + " WHERE marka='" + bicikla.getMarka()
                    + "' AND model='" + bicikla.getModel()
                    + "' AND boja='" + bicikla.getBoja()
                    + "' AND cenaPoSatu=" + bicikla.getCenaPoSatu()
                    + " AND cenaPoDanu=" + bicikla.getCenaPoDanu()
                    + " AND idBicikla != " + bicikla.getIdBicikla();
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
     * Izvrsava izmenu bicikle u bazi podataka.
     * Bicikla se izmenjuje samo ako ne postoji druga bicikla sa istim
     * atributima. Izmenjuju se i zajednicki atributi u tabeli "bicikla"
     * i specificni atributi u podtabeli konkretnog tipa bicikle.
     *
     * @param objekat objekat tipa {@link Bicikla} koji se izmenjuje
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!postoji) {
            Bicikla bicikla = (Bicikla) objekat;

            String upit1 = "UPDATE bicikla SET"
                    + " cenaPoSatu=" + bicikla.getCenaPoSatu()
                    + ", cenaPoDanu=" + bicikla.getCenaPoDanu()
                    + ", marka='" + bicikla.getMarka()
                    + "', model='" + bicikla.getModel()
                    + "', boja='" + bicikla.getBoja()
                    + "' WHERE idBicikla=" + bicikla.getIdBicikla();
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            st.executeUpdate(upit1);

            String upit2 = bicikla.vratiIzmenePodTabele();
            st.executeUpdate(upit2);

            uspesno = true;
        }
    }
}