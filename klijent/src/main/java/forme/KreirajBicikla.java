package forme;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.Bicikla;
import model.BiciklaZaOdrasle;
import model.BiciklaZaDecu;
import model.BiciklaSaRiksom;

public class KreirajBicikla extends JDialog {

    private GlavnaForma gf;
    private Bicikla bicikla;

    // Zajednicki elementi
    private JComboBox<String> jComboBoxTip;
    private JTextField jTextFieldMarka;
    private JTextField jTextFieldModel;
    private JTextField jTextFieldBoja;
    private JTextField jTextFieldCenaPoSatu;
    private JTextField jTextFieldCenaPoDanu;

    // Panel za odrasle
    private JPanel panelOdrasli;
    private JTextField jTextFieldVelicinaTockovaOdrasli;
    private JTextField jTextFieldBrojBrzina;

    // Panel za decu
    private JPanel panelDeca;
    private JTextField jTextFieldVelicinaTockovaDeca;
    private JTextField jTextFieldBrojBrzinaDeca;
    private JCheckBox jCheckBoxPomocniTockovi;

    // Panel za riksu
    private JPanel panelRiksa;
    private JTextField jTextFieldBrojSedista;
    private JTextField jTextFieldTipRikse;
    private JTextField jTextFieldMaxNosivost;

    // Dugmad
    private JButton jButtonZapamti;
    private JButton jButtonSacuvaj;
    private JButton jButtonObrisi;
    private JButton jButtonObrisiB;
    private JButton jButtonPromeni;
    private JButton jButtonPrikaziObrisi;
    private JButton jButtonPrikaziPromeni;
    private JButton jButtonOdustani;

    public KreirajBicikla(java.awt.Frame parent, boolean modal, Bicikla bicikla) {
        super(parent, modal);
        this.gf = (GlavnaForma) parent;
        this.bicikla = bicikla;
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.setTitle("Kreiraj biciklu");
    }

    private void initComponents() {
        setLayout(null);

        // Naslov
        JLabel jLabel1 = new JLabel("Bicikla");
        jLabel1.setBounds(25, 20, 100, 25);
        add(jLabel1);

        // Tip
        JLabel jLabelTip = new JLabel("Tip bicikle:");
        jLabelTip.setBounds(25, 55, 150, 25);
        add(jLabelTip);

        jComboBoxTip = new JComboBox<>(new String[]{"Za odrasle", "Za decu", "Sa riksom"});
        jComboBoxTip.setBounds(197, 55, 211, 25);
        add(jComboBoxTip);

        // Marka
        JLabel jLabelMarka = new JLabel("Marka:");
        jLabelMarka.setBounds(25, 90, 150, 25);
        add(jLabelMarka);

        jTextFieldMarka = new JTextField();
        jTextFieldMarka.setBounds(197, 90, 211, 25);
        add(jTextFieldMarka);

        // Model
        JLabel jLabelModel = new JLabel("Model:");
        jLabelModel.setBounds(25, 125, 150, 25);
        add(jLabelModel);

        jTextFieldModel = new JTextField();
        jTextFieldModel.setBounds(197, 125, 211, 25);
        add(jTextFieldModel);

        // Boja
        JLabel jLabelBoja = new JLabel("Boja:");
        jLabelBoja.setBounds(25, 160, 150, 25);
        add(jLabelBoja);

        jTextFieldBoja = new JTextField();
        jTextFieldBoja.setBounds(197, 160, 211, 25);
        add(jTextFieldBoja);

        // Cena po satu
        JLabel jLabelCenaPoSatu = new JLabel("Cena po satu:");
        jLabelCenaPoSatu.setBounds(25, 195, 150, 25);
        add(jLabelCenaPoSatu);

        jTextFieldCenaPoSatu = new JTextField();
        jTextFieldCenaPoSatu.setBounds(197, 195, 211, 25);
        add(jTextFieldCenaPoSatu);

        // Cena po danu
        JLabel jLabelCenaPoDanu = new JLabel("Cena po danu:");
        jLabelCenaPoDanu.setBounds(25, 230, 150, 25);
        add(jLabelCenaPoDanu);

        jTextFieldCenaPoDanu = new JTextField();
        jTextFieldCenaPoDanu.setBounds(197, 230, 211, 25);
        add(jTextFieldCenaPoDanu);

        // Panel za odrasle
        panelOdrasli = new JPanel(null);
        panelOdrasli.setBounds(25, 270, 500, 80);
        panelOdrasli.setBorder(BorderFactory.createTitledBorder("Za odrasle"));

        JLabel lVelicinaTockovaO = new JLabel("Veličina točkova:");
        lVelicinaTockovaO.setBounds(10, 20, 150, 25);
        panelOdrasli.add(lVelicinaTockovaO);

        jTextFieldVelicinaTockovaOdrasli = new JTextField();
        jTextFieldVelicinaTockovaOdrasli.setBounds(172, 20, 150, 25);
        panelOdrasli.add(jTextFieldVelicinaTockovaOdrasli);

        JLabel lBrojBrzina = new JLabel("Broj brzina:");
        lBrojBrzina.setBounds(10, 50, 150, 25);
        panelOdrasli.add(lBrojBrzina);

        jTextFieldBrojBrzina = new JTextField();
        jTextFieldBrojBrzina.setBounds(172, 50, 150, 25);
        panelOdrasli.add(jTextFieldBrojBrzina);

        add(panelOdrasli);

        // Panel za decu
        panelDeca = new JPanel(null);
        panelDeca.setBounds(25, 270, 500, 110);
        panelDeca.setBorder(BorderFactory.createTitledBorder("Za decu"));

        JLabel lVelicinaTockovaD = new JLabel("Veličina točkova:");
        lVelicinaTockovaD.setBounds(10, 20, 150, 25);
        panelDeca.add(lVelicinaTockovaD);

        jTextFieldVelicinaTockovaDeca = new JTextField();
        jTextFieldVelicinaTockovaDeca.setBounds(172, 20, 150, 25);
        panelDeca.add(jTextFieldVelicinaTockovaDeca);

        JLabel lBrojBrzinaDeca = new JLabel("Broj brzina:");
        lBrojBrzinaDeca.setBounds(10, 50, 150, 25);
        panelDeca.add(lBrojBrzinaDeca);

        jTextFieldBrojBrzinaDeca = new JTextField();
        jTextFieldBrojBrzinaDeca.setBounds(172, 50, 150, 25);
        panelDeca.add(jTextFieldBrojBrzinaDeca);

        JLabel lPomocniTockovi = new JLabel("Pomoćni točkovi:");
        lPomocniTockovi.setBounds(10, 80, 150, 25);
        panelDeca.add(lPomocniTockovi);

        jCheckBoxPomocniTockovi = new JCheckBox();
        jCheckBoxPomocniTockovi.setBounds(172, 80, 25, 25);
        panelDeca.add(jCheckBoxPomocniTockovi);

        add(panelDeca);

        // Panel za riksu
        panelRiksa = new JPanel(null);
        panelRiksa.setBounds(25, 270, 500, 110);
        panelRiksa.setBorder(BorderFactory.createTitledBorder("Sa riksom"));

        JLabel lBrojSedista = new JLabel("Broj sedišta:");
        lBrojSedista.setBounds(10, 20, 150, 25);
        panelRiksa.add(lBrojSedista);

        jTextFieldBrojSedista = new JTextField();
        jTextFieldBrojSedista.setBounds(172, 20, 150, 25);
        panelRiksa.add(jTextFieldBrojSedista);

        JLabel lTipRikse = new JLabel("Tip rikse:");
        lTipRikse.setBounds(10, 50, 150, 25);
        panelRiksa.add(lTipRikse);

        jTextFieldTipRikse = new JTextField();
        jTextFieldTipRikse.setBounds(172, 50, 150, 25);
        panelRiksa.add(jTextFieldTipRikse);

        JLabel lMaxNosivost = new JLabel("Max nosivost:");
        lMaxNosivost.setBounds(10, 80, 150, 25);
        panelRiksa.add(lMaxNosivost);

        jTextFieldMaxNosivost = new JTextField();
        jTextFieldMaxNosivost.setBounds(172, 80, 150, 25);
        panelRiksa.add(jTextFieldMaxNosivost);

        add(panelRiksa);

        // Inicijalno sakrij decu i riksu
        panelDeca.setVisible(false);
        panelRiksa.setVisible(false);

        // Listener za ComboBox
        jComboBoxTip.addActionListener(e -> {
            String izabrani = (String) jComboBoxTip.getSelectedItem();
            panelOdrasli.setVisible(false);
            panelDeca.setVisible(false);
            panelRiksa.setVisible(false);
            switch (izabrani) {
                case "Za odrasle" -> panelOdrasli.setVisible(true);
                case "Za decu" -> panelDeca.setVisible(true);
                case "Sa riksom" -> panelRiksa.setVisible(true);
            }
        });

        // Dugmad
        jButtonZapamti = new JButton("Zapamti");
        jButtonZapamti.setBounds(25, 400, 150, 30);
        add(jButtonZapamti);

        jButtonSacuvaj = new JButton("Sačuvaj");
        jButtonSacuvaj.setBounds(25, 400, 150, 30);
        add(jButtonSacuvaj);

        jButtonObrisi = new JButton("Obriši");
        jButtonObrisi.setBounds(200, 400, 150, 30);
        add(jButtonObrisi);

        jButtonObrisiB = new JButton("Obriši biciklu");
        jButtonObrisiB.setBounds(25, 440, 150, 30);
        add(jButtonObrisiB);

        jButtonPromeni = new JButton("Promeni biciklu");
        jButtonPromeni.setBounds(25, 440, 150, 30);
        add(jButtonPromeni);

        jButtonPrikaziObrisi = new JButton("Prikaži biciklu");
        jButtonPrikaziObrisi.setBounds(25, 400, 150, 30);
        add(jButtonPrikaziObrisi);

        jButtonPrikaziPromeni = new JButton("Prikaži biciklu");
        jButtonPrikaziPromeni.setBounds(25, 440, 150, 30);
        add(jButtonPrikaziPromeni);

        jButtonOdustani = new JButton("Odustani");
        jButtonOdustani.setBounds(375, 400, 150, 30);
        add(jButtonOdustani);

        setSize(560, 510);
    }

    // Getteri
    public JComboBox<String> getjComboBoxTip() { return jComboBoxTip; }
    public JTextField getjTextFieldMarka() { return jTextFieldMarka; }
    public JTextField getjTextFieldModel() { return jTextFieldModel; }
    public JTextField getjTextFieldBoja() { return jTextFieldBoja; }
    public JTextField getjTextFieldCenaPoSatu() { return jTextFieldCenaPoSatu; }
    public JTextField getjTextFieldCenaPoDanu() { return jTextFieldCenaPoDanu; }
    public JTextField getjTextFieldVelicinaTockovaOdrasli() { return jTextFieldVelicinaTockovaOdrasli; }
    public JTextField getjTextFieldBrojBrzina() { return jTextFieldBrojBrzina; }
    public JTextField getjTextFieldVelicinaTockovaDeca() { return jTextFieldVelicinaTockovaDeca; }
    public JTextField getjTextFieldBrojBrzinaDeca() { return jTextFieldBrojBrzinaDeca; }
    public JCheckBox getjCheckBoxPomocniTockovi() { return jCheckBoxPomocniTockovi; }
    public JTextField getjTextFieldBrojSedista() { return jTextFieldBrojSedista; }
    public JTextField getjTextFieldTipRikse() { return jTextFieldTipRikse; }
    public JTextField getjTextFieldMaxNosivost() { return jTextFieldMaxNosivost; }
    public JButton getjButtonZapamti() { return jButtonZapamti; }
    public JButton getjButtonSacuvaj() { return jButtonSacuvaj; }
    public JButton getjButtonObrisi() { return jButtonObrisi; }
    public JButton getjButtonObrisiBiciklu() { return jButtonObrisiB; }
    public JButton getjButtonPromeni() { return jButtonPromeni; }
    public JButton getjButtonPrikaziObrisi() { return jButtonPrikaziObrisi; }
    public JButton getjButtonPrikaziPromeni() { return jButtonPrikaziPromeni; }
    public JButton getjButtonOdustani() { return jButtonOdustani; }
    public GlavnaForma getGf() { return gf; }
    public Bicikla getBicikla() { return bicikla; }
    public void setBicikla(Bicikla bicikla) { this.bicikla = bicikla; }

    // Action listeners
    public void ZapamtiAddActionListeners(ActionListener al) { jButtonZapamti.addActionListener(al); }
    public void PromeniAddActionListeners(ActionListener al) { jButtonSacuvaj.addActionListener(al); }
    public void ObrisiAddActionListeners(ActionListener al) { jButtonObrisi.addActionListener(al); }
    public void OdustaniAddActionListeners(ActionListener al) { jButtonOdustani.addActionListener(al); }
    public void ObrisiBiciklaAddActionListeners(ActionListener al) { jButtonObrisiB.addActionListener(al); }
    public void PrikaziObrisiAddActionListeners(ActionListener al) { jButtonPrikaziObrisi.addActionListener(al); }
    public void PromeniBiciklaAddActionListeners(ActionListener al) { jButtonPromeni.addActionListener(al); }
    public void PrikaziPromeniAddActionListeners(ActionListener al) { jButtonPrikaziPromeni.addActionListener(al); }

    public Bicikla kreirajBicikluIzForme() {
        String tip = (String) jComboBoxTip.getSelectedItem();
        double cenaPoSatu = Double.parseDouble(jTextFieldCenaPoSatu.getText());
        double cenaPoDanu = Double.parseDouble(jTextFieldCenaPoDanu.getText());
        String marka = jTextFieldMarka.getText();
        String model = jTextFieldModel.getText();
        String boja = jTextFieldBoja.getText();

        return switch (tip) {
            case "Za odrasle" -> new BiciklaZaOdrasle(
                    cenaPoSatu, cenaPoDanu, marka, model, boja,
                    Integer.parseInt(jTextFieldVelicinaTockovaOdrasli.getText()),
                    Integer.parseInt(jTextFieldBrojBrzina.getText()));
            case "Za decu" -> new BiciklaZaDecu(
                    cenaPoSatu, cenaPoDanu, marka, model, boja,
                    Integer.parseInt(jTextFieldVelicinaTockovaDeca.getText()),
                    Integer.parseInt(jTextFieldBrojBrzinaDeca.getText()),
                    jCheckBoxPomocniTockovi.isSelected());
            case "Sa riksom" -> new BiciklaSaRiksom(
                    cenaPoSatu, cenaPoDanu, marka, model, boja,
                    Integer.parseInt(jTextFieldBrojSedista.getText()),
                    jTextFieldTipRikse.getText(),
                    Integer.parseInt(jTextFieldMaxNosivost.getText()));
            default -> null;
        };
    }
}