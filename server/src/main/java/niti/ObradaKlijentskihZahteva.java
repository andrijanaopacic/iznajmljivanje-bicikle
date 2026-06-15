package niti;

import kontroler.ServerKontroler;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import model.Bicikla;
import model.Iznajmljivanje;
import model.Kupac;
import model.Mesto;
import model.Prodavac;
import model.Termin;
import transfer.Odgovor;
import transfer.Zahtev;

public class ObradaKlijentskihZahteva extends Thread {

    private Socket socket;
    private Primalac primalac;
    private Posiljalac posiljalac;
    private Prodavac prodavac;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String sifra;

    public ObradaKlijentskihZahteva(Socket socket) throws IOException {
        this.socket = socket;
        this.primalac = new Primalac(socket);
        this.posiljalac = new Posiljalac(socket);
        this.ime = null;
        this.prezime = null;
        this.korisnickoIme = null;
        this.sifra = null;
    }

    @Override
    public void run() {
        Odgovor so = new Odgovor();
        
        while(socket != null && !socket.isClosed()){
            try {
                Zahtev kz = (Zahtev) primalac.primi();
                if(kz != null){
                    switch (kz.getOperacija()) {
                        case PRIJAVI_PRODAVAC:
                            Prodavac p = (Prodavac) kz.getParametar();
                            Prodavac prodavac = ServerKontroler.getInstance().prijaviProdavac(p);
                            so.setOdgovor(prodavac);
                            
                            posiljalac.posalji(so);
                            break;
                        case ODJAVI_SE:

                            ServerKontroler.getInstance().odjaviSe((Prodavac) kz.getParametar());

                            break;
                        case VRATI_LISTU_SVI_PRODAVCI:
                            List<Prodavac> listaProdavaca = ServerKontroler.getInstance().vratiListuSviProdavci();
                            so.setOdgovor(listaProdavaca);
                            
                            posiljalac.posalji(so);
                            break;
                        case DODAJ_PRIJAVLJENOG:
                            boolean prijavljen = ServerKontroler.getInstance().dodajPrijavljenog((Prodavac) kz.getParametar());
                            so.setOdgovor(prijavljen);
                            
                            posiljalac.posalji(so);
                            break;
                        case KREIRAJ_KUPAC:
                            boolean uspesnoKupac = ServerKontroler.getInstance().kreirajKupac((Kupac) kz.getParametar());
                            so.setOdgovor(uspesnoKupac);
                            
                            posiljalac.posalji(so);
                            break;
                        case VRATI_LISTU_SVI_KUPCI:

                            List<Kupac> listaKupaca= ServerKontroler.getInstance().vratiListuSviKupci();
                            so.setOdgovor(listaKupaca);

                            posiljalac.posalji(so);
                            break;
                        case PROMENI_KUPAC:
                            boolean uspesnoPromeniKupac = ServerKontroler.getInstance().promeniKupac((Kupac) kz.getParametar());
                            so.setOdgovor(uspesnoPromeniKupac);
                            
                            posiljalac.posalji(so);
                            break;
                        case OBRISI_KUPAC:
                            boolean uspesnoObrisiKupac = ServerKontroler.getInstance().obrisiKupac((Kupac) kz.getParametar());
                            so.setOdgovor(uspesnoObrisiKupac);
                            
                            posiljalac.posalji(so);
                            break; 
                        case VRATI_LISTU_SVI_MESTO:
                            List<Mesto> listaMesta = ServerKontroler.getInstance().vratiListuSviMesto();
                            so.setOdgovor(listaMesta);
                            
                            posiljalac.posalji(so);
                            break;
                        case VRATI_LISTU_KUPAC_KUPAC:
                            List<Kupac> listaKupacKupac = ServerKontroler.getInstance().vratiListuKupacKupac((String) kz.getParametar());
                            so.setOdgovor(listaKupacKupac);
                            
                            posiljalac.posalji(so);
                            break;
                        case VRATI_LISTU_KUPAC_MESTO:
                            List<Kupac> listaKupacMesto = ServerKontroler.getInstance().vratiListuKupacMesto((String) kz.getParametar());
                            so.setOdgovor(listaKupacMesto);
                            
                            posiljalac.posalji(so);
                            break;
                        case PRETRAZI_KUPAC:
                            System.out.println("SAD SAM U OKZ - PRETRAZI KUPCA DELU");
                            Kupac kupac = ServerKontroler.getInstance().pretraziKupac((Kupac) kz.getParametar());
                            so.setOdgovor(kupac);

                            posiljalac.posalji(so);
                            break;
                        case KREIRAJ_PRODAVAC:
                            System.out.println("SAD SAM U OKZ - KREIRAJ PRODAVCA DELU");
                            boolean uspesnoProdavac = ServerKontroler.getInstance().kreirajProdavac((Prodavac) kz.getParametar());
                            so.setOdgovor(uspesnoProdavac);
                            
                            posiljalac.posalji(so);
                            break;
                        case PROMENI_PRODAVAC:
                            boolean uspesnoPromeniProdavac = ServerKontroler.getInstance().promeniProdavac((Prodavac) kz.getParametar());
                            so.setOdgovor(uspesnoPromeniProdavac);
                            
                            posiljalac.posalji(so);
                            break;
                        case OBRISI_PRODAVAC:
                            boolean uspesnoObrisiProdavac = ServerKontroler.getInstance().obrisiProdavac((Prodavac) kz.getParametar());
                            so.setOdgovor(uspesnoObrisiProdavac);
                            
                            posiljalac.posalji(so);
                            break;
                        case VRATI_LISTU_PRODAVAC_TERMIN:
                            List<Prodavac> listaProdavacTermin = ServerKontroler.getInstance().vratiListuProdavacTermin((Termin) kz.getParametar());
                            so.setOdgovor(listaProdavacTermin);

                            posiljalac.posalji(so);
                            break;
                        case VRATI_LISTU_PRODAVAC_PRODAVAC:
                            List<Prodavac> listaProdavacProdavac = ServerKontroler.getInstance().vratiListuProdavacProdavac((String) kz.getParametar());
                            so.setOdgovor(listaProdavacProdavac);
                            
                            posiljalac.posalji(so);
                            break;
                        case VRATI_LISTU_SVI_TERMIN_DEZURSTVA:
                            List<Termin> listaTermina = ServerKontroler.getInstance().vratiListuSviTermini();
                            so.setOdgovor(listaTermina);
                            
                            posiljalac.posalji(so);
                            break;
                        case OBRISI_TERMIN:
                            boolean uspesnoObrisiTermin = ServerKontroler.getInstance().obrisiTermin((Termin) kz.getParametar());
                            so.setOdgovor(uspesnoObrisiTermin);
                            
                            posiljalac.posalji(so);
                            break;
                        case PROMENI_TERMIN:
                            boolean uspesnoPromeniTermin = ServerKontroler.getInstance().promeniTermin((Termin) kz.getParametar());
                            so.setOdgovor(uspesnoPromeniTermin);

                            posiljalac.posalji(so);
                            break;
                        case UBACI_TERMIN:
                            boolean uspesnoKvalifikacija = ServerKontroler.getInstance().ubaciTermin((Termin) kz.getParametar());
                            so.setOdgovor(uspesnoKvalifikacija);

                            posiljalac.posalji(so);
                            break;
                        case VRATI_LISTU_TERMIN_TERMIN:
                            List<Termin> listaTerminTermin = ServerKontroler.getInstance().vratiListuTerminTermin((Termin) kz.getParametar());
                            so.setOdgovor(listaTerminTermin);
                            
                            posiljalac.posalji(so);
                            break;
                        case PRETRAZI_PRODAVAC:
                            Prodavac prod = ServerKontroler.getInstance().pretraziProdavac((Prodavac) kz.getParametar());
                            so.setOdgovor(prod);

                            posiljalac.posalji(so);
                            break;
                        case PRETRAZI_TERMIN:
                            Termin te = ServerKontroler.getInstance().pretraziTermin((Termin) kz.getParametar());
                            so.setOdgovor(te);

                            posiljalac.posalji(so);
                            break;
                        case KREIRAJ_IZNAJMLJIVANJE:
                            System.out.println("Primljena operacija: " + kz.getOperacija());
                            boolean uspesnoIznajmljivanje= ServerKontroler.getInstance().kreirajIznajmljivanje((Iznajmljivanje) kz.getParametar());
                            so.setOdgovor(uspesnoIznajmljivanje);

                            posiljalac.posalji(so);
                            break;
                        case PROMENI_IZNAJMLJIVANJE:
                            boolean uspesnoPromeniIznajmljivanje = ServerKontroler.getInstance().promeniIznajmljivanje((Iznajmljivanje) kz.getParametar());
                            so.setOdgovor(uspesnoPromeniIznajmljivanje);
                            
                            posiljalac.posalji(so);
                            break;
                        case VRATI_LISTU_IZNAJMLJIVANJE_IZNAJMLJIVANJE:
                            List<Iznajmljivanje> listaIznajmljivanjeID = ServerKontroler.getInstance().vratiListuIznajmljivanjeIznajmljivanje((int) kz.getParametar());
                            so.setOdgovor(listaIznajmljivanjeID);
                            
                            posiljalac.posalji(so);
                            break;
                        case VRATI_LISTU_IZNAJMLJIVANJE_PRODAVAC:
                            List<Iznajmljivanje> listaIznajmljivanjeProdavac = ServerKontroler.getInstance().vratiListuIznajmljivanjeProdavac((String) kz.getParametar());
                            so.setOdgovor(listaIznajmljivanjeProdavac);

                            posiljalac.posalji(so);
                            break;
                        case VRATI_LISTU_IZNAJMLJIVANJE_KUPAC:
                            List<Iznajmljivanje> listaIznajmljivanjeKupac= ServerKontroler.getInstance().vratiListuIznajmljivanjeKupac((String) kz.getParametar());
                            so.setOdgovor(listaIznajmljivanjeKupac);
                            
                            posiljalac.posalji(so);
                            break;
                        case VRATI_LISTU_IZNAJMLJIVANJE_BICIKLA:
                            List<Iznajmljivanje> listaIznajmljivanjeBicikla = ServerKontroler.getInstance().vratiListuIznajmljivanjeBicikla((Bicikla) kz.getParametar());
                            so.setOdgovor(listaIznajmljivanjeBicikla);
                            
                            posiljalac.posalji(so);
                            break;
                        case VRATI_LISTU_SVA_IZNAJMLJIVANJA:
                            List<Iznajmljivanje> listaSvihIznajmljivanja = ServerKontroler.getInstance().vratiListuSviIznajmljivanje();
                            so.setOdgovor(listaSvihIznajmljivanja);
                            
                            posiljalac.posalji(so);
                            break;
                        case PRETRAZI_IZNAJMLJIVANJE:
                            Iznajmljivanje iz = ServerKontroler.getInstance().pretraziIznajmljivanje((Iznajmljivanje) kz.getParametar());
                            so.setOdgovor(iz);

                            posiljalac.posalji(so);
                            break;
                        case VRATI_LISTU_SVI_BICIKLA:
                            List<Bicikla> listaBicikla = ServerKontroler.getInstance().vratiListuSviBicikla();
                            so.setOdgovor(listaBicikla);

                            posiljalac.posalji(so);
                            break;
                        case KREIRAJ_BICIKLA:
                            boolean uspesnoBicikla = ServerKontroler.getInstance().kreirajBicikla((Bicikla) kz.getParametar());
                            so.setOdgovor(uspesnoBicikla);

                            posiljalac.posalji(so);
                            break;
                        case OBRISI_BICIKLA:
                            boolean uspesnoObrisiBicikla= ServerKontroler.getInstance().obrisiBicikla((Bicikla) kz.getParametar());
                            so.setOdgovor(uspesnoObrisiBicikla);
                            
                            posiljalac.posalji(so);
                            break;
                        case PROMENI_BICIKLA:
                            boolean uspesnoPromeniBicikla = ServerKontroler.getInstance().promeniBicikla((Bicikla) kz.getParametar());
                            so.setOdgovor(uspesnoPromeniBicikla);
                            
                            posiljalac.posalji(so);
                            break;
                        case VRATI_LISTU_BICIKLA_BICIKLA:
                            List<Bicikla> listaBiciklaBicikla = ServerKontroler.getInstance().vratiListuBiciklaBicikla((Bicikla) kz.getParametar());
                            so.setOdgovor(listaBiciklaBicikla);
                            
                            posiljalac.posalji(so);
                            break;
                        case PRETRAZI_BICIKLA:
                            Bicikla b = ServerKontroler.getInstance().pretraziBicikla((Bicikla) kz.getParametar());
                            so.setOdgovor(b);

                            posiljalac.posalji(so);
                            break;
                        case KREIRAJ_MESTO:
                            boolean uspesnoMesto = ServerKontroler.getInstance().kreirajMesto((Mesto) kz.getParametar());
                            so.setOdgovor(uspesnoMesto);
                            
                            posiljalac.posalji(so);
                            break;
                        case PROMENI_MESTO:
                            boolean uspesnoPromeniM = ServerKontroler.getInstance().promeniMesto((Mesto) kz.getParametar());
                            so.setOdgovor(uspesnoPromeniM);
                            
                            posiljalac.posalji(so);
                            break;
                        case OBRISI_MESTO:
                            boolean uspesnoObrisiM = ServerKontroler.getInstance().obrisiMesto((Mesto) kz.getParametar());
                            so.setOdgovor(uspesnoObrisiM);
                            
                            posiljalac.posalji(so);
                            break;
                        case VRATI_LISTU_MESTO_MESTO:
                            List<Mesto> listaMM= ServerKontroler.getInstance().vratiListuMestoMesto((Mesto) kz.getParametar());
                            so.setOdgovor(listaMM);
                            
                            posiljalac.posalji(so);
                            break;
                        case PRETRAZI_MESTO:
                            Mesto me = ServerKontroler.getInstance().pretraziMesto((Mesto) kz.getParametar());
                            so.setOdgovor(me);

                            posiljalac.posalji(so);
                            break;
                        default:
                            System.err.println("Nepoznata operacija: " + kz.getOperacija());
                            System.err.println("GREŠKA: Nepoznata sistemska operacija.");
                        }
                    
                }
            } catch (Exception ex) {
                System.out.println("Greska pri obradi klijentskih zahteva: "+ex.getMessage());
            }
        }
           
    }
    

    public Socket getSocket() {
        return socket;
    }

    

    public Primalac getPrimalac() {
        return primalac;
    }

    public void setPrimalac(Primalac primalac) {
        this.primalac = primalac;
    }

    public Posiljalac getPosiljalac() {
        return posiljalac;
    }

    public void setPosiljalac(Posiljalac posiljalac) {
        this.posiljalac = posiljalac;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    

    
    
}
