package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StavkaIznajmljivanja implements ApstraktniDomenskiObjekat, Serializable {

    private Bicikla bicikla;
    private int idStavkaIznajmljivanja;
    private double iznos;
    private double cena;
    private LocalDateTime vremeOd;
    private LocalDateTime vremeDo;
    private int brojSati;
    private int brojDana;
    private Iznajmljivanje iznajmljivanje;

    public StavkaIznajmljivanja() {
    }

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

    public Bicikla getBicikla() { return bicikla; }
    public void setBicikla(Bicikla bicikla) { this.bicikla = bicikla; }
    public Iznajmljivanje getIznajmljivanje() { return iznajmljivanje; }
    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) { this.iznajmljivanje = iznajmljivanje; }
    public int getIdStavkaIznajmljivanja() { return idStavkaIznajmljivanja; }
    public void setIdStavkaIznajmljivanja(int idStavkaIznajmljivanja) { this.idStavkaIznajmljivanja = idStavkaIznajmljivanja; }
    public double getIznos() { return iznos; }
    public void setIznos(double iznos) { this.iznos = iznos; }
    public double getCena() { return cena; }
    public void setCena(double cena) { this.cena = cena; }
    public LocalDateTime getVremeOd() { return vremeOd; }
    public void setVremeOd(LocalDateTime vremeOd) { this.vremeOd = vremeOd; }
    public LocalDateTime getVremeDo() { return vremeDo; }
    public void setVremeDo(LocalDateTime vremeDo) { this.vremeDo = vremeDo; }
    public int getBrojSati() { return brojSati; }
    public void setBrojSati(int brojSati) { this.brojSati = brojSati; }
    public int getBrojDana() { return brojDana; }
    public void setBrojDana(int brojDana) { this.brojDana = brojDana; }

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

    @Override
    public String vratiNazivTabele() {
        return "stavkaiznajmljivanja";
    }

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