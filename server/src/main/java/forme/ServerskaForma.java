/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forme;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import konfiguracija.Konfiguracija;
import kontroler.ServerKontroler;
import model.ModelTabeleProdavci;
import model.Prodavac;
import niti.ObradaKlijentskihZahteva;
import niti.ServerskaNit;
import repozitorijum.db.DBKonekcija;

/**
 *
 * @author HP
 */
public class ServerskaForma extends javax.swing.JFrame {

    private ServerskaNit serverskaNit;
    public static KreirajKonfiguracijuForma konfForma;
    public static PregledKonfiguracijeForma pregledKonfForma;
    private List<Prodavac> sviKorisnici = new ArrayList<>();
    private List<Prodavac> nepovezaniKorisnici = new ArrayList<>();
    
    
    /**
     * Creates new form ServerskaForma
     */
    public ServerskaForma() {
        initComponents();
        this.setTitle("Server");
        jLabelStatus.setText("");
        jButtonZaustaviServer.setEnabled(false);
        jButtonPokreniServer.setEnabled(true);
        setLocationRelativeTo(null);
        jLabelStatus.setText("SERVER NIJE POKRENUT!");
//        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

//        this.addWindowListener(new java.awt.event.WindowAdapter() {
//            @Override
//            public void windowClosing(java.awt.event.WindowEvent e) {
//                ugasiServer();
//            }
//        });
        
        popuniTabeluPovezani();
        popuniTabeluNepovezani();

        konfForma = new KreirajKonfiguracijuForma(this, true);
        pregledKonfForma = new PregledKonfiguracijeForma(this,true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonPokreniServer = new javax.swing.JButton();
        jButtonZaustaviServer = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabelStatus = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePovezaniKorisnici = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableNepovezaniKorisnici = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemKreirajKonfiguraciju = new javax.swing.JMenuItem();
        jMenuItemPregledKonfiguracije = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonPokreniServer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonPokreniServer.setText("POKRENI SERVER");
        jButtonPokreniServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPokreniServerActionPerformed(evt);
            }
        });

        jButtonZaustaviServer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonZaustaviServer.setText("ZAUSTAVI SERVER");
        jButtonZaustaviServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonZaustaviServerActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("STATUS:");

        jLabelStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelStatus.setText("jLabel2");

        jLabel2.setText("POVEZANI KORISNICI:");

        jLabel3.setText("NEPOVEZANI KORISNICI:");

        jTablePovezaniKorisnici.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTablePovezaniKorisnici);

        jTableNepovezaniKorisnici.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTableNepovezaniKorisnici);

        jMenu1.setText("Konfiguracija");

        jMenuItemKreirajKonfiguraciju.setText("Kreiraj konfiguraciju");
        jMenuItemKreirajKonfiguraciju.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKreirajKonfiguracijuActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemKreirajKonfiguraciju);

        jMenuItemPregledKonfiguracije.setText("Pregled konfiguracije");
        jMenuItemPregledKonfiguracije.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPregledKonfiguracijeActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemPregledKonfiguracije);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonPokreniServer)
                            .addComponent(jLabel1))
                        .addGap(67, 67, 67))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabelStatus)
                    .addComponent(jButtonZaustaviServer)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelStatus))
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPokreniServer)
                    .addComponent(jButtonZaustaviServer))
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemKreirajKonfiguracijuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKreirajKonfiguracijuActionPerformed
        konfForma.setVisible(true);
    }//GEN-LAST:event_jMenuItemKreirajKonfiguracijuActionPerformed

    private void jMenuItemPregledKonfiguracijeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPregledKonfiguracijeActionPerformed
        pregledKonfForma.setVisible(true);
    }//GEN-LAST:event_jMenuItemPregledKonfiguracijeActionPerformed

    private void jButtonPokreniServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPokreniServerActionPerformed
        
        pregledKonfForma.ucitajPodatke();
       
        boolean ispravniPodaci = pregledKonfForma.proveriPodatke();
        if (!ispravniPodaci) {
            JOptionPane.showMessageDialog(this, "Server ne može biti pokrenut, jer podaci o konfiguraciji nisu ispravni. Unesite podatke o konfiguraciji i pokušajte ponovo.", "Greška", JOptionPane.ERROR_MESSAGE);
            konfForma.setVisible(true);
            return;
        }
        
        
        System.out.println("Pokušavam da napravim serversku nit...");
        serverskaNit = new ServerskaNit();
        serverskaNit.start();
        
        
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerskaForma.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!serverskaNit.isPokrenut()) {

            JOptionPane.showMessageDialog(this, "Server je već pokrenut.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;

        }
        
        ServerKontroler.getInstance().setSf(this);
        
        try {
            sviKorisnici = ServerKontroler.getInstance().vratiListuSviProdavci();
        }catch (Exception ex) {
            System.err.println("Sistem ne može da učita prodavce iz baze.");
        }
        if (sviKorisnici == null) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da učita prodavce iz baze. Proverite da li je uspostavljena veza sa MySQL serverom.", "Greška", JOptionPane.ERROR_MESSAGE);
            serverskaNit.zaustaviServer();
            DBKonekcija.resetuj();
            unesiPodatke();
            return;
        }
        
        jButtonZaustaviServer.setEnabled(true);
        jButtonPokreniServer.setEnabled(false);
        jLabelStatus.setText("SERVER JE POKRENUT!");
        System.out.println("SERVER JE POKRENUT!");
        
        nepovezaniKorisnici = new ArrayList<>();
        List<Prodavac> povezaniKorisnici = new ArrayList<>();
        for (ObradaKlijentskihZahteva korisnik : ServerskaNit.getPrijavljeniKorisnici()) {
            Prodavac p = new Prodavac(korisnik.getIme(), korisnik.getPrezime(),korisnik.getKorisnickoIme(), korisnik.getSifra());
            povezaniKorisnici.add(p);
        }
        for (Prodavac prodavac : sviKorisnici) {
            if (!povezaniKorisnici.contains(prodavac)) {
                nepovezaniKorisnici.add(prodavac);
            }
        }
        
        ModelTabeleProdavci mtp1 = (ModelTabeleProdavci) jTableNepovezaniKorisnici.getModel();
        mtp1.setProdavci(nepovezaniKorisnici);
        mtp1.refresujPodatke();
        ModelTabeleProdavci mtp2 = (ModelTabeleProdavci) jTablePovezaniKorisnici.getModel();
        mtp2.setProdavci(povezaniKorisnici);
        mtp2.refresujPodatke();
        
        unesiPodatke();
    }//GEN-LAST:event_jButtonPokreniServerActionPerformed

    private void jButtonZaustaviServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonZaustaviServerActionPerformed
        
        jButtonZaustaviServer.setEnabled(false);
        jButtonPokreniServer.setEnabled(true);
        System.out.println("Doslo je do linije za zaustavljanje servera.");
        serverskaNit.zaustaviServer();
        jLabelStatus.setText("SERVER JE ZAUSTAVLJEN!");
        System.out.println("SERVER JE ZAUSTAVLJEN.");
        
        nepovezaniKorisnici = new ArrayList<>();
        ModelTabeleProdavci mtp1 = (ModelTabeleProdavci) jTableNepovezaniKorisnici.getModel();
        mtp1.setProdavci(nepovezaniKorisnici);
        mtp1.refresujPodatke();
        ModelTabeleProdavci mtp2 = (ModelTabeleProdavci) jTablePovezaniKorisnici.getModel();
        List<Prodavac> povezaniKorisnici1 = new ArrayList<>();
        mtp2.setProdavci(povezaniKorisnici1);
        mtp2.refresujPodatke();
        
       
        
    }//GEN-LAST:event_jButtonZaustaviServerActionPerformed

    private void ugasiServer() {
        int potvrda = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "Da li ste sigurni da želite da zatvorite aplikaciju?",
                "Zatvori server",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (potvrda == javax.swing.JOptionPane.YES_OPTION) {
            serverskaNit.zaustaviServer();
            dispose(); 
            System.exit(0);
        }
    }
    
     private void popuniTabeluPovezani() {
        List<Prodavac> povezaniKorisnici1 = new ArrayList<>();

         ModelTabeleProdavci modelTabele = new ModelTabeleProdavci(povezaniKorisnici1);
        jTablePovezaniKorisnici.setModel(modelTabele);
    }

    private void popuniTabeluNepovezani() {
        ModelTabeleProdavci modelTabele = new ModelTabeleProdavci(nepovezaniKorisnici);
        jTableNepovezaniKorisnici.setModel(modelTabele);
    }

    


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPokreniServer;
    private javax.swing.JButton jButtonZaustaviServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemKreirajKonfiguraciju;
    private javax.swing.JMenuItem jMenuItemPregledKonfiguracije;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableNepovezaniKorisnici;
    private javax.swing.JTable jTablePovezaniKorisnici;
    // End of variables declaration//GEN-END:variables

    public void unesiPodatke() {
        if (Konfiguracija.getInstance().daLiPostojiKonfigFajl()) {
            pregledKonfForma.ucitajPodatke();

        } else {
            pregledKonfForma.getjLabelPort().setText("N/A");
            pregledKonfForma.getjLabelURL().setText("N/A");
            pregledKonfForma.getjLabelUsername().setText("N/A");
            pregledKonfForma.getjLabelPassword().setText("N/A");

            konfForma.setVisible(true);
        }
    }
    
    public void osveziTabele() {
        try {
            sviKorisnici = ServerKontroler.getInstance().vratiListuSviProdavci();
        } catch (Exception ex) {
            System.err.println("Sistem ne može da učita prodavce iz baze.");
        }
        nepovezaniKorisnici = new ArrayList<>();
        List<Prodavac> povezaniKorisnici1 = new ArrayList<>();
        for (ObradaKlijentskihZahteva korisnik : ServerskaNit.getPrijavljeniKorisnici()) {
            Prodavac p = new Prodavac(korisnik.getIme(), korisnik.getPrezime(),korisnik.getKorisnickoIme(), korisnik.getSifra());
            povezaniKorisnici1.add(p);
        }
        for (Prodavac prodavac : sviKorisnici) {
            if (!povezaniKorisnici1.contains(prodavac)) {
                nepovezaniKorisnici.add(prodavac);
            }
        }
        ModelTabeleProdavci mtp1 = (ModelTabeleProdavci) jTableNepovezaniKorisnici.getModel();
        mtp1.setProdavci(nepovezaniKorisnici);
        mtp1.refresujPodatke();
        ModelTabeleProdavci mtp2 = (ModelTabeleProdavci) jTablePovezaniKorisnici.getModel();
        mtp2.setProdavci(povezaniKorisnici1);
        mtp2.refresujPodatke();

    }

    public void azuriraj() {
        pregledKonfForma.setIspravniPodaci(true);
        unesiPodatke();
    }
}
