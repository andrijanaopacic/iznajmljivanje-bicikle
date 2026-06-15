package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;

public abstract class Bicikla implements ApstraktniDomenskiObjekat, Serializable {

    protected int idBicikla;
    protected double cenaPoSatu;
    protected double cenaPoDanu;
    protected String marka;
    protected String model;
    protected String boja;

    public Bicikla() {
    }

    public Bicikla(int idBicikla, double cenaPoSatu, double cenaPoDanu,
            String marka, String model, String boja) {
        this.idBicikla = idBicikla;
        this.cenaPoSatu = cenaPoSatu;
        this.cenaPoDanu = cenaPoDanu;
        this.marka = marka;
        this.model = model;
        this.boja = boja;
    }

    public Bicikla(double cenaPoSatu, double cenaPoDanu,
            String marka, String model, String boja) {
        this.cenaPoSatu = cenaPoSatu;
        this.cenaPoDanu = cenaPoDanu;
        this.marka = marka;
        this.model = model;
        this.boja = boja;
    }

    public int getIdBicikla() { return idBicikla; }
    public void setIdBicikla(int idBicikla) { this.idBicikla = idBicikla; }
    public double getCenaPoSatu() { return cenaPoSatu; }
    public void setCenaPoSatu(double cenaPoSatu) { this.cenaPoSatu = cenaPoSatu; }
    public double getCenaPoDanu() { return cenaPoDanu; }
    public void setCenaPoDanu(double cenaPoDanu) { this.cenaPoDanu = cenaPoDanu; }
    public String getMarka() { return marka; }
    public void setMarka(String marka) { this.marka = marka; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getBoja() { return boja; }
    public void setBoja(String boja) { this.boja = boja; }

    @Override
    public int hashCode() {
        return Objects.hash(idBicikla);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Bicikla other = (Bicikla) obj;
        return this.idBicikla == other.idBicikla;
    }

    
    public abstract String vratiNazivPodTabele();

    public abstract String vratiUpisPodTabele(int id);

    public abstract String vratiIzmenePodTabele();
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