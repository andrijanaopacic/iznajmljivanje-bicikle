package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BiciklaZaOdrasle extends Bicikla {

    private int velicinaTockova;
    private int brojBrzina;

    public BiciklaZaOdrasle() {
    }

    public BiciklaZaOdrasle(int idBicikla, double cenaPoSatu, double cenaPoDanu,
            String marka, String model, String boja,
            int velicinaTockova, int brojBrzina) {
        super(idBicikla, cenaPoSatu, cenaPoDanu, marka, model, boja);
        this.velicinaTockova = velicinaTockova;
        this.brojBrzina = brojBrzina;
    }

    public BiciklaZaOdrasle(double cenaPoSatu, double cenaPoDanu,
            String marka, String model, String boja,
            int velicinaTockova, int brojBrzina) {
        super(cenaPoSatu, cenaPoDanu, marka, model, boja);
        this.velicinaTockova = velicinaTockova;
        this.brojBrzina = brojBrzina;
    }

    public int getVelicinaTockova() { return velicinaTockova; }
    public void setVelicinaTockova(int velicinaTockova) { this.velicinaTockova = velicinaTockova; }
    public int getBrojBrzina() { return brojBrzina; }
    public void setBrojBrzina(int brojBrzina) { this.brojBrzina = brojBrzina; }

    @Override
    public String toString() {
        return "Bicikla za odrasle";
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
        return "biciklazaodrasle";
    }

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