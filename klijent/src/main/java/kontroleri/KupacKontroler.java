/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.KreirajKupac;
import forme.PretraziKupac;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import model.Kupac;
import model.Mesto;
import modeli.ModelTabeleKupci;

/**
 *
 * @author HP
 */
public class KupacKontroler {
    
    private final KreirajKupac kk;
    private final PretraziKupac pk;

    public KupacKontroler(KreirajKupac kk, PretraziKupac pk) {
        this.kk = kk;
        this.pk = pk;
        addActionListeners();
    }

    public void otvoriFormuKreirajKupac(Kupac kupac) {
        if (kupac == null) {

            popuniMesta();
            
            kk.getjButtonZapamti().setVisible(true);
            kk.getjButtonOdustani().setVisible(true);
            
            kk.getjButtonObrisi().setVisible(false);
            kk.getjButtonObrisiKupca().setVisible(false);
            kk.getjButtonPrikaziObrisi().setVisible(false);
            
            kk.getjButtonPromeniKupca().setVisible(false);
            kk.getjButtonPrikaziPromeni().setVisible(false);
            kk.getjButtonSacuvaj().setVisible(false);
            
            kk.getjComboBoxMesto().setSelectedItem(null);
//            JOptionPane.showMessageDialog(kk, "Sistem je kreirao kupca.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            kk.setVisible(true);

        } else {
            
            popuniMesta();

            kk.getjButtonZapamti().setVisible(false);
            kk.getjButtonOdustani().setVisible(false);
            
            kk.getjButtonObrisiKupca().setVisible(true);
            kk.getjButtonObrisi().setVisible(false);
            kk.getjButtonPrikaziObrisi().setVisible(false);
            
            kk.getjButtonPromeniKupca().setVisible(true);
            kk.getjButtonPrikaziPromeni().setVisible(false);
            kk.getjButtonSacuvaj().setVisible(false);
            
            kk.getjComboBoxMesto().setSelectedItem(null);
            kk.getjTextFieldIme().setText(kupac.getIme());
            kk.getjTextFieldPrezime().setText(kupac.getPrezime());
            kk.getjComboBoxMesto().setSelectedItem(kupac.getMesto());
            kk.getjComboBoxMesto().setEnabled(false);
            kk.getjTextFieldIme().setEditable(false);
            kk.getjTextFieldPrezime().setEditable(false);
            kk.getjTextFieldBrojLicneKarte().setEditable(false);
            JOptionPane.showMessageDialog(kk, "Sistem je našao kupca.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            kk.setVisible(true);
        }
    }
    
       
        private void addActionListeners() {
        kk.ZapamtiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapamti();

            }

            private void zapamti() {
                String ime = null;
                String prezime = null;
                String brojLicneKarte = null;
                Mesto mesto = null;

                try {

                    ime = kk.getjTextFieldIme().getText().trim();
                    prezime = kk.getjTextFieldPrezime().getText().trim();
                    brojLicneKarte = kk.getjTextFieldBrojLicneKarte().getText().trim();
                    mesto = (Mesto) kk.getjComboBoxMesto().getSelectedItem();

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(kk, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (ime.trim().isEmpty() || prezime.trim().isEmpty() || brojLicneKarte.trim().isEmpty() || mesto == null) {

                    JOptionPane.showMessageDialog(kk, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (ime.trim().length() > 30 || prezime.trim().length() > 30 || brojLicneKarte.trim().length() != 9) {

                    if (ime.trim().length() > 30) {
                        JOptionPane.showMessageDialog(kk, "Ime može sadržati maksimalno 30 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (prezime.trim().length() > 30) {
                        JOptionPane.showMessageDialog(kk, "Prezime može sadržati maksimalno 30 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (!brojLicneKarte.matches("\\d{9}")) {
                        JOptionPane.showMessageDialog(kk, "Broj lične karte mora da sadrži tačno 9 cifara.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }

                    return;
                }
                
                boolean ispravnoIme = true;
                for (int i = 0; i < ime.length(); i++) {
                    if (!Character.isLetter(ime.charAt(i))) {
                        ispravnoIme = false;
                    }

                }

                if (!ispravnoIme) {
                    JOptionPane.showMessageDialog(kk, "Ime može sadržati samo slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                boolean ispravnoPrezime = true;
                for (int i = 0; i < prezime.length(); i++) {
                    if (!Character.isLetter(prezime.charAt(i))) {
                        ispravnoPrezime = false;
                    }

                }

                if (!ispravnoPrezime) {
                    JOptionPane.showMessageDialog(kk, "Prezime može sadržati samo slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Kupac kupac = new Kupac(ime, prezime, brojLicneKarte, mesto);

                boolean uspesno = Komunikacija.getInstance().kreirajKupac(kupac);
                if (!uspesno) {
                    JOptionPane.showMessageDialog(kk, "Sistem ne može da zapamti kupca.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(kk, "Sistem je zapamtio kupca.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    kk.dispose();
                }
            }

        });
        
        
        kk.PromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni();

            }

            private void promeni() {
                String ime = null;
                String prezime = null;
                String brojLicneKarte = null;
                Mesto mesto = null;

                try {

                    ime = kk.getjTextFieldIme().getText().trim();
                    prezime = kk.getjTextFieldPrezime().getText().trim();
                    brojLicneKarte = kk.getjTextFieldBrojLicneKarte().getText().trim();
                    mesto = (Mesto) kk.getjComboBoxMesto().getSelectedItem();

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(kk, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (ime.trim().isEmpty() || prezime.trim().isEmpty() || brojLicneKarte.trim().isEmpty() || mesto == null) {

                    JOptionPane.showMessageDialog(kk, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (ime.trim().length() > 30 || prezime.trim().length() > 30 || brojLicneKarte.trim().length() != 9) {

                    if (ime.trim().length() > 30) {
                        JOptionPane.showMessageDialog(kk, "Ime može sadržati maksimalno 30 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (prezime.trim().length() > 30) {
                        JOptionPane.showMessageDialog(kk, "Prezime može sadržati maksimalno 30 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (!brojLicneKarte.matches("\\d{9}")) {
                        JOptionPane.showMessageDialog(kk, "Broj lične karte mora da sadrži tačno 9 cifara.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }

                    return;
                }

                boolean ispravnoIme = true;
                for (int i = 0; i < ime.length(); i++) {
                    if (!Character.isLetter(ime.charAt(i))) {
                        ispravnoIme = false;
                    }

                }

                if (!ispravnoIme) {
                    JOptionPane.showMessageDialog(kk, "Ime može sadržati samo slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                boolean ispravnoPrezime = true;
                for (int i = 0; i < prezime.length(); i++) {
                    if (!Character.isLetter(prezime.charAt(i))) {
                        ispravnoPrezime = false;
                    }

                }

                if (!ispravnoPrezime) {
                    JOptionPane.showMessageDialog(kk, "Prezime može sadržati samo slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Kupac k = new Kupac(kk.getKupac().getIdKupac(), ime, prezime, brojLicneKarte, mesto);

                int odgovor = JOptionPane.showConfirmDialog(kk, "Da li ste sigurni da želite da sačuvate unete promene?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {

                    boolean uspesno = Komunikacija.getInstance().promeniKupac(k);

                    if (!uspesno) {
                        JOptionPane.showMessageDialog(kk, "Sistem ne može da zapamti kupca.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(kk, "Sistem je zapamtio kupca.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        kk.dispose();
                    }
                }
            }

        });
        
        kk.ObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi();

            }

            private void obrisi() {
                int odgovor = JOptionPane.showConfirmDialog(kk, "Da li ste sigurni da želite da obrišete kupca?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {
                    String ime = null;
                    String prezime = null;
                    String brojLicneKarte = null;
                    Mesto mesto = null;

                    ime = kk.getjTextFieldIme().getText().trim();
                    prezime = kk.getjTextFieldPrezime().getText().trim();
                    brojLicneKarte = kk.getjTextFieldBrojLicneKarte().getText().trim();
                    mesto = (Mesto) kk.getjComboBoxMesto().getSelectedItem();

                    Kupac k = new Kupac(kk.getKupac().getIdKupac(), ime, prezime, brojLicneKarte, mesto);
                    
                    System.out.println("KUPAC ZA BRISANJE: "+k);

                    boolean uspesno = Komunikacija.getInstance().obrisiKupac(k);

                    if (!uspesno) {
                        JOptionPane.showMessageDialog(kk, "Sistem ne može da obriše kupca.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(kk, "Sistem je obrisao kupca.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        kk.dispose();
                    }
                }
            }
        });
         
        pk.VratiListuSvihKupacaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("KLIKNUTO NA PRIKAZI SVE KUPCE!");
                vratiListuSvihKupaca();

            }

            private void vratiListuSvihKupaca() {
//                System.out.println("SALJEM ZAHTEV SERVERU ZA SVE KUPCE...");
                List<Kupac> listaKupaca = Komunikacija.getInstance().vratiListuSviKupci();
//                System.out.println("PRIMIO LISTU KUPACA " + (listaKupaca != null ? listaKupaca.size() : "null"));

                if (listaKupaca == null || listaKupaca.isEmpty()) {
                    JOptionPane.showMessageDialog(pk, "Sistem ne može da nađe sve kupce.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pk, "Sistem je našao sve kupce.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }

                ModelTabeleKupci mt = (ModelTabeleKupci) pk.getjTableKupci().getModel();

                if (listaKupaca.size() > 50) {
                    List<Kupac> lista = listaKupaca.subList(0, Math.min(50, listaKupaca.size()));
                    mt.setLista(lista);
                    mt.refresujPodatke();
                } else {
                    mt.setLista(listaKupaca);
                    mt.refresujPodatke();
                }
            }

        });
        
        pk.VratiListuKupacaSaUslovomAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuKupacaSaUslovom();

            }

            private void vratiListuKupacaSaUslovom() {
                List<Kupac> listaKupaca= new ArrayList<>();
                String kupac = null;
                String mesto = null;

                try {

                    if (!pk.getjTextFieldFiltrirajPoImePrez().getText().trim().isEmpty()) {
                        kupac = pk.getjTextFieldFiltrirajPoImePrez().getText().trim();
                    }

                    if (!pk.getjTextFieldFiltrirajPoMestu().getText().trim().isEmpty()) {
                        mesto = pk.getjTextFieldFiltrirajPoMestu().getText().trim();
                    }
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(pk, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (kupac == null && mesto == null) {
                    JOptionPane.showMessageDialog(pk, "Morate uneti bar jedan kriterijum po kome želite da pretražite kupce.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (kupac != null) {

                    List<Kupac> lista = Komunikacija.getInstance().vratiListuKupacKupac(kupac);

                    for (Kupac k : lista) {
                        if (!listaKupaca.contains(k)) {
                            listaKupaca.add(k);
                        }

                    }
                }

                //neka provera za mesto mozda fali?

                if (mesto != null) {

                    List<Kupac> lista = Komunikacija.getInstance().vratiListuKupacMesto(mesto);

                    for (Kupac k : lista) {
                        if (!listaKupaca.contains(k)) {
                            listaKupaca.add(k);
                        }

                    }
                }

                if (listaKupaca == null || listaKupaca.isEmpty()) {
                    JOptionPane.showMessageDialog(pk, "Sistem ne može da nađe kupce po zadatim kriterijumima.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pk, "Sistem je našao kupce po zadatim kriterijumima.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }
                ModelTabeleKupci mt = (ModelTabeleKupci) pk.getjTableKupci().getModel();

                if (listaKupaca.size() > 50) {
                    List<Kupac> lista = listaKupaca.subList(0, Math.min(50, listaKupaca.size()));
                    mt.setLista(lista);
                    mt.refresujPodatke();
                } else {
                    mt.setLista(listaKupaca);
                    mt.refresujPodatke();
                }
            }
        });
        
        pk.PrikaziKupcaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziKupca();
                System.out.println("KLIKNUO SAM PRIKAZI KUPCA");

            }

            private void prikaziKupca() {
                int selektovaniRed = pk.getjTableKupci().getSelectedRow();
                System.out.println("DOSAO SAM DO DELA PRIKAZI KUPAC U KUPACKONTROLER");
                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(pk, "Morate izabrati stavku iz tabele.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ModelTabeleKupci mt = (ModelTabeleKupci) pk.getjTableKupci().getModel();
                Kupac kupac = mt.getLista().get(selektovaniRed);
                System.out.println(kupac);
                Kupac kupacDetalji = Komunikacija.getInstance().pretraziKupac(kupac);
                if (kupacDetalji == null) {
                    JOptionPane.showMessageDialog(pk, "Sistem ne može da nađe kupca.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                pk.dispose();
                try {
                    Koordinator.getInstance().otvoriKreirajKupac(kupacDetalji);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pk, "Sistem ne može da nađe kupca.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                }
            }

        });
        
        
        kk.PromeniKupcaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni();

            }

            private void promeni() {
                kk.getjButtonSacuvaj().setVisible(true);
                kk.getjButtonZapamti().setVisible(false);
                kk.getjButtonOdustani().setVisible(true);
                kk.getjButtonObrisi().setVisible(false);
                kk.getjButtonObrisiKupca().setVisible(true);
                kk.getjButtonPromeniKupca().setVisible(false);
                kk.getjComboBoxMesto().setSelectedItem(null);
                kk.getjTextFieldBrojLicneKarte().setText(kk.getKupac().getBrojLicneKarte());
                kk.getjTextFieldIme().setText(kk.getKupac().getIme());
                kk.getjTextFieldPrezime().setText(kk.getKupac().getPrezime());
                kk.getjComboBoxMesto().setSelectedItem(kk.getKupac().getMesto());
                kk.getjComboBoxMesto().setEnabled(true);
                kk.getjTextFieldBrojLicneKarte().setEditable(true);
                kk.getjTextFieldIme().setEditable(true);
                kk.getjTextFieldPrezime().setEditable(true);
                kk.getjButtonPrikaziPromeni().setVisible(true);
                kk.getjButtonPrikaziObrisi().setVisible(false);
            }

        });
        
        kk.PrikaziPromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziPromeni();

            }

            private void prikaziPromeni() {
                kk.getjButtonSacuvaj().setVisible(false);
                kk.getjButtonZapamti().setVisible(false);
                kk.getjButtonOdustani().setVisible(false);
                kk.getjButtonObrisi().setVisible(false);
                kk.getjButtonObrisiKupca().setVisible(true);
                kk.getjButtonPromeniKupca().setVisible(true);
                kk.getjComboBoxMesto().setSelectedItem(null);
                kk.getjTextFieldBrojLicneKarte().setText(kk.getKupac().getBrojLicneKarte());
                kk.getjTextFieldIme().setText(kk.getKupac().getIme());
                kk.getjTextFieldPrezime().setText(kk.getKupac().getPrezime());
                kk.getjComboBoxMesto().setSelectedItem(kk.getKupac().getMesto());
                kk.getjComboBoxMesto().setEnabled(false);
                kk.getjTextFieldBrojLicneKarte().setEditable(false);
                kk.getjTextFieldIme().setEditable(false);
                kk.getjTextFieldPrezime().setEditable(false);
                kk.getjButtonPrikaziPromeni().setVisible(false);
                kk.getjButtonPrikaziObrisi().setVisible(false);
            }

        });
        
        kk.ObrisiKupcaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiKupca();

            }

            private void obrisiKupca() {
                
                kk.getjButtonSacuvaj().setVisible(false);
                kk.getjButtonZapamti().setVisible(false);
                kk.getjButtonOdustani().setVisible(true);
                
                kk.getjButtonObrisi().setVisible(true);
                kk.getjButtonObrisiKupca().setVisible(false);
                kk.getjButtonPrikaziObrisi().setVisible(true);
                
                kk.getjButtonPromeniKupca().setVisible(true);
                kk.getjButtonPrikaziPromeni().setVisible(false);
                
                kk.getjComboBoxMesto().setEnabled(false);
                kk.getjTextFieldBrojLicneKarte().setEditable(false);
                kk.getjTextFieldIme().setEditable(false);
                kk.getjTextFieldPrezime().setEditable(false);
                kk.getjButtonPrikaziObrisi().setVisible(true);
                kk.getjButtonOdustani().setVisible(false);
                kk.getjComboBoxMesto().setSelectedItem(null);
                kk.getjTextFieldBrojLicneKarte().setText(kk.getKupac().getBrojLicneKarte());
                kk.getjTextFieldIme().setText(kk.getKupac().getIme());
                kk.getjTextFieldPrezime().setText(kk.getKupac().getPrezime());
                kk.getjComboBoxMesto().setSelectedItem(kk.getKupac().getMesto());
            }

        });
        
        kk.PrikaziObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziObrisi();

            }

            private void prikaziObrisi() {
                
                kk.getjButtonOdustani().setVisible(false);
                kk.getjButtonObrisiKupca().setVisible(true);
                kk.getjButtonPrikaziPromeni().setVisible(false);
                
                kk.getjButtonSacuvaj().setVisible(false);
                kk.getjButtonZapamti().setVisible(false);
                kk.getjButtonObrisi().setVisible(false);
                kk.getjButtonPromeniKupca().setVisible(true);
                
                kk.getjComboBoxMesto().setEnabled(false);
                kk.getjTextFieldBrojLicneKarte().setEditable(false);
                kk.getjTextFieldIme().setEditable(false);
                kk.getjTextFieldPrezime().setEditable(false);
                kk.getjButtonPrikaziObrisi().setVisible(false);
                kk.getjComboBoxMesto().setSelectedItem(null);
                kk.getjTextFieldBrojLicneKarte().setText(kk.getKupac().getBrojLicneKarte());
                kk.getjTextFieldIme().setText(kk.getKupac().getIme());
                kk.getjTextFieldPrezime().setText(kk.getKupac().getPrezime());
                kk.getjComboBoxMesto().setSelectedItem(kk.getKupac().getMesto());
                
                
            }

        });
        
        pk.RadioMestoAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioMesto();

            }

            private void radioMesto() {
                pk.getjTextFieldFiltrirajPoImePrez().setText("");
                pk.getjTextFieldFiltrirajPoMestu().setText("");
                pk.getjTextFieldFiltrirajPoMestu().setEditable(true);
                pk.getjTextFieldFiltrirajPoImePrez().setEditable(false);
            }
        });

        
        pk.RadioImePrezAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioImePrez();

            }

            private void radioImePrez() {
                pk.getjTextFieldFiltrirajPoImePrez().setText("");
                pk.getjTextFieldFiltrirajPoMestu().setText("");
                pk.getjTextFieldFiltrirajPoMestu().setEditable(false);
                pk.getjTextFieldFiltrirajPoImePrez().setEditable(true);
            }
        });
        
        kk.OdustaniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odustani();

            }

            private void odustani() {
                int izbor = JOptionPane.showConfirmDialog(kk, "Da li ste sigurni da želite da odustanete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (izbor == 0) {
                    kk.dispose();
                }
            }

        });
        
        }

    public void otvoriFormuPretraziKupac() {
        pk.getjRadioButtonFiltrirajImePrez().setSelected(true);
        pk.getjTextFieldFiltrirajPoImePrez().setEditable(true);
        pk.getjTextFieldFiltrirajPoMestu().setEditable(false);
        ButtonGroup group = new ButtonGroup();
        group.add(pk.getjRadioButtonFiltrirajImePrez());
        group.add(pk.getjRadioButtonFiltrirajPoMestu());
        popuniTabelu();
        pk.setVisible(true);
    }
    
    private void popuniTabelu() {
        List<Kupac> kupci = new ArrayList<>();
        ModelTabeleKupci modelTabele = new ModelTabeleKupci(kupci);
        pk.getjTableKupci().setModel(modelTabele);
    }
        
     private void popuniMesta() {

            List<Mesto> listaMesta = Komunikacija.getInstance().vratiListuSviMesto();

            kk.getjComboBoxMesto().removeAllItems();

            for (Mesto mesto : listaMesta) {
                kk.getjComboBoxMesto().addItem(mesto);

            }

    }
        
    
}
