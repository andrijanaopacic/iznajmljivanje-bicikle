package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Predstavlja biciklu za decu koja se moze iznajmiti.
 * Pored zajednickih atributa nasledjenih od {@link Bicikla}, dodaje specificne atribute
 * koji se cuvaju u podtabeli "biciklazadecu".
 *
 * @author Andrijana Opacic
 * @see Bicikla
 */
@Getter
@Setter
@NoArgsConstructor
public class BiciklaZaDecu extends Bicikla {

	/** Velicina tockova bicikle za decu, izrazena u incima. */
    private int velicinaTockova;
    
    /** Broj brzina bicikle za decu. */
    private int brojBrzina;
    
    /** Indikator da li bicikla ima pomocne tockove. */
    private boolean pomocniTockovi;


    /**
     * Konstruktor koji inicijalizuje sve atribute bicikle za decu ukljucujuci i ID.
     *
     * @param idBicikla jedinstveni identifikator bicikle
     * @param cenaPoSatu cena iznajmljivanja po satu
     * @param cenaPoDanu cena iznajmljivanja po danu
     * @param marka marka bicikle
     * @param model model bicikle
     * @param boja boja bicikle
     * @param velicinaTockova velicina tockova u incima
     * @param brojBrzina broj brzina bicikle
     * @param pomocniTockovi true ako bicikla ima pomocne tockove, false inace
     */
    public BiciklaZaDecu(int idBicikla, double cenaPoSatu, double cenaPoDanu,
            String marka, String model, String boja,
            int velicinaTockova, int brojBrzina, boolean pomocniTockovi) {
        super(idBicikla, cenaPoSatu, cenaPoDanu, marka, model, boja);
        this.velicinaTockova = velicinaTockova;
        this.brojBrzina = brojBrzina;
        this.pomocniTockovi = pomocniTockovi;
    }

    /**
     * Konstruktor koji inicijalizuje atribute bicikle za decu bez ID-a.
     * Koristi se prilikom kreiranja nove bicikle pre unosa u bazu podataka.
     *
     * @param cenaPoSatu cena iznajmljivanja po satu
     * @param cenaPoDanu cena iznajmljivanja po danu
     * @param marka marka bicikle
     * @param model model bicikle
     * @param boja boja bicikle
     * @param velicinaTockova velicina tockova u incima
     * @param brojBrzina broj brzina bicikle
     * @param pomocniTockovi true ako bicikla ima pomocne tockove, false inace
     */
    public BiciklaZaDecu(double cenaPoSatu, double cenaPoDanu,
            String marka, String model, String boja,
            int velicinaTockova, int brojBrzina, boolean pomocniTockovi) {
        super(cenaPoSatu, cenaPoDanu, marka, model, boja);
        this.velicinaTockova = velicinaTockova;
        this.brojBrzina = brojBrzina;
        this.pomocniTockovi = pomocniTockovi;
    }

    /**
     * Vraca tekstualnu reprezentaciju tipa bicikle.
     *
     * @return string "Bicikla za decu"
     */
    @Override
    public String toString() {
        return "Bicikla za decu";
    }

    /**
     * Poredi ovu biciklu za decu sa drugim objektom.
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
     * Vraca hash kod bicikle za decu racunat na osnovu jedinstvenog identifikatora.
     *
     * @return hash kod bicikle na osnovu jedinstvenog identifikatora.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getIdBicikla());
    }

    @Override
    public String vratiNazivPodTabele() {
        return "biciklazadecu";
    }

    /**
     * Vraca JOIN izraz koji spaja tabelu "bicikla" sa podtabelom "biciklazadecu".
     * Koristi se prilikom SELECT upita kako bi se ucitali i zajednicki i
     * specificni atributi bicikle za decu.
     *
     * @return JOIN izraz za bicikle za decu
     */
    @Override
    public String vratiNazivTabele() {
        return "bicikla JOIN biciklazadecu ON bicikla.idBicikla = biciklazadecu.idBicikla";
    }

    @Override
    public String vratiUpisPodTabele(int id) {
        return "INSERT INTO biciklazadecu (idBicikla, velicinaTockova, brojBrzina, pomocniTockovi) VALUES ("
                + id + "," + velicinaTockova + "," + brojBrzina + "," + (pomocniTockovi ? 1 : 0) + ")";
    }

    @Override
    public String vratiIzmenePodTabele() {
        return "UPDATE biciklazadecu SET velicinaTockova=" + velicinaTockova
                + ", brojBrzina=" + brojBrzina
                + ", pomocniTockovi=" + (pomocniTockovi ? 1 : 0)
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
            int velicinaTockova = rs.getInt("biciklazadecu.velicinaTockova");
            int brojBrzina = rs.getInt("biciklazadecu.brojBrzina");
            boolean pomocniTockovi = rs.getBoolean("biciklazadecu.pomocniTockovi");
            lista.add(new BiciklaZaDecu(id, cenaPoSatu, cenaPoDanu, marka, model, boja, velicinaTockova, brojBrzina, pomocniTockovi));
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        BiciklaZaDecu b = null;
        while (rs.next()) {
            int id = rs.getInt("bicikla.idBicikla");
            double cenaPoSatu = rs.getDouble("bicikla.cenaPoSatu");
            double cenaPoDanu = rs.getDouble("bicikla.cenaPoDanu");
            String marka = rs.getString("bicikla.marka");
            String model = rs.getString("bicikla.model");
            String boja = rs.getString("bicikla.boja");
            int velicinaTockova = rs.getInt("biciklazadecu.velicinaTockova");
            int brojBrzina = rs.getInt("biciklazadecu.brojBrzina");
            boolean pomocniTockovi = rs.getBoolean("biciklazadecu.pomocniTockovi");
            b = new BiciklaZaDecu(id, cenaPoSatu, cenaPoDanu, marka, model, boja, velicinaTockova, brojBrzina, pomocniTockovi);
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
        int velicinaTockova = rs.getInt("biciklazadecu.velicinaTockova");
        int brojBrzina = rs.getInt("biciklazadecu.brojBrzina");
        boolean pomocniTockovi = rs.getBoolean("biciklazadecu.pomocniTockovi");
        return new BiciklaZaDecu(id, cenaPoSatu, cenaPoDanu,marka, model, boja, velicinaTockova, brojBrzina, pomocniTockovi);
    }
}