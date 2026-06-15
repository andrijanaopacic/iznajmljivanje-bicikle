/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forme;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
public class PretraziKupac extends javax.swing.JDialog {

    private GlavnaForma gf;
    
    public PretraziKupac(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);

        setResizable(false);
        this.setTitle("Pretraži kupca");
        
        this.gf = (GlavnaForma) parent;
//        System.out.println("PRRETRAZI KUPAC KRAJ KONSTRUKTORA");
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonPrikaziSveKupce = new javax.swing.JButton();
        jRadioButtonFiltrirajImePrez = new javax.swing.JRadioButton();
        jRadioButtonFiltrirajPoMestu = new javax.swing.JRadioButton();
        jTextFieldFiltrirajPoImePrez = new javax.swing.JTextField();
        jTextFieldFiltrirajPoMestu = new javax.swing.JTextField();
        jButtonPretrazi = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableKupci = new javax.swing.JTable();
        jButtonPrikaziKupca = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("KUPCI");

        jLabel2.setText("Prikaži sve kupce:");

        jButtonPrikaziSveKupce.setText("PRIKAŽI");
        jButtonPrikaziSveKupce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrikaziSveKupceActionPerformed(evt);
            }
        });

        jRadioButtonFiltrirajImePrez.setText("Filtriraj kupce po imenu i prezimenu");

        jRadioButtonFiltrirajPoMestu.setText("Filtriraj kupce po mestu");

        jButtonPretrazi.setText("PRETRAŽI");
        jButtonPretrazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPretraziActionPerformed(evt);
            }
        });

        jLabel3.setText("PRONAĐENI KUPCI");

        jTableKupci.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableKupci);

        jButtonPrikaziKupca.setText("PRIKAŽI KUPCA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(88, 88, 88)
                        .addComponent(jButtonPrikaziSveKupce, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButtonFiltrirajImePrez)
                        .addGap(47, 47, 47)
                        .addComponent(jTextFieldFiltrirajPoImePrez, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jButtonPretrazi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButtonFiltrirajPoMestu)
                        .addGap(113, 113, 113)
                        .addComponent(jTextFieldFiltrirajPoMestu, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(196, 196, 196))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonPrikaziKupca)
                .addGap(133, 133, 133))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jButtonPrikaziSveKupce))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonFiltrirajImePrez)
                    .addComponent(jTextFieldFiltrirajPoImePrez, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButtonPretrazi)))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonFiltrirajPoMestu)
                    .addComponent(jTextFieldFiltrirajPoMestu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jLabel3)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButtonPrikaziKupca)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPretraziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPretraziActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPretraziActionPerformed

    private void jButtonPrikaziSveKupceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrikaziSveKupceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPrikaziSveKupceActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPretrazi;
    private javax.swing.JButton jButtonPrikaziKupca;
    private javax.swing.JButton jButtonPrikaziSveKupce;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton jRadioButtonFiltrirajImePrez;
    private javax.swing.JRadioButton jRadioButtonFiltrirajPoMestu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableKupci;
    private javax.swing.JTextField jTextFieldFiltrirajPoImePrez;
    private javax.swing.JTextField jTextFieldFiltrirajPoMestu;
    // End of variables declaration//GEN-END:variables

    public GlavnaForma getGf() {
        return gf;
    }

    public void setGf(GlavnaForma gf) {
        this.gf = gf;
    }

    public JButton getjButtonPretrazi() {
        return jButtonPretrazi;
    }

    public void setjButtonPretrazi(JButton jButtonPretrazi) {
        this.jButtonPretrazi = jButtonPretrazi;
    }

    public JButton getjButtonPrikaziKupca() {
        return jButtonPrikaziKupca;
    }

    public void setjButtonPrikaziKupca(JButton jButtonPrikaziKupca) {
        this.jButtonPrikaziKupca = jButtonPrikaziKupca;
    }

    public JButton getjButtonPrikaziSveKupce() {
        return jButtonPrikaziSveKupce;
    }

    public void setjButtonPrikaziSveKupce(JButton jButtonPrikaziSveKupce) {
        this.jButtonPrikaziSveKupce = jButtonPrikaziSveKupce;
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

    public JRadioButton getjRadioButtonFiltrirajImePrez() {
        return jRadioButtonFiltrirajImePrez;
    }

    public void setjRadioButtonFiltrirajImePrez(JRadioButton jRadioButtonFiltrirajImePrez) {
        this.jRadioButtonFiltrirajImePrez = jRadioButtonFiltrirajImePrez;
    }

    public JRadioButton getjRadioButtonFiltrirajPoMestu() {
        return jRadioButtonFiltrirajPoMestu;
    }

    public void setjRadioButtonFiltrirajPoMestu(JRadioButton jRadioButtonFiltrirajPoMestu) {
        this.jRadioButtonFiltrirajPoMestu = jRadioButtonFiltrirajPoMestu;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JTable getjTableKupci() {
        return jTableKupci;
    }

    public void setjTableKupci(JTable jTableKupci) {
        this.jTableKupci = jTableKupci;
    }

    public JTextField getjTextFieldFiltrirajPoImePrez() {
        return jTextFieldFiltrirajPoImePrez;
    }

    public void setjTextFieldFiltrirajPoImePrez(JTextField jTextFieldFiltrirajPoImePrez) {
        this.jTextFieldFiltrirajPoImePrez = jTextFieldFiltrirajPoImePrez;
    }

    public JTextField getjTextFieldFiltrirajPoMestu() {
        return jTextFieldFiltrirajPoMestu;
    }

    public void setjTextFieldFiltrirajPoMestu(JTextField jTextFieldFiltrirajPoMestu) {
        this.jTextFieldFiltrirajPoMestu = jTextFieldFiltrirajPoMestu;
    }

    public void VratiListuSvihKupacaAddActionListeners(ActionListener actionListener) {
        jButtonPrikaziSveKupce.addActionListener(actionListener);
    }


    public void VratiListuKupacaSaUslovomAddActionListeners(ActionListener actionListener) {
        jButtonPretrazi.addActionListener(actionListener);
    }

    public void PrikaziKupcaAddActionListeners(ActionListener actionListener) {
        jButtonPrikaziKupca.addActionListener(actionListener);
    }

    public void RadioMestoAddActionListeners(ActionListener actionListener) {
        jRadioButtonFiltrirajPoMestu.addActionListener(actionListener);
    }

    public void RadioImePrezAddActionListeners(ActionListener actionListener) {
        jRadioButtonFiltrirajImePrez.addActionListener(actionListener);
    }

    
}
