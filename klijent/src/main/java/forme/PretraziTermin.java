/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forme;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
public class PretraziTermin extends javax.swing.JDialog {

    private GlavnaForma gf;
    
    public PretraziTermin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.gf = (GlavnaForma) parent;
        this.setLocationRelativeTo(null);
    }

    public GlavnaForma getGf() {
        return gf;
    }

    public void setGf(GlavnaForma gf) {
        this.gf = gf;
    }

    public JButton getjButtonNadji() {
        return jButtonNadji;
    }

    public JTable getjTableTermini() {
        return jTableTermini;
    }

    public void setjTableTermini(JTable jTableTermini) {
        this.jTableTermini = jTableTermini;
    }
    
    

    public void setjButtonNadji(JButton jButtonNadji) {
        this.jButtonNadji = jButtonNadji;
    }

    public JButton getjButtonPretrazi() {
        return jButtonPretrazi;
    }

    public void setjButtonPretrazi(JButton jButtonPretrazi) {
        this.jButtonPretrazi = jButtonPretrazi;
    }

    public JButton getjButtonPrikazi() {
        return jButtonPrikazi;
    }

    public void setjButtonPrikazi(JButton jButtonPrikazi) {
        this.jButtonPrikazi = jButtonPrikazi;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JTextField getjTextFieldPretraziPoNazivu() {
        return jTextFieldPretraziPoNazivu;
    }

    public void setjTextFieldPretraziPoNazivu(JTextField jTextFieldPretraziPoNazivu) {
        this.jTextFieldPretraziPoNazivu = jTextFieldPretraziPoNazivu;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTermini = new javax.swing.JTable();
        jButtonPrikazi = new javax.swing.JButton();
        jTextFieldPretraziPoNazivu = new javax.swing.JTextField();
        jButtonPretrazi = new javax.swing.JButton();
        jButtonNadji = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Termini dežurstava");

        jLabel2.setText("Prikaži sve termine:");

        jLabel3.setText("Filtriraj po nazivu:");

        jLabel4.setText("Pronađeni termini:");

        jTableTermini.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableTermini);

        jButtonPrikazi.setText("Prikaži");

        jButtonPretrazi.setText("Pretraži");

        jButtonNadji.setText("Prikaži termin");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonPrikazi)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldPretraziPoNazivu, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                .addComponent(jButtonPretrazi)))))
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonNadji)
                .addGap(85, 85, 85))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButtonPrikazi))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldPretraziPoNazivu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPretrazi))
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jButtonNadji)
                .addGap(77, 77, 77))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonNadji;
    private javax.swing.JButton jButtonPretrazi;
    private javax.swing.JButton jButtonPrikazi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTermini;
    private javax.swing.JTextField jTextFieldPretraziPoNazivu;
    // End of variables declaration//GEN-END:variables

    public void VratiListuTerminaSaUslovomAddActionListeners(ActionListener actionListener) {
       jButtonPretrazi.addActionListener(actionListener);
    }

    public void PrikaziTerminAddActionListeners(ActionListener actionListener) {
        jButtonNadji.addActionListener(actionListener);
    }
    
    public void VratiListuSvihTerminaAddActionListeners(ActionListener actionListener) {
        jButtonPrikazi.addActionListener(actionListener);
    }
}
