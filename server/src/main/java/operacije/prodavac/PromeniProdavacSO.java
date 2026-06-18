/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.prodavac;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import model.Prodavac;
import model.ProdavacTermin;
import model.Termin;
import operacije.ApstraktnaGenerickaOperacija;
import repozitorijum.db.DBKonekcija;

/**
 * Sistemska operacija za izmenu podataka o prodavcu.
 * Pre izmene proverava da li vec postoji prodavac sa istim podacima i istim terminima.
 * Ukoliko ne postoji, vrsi izmenu prodavca i azurira njegove termine.
 *
 * @author Andrijana Opacic
 * @see Prodavac
 */
public class PromeniProdavacSO extends ApstraktnaGenerickaOperacija {
    
	 /** Indikator da li je izmena prodavca uspesno izvrsena. */
    private boolean uspesno = false;
    
    /** Indikator da li prodavac sa istim podacima vec postoji. */
    private boolean postoji = false;

    /**
     * Vraca informaciju o uspesnosti izmene prodavca.
     *
     * @return true ako je prodavac uspesno izmenjen, false u suprotnom
     */
    public boolean getUspesno() {
        return uspesno;
    }

    /**
     * Proverava da li je prosledjeni objekat odgovarajuceg tipa.
     * Takodje proverava da li vec postoji prodavac sa istim podacima i istim dodeljenim terminima.
     *
     * @param objekat objekat tipa {@link Prodavac} koji se proverava
     * @throws Exception ako objekat nije odgovarajuceg tipa ili dodje do greske pri radu sa bazom
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Prodavac)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

        int brTer = ((Prodavac) objekat).getProdavacTermini().size();

        int brIstih = 0;
        String upit = "SELECT * FROM prodavac JOIN prodavactermin ON prodavac.idProdavac = prodavactermin.idProdavac JOIN termin ON termin.idTerminDezurstva = prodavactermin.idTermin WHERE prodavac.ime = '" + ((Prodavac) objekat).getIme() + "' AND prodavac.prezime = '" + ((Prodavac) objekat).getPrezime() + "' AND prodavac.korisnickoIme = '" + ((Prodavac) objekat).getKorisnickoIme() + "' AND prodavac.sifra = '" + ((Prodavac) objekat).getSifra() + "' ORDER BY prodavac.idProdavac";
        System.out.println(upit);
        Statement s = DBKonekcija.getInstance().getConnection().createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            String naziv = rs.getString("termin.naziv");
            Termin t = new Termin(naziv);
            LocalDate datumDezurstva = rs.getDate("datumDezurstva").toLocalDate();
            String smena = rs.getString("smena");

            ProdavacTermin pt = new ProdavacTermin(((Prodavac) objekat), t, datumDezurstva, smena);
            if (((Prodavac) objekat).getProdavacTermini().contains(pt)) {
                brIstih++;
            }
        }
        System.out.println("istii" + brIstih);
        System.out.println("brTer" + brTer);
        if (brIstih == brTer) {
            postoji = true;

        }

    }

    /**
     * Menja podatke prodavca ukoliko ne postoji duplikat.
     * Nakon izmene podataka brisu se stari termini prodavca i dodaju novi.
     *
     * @param objekat objekat tipa {@link Prodavac} koji se menja
     * @param kljuc dodatni parametar operacije
     * @throws Exception ako dodje do greske prilikom izmene
     */
    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        if (!postoji) {
            broker.edit((Prodavac) objekat);
            String upit = "DELETE FROM prodavactermin WHERE idProdavac=" + ((Prodavac) objekat).getIdProdavac();
            Statement s = DBKonekcija.getInstance().getConnection().createStatement();
            s.executeUpdate(upit);
            s.close();

            for (ProdavacTermin pt : ((Prodavac) objekat).getProdavacTermini()) {
                pt.setProdavac((Prodavac) objekat);
                broker.add(pt);
            }

            uspesno = true;

        }
    }
}
