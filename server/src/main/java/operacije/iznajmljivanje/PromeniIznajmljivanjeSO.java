package operacije.iznajmljivanje;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

/**
 * Sistemska operacija za izmenu postojeceg iznajmljivanja zajedno sa njegovim stavkama. Pre izmene proverava da li su vrednosti atributa
 * iznajmljivanja validne, i da li neko drugo iznajmljivanje (sa razlicitim ID-om) vec ima identicne podatke (ukupan iznos, prodavac, kupac i
 * stavke). Prilikom izmene, stavke koje vise ne postoje u prosledjenom iznajmljivanju se brisu, postojece stavke se azuriraju, 
 * a nove stavke se dodaju. Nakon izmene, ponovo se generise i JSON racun.
 *
 * @author Andrijana Opacic
 * @see Iznajmljivanje
 * @see StavkaIznajmljivanja
 * @see GenerisiRacunSO
 */
public class PromeniIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

	/** Indikator uspesnosti izmene iznajmljivanja. */
    private boolean uspesno = false;
    
    /** Indikator da li drugo iznajmljivanje sa identicnim podacima vec postoji u bazi podataka. */
    private boolean postoji = false;

    /**
     * Vraca indikator uspesnosti izmene iznajmljivanja.
     *
     * @return true ako je iznajmljivanje uspesno izmenjeno, false inace
     */
    public boolean getUspesno() {
        return uspesno;
    }

    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa, da li su vrednosti atributa iznajmljivanja validne 
     * (u skladu sa Jakarta Validation anotacijama definisanim u {@link Iznajmljivanje}), i da li
     * neko drugo iznajmljivanje (razlicitog ID-a) vec ima identicne podatke (ukupan iznos, prodavac, kupac i isti broj podudarajucih stavki).
     *
     * @param objekat objekat tipa {@link Iznajmljivanje} koje se izmenjuje
     * @throws Exception ako parametar nije odgovarajuceg tipa, ili ako
     *         vrednosti atributa nisu validne
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Iznajmljivanje)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) objekat;

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Iznajmljivanje>> violations = validator.validate(iznajmljivanje);
        if (!violations.isEmpty()) {
            StringBuilder poruka = new StringBuilder();
            for (ConstraintViolation<Iznajmljivanje> v : violations) {
                poruka.append(v.getMessage()).append(" ");
            }
            throw new Exception(poruka.toString().trim());
        }

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

    /**
     * Izmena se vrsi samo ako ne postoji drugo iznajmljivanje sa identicnim podacima. Stavke koje su prethodno postojale u bazi, a vise se ne
     * nalaze u prosledjenoj listi stavki, se brisu. Stavke koje vec postoje u bazi se azuriraju, a nove stavke se dodaju. 
     * Nakon obrade stavki, ukupan iznos iznajmljivanja se ponovo izracunava na osnovu zbira iznosa svih prosledjenih stavki, 
     * a iznajmljivanje se azurira u bazi.
     * Na kraju se pokusava ponovo generisati JSON racun za izmenjeno iznajmljivanje; ukoliko generisanje racuna ne uspe, 
     * greska se samo zapisuje u konzolu i ne prekida izvrsavanje operacije.
     *
     * @param objekat objekat tipa {@link Iznajmljivanje} koje se izmenjuje
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
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

            List<StavkaIznajmljivanja> zaBrisanje = stavkeIzBaze.stream()
                    .filter(stBaza -> stavkeProsledjene.stream()
                    .noneMatch(st -> st.getIdStavkaIznajmljivanja() == stBaza.getIdStavkaIznajmljivanja()))
                    .collect(Collectors.toList());
            
            for (StavkaIznajmljivanja stBaza : zaBrisanje) {
                stBaza.setIznajmljivanje(iznajmljivanjeZaPromenu);
                broker.delete(stBaza);
            }
            
            for (StavkaIznajmljivanja st : stavkeProsledjene) {
                boolean postojiUBazi = stavkeIzBaze.stream()
                        .anyMatch(stBaza -> st.getIdStavkaIznajmljivanja() == stBaza.getIdStavkaIznajmljivanja());
                if (postojiUBazi) {
                    broker.edit(st);
                } else {
                    st.setIznajmljivanje(iznajmljivanjeZaPromenu);
                    broker.add(st);
                }
            }
            
            double ukupanIznos = stavkeProsledjene.stream()
                    .mapToDouble(StavkaIznajmljivanja::getIznos)
                    .sum();

            iznajmljivanjeZaPromenu.setUkupanIznos(ukupanIznos);
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