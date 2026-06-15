/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import konfiguracija.Konfiguracija;
import model.Prodavac;
import repozitorijum.db.DBKonekcija;

/**
 *
 * @author HP
 */
public class ServerskaNit extends Thread{
        
    private ServerSocket serverskiSoket;
    private static Socket s;
    private String port;
    private static boolean pokrenut = false;
    private static ObradaKlijentskihZahteva nit = null;
    private static List<ObradaKlijentskihZahteva> prijavljeniKorisnici = new ArrayList<>();


    @Override
    public void run() {
        try{
            try{
                port = Konfiguracija.getInstance().ucitajPodatak("port");
                System.out.println("Učitani port iz konfiguracije: " + port);
                if (port == null || port.equals("N/A")) {
                    System.err.println("Port nije ispravno učitan iz konfiguracije.");
                    return;
                }
            }catch (NumberFormatException e) {
                    System.err.println(e.getMessage());
            }
        
        serverskiSoket = new ServerSocket(Integer.parseInt(port));
        pokrenut = true;
        System.out.println("Server je pokrenut na portu: " + port);
        
        while(pokrenut){
            try {
                System.out.println("Čekanje klijenta...");
                s = serverskiSoket.accept();
                
                System.out.println("Klijent je povezan!");
                nit = new ObradaKlijentskihZahteva(s);
                prijavljeniKorisnici.add(nit);
                nit.start();
                
            } catch (SocketException e) {
                    if (nit != null) {
                        nit.interrupt();
                    }
                    if (!pokrenut) {
                        break;
                    }
                    System.err.println("Greška: " + e.getMessage());
            }
            }
        }       catch (IOException ex) {
                    pokrenut = false;
                    System.err.println("Greška prilikom pokretanja servera.");
        }
    }
    
    public void zaustaviServer() {

        try {
            if (serverskiSoket != null && !serverskiSoket.isClosed()) {
                serverskiSoket.close();
                pokrenut = false;
                
                for (ObradaKlijentskihZahteva nit : prijavljeniKorisnici) {
                    try {
                        nit.interrupt();
                        nit.getSocket().close();
                        prijavljeniKorisnici.remove(nit);
                        break;
                    } catch (IOException ex) {
                        Logger.getLogger(ServerskaNit.class.getName()).log(Level.SEVERE, "Greška prilikom odjavljivanja klijenta.", ex);
                    }

                }

                System.out.println("Server je zaustavljen.");

            }

            DBKonekcija.resetuj();

        } catch (IOException e) {
            System.err.println("Greška pri zaustavljanju servera: " + e.getMessage());
        }
    }
    
    public static void odjaviKlijenta(Prodavac prodavac) {

        if (prodavac != null) {
            for (ObradaKlijentskihZahteva nit : prijavljeniKorisnici) {
                if (nit.getIme().equals(prodavac.getIme())
                        && nit.getPrezime().equals(prodavac.getPrezime())
                        && nit.getKorisnickoIme().equals(prodavac.getKorisnickoIme())
                        && nit.getSifra().equals(prodavac.getSifra())) {
                    try {
                        nit.interrupt();
                        nit.getSocket().close();
                        prijavljeniKorisnici.remove(nit);
                        System.out.println("Klijent " + prodavac.getIme() + " " + prodavac.getPrezime() + " se odjavio.");

                        return;
                    } catch (IOException ex) {
                        Logger.getLogger(ServerskaNit.class.getName()).log(Level.SEVERE, "Greška prilikom odjavljivanja klijenta.", ex);
                    }
                }
            }
        }
        try {
            nit.interrupt();
            nit.getSocket().close();
            prijavljeniKorisnici.remove(nit);
        } catch (IOException e) {
            Logger.getLogger(ServerskaNit.class.getName()).log(Level.SEVERE, "Greška prilikom odjavljivanja klijenta.", e);

        }
    }

    public ServerSocket getServerSocket() {
        return serverskiSoket;
    }

    public void setServerskiSoket(ServerSocket serverskiSoket) {
        this.serverskiSoket = serverskiSoket;
    }

    public static Socket getS() {
        return s;
    }

    public static void setS(Socket s) {
        ServerskaNit.s = s;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public static boolean isPokrenut() {
        return pokrenut;
    }

    public static void setPokrenut(boolean pokrenut) {
        ServerskaNit.pokrenut = pokrenut;
    }

    public static ObradaKlijentskihZahteva getNit() {
        return nit;
    }

    public static void setNit(ObradaKlijentskihZahteva nit) {
        ServerskaNit.nit = nit;
    }

    public static List<ObradaKlijentskihZahteva> getPrijavljeniKorisnici() {
        return prijavljeniKorisnici;
    }

    public static void setPrijavljeniKorisnici(List<ObradaKlijentskihZahteva> prijavljeniKorisnici) {
        ServerskaNit.prijavljeniKorisnici = prijavljeniKorisnici;
    }
    
    public static void dodajKorisnika() {
        prijavljeniKorisnici.add(nit);
    }

    

    
    
    
    
        
    
}
