/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.GlavnaForma;
import forme.KreirajProdavac;
import forme.KreirajTermin;
import forme.PretraziProdavac;
import forme.PretraziTermin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import model.Prodavac;
import model.ProdavacTermin;
import model.Termin;
import modeli.ModelTabeleProdavacTermin;
import modeli.ModelTabeleProdavci;

/**
 *
 * @author HP
 */
public class ProdavacKontroler {
    
    private final KreirajProdavac kp;
    private final PretraziProdavac pp;
    private TerminKontroler tk;
    private List<ProdavacTermin> termini = new ArrayList<>();

    public ProdavacKontroler(KreirajProdavac kp, PretraziProdavac pp) {
        this.kp = kp;
        this.pp = pp;
        tk = new TerminKontroler(new KreirajTermin(new GlavnaForma(),true,null),new PretraziTermin(new GlavnaForma(),true));
        addActionListeners();
    }
    
    public void prikaziFormuKreirajProdavca(Prodavac prodavac) {
        if (prodavac == null) {
            popuniComboBoxTermini();

            kp.getjButtonPrikaziSifru().setVisible(true);
            kp.getjComboBoxNazivTermina().setSelectedItem(null);
            kp.getjButtonSacuvaj().setVisible(false);
            kp.getjButtonObrisi().setVisible(false);
            kp.getjButtonObrisiProdavca().setVisible(false);
            kp.getjButtonPromeni().setVisible(false);
            kp.getjButtonPrikaziPromeni().setVisible(false);
            kp.getjButtonPrikaziObrisi().setVisible(false);
            kp.getjButtonZapamti().setVisible(true);
            kp.getjButtonOdustani().setVisible(true);
            kp.getjButtonDodaj().setVisible(true);
            kp.getjButtonObrisiTermin().setVisible(true);
            popuniTabeluTerminima(new ArrayList<>());
//            JOptionPane.showMessageDialog(kp, "Sistem je kreirao prodavca.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            kp.setVisible(true);

        } else {
            popuniComboBoxTermini();

            kp.getjButtonPrikaziSifru().setVisible(true);
            kp.getjComboBoxNazivTermina().setSelectedItem(null);
            kp.getjTextFieldIme().setText(prodavac.getIme());
            kp.getjTextFieldPrezime().setText(prodavac.getPrezime());
            kp.getjTextFieldKorisnickoIme().setText(prodavac.getKorisnickoIme());
            kp.getjPasswordFieldSifra().setText(prodavac.getSifra());
            kp.getjTextFieldDatumDezurstva().setEditable(false);
            kp.getjTextFieldIme().setEditable(false);
            kp.getjTextFieldKorisnickoIme().setEditable(false);
            kp.getjComboBoxNazivTermina().setEnabled(false);
            kp.getjTextFieldPrezime().setEditable(false);
            kp.getjPasswordFieldSifra().setEditable(false);
            kp.getjButtonObrisiTermin().setEnabled(false);
            kp.getjButtonDodajNovTermin().setEnabled(false);
            kp.getjButtonSacuvaj().setVisible(false);
            kp.getjButtonObrisi().setVisible(false);
            kp.getjButtonObrisiProdavca().setVisible(true);
            kp.getjButtonPromeni().setVisible(true);
            kp.getjButtonPrikaziPromeni().setVisible(false);
            kp.getjButtonPrikaziObrisi().setVisible(false);
            kp.getjButtonZapamti().setVisible(false);
            kp.getjButtonOdustani().setVisible(false);
            kp.getjButtonDodaj().setVisible(false);
            kp.getjButtonObrisiTermin().setVisible(false);

            termini = prodavac.getProdavacTermini();
            popuniTabeluTerminima(termini);
            JOptionPane.showMessageDialog(kp, "Sistem je našao prodavca.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            kp.setVisible(true);

        }

    }
    
    public void otvoriFormuPretraziProdavac() {
        pp.getjRadioButtonFiltrirajImePrezime().setSelected(true);
        pp.getjTextFieldImePrez().setEditable(true);
        popuniComboBoxTermini();
        pp.getjComboBoxTermini().setSelectedItem(null);
        pp.getjComboBoxTermini().setEnabled(false);

        ButtonGroup group = new ButtonGroup();
        group.add(pp.getjRadioButtonFiltrirajImePrezime());
        group.add(pp.getjRadioButtonFiltrirajPrematerminu());
        popuniTabeluTerminima(termini);
        pp.setVisible(true);
    }
    
    private void addActionListeners() {
        kp.ZapamtiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapamti();
            }

            private void zapamti() {

                String ime = null;
                String prezime = null;
                String korisnickoIme = null;
                String sifra = null;
                List<ProdavacTermin> lista = new ArrayList<>();

                try {
                    ime = kp.getjTextFieldIme().getText().trim();
                    prezime = kp.getjTextFieldPrezime().getText().trim();
                    korisnickoIme = kp.getjTextFieldKorisnickoIme().getText().trim();
                    sifra = String.valueOf(kp.getjPasswordFieldSifra().getPassword()).trim();
                    ModelTabeleProdavacTermin mt = (ModelTabeleProdavacTermin) kp.getjTableTermini().getModel();
                    lista = mt.getTermini();

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(kp, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;

                }

                if (ime.trim().isEmpty() || prezime.trim().isEmpty() || korisnickoIme.trim().isEmpty() || sifra.trim().isEmpty() || lista == null || lista.isEmpty()) {

                    JOptionPane.showMessageDialog(kp, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (ime.trim().length() > 30 || prezime.trim().length() > 30 || sifra.trim().length() > 10 || korisnickoIme.trim().length() > 30) {

                    if (ime.trim().length() > 30) {
                        JOptionPane.showMessageDialog(kp, "Ime može sadržati maksimalno 30 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (prezime.trim().length() > 30) {
                        JOptionPane.showMessageDialog(kp, "Prezime može sadržati maksimalno 30 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (korisnickoIme.trim().length() > 20) {
                        JOptionPane.showMessageDialog(kp, "Korisničko ime može sadržati maksimalno 30 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (sifra.trim().length() > 20) {
                        JOptionPane.showMessageDialog(kp, "Šifra može sadržati maksimalno 10 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }

                    return;
                }

                boolean ispravnoIme = true;
                for (int i = 0; i < ime.length(); i++) {
                    if (!Character.isLetter(ime.charAt(i))) {
                        ispravnoIme = false;
                    }

                }

                boolean ispravnoPrezime = true;
                for (int i = 0; i < prezime.length(); i++) {
                    if (!Character.isLetter(prezime.charAt(i))) {
                        ispravnoPrezime = false;
                    }

                }

                if (!ispravnoIme || !ispravnoPrezime) {

                    if (!ispravnoIme) {
                        JOptionPane.showMessageDialog(kp, "Ime može sadržati samo slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    }

                    if (!ispravnoPrezime) {
                        JOptionPane.showMessageDialog(kp, "Prezime može sadržati samo slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    return;
                }

                Prodavac prodavac = new Prodavac(ime, prezime, korisnickoIme, sifra);
                for (ProdavacTermin prodavacTermin : lista) {
                    prodavacTermin.setProdavac(prodavac);
                }
                prodavac.setProdavacTermini(lista);

                boolean uspesno = Komunikacija.getInstance().kreirajProdavac(prodavac);

                if (!uspesno) {
                    JOptionPane.showMessageDialog(kp, "Sistem ne može da zapamti prodavca.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(kp, "Sistem je zapamtio prodavca.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    kp.dispose();
                }
            }

        });
        
        kp.prikaziSifruAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziSifru();
            }

            private void prikaziSifru() {
                if (kp.getjPasswordFieldSifra().getEchoChar() == (char)0) { 
                    kp.getjPasswordFieldSifra().setEchoChar('*'); 
                } else {
                    kp.getjPasswordFieldSifra().setEchoChar((char)0); 
                }
            }

        });
        
        kp.PromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni();
            }

            private void promeni() {
                String ime = null;
                String prezime = null;
                String korisnickoIme = null;
                String sifra = null;

                List<ProdavacTermin> lista = new ArrayList<>();

                try {

                    ime = kp.getjTextFieldIme().getText().trim();
                    prezime = kp.getjTextFieldPrezime().getText().trim();
                    korisnickoIme = kp.getjTextFieldKorisnickoIme().getText().trim();
                    sifra = String.valueOf(kp.getjPasswordFieldSifra().getPassword()).trim();
                    ModelTabeleProdavacTermin mt = (ModelTabeleProdavacTermin) kp.getjTableTermini().getModel();
                    lista = mt.getTermini();

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(kp, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;

                }

                if (ime.trim().isEmpty() || prezime.trim().isEmpty() || korisnickoIme.trim().isEmpty() || sifra.trim().isEmpty() || lista == null || lista.isEmpty()) {

                    JOptionPane.showMessageDialog(kp, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
//                if (lista == null || lista.isEmpty()) {
//                    int potvrda = JOptionPane.showConfirmDialog(kp, "Prodavac nema nijedan termin. Da li želite da nastavite?", "Upozorenje", JOptionPane.YES_NO_OPTION);
//                    if (potvrda != JOptionPane.YES_OPTION) return;
//                }

                if (ime.trim().length() > 30 || prezime.trim().length() > 30 || sifra.trim().length() > 10 || korisnickoIme.trim().length() > 30) {

                    if (ime.trim().length() > 30) {
                        JOptionPane.showMessageDialog(kp, "Ime može sadržati maksimalno 30 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (prezime.trim().length() > 30) {
                        JOptionPane.showMessageDialog(kp, "Prezime može sadržati maksimalno 30 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (korisnickoIme.trim().length() > 20) {
                        JOptionPane.showMessageDialog(kp, "Korisničko ime može sadržati maksimalno 30 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (sifra.trim().length() > 20) {
                        JOptionPane.showMessageDialog(kp, "Šifra može sadržati maksimalno 10 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }

                    return;
                }

                boolean ispravnoIme = true;
                for (int i = 0; i < ime.length(); i++) {
                    if (!Character.isLetter(ime.charAt(i))) {
                        ispravnoIme = false;
                    }

                }

                boolean ispravnoPrezime = true;
                for (int i = 0; i < prezime.length(); i++) {
                    if (!Character.isLetter(prezime.charAt(i))) {
                        ispravnoPrezime = false;
                    }

                }

                if (!ispravnoIme || !ispravnoPrezime) {

                    if (!ispravnoIme) {
                        JOptionPane.showMessageDialog(kp, "Ime može sadržati samo slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    }

                    if (!ispravnoPrezime) {
                        JOptionPane.showMessageDialog(kp, "Prezime može sadržati samo slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                }
                
                Prodavac prodavac = new Prodavac(kp.getProdavac().getIdProdavac(),ime, prezime, korisnickoIme, sifra);
                for (ProdavacTermin prodavacTermin : lista) {
                    prodavacTermin.setProdavac(prodavac);
                }
                prodavac.setProdavacTermini(termini);


                int odgovor = JOptionPane.showConfirmDialog(kp, "Da li ste sigurni da želite da sačuvate unete promene?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {

                    boolean uspesno = Komunikacija.getInstance().promeniProdavac(prodavac);

                    if (!uspesno) {
                        JOptionPane.showMessageDialog(kp, "Sistem ne može da zapamti prodavca.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(kp, "Sistem je zapamtio prodavca.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        kp.dispose();
                    }
                }
            }

        });
        
        kp.ObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi();

            }

            private void obrisi() {

                int odgovor = JOptionPane.showConfirmDialog(kp, "Da li ste sigurni da želite da obrišete prodavca?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {
                    String ime = null;
                    String prezime = null;
                    String korisnickoIme = null;
                    String sifra = null;

                    ime = kp.getjTextFieldIme().getText().trim();
                    prezime = kp.getjTextFieldPrezime().getText().trim();
                    korisnickoIme = kp.getjTextFieldKorisnickoIme().getText().trim();
                    sifra = String.valueOf(kp.getjPasswordFieldSifra().getPassword()).trim();
                    ModelTabeleProdavacTermin mt = (ModelTabeleProdavacTermin) kp.getjTableTermini().getModel();
                    List<ProdavacTermin> lista = mt.getTermini();

                    Prodavac p = new Prodavac(kp.getProdavac().getIdProdavac(),ime, prezime, korisnickoIme, sifra);

                    for (ProdavacTermin prodavacTermin : lista) {
                        prodavacTermin.setProdavac(p);
                    }
                    p.setProdavacTermini(termini);

                    boolean uspesno = Komunikacija.getInstance().obrisiProdavac(p);

                    if (!uspesno) {
                        JOptionPane.showMessageDialog(kp, "Sistem ne može da obriše prodavca.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(kp, "Sistem je obrisao prodavca.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                        kp.dispose();
                    }
                }
            }

        });
        
        kp.ObrisiProdavcaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiProdavca();

            }

            private void obrisiProdavca() {
                kp.getjTextFieldIme().setText(kp.getProdavac().getIme());
                kp.getjTextFieldPrezime().setText(kp.getProdavac().getPrezime());
                kp.getjTextFieldKorisnickoIme().setText(kp.getProdavac().getKorisnickoIme());
                kp.getjPasswordFieldSifra().setText(kp.getProdavac().getSifra());
                kp.getjTextFieldDatumDezurstva().setEditable(false);
                kp.getjTextFieldIme().setEditable(false);
                kp.getjTextFieldKorisnickoIme().setEditable(false);
                kp.getjComboBoxNazivTermina().setEnabled(false);
                kp.getjTextFieldPrezime().setEditable(false);
                kp.getjPasswordFieldSifra().setEditable(false);
                kp.getjButtonObrisiTermin().setEnabled(false);
                kp.getjButtonDodajNovTermin().setEnabled(false);
                kp.getjButtonSacuvaj().setVisible(false);
                kp.getjButtonObrisi().setVisible(true);
                kp.getjButtonObrisiProdavca().setVisible(false);
                kp.getjButtonPromeni().setVisible(true);
                kp.getjButtonPrikaziPromeni().setVisible(false);
                kp.getjButtonPrikaziObrisi().setVisible(true);
                kp.getjButtonZapamti().setVisible(false);
                kp.getjButtonOdustani().setVisible(true);
                kp.getjButtonDodaj().setVisible(false);
                kp.getjButtonObrisiTermin().setVisible(false);

                List<ProdavacTermin> lista = kp.getProdavac().getProdavacTermini();
                popuniTabeluTerminima(lista);
            }


        });
        
        kp.PrikaziObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziObrisi();

            }

            private void prikaziObrisi() {
                kp.getjTextFieldIme().setText(kp.getProdavac().getIme());
                kp.getjTextFieldPrezime().setText(kp.getProdavac().getPrezime());
                kp.getjTextFieldKorisnickoIme().setText(kp.getProdavac().getKorisnickoIme());
                kp.getjPasswordFieldSifra().setText(kp.getProdavac().getSifra());
                kp.getjTextFieldDatumDezurstva().setText("");
                kp.getjTextFieldDatumDezurstva().setEditable(false);
                kp.getjTextFieldIme().setEditable(false);
                kp.getjTextFieldKorisnickoIme().setEditable(false);
                kp.getjComboBoxNazivTermina().setEnabled(false);
                kp.getjTextFieldPrezime().setEditable(false);
                kp.getjPasswordFieldSifra().setEditable(false);
                kp.getjButtonObrisiTermin().setEnabled(false);
                kp.getjButtonDodajNovTermin().setEnabled(false);
                kp.getjButtonSacuvaj().setVisible(false);
                kp.getjButtonObrisi().setVisible(false);
                kp.getjButtonObrisiProdavca().setVisible(true);
                kp.getjButtonPromeni().setVisible(true);
                kp.getjButtonPrikaziPromeni().setVisible(false);
                kp.getjButtonPrikaziObrisi().setVisible(false);
                kp.getjButtonZapamti().setVisible(false);
                kp.getjButtonOdustani().setVisible(false);
                kp.getjButtonDodaj().setVisible(false);
                kp.getjButtonObrisiTermin().setVisible(false);

                List<ProdavacTermin> lista = kp.getProdavac().getProdavacTermini();
                popuniTabeluTerminima(lista);
            }

        });
        
        kp.PrikaziPromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziPromeni();

            }

            private void prikaziPromeni() {
                kp.getjTextFieldIme().setText(kp.getProdavac().getIme());
                kp.getjTextFieldPrezime().setText(kp.getProdavac().getPrezime());
                kp.getjTextFieldKorisnickoIme().setText(kp.getProdavac().getKorisnickoIme());
                kp.getjPasswordFieldSifra().setText(kp.getProdavac().getSifra());
                kp.getjTextFieldDatumDezurstva().setText("");
                kp.getjTextFieldDatumDezurstva().setEditable(false);
                kp.getjTextFieldIme().setEditable(false);
                kp.getjTextFieldKorisnickoIme().setEditable(false);
                kp.getjComboBoxNazivTermina().setEnabled(false);
                kp.getjTextFieldPrezime().setEditable(false);
                kp.getjPasswordFieldSifra().setEditable(false);
                kp.getjButtonObrisiTermin().setEnabled(false);
                kp.getjButtonDodajNovTermin().setEnabled(false);
                kp.getjButtonSacuvaj().setVisible(false);
                kp.getjButtonObrisi().setVisible(false);
                kp.getjButtonObrisiProdavca().setVisible(true);
                kp.getjButtonPromeni().setVisible(true);
                kp.getjButtonPrikaziPromeni().setVisible(false);
                kp.getjButtonPrikaziObrisi().setVisible(false);
                kp.getjButtonZapamti().setVisible(false);
                kp.getjButtonOdustani().setVisible(false);
                kp.getjButtonDodaj().setVisible(false);
                kp.getjButtonObrisiTermin().setVisible(false);

                List<ProdavacTermin> lista = kp.getProdavac().getProdavacTermini();
                popuniTabeluTerminima(lista);
            }

        });
        
        kp.PromeniProdavcaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeniProdavca();

            }

            private void promeniProdavca() {
                kp.getjTextFieldDatumDezurstva().setEditable(true);
                kp.getjTextFieldIme().setEditable(true);
                kp.getjTextFieldKorisnickoIme().setEditable(true);
                kp.getjComboBoxNazivTermina().setEnabled(true);
                kp.getjTextFieldPrezime().setEditable(true);
                kp.getjPasswordFieldSifra().setEditable(true);
                kp.getjButtonObrisiTermin().setEnabled(true);
                kp.getjButtonDodajNovTermin().setEnabled(true);
                kp.getjButtonSacuvaj().setVisible(true);
                kp.getjButtonObrisi().setVisible(false);
                kp.getjButtonObrisiProdavca().setVisible(true);
                kp.getjButtonPromeni().setVisible(false);
                kp.getjButtonPrikaziPromeni().setVisible(true);
                kp.getjButtonPrikaziObrisi().setVisible(false);
                kp.getjButtonZapamti().setVisible(false);
                kp.getjButtonOdustani().setVisible(true);
                kp.getjButtonDodaj().setVisible(true);
                kp.getjButtonObrisiTermin().setVisible(true);
            }
        });
        
        kp.DodajNovTerminAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajNovTermin();

            }

            private void dodajNovTermin() {
                tk = new TerminKontroler(new KreirajTermin(new GlavnaForma(), true, null), new PretraziTermin(new GlavnaForma(), true));
                tk.otvoriFormuKreirajTermin(null);
                popuniComboBoxTermini();
                kp.getjComboBoxNazivTermina().setSelectedItem(null);

            }

            
        });
        
        kp.OdustaniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odustani();

            }

            private void odustani() {
                int izbor = JOptionPane.showConfirmDialog(kp, "Da li ste sigurni da želite da odustanete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (izbor == 0) {
                    kp.dispose();
                }
            }
        });
        
        kp.DodajTerminAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajTermin();

            }

            private void dodajTermin() {

                Termin termin = null;
                String smena = null;
                String datum = null;
                LocalDate datumDezurstva = null;
                int danBr;
                int mesecBr;
                int godinaBr;

                
                
                try {
                    termin = (Termin) kp.getjComboBoxNazivTermina().getSelectedItem();

                    smena = kp.getjTextFieldSmena().getText().trim();
                    datum = kp.getjTextFieldDatumDezurstva().getText().trim();

                } catch (NumberFormatException numberFormatException) {

                    JOptionPane.showMessageDialog(kp, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (termin == null && smena == null || datum.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(kp, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (termin == null) {
                    JOptionPane.showMessageDialog(kp, "Niste uneli naziv termina.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;

                } else if (smena == null) {
                    JOptionPane.showMessageDialog(kp, "Niste uneli smenu ", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;

                } else if (datum.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(kp, "Niste uneli datum dežurstva.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;

                }
                
                
                try{
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
                    datumDezurstva = LocalDate.parse(datum, formatter);
                }catch (DateTimeParseException ex){
                    JOptionPane.showMessageDialog(kp, "Niste uneli datum u ispravnom formatu (dd.MM.yyyy.).");
                    return;
                }
                
                if (datumDezurstva == null) {
                    JOptionPane.showMessageDialog(kp, "Datum nije pravilno unet.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }


                if (datumDezurstva.isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(kp, "Datum se ne sme odnositi na budućnost.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ModelTabeleProdavacTermin mt = (ModelTabeleProdavacTermin) kp.getjTableTermini().getModel();
                List<ProdavacTermin> lista = mt.getTermini();

                ProdavacTermin pt = new ProdavacTermin(null, termin, datumDezurstva, smena);
                if (lista.contains(pt)) {

                    JOptionPane.showMessageDialog(kp, "Ovaj termin je već dodat.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                } else {
                    mt.dodajElement(pt);
                }

            }
        });
        
        kp.ObrisiTerminAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiTermin();

            }

            private void obrisiTermin() {
                int selektovaniRed = kp.getjTableTermini().getSelectedRow();

                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(kp, "Morate izabrati termin koju želite da obrišete.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ModelTabeleProdavacTermin mt = (ModelTabeleProdavacTermin) kp.getjTableTermini().getModel();
                mt.getTermini().remove(selektovaniRed);

                mt.refresujPodatke();
            }
        });

        
        
        //PRETRAZI PRODAVCA
        
        pp.VratiListuSvihProdavacaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuSvihProdavaca();
            }

            private void vratiListuSvihProdavaca() {
                List<Prodavac> listaProdavaca = new ArrayList<>();

                listaProdavaca = Komunikacija.getInstance().vratiListuSviProdavci();

                if (listaProdavaca == null || listaProdavaca.isEmpty()) {
                    JOptionPane.showMessageDialog(pp, "Sistem ne može da nađe sve prodavce.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pp, "Sistem je našao sve prodavce.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }
                
                ModelTabeleProdavci model = new ModelTabeleProdavci(listaProdavaca);
                pp.getjTableProdavci().setModel(model);
                model.refresujPodatke();

            }

        });
        
        pp.VratiListuProdavacaSaUslovomAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuProdavacaSaUslovom();

            }

            private void vratiListuProdavacaSaUslovom() {
                List<Prodavac> listaProdavaca = new ArrayList<>();
                String prodavac = null;
                Termin termin = null;

                if (!pp.getjTextFieldImePrez().getText().trim().isEmpty()) {
                    prodavac = pp.getjTextFieldImePrez().getText().trim();
                }

                termin = (Termin) pp.getjComboBoxTermini().getSelectedItem();

                if (prodavac == null && termin == null) {
                    JOptionPane.showMessageDialog(pp, "Morate uneti bar jedan kriterijum po kome želite da pretražite prodavce.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (prodavac != null && prodavac.strip().split(" ").length >= 3) {
                    JOptionPane.showMessageDialog(pp, "Možete uneti samo ime i prezime za prodavca.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (prodavac != null) {

                    List<Prodavac> lista = Komunikacija.getInstance().vratiListuProdavacProdavac(prodavac);

                    for (Prodavac p : lista) {
                        if (!listaProdavaca.contains(p)) {
                            listaProdavaca.add(p);
                        }

                    }
                }

                if (termin != null) {

                    List<Prodavac> lista = Komunikacija.getInstance().vratiListuProdavacTermin(termin);
                    for (Prodavac p : lista) {
                        if (!listaProdavaca.contains(p)) {
                            listaProdavaca.add(p);
                        }

                    }
                }

                if (listaProdavaca.isEmpty()) {
                    JOptionPane.showMessageDialog(pp, "Sistem ne može da nađe prodavce po zadatim kriterijumima.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pp, "Sistem je našao prodavce po zadatim kriterijumima.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }
                
                ModelTabeleProdavci model = new ModelTabeleProdavci(listaProdavaca);
                pp.getjTableProdavci().setModel(model);
                model.refresujPodatke();

            }
        });
        
        pp.PrikaziProdavcaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziProdavca();

            }

            private void prikaziProdavca() {
//                int selektovaniRed = pp.getjTableProdavci().getSelectedRow();
//
//                if (selektovaniRed == -1) {
//                    JOptionPane.showMessageDialog(pp, "Morate izabrati prodavca iz tabele.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
//                    return;
//                }
//
//                ModelTabeleProdavci mt = (ModelTabeleProdavci) pp.getjTableProdavci().getModel();
//                Prodavac prodavac = mt.getProdavci().get(selektovaniRed);
//                Prodavac prodavacDetalji = Komunikacija.getInstance().pretraziProdavac(prodavac);
//                if (prodavacDetalji == null) {
//                    JOptionPane.showMessageDialog(pp, "Sistem ne može da nađe prodavca.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
//                    return;
//                }
//
//                pp.dispose();
//                Koordinator.getInstance().otvoriKreirajProdavca(prodavacDetalji);
try {
        int selektovaniRed = pp.getjTableProdavci().getSelectedRow();

        if (selektovaniRed == -1) {
            JOptionPane.showMessageDialog(pp, "Morate izabrati prodavca iz tabele.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ModelTabeleProdavci mt = (ModelTabeleProdavci) pp.getjTableProdavci().getModel();
        Prodavac prodavac = mt.getProdavci().get(selektovaniRed);
        System.out.println("Selektovani prodavac: " + prodavac.getIme() + " " + prodavac.getPrezime());

        Prodavac prodavacDetalji = Komunikacija.getInstance().pretraziProdavac(prodavac);
        if (prodavacDetalji == null) {
            JOptionPane.showMessageDialog(pp, "Sistem ne može da nađe prodavca.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
            return;
        }

        pp.dispose();
        Koordinator.getInstance().otvoriKreirajProdavca(prodavacDetalji);
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(pp, "Došlo je do greške: " + e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
    }
            }
        });

        pp.RadioImePrezimeAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioImePrez();
            }

            private void radioImePrez() {
                pp.getjTextFieldImePrez().setText("");
                pp.getjComboBoxTermini().setSelectedItem(null);
                pp.getjComboBoxTermini().setEnabled(false);
                pp.getjTextFieldImePrez().setEditable(true);
            }

        });
        
        pp.RadioterminAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioTermin();
            }

            private void radioTermin() {
                pp.getjTextFieldImePrez().setText("");
                pp.getjComboBoxTermini().removeAllItems(); 
                popuniComboBoxTerminiPretraga(); 
                pp.getjComboBoxTermini().setSelectedItem(null);
                pp.getjComboBoxTermini().setEnabled(true);
                pp.getjTextFieldImePrez().setEditable(false);

            }
        });
        
        
    }
    
    private void popuniTabeluTerminima(List<ProdavacTermin> lista) {
        ModelTabeleProdavacTermin modelTabele = new ModelTabeleProdavacTermin(lista);
        modelTabele.refresujPodatke();
        kp.getjTableTermini().setModel(modelTabele);
    }
    
    private void popuniComboBoxTermini() {
        List<Termin> listaTermina = Komunikacija.getInstance().vratiListuSviTermini();

        kp.getjComboBoxNazivTermina().removeAllItems();

        for (Termin t : listaTermina) {
            kp.getjComboBoxNazivTermina().addItem(t);

        }
    }
    
    private void popuniComboBoxTerminiPretraga() {
        List<Termin> listaTermina = Komunikacija.getInstance().vratiListuSviTermini();
        pp.getjComboBoxTermini().removeAllItems();
        for (Termin t : listaTermina) {
            pp.getjComboBoxTermini().addItem(t);
        }
    }

    

    
    
}
