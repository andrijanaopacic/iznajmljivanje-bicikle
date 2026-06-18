package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Predstavlja biciklu sa riksom koja se moze iznajmiti za prevoz vise osoba ili tereta.
 * Pored zajednickih atributa nasledjenih od {@link Bicikla}, dodaje specificne atribute
 * koji se cuvaju u podtabeli "biciklasariksom".
 *
 * @author Andrijana Opacic
 * @see Bicikla
 */
@Getter
@Setter
@NoArgsConstructor
public class BiciklaSaRiksom extends Bicikla {

	/** Broj sedista na riksi. */
    private int brojSedista;
    
    /** Tip rikse, npr. "decija", "zatvorena" ili "otvorena". */
    private String tipRikse;
    
    /** Maksimalna nosivost rikse u kilogramima. */
    private int maxNosivost;


    /**
     * Konstruktor koji inicijalizuje sve atribute bicikle sa riksom ukljucujuci i ID.
     *
     * @param idBicikla jedinstveni identifikator bicikle
     * @param cenaPoSatu cena iznajmljivanja po satu
     * @param cenaPoDanu cena iznajmljivanja po danu
     * @param marka marka bicikle
     * @param model model bicikle
     * @param boja boja bicikle
     * @param brojSedista broj sedista na riksi
     * @param tipRikse tip rikse
     * @param maxNosivost maksimalna nosivost rikse u kilogramima
     */
    public BiciklaSaRiksom(int idBicikla, double cenaPoSatu, double cenaPoDanu, String marka, String model, String boja, int brojSedista, String tipRikse, int maxNosivost) {
        super(idBicikla, cenaPoSatu, cenaPoDanu, marka, model, boja);
        this.brojSedista = brojSedista;
        this.tipRikse = tipRikse;
        this.maxNosivost = maxNosivost;
    }

    /**
     * Konstruktor koji inicijalizuje atribute bicikle sa riksom bez ID-a.
     * Koristi se prilikom kreiranja nove bicikle pre unosa u bazu podataka.
     *
     * @param cenaPoSatu cena iznajmljivanja po satu
     * @param cenaPoDanu cena iznajmljivanja po danu
     * @param marka marka bicikle
     * @param model model bicikle
     * @param boja boja bicikle
     * @param brojSedista broj sedista na riksi
     * @param tipRikse tip rikse
     * @param maxNosivost maksimalna nosivost rikse u kilogramima
     */
    public BiciklaSaRiksom(double cenaPoSatu, double cenaPoDanu, String marka, String model, String boja, int brojSedista, String tipRikse, int maxNosivost) {
        super(cenaPoSatu, cenaPoDanu, marka, model, boja);
        this.brojSedista = brojSedista;
        this.tipRikse = tipRikse;
        this.maxNosivost = maxNosivost;
    }


    /**
     * Vraca tekstualnu reprezentaciju tipa bicikle.
     *
     * @return string "Bicikla sa riksom"
     */
    @Override
    public String toString() {
        return "Bicikla sa riksom";
    }

    /**
     * Poredi ovu biciklu sa riksom sa drugim objektom.
     * Bicikle su jednake ako su istog konkretnog tipa i imaju isti idBicikla,
     * sto se proverava delegiranjem na {@link Bicikla#equals(Object)}.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su bicikle istog tipa i imaju isti idBicikla, false ako je
     *         obj null, ako je obj drugog konkretnog tipa, ili ako se idBicikla razlikuju
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        return super.equals(obj);
    }

    /**
     * Vraca hash kod bicikle sa riksom racunat na osnovu jedinstvenog identifikatora.
     *
     * @return hash kod bicikle
     */
    @Override
    public int hashCode() {
        return Objects.hash(getIdBicikla());
    }

    @Override
    public String vratiNazivPodTabele() {
        return "biciklasariksom";
    }

    /**
     * Vraca JOIN izraz koji spaja tabelu "bicikla" sa podtabelom "biciklasariksom".
     * Koristi se prilikom SELECT upita kako bi se ucitali i zajednicki i
     * specificni atributi bicikle sa riksom.
     *
     * @return JOIN izraz za bicikle sa riksom
     */
    @Override
    public String vratiNazivTabele() {
        return "bicikla JOIN biciklasariksom ON bicikla.idBicikla = biciklasariksom.idBicikla";
    }

    @Override
    public String vratiUpisPodTabele(int id) {
        return "INSERT INTO biciklasariksom (idBicikla, brojSedista, tipRikse, maxNosivost) VALUES ("
                + id + "," + brojSedista + ",'" + tipRikse + "'," + maxNosivost + ")";
    }

    @Override
    public String vratiIzmenePodTabele() {
        return "UPDATE biciklasariksom SET brojSedista=" + brojSedista
                + ", tipRikse='" + tipRikse + "'"
                + ", maxNosivost=" + maxNosivost
                + " WHERE idBicikla=" + idBicikla;
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("bicikla.idBicikla");
            double cenaPoSatu = rs.getDouble("bicikla.cenaPoSatu");
            double cenaPoDanu = rs.getDouble("bicikla.cenaPoDanu");
            String marka = rs.getString("bicikla.marka");
            String model = rs.getString("bicikla.model");
            String boja = rs.getString("bicikla.boja");
            int brojSedista = rs.getInt("biciklasariksom.brojSedista");
            String tipRikse = rs.getString("biciklasariksom.tipRikse");
            int maxNosivost = rs.getInt("biciklasariksom.maxNosivost");
            lista.add(new BiciklaSaRiksom(id, cenaPoSatu, cenaPoDanu,
                    marka, model, boja, brojSedista, tipRikse, maxNosivost));
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        BiciklaSaRiksom b = null;
        while (rs.next()) {
            int id = rs.getInt("bicikla.idBicikla");
            double cenaPoSatu = rs.getDouble("bicikla.cenaPoSatu");
            double cenaPoDanu = rs.getDouble("bicikla.cenaPoDanu");
            String marka = rs.getString("bicikla.marka");
            String model = rs.getString("bicikla.model");
            String boja = rs.getString("bicikla.boja");
            int brojSedista = rs.getInt("biciklasariksom.brojSedista");
            String tipRikse = rs.getString("biciklasariksom.tipRikse");
            int maxNosivost = rs.getInt("biciklasariksom.maxNosivost");
            b = new BiciklaSaRiksom(id, cenaPoSatu, cenaPoDanu,
                    marka, model, boja, brojSedista, tipRikse, maxNosivost);
        }
        return b;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "cenaPoSatu,cenaPoDanu,marka,model,boja";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return cenaPoSatu + "," + cenaPoDanu + ",'"
                + marka + "','" + model + "','" + boja + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "bicikla.idBicikla=" + idBicikla;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "cenaPoSatu=" + cenaPoSatu + ", cenaPoDanu=" + cenaPoDanu
                + ", marka='" + marka + "', model='" + model + "', boja='" + boja + "'";
    }
    
    @Override
    public Bicikla citajTrenutniRed(ResultSet rs) throws Exception {
        int id = rs.getInt("bicikla.idBicikla");
        double cenaPoSatu = rs.getDouble("bicikla.cenaPoSatu");
        double cenaPoDanu = rs.getDouble("bicikla.cenaPoDanu");
        String marka = rs.getString("bicikla.marka");
        String model = rs.getString("bicikla.model");
        String boja = rs.getString("bicikla.boja");
        int brojSedista = rs.getInt("biciklasariksom.brojSedista");
        String tipRikse = rs.getString("biciklasariksom.tipRikse");
        int maxNosivost = rs.getInt("biciklasariksom.maxNosivost");
        return new BiciklaSaRiksom(id, cenaPoSatu, cenaPoDanu, marka, model, boja, brojSedista, tipRikse, maxNosivost);
    }
}