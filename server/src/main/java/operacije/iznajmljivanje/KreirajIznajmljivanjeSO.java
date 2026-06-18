package operacije.iznajmljivanje;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

/**
 * Sistemska operacija za kreiranje novog iznajmljivanja zajedno sa svim
 * njegovim stavkama. Pre kreiranja proverava da li identicno iznajmljivanje
 * (sa istim ukupnim iznosom, prodavcem, kupcem i istim stavkama) vec
 * postoji u bazi podataka. Nakon uspesnog kreiranja, automatski generise
 * i JSON racun za novokreirano iznajmljivanje.
 *
 * @author Andrijana Opacic
 * @see Iznajmljivanje
 * @see StavkaIznajmljivanja
 * @see GenerisiRacunSO
 */
public class KreirajIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

	/** Indikator uspesnosti kreiranja iznajmljivanja. */
    private boolean uspesno = false;
    
    /** Indikator da li identicno iznajmljivanje vec postoji u bazi podataka. */
    private boolean postoji = false;

    /**
     * Vraca indikator uspesnosti kreiranja iznajmljivanja.
     *
     * @return true ako je iznajmljivanje uspesno kreirano, false inace
     */
    public boolean getUspesno() {
        return uspesno;
    }

    /**
     * Proverava preduslove pre kreiranja iznajmljivanja.
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i da li
     * identicno iznajmljivanje (sa istim ukupnim iznosom, prodavcem, kupcem
     * i istim brojem podudarajucih stavki) vec postoji u bazi podataka.
     * Iznajmljivanje se smatra postojecim samo ako se broj podudarajucih
     * stavki poklapa sa ukupnim brojem stavki prosledjenog iznajmljivanja.
     *
     * @param objekat objekat tipa {@link Iznajmljivanje} koje se kreira
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
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

    /**
     * Izvrsava kreiranje iznajmljivanja u bazi podataka.
     * Iznajmljivanje se kreira samo ako ne postoji u bazi podataka. Nakon
     * dodavanja iznajmljivanja, preuzima se automatski generisani ID i
     * koristi se da se sve stavke iznajmljivanja povezu sa njim i upisu u
     * bazu. Na kraju se pokusava generisati JSON racun za novokreirano
     * iznajmljivanje; ukoliko generisanje racuna ne uspe, greska se samo
     * zapisuje u konzolu i ne prekida izvrsavanje operacije.
     *
     * @param objekat objekat tipa {@link Iznajmljivanje} koje se kreira
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
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
            
            try {
                GenerisiRacunSO racunSO = new GenerisiRacunSO();
                racunSO.izvrsiOperaciju(iznajmljivanje, null);
            } catch (Exception e) {
                System.err.println("Upozorenje: Račun nije generisan: " + e.getMessage());
            }
        }
    }
}