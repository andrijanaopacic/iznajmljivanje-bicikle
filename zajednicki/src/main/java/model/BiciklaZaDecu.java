package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BiciklaZaDecu extends Bicikla {

    private int velicinaTockova;
    private int brojBrzina;
    private boolean pomocniTockovi;

    public BiciklaZaDecu() {
    }

    public BiciklaZaDecu(int idBicikla, double cenaPoSatu, double cenaPoDanu,
            String marka, String model, String boja,
            int velicinaTockova, int brojBrzina, boolean pomocniTockovi) {
        super(idBicikla, cenaPoSatu, cenaPoDanu, marka, model, boja);
        this.velicinaTockova = velicinaTockova;
        this.brojBrzina = brojBrzina;
        this.pomocniTockovi = pomocniTockovi;
    }

    public BiciklaZaDecu(double cenaPoSatu, double cenaPoDanu,
            String marka, String model, String boja,
            int velicinaTockova, int brojBrzina, boolean pomocniTockovi) {
        super(cenaPoSatu, cenaPoDanu, marka, model, boja);
        this.velicinaTockova = velicinaTockova;
        this.brojBrzina = brojBrzina;
        this.pomocniTockovi = pomocniTockovi;
    }

    public int getVelicinaTockova() { return velicinaTockova; }
    public void setVelicinaTockova(int velicinaTockova) { this.velicinaTockova = velicinaTockova; }
    public int getBrojBrzina() { return brojBrzina; }
    public void setBrojBrzina(int brojBrzina) { this.brojBrzina = brojBrzina; }
    public boolean isPomocniTockovi() { return pomocniTockovi; }
    public void setPomocniTockovi(boolean pomocniTockovi) { this.pomocniTockovi = pomocniTockovi; }

    @Override
    public String toString() {
        return "Bicikla za decu";
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
        return "biciklazadecu";
    }

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
            lista.add(new BiciklaZaDecu(id, cenaPoSatu, cenaPoDanu,
                    marka, model, boja, velicinaTockova, brojBrzina, pomocniTockovi));
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
            b = new BiciklaZaDecu(id, cenaPoSatu, cenaPoDanu,
                    marka, model, boja, velicinaTockova, brojBrzina, pomocniTockovi);
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
        return new BiciklaZaDecu(id, cenaPoSatu, cenaPoDanu,
                marka, model, boja, velicinaTockova, brojBrzina, pomocniTockovi);
    }
}