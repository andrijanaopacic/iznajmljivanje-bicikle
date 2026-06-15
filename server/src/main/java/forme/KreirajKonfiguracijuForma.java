/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forme;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
import konfiguracija.Konfiguracija;

/**
 *
 * @author HP
 */
public class KreirajKonfiguracijuForma extends javax.swing.JDialog {

    private boolean ispravniPodaci = true;

    public boolean isIspravniPodaci() {
        return ispravniPodaci;
    }

    public void setIspravniPodaci(boolean ispravniPodaci) {
        this.ispravniPodaci = ispravniPodaci;
    }
    
    public KreirajKonfiguracijuForma(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Konfiguracija");
        this.setLocationRelativeTo(null);
        this.setSize(540, 320);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldUrl = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldPort = new javax.swing.JTextField();
        jTextFieldPassword = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("URL:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Username:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Password:");

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Sačuvaj");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Port:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("KREIRAJ KONFIGURACIJU");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                    .addComponent(jTextFieldUrl)
                                    .addComponent(jTextFieldPort)
                                    .addComponent(jTextFieldPassword))))
                        .addGap(46, 46, 46))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel5)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(jButton1)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        String portText = jTextFieldPort.getText().trim();
        String username = jTextFieldUsername.getText().trim();
        String password = jTextFieldPassword.getText().trim();
        String url = jTextFieldUrl.getText().trim();

        jTextFieldPort.setText("");
        jTextFieldUrl.setText("");
        jTextFieldUsername.setText("");
        jTextFieldPassword.setText("");

        int port;

        if (portText.trim().isEmpty() || username.trim().isEmpty() || url.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Niste uneli sve podatke.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            port = Integer.parseInt(portText);
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(this, "Niste uneli broj porta u ispravnom formatu.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (port < 0 || port > 65535) {
            JOptionPane.showMessageDialog(this, "Niste uneli broj porta u okviru dozvoljenog opsega (0-65535).", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String putanja = "config/config/config.properties";

        Properties podaci = new Properties();

        podaci.setProperty("port", String.valueOf(port));
        podaci.setProperty("url", url);
        podaci.setProperty("username", username);
        podaci.setProperty("password", password);

        try (FileOutputStream outputStream = new FileOutputStream(putanja)) {
            podaci.store(outputStream, "Konfiguracija");
            JOptionPane.showMessageDialog(this, "Konfiguracioni fajl je kreiran na putanji: " + putanja, "Uspešno", JOptionPane.INFORMATION_MESSAGE);
         
            this.dispose();
        } catch (IOException e) {
            System.err.println("Greška prilikom kreiranja konfiguracionog fajla: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Greška prilikom kreiranja konfiguracionog fajla.", "Neuspešno", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextFieldPassword;
    private javax.swing.JTextField jTextFieldPort;
    private javax.swing.JTextField jTextFieldUrl;
    private javax.swing.JTextField jTextFieldUsername;
    // End of variables declaration//GEN-END:variables
}
