package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Predstavlja apstraktnu biciklu sa zajednickim atributima za sve tipove bicikli
 * koje se mogu iznajmiti. Konkretni tipovi bicikle ({@link BiciklaZaOdrasle},
 * {@link BiciklaZaDecu}, {@link BiciklaSaRiksom}) nasledjuju ovu klasu i dodaju
 * svoje specificne atribute.
 * Podaci se cuvaju po principu Table Per Subclass - zajednicki atributi u tabeli
 * "bicikla", a specificni atributi u odgovarajucoj podtabeli.
 *
 * @author Andrijana Opacic
 * @see BiciklaZaOdrasle
 * @see BiciklaZaDecu
 * @see BiciklaSaRiksom
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class Bicikla implements ApstraktniDomenskiObjekat, Serializable {

	 /** Jedinstveni identifikator bicikle u bazi podataka. */
    protected int idBicikla;
    
    /** Cena iznajmljivanja bicikle po satu. */
    protected double cenaPoSatu;
    
    /** Cena iznajmljivanja bicikle po danu. */
    protected double cenaPoDanu;
    
    /** Marka bicikle. */
    protected String marka;
    
    /** Model bicikle. */
    protected String model;
    
    /** Boja bicikle. */
    protected String boja;


    /**
     * Konstruktor koji inicijalizuje sve zajednicke atribute bicikle ukljucujuci i ID.
     *
     * @param idBicikla jedinstveni identifikator bicikle
     * @param cenaPoSatu cena iznajmljivanja po satu
     * @param cenaPoDanu cena iznajmljivanja po danu
     * @param marka marka bicikle
     * @param model model bicikle
     * @param boja boja bicikle
     */
    public Bicikla(int idBicikla, double cenaPoSatu, double cenaPoDanu, String marka, String model, String boja) {
        this.idBicikla = idBicikla;
        this.cenaPoSatu = cenaPoSatu;
        this.cenaPoDanu = cenaPoDanu;
        this.marka = marka;
        this.model = model;
        this.boja = boja;
    }

    /**
     * Konstruktor koji inicijalizuje zajednicke atribute bicikle bez ID-a.
     * Koristi se prilikom kreiranja nove bicikle pre unosa u bazu podataka.
     *
     * @param cenaPoSatu cena iznajmljivanja po satu
     * @param cenaPoDanu cena iznajmljivanja po danu
     * @param marka marka bicikle
     * @param model model bicikle
     * @param boja boja bicikle
     */
    public Bicikla(double cenaPoSatu, double cenaPoDanu, String marka, String model, String boja) {
        this.cenaPoSatu = cenaPoSatu;
        this.cenaPoDanu = cenaPoDanu;
        this.marka = marka;
        this.model = model;
        this.boja = boja;
    }

    /**
     * Vraca hash kod bicikle racunat na osnovu jedinstvenog identifikatora.
     *
     * @return hash kod bicikle na osnovu jedinstvenog identifikatora
     */
    @Override
    public int hashCode() {
        return Objects.hash(idBicikla);
    }

    /**
     * Poredi dve bicikle na osnovu jedinstvenog identifikatora i konkretne klase.
     * Dve bicikle su jednake samo ako su istog konkretnog tipa i imaju isti ID.
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
        final Bicikla other = (Bicikla) obj;
        return this.idBicikla == other.idBicikla;
    }

    /**
     * Vraca naziv podtabele u kojoj se cuvaju specificni atributi konkretnog tipa bicikle.
     * Koristi se za INSERT/UPDATE u podtabelu i za prepoznavanje tipa bicikle
     * prilikom citanja podataka iz ResultSet-a.
     *
     * @return naziv podtabele konkretnog tipa bicikle
     */
    public abstract String vratiNazivPodTabele();

    /**
     * Vraca SQL upit za upis specificnih atributa konkretnog tipa bicikle u podtabelu.
     *
     * @param id jedinstveni identifikator bicikle za koju se upisuju specificni atributi
     * @return SQL INSERT upit za podtabelu
     */
    public abstract String vratiUpisPodTabele(int id);

    /**
     * Vraca SQL upit za izmenu specificnih atributa konkretnog tipa bicikle u podtabeli.
     *
     * @return SQL UPDATE upit za podtabelu
     */
    public abstract String vratiIzmenePodTabele();
    
    /**
     * Cita trenutni red ResultSet-a (bez pozivanja rs.next()) i kreira odgovarajuci
     * objekat konkretnog tipa bicikle. Koristi se kada je pozicija u ResultSet-u
     * vec postavljena od strane pozivajuceg koda.
     *
     * @param rs ResultSet pozicioniran na red koji treba procitati
     * @return objekat konkretnog tipa bicikle kreiran iz trenutnog reda
     * @throws Exception ako dodje do greske pri citanju podataka iz ResultSet-a
     */
    public abstract Bicikla citajTrenutniRed(ResultSet rs) throws Exception;

    @Override
    public abstract String vratiNazivTabele();

    @Override
    public abstract List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;

    @Override
    public abstract String vratiKoloneZaUbacivanje();

    @Override
    public abstract String vratiVrednostiZaUbacivanje();

    @Override
    public abstract String vratiPrimarniKljuc();

    @Override
    public abstract ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception;

    @Override
    public abstract String vratiVrednostiZaIzmenu();
}