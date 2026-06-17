package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Predstavlja biciklu za odrasle koja se moze iznajmiti.
 * Pored zajednickih atributa nasledjenih od {@link Bicikla}, dodaje specificne atribute
 * koji se cuvaju u podtabeli "biciklazaodrasle".
 *
 * @author Andrijana Opacic
 * @see Bicikla
 */
@Getter
@Setter
@NoArgsConstructor
public class BiciklaZaOdrasle extends Bicikla {

	/** Velicina tockova bicikle za odrasle, izrazena u incima. */
    private int velicinaTockova;
    
    /** Broj brzina bicikle za odrasle. */
    private int brojBrzina;


    /**
     * Konstruktor koji inicijalizuje sve atribute bicikle za odrasle ukljucujuci i ID.
     *
     * @param idBicikla jedinstveni identifikator bicikle
     * @param cenaPoSatu cena iznajmljivanja po satu
     * @param cenaPoDanu cena iznajmljivanja po danu
     * @param marka marka bicikle
     * @param model model bicikle
     * @param boja boja bicikle
     * @param velicinaTockova velicina tockova u incima
     * @param brojBrzina broj brzina bicikle
     */
    public BiciklaZaOdrasle(int idBicikla, double cenaPoSatu, double cenaPoDanu, String marka, String model, String boja, int velicinaTockova, int brojBrzina) {
        super(idBicikla, cenaPoSatu, cenaPoDanu, marka, model, boja);
        this.velicinaTockova = velicinaTockova;
        this.brojBrzina = brojBrzina;
    }

    /**
     * Konstruktor koji inicijalizuje atribute bicikle za odrasle bez ID-a.
     * Koristi se prilikom kreiranja nove bicikle pre unosa u bazu podataka.
     *
     * @param cenaPoSatu cena iznajmljivanja po satu
     * @param cenaPoDanu cena iznajmljivanja po danu
     * @param marka marka bicikle
     * @param model model bicikle
     * @param boja boja bicikle
     * @param velicinaTockova velicina tockova u incima
     * @param brojBrzina broj brzina bicikle
     */
    public BiciklaZaOdrasle(double cenaPoSatu, double cenaPoDanu, String marka, String model, String boja, int velicinaTockova, int brojBrzina) {
        super(cenaPoSatu, cenaPoDanu, marka, model, boja);
        this.velicinaTockova = velicinaTockova;
        this.brojBrzina = brojBrzina;
    }

    /**
     * Vraca tekstualnu reprezentaciju tipa bicikle.
     *
     * @return string "Bicikla za odrasle"
     */
    @Override
    public String toString() {
        return "Bicikla za odrasle";
    }

    /**
     * Poredi ovu biciklu za odrasle sa drugim objektom.
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
     * Vraca hash kod bicikle za odrasle racunat na osnovu jedinstvenog identifikatora.
     *
     * @return hash kod bicikle na osnovu jedinstvenog identifikatora
     */
    @Override
    public int hashCode() {
        return Objects.hash(getIdBicikla());
    }

    @Override
    public String vratiNazivPodTabele() {
        return "biciklazaodrasle";
    }

    /**
     * Vraca JOIN izraz koji spaja tabelu "bicikla" sa podtabelom "biciklazaodrasle".
     * Koristi se prilikom SELECT upita kako bi se ucitali i zajednicki i
     * specificni atributi bicikle za odrasle.
     *
     * @return JOIN izraz za bicikle za odrasle
     */
    @Override
    public String vratiNazivTabele() {
        return "bicikla JOIN biciklazaodrasle ON bicikla.idBicikla = biciklazaodrasle.idBicikla";
    }

    @Override
    public String vratiUpisPodTabele(int id) {
        return "INSERT INTO biciklazaodrasle (idBicikla, velicinaTockova, brojBrzina) VALUES ("
                + id + "," + velicinaTockova + "," + brojBrzina + ")";
    }

    @Override
    public String vratiIzmenePodTabele() {
        return "UPDATE biciklazaodrasle SET velicinaTockova=" + velicinaTockova
                + ", brojBrzina=" + brojBrzina
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
            int velicinaTockova = rs.getInt("biciklazaodrasle.velicinaTockova");
            int brojBrzina = rs.getInt("biciklazaodrasle.brojBrzina");
            lista.add(new BiciklaZaOdrasle(id, cenaPoSatu, cenaPoDanu,
                    marka, model, boja, velicinaTockova, brojBrzina));
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        BiciklaZaOdrasle b = null;
        while (rs.next()) {
            int id = rs.getInt("bicikla.idBicikla");
            double cenaPoSatu = rs.getDouble("bicikla.cenaPoSatu");
            double cenaPoDanu = rs.getDouble("bicikla.cenaPoDanu");
            String marka = rs.getString("bicikla.marka");
            String model = rs.getString("bicikla.model");
            String boja = rs.getString("bicikla.boja");
            int velicinaTockova = rs.getInt("biciklazaodrasle.velicinaTockova");
            int brojBrzina = rs.getInt("biciklazaodrasle.brojBrzina");
            b = new BiciklaZaOdrasle(id, cenaPoSatu, cenaPoDanu,
                    marka, model, boja, velicinaTockova, brojBrzina);
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
        int velicinaTockova = rs.getInt("biciklazaodrasle.velicinaTockova");
        int brojBrzina = rs.getInt("biciklazaodrasle.brojBrzina");
        return new BiciklaZaOdrasle(id, cenaPoSatu, cenaPoDanu,
                marka, model, boja, velicinaTockova, brojBrzina);
    }
}