/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.KreirajMesto;
import forme.PretraziMesto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import model.Mesto;
import modeli.ModelTabeleMesto;

/**
 *
 * @author HP
 */
public class MestoKontroler {
    
    private final KreirajMesto km;
    private final PretraziMesto pm;

    public MestoKontroler(KreirajMesto km, PretraziMesto pm) {
        this.km = km;
        this.pm = pm;
        addActionListeners();
    }
    
    private void addActionListeners() {
        km.ZapamtiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapamti();
            }

            private void zapamti() {
                String naziv = null;

                try {
                    naziv = km.getjTextFieldMesto().getText().trim();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(km, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (naziv.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(km, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (naziv.trim().length() > 30) {
                    JOptionPane.showMessageDialog(km, "Naziv kategorije može sadržati maksimalno 30 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Mesto m = new Mesto(naziv);

                boolean uspesno = Komunikacija.getInstance().kreirajMesto(m);
                if (!uspesno) {
                    JOptionPane.showMessageDialog(km, "Sistem ne može da zapamti mesto.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(km, "Sistem je zapamtio mesto.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    km.dispose();
                }
            }

        });
        
        km.PromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni();

            }

            private void promeni() {
                String naziv = null;

                try {
                    naziv = km.getjTextFieldMesto().getText().trim();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(km, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (naziv.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(km, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (naziv.trim().length() > 30) {
                    JOptionPane.showMessageDialog(km, "Naziv kategorije može sadržati maksimalno 30 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                Mesto m = new Mesto(km.getMesto().getIdMesto(),naziv);

                int odgovor = JOptionPane.showConfirmDialog(km, "Da li ste sigurni da želite da sačuvate unete promene?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {

                    boolean uspesno = Komunikacija.getInstance().promeniMesto(m);
                    if (!uspesno) {
                        JOptionPane.showMessageDialog(km, "Sistem ne može da zapamti mesto.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(km, "Sistem je zapamtio mesto.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        km.dispose();
                    }
                }
            }

        });
        
        km.ObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi();
            }

            private void obrisi() {
                int odgovor = JOptionPane.showConfirmDialog(km, "Da li ste sigurni da želite da obrišete mesto?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {
                    String naziv = null;

                    naziv = km.getjTextFieldMesto().getText().trim();
                    
                    Mesto m = new Mesto(km.getMesto().getIdMesto(),naziv);

                    boolean uspesno = Komunikacija.getInstance().obrisiMesto(m);

                    if (!uspesno) {
                        JOptionPane.showMessageDialog(km, "Sistem ne može da obriše mesto.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    } else {

                        JOptionPane.showMessageDialog(km, "Sistem je obrisao mesto.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        km.dispose();
                    }
                }
            }
        });
        
        km.OdustaniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odustani();
            }

            private void odustani() {
                int izbor = JOptionPane.showConfirmDialog(km, "Da li ste sigurni da želite da odustanete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (izbor == 0) {
                    km.dispose();
                }
            }
        });
        
        km.PromeniMestoAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeniMesto();
            }

            private void promeniMesto() {
                km.getjButtonSacuvaj().setVisible(true);
                km.getjButtonZapamti().setVisible(false);
                km.getjButtonOdustani().setVisible(true);
                km.getjButtonObrisi().setVisible(false);
                km.getjButtonPrikaziObrisi().setVisible(false);
                km.getjButtonPrikaziPromeni().setVisible(true);
                km.getjButtonPromeni().setVisible(false);
                km.getjButtonObrisiMesto().setVisible(true);
                km.getjTextFieldMesto().setEditable(true);
            }
        });
        
        km.PrikaziPromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziPromeni();
            }

            private void prikaziPromeni() {
                km.getjButtonSacuvaj().setVisible(false);
                km.getjButtonZapamti().setVisible(false);
                km.getjButtonOdustani().setVisible(false);
                km.getjButtonObrisi().setVisible(false);
                km.getjButtonPrikaziObrisi().setVisible(false);
                km.getjButtonPrikaziPromeni().setVisible(false);
                km.getjButtonPromeni().setVisible(true);
                km.getjButtonObrisiMesto().setVisible(true);
                km.getjTextFieldMesto().setText(km.getMesto().getNaziv());
                km.getjTextFieldMesto().setEditable(false);
            }
        });
        
        km.ObrisiMestoAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiMesto();
            }

            private void obrisiMesto() {
                km.getjButtonSacuvaj().setVisible(false);
                km.getjButtonZapamti().setVisible(false);
                km.getjButtonOdustani().setVisible(true);
                km.getjButtonObrisi().setVisible(true);
                km.getjButtonPrikaziObrisi().setVisible(true);
                km.getjButtonPrikaziPromeni().setVisible(false);
                km.getjButtonPromeni().setVisible(true);
                km.getjButtonObrisiMesto().setVisible(false);
                km.getjTextFieldMesto().setText(km.getMesto().getNaziv());
                km.getjTextFieldMesto().setEditable(false);
            }
        });
        
        km.PrikaziObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziObrisi();
            }

            private void prikaziObrisi() {
                km.getjButtonSacuvaj().setVisible(false);
                km.getjButtonZapamti().setVisible(false);
                km.getjButtonOdustani().setVisible(false);
                km.getjButtonObrisi().setVisible(false);
                km.getjButtonPrikaziObrisi().setVisible(false);
                km.getjButtonPrikaziPromeni().setVisible(false);
                km.getjButtonPromeni().setVisible(true);
                km.getjButtonObrisiMesto().setVisible(true);
                km.getjTextFieldMesto().setText(km.getMesto().getNaziv());
                km.getjTextFieldMesto().setEditable(false);
            }
        });
        
        pm.VratiListuSviMestaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuSviMesta();
            }

            private void vratiListuSviMesta() {

                List<Mesto> lista = Komunikacija.getInstance().vratiListuSviMesto();

                if (lista == null || lista.isEmpty()) {
                    JOptionPane.showMessageDialog(pm, "Sistem ne može da nađe sva mesta.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pm, "Sistem je našao sva mesta.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }
                ModelTabeleMesto mt = (ModelTabeleMesto) pm.getjTable1().getModel();
                mt.setLista(lista);
                mt.refresujPodatke();

            }

        });
        
        pm.VratiListuMestoSaUslovomAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuMestoSaUslovom(e);
            }

            public void vratiListuMestoSaUslovom(ActionEvent e) {

                List<Mesto> lista = new ArrayList<>();
                String naziv = pm.getjTextFieldMesto().getText().trim();

                if (naziv.isEmpty()) {
                    JOptionPane.showMessageDialog(pm, "Morate uneti naziv mesta za pretragu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }


                if (naziv != null) {
                    Mesto m = new Mesto(naziv);

                    List<Mesto> lista1 = Komunikacija.getInstance().vratiListuMestoMesto(m);

                    for (Mesto me : lista1) {
                        if (!lista.contains(me)) {
                            lista.add(me);
                        }

                    }
                }

                if (lista.isEmpty()) {
                    JOptionPane.showMessageDialog(pm, "Sistem ne može da nađe mesto po zadatom kriterijumu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pm, "Sistem je našao mesto po zadatom kriterijumu.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }

                ModelTabeleMesto mt = (ModelTabeleMesto) pm.getjTable1().getModel();
                mt.setLista(lista);
                mt.refresujPodatke();

            }

        });
        
        pm.PrikaziMestoAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziMesto();
            }

            private void prikaziMesto() {
                int selektovaniRed = pm.getjTable1().getSelectedRow();

                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(pm, "Morate izabrati mesto iz tabele.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ModelTabeleMesto mt = (ModelTabeleMesto) pm.getjTable1().getModel();
                Mesto m = mt.getLista().get(selektovaniRed);
                Mesto mDetalji = Komunikacija.getInstance().pretraziMesto(m);
                if (mDetalji == null) {
                    JOptionPane.showMessageDialog(km, "Sistem ne može da nađe mesto.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;

                }
                pm.dispose();
                Koordinator.getInstance().otvoriKreirajMesto(mDetalji);

            }
        });
        
        
    }
    
    public void otvoriFormuKreirajMesto(Mesto mesto) {
        if (mesto == null) {

            km.getjButtonSacuvaj().setVisible(false);
            km.getjButtonObrisi().setVisible(false);
            km.getjButtonZapamti().setVisible(true);
            km.getjButtonOdustani().setVisible(true);
            km.getjButtonPrikaziObrisi().setVisible(false);
            km.getjButtonPrikaziPromeni().setVisible(false);
            km.getjButtonPromeni().setVisible(false);
            km.getjButtonObrisiMesto().setVisible(false);
            JOptionPane.showMessageDialog(km, "Sistem je kreirao mesto.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);

            km.setVisible(true);

        } else {
            km.getjButtonSacuvaj().setVisible(false);
            km.getjButtonZapamti().setVisible(false);
            km.getjButtonOdustani().setVisible(false);
            km.getjButtonObrisi().setVisible(false);
            km.getjButtonPrikaziObrisi().setVisible(false);
            km.getjButtonPrikaziPromeni().setVisible(false);
            km.getjButtonPromeni().setVisible(true);
            km.getjButtonObrisiMesto().setVisible(true);
            km.getjTextFieldMesto().setText(mesto.getNaziv());
            km.getjTextFieldMesto().setEditable(false);

            JOptionPane.showMessageDialog(km, "Sistem je našao mesto.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);

            km.setVisible(true);
        }

    }
    
    public void otvoriFormuPretraziMesto() {

        popuniTabelu();
        pm.setVisible(true);

    }

    private void popuniTabelu() {

        List<Mesto> lista = new ArrayList<>();
        ModelTabeleMesto modelTabele = new ModelTabeleMesto(lista);
        pm.getjTable1().setModel(modelTabele);
    }
    
}
