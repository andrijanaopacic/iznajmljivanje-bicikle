/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.KreirajKonfiguracijuForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class KreirajKonfiguracijuKontroler {
    
    private final KreirajKonfiguracijuForma kk;

    public KreirajKonfiguracijuKontroler(KreirajKonfiguracijuForma kk) {
        this.kk = kk;
        addActionListeners();
    }

    private void addActionListeners() {

        kk.OdustaniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odustani();
            }

            private void odustani() {

                int odgovor = JOptionPane.showConfirmDialog(kk, "Da li ste sigurni da želite da odustanete od unosa podataka o konfiguraciji?", "Pitanje", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {
                    kk.dispose();
                }
            }

        });

        kk.SacuvajAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvaj();
            }

            private void sacuvaj() {

                String portText = kk.getjTextFieldPort().getText().trim();
                int port;

                if (portText.isEmpty()) {
                    JOptionPane.showMessageDialog(kk, "Niste uneli broj porta.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    port = Integer.parseInt(portText);
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(kk, "Niste uneli broj porta u ispravnom formatu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (port < 0 || port > 65536) {
                    JOptionPane.showMessageDialog(kk, "Niste uneli broj porta u okviru dozvoljenog opsega (0-65535).", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String putanja = "config/config/configKlijent.properties";

                Properties podaci = new Properties();

                podaci.setProperty("port", String.valueOf(port));

                try (FileOutputStream outputStream = new FileOutputStream(putanja)) {
                    podaci.store(outputStream, "Konfiguracija");
                    JOptionPane.showMessageDialog(kk, "Konfiguracioni fajl je kreiran na putanji: " + putanja, "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    kk.dispose();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(kk, "Greška prilikom kreiranja konfiguracionog fajla.", "Neuspešno", JOptionPane.ERROR_MESSAGE);

                }

            }
        });

    }
    
    public void otvoriFormu() {
       kk.setVisible(true);
    }
    
}
