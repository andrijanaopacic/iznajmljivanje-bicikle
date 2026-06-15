package forme;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PretraziIznajmljivanje extends javax.swing.JDialog {

    private GlavnaForma gf;

    public PretraziIznajmljivanje(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.gf = (GlavnaForma) parent;
        this.setLocationRelativeTo(null);
        this.setTitle("Pretraži iznajmljivanje");
    }

    public GlavnaForma getGf() { return gf; }
    public void setGf(GlavnaForma gf) { this.gf = gf; }
    public JButton getjButtonPretrazi() { return jButtonPretrazi; }
    public JButton getjButtonPretraziSve() { return jButtonPretraziSve; }
    public JButton getjButtonPrikaziIznajmljivanje() { return jButtonPrikaziIznajmljivanje; }
    public JRadioButton getjRadioButtonBicikla() { return jRadioButtonBicikla; }
    public JRadioButton getjRadioButtonID() { return jRadioButtonID; }
    public JRadioButton getjRadioButtonKupac() { return jRadioButtonKupac; }
    public JRadioButton getjRadioButtonProdavac() { return jRadioButtonProdavac; }
    public JTable getjTableIznajmljivanja() { return jTableIznajmljivanja; }
    public JTextField getjTextFieldID() { return jTextFieldID; }
    public JTextField getjTextFieldKupac() { return jTextFieldKupac; }
    public JTextField getjTextFieldProdavac() { return jTextFieldProdavac; }
    public JComboBox<String> getjComboBoxBicikla() { return jComboBoxBicikla; }

    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonPretraziSve = new javax.swing.JButton();
        jRadioButtonID = new javax.swing.JRadioButton();
        jTextFieldID = new javax.swing.JTextField();
        jRadioButtonProdavac = new javax.swing.JRadioButton();
        jTextFieldProdavac = new javax.swing.JTextField();
        jRadioButtonKupac = new javax.swing.JRadioButton();
        jTextFieldKupac = new javax.swing.JTextField();
        jRadioButtonBicikla = new javax.swing.JRadioButton();
        jComboBoxBicikla = new javax.swing.JComboBox<>(
                new String[]{"Za odrasle", "Za decu", "Sa riksom"});
        jButtonPretrazi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableIznajmljivanja = new javax.swing.JTable();
        jButtonPrikaziIznajmljivanje = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Iznajmljivanje");
        jLabel2.setText("Prikaži sva iznajmljivanja:");
        jButtonPretraziSve.setText("Prikaži sve");
        jRadioButtonID.setText("Filtriraj iznajmljivanja prema ID-u:");
        jRadioButtonProdavac.setText("Filtriraj iznajmljivanja prema prodavcu:");
        jRadioButtonKupac.setText("Filtriraj iznajmljivanja prema kupcu:");
        jRadioButtonBicikla.setText("Filtriraj iznajmljivanja prema tipu bicikle:");
        jButtonPretrazi.setText("Pretraži");

        jTableIznajmljivanja.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Prodavac", "Kupac", "Ukupan iznos"}
        ));
        jScrollPane1.setViewportView(jTableIznajmljivanja);
        jButtonPrikaziIznajmljivanje.setText("PRIKAŽI DETALJE IZNAJMLJIVANJA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonPrikaziIznajmljivanje, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRadioButtonBicikla)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBoxBicikla))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonPretraziSve, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jRadioButtonID)
                                            .addComponent(jRadioButtonProdavac)
                                            .addComponent(jRadioButtonKupac))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jTextFieldID, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                                                    .addComponent(jTextFieldProdavac)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(jTextFieldKupac, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                                .addComponent(jButtonPretrazi, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(83, 83, 83))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonID)
                            .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jRadioButtonProdavac)
                                    .addComponent(jTextFieldProdavac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jButtonPretrazi)))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonKupac)
                            .addComponent(jTextFieldKupac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonBicikla)
                            .addComponent(jComboBoxBicikla))
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jButtonPrikaziIznajmljivanje, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonPretraziSve))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }

    private javax.swing.JButton jButtonPretrazi;
    private javax.swing.JButton jButtonPretraziSve;
    private javax.swing.JButton jButtonPrikaziIznajmljivanje;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButtonBicikla;
    private javax.swing.JRadioButton jRadioButtonID;
    private javax.swing.JRadioButton jRadioButtonKupac;
    private javax.swing.JRadioButton jRadioButtonProdavac;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableIznajmljivanja;
    private javax.swing.JComboBox<String> jComboBoxBicikla;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldKupac;
    private javax.swing.JTextField jTextFieldProdavac;

    public void VratiListuSvihIznajmljivanjaAddActionListeners(ActionListener actionListener) {
        jButtonPretraziSve.addActionListener(actionListener);
    }

    public void VratiListuIznajmljivanjaSaUslovomAddActionListeners(ActionListener actionListener) {
        jButtonPretrazi.addActionListener(actionListener);
    }

    public void PrikaziIznajmljivanjeAddActionListeners(ActionListener actionListener) {
        jButtonPrikaziIznajmljivanje.addActionListener(actionListener);
    }

    public void RadioIDAddActionListeners(ActionListener actionListener) {
        jRadioButtonID.addActionListener(actionListener);
    }

    public void RadioProdavacAddActionListeners(ActionListener actionListener) {
        jRadioButtonProdavac.addActionListener(actionListener);
    }

    public void RadioKupacAddActionListeners(ActionListener actionListener) {
        jRadioButtonKupac.addActionListener(actionListener);
    }

    public void RadioBiciklaAddActionListeners(ActionListener actionListener) {
        jRadioButtonBicikla.addActionListener(actionListener);
    }
}