/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.KreirajTermin;
import forme.PretraziTermin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import model.Termin;
import modeli.ModelTabeleTermini;

/**
 *
 * @author HP
 */
public class TerminKontroler {

    private final KreirajTermin kt;
    private final PretraziTermin pt;
    
    
    public TerminKontroler(KreirajTermin kt, PretraziTermin pt){
        this.kt = kt;
        this.pt = pt;
        addActionListeners();

    }

    private void addActionListeners() {
        kt.ZapamtiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapamti();

            }

            private void zapamti() {
                String naziv = null;

                try {

                    naziv = kt.getjTextFieldNazivTermina().getText().trim();
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(kt, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;

                }

                if (naziv.trim().isEmpty()) {

                    JOptionPane.showMessageDialog(kt, "Niste uneli sve podatke", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (naziv.trim().length() > 30) {
                    JOptionPane.showMessageDialog(kt, "Tip kvalifikacije može sadržati maksimalno 100 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Termin t = new Termin(naziv);

                boolean uspesno = Komunikacija.getInstance().dodajTermin(t);

                if (!uspesno) {
                    JOptionPane.showMessageDialog(kt, "Sistem ne može da zapamti termin.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(kt, "Sistem je zapamtio termin.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    kt.dispose();
                }
            }

        });
        
        kt.PromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni();

            }

            private void promeni() {

                String naziv = null;

                try {

                    naziv = kt.getjTextFieldNazivTermina().getText().trim();
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(kt, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;

                }

                if (naziv.trim().isEmpty()) {

                    JOptionPane.showMessageDialog(kt, "Niste uneli sve podatke", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (naziv.trim().length() > 30) {
                    JOptionPane.showMessageDialog(kt, "Tip kvalifikacije može sadržati maksimalno 100 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Termin t = new Termin(naziv);
                int odgovor = JOptionPane.showConfirmDialog(kt, "Da li ste sigurni da želite da sačuvate unete promene?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {

                    boolean uspesno = Komunikacija.getInstance().promeniTermin(t);
                    if (!uspesno) {
                        JOptionPane.showMessageDialog(kt, "Sistem ne može da zapamti termin.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(kt, "Sistem je zapamtio termin.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        kt.dispose();
                    }
                }
            }

        });
        
        kt.ObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi();

            }

            private void obrisi() {
                int odgovor = JOptionPane.showConfirmDialog(kt, "Da li ste sigurni da želite da obrišete termin?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {
                    String naziv = null;

                    naziv = kt.getjTextFieldNazivTermina().getText().trim();
                    
                    Termin t = new Termin(kt.getTermin().getIdTerminDezurstva(), naziv);

                    boolean uspesno = Komunikacija.getInstance().obrisiTermin(t);
                    if (!uspesno) {
                        JOptionPane.showMessageDialog(kt, "Sistem ne može da obriše termin.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(kt, "Sistem je obrisao termin.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        kt.dispose();
                    }
                }
            }
        });
        
        pt.VratiListuSvihTerminaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuSvihTermina();

            }

            private void vratiListuSvihTermina() {

                List<Termin> lista = Komunikacija.getInstance().vratiListuSviTermini();

                if (lista == null || lista.isEmpty()) {
                    JOptionPane.showMessageDialog(pt, "Sistem ne može da nađe sve termine.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pt, "Sistem je našao sve termine.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }
                
                ModelTabeleTermini model = new ModelTabeleTermini(lista);
                pt.getjTableTermini().setModel(model);
                model.refresujPodatke();

            }
        });
        
        pt.VratiListuTerminaSaUslovomAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuTerminaSaUslovom();

            }

            private void vratiListuTerminaSaUslovom() {
                List<Termin> lista = new ArrayList<>();
                String naziv = null;

                try {

                    if (!pt.getjTextFieldPretraziPoNazivu().getText().trim().isEmpty()) {
                        naziv = pt.getjTextFieldPretraziPoNazivu().getText().trim();
                    }

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(pt, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if ((naziv == null || naziv.trim().isEmpty())) {
                    JOptionPane.showMessageDialog(pt, "Morate uneti kriterijum za pretraživanje.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (naziv != null) {
                    Termin t = new Termin(naziv);

                    List<Termin> lista1 = Komunikacija.getInstance().vratiListuTerminTermin(t);

                    for (Termin t1 : lista1) {
                        if (!lista.contains(t1)) {
                            lista.add(t1);
                        }

                    }
                }

                if (lista.isEmpty()) {
                    JOptionPane.showMessageDialog(pt, "Sistem ne može da nađe termine po zadatom kriterijumu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pt, "Sistem je našao termine po zadatom kriterijumu.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }
                
                ModelTabeleTermini model = new ModelTabeleTermini(lista);
                pt.getjTableTermini().setModel(model);
                model.refresujPodatke();

            }
        });
        
        pt.PrikaziTerminAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziTermin();

            }

            private void prikaziTermin() {
                int selektovaniRed = pt.getjTableTermini().getSelectedRow();

                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(pt, "Morate izabrati termin iz tabele.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ModelTabeleTermini mt = (ModelTabeleTermini) pt.getjTableTermini().getModel();
                Termin t = mt.getLista().get(selektovaniRed);
                Termin tDetalji = Komunikacija.getInstance().pretraziTermin(t);
                if (tDetalji == null) {
                    JOptionPane.showMessageDialog(pt, "Sistem ne može da nađe termin.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                pt.dispose();
                Koordinator.getInstance().otvoriKreirajTermin(tDetalji);
            }

        });
        
        kt.OdustaniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odustani();

            }

            private void odustani() {
                int izbor = JOptionPane.showConfirmDialog(kt, "Da li ste sigurni da želite da odustanete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (izbor == 0) {
                    kt.dispose();
                }

            }

        });

        kt.PromeniTerminAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni();

            }

            private void promeni() {
                kt.getjButtonSacuvaj().setVisible(true);
                kt.getjButtonZapamti().setVisible(false);
                kt.getjButtonOdustani().setVisible(true);
                kt.getjButtonObrisi().setVisible(false);
                kt.getjButtonPrikaziObrisi().setVisible(false);
                kt.getjButtonPrikaziPromeni().setVisible(true);
                kt.getjButtonObrisiTermin().setVisible(true);
                kt.getjButtonPromeni().setVisible(false);
                kt.getjTextFieldNazivTermina().setText(kt.getTermin().getNaziv());
                kt.getjTextFieldNazivTermina().setEditable(true);
            }

        });
        
        kt.PrikaziPromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziPromeni();

            }

            private void prikaziPromeni() {
                kt.getjButtonSacuvaj().setVisible(false);
                kt.getjButtonZapamti().setVisible(false);
                kt.getjButtonOdustani().setVisible(false);
                kt.getjButtonObrisi().setVisible(false);
                kt.getjButtonPrikaziObrisi().setVisible(false);
                kt.getjButtonPrikaziPromeni().setVisible(false);
                kt.getjButtonObrisiTermin().setVisible(true);
                kt.getjButtonPromeni().setVisible(true);
                kt.getjTextFieldNazivTermina().setText(kt.getTermin().getNaziv());
                kt.getjTextFieldNazivTermina().setEditable(false);
            }
        });
        
        kt.ObrisiTerminAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiTermin();

            }

            private void obrisiTermin() {
                kt.getjButtonSacuvaj().setVisible(false);
                kt.getjButtonZapamti().setVisible(false);
                kt.getjButtonOdustani().setVisible(true);
                kt.getjButtonObrisi().setVisible(true);
                kt.getjButtonPrikaziObrisi().setVisible(true);
                kt.getjButtonPrikaziPromeni().setVisible(false);
                kt.getjButtonObrisiTermin().setVisible(false);
                kt.getjButtonPromeni().setVisible(true);
                kt.getjTextFieldNazivTermina().setText(kt.getTermin().getNaziv());
                kt.getjTextFieldNazivTermina().setEditable(false);
            }

        });
        
        kt.PrikaziObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziObrisi();

            }

            private void prikaziObrisi() {
                kt.getjButtonSacuvaj().setVisible(false);
                kt.getjButtonZapamti().setVisible(false);
                kt.getjButtonOdustani().setVisible(false);
                kt.getjButtonObrisi().setVisible(false);
                kt.getjButtonPrikaziObrisi().setVisible(false);
                kt.getjButtonPrikaziPromeni().setVisible(false);
                kt.getjButtonObrisiTermin().setVisible(true);
                kt.getjButtonPromeni().setVisible(true);
                kt.getjTextFieldNazivTermina().setText(kt.getTermin().getNaziv());
                kt.getjTextFieldNazivTermina().setEditable(false);
            }

        });
        
        
        
    }

    public void otvoriFormuKreirajTermin(Termin termin) {
        if (termin == null) {

            kt.getjButtonSacuvaj().setVisible(false);
            kt.getjButtonZapamti().setVisible(true);
            kt.getjButtonOdustani().setVisible(true);
            kt.getjButtonObrisi().setVisible(false);
            kt.getjButtonPrikaziObrisi().setVisible(false);
            kt.getjButtonPrikaziPromeni().setVisible(false);
            kt.getjButtonObrisiTermin().setVisible(false);
            kt.getjButtonPromeni().setVisible(false);
            JOptionPane.showMessageDialog(kt, "Sistem je kreirao termin.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            kt.setVisible(true);

        } else {

            kt.getjButtonSacuvaj().setVisible(false);
            kt.getjButtonZapamti().setVisible(false);
            kt.getjButtonOdustani().setVisible(false);
            kt.getjButtonObrisi().setVisible(false);
            kt.getjButtonPrikaziObrisi().setVisible(false);
            kt.getjButtonPrikaziPromeni().setVisible(false);
            kt.getjButtonObrisiTermin().setVisible(true);
            kt.getjButtonPromeni().setVisible(true);

            kt.getjTextFieldNazivTermina().setText(termin.getNaziv());
            kt.getjTextFieldNazivTermina().setEditable(false);
            JOptionPane.showMessageDialog(kt, "Sistem je našao termin.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);

            kt.setVisible(true);
        }
    }
    
    public void otvoriFormuPretraziTermin() {

        popuniTabelu();
        pt.setVisible(true);
    }

    private void popuniTabelu() {
        List<Termin> lista = new ArrayList<>();
        ModelTabeleTermini modelTabele = new ModelTabeleTermini(lista);
        pt.getjTableTermini().setModel(modelTabele);
    }
    
}
