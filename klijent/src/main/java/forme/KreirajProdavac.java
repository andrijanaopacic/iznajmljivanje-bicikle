/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forme;

import java.awt.Frame;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.Prodavac;
import model.Termin;

/**
 *
 * @author HP
 */
public class KreirajProdavac extends javax.swing.JDialog {

    private GlavnaForma gf;
    private Prodavac prodavac;
    private boolean prikazi = true;
    
    public KreirajProdavac(Frame parent, boolean modal, Prodavac prodavac) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.gf = (GlavnaForma) parent;
        this.prodavac = prodavac;
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldIme = new javax.swing.JTextField();
        jTextFieldPrezime = new javax.swing.JTextField();
        jTextFieldKorisnickoIme = new javax.swing.JTextField();
        jPasswordFieldSifra = new javax.swing.JPasswordField();
        jButtonPrikaziSifru = new javax.swing.JButton();
        jButtonZapamti = new javax.swing.JButton();
        jButtonOdustani = new javax.swing.JButton();
        jButtonPromeni = new javax.swing.JButton();
        jButtonPrikaziPromeni = new javax.swing.JButton();
        jButtonObrisiProdavca = new javax.swing.JButton();
        jButtonSacuvaj = new javax.swing.JButton();
        jButtonObrisi = new javax.swing.JButton();
        jButtonPrikaziObrisi = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldDatumDezurstva = new javax.swing.JTextField();
        jTextFieldSmena = new javax.swing.JTextField();
        jButtonDodajNovTermin = new javax.swing.JButton();
        jButtonDodaj = new javax.swing.JButton();
        jButtonObrisiTermin = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTermini = new javax.swing.JTable();
        jComboBoxNazivTermina = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("PRODAVAC");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel2.setText("Ime:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 86, -1, -1));

        jLabel3.setText("Prezime:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 123, -1, -1));

        jLabel4.setText("Korisničko ime:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 163, -1, -1));

        jLabel5.setText("Šifra:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 204, -1, -1));
        getContentPane().add(jTextFieldIme, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 83, 215, -1));
        getContentPane().add(jTextFieldPrezime, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 123, 215, -1));
        getContentPane().add(jTextFieldKorisnickoIme, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 163, 215, -1));
        getContentPane().add(jPasswordFieldSifra, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 204, 173, -1));

        jButtonPrikaziSifru.setText("👁");
        jButtonPrikaziSifru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrikaziSifruActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonPrikaziSifru, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 204, -1, -1));

        jButtonZapamti.setText("ZAPAMTI");
        getContentPane().add(jButtonZapamti, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 106, 153, -1));

        jButtonOdustani.setText("ODUSTANI");
        jButtonOdustani.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOdustaniActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonOdustani, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 163, 147, -1));

        jButtonPromeni.setText("PROMENI PRODAVCA");
        getContentPane().add(jButtonPromeni, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 106, -1, -1));

        jButtonPrikaziPromeni.setText("PRIKAŽI PRODAVCA");
        getContentPane().add(jButtonPrikaziPromeni, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 106, 153, -1));

        jButtonObrisiProdavca.setText("OBRIŠI PRODAVCA");
        getContentPane().add(jButtonObrisiProdavca, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 163, 153, -1));

        jButtonSacuvaj.setText("SAČUVAJ");
        getContentPane().add(jButtonSacuvaj, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 106, 147, -1));

        jButtonObrisi.setText("OBRIŠI");
        getContentPane().add(jButtonObrisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 106, 147, -1));

        jButtonPrikaziObrisi.setText("PRIKAŽI PRODAVCA");
        getContentPane().add(jButtonPrikaziObrisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 163, 153, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 258, 878, 10));

        jLabel6.setText("TERMIN");

        jLabel7.setText("Datum dežurstva (dd.MM.yyyy):");

        jLabel8.setText("Smena:");

        jTextFieldDatumDezurstva.setText("10.10.2024.");

        jTextFieldSmena.setText("prva");

        jButtonDodajNovTermin.setText("DODAJ NOV TERMIN");

        jButtonDodaj.setText("DODAJ");

        jButtonObrisiTermin.setText("OBRIŠI");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 829, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(60, 60, 60)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldDatumDezurstva, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldSmena, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonObrisiTermin, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(jButtonDodajNovTermin, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addComponent(jComboBoxNazivTermina, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldDatumDezurstva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxNazivTermina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldSmena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDodajNovTermin))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDodaj)
                    .addComponent(jButtonObrisiTermin))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 280, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPrikaziSifruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrikaziSifruActionPerformed
//        if (jPasswordFieldSifra.getEchoChar() == (char)0) { // Proverava da li su šifre maskirane
//            jPasswordFieldSifra.setEchoChar('*'); // Prikazuje šifru
//        } else {
//            jPasswordFieldSifra.setEchoChar((char)0); // Maskira šifru ponovo (korišćenje tačke)
//        }

    }//GEN-LAST:event_jButtonPrikaziSifruActionPerformed

    private void jButtonOdustaniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOdustaniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonOdustaniActionPerformed

    public GlavnaForma getGf() {
        return gf;
    }

    public void setGf(GlavnaForma gf) {
        this.gf = gf;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public boolean isPrikazi() {
        return prikazi;
    }

    public void setPrikazi(boolean prikazi) {
        this.prikazi = prikazi;
    }

    public JComboBox<Termin> getjComboBoxNazivTermina() {
        return jComboBoxNazivTermina;
    }

    public void setjComboBoxNazivTermina(JComboBox<Termin> jComboBoxNazivTermina) {
        this.jComboBoxNazivTermina = jComboBoxNazivTermina;
    }

    public JButton getjButtonPrikaziSifru() {
        return jButtonPrikaziSifru;
    }

    public void setjButtonPrikaziSifru(JButton jButtonPrikaziSifru) {
        this.jButtonPrikaziSifru = jButtonPrikaziSifru;
    }
    
    
    public JButton getjButtonDodaj() {
        return jButtonDodaj;
    }

    public void setjButtonDodaj(JButton jButtonDodaj) {
        this.jButtonDodaj = jButtonDodaj;
    }

    public JButton getjButtonDodajNovTermin() {
        return jButtonDodajNovTermin;
    }

    public void setjButtonDodajNovTermin(JButton jButtonDodajNovTermin) {
        this.jButtonDodajNovTermin = jButtonDodajNovTermin;
    }

    public JButton getjButtonObrisi() {
        return jButtonObrisi;
    }

    public void setjButtonObrisi(JButton jButtonObrisi) {
        this.jButtonObrisi = jButtonObrisi;
    }

    public JButton getjButtonObrisiProdavca() {
        return jButtonObrisiProdavca;
    }

    public void setjButtonObrisiProdavca(JButton jButtonObrisiProdavca) {
        this.jButtonObrisiProdavca = jButtonObrisiProdavca;
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

    
    

    public JPasswordField getjPasswordFieldSifra() {
        return jPasswordFieldSifra;
    }

    public void setjPasswordFieldSifra(JPasswordField jPasswordFieldSifra) {
        this.jPasswordFieldSifra = jPasswordFieldSifra;
    }

    public JTable getjTableTermini() {
        return jTableTermini;
    }

    public void setjTableTermini(JTable jTable1) {
        this.jTableTermini = jTable1;
    }

    public JTextField getjTextFieldDatumDezurstva() {
        return jTextFieldDatumDezurstva;
    }

    public void setjTextFieldDatumDezurstva(JTextField jTextFieldDatumDezurstva) {
        this.jTextFieldDatumDezurstva = jTextFieldDatumDezurstva;
    }

    public JTextField getjTextFieldIme() {
        return jTextFieldIme;
    }

    public void setjTextFieldIme(JTextField jTextFieldIme) {
        this.jTextFieldIme = jTextFieldIme;
    }

    public JTextField getjTextFieldKorisnickoIme() {
        return jTextFieldKorisnickoIme;
    }

    public void setjTextFieldKorisnickoIme(JTextField jTextFieldKorisnickoIme) {
        this.jTextFieldKorisnickoIme = jTextFieldKorisnickoIme;
    }

    public JTextField getjTextFieldPrezime() {
        return jTextFieldPrezime;
    }

    public void setjTextFieldPrezime(JTextField jTextFieldPrezime) {
        this.jTextFieldPrezime = jTextFieldPrezime;
    }

    public JTextField getjTextFieldSmena() {
        return jTextFieldSmena;
    }

    public void setjTextFieldSmena(JTextField jTextFieldSmena) {
        this.jTextFieldSmena = jTextFieldSmena;
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDodaj;
    private javax.swing.JButton jButtonDodajNovTermin;
    private javax.swing.JButton jButtonObrisi;
    private javax.swing.JButton jButtonObrisiProdavca;
    private javax.swing.JButton jButtonObrisiTermin;
    private javax.swing.JButton jButtonOdustani;
    private javax.swing.JButton jButtonPrikaziObrisi;
    private javax.swing.JButton jButtonPrikaziPromeni;
    private javax.swing.JButton jButtonPrikaziSifru;
    private javax.swing.JButton jButtonPromeni;
    private javax.swing.JButton jButtonSacuvaj;
    private javax.swing.JButton jButtonZapamti;
    private javax.swing.JComboBox<Termin> jComboBoxNazivTermina;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordFieldSifra;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableTermini;
    private javax.swing.JTextField jTextFieldDatumDezurstva;
    private javax.swing.JTextField jTextFieldIme;
    private javax.swing.JTextField jTextFieldKorisnickoIme;
    private javax.swing.JTextField jTextFieldPrezime;
    private javax.swing.JTextField jTextFieldSmena;
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

    public void ObrisiProdavcaAddActionListeners(ActionListener actionListener) {
        jButtonObrisiProdavca.addActionListener(actionListener);
    }

    public void PrikaziObrisiAddActionListeners(ActionListener actionListener) {
        jButtonPrikaziObrisi.addActionListener(actionListener);
    }

    public void PrikaziPromeniAddActionListeners(ActionListener actionListener) {
        jButtonPrikaziPromeni.addActionListener(actionListener);
    }

    public void PromeniProdavcaAddActionListeners(ActionListener actionListener) {
        jButtonPromeni.addActionListener(actionListener);
    }

    public void DodajNovTerminAddActionListeners(ActionListener actionListener) {
        jButtonDodajNovTermin.addActionListener(actionListener);
    }

    public void OdustaniAddActionListeners(ActionListener actionListener) {
        jButtonOdustani.addActionListener(actionListener);
    }

    public void DodajTerminAddActionListeners(ActionListener actionListener) {
        jButtonDodaj.addActionListener(actionListener);
    }

    public void ObrisiTerminAddActionListeners(ActionListener actionListener) {
        jButtonObrisiTermin.addActionListener(actionListener);
    }

    public void prikaziSifruAddActionListeners(ActionListener actionListener) {
        jButtonPrikaziSifru.addActionListener(actionListener);
    }
}
