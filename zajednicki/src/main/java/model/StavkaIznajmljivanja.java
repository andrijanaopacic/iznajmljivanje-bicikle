package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Predstavlja stavku iznajmljivanja koja opisuje jednu iznajmljenu biciklu
 * u okviru iznajmljivanja. Cena i ukupan iznos stavke se automatski
 * izracunavaju na osnovu cene bicikle i broja iznajmljenih dana ili sati.
 *
 * @author Andrijana Opacic
 * @see Iznajmljivanje
 * @see Bicikla
 */
@Getter
@Setter
@NoArgsConstructor
public class StavkaIznajmljivanja implements ApstraktniDomenskiObjekat, Serializable {

	/** Iznajmljena bicikla u okviru ove stavke. */
    private Bicikla bicikla;
    
    /** Jedinstveni identifikator stavke iznajmljivanja u bazi podataka. */
    private int idStavkaIznajmljivanja;
    
    /** Ukupan iznos za ovu stavku, izracunat na osnovu cene i broja dana ili sati. */
    private double iznos;
    
    /** Cena po danu ili po satu, preuzeta od bicikle u zavisnosti od broja dana. */
    private double cena;
    
    /** Vreme od kada je bicikla iznajmljena. */
    private LocalDateTime vremeOd;
    
    /** Vreme do kada je bicikla iznajmljena. */
    private LocalDateTime vremeDo;
    
    /** Broj sati na koje je bicikla iznajmljena, ako se iznajmljuje po satu. */
    private int brojSati;
    
    /** Broj dana na koje je bicikla iznajmljena, ako se iznajmljuje po danu. */
    private int brojDana;
    
    /** Iznajmljivanje u okviru kojeg se nalazi ova stavka. */
    private Iznajmljivanje iznajmljivanje;


    /**
     * Konstruktor koji inicijalizuje sve atribute stavke iznajmljivanja ukljucujuci i ID.
     * Cena i iznos se automatski izracunavaju - ako je brojDana veci od 0, koristi
     * se cena po danu i racuna iznos za brojDana, inace se koristi cena po satu
     * i racuna iznos za brojSati.
     *
     * @param bicikla iznajmljena bicikla
     * @param idStavkaIznajmljivanja jedinstveni identifikator stavke
     * @param iznos ukupan iznos stavke (bice prepisan izracunatom vrednoscu)
     * @param cena cena stavke (bice prepisana izracunatom vrednoscu)
     * @param vremeOd vreme od kada je bicikla iznajmljena
     * @param vremeDo vreme do kada je bicikla iznajmljena
     * @param brojSati broj sati iznajmljivanja
     * @param brojDana broj dana iznajmljivanja
     * @param iznajmljivanje iznajmljivanje kojem stavka pripada
     */
    public StavkaIznajmljivanja(Bicikla bicikla, int idStavkaIznajmljivanja, double iznos, double cena,
            LocalDateTime vremeOd, LocalDateTime vremeDo, int brojSati, int brojDana,
            Iznajmljivanje iznajmljivanje) {
        this.bicikla = bicikla;
        this.idStavkaIznajmljivanja = idStavkaIznajmljivanja;
        this.vremeOd = vremeOd;
        this.vremeDo = vremeDo;
        this.brojSati = brojSati;
        this.brojDana = brojDana;
        this.iznajmljivanje = iznajmljivanje;
        if (brojDana > 0) {
            this.cena = bicikla.getCenaPoDanu();
        } else {
            this.cena = bicikla.getCenaPoSatu();
        }
        if (brojDana > 0) {
            this.iznos = this.cena * brojDana;
        } else {
            this.iznos = this.cena * brojSati;
        }
    }

    /**
     * Konstruktor koji inicijalizuje atribute stavke iznajmljivanja bez ID-a.
     * Koristi se prilikom kreiranja nove stavke pre unosa u bazu podataka.
     * Cena i iznos se automatski izracunavaju na isti nacin kao u
     * {@link #StavkaIznajmljivanja(Bicikla, int, double, double, LocalDateTime, LocalDateTime, int, int, Iznajmljivanje)}.
     *
     * @param bicikla iznajmljena bicikla
     * @param iznos ukupan iznos stavke (bice prepisan izracunatom vrednoscu)
     * @param cena cena stavke (bice prepisana izracunatom vrednoscu)
     * @param vremeOd vreme od kada je bicikla iznajmljena
     * @param vremeDo vreme do kada je bicikla iznajmljena
     * @param brojSati broj sati iznajmljivanja
     * @param brojDana broj dana iznajmljivanja
     * @param iznajmljivanje iznajmljivanje kojem stavka pripada
     */
    public StavkaIznajmljivanja(Bicikla bicikla, double iznos, double cena,
            LocalDateTime vremeOd, LocalDateTime vremeDo, int brojSati, int brojDana,
            Iznajmljivanje iznajmljivanje) {
        this.bicikla = bicikla;
        this.vremeOd = vremeOd;
        this.vremeDo = vremeDo;
        this.brojSati = brojSati;
        this.brojDana = brojDana;
        this.iznajmljivanje = iznajmljivanje;
        if (brojDana > 0) {
            this.cena = bicikla.getCenaPoDanu();
        } else {
            this.cena = bicikla.getCenaPoSatu();
        }
        if (brojDana > 0) {
            this.iznos = this.cena * brojDana;
        } else {
            this.iznos = this.cena * brojSati;
        }
    }

    /**
     * Vraca tekstualnu reprezentaciju stavke iznajmljivanja sa svim atributima.
     * Za iznajmljivanje i biciklu se prikazuje samo njihov identifikator.
     *
     * @return string sa svim atributima stavke
     */
    @Override
    public String toString() {
        return "StavkaIznajmljivanja{idStavkaIznajmljivanja=" + idStavkaIznajmljivanja
                + ", iznos=" + iznos + ", cena=" + cena
                + ", brojSati=" + brojSati + ", brojDana=" + brojDana
                + ", iznajmljivanje=" + iznajmljivanje.getIdIznajmljivanje()
                + ", bicikla=" + bicikla.getIdBicikla() + "}";
    }

    /**
     * Pomocna metoda koja odredjuje konkretan tip bicikle iz ResultSet-a.
     * Koristi vratiNazivPodTabele() da proveri koja podtabela ima vrednost
     * za dati red, sto ukazuje na tip bicikle.
     */
    private Bicikla ocitajBiciklu(ResultSet rs) throws Exception {
        List<Bicikla> tipovi = new ArrayList<>();
        tipovi.add(new BiciklaZaOdrasle());
        tipovi.add(new BiciklaZaDecu());
        tipovi.add(new BiciklaSaRiksom());

        for (Bicikla tip : tipovi) {
            rs.getInt(tip.vratiNazivPodTabele() + ".idBicikla");
            if (!rs.wasNull()) {
                return tip.citajTrenutniRed(rs);
            }
        }
        return null;
    }

    /**
     * Vraca naziv tabele "stavkaiznajmljivanja" u bazi podataka.
     *
     * @return naziv tabele "stavkaiznajmljivanja"
     */
    @Override
    public String vratiNazivTabele() {
        return "stavkaiznajmljivanja";
    }

    /**
     * Vraca listu stavki iznajmljivanja kreiranih na osnovu podataka iz ResultSet-a.
     * Svaka stavka se kreira sa iznajmljivanjem postavljenim na null - polje
     * iznajmljivanje se postavlja posebno od strane pozivajuceg koda.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return lista stavki iznajmljivanja kreiranih iz ResultSet-a, ili null
     *         ako dodje do greske pri citanju podataka
     * @throws Exception ako dodje do greske pri citanju podataka iz ResultSet-a
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        try {
            List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
            while (rs.next()) {
                int idStavkaIznajmljivanja = rs.getInt("stavkaiznajmljivanja.idStavkaIznajmljivanja");
                double iznos = rs.getDouble("stavkaiznajmljivanja.iznos");
                double cena = rs.getDouble("stavkaiznajmljivanja.cena");
                LocalDateTime vremeOd = rs.getTimestamp("stavkaiznajmljivanja.vremeOd").toLocalDateTime();
                LocalDateTime vremeDo = rs.getTimestamp("stavkaiznajmljivanja.vremeDo").toLocalDateTime();
                int brojSati = rs.getInt("stavkaiznajmljivanja.brojSati");
                int brojDana = rs.getInt("stavkaiznajmljivanja.brojDana");

                Bicikla bicikla = ocitajBiciklu(rs);

                StavkaIznajmljivanja stavka = new StavkaIznajmljivanja(bicikla,
                        idStavkaIznajmljivanja, iznos, cena,
                        vremeOd, vremeDo, brojSati, brojDana, null);
                lista.add(stavka);
            }
            return lista;
        } catch (SQLException e) {
            System.err.println("Greška: " + e.getMessage());
            return null;
        }
    }

    /**
     * Vraca jednu stavku iznajmljivanja kreiranu na osnovu podataka iz ResultSet-a,
     * zajedno sa kompletnim iznajmljivanjem (kupcem i prodavcem) kojem pripada.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return objekat stavke iznajmljivanja kreiran iz ResultSet-a, ili null
     *         ako dodje do greske pri citanju podataka
     * @throws Exception ako dodje do greske pri citanju podataka iz ResultSet-a
     */
    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        try {
            StavkaIznajmljivanja stavka = null;
            while (rs.next()) {
                int idStavkaIznajmljivanja = rs.getInt("idStavkaIznajmljivanja");
                double iznos = rs.getDouble("stavkaiznajmljivanja.iznos");
                double cena = rs.getDouble("stavkaiznajmljivanja.cena");
                LocalDateTime vremeOd = rs.getTimestamp("stavkaiznajmljivanja.vremeOd").toLocalDateTime();
                LocalDateTime vremeDo = rs.getTimestamp("stavkaiznajmljivanja.vremeDo").toLocalDateTime();
                int brojSati = rs.getInt("stavkaiznajmljivanja.brojSati");
                int brojDana = rs.getInt("stavkaiznajmljivanja.brojDana");

                Bicikla bicikla = ocitajBiciklu(rs);

                int idIznajmljivanje = rs.getInt("stavkaiznajmljivanja.idIznajmljivanje");
                double ukupanIznos = rs.getDouble("iznajmljivanje.ukupanIznos");

                int kupacID = rs.getInt("kupac.idKupac");
                String imeKupac = rs.getString("kupac.ime");
                String prezimeKupac = rs.getString("kupac.prezime");
                String brojLicneKarte = rs.getString("kupac.brojLicneKarte");
                int idMesto = rs.getInt("kupac.idMesto");
                String nazivMesta = rs.getString("mesto.naziv");
                Mesto mesto = new Mesto(idMesto, nazivMesta);
                Kupac kupac = new Kupac(kupacID, imeKupac, prezimeKupac, brojLicneKarte, mesto);

                int prodavacID = rs.getInt("prodavac.idProdavac");
                String imeProdavac = rs.getString("prodavac.ime");
                String prezimeProdavac = rs.getString("prodavac.prezime");
                String korisnickoIme = rs.getString("prodavac.korisnickoIme");
                String sifra = rs.getString("prodavac.sifra");
                Prodavac prodavac = new Prodavac(prodavacID, imeProdavac, prezimeProdavac, korisnickoIme, sifra);

                Iznajmljivanje iznajmljivanje = new Iznajmljivanje(idIznajmljivanje, ukupanIznos,
                        new ArrayList<>(), kupac, prodavac);

                stavka = new StavkaIznajmljivanja(bicikla, idStavkaIznajmljivanja, iznos, cena,
                        vremeOd, vremeDo, brojSati, brojDana, iznajmljivanje);
            }
            return stavka;
        } catch (Exception ex) {
            System.err.println("Greška: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "iznos,cena,vremeOd,vremeDo,brojSati,brojDana,idBicikla,idIznajmljivanje";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return iznos + "," + cena + ",'" + Timestamp.valueOf(vremeOd) + "','"
                + Timestamp.valueOf(vremeDo) + "'," + brojSati + "," + brojDana
                + "," + bicikla.getIdBicikla() + "," + iznajmljivanje.getIdIznajmljivanje();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "stavkaiznajmljivanja.idIznajmljivanje=" + iznajmljivanje.getIdIznajmljivanje()
                + " AND stavkaiznajmljivanja.idStavkaIznajmljivanja=" + idStavkaIznajmljivanja;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "iznos=" + iznos + ", cena=" + cena
                + ", vremeOd='" + Timestamp.valueOf(vremeOd)
                + "', vremeDo='" + Timestamp.valueOf(vremeDo)
                + "', brojSati=" + brojSati + ", brojDana=" + brojDana
                + ", idBicikla=" + bicikla.getIdBicikla();
    }
}