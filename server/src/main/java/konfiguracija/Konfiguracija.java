/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Tijana
 */
public class Konfiguracija {

    private static Konfiguracija instance;

    public Konfiguracija() {

    }

    public static Konfiguracija getInstance() {
        if (instance == null) {
            instance = new Konfiguracija();
        }

        return instance;
    }

    public String ucitajPodatak(String kljuc) {

        Properties p = new Properties();
        String putanja = "konfiguracija/config.properties";

        try (FileInputStream fis = new FileInputStream(putanja)) {
            p.load(fis);

        } catch (IOException e) {
            System.err.println("Greška pri učitavanju konfiguracionog fajla: " + e);
            JOptionPane.showMessageDialog(null, "Greška pri učitavanju konfiguracionog fajla.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
        if (kljuc.equals("port")) {
            int port;
            if (!p.getProperty("port", "N/A").equals("N/A")) {
                try {

                    port = Integer.parseInt(p.getProperty(kljuc));

                } catch (NumberFormatException numberFormatException) {
                    System.err.println("Broj porta nije dat u ispravnom formatu. Broj porta se može sastojati samo od cifara.");
                    return "N/A";

                }
                if (port < 0 || port > 65535) {
                    System.err.println("Broj porta nije dat u okviru dozvoljenog opsega (0-65535).");
                    return "N/A";
                }

            }

        }
        return p.getProperty(kljuc, "N/A");
    }

    public boolean daLiPostojiKonfigFajl() {
        String putanja = "konfiguracija/config.properties";

        try (FileInputStream fis = new FileInputStream(putanja)) {

        } catch (IOException e) {
            System.err.println("Greška pri učitavanju konfiguracionog fajla: " + e);
            JOptionPane.showMessageDialog(null, "Greška pri učitavanju konfiguracionog fajla. Unesite podatke.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

}
