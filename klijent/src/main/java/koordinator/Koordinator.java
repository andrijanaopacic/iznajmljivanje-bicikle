/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koordinator;

import kontroleri.LoginKontroler;
import forme.GlavnaForma;
import forme.KreirajBicikla;
import forme.KreirajIznajmljivanje;
import forme.KreirajKonfiguracijuForma;
import forme.KreirajKupac;
import forme.KreirajMesto;
import forme.KreirajProdavac;
import forme.KreirajTermin;
import forme.LoginForma;
import forme.PretraziBicikla;
import forme.PretraziIznajmljivanje;
import forme.PretraziKupac;
import forme.PretraziMesto;
import forme.PretraziProdavac;
import forme.PretraziTermin;
import kontroleri.BiciklaKontroler;
import kontroleri.GlavnaFormaKontroler;
import kontroleri.IznajmljivanjeKontroler;
import kontroleri.KreirajKonfiguracijuKontroler;
import kontroleri.KupacKontroler;
import kontroleri.MestoKontroler;
import kontroleri.ProdavacKontroler;
import kontroleri.TerminKontroler;
import model.Bicikla;
import model.Iznajmljivanje;
import model.Kupac;
import model.Mesto;
import model.Prodavac;
import model.Termin;

/**
 *
 * @author HP
 */
public class Koordinator {
    
    private Prodavac ulogovaniProdavac = null;
    
    private static Koordinator instance;
    
    private LoginKontroler loginKontroler;
    private GlavnaFormaKontroler glavnaFormaKontroler;
    private KreirajKonfiguracijuKontroler kreirajKonfiguracijuKontroler;
    private KupacKontroler kupacKontroler;
    private ProdavacKontroler prodavacKontroler;
    private TerminKontroler terminKontroler;
    private IznajmljivanjeKontroler iznajmljivanjeKontroler;
    private BiciklaKontroler biciklaKontroler;
    private MestoKontroler mestoKontroler;
    

    private Koordinator() {
        
    }
    
    public static Koordinator getInstance(){
        if(instance == null){
            instance = new Koordinator();
        }
        return instance;
    }

    public Prodavac getUlogovaniProdavac() {
        return ulogovaniProdavac;
    }

    public void setUlogovaniProdavac(Prodavac ulogovaniProdavac) {
        this.ulogovaniProdavac = ulogovaniProdavac;
    }

    
    
    public void otvoriLoginFormu() {
        loginKontroler = new LoginKontroler(new LoginForma());
        loginKontroler.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaKontroler = new GlavnaFormaKontroler(new GlavnaForma());
        glavnaFormaKontroler.otvoriFormu();
    }

    public void otvoriKreirajKonfiguraciju() {
        kreirajKonfiguracijuKontroler = new KreirajKonfiguracijuKontroler(new KreirajKonfiguracijuForma(new LoginForma(), true));
        kreirajKonfiguracijuKontroler.otvoriFormu();
    }
    
    public void otvoriKreirajKupac(Kupac kupac){
//        System.out.println("USAO U DEO KREIRAJ kupac - KOOOORDINATOR");
        kupacKontroler = new KupacKontroler(new KreirajKupac(new GlavnaForma(), true, kupac), new PretraziKupac(new GlavnaForma(), true));
//        System.out.println("ulazim u deo kupac kontroler");
        kupacKontroler.otvoriFormuKreirajKupac(kupac);
    }

    public void otvoriPretraziKupac() {
        kupacKontroler = new KupacKontroler(new KreirajKupac(new GlavnaForma(), true, null), new PretraziKupac(new GlavnaForma(), true));
        kupacKontroler.otvoriFormuPretraziKupac();
    }

    public void otvoriKreirajProdavca(Prodavac prodavac) {
        prodavacKontroler = new ProdavacKontroler(new KreirajProdavac(new GlavnaForma(), true, prodavac), new PretraziProdavac(new GlavnaForma(), true));
        prodavacKontroler.prikaziFormuKreirajProdavca(prodavac);

    }
    
    public void otvoriPretraziProdavac(){
        prodavacKontroler = new ProdavacKontroler(new KreirajProdavac(new GlavnaForma(), true, null), new PretraziProdavac(new GlavnaForma(), true));
        prodavacKontroler.otvoriFormuPretraziProdavac();
    }

    public void otvoriKreirajTermin(Termin termin) {
        terminKontroler = new TerminKontroler(new KreirajTermin(new GlavnaForma(), true, termin), new PretraziTermin(new GlavnaForma(), true));
        terminKontroler.otvoriFormuKreirajTermin(termin);
    }
    
    public void otvoriPretraziTermin(){
        terminKontroler = new TerminKontroler(new KreirajTermin(new GlavnaForma(), true, null), new PretraziTermin(new GlavnaForma(), true));
        terminKontroler.otvoriFormuPretraziTermin();
    }

    public void otvoriKreirajIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        iznajmljivanjeKontroler = new IznajmljivanjeKontroler(new KreirajIznajmljivanje(new GlavnaForma(), true, iznajmljivanje), new PretraziIznajmljivanje(new GlavnaForma(), true));
        iznajmljivanjeKontroler.otvoriFormuKreirajIznajmljivanje(iznajmljivanje);
    }
    
    public void otvoriPretraziIznajmljivanje() {
        iznajmljivanjeKontroler = new IznajmljivanjeKontroler(new KreirajIznajmljivanje(new GlavnaForma(), true, null), new PretraziIznajmljivanje(new GlavnaForma(), true));
        iznajmljivanjeKontroler.otvoriFormuPretraziIznajmljivanje();
    }

    public void otvoriKreirajBickikla(Bicikla b) {
        biciklaKontroler = new BiciklaKontroler(new KreirajBicikla(new GlavnaForma(), true, b), new PretraziBicikla(new GlavnaForma(), true));
        biciklaKontroler.otvoriFormuKreirajBicikla(b);
    }
    
    public void otvoriPretraziBicikla() {
        biciklaKontroler = new BiciklaKontroler(new KreirajBicikla(new GlavnaForma(), true, null), new PretraziBicikla(new GlavnaForma(), true));
        biciklaKontroler.otvoriFormuPretraziBicikla();
    }

    public void otvoriKreirajMesto(Mesto mesto) {
        mestoKontroler = new MestoKontroler(new KreirajMesto(new GlavnaForma(), true, mesto), new PretraziMesto(new GlavnaForma(), true));
        mestoKontroler.otvoriFormuKreirajMesto(mesto);
    }
    
    public void otvoriPretraziMesto() {
        mestoKontroler = new MestoKontroler(new KreirajMesto(new GlavnaForma(), true, null), new PretraziMesto(new GlavnaForma(), true));
        mestoKontroler.otvoriFormuPretraziMesto();
    }
    
    
    
}
