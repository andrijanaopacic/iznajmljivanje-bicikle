package operacije.bicikla;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Bicikla;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

public class PromeniBiciklaSO extends ApstraktnaGenerickaOperacija {

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
            // Proveravamo samo u bicikla tabeli
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

    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!postoji) {
            Bicikla bicikla = (Bicikla) objekat;

            // Izmeni zajednicke atribute u bicikla tabeli
            String upit1 = "UPDATE bicikla SET"
                    + " cenaPoSatu=" + bicikla.getCenaPoSatu()
                    + ", cenaPoDanu=" + bicikla.getCenaPoDanu()
                    + ", marka='" + bicikla.getMarka()
                    + "', model='" + bicikla.getModel()
                    + "', boja='" + bicikla.getBoja()
                    + "' WHERE idBicikla=" + bicikla.getIdBicikla();
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            st.executeUpdate(upit1);

            // Izmeni specificne atribute u podtabeli
            String upit2 = bicikla.vratiIzmenePodTabele();
            st.executeUpdate(upit2);

            uspesno = true;
        }
    }
}