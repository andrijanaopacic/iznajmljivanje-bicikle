package operacije.bicikla;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Bicikla;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

public class ObrisiBiciklaSO extends ApstraktnaGenerickaOperacija {

    private boolean uspesno = false;
    private boolean uUpotrebi = false;

    public boolean getUspesno() {
        return uspesno;
    }

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