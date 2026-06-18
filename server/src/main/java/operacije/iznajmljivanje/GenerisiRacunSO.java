package operacije.iznajmljivanje;

import java.io.IOException;
import model.Iznajmljivanje;
import operacije.ApstraktnaGenerickaOperacija;
import json.JsonUtil;

/**
 * Sistemska operacija koja generise JSON racun za dato iznajmljivanje.
 * Racun se cuva u fajl sistemu servera u folderu "racuni".
 *
 * @author Andrijana Opacic
 * @see Iznajmljivanje
 * @see JsonUtil
 */
public class GenerisiRacunSO extends ApstraktnaGenerickaOperacija {

	/** Indikator uspesnosti generisanja racuna. */
    private boolean uspesno = false;

    /**
    * Vraca indikator uspesnosti generisanja racuna.
    *
    * @return true ako je racun uspesno generisan, false inace
    */
    public boolean getUspesno() {
        return uspesno;
    }

    /**
     * Proverava preduslove pre generisanja racuna.
     * Proverava da li je prosledjen parametar odgovarajuceg tipa, da li je
     * ID iznajmljivanja ispravan, i da li iznajmljivanje ima bar jednu stavku.
     *
     * @param objekat objekat tipa {@link Iznajmljivanje} za koje se generise racun
     * @throws Exception ako parametar nije odgovarajuceg tipa, ako ID
     *         iznajmljivanja nije ispravan, ili ako iznajmljivanje nema stavki
     */
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

    /**
     * Izvrsava generisanje JSON racuna za dato iznajmljivanje koristeci
     * {@link JsonUtil#serijalizujRacun(Iznajmljivanje, String)}. Racun se
     * cuva u fajlu "racuni/racun_{idIznajmljivanje}.json".
     *
     * @param objekat objekat tipa {@link Iznajmljivanje} za koje se generise racun
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske prilikom kreiranja racuna
     */
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