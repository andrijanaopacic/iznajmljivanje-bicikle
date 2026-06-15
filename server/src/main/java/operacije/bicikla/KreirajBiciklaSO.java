package operacije.bicikla;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Bicikla;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

public class KreirajBiciklaSO extends ApstraktnaGenerickaOperacija {

    private boolean uspesno = false;
    private boolean postoji = false;

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

    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!postoji) {
            Bicikla bicikla = (Bicikla) objekat;

            // Prvo ubaci u bicikla tabelu
            String upit1 = "INSERT INTO bicikla (cenaPoSatu, cenaPoDanu, marka, model, boja) VALUES ("
                    + bicikla.getCenaPoSatu() + "," + bicikla.getCenaPoDanu()
                    + ",'" + bicikla.getMarka() + "','" + bicikla.getModel()
                    + "','" + bicikla.getBoja() + "')";
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            st.executeUpdate(upit1, Statement.RETURN_GENERATED_KEYS);

            // Uzmi generisani ID
            ResultSet rs = st.getGeneratedKeys();
            int id = -1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            bicikla.setIdBicikla(id);

            // Ubaci specificne atribute u podtabelu
            String upit2 = bicikla.vratiUpisPodTabele(id);
            st.executeUpdate(upit2);

            uspesno = true;
        }
    }
}