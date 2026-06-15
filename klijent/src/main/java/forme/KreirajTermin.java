/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forme;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.Termin;

/**
 *
 * @author HP
 */
public class KreirajTermin extends javax.swing.JDialog {

    private GlavnaForma gf;
    private Termin termin;
    
    public KreirajTermin(java.awt.Frame parent, boolean modal, Termin termin) {
        super(parent, modal);
        initComponents();
        this.setTitle("Kreiraj termin");

        this.gf = (GlavnaForma) parent;
        this.termin = termin;
        this.setLocationRelativeTo(null);
        
        this.setPreferredSize(new Dimension(450, 350)); 
        this.setMinimumSize(new Dimension(450, 350));
        this.setMaximumSize(new Dimension(450, 350));
    }

    public GlavnaForma getGf() {
        return gf;
    }

    public void setGf(GlavnaForma gf) {
        this.gf = gf;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }

    public JButton getjButtonObrisi() {
        return jButtonObrisi;
    }

    public void setjButtonObrisi(JButton jButtonObrisi) {
        this.jButtonObrisi = jButtonObrisi;
    }

    public JButton getjButtonObrisiTermin() {
        return jButtonObrisiTermin;
    }

    public void setjButtonObrisiTermin(JButton jButtonObrisiTermin) {
        this.jButtonObrisiTermin = jButtonObrisiTermin;
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

    public JButton getjButtonPromeni() {
        return jButtonPromeni;
    }

    public void setjButtonPromeni(JButton jButtonPromeni) {
        this.jButtonPromeni = jButtonPromeni;
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

    public JTextField getjTextFieldNazivTermina() {
        return jTextFieldNazivTermina;
    }

    public void setjTextFieldNazivTermina(JTextField jTextFieldNazivTermina) {
        this.jTextFieldNazivTermina = jTextFieldNazivTermina;
    }

    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNazivTermina = new javax.swing.JTextField();
        jButtonPromeni = new javax.swing.JButton();
        jButtonPrikaziPromeni = new javax.swing.JButton();
        jButtonZapamti = new javax.swing.JButton();
        jButtonObrisiTermin = new javax.swing.JButton();
        jButtonPrikaziObrisi = new javax.swing.JButton();
        jButtonObrisi = new javax.swing.JButton();
        jButtonSacuvaj = new javax.swing.JButton();
        jButtonOdustani = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Termin dežurstva");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 33, -1, -1));

        jLabel2.setText("Naziv:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 92, -1, -1));
        getContentPane().add(jTextFieldNazivTermina, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 89, 220, -1));

        jButtonPromeni.setText("Promeni termin");
        getContentPane().add(jButtonPromeni, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 168, -1, -1));

        jButtonPrikaziPromeni.setText("Prikaži termin");
        jButtonPrikaziPromeni.setPreferredSize(new java.awt.Dimension(115, 23));
        getContentPane().add(jButtonPrikaziPromeni, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 168, 115, -1));

        jButtonZapamti.setText("Zapamti");
        getContentPane().add(jButtonZapamti, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 168, 115, -1));

        jButtonObrisiTermin.setText("Obriši termin");
        getContentPane().add(jButtonObrisiTermin, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 215, 115, -1));

        jButtonPrikaziObrisi.setText("Prikaži termin");
        getContentPane().add(jButtonPrikaziObrisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 215, 115, -1));

        jButtonObrisi.setText("Obriši");
        getContentPane().add(jButtonObrisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 168, 115, -1));

        jButtonSacuvaj.setText("Sačuvaj");
        getContentPane().add(jButtonSacuvaj, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 168, 115, -1));

        jButtonOdustani.setText("Odustani");
        jButtonOdustani.setPreferredSize(new java.awt.Dimension(115, 23));
        getContentPane().add(jButtonOdustani, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 215, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonObrisi;
    private javax.swing.JButton jButtonObrisiTermin;
    private javax.swing.JButton jButtonOdustani;
    private javax.swing.JButton jButtonPrikaziObrisi;
    private javax.swing.JButton jButtonPrikaziPromeni;
    private javax.swing.JButton jButtonPromeni;
    private javax.swing.JButton jButtonSacuvaj;
    private javax.swing.JButton jButtonZapamti;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextFieldNazivTermina;
    // End of variables declaration//GEN-END:variables

    
    public void ZapamtiAddActionListeners(ActionListener actionListener) {
        jButtonZapamti.addActionListener(actionListener);
    }

    public void PromeniAddActionListeners(ActionListener actionListener) {
        jButtonSacuvaj.addActionListener(actionListener);
    }

    public void ObrisiAddActionListeners(ActionListener actionListener) {
        jButtonObrisi.addActionListener(actionListener);
    }

    public void OdustaniAddActionListeners(ActionListener actionListener) {
        jButtonOdustani.addActionListener(actionListener);
    }

    public void PromeniTerminAddActionListeners(ActionListener actionListener) {
        jButtonPromeni.addActionListener(actionListener);
    }

    public void PrikaziPromeniAddActionListeners(ActionListener actionListener) {
        jButtonPrikaziPromeni.addActionListener(actionListener);
    }

    public void ObrisiTerminAddActionListeners(ActionListener actionListener) {
        jButtonObrisiTermin.addActionListener(actionListener);
    }

    public void PrikaziObrisiAddActionListeners(ActionListener actionListener) {
        jButtonPrikaziObrisi.addActionListener(actionListener);
    }
}
