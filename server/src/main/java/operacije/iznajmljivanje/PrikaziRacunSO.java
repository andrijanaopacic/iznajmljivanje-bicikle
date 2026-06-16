package operacije.iznajmljivanje;

import json.JsonUtil;
import model.Iznajmljivanje;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija koja cita prethodno generisani JSON racun
 * i vraca ga kao String za prikaz korisniku.
 */
public class PrikaziRacunSO extends ApstraktnaGenerickaOperacija {

    private String racun;

    public String getRacun() {
        return racun;
    }

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Iznajmljivanje)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) objekat;
        if (iznajmljivanje.getIdIznajmljivanje() <= 0) {
            throw new Exception("ID iznajmljivanja nije ispravan.");
        }
    }

    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) objekat;
        String putanja = "racuni/racun_" + iznajmljivanje.getIdIznajmljivanje() + ".json";

        java.io.File fajl = new java.io.File(putanja);
        if (!fajl.exists()) {
            racun = null;
            return;
        }

        racun = JsonUtil.deserijalizujRacun(putanja);
    }
}