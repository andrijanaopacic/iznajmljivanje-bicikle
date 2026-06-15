/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forme;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
public class PretraziMesto extends javax.swing.JDialog {

    private GlavnaForma gf;
    public PretraziMesto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.setTitle("Pretraži mesto");
        this.setPreferredSize(new Dimension(650, 500)); 
        this.setMinimumSize(new Dimension(650, 500));
        this.setMaximumSize(new Dimension(650, 500));

       this.gf = (GlavnaForma) parent;
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonPretraziSve = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldMesto = new javax.swing.JTextField();
        jButtonPretraziMesto = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonNadji = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Mesto");

        jLabel2.setText("Prikaži sva mesta:");

        jButtonPretraziSve.setText("Prikaži sva mesta");

        jLabel3.setText("Filtriraj mesta prema nazivu:");

        jButtonPretraziMesto.setText("Pretraži");

        jLabel4.setText("Pronađena mesta:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jButtonNadji.setText("Prikaži mesto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonNadji, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4)
                        .addComponent(jLabel1)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addGap(34, 34, 34)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonPretraziSve)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jTextFieldMesto, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(69, 69, 69)
                                    .addComponent(jButtonPretraziMesto, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jScrollPane1)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButtonPretraziSve))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldMesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPretraziMesto))
                .addGap(34, 34, 34)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButtonNadji)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public GlavnaForma getGf() {
        return gf;
    }

    public void setGf(GlavnaForma gf) {
        this.gf = gf;
    }

    public JButton getjButtonNadji() {
        return jButtonNadji;
    }

    public void setjButtonNadji(JButton jButtonNadji) {
        this.jButtonNadji = jButtonNadji;
    }

    public JButton getjButtonPretraziMesto() {
        return jButtonPretraziMesto;
    }

    public void setjButtonPretraziMesto(JButton jButtonPretraziMesto) {
        this.jButtonPretraziMesto = jButtonPretraziMesto;
    }

    public JButton getjButtonPretraziSve() {
        return jButtonPretraziSve;
    }

    public void setjButtonPretraziSve(JButton jButtonPretraziSve) {
        this.jButtonPretraziSve = jButtonPretraziSve;
    }

    public JTable getjTable1() {
        return jTable1;
    }

    public void setjTable1(JTable jTable1) {
        this.jTable1 = jTable1;
    }

    public JTextField getjTextFieldMesto() {
        return jTextFieldMesto;
    }

    public void setjTextFieldMesto(JTextField jTextFieldMesto) {
        this.jTextFieldMesto = jTextFieldMesto;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonNadji;
    private javax.swing.JButton jButtonPretraziMesto;
    private javax.swing.JButton jButtonPretraziSve;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldMesto;
    // End of variables declaration//GEN-END:variables

    public void VratiListuSviMestaAddActionListeners(ActionListener actionListener) {
        jButtonPretraziSve.addActionListener(actionListener);
    }

    public void VratiListuMestoSaUslovomAddActionListeners(ActionListener actionListener) {
        jButtonPretraziMesto.addActionListener(actionListener);
    }

    public void PrikaziMestoAddActionListeners(ActionListener actionListener) {
        jButtonNadji.addActionListener(actionListener);
    }
}
