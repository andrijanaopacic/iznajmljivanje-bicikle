/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forme;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import model.Mesto;

/**
 *
 * @author HP
 */
public class KreirajMesto extends javax.swing.JDialog {

    private GlavnaForma gf;
    private Mesto mesto;
    
    public KreirajMesto(java.awt.Frame parent, boolean modal, Mesto mesto) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.setTitle("Kreiraj mesto");
        this.setPreferredSize(new Dimension(650, 500)); 
        this.setMinimumSize(new Dimension(650, 500));
        this.setMaximumSize(new Dimension(650, 500));

        this.gf = (GlavnaForma) parent;
        this.mesto = mesto;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldMesto = new javax.swing.JTextField();
        jButtonPromeni = new javax.swing.JButton();
        jButtonPrikaziPromeni = new javax.swing.JButton();
        jButtonObrisiMesto = new javax.swing.JButton();
        jButtonPrikaziObrisi = new javax.swing.JButton();
        jButtonSacuvaj = new javax.swing.JButton();
        jButtonObrisi = new javax.swing.JButton();
        jButtonZapamti = new javax.swing.JButton();
        jButtonOdustani = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Mesto");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 30, -1, -1));

        jLabel2.setText("Naziv mesta:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 79, -1, -1));
        getContentPane().add(jTextFieldMesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 76, 182, -1));

        jButtonPromeni.setText("Promeni mesto");
        getContentPane().add(jButtonPromeni, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 133, 137, -1));

        jButtonPrikaziPromeni.setText("Prikaži mesto");
        getContentPane().add(jButtonPrikaziPromeni, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 133, 137, -1));

        jButtonObrisiMesto.setText("Obriši mesto");
        getContentPane().add(jButtonObrisiMesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 174, 137, -1));

        jButtonPrikaziObrisi.setText("Prikaži mesto");
        getContentPane().add(jButtonPrikaziObrisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 174, 137, -1));

        jButtonSacuvaj.setText("Sačuvaj");
        getContentPane().add(jButtonSacuvaj, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 133, 137, -1));

        jButtonObrisi.setText("Obriši");
        getContentPane().add(jButtonObrisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 133, 137, -1));

        jButtonZapamti.setText("Zapamti");
        getContentPane().add(jButtonZapamti, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 133, 137, -1));

        jButtonOdustani.setText("Odustani");
        getContentPane().add(jButtonOdustani, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 174, 137, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public GlavnaForma getGf() {
        return gf;
    }

    public void setGf(GlavnaForma gf) {
        this.gf = gf;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public JButton getjButtonObrisi() {
        return jButtonObrisi;
    }

    public void setjButtonObrisi(JButton jButtonObrisi) {
        this.jButtonObrisi = jButtonObrisi;
    }

    public JButton getjButtonObrisiMesto() {
        return jButtonObrisiMesto;
    }

    public void setjButtonObrisiMesto(JButton jButtonObrisiMesto) {
        this.jButtonObrisiMesto = jButtonObrisiMesto;
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

    public JTextField getjTextFieldMesto() {
        return jTextFieldMesto;
    }

    public void setjTextFieldMesto(JTextField jTextFieldMesto) {
        this.jTextFieldMesto = jTextFieldMesto;
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonObrisi;
    private javax.swing.JButton jButtonObrisiMesto;
    private javax.swing.JButton jButtonOdustani;
    private javax.swing.JButton jButtonPrikaziObrisi;
    private javax.swing.JButton jButtonPrikaziPromeni;
    private javax.swing.JButton jButtonPromeni;
    private javax.swing.JButton jButtonSacuvaj;
    private javax.swing.JButton jButtonZapamti;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextFieldMesto;
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

    public void PromeniMestoAddActionListeners(ActionListener actionListener) {
        jButtonPromeni.addActionListener(actionListener);
    }

    public void PrikaziPromeniAddActionListeners(ActionListener actionListener) {
        jButtonPrikaziPromeni.addActionListener(actionListener);
    }

    public void ObrisiMestoAddActionListeners(ActionListener actionListener) {
        jButtonObrisiMesto.addActionListener(actionListener);
    }

    public void PrikaziObrisiAddActionListeners(ActionListener actionListener) {
        jButtonPrikaziObrisi.addActionListener(actionListener);
    }

}
