/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forme;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.Kupac;
import model.Mesto;

/**
 *
 * @author HP
 */
public class KreirajKupac extends javax.swing.JDialog {

    private GlavnaForma gf;
    private Kupac kupac;

    public GlavnaForma getGf() {
        return gf;
    }

    public void setGf(GlavnaForma gf) {
        this.gf = gf;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public JButton getjButtonObrisi() {
        return jButtonObrisi;
    }

    public void setjButtonObrisi(JButton jButtonObrisi) {
        this.jButtonObrisi = jButtonObrisi;
    }

    public JButton getjButtonObrisiKupca() {
        return jButtonObrisiKupca;
    }

    public void setjButtonObrisiKupca(JButton jButtonObrisiKupca) {
        this.jButtonObrisiKupca = jButtonObrisiKupca;
    }

    public JButton getjButtonOdustani() {
        return jButtonOdustani;
    }

    public void setjButtonOdustani(JButton jButtonOdustani) {
        this.jButtonOdustani = jButtonOdustani;
    }

    public JButton getjButtonPrikaziObrisi() {
        return jButtonPrikaziObrisi;
    }

    public void setjButtonPrikaziObrisi(JButton jButtonPrikaziObrisi) {
        this.jButtonPrikaziObrisi = jButtonPrikaziObrisi;
    }

    public JButton getjButtonPrikaziPromeni() {
        return jButtonPrikaziPromeni;
    }

    public void setjButtonPrikaziPromeni(JButton jButtonPrikaziPromeni) {
        this.jButtonPrikaziPromeni = jButtonPrikaziPromeni;
    }

    public JButton getjButtonPromeniKupca() {
        return jButtonPromeniKupca;
    }

    public void setjButtonPromeniKupca(JButton jButtonPromeniKupca) {
        this.jButtonPromeniKupca = jButtonPromeniKupca;
    }

    public JButton getjButtonSacuvaj() {
        return jButtonSacuvaj;
    }

    public void setjButtonSacuvaj(JButton jButtonSacuvaj) {
        this.jButtonSacuvaj = jButtonSacuvaj;
    }

    public JButton getjButtonZapamti() {
        return jButtonZapamti;
    }

    public void setjButtonZapamti(JButton jButtonZapamti) {
        this.jButtonZapamti = jButtonZapamti;
    }

    public JComboBox<Mesto> getjComboBoxMesto() {
        return jComboBoxMesto;
    }

    public void setjComboBoxMesto(JComboBox<Mesto> jComboBoxMesto) {
        this.jComboBoxMesto = jComboBoxMesto;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JLabel getjLabel2() {
        return jLabel2;
    }

    public void setjLabel2(JLabel jLabel2) {
        this.jLabel2 = jLabel2;
    }

    public JLabel getjLabel3() {
        return jLabel3;
    }

    public void setjLabel3(JLabel jLabel3) {
        this.jLabel3 = jLabel3;
    }

    public JLabel getjLabel4() {
        return jLabel4;
    }

    public void setjLabel4(JLabel jLabel4) {
        this.jLabel4 = jLabel4;
    }

    public JTextField getjTextFieldBrojLicneKarte() {
        return jTextFieldBrojLicneKarte;
    }

    public void setjTextFieldBrojLicneKarte(JTextField jTextFieldBrojLicneKarte) {
        this.jTextFieldBrojLicneKarte = jTextFieldBrojLicneKarte;
    }

    public JTextField getjTextFieldIme() {
        return jTextFieldIme;
    }

    public void setjTextFieldIme(JTextField jTextFieldIme) {
        this.jTextFieldIme = jTextFieldIme;
    }

    public JTextField getjTextFieldPrezime() {
        return jTextFieldPrezime;
    }

    public void setjTextFieldPrezime(JTextField jTextFieldPrezime) {
        this.jTextFieldPrezime = jTextFieldPrezime;
    }

    
    
    public KreirajKupac(java.awt.Frame parent, boolean modal, Kupac kupac) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.setTitle("Kreiraj kupca");
        this.setPreferredSize(new Dimension(850, 500)); 
        this.setMinimumSize(new Dimension(850, 500));
        this.setMaximumSize(new Dimension(850, 500));

        this.gf = (GlavnaForma) parent;
        this.kupac = kupac;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldIme = new javax.swing.JTextField();
        jTextFieldPrezime = new javax.swing.JTextField();
        jTextFieldBrojLicneKarte = new javax.swing.JTextField();
        jComboBoxMesto = new javax.swing.JComboBox<>();
        jButtonOdustani = new javax.swing.JButton();
        jButtonSacuvaj = new javax.swing.JButton();
        jButtonPrikaziPromeni = new javax.swing.JButton();
        jButtonObrisi = new javax.swing.JButton();
        jButtonPrikaziObrisi = new javax.swing.JButton();
        jButtonZapamti = new javax.swing.JButton();
        jButtonObrisiKupca = new javax.swing.JButton();
        jButtonPromeniKupca = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Ime:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 58, -1, -1));

        jLabel2.setText("Prezime:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 112, -1, -1));

        jLabel3.setText("Broj lične karte:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 174, -1, -1));

        jLabel4.setText("Mesto:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 271, -1, -1));
        getContentPane().add(jTextFieldIme, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 55, 292, -1));
        getContentPane().add(jTextFieldPrezime, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 109, 292, -1));
        getContentPane().add(jTextFieldBrojLicneKarte, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 174, 292, -1));

        getContentPane().add(jComboBoxMesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 268, 292, -1));

        jButtonOdustani.setText("ODUSTANI");
        jButtonOdustani.setPreferredSize(new java.awt.Dimension(75, 23));
        getContentPane().add(jButtonOdustani, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 220, 146, 50));

        jButtonSacuvaj.setText("SAČUVAJ");
        jButtonSacuvaj.setMaximumSize(new java.awt.Dimension(75, 23));
        jButtonSacuvaj.setMinimumSize(new java.awt.Dimension(75, 23));
        jButtonSacuvaj.setPreferredSize(new java.awt.Dimension(75, 23));
        getContentPane().add(jButtonSacuvaj, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 117, 146, 55));

        jButtonPrikaziPromeni.setText("PRIKAŽI KUPCA");
        getContentPane().add(jButtonPrikaziPromeni, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 347, 146, 48));

        jButtonObrisi.setText("OBRIŠI KUPCA");
        jButtonObrisi.setPreferredSize(new java.awt.Dimension(75, 23));
        jButtonObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonObrisiActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonObrisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 117, 146, 55));

        jButtonPrikaziObrisi.setText("PRIKAŽI KUPCA");
        jButtonPrikaziObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrikaziObrisiActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonPrikaziObrisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 347, 146, 48));

        jButtonZapamti.setText("ZAPAMTI");
        jButtonZapamti.setMaximumSize(new java.awt.Dimension(75, 23));
        jButtonZapamti.setMinimumSize(new java.awt.Dimension(75, 23));
        jButtonZapamti.setPreferredSize(new java.awt.Dimension(75, 23));
        getContentPane().add(jButtonZapamti, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 117, 146, 55));

        jButtonObrisiKupca.setText("OBRIŠI ");
        jButtonObrisiKupca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonObrisiKupcaActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonObrisiKupca, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 347, 146, 48));

        jButtonPromeniKupca.setText("PROMENI ");
        getContentPane().add(jButtonPromeniKupca, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 347, 146, 48));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonObrisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonObrisiActionPerformed

    private void jButtonPrikaziObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrikaziObrisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPrikaziObrisiActionPerformed

    private void jButtonObrisiKupcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonObrisiKupcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonObrisiKupcaActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonObrisi;
    private javax.swing.JButton jButtonObrisiKupca;
    private javax.swing.JButton jButtonOdustani;
    private javax.swing.JButton jButtonPrikaziObrisi;
    private javax.swing.JButton jButtonPrikaziPromeni;
    private javax.swing.JButton jButtonPromeniKupca;
    private javax.swing.JButton jButtonSacuvaj;
    private javax.swing.JButton jButtonZapamti;
    private javax.swing.JComboBox<Mesto> jComboBoxMesto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextFieldBrojLicneKarte;
    private javax.swing.JTextField jTextFieldIme;
    private javax.swing.JTextField jTextFieldPrezime;
    // End of variables declaration//GEN-END:variables

    public void ZapamtiAddActionListeners(ActionListener actionListener) {
        jButtonZapamti.addActionListener(actionListener);
    }    
        
    public void OdustaniAddActionListeners(ActionListener actionListener) {
        jButtonOdustani.addActionListener(actionListener);
    }

    public void PromeniAddActionListeners(ActionListener actionListener) {
        jButtonSacuvaj.addActionListener(actionListener);
    }

    public void ObrisiAddActionListeners(ActionListener actionListener) {
       jButtonObrisi.addActionListener(actionListener);
    }

    public void PromeniKupcaAddActionListeners(ActionListener actionListener) {
       jButtonPromeniKupca.addActionListener(actionListener);
    }

    public void PrikaziPromeniAddActionListeners(ActionListener actionListener) {
        jButtonPrikaziPromeni.addActionListener(actionListener);
    }

    public void ObrisiKupcaAddActionListeners(ActionListener actionListener) {
        jButtonObrisiKupca.addActionListener(actionListener);
    }

    public void PrikaziObrisiAddActionListeners(ActionListener actionListener) {
        jButtonPrikaziObrisi.addActionListener(actionListener);
    }

    
    
}
