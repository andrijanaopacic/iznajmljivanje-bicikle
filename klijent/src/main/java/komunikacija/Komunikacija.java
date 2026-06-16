/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import koordinator.Koordinator;
import model.Bicikla;
import model.Iznajmljivanje;
import model.Kupac;
import model.Mesto;
import model.Prodavac;
import model.Termin;
import transfer.Odgovor;
import transfer.Zahtev;

/**
 *
 * @author HP
 */
public class Komunikacija {
    
    private Socket soket;
    private static Komunikacija instance;
    private int port;
    private Posiljalac posiljalac;
    private Primalac primalac;
    public static boolean uspeh;
    
    
    private Komunikacija() {
    }
    
    public static Komunikacija getInstance(){
        if(instance == null){
            instance = new Komunikacija();
        }
        return instance;
    }

    public void setSoket(Socket soket) {
        this.soket = soket;
    }
    
    
    public void povezivanje() throws Exception {

        ucitajBrojPorta();
        try {
            soket = new Socket("localhost", port);
            posiljalac = new Posiljalac(soket);
            primalac = new Primalac(soket);
            uspeh = true;
        } catch (IOException ex) {
            System.err.println("Neuspešno povezivanje na server: " + ex.getMessage());
            uspeh = false;

        }

    }
    
    private void ucitajBrojPorta() throws Exception {
        String putanja = "konfiguracija/configKlijent.properties";

        Properties p = new Properties();

        try (FileInputStream fis = new FileInputStream(putanja)) {
            p.load(fis);

            try {
                port = Integer.parseInt(p.getProperty("port"));
            } catch (NumberFormatException numberFormatException) {
                System.err.println("Broj porta nije dat u ispravnom formatu.");

            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Greška pri učitavanju konfiguracionog fajla. Unesite broj porta.", "Greška", JOptionPane.ERROR_MESSAGE);
            Koordinator.getInstance().otvoriKreirajKonfiguraciju();
            throw e;

        }
    }

    public Prodavac login(String korisnickoIme, String sifra) throws Exception {
        Prodavac p = new Prodavac(korisnickoIme, sifra);
        Zahtev zahtev = new Zahtev(Operacije.PRIJAVI_PRODAVAC, p);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            throw new Exception("Server nije u funkciji.");
        }
        Prodavac prodavac = (Prodavac) so.getOdgovor();
        return prodavac;
    }

    public void odjaviSe(Prodavac prodavac) {
        Zahtev zahtev = new Zahtev(Operacije.ODJAVI_SE, prodavac);

        posiljalac.posalji(zahtev);

        if (soket != null && !soket.isClosed()) {
            try {
                soket.close();
                instance = null;

            } catch (IOException ex) {
                System.err.println("Greška prilikom odjavljivanja: " + ex.getMessage());
            }

        }
    }

    public boolean dodajPrijavljenogKorisnika(Prodavac prodavac) {
        Zahtev zahtev = new Zahtev(Operacije.DODAJ_PRIJAVLJENOG, prodavac);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);

            return false;
        }
        return (boolean) so.getOdgovor();
    }
    
    public List<Prodavac> vratiListuSviProdavci() {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_SVI_PRODAVCI, null);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Prodavac> lista = (List<Prodavac>) so.getOdgovor();
        return lista;

    }

    public List<Mesto> vratiListuSviMesto() {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_SVI_MESTO, null);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Mesto> lista = (List<Mesto>) so.getOdgovor();
        return lista;
    }

    public boolean kreirajKupac(Kupac kupac) {
       Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_KUPAC, kupac);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public boolean promeniKupac(Kupac k) {
        Zahtev zahtev = new Zahtev(Operacije.PROMENI_KUPAC, k);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public boolean obrisiKupac(Kupac k) {
        Zahtev zahtev = new Zahtev(Operacije.OBRISI_KUPAC, k);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public List<Kupac> vratiListuSviKupci() {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_SVI_KUPCI, null);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Kupac> lista = (List<Kupac>) so.getOdgovor();
        return lista;
    }

    public List<Kupac> vratiListuKupacKupac(String kupac) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_KUPAC_KUPAC, kupac);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Kupac> lista = (List<Kupac>) so.getOdgovor();
        return lista;
    }

    public List<Kupac> vratiListuKupacMesto(String mesto) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_KUPAC_MESTO, mesto);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Kupac> lista = (List<Kupac>) so.getOdgovor();
        return lista;
    }

    public Kupac pretraziKupac(Kupac kupac) {
       Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_KUPAC, kupac);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);

            return null;
        }
        return (Kupac) so.getOdgovor();
    }

    public boolean kreirajProdavac(Prodavac prodavac) {
        System.out.println("USAO U DEO KREIRAJ PRODAVAC - KOMUNIKACIJA");
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_PRODAVAC, prodavac);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public boolean promeniProdavac(Prodavac prodavac) {
        Zahtev zahtev = new Zahtev(Operacije.PROMENI_PRODAVAC, prodavac);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public boolean obrisiProdavac(Prodavac p) {
        Zahtev zahtev = new Zahtev(Operacije.OBRISI_PRODAVAC, p);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public List<Termin> vratiListuSviTermini() {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_SVI_TERMIN_DEZURSTVA, null);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Termin> lista = (List<Termin>) so.getOdgovor();
        return lista;
    }

    public List<Prodavac> vratiListuProdavacProdavac(String prodavac) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_PRODAVAC_PRODAVAC, prodavac);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Prodavac> lista = (List<Prodavac>) so.getOdgovor();
        return lista;
    }

    public List<Prodavac> vratiListuProdavacTermin(Termin termin) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_PRODAVAC_TERMIN, termin);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Prodavac> lista = (List<Prodavac>) so.getOdgovor();
        return lista;
    }

    public Prodavac pretraziProdavac(Prodavac prodavac) {
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_PRODAVAC, prodavac);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);

            return null;
        }
        return (Prodavac) so.getOdgovor();
    }

    public boolean dodajTermin(Termin t) {
        Zahtev zahtev = new Zahtev(Operacije.UBACI_TERMIN, t);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public boolean promeniTermin(Termin t) {
        Zahtev zahtev = new Zahtev(Operacije.PROMENI_TERMIN, t);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public boolean obrisiTermin(Termin t) {
        Zahtev zahtev = new Zahtev(Operacije.OBRISI_TERMIN, t);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public List<Termin> vratiListuTerminTermin(Termin t) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_TERMIN_TERMIN, t);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Termin> lista = (List<Termin>) so.getOdgovor();
        return lista;
    }

    public Termin pretraziTermin(Termin t) {
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_TERMIN, t);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);

            return null;
        }
        return (Termin) so.getOdgovor();
    }

    public boolean kreirajIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_IZNAJMLJIVANJE, iznajmljivanje);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;

    }

    public boolean promeniIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        Zahtev zahtev = new Zahtev(Operacije.PROMENI_IZNAJMLJIVANJE, iznajmljivanje);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public List<Iznajmljivanje> vratiListuSvaIznajmljivanja() {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_SVA_IZNAJMLJIVANJA, null);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();

        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Iznajmljivanje> lista = (List<Iznajmljivanje>) so.getOdgovor();
        return lista;
    }

    public List<Iznajmljivanje> vratiIznajmljivanjaPoID(int id) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_IZNAJMLJIVANJE_IZNAJMLJIVANJE, id);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Iznajmljivanje> lista = (List<Iznajmljivanje>) so.getOdgovor();
        return lista;
    }

    public List<Iznajmljivanje> vratiIznajmljivanjaPoImenuProdavca(String prodavac) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_IZNAJMLJIVANJE_PRODAVAC, prodavac);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Iznajmljivanje> lista = (List<Iznajmljivanje>) so.getOdgovor();
        return lista;
    }

    public List<Iznajmljivanje> vratiIznajmljivanjaPoImenuKupca(String kupac) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_IZNAJMLJIVANJE_KUPAC, kupac);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Iznajmljivanje> lista = (List<Iznajmljivanje>) so.getOdgovor();
        return lista;
    }

    public List<Iznajmljivanje> vratiIznajmljivanjaPoTipuBicikle(Bicikla bicikla) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_IZNAJMLJIVANJE_BICIKLA, bicikla);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Iznajmljivanje> lista = (List<Iznajmljivanje>) so.getOdgovor();
        return lista;
    }

    public Iznajmljivanje pretraziIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_IZNAJMLJIVANJE, iznajmljivanje);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);

            return null;
        }
        return (Iznajmljivanje) so.getOdgovor();
    }

    public List<Bicikla> vratiListuSviBicikla() {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_SVI_BICIKLA, null);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Bicikla> lista = (List<Bicikla>) so.getOdgovor();
        return lista;
    }

    public boolean kreirajBicikla(Bicikla b) {
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_BICIKLA, b);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public boolean promeniBicikla(Bicikla b) {
        Zahtev zahtev = new Zahtev(Operacije.PROMENI_BICIKLA, b);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public boolean obrisiBicikla(Bicikla b) {
        Zahtev zahtev = new Zahtev(Operacije.OBRISI_BICIKLA, b);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public List<Bicikla> vratiListuBiciklaBicikla(Bicikla b) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_BICIKLA_BICIKLA, b);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Bicikla> lista = (List<Bicikla>) so.getOdgovor();
        return lista;
    }

    public Bicikla pretraziBicikla(Bicikla b) {
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_BICIKLA, b);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);

            return null;
        }
        return (Bicikla) so.getOdgovor();
    }

    public boolean kreirajMesto(Mesto m) {
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_MESTO, m);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public boolean promeniMesto(Mesto m) {
        Zahtev zahtev = new Zahtev(Operacije.PROMENI_MESTO, m);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public boolean obrisiMesto(Mesto m) {
        Zahtev zahtev = new Zahtev(Operacije.OBRISI_MESTO, m);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public List<Mesto> vratiListuMestoMesto(Mesto m) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_MESTO_MESTO, m);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Mesto> lista = (List<Mesto>) so.getOdgovor();
        return lista;
    }

    public Mesto pretraziMesto(Mesto m) {
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_MESTO, m);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);

            return null;
        }
        return (Mesto) so.getOdgovor();
    }

    public String prikaziRacun(Iznajmljivanje iznajmljivanje) {
        Zahtev zahtev = new Zahtev(Operacije.PRIKAZI_RACUN, iznajmljivanje);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return (String) so.getOdgovor();
    }
    
    
}
