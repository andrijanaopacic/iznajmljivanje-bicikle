/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forme;

//import forme.iznajmljivanje.KreirajIznajmljivanje;
//import forme.iznajmljivanje.PretraziIznajmljivanje;
//import forme.kupac.KreirajKupac;
//import forme.kupac.PretraziKupac;
//import forme.prodavac.Kreiraj;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import model.Prodavac;

/**
 *
 * @author HP
 */
public class GlavnaForma extends javax.swing.JFrame {

//    private Prodavac ulogovaniProdavac;
//    Prodavac ulogovani = Koordinator.getInstance().getUlogovaniProdavac();
    /**
     * Creates new form GlavnaForma
     */
    
    public GlavnaForma() {
        //this.ulogovaniProdavac = prodavac;
        initComponents();
        setTitle("Glavna stranica");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                Komunikacija.getInstance().odjaviSe(Koordinator.getInstance().getUlogovaniProdavac());
                System.exit(0);

            }
        });
        


    }
    
    public JLabel getjLabelIme() {
        return jLabelKorisnik;
    }

    public void setjLabelIme(JLabel jLabelIme) {
        this.jLabelKorisnik = jLabelIme;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jLabelKorisnik = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonOdjava = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenuDokumenti = new javax.swing.JMenu();
        jMenuIznajmljivanje = new javax.swing.JMenu();
        jMenuItemKreirajIznajmljivanje = new javax.swing.JMenuItem();
        jMenuItemPretraziIznajmljivanje = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuProdavac = new javax.swing.JMenu();
        jMenuItemKreirajProdavac = new javax.swing.JMenuItem();
        jMenuItemPretraziProdavac = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuKupac = new javax.swing.JMenu();
        jMenuItemKreirajKupac = new javax.swing.JMenuItem();
        jMenuItemPretraziKupac = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuBicikla = new javax.swing.JMenu();
        jMenuItemKreirajBicikla = new javax.swing.JMenuItem();
        jMenuItemPretraziBicikla = new javax.swing.JMenuItem();
        jMenuMesto = new javax.swing.JMenu();
        jMenuItemKreirajMesto = new javax.swing.JMenuItem();
        jMenuItemPretraziMesto = new javax.swing.JMenuItem();
        jMenuTerminDezurstva = new javax.swing.JMenu();
        jMenuItemUbaciTermin = new javax.swing.JMenuItem();
        jMenuItemPretraziTermin = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenu5.setText("jMenu5");

        jMenu6.setText("jMenu6");

        jMenu7.setText("File");
        jMenuBar1.add(jMenu7);

        jMenu9.setText("Edit");
        jMenuBar1.add(jMenu9);

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelKorisnik.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelKorisnik.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelKorisnik.setText("Zdravo, korisnice");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Dobrodošli u aplikaciju za praćenje iznajmljivanja bicikala!");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButtonOdjava.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonOdjava.setText("ODJAVI SE");
        jButtonOdjava.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonOdjava.setPreferredSize(new java.awt.Dimension(65, 27));
        jButtonOdjava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOdjavaActionPerformed(evt);
            }
        });

        jMenuDokumenti.setText("Dokumenti");

        jMenuIznajmljivanje.setText("Iznajmljivanje");

        jMenuItemKreirajIznajmljivanje.setText("Kreiraj");
        jMenuItemKreirajIznajmljivanje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKreirajIznajmljivanjeActionPerformed(evt);
            }
        });
        jMenuIznajmljivanje.add(jMenuItemKreirajIznajmljivanje);

        jMenuItemPretraziIznajmljivanje.setText("Pretraži");
        jMenuItemPretraziIznajmljivanje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPretraziIznajmljivanjeActionPerformed(evt);
            }
        });
        jMenuIznajmljivanje.add(jMenuItemPretraziIznajmljivanje);

        jMenuDokumenti.add(jMenuIznajmljivanje);

        jMenuBar2.add(jMenuDokumenti);

        jMenu8.setText("Pružalac usluge");

        jMenuProdavac.setText("Prodavac");

        jMenuItemKreirajProdavac.setText("Kreiraj");
        jMenuItemKreirajProdavac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKreirajProdavacActionPerformed(evt);
            }
        });
        jMenuProdavac.add(jMenuItemKreirajProdavac);

        jMenuItemPretraziProdavac.setText("Pretraži");
        jMenuItemPretraziProdavac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPretraziProdavacActionPerformed(evt);
            }
        });
        jMenuProdavac.add(jMenuItemPretraziProdavac);

        jMenu8.add(jMenuProdavac);

        jMenuBar2.add(jMenu8);

        jMenu1.setText("Primalac usluge");

        jMenuKupac.setText("Kupac");

        jMenuItemKreirajKupac.setText("Kreiraj");
        jMenuItemKreirajKupac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKreirajKupacActionPerformed(evt);
            }
        });
        jMenuKupac.add(jMenuItemKreirajKupac);

        jMenuItemPretraziKupac.setText("Pretraži");
        jMenuItemPretraziKupac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPretraziKupacActionPerformed(evt);
            }
        });
        jMenuKupac.add(jMenuItemPretraziKupac);

        jMenu1.add(jMenuKupac);

        jMenuBar2.add(jMenu1);

        jMenu2.setText("Šifarnici");

        jMenuBicikla.setText("Bicikla");

        jMenuItemKreirajBicikla.setText("Kreiraj");
        jMenuItemKreirajBicikla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKreirajBiciklaActionPerformed(evt);
            }
        });
        jMenuBicikla.add(jMenuItemKreirajBicikla);

        jMenuItemPretraziBicikla.setText("Pretraži");
        jMenuItemPretraziBicikla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPretraziBiciklaActionPerformed(evt);
            }
        });
        jMenuBicikla.add(jMenuItemPretraziBicikla);

        jMenu2.add(jMenuBicikla);

        jMenuMesto.setText("Mesto");

        jMenuItemKreirajMesto.setText("Kreiraj");
        jMenuItemKreirajMesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKreirajMestoActionPerformed(evt);
            }
        });
        jMenuMesto.add(jMenuItemKreirajMesto);

        jMenuItemPretraziMesto.setText("Pretraži");
        jMenuItemPretraziMesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPretraziMestoActionPerformed(evt);
            }
        });
        jMenuMesto.add(jMenuItemPretraziMesto);

        jMenu2.add(jMenuMesto);

        jMenuTerminDezurstva.setText("Termin dežurstva");

        jMenuItemUbaciTermin.setText("Ubaci");
        jMenuItemUbaciTermin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUbaciTerminActionPerformed(evt);
            }
        });
        jMenuTerminDezurstva.add(jMenuItemUbaciTermin);

        jMenuItemPretraziTermin.setText("Pretraži");
        jMenuItemPretraziTermin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPretraziTerminActionPerformed(evt);
            }
        });
        jMenuTerminDezurstva.add(jMenuItemPretraziTermin);

        jMenu2.add(jMenuTerminDezurstva);

        jMenuBar2.add(jMenu2);

        jMenu3.setText("Podešavanja");

        jMenuItem3.setText("Konfiguracija");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("O programu");

        jMenuItem2.setText("O autoru");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(474, 474, 474)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelKorisnik, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonOdjava, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(626, 626, 626))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(jLabelKorisnik)
                .addGap(32, 32, 32)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131)
                .addComponent(jButtonOdjava, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(195, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOdjavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOdjavaActionPerformed
//        String[] options = {"Da", "Ne"};
//        int odgovor = JOptionPane.showOptionDialog(
//            this,
//            "Da li ste sigurni da želite da se odjavite?",
//            "Potvrda odjave",
//            JOptionPane.YES_NO_OPTION,
//            JOptionPane.QUESTION_MESSAGE,
//            null,
//            options,
//            options[1]
//        );
//
//        if (odgovor == JOptionPane.YES_OPTION) {
//            LoginForma loginForma = new LoginForma();
//            loginForma.setVisible(true);
//            this.dispose();
//        }

    }//GEN-LAST:event_jButtonOdjavaActionPerformed

    private void jMenuItemKreirajProdavacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKreirajProdavacActionPerformed
       try {
           System.out.println("USAO U DEO KREIRAJ PRODAVAC - glavna forma");
            Koordinator.getInstance().otvoriKreirajProdavca(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da kreira prodavca.", "Greška", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jMenuItemKreirajProdavacActionPerformed

    private void jMenuItemKreirajIznajmljivanjeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKreirajIznajmljivanjeActionPerformed
        try {
            Koordinator.getInstance().otvoriKreirajIznajmljivanje(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da kreira iznajmljivanje.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemKreirajIznajmljivanjeActionPerformed

    private void jMenuItemPretraziIznajmljivanjeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPretraziIznajmljivanjeActionPerformed
        try {
            Koordinator.getInstance().otvoriPretraziIznajmljivanje();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da prikaže formu za pretraživanje iznajmljivanja.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemPretraziIznajmljivanjeActionPerformed

    private void jMenuItemKreirajKupacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKreirajKupacActionPerformed
        try {
            Koordinator.getInstance().otvoriKreirajKupac(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da kreira kupca.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_jMenuItemKreirajKupacActionPerformed

    private void jMenuItemPretraziKupacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPretraziKupacActionPerformed
        try {
            Koordinator.getInstance().otvoriPretraziKupac();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da prikaže formu za pretraživanje kupca.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemPretraziKupacActionPerformed

    private void jMenuItemPretraziProdavacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPretraziProdavacActionPerformed
        // TODO add your handling code here:
        try {
            Koordinator.getInstance().otvoriPretraziProdavac();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da prikaže formu za pretraživanje prodavca.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemPretraziProdavacActionPerformed

    private void jMenuItemUbaciTerminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUbaciTerminActionPerformed
        // TODO add your handling code here:
        try {
            Koordinator.getInstance().otvoriKreirajTermin(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da kreira termin.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemUbaciTerminActionPerformed

    private void jMenuItemPretraziTerminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPretraziTerminActionPerformed
        // TODO add your handling code here:
        try {
            Koordinator.getInstance().otvoriPretraziTermin();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da prikaže formu za pretraživanje termina.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemPretraziTerminActionPerformed

    private void jMenuItemKreirajBiciklaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKreirajBiciklaActionPerformed
        // TODO add your handling code here:
        try {
            Koordinator.getInstance().otvoriKreirajBickikla(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da kreira biciklu.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemKreirajBiciklaActionPerformed

    private void jMenuItemPretraziBiciklaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPretraziBiciklaActionPerformed
        // TODO add your handling code here:
        try {
            Koordinator.getInstance().otvoriPretraziBicikla();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da prikaže formu za pretraživanje bicikle.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemPretraziBiciklaActionPerformed

    private void jMenuItemKreirajMestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKreirajMestoActionPerformed
        // TODO add your handling code here:
        try {
            Koordinator.getInstance().otvoriKreirajMesto(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da kreira mesto.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemKreirajMestoActionPerformed

    private void jMenuItemPretraziMestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPretraziMestoActionPerformed
        // TODO add your handling code here:
        try {
            Koordinator.getInstance().otvoriPretraziMesto();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da prikaže formu za pretraživanje uzrasnih kategorija.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemPretraziMestoActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        try {
            Autor autor = new Autor(this, true);
            autor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da prikaže informacije o autoru.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        try {
            Konfiguracija ks = new Konfiguracija(this, true);
            ks.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da prikaže konfiguracije.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonOdjava;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelKorisnik;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenu jMenuBicikla;
    private javax.swing.JMenu jMenuDokumenti;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItemKreirajBicikla;
    private javax.swing.JMenuItem jMenuItemKreirajIznajmljivanje;
    private javax.swing.JMenuItem jMenuItemKreirajKupac;
    private javax.swing.JMenuItem jMenuItemKreirajMesto;
    private javax.swing.JMenuItem jMenuItemKreirajProdavac;
    private javax.swing.JMenuItem jMenuItemPretraziBicikla;
    private javax.swing.JMenuItem jMenuItemPretraziIznajmljivanje;
    private javax.swing.JMenuItem jMenuItemPretraziKupac;
    private javax.swing.JMenuItem jMenuItemPretraziMesto;
    private javax.swing.JMenuItem jMenuItemPretraziProdavac;
    private javax.swing.JMenuItem jMenuItemPretraziTermin;
    private javax.swing.JMenuItem jMenuItemUbaciTermin;
    private javax.swing.JMenu jMenuIznajmljivanje;
    private javax.swing.JMenu jMenuKupac;
    private javax.swing.JMenu jMenuMesto;
    private javax.swing.JMenu jMenuProdavac;
    private javax.swing.JMenu jMenuTerminDezurstva;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    // End of variables declaration//GEN-END:variables

    public JLabel getjLabelKorisnik() {
        return jLabelKorisnik;
    }

    public void setjLabelKorisnik(JLabel jLabelKorisnik) {
        this.jLabelKorisnik = jLabelKorisnik;
    }


    public void GlavnaFormaAddActionListeners(ActionListener actionListener) {
         jButtonOdjava.addActionListener(actionListener);
    }
    
    
}
