package kontroleri;

import forme.KreirajBicikla;
import forme.PretraziBicikla;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import model.Bicikla;
import model.BiciklaZaDecu;
import model.BiciklaZaOdrasle;
import model.BiciklaSaRiksom;
import modeli.ModelTabeleBicikla;

public class BiciklaKontroler {

    private final KreirajBicikla kb;
    private final PretraziBicikla pb;

    public BiciklaKontroler(KreirajBicikla kb, PretraziBicikla pb) {
        this.kb = kb;
        this.pb = pb;
        addActionListeners();
    }

    private void addActionListeners() {

        kb.ZapamtiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapamti();
            }

            private void zapamti() {
                Bicikla b = kb.kreirajBicikluIzForme();
                if (b == null) {
                    JOptionPane.showMessageDialog(kb, "Greška pri kreiranju bicikle.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                boolean uspesno = Komunikacija.getInstance().kreirajBicikla(b);
                if (!uspesno) {
                    JOptionPane.showMessageDialog(kb, "Sistem ne može da zapamti biciklu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(kb, "Sistem je zapamtio biciklu.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    kb.dispose();
                }
            }
        });

        kb.PromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni();
            }

            private void promeni() {
                Bicikla b = kb.kreirajBicikluIzForme();
                if (b == null) {
                    JOptionPane.showMessageDialog(kb, "Greška pri kreiranju bicikle.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                b.setIdBicikla(kb.getBicikla().getIdBicikla());

                int odgovor = JOptionPane.showConfirmDialog(kb,
                        "Da li ste sigurni da želite da sačuvate unete promene?",
                        "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {
                    boolean uspesno = Komunikacija.getInstance().promeniBicikla(b);
                    if (!uspesno) {
                        JOptionPane.showMessageDialog(kb, "Sistem ne može da zapamti biciklu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(kb, "Sistem je zapamtio biciklu.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        kb.dispose();
                    }
                }
            }
        });

        kb.ObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi();
            }

            private void obrisi() {
                int odgovor = JOptionPane.showConfirmDialog(kb,
                        "Da li ste sigurni da želite da obrišete biciklu?",
                        "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {
                    Bicikla b = kb.getBicikla();
                    boolean uspesno = Komunikacija.getInstance().obrisiBicikla(b);
                    if (!uspesno) {
                        JOptionPane.showMessageDialog(kb, "Sistem ne može da obriše biciklu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(kb, "Sistem je obrisao biciklu.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        kb.dispose();
                    }
                }
            }
        });

        kb.OdustaniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int izbor = JOptionPane.showConfirmDialog(kb,
                        "Da li ste sigurni da želite da odustanete?",
                        "Potvrda", JOptionPane.YES_NO_OPTION);
                if (izbor == 0) {
                    kb.dispose();
                }
            }
        });

        kb.ObrisiBiciklaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kb.getjButtonSacuvaj().setVisible(false);
                kb.getjButtonZapamti().setVisible(false);
                kb.getjButtonOdustani().setVisible(true);
                kb.getjButtonObrisi().setVisible(true);
                kb.getjButtonPromeni().setVisible(true);
                kb.getjButtonObrisiBiciklu().setVisible(false);
                kb.getjButtonPrikaziObrisi().setVisible(true);
                kb.getjButtonPrikaziPromeni().setVisible(false);
                popuniForme(false);
            }
        });

        kb.PrikaziObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kb.getjButtonSacuvaj().setVisible(false);
                kb.getjButtonZapamti().setVisible(false);
                kb.getjButtonOdustani().setVisible(false);
                kb.getjButtonObrisi().setVisible(false);
                kb.getjButtonPromeni().setVisible(true);
                kb.getjButtonObrisiBiciklu().setVisible(true);
                kb.getjButtonPrikaziObrisi().setVisible(false);
                kb.getjButtonPrikaziPromeni().setVisible(false);
                popuniForme(false);
            }
        });

        kb.PromeniBiciklaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kb.getjButtonSacuvaj().setVisible(true);
                kb.getjButtonZapamti().setVisible(false);
                kb.getjButtonOdustani().setVisible(true);
                kb.getjButtonObrisi().setVisible(false);
                kb.getjButtonPromeni().setVisible(false);
                kb.getjButtonObrisiBiciklu().setVisible(true);
                kb.getjButtonPrikaziObrisi().setVisible(false);
                kb.getjButtonPrikaziPromeni().setVisible(true);
                popuniForme(true);
            }
        });

        kb.PrikaziPromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kb.getjButtonSacuvaj().setVisible(false);
                kb.getjButtonZapamti().setVisible(false);
                kb.getjButtonOdustani().setVisible(false);
                kb.getjButtonObrisi().setVisible(false);
                kb.getjButtonPromeni().setVisible(true);
                kb.getjButtonObrisiBiciklu().setVisible(true);
                kb.getjButtonPrikaziObrisi().setVisible(false);
                kb.getjButtonPrikaziPromeni().setVisible(false);
                popuniForme(false);
            }
        });

        pb.VratiListuSviBicklaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Bicikla> lista = Komunikacija.getInstance().vratiListuSviBicikla();
                if (lista == null || lista.isEmpty()) {
                    JOptionPane.showMessageDialog(pb, "Sistem ne može da nađe sve bicikle.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ModelTabeleBicikla mt = (ModelTabeleBicikla) pb.getjTableBicikle().getModel();
                mt.setLista(lista);
                mt.refresujPodatke();
            }
        });

        pb.VratiListuBiciklaSaUslovomAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tip = (String) pb.getjComboBoxTip().getSelectedItem();
                Bicikla uslov = kreirajPrazanObjekatPoTipu(tip);
                if (uslov == null) return;

                List<Bicikla> lista = Komunikacija.getInstance().vratiListuBiciklaBicikla(uslov);
                if (lista == null || lista.isEmpty()) {
                    JOptionPane.showMessageDialog(pb, "Sistem ne može da nađe biciklu po zadatom kriterijumu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ModelTabeleBicikla mt = (ModelTabeleBicikla) pb.getjTableBicikle().getModel();
                mt.setLista(lista);
                mt.refresujPodatke();
            }
        });

        pb.PrikaziBiciklaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = pb.getjTableBicikle().getSelectedRow();
                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(pb, "Morate izabrati biciklu iz tabele.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ModelTabeleBicikla mt = (ModelTabeleBicikla) pb.getjTableBicikle().getModel();
                Bicikla b = mt.getLista().get(selektovaniRed);
                Bicikla bDetalji = Komunikacija.getInstance().pretraziBicikla(b);
                if (bDetalji == null) {
                    JOptionPane.showMessageDialog(pb, "Sistem ne može da nađe biciklu.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                pb.dispose();
                Koordinator.getInstance().otvoriKreirajBickikla(bDetalji);
            }
        });
    }

    /**
     * Pomocna metoda koja kreira prazan objekat odgovarajuceg tipa bicikle
     * na osnovu izabranog stringa iz ComboBox-a.
     */
    private Bicikla kreirajPrazanObjekatPoTipu(String tip) {
        return switch (tip) {
            case "Za odrasle" -> new BiciklaZaOdrasle();
            case "Za decu" -> new BiciklaZaDecu();
            case "Sa riksom" -> new BiciklaSaRiksom();
            default -> null;
        };
    }

    /**
     * Pomocna metoda koja puni polja forme podacima iz trenutne bicikle
     * i postavlja editabilnost polja.
     */
    private void popuniForme(boolean editabilno) {
        Bicikla b = kb.getBicikla();
        kb.getjTextFieldCenaPoSatu().setText(String.valueOf(b.getCenaPoSatu()));
        kb.getjTextFieldCenaPoDanu().setText(String.valueOf(b.getCenaPoDanu()));
        kb.getjTextFieldMarka().setText(b.getMarka());
        kb.getjTextFieldModel().setText(b.getModel());
        kb.getjTextFieldBoja().setText(b.getBoja());
        kb.getjTextFieldCenaPoSatu().setEditable(editabilno);
        kb.getjTextFieldCenaPoDanu().setEditable(editabilno);
        kb.getjTextFieldMarka().setEditable(editabilno);
        kb.getjTextFieldModel().setEditable(editabilno);
        kb.getjTextFieldBoja().setEditable(editabilno);
        kb.getjComboBoxTip().setEnabled(editabilno);

        if (b instanceof BiciklaZaOdrasle bo) {
            kb.getjComboBoxTip().setSelectedItem("Za odrasle");
            kb.getjTextFieldVelicinaTockovaOdrasli().setText(String.valueOf(bo.getVelicinaTockova()));
            kb.getjTextFieldBrojBrzina().setText(String.valueOf(bo.getBrojBrzina()));
            kb.getjTextFieldVelicinaTockovaOdrasli().setEditable(editabilno);
            kb.getjTextFieldBrojBrzina().setEditable(editabilno);
        } else if (b instanceof BiciklaZaDecu bd) {
            kb.getjComboBoxTip().setSelectedItem("Za decu");
            kb.getjTextFieldVelicinaTockovaDeca().setText(String.valueOf(bd.getVelicinaTockova()));
            kb.getjTextFieldBrojBrzinaDeca().setText(String.valueOf(bd.getBrojBrzina()));
            kb.getjCheckBoxPomocniTockovi().setSelected(bd.isPomocniTockovi());
            kb.getjTextFieldVelicinaTockovaDeca().setEditable(editabilno);
            kb.getjTextFieldBrojBrzinaDeca().setEditable(editabilno);
            kb.getjCheckBoxPomocniTockovi().setEnabled(editabilno);
        } else if (b instanceof BiciklaSaRiksom br) {
            kb.getjComboBoxTip().setSelectedItem("Sa riksom");
            kb.getjTextFieldBrojSedista().setText(String.valueOf(br.getBrojSedista()));
            kb.getjTextFieldTipRikse().setText(br.getTipRikse());
            kb.getjTextFieldMaxNosivost().setText(String.valueOf(br.getMaxNosivost()));
            kb.getjTextFieldBrojSedista().setEditable(editabilno);
            kb.getjTextFieldTipRikse().setEditable(editabilno);
            kb.getjTextFieldMaxNosivost().setEditable(editabilno);
        }
    }

    public void otvoriFormuKreirajBicikla(Bicikla bicikla) {
        if (bicikla == null) {
            kb.getjButtonSacuvaj().setVisible(false);
            kb.getjButtonZapamti().setVisible(true);
            kb.getjButtonOdustani().setVisible(true);
            kb.getjButtonObrisi().setVisible(false);
            kb.getjButtonPromeni().setVisible(false);
            kb.getjButtonObrisiBiciklu().setVisible(false);
            kb.getjButtonPrikaziObrisi().setVisible(false);
            kb.getjButtonPrikaziPromeni().setVisible(false);
        } else {
            kb.setBicikla(bicikla);
            kb.getjButtonSacuvaj().setVisible(false);
            kb.getjButtonZapamti().setVisible(false);
            kb.getjButtonOdustani().setVisible(false);
            kb.getjButtonObrisi().setVisible(false);
            kb.getjButtonPromeni().setVisible(true);
            kb.getjButtonObrisiBiciklu().setVisible(true);
            kb.getjButtonPrikaziObrisi().setVisible(false);
            kb.getjButtonPrikaziPromeni().setVisible(false);
            popuniForme(false);
        }
        kb.setVisible(true);
    }

    public void otvoriFormuPretraziBicikla() {
        popuniTabelu();
        pb.setVisible(true);
    }

    private void popuniTabelu() {
        List<Bicikla> bicikle = new ArrayList<>();
        ModelTabeleBicikla modelTabele = new ModelTabeleBicikla(bicikle);
        pb.getjTableBicikle().setModel(modelTabele);
    }
}