package operacije.iznajmljivanje;

import json.JsonUtil;
import model.Iznajmljivanje;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija koja cita prethodno generisani JSON racun
 * i vraca ga kao String za prikaz korisniku.
 *
 * @author Andrijana Opacic
 * @see Iznajmljivanje
 * @see JsonUtil
 */
public class PrikaziRacunSO extends ApstraktnaGenerickaOperacija {

	/** Tekstualni prikaz racuna nakon izvrsavanja operacije. */
    private String racun;

    /**
     * Vraca tekstualni prikaz racuna.
     *
     * @return tekstualni prikaz racuna, ili null ako racun ne postoji
     */
    public String getRacun() {
        return racun;
    }

    /**
     * Proverava preduslove pre prikazivanja racuna.
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i
     * da li je ID iznajmljivanja ispravan - veci od nule.
     *
     * @param objekat objekat tipa {@link Iznajmljivanje} za koje se prikazuje racun
     * @throws Exception ako parametar nije odgovarajuceg tipa, ili ako
     *         ID iznajmljivanja nije ispravan
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
    }

    /**
     * Izvrsava citanje JSON racuna za dato iznajmljivanje iz fajla
     * "racuni/racun_{idIznajmljivanje}.json" i postavlja njegov tekstualni
     * prikaz. Ukoliko fajl sa racunom ne postoji, racun se postavlja na null.
     *
     * @param objekat objekat tipa {@link Iznajmljivanje} za koje se prikazuje racun
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske prilikom citanja fajla
     */
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