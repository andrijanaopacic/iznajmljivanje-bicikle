/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import forme.ServerskaForma;
import java.util.List;
import model.Bicikla;
import model.Iznajmljivanje;
import model.Kupac;
import model.Mesto;
import model.Prodavac;
import model.Termin;
import niti.ObradaKlijentskihZahteva;
import niti.ServerskaNit;
import operacije.bicikla.KreirajBiciklaSO;
import operacije.bicikla.ObrisiBiciklaSO;
import operacije.bicikla.PretraziBiciklaSO;
import operacije.bicikla.PromeniBiciklaSO;
import operacije.bicikla.VratiListuBiciklaBiciklaSO;
import operacije.bicikla.VratiListuSviBiciklaSO;
import operacije.iznajmljivanje.GenerisiRacunSO;
import operacije.iznajmljivanje.KreirajIznajmljivanjeSO;
import operacije.iznajmljivanje.PretraziIznajmljivanjeSO;
import operacije.iznajmljivanje.PrikaziRacunSO;
import operacije.iznajmljivanje.PromeniIznajmljivanjeSO;
import operacije.iznajmljivanje.VratiListuIznajmljivanjeBiciklaSO;
import operacije.iznajmljivanje.VratiListuIznajmljivanjeIznajmljivanjeSO;
import operacije.iznajmljivanje.VratiListuIznajmljivanjeKupacSO;
import operacije.iznajmljivanje.VratiListuIznajmljivanjeProdavacSO;
import operacije.iznajmljivanje.VratiListuSviIznajmljivanjeSO;
import operacije.kupac.KreirajKupacSO;
import operacije.kupac.ObrisiKupacSO;
import operacije.kupac.PretraziKupacSO;
import operacije.kupac.PromeniKupacSO;
import operacije.kupac.VratiListuKupacKupacSO;
import operacije.kupac.VratiListuKupacMestoSO;
import operacije.kupac.VratiListuSviKupciSO;
import operacije.mesto.KreirajMestoSO;
import operacije.mesto.ObrisiMestoSO;
import operacije.mesto.PretraziMestoSO;
import operacije.mesto.PromeniMestoSO;
import operacije.mesto.VratiListuMestoMestoSO;
import operacije.mesto.VratiListuSviMestoSO;
import operacije.prodavac.KreirajProdavacSO;
import operacije.prodavac.ObrisiProdavacSO;
import operacije.prodavac.PretraziProdavacSO;
import operacije.prodavac.PrijaviProdavacSO;
import operacije.prodavac.PromeniProdavacSO;
import operacije.prodavac.VratiListuProdavacProdavacSO;
import operacije.prodavac.VratiListuProdavacTerminSO;
import operacije.prodavac.VratiListuSviProdavciSO;
import operacije.termin.ObrisiTerminSO;
import operacije.termin.PretraziTerminSO;
import operacije.termin.PromeniTerminSO;
import operacije.termin.UbaciTerminSO;
import operacije.termin.VratiListuSviTerminiSO;
import operacije.termin.VratiListuTerminTerminSO;

/**
 *
 * @author HP
 */
public class ServerKontroler {
    
    private static ServerKontroler instance;
    private Prodavac prijavljen = null;
    ServerskaForma sf = new ServerskaForma();
    
    
    public ServerKontroler() {
    }
    
    public static ServerKontroler getInstance(){
        if(instance == null)
            instance = new ServerKontroler();
        return instance;
    }

    public Prodavac getPrijavljen() {
        return prijavljen;
    }

    public void setPrijavljen(Prodavac prijavljen) {
        this.prijavljen = prijavljen;
    }

    public ServerskaForma getSf() {
        return sf;
    }

    public void setSf(ServerskaForma sf) {
        this.sf = sf;
    }

    
    
    public Prodavac prijaviProdavac(Prodavac p) throws Exception {
        PrijaviProdavacSO operacija = new PrijaviProdavacSO();
        operacija.izvrsiOperaciju(p, null);
        System.out.println("KLASA KONTROLER "+operacija.getProdavac());
        return operacija.getProdavac();
    }
    
    public void odjaviSe(Prodavac prodavac) throws Exception {
        ServerskaNit.odjaviKlijenta(prodavac);
        ServerKontroler.getInstance().getSf().osveziTabele();

    }
    
    public boolean dodajPrijavljenog(Prodavac prodavac) throws Exception {
        boolean prijavljen = false;

        for (ObradaKlijentskihZahteva korisnik : ServerskaNit.getPrijavljeniKorisnici()) {
            if (korisnik.getIme() != null && korisnik.getIme().equals(prodavac.getIme())
                    && korisnik.getPrezime() != null && korisnik.getPrezime().equals(prodavac.getPrezime())
                    && korisnik.getKorisnickoIme() != null && korisnik.getKorisnickoIme().equals(prodavac.getKorisnickoIme())
                    && korisnik.getSifra() != null && korisnik.getSifra().equals(prodavac.getSifra())) {
                prijavljen = true;

            }
        }

        if (!prijavljen) {
            for (ObradaKlijentskihZahteva korisnik : ServerskaNit.getPrijavljeniKorisnici()) {

                if (korisnik.getIme() == null
                        && korisnik.getPrezime() == null
                        && korisnik.getKorisnickoIme() == null
                        && korisnik.getSifra() == null) {
                    korisnik.setIme(prodavac.getIme());
                    korisnik.setPrezime(prodavac.getPrezime());
                    korisnik.setKorisnickoIme(prodavac.getKorisnickoIme());
                    korisnik.setSifra(prodavac.getSifra());
                    break;

                }
            }

        }
        ServerKontroler.getInstance().getSf().osveziTabele();

        return prijavljen;

    }

    public List<Prodavac> vratiListuSviProdavci() throws Exception {
        VratiListuSviProdavciSO operacija = new VratiListuSviProdavciSO();
        operacija.izvrsiOperaciju(new Prodavac(), null);
        return operacija.getLista();
    }
    
    public void osveziTabele() {
        sf.osveziTabele();
    }

    public boolean kreirajKupac(Kupac kupac) throws Exception {
        KreirajKupacSO operacija = new KreirajKupacSO();
        operacija.izvrsiOperaciju(kupac, null);
       return operacija.getUspesno();
    }
    
    public List<Kupac> vratiListuSviKupci() throws Exception{
        VratiListuSviKupciSO operacija = new VratiListuSviKupciSO();
        operacija.izvrsiOperaciju(new Kupac(), null);
        return operacija.getLista();
    }
    
    public boolean promeniKupac(Kupac kupac) throws Exception {
        PromeniKupacSO operacija = new PromeniKupacSO();
        operacija.izvrsiOperaciju(kupac, null);
        return operacija.getUspesno();
    }
    
    public boolean obrisiKupac(Kupac kupac) throws Exception {
        ObrisiKupacSO operacija = new ObrisiKupacSO();
        operacija.izvrsiOperaciju(kupac, null);
        return operacija.getUspesno();
    }
    
    public List<Kupac> vratiListuKupacKupac(String kupac) throws Exception {
        VratiListuKupacKupacSO operacija = new VratiListuKupacKupacSO();
        operacija.izvrsiOperaciju(new Kupac(), kupac);
        return operacija.getLista();
    }
    
    public List<Kupac> vratiListuKupacMesto(String mesto) throws Exception {
        VratiListuKupacMestoSO operacija = new VratiListuKupacMestoSO();
        operacija.izvrsiOperaciju(new Kupac(), mesto);
        return operacija.getLista();
    }
    
    public Kupac pretraziKupac(Kupac kupac) throws Exception {
        PretraziKupacSO operacija = new PretraziKupacSO();
        operacija.izvrsiOperaciju(kupac, null);
        return operacija.getKupac();
    }

    public List<Mesto> vratiListuSviMesto() throws Exception {
        VratiListuSviMestoSO operacija = new VratiListuSviMestoSO();
        operacija.izvrsiOperaciju(new Mesto(), null);
        return operacija.getLista();
    }
    
    public List<Termin> vratiListuSviTermini() throws Exception {
        VratiListuSviTerminiSO operacija = new VratiListuSviTerminiSO();
        operacija.izvrsiOperaciju(new Termin(), null);
        return operacija.getLista();
    }
    
    public List<Prodavac> vratiListuProdavacTermin(Termin termin) throws Exception {
        VratiListuProdavacTerminSO operacija = new VratiListuProdavacTerminSO();
        operacija.izvrsiOperaciju(new Prodavac(), termin);
        return operacija.getLista();
    }
    
    public List<Prodavac> vratiListuProdavacProdavac(String prodavac) throws Exception {
        VratiListuProdavacProdavacSO operacija = new VratiListuProdavacProdavacSO();
        operacija.izvrsiOperaciju(new Prodavac(), prodavac);
        return operacija.getLista();
    }
    
    public boolean kreirajProdavac(Prodavac prodavac) throws Exception {
        System.out.println("KREIRAJ PRODAVAC - SERVER KONTROLER DEO");
        KreirajProdavacSO operacija = new KreirajProdavacSO();
        operacija.izvrsiOperaciju(prodavac, null);
        return operacija.getUspesno();
    }
    
    public boolean promeniProdavac(Prodavac prodavac) throws Exception {
        PromeniProdavacSO operacija = new PromeniProdavacSO();
        operacija.izvrsiOperaciju(prodavac, null);
        return operacija.getUspesno();
    }
    
    public boolean obrisiProdavac(Prodavac prodavac) throws Exception {
        ObrisiProdavacSO operacija = new ObrisiProdavacSO();
        operacija.izvrsiOperaciju(prodavac, null);
        return operacija.getUspesno();
    }
    
    public List<Termin> vratiListuTerminTermin(Termin termin) throws Exception {
        VratiListuTerminTerminSO operacija = new VratiListuTerminTerminSO();
        operacija.izvrsiOperaciju(new Termin(), termin);
        return operacija.getLista();
    }
    
    public boolean ubaciTermin(Termin termin) throws Exception {
        UbaciTerminSO operacija = new UbaciTerminSO();
        operacija.izvrsiOperaciju(termin, null);
        return operacija.getUspesno();
    }
    
    public boolean promeniTermin(Termin termin) throws Exception {
        PromeniTerminSO operacija = new PromeniTerminSO();
        operacija.izvrsiOperaciju(termin, null);
        return operacija.getUspesno();
    }
    
    public boolean obrisiTermin(Termin termin) throws Exception {
        ObrisiTerminSO operacija = new ObrisiTerminSO();
        operacija.izvrsiOperaciju(termin, null);
        return operacija.getUspesno();
    }
    
    public Prodavac pretraziProdavac(Prodavac prodavac) throws Exception {
        PretraziProdavacSO operacija = new PretraziProdavacSO();
        operacija.izvrsiOperaciju(prodavac, null);
        return operacija.getProdavac();
    }
    
    public Termin pretraziTermin(Termin termin) throws Exception {
        PretraziTerminSO operacija = new PretraziTerminSO();
        operacija.izvrsiOperaciju(termin, null);
        return operacija.getTermin();
    }
    
    public boolean kreirajIznajmljivanje(Iznajmljivanje i) throws Exception {
        KreirajIznajmljivanjeSO operacija = new KreirajIznajmljivanjeSO();
        operacija.izvrsiOperaciju(i, null);
        return operacija.getUspesno();
    }
    
    public List<Iznajmljivanje> vratiListuIznajmljivanjeIznajmljivanje(int ID) throws Exception {
        VratiListuIznajmljivanjeIznajmljivanjeSO operacija = new VratiListuIznajmljivanjeIznajmljivanjeSO();
        operacija.izvrsiOperaciju(new Iznajmljivanje(), ID);
        return operacija.getLista();
    }
    
    public List<Iznajmljivanje> vratiListuIznajmljivanjeProdavac(String prodavac) throws Exception {
        VratiListuIznajmljivanjeProdavacSO operacija = new VratiListuIznajmljivanjeProdavacSO();
        operacija.izvrsiOperaciju(new Iznajmljivanje(), prodavac);
        return operacija.getLista();
    }
    
    public List<Iznajmljivanje> vratiListuIznajmljivanjeKupac(String kupac) throws Exception {
        VratiListuIznajmljivanjeKupacSO operacija = new VratiListuIznajmljivanjeKupacSO();
        operacija.izvrsiOperaciju(new Iznajmljivanje(), kupac);
        return operacija.getLista();
    }
    
    public boolean promeniIznajmljivanje(Iznajmljivanje i) throws Exception {
        PromeniIznajmljivanjeSO operacija = new PromeniIznajmljivanjeSO();
        operacija.izvrsiOperaciju(i, null);
        return operacija.getUspesno();
    }
    
    public List<Iznajmljivanje> vratiListuSviIznajmljivanje() throws Exception {
        VratiListuSviIznajmljivanjeSO operacija = new VratiListuSviIznajmljivanjeSO();
        operacija.izvrsiOperaciju(new Iznajmljivanje(), null);
        return operacija.getLista();
    }
    
    public List<Iznajmljivanje> vratiListuIznajmljivanjeBicikla(Bicikla bicikla) throws Exception {
        VratiListuIznajmljivanjeBiciklaSO operacija = new VratiListuIznajmljivanjeBiciklaSO();
        operacija.izvrsiOperaciju(new Iznajmljivanje(), bicikla);
        return operacija.getLista();
    }
    
    public Iznajmljivanje pretraziIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        PretraziIznajmljivanjeSO operacija = new PretraziIznajmljivanjeSO();
        operacija.izvrsiOperaciju(iznajmljivanje, null);
        return operacija.getIznajmljivanje();
    }

    public List<Bicikla> vratiListuSviBicikla() throws Exception {
        VratiListuSviBiciklaSO operacija = new VratiListuSviBiciklaSO();
        operacija.izvrsiOperaciju(null, null);
        return operacija.getLista();
    }
    
    public boolean kreirajBicikla(Bicikla bicikla) throws Exception {
        KreirajBiciklaSO operacija = new KreirajBiciklaSO();
        operacija.izvrsiOperaciju(bicikla, null);
        return operacija.getUspesno();
    }
    
    public boolean obrisiBicikla(Bicikla bicikla) throws Exception {
        ObrisiBiciklaSO operacija = new ObrisiBiciklaSO();
        operacija.izvrsiOperaciju(bicikla, null);
        return operacija.getUspesno();
    }
    
    public boolean promeniBicikla(Bicikla bicikla) throws Exception {
        PromeniBiciklaSO operacija = new PromeniBiciklaSO();
        operacija.izvrsiOperaciju(bicikla, null);
        return operacija.getUspesno();
    }
    
    public List<Bicikla> vratiListuBiciklaBicikla(Bicikla b) throws Exception {
        VratiListuBiciklaBiciklaSO operacija = new VratiListuBiciklaBiciklaSO();
        operacija.izvrsiOperaciju(b, b);
        return operacija.getLista();
    }
    
    public Bicikla pretraziBicikla(Bicikla bicikla) throws Exception {
        PretraziBiciklaSO operacija = new PretraziBiciklaSO();
        operacija.izvrsiOperaciju(bicikla, null);
        return operacija.getBicikla();
    }
    
    public boolean kreirajMesto(Mesto mesto) throws Exception {
        KreirajMestoSO operacija = new KreirajMestoSO();
        operacija.izvrsiOperaciju(mesto, null);
        return operacija.getUspesno();
    }
    
    public boolean promeniMesto(Mesto mesto) throws Exception {
        PromeniMestoSO operacija = new PromeniMestoSO();
        operacija.izvrsiOperaciju(mesto, null);
        return operacija.getUspesno();
    }
    
    public boolean obrisiMesto(Mesto mesto) throws Exception {
        ObrisiMestoSO operacija = new ObrisiMestoSO();
        operacija.izvrsiOperaciju(mesto, null);
        return operacija.getUspesno();
    }
    
    public Mesto pretraziMesto(Mesto mesto) throws Exception {
        PretraziMestoSO operacija = new PretraziMestoSO();
        operacija.izvrsiOperaciju(mesto, null);
        return operacija.getMesto();
    }
    
    public List<Mesto> vratiListuMestoMesto(Mesto mesto) throws Exception {
        VratiListuMestoMestoSO operacija = new VratiListuMestoMestoSO();
        operacija.izvrsiOperaciju(new Mesto(), mesto);
        return operacija.getLista();
    }
    
    public boolean generisiRacun(Iznajmljivanje iznajmljivanje) throws Exception {
        GenerisiRacunSO operacija = new GenerisiRacunSO();
        operacija.izvrsiOperaciju(iznajmljivanje, null);
        return operacija.getUspesno();
    }
    
    public String prikaziRacun(Iznajmljivanje iznajmljivanje) throws Exception {
        PrikaziRacunSO operacija = new PrikaziRacunSO();
        operacija.izvrsiOperaciju(iznajmljivanje, null);
        return operacija.getRacun();
    }

}
