/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forme;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.Termin;

/**
 *
 * @author HP
 */
public class PretraziProdavac extends javax.swing.JDialog {

    private GlavnaForma gf;
    
    public PretraziProdavac(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.gf = (GlavnaForma) parent;
        this.setSize(914, 656);
        this.setLocationRelativeTo(null);
        System.out.println("PRETRAZI PRODAVAC KRAJ KONSTRUKTORA");
    }

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

    public JButton getjButtonPrikaziProdavca() {
        return jButtonPrikaziProdavca;
    }

    public void setjButtonPrikaziProdavca(JButton jButtonPrikaziProdavca) {
        this.jButtonPrikaziProdavca = jButtonPrikaziProdavca;
    }

    public JButton getjButtonPrikaziSve() {
        return jButtonPrikaziSve;
    }

    public void setjButtonPrikaziSve(JButton jButtonPrikaziSve) {
        this.jButtonPrikaziSve = jButtonPrikaziSve;
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

    public JRadioButton getjRadioButtonFiltrirajImePrezime() {
        return jRadioButtonFiltrirajImePrezime;
    }

    public void setjRadioButtonFiltrirajImePrezime(JRadioButton jRadioButtonFiltrirajImePrezime) {
        this.jRadioButtonFiltrirajImePrezime = jRadioButtonFiltrirajImePrezime;
    }

    public JRadioButton getjRadioButtonFiltrirajPrematerminu() {
        return jRadioButtonFiltrirajPrematerminu;
    }

    public void setjRadioButtonFiltrirajPrematerminu(JRadioButton jRadioButtonFiltrirajPrematerminu) {
        this.jRadioButtonFiltrirajPrematerminu = jRadioButtonFiltrirajPrematerminu;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JTable getjTableProdavci() {
        return jTableProdavci;
    }

    public void setjTableProdavci(JTable jTableProdavci) {
        this.jTableProdavci = jTableProdavci;
    }

    public JTextField getjTextFieldImePrez() {
        return jTextFieldImePrez;
    }

    public void setjTextFieldImePrez(JTextField jTextFieldImePrez) {
        this.jTextFieldImePrez = jTextFieldImePrez;
    }

    public JComboBox<Termin> getjComboBoxTermini() {
        return jComboBoxTermini;
    }

    public void setjComboBoxTermini(JComboBox<Termin> jComboBoxTermini) {
        this.jComboBoxTermini = jComboBoxTermini;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonPrikaziSve = new javax.swing.JButton();
        jRadioButtonFiltrirajImePrezime = new javax.swing.JRadioButton();
        jRadioButtonFiltrirajPrematerminu = new javax.swing.JRadioButton();
        jTextFieldImePrez = new javax.swing.JTextField();
        jButtonPretrazi = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProdavci = new javax.swing.JTable();
        jButtonPrikaziProdavca = new javax.swing.JButton();
        jComboBoxTermini = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("PRODAVCI");

        jLabel2.setText("Prikaži sve prodavce:");

        jButtonPrikaziSve.setText("PRIKAŽI");

        jRadioButtonFiltrirajImePrezime.setText("Filtriraj prodavce po imenu i prezimenu");

        jRadioButtonFiltrirajPrematerminu.setText("Filtriraj prodavce prema terminu");

        jButtonPretrazi.setText("PRETRAŽI");

        jLabel3.setText("Pronađeni prodavci");

        jTableProdavci.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableProdavci);

        jButtonPrikaziProdavca.setText("PRIKAŽI PRODAVCA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(93, 93, 93)
                                .addComponent(jButtonPrikaziSve, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButtonFiltrirajImePrezime)
                                    .addComponent(jRadioButtonFiltrirajPrematerminu))
                                .addGap(69, 69, 69)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldImePrez, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                    .addComponent(jComboBoxTermini, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonPretrazi))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonPrikaziProdavca, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(61, 61, 61))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButtonPrikaziSve))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonFiltrirajImePrezime)
                            .addComponent(jTextFieldImePrez, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonFiltrirajPrematerminu)
                            .addComponent(jComboBoxTermini, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jButtonPretrazi)))
                .addGap(32, 32, 32)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonPrikaziProdavca, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPretrazi;
    private javax.swing.JButton jButtonPrikaziProdavca;
    private javax.swing.JButton jButtonPrikaziSve;
    private javax.swing.JComboBox<Termin> jComboBoxTermini;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton jRadioButtonFiltrirajImePrezime;
    private javax.swing.JRadioButton jRadioButtonFiltrirajPrematerminu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProdavci;
    private javax.swing.JTextField jTextFieldImePrez;
    // End of variables declaration//GEN-END:variables

    public void VratiListuSvihProdavacaAddActionListeners(ActionListener actionListener) {
        jButtonPrikaziSve.addActionListener(actionListener);
    }

    public void VratiListuProdavacaSaUslovomAddActionListeners(ActionListener actionListener) {
        jButtonPretrazi.addActionListener(actionListener);
    }

    public void PrikaziProdavcaAddActionListeners(ActionListener actionListener) {
        jButtonPrikaziProdavca.addActionListener(actionListener);
    }

    public void RadioImePrezimeAddActionListeners(ActionListener actionListener) {
        jRadioButtonFiltrirajImePrezime.addActionListener(actionListener);
    }

    public void RadioterminAddActionListeners(ActionListener actionListener) {
        jRadioButtonFiltrirajPrematerminu.addActionListener(actionListener);
    }
}
