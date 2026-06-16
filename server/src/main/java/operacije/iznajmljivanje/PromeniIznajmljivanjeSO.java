package operacije.iznajmljivanje;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

public class PromeniIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

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
                    + " JOIN kupac k ON i.idKupac = k.idKupac"
                    + " JOIN bicikla b ON b.idBicikla = si.idBicikla"
                    + " LEFT JOIN biciklazaodrasle bo ON si.idBicikla = bo.idBicikla"
                    + " LEFT JOIN biciklazadecu bd ON si.idBicikla = bd.idBicikla"
                    + " LEFT JOIN biciklasariksom br ON si.idBicikla = br.idBicikla"
                    + " WHERE i.ukupanIznos = " + iznajmljivanje.getUkupanIznos()
                    + " AND p.idProdavac = " + iznajmljivanje.getProdavac().getIdProdavac()
                    + " AND k.idKupac = " + iznajmljivanje.getKupac().getIdKupac()
                    + " AND i.idIznajmljivanje != " + iznajmljivanje.getIdIznajmljivanje();

            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {
                double cena = rs.getDouble("si.cena");
                double iznos = rs.getDouble("si.iznos");
                int brojSati = rs.getInt("si.brojSati");
                int brojDana = rs.getInt("si.brojDana");
                LocalDateTime vremeOdBaza = rs.getTimestamp("si.vremeOd").toLocalDateTime();
                LocalDateTime vremeDoBaza = rs.getTimestamp("si.vremeDo").toLocalDateTime();
                int idBicikla = rs.getInt("si.idBicikla");

                for (StavkaIznajmljivanja stavka : iznajmljivanje.getListaStavkiIznajmljivanja()) {
                    if (stavka.getCena() == cena
                            && stavka.getIznos() == iznos
                            && stavka.getBrojSati() == brojSati
                            && stavka.getBrojDana() == brojDana
                            && stavka.getVremeOd().equals(vremeOdBaza)
                            && stavka.getVremeDo().equals(vremeDoBaza)
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
            Iznajmljivanje iznajmljivanjeZaPromenu = (Iznajmljivanje) objekat;
            List<StavkaIznajmljivanja> stavkeProsledjene = iznajmljivanjeZaPromenu.getListaStavkiIznajmljivanja();

            String upit = " JOIN bicikla ON bicikla.idBicikla = stavkaiznajmljivanja.idBicikla"
                    + " LEFT JOIN biciklazaodrasle ON biciklazaodrasle.idBicikla = stavkaiznajmljivanja.idBicikla"
                    + " LEFT JOIN biciklazadecu ON biciklazadecu.idBicikla = stavkaiznajmljivanja.idBicikla"
                    + " LEFT JOIN biciklasariksom ON biciklasariksom.idBicikla = stavkaiznajmljivanja.idBicikla"
                    + " JOIN iznajmljivanje ON iznajmljivanje.idIznajmljivanje = stavkaiznajmljivanja.idIznajmljivanje"
                    + " WHERE iznajmljivanje.idIznajmljivanje = " + iznajmljivanjeZaPromenu.getIdIznajmljivanje();

            List<StavkaIznajmljivanja> stavkeIzBaze = broker.getAll(new StavkaIznajmljivanja(), upit);

            for (StavkaIznajmljivanja stBaza : stavkeIzBaze) {
                boolean postojiUListiProsledjenih = false;
                for (StavkaIznajmljivanja st : stavkeProsledjene) {
                    if (stBaza.getIdStavkaIznajmljivanja() == st.getIdStavkaIznajmljivanja()) {
                        postojiUListiProsledjenih = true;
                        break;
                    }
                }
                if (!postojiUListiProsledjenih) {
                    stBaza.setIznajmljivanje(iznajmljivanjeZaPromenu);
                    broker.delete(stBaza);
                }
            }

            for (StavkaIznajmljivanja st : stavkeProsledjene) {
                boolean postojiUBazi = false;
                for (StavkaIznajmljivanja stBaza : stavkeIzBaze) {
                    if (st.getIdStavkaIznajmljivanja() == stBaza.getIdStavkaIznajmljivanja()) {
                        postojiUBazi = true;
                        break;
                    }
                }
                if (postojiUBazi) {
                    broker.edit(st);
                } else {
                    st.setIznajmljivanje(iznajmljivanjeZaPromenu);
                    broker.add(st);
                }
            }

            broker.edit(iznajmljivanjeZaPromenu);
            uspesno = true;
            try {
            	GenerisiRacunSO racunSO = new GenerisiRacunSO();
                racunSO.izvrsiOperaciju(iznajmljivanjeZaPromenu, null);
            } catch (Exception e) {
                System.err.println("Upozorenje: Račun nije promenjen: " + e.getMessage());
            }
        }
        
    }
    
}