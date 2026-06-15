/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import model.Prodavac;

/**
 *
 * @author HP
 */
public class LoginKontroler {
    
    private final LoginForma lf;
    public static final int MAX_BROJ_POKUSAJA = 3;
    private int brojac;
    private int brojacPrazno;

    public LoginKontroler(LoginForma lf) {
        this.lf = lf;
        brojac = 0;
        brojacPrazno = 0;
        addActionListeners();
    }

    private void addActionListeners() {
        
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava();
            }

            private void prijava() {
                
                    
                    String korisnickoIme = lf.getjTextFieldKorisnickoIme().getText().trim();
                    String sifra = String.valueOf(lf.getjPasswordFieldSifra().getPassword());
                    
                    if(korisnickoIme.trim().isEmpty() && sifra.trim().isEmpty()){
                        brojacPrazno++;
                        if(brojacPrazno == 2){
                            JOptionPane.showMessageDialog(lf, "Nemate više pokušaja.", "Neuspešna prijava", JOptionPane.ERROR_MESSAGE);
                            lf.dispose();
                            System.out.println("Prekinuta veza sa serverom.");
                            return;
                        }
                        JOptionPane.showMessageDialog(lf, "Morate uneti korisničko ime i šifru.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    
                    if (korisnickoIme.trim().isEmpty()) {
                    brojacPrazno++;
                    if (brojacPrazno == 2) {
                        JOptionPane.showMessageDialog(lf, "Nemate više pokušaja.", "Neuspešna prijava", JOptionPane.ERROR_MESSAGE);
                        lf.dispose();
                        System.out.println("Prekinuta veza sa serverom.");
                        return;
                    }
                        JOptionPane.showMessageDialog(lf, "Morate uneti korisničko ime.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    
                    if (sifra.trim().isEmpty()) {
                    brojacPrazno++;
                    if (brojacPrazno == 2) {
                        JOptionPane.showMessageDialog(lf, "Nemate više pokušaja.", "Neuspešna prijava", JOptionPane.ERROR_MESSAGE);
                        lf.dispose();
                        System.out.println("Prekinuta veza sa serverom.");
                        return;
                    }
                        JOptionPane.showMessageDialog(lf, "Morate uneti šifru.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    
                    brojac++;
                    
                    try {
                        Komunikacija.getInstance().povezivanje();
                    } catch (Exception ex) {
                        return;
                    }
                    
                    
                    boolean uspesno = Komunikacija.uspeh;
                    if (!uspesno) {

                        JOptionPane.showMessageDialog(lf, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);

                        String putanja = "config/config/configKlijent.properties";
                        String port = "";
                        Properties p = new Properties();

                        try (FileInputStream fis = new FileInputStream(putanja)) {
                            p.load(fis);

                            port = p.getProperty("port");

                        } catch (IOException ex) {
                            System.err.println("Greška pri učitavanju konfiguracionog fajla. " + ex);

                        }
                        int izbor = JOptionPane.showConfirmDialog(lf, "Broj porta je " + port + ". Da li želite da promenite broj porta?", "Greška", JOptionPane.YES_NO_OPTION);
                        if (izbor == 0) {
                            Koordinator.getInstance().otvoriKreirajKonfiguraciju();

                        }

                        return;
                    }
                    
                    Prodavac prodavac = null;
                    
                    try {
                        prodavac = Komunikacija.getInstance().login(korisnickoIme, sifra);
                        System.out.println(prodavac);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(lf, "Server nije u funkciji", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }   
                    
                    if (prodavac == null) {
                        int preostalo = MAX_BROJ_POKUSAJA - brojac;

                        if (preostalo == 0) {
                            JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra nisu ispravni.", "Neuspešna prijava", JOptionPane.ERROR_MESSAGE);
                            lf.dispose();
                            System.out.println("Prekinuta veza sa serverom.");

                        } else {
                            JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra nisu ispravni.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        }
                        Komunikacija.getInstance().odjaviSe(null);

                    } else {

                        boolean prijavljen = Komunikacija.getInstance().dodajPrijavljenogKorisnika(prodavac);

                        if (prijavljen) {

                            JOptionPane.showMessageDialog(lf, "Već ste prijavljeni na sistem.", "Neuspešna prijava", JOptionPane.ERROR_MESSAGE);
                            Komunikacija.getInstance().odjaviSe(null);

                            lf.dispose();

                        } else {

                            Koordinator.getInstance().setUlogovaniProdavac(prodavac);

                            JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra su ispravni.", "Uspešna prijava", JOptionPane.INFORMATION_MESSAGE);

                            try {
                                Koordinator.getInstance().otvoriGlavnuFormu();
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(lf, "Ne može da se otvori glavna froma i meni.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                            }

                            lf.dispose();
                    }
                }
            }
    });
        
        lf.prikaziSifruAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziSifru();
            }

            private void prikaziSifru() {
                if (lf.getjPasswordFieldSifra().getEchoChar() == (char)0) { 
                    lf.getjPasswordFieldSifra().setEchoChar('*'); 
                } else {
                    lf.getjPasswordFieldSifra().setEchoChar((char)0); 
                }
            }

        });
        
        lf.otkaziAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                otkazi();
            }

            private void otkazi() {
                String[] options = {"Da", "Ne"};
                int odgovor = JOptionPane.showOptionDialog(
                    lf, 
                    "Da li ste sigurni da želite da napustite prijavu?", 
                    "Potvrda", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, 
                    options, 
                    options[0]
                );
                if (odgovor == JOptionPane.YES_OPTION) {
                    lf.dispose(); 
                }
            }

        });
    }
    

    public void otvoriFormu() {
        lf.setVisible(true);
    }
    
    
    
    
}
