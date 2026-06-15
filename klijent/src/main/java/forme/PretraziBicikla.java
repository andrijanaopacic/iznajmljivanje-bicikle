package forme;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class PretraziBicikla extends javax.swing.JDialog {

    private GlavnaForma gf;

    public PretraziBicikla(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.setTitle("Pretraži biciklu");
        this.setPreferredSize(new Dimension(650, 500));
        this.setMinimumSize(new Dimension(650, 500));
        this.setMaximumSize(new Dimension(650, 500));
        this.gf = (GlavnaForma) parent;
    }

    public GlavnaForma getGf() { return gf; }
    public void setGf(GlavnaForma gf) { this.gf = gf; }
    public JButton getjButtonPretraziSve() { return jButtonPretraziSve; }
    public JButton getjButtonPretraziTip() { return jButtonPretraziTip; }
    public JTable getjTableBicikle() { return jTableBicikle; }
    public JComboBox<String> getjComboBoxTip() { return jComboBoxTip; }

    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonPretraziSve = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxTip = new javax.swing.JComboBox<>(
                new String[]{"Za odrasle", "Za decu", "Sa riksom"});
        jButtonPretraziTip = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBicikle = new javax.swing.JTable();
        jButtonNadjiBiciklu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Bicikla");
        jLabel2.setText("Prikaži sve bicikle:");
        jButtonPretraziSve.setText("Prikaži sve");
        jLabel3.setText("Filtriraj prema tipu:");
        jButtonPretraziTip.setText("Pretraži");
        jLabel4.setText("Pronađene bicikle:");

        jTableBicikle.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{}, new String[]{"Tip", "Marka", "Model", "Boja", "Cena sata", "Cena dana", "Spec. atributi"}
        ));
        jScrollPane1.setViewportView(jTableBicikle);

        jButtonNadjiBiciklu.setText("Prikaži biciklu");
        jButtonNadjiBiciklu.setPreferredSize(new java.awt.Dimension(72, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonNadjiBiciklu, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4)
                        .addComponent(jLabel1)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addGap(36, 36, 36)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButtonPretraziSve, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                                .addComponent(jComboBoxTip))
                            .addGap(76, 76, 76)
                            .addComponent(jButtonPretraziTip, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButtonPretraziSve))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxTip)
                    .addComponent(jButtonPretraziTip))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButtonNadjiBiciklu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }

    private javax.swing.JButton jButtonNadjiBiciklu;
    private javax.swing.JButton jButtonPretraziSve;
    private javax.swing.JButton jButtonPretraziTip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableBicikle;
    private javax.swing.JComboBox<String> jComboBoxTip;

    public void VratiListuSviBicklaAddActionListeners(ActionListener actionListener) {
        jButtonPretraziSve.addActionListener(actionListener);
    }

    public void VratiListuBiciklaSaUslovomAddActionListeners(ActionListener actionListener) {
        jButtonPretraziTip.addActionListener(actionListener);
    }

    public void PrikaziBiciklaAddActionListeners(ActionListener actionListener) {
        jButtonNadjiBiciklu.addActionListener(actionListener);
    }
}