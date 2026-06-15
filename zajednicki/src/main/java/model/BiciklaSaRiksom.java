package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BiciklaSaRiksom extends Bicikla {

    private int brojSedista;
    private String tipRikse;
    private int maxNosivost;

    public BiciklaSaRiksom() {
    }

    public BiciklaSaRiksom(int idBicikla, double cenaPoSatu, double cenaPoDanu,
            String marka, String model, String boja,
            int brojSedista, String tipRikse, int maxNosivost) {
        super(idBicikla, cenaPoSatu, cenaPoDanu, marka, model, boja);
        this.brojSedista = brojSedista;
        this.tipRikse = tipRikse;
        this.maxNosivost = maxNosivost;
    }

    public BiciklaSaRiksom(double cenaPoSatu, double cenaPoDanu,
            String marka, String model, String boja,
            int brojSedista, String tipRikse, int maxNosivost) {
        super(cenaPoSatu, cenaPoDanu, marka, model, boja);
        this.brojSedista = brojSedista;
        this.tipRikse = tipRikse;
        this.maxNosivost = maxNosivost;
    }

    public int getBrojSedista() { return brojSedista; }
    public void setBrojSedista(int brojSedista) { this.brojSedista = brojSedista; }
    public String getTipRikse() { return tipRikse; }
    public void setTipRikse(String tipRikse) { this.tipRikse = tipRikse; }
    public int getMaxNosivost() { return maxNosivost; }
    public void setMaxNosivost(int maxNosivost) { this.maxNosivost = maxNosivost; }

    @Override
    public String toString() {
        return "Bicikla sa riksom";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdBicikla());
    }

    @Override
    public String vratiNazivPodTabele() {
        return "biciklasariksom";
    }

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
        return new BiciklaSaRiksom(id, cenaPoSatu, cenaPoDanu,
                marka, model, boja, brojSedista, tipRikse, maxNosivost);
    }
}