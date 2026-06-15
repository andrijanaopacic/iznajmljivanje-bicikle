package operacije.iznajmljivanje;

import java.io.IOException;
import model.Iznajmljivanje;
import operacije.ApstraktnaGenerickaOperacija;
import json.JsonUtil;

/**
 * Sistemska operacija koja generise JSON racun za dato iznajmljivanje.
 * Racun se cuva u fajl sistemu servera u folderu "racuni".
 */
public class GenerisiRacunSO extends ApstraktnaGenerickaOperacija {

    private boolean uspesno = false;

    public boolean getUspesno() {
        return uspesno;
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
        if (iznajmljivanje.getListaStavkiIznajmljivanja() == null
                || iznajmljivanje.getListaStavkiIznajmljivanja().isEmpty()) {
            throw new Exception("Iznajmljivanje nema stavki.");
        }
    }

    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) objekat;
        String putanja = "racuni/racun_" + iznajmljivanje.getIdIznajmljivanje() + ".json";

        try {
        	JsonUtil.serijalizujRacun(iznajmljivanje, putanja);
            uspesno = true;
            System.out.println("Račun uspešno kreiran: " + putanja);
        } catch (IOException e) {
            throw new Exception("Greška pri kreiranju računa: " + e.getMessage());
        }
    }
}