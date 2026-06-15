package operacije.iznajmljivanje;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

public class KreirajIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

    private boolean uspesno = false;
    private boolean postoji = false;

    public boolean getUspesno() {
        return uspesno;
    }

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Iznajmljivanje)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) objekat;
        int brStavki = iznajmljivanje.getListaStavkiIznajmljivanja().size();
        int brIstih = 0;

        try {
            String upit = "SELECT * FROM iznajmljivanje i"
                    + " JOIN stavkaiznajmljivanja si ON i.idIznajmljivanje = si.idIznajmljivanje"
                    + " JOIN prodavac p ON i.idProdavac = p.idProdavac"
                    + " JOIN kupac k ON k.idKupac = i.idKupac"
                    + " JOIN bicikla b ON b.idBicikla = si.idBicikla"
                    + " LEFT JOIN biciklazaodrasle bo ON si.idBicikla = bo.idBicikla"
                    + " LEFT JOIN biciklazadecu bd ON si.idBicikla = bd.idBicikla"
                    + " LEFT JOIN biciklasariksom br ON si.idBicikla = br.idBicikla"
                    + " WHERE i.ukupanIznos=" + iznajmljivanje.getUkupanIznos()
                    + " AND p.idProdavac=" + iznajmljivanje.getProdavac().getIdProdavac()
                    + " AND k.idKupac=" + iznajmljivanje.getKupac().getIdKupac();

            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {
                double cena = rs.getDouble("si.cena");
                double iznos = rs.getDouble("si.iznos");
                int brojSati = rs.getInt("si.brojSati");
                int brojDana = rs.getInt("si.brojDana");
                Date vremeOd = rs.getTimestamp("si.vremeOd");
                Date vremeDo = rs.getTimestamp("si.vremeDo");
                int idBicikla = rs.getInt("si.idBicikla");

                for (StavkaIznajmljivanja stavka : iznajmljivanje.getListaStavkiIznajmljivanja()) {
                    if (stavka.getCena() == cena
                            && stavka.getIznos() == iznos
                            && stavka.getBrojSati() == brojSati
                            && stavka.getBrojDana() == brojDana
                            && stavka.getVremeOd().equals(vremeOd)
                            && stavka.getVremeDo().equals(vremeDo)
                            && stavka.getBicikla().getIdBicikla() == idBicikla) {
                        brIstih++;
                    }
                }
            }

            if (brIstih == brStavki) {
                postoji = true;
            }

        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!postoji) {
            Iznajmljivanje iznajmljivanje = (Iznajmljivanje) objekat;

            broker.add(iznajmljivanje);

            int ID = -1;
            try {
                String upit = "SELECT * FROM iznajmljivanje WHERE idKupac = "
                        + iznajmljivanje.getKupac().getIdKupac()
                        + " AND idProdavac = " + iznajmljivanje.getProdavac().getIdProdavac()
                        + " AND ukupanIznos = " + iznajmljivanje.getUkupanIznos();

                Statement st = DBKonekcija.getInstance().getConnection().createStatement();
                ResultSet rs = st.executeQuery(upit);

                while (rs.next()) {
                    ID = rs.getInt("idIznajmljivanje");
                }

            } catch (SQLException ex) {
                throw ex;
            }

            iznajmljivanje.setIdIznajmljivanje(ID);

            for (StavkaIznajmljivanja stavka : iznajmljivanje.getListaStavkiIznajmljivanja()) {
                stavka.setIznajmljivanje(iznajmljivanje);
                broker.add(stavka);
            }

            uspesno = true;
        }
    }
}