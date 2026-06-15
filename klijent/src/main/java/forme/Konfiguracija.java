/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forme;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author HP
 */
public class Konfiguracija extends javax.swing.JDialog {

    /**
     * Creates new form Konfiguracija
     */
    public Konfiguracija(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        unesiPodatke();
        this.setTitle("Konfiguracija");
        this.setLocationRelativeTo(null);
        setResizable(false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabelPort = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Port:");

        jLabelPort.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addGap(64, 64, 64)
                .addComponent(jLabelPort, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelPort, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(163, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelPort;
    // End of variables declaration//GEN-END:variables

    private void unesiPodatke() {

        String putanja = "config/config/configKlijent.properties";

        Properties p = new Properties();

        try (FileInputStream fis = new FileInputStream(putanja)) {
            p.load(fis);

            try {
                int port = Integer.parseInt(p.getProperty("port"));
                jLabelPort.setText(String.valueOf(port));
            } catch (NumberFormatException numberFormatException) {
                System.err.println("Broj porta nije dat u ispravnom formatu.");

            }
        } catch (IOException e) {
            System.err.println("Greška pri učitavanju konfiguracionog fajla. " + e);

        }

    }
    
}
