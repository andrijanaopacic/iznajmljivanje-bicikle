package kontroleri;

import forme.KreirajIznajmljivanje;
import forme.PretraziIznajmljivanje;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import model.Bicikla;
import model.BiciklaZaDecu;
import model.BiciklaZaOdrasle;
import model.BiciklaSaRiksom;
import model.Iznajmljivanje;
import model.Kupac;
import model.Prodavac;
import model.StavkaIznajmljivanja;
import modeli.ModelTabeleIznajmljivanje;
import modeli.ModelTabeleStavkaIznajmljivanja;

public class IznajmljivanjeKontroler {

    private final KreirajIznajmljivanje ki;
    private final PretraziIznajmljivanje pi;

    public IznajmljivanjeKontroler(KreirajIznajmljivanje ki, PretraziIznajmljivanje pi) {
        this.ki = ki;
        this.pi = pi;
        addActionListeners();
    }

    private void addActionListeners() {

        ki.ZapamtiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapamti();
            }

            private void zapamti() {
                Prodavac prodavac = null;
                Kupac kupac = null;
                double ukupanIznos = 0;
                List<StavkaIznajmljivanja> stavke = new ArrayList<>();

                try {
                    prodavac = (Prodavac) ki.getjComboBoxProdavci().getSelectedItem();
                    kupac = (Kupac) ki.getjComboBoxKupac().getSelectedItem();
                    ukupanIznos = Double.parseDouble(ki.getjTextFieldUkupanIznos().getText().trim());
                    ModelTabeleStavkaIznajmljivanja mt = (ModelTabeleStavkaIznajmljivanja) ki.getjTableStavkeIznajmljivanja().getModel();
                    stavke = mt.getStavke();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ki, "Greška pri unosu iznosa.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (prodavac == null || kupac == null || stavke.isEmpty()) {
                    JOptionPane.showMessageDialog(ki, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (ukupanIznos <= 0) {
                    JOptionPane.showMessageDialog(ki, "Ukupan iznos mora biti veći od 0.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Iznajmljivanje iznajmljivanje = new Iznajmljivanje();
                iznajmljivanje.setKupac(kupac);
                iznajmljivanje.setProdavac(prodavac);
                iznajmljivanje.setUkupanIznos(ukupanIznos);
                iznajmljivanje.setListaStavkiIznajmljivanja(stavke);

                for (StavkaIznajmljivanja stavka : stavke) {
                    stavka.setIznajmljivanje(iznajmljivanje);
                }

                boolean uspesno = Komunikacija.getInstance().kreirajIznajmljivanje(iznajmljivanje);
                if (!uspesno) {
                    JOptionPane.showMessageDialog(ki, "Sistem ne može da zapamti iznajmljivanje.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ki, "Sistem je zapamtio iznajmljivanje.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    ki.dispose();
                }
            }
        });

        ki.PromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni();
            }

            private void promeni() {
                Prodavac prodavac = null;
                Kupac kupac = null;
                List<StavkaIznajmljivanja> stavke = new ArrayList<>();
                double ukupanIznos = 0.0;

                try {
                    prodavac = (Prodavac) ki.getjComboBoxProdavci().getSelectedItem();
                    kupac = (Kupac) ki.getjComboBoxKupac().getSelectedItem();

                    ModelTabeleStavkaIznajmljivanja mt = (ModelTabeleStavkaIznajmljivanja) ki.getjTableStavkeIznajmljivanja().getModel();
                    stavke = mt.getStavke();

                    if (prodavac == null || kupac == null || stavke == null || stavke.isEmpty()) {
                        JOptionPane.showMessageDialog(ki, "Niste uneli sve podatke.", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    for (StavkaIznajmljivanja stavka : stavke) {
                        LocalDateTime vremeOd = stavka.getVremeOd();
                        LocalDateTime vremeDo = stavka.getVremeDo();

                        if (vremeOd.isAfter(vremeDo) || vremeOd.isEqual(vremeDo)) {
                            JOptionPane.showMessageDialog(ki, "Vreme početka mora biti pre vremena završetka.", "Greška", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        if (vremeOd.isAfter(LocalDateTime.now()) || vremeDo.isAfter(LocalDateTime.now())) {
                            JOptionPane.showMessageDialog(ki, "Vreme iznajmljivanja ne može biti u budućnosti.", "Greška", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        long trajanje = Duration.between(vremeOd, vremeDo).toHours();
                        int brojDana = (int) (trajanje / 24);
                        int brojSati = (int) (trajanje % 24);

                        Bicikla bicikla = stavka.getBicikla();

                        StavkaIznajmljivanja novaStavka = new StavkaIznajmljivanja(
                                bicikla, stavka.getIdStavkaIznajmljivanja(),
                                0, 0, vremeOd, vremeDo, brojSati, brojDana, null);

                        ukupanIznos += novaStavka.getIznos();
                        stavka.setCena(novaStavka.getCena());
                        stavka.setIznos(novaStavka.getIznos());
                        stavka.setBrojSati(brojSati);
                        stavka.setBrojDana(brojDana);
                    }

                    if (ukupanIznos <= 0) {
                        JOptionPane.showMessageDialog(ki, "Ukupan iznos mora biti veći od 0.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ki, "Greška pri unosu ili obradi podataka.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Iznajmljivanje iznajmljivanje = new Iznajmljivanje(
                        ki.getIznajmljivanje().getIdIznajmljivanje(),
                        ukupanIznos, stavke, kupac, prodavac);

                for (StavkaIznajmljivanja stavka : stavke) {
                    stavka.setIznajmljivanje(iznajmljivanje);
                }

                int odluka = JOptionPane.showConfirmDialog(ki,
                        "Da li ste sigurni da želite da sačuvate promene?",
                        "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odluka == JOptionPane.YES_OPTION) {
                    boolean uspesno = Komunikacija.getInstance().promeniIznajmljivanje(iznajmljivanje);
                    if (!uspesno) {
                        JOptionPane.showMessageDialog(ki, "Sistem ne može da zapamti iznajmljivanje.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(ki, "Sistem je zapamtio iznajmljivanje.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        ki.dispose();
                    }
                }
            }
        });

        ki.OdustaniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int izbor = JOptionPane.showConfirmDialog(ki,
                        "Da li ste sigurni da želite da odustanete?",
                        "Potvrda", JOptionPane.YES_NO_OPTION);
                if (izbor == 0) {
                    ki.dispose();
                }
            }
        });

        ki.PromeniIznajmljivanjeAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ki.getjComboBoxProdavci().setEnabled(true);
                ki.getjComboBoxKupac().setEnabled(true);
                ki.getjComboBoxTipBicikle().setEnabled(true);
                ki.getjComboBoxBicikla().setEnabled(true);
                ki.getjTextFieldVremeOd().setEditable(true);
                ki.getjTextFieldVremeDo().setEditable(true);
                ki.getjButtonDodajStavku().setVisible(true);
                ki.getjButtonSacuvaj().setVisible(true);
                ki.getjButtonZapamti().setVisible(false);
                ki.getjButtonOdustani().setVisible(true);
                ki.getjButtonObrisiStavku().setVisible(true);
                ki.getjButtonPrikazi().setVisible(true);
                ki.getjButtonPromeni().setVisible(false);
                ki.getjTextFieldUkupanIznos().setEditable(false);
            }
        });

        ki.PrikaziAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ki.getjTextFieldUkupanIznos().setText(String.valueOf(ki.getIznajmljivanje().getUkupanIznos()));
                ki.getjComboBoxProdavci().setSelectedItem(ki.getIznajmljivanje().getProdavac());
                ki.getjComboBoxKupac().setSelectedItem(ki.getIznajmljivanje().getKupac());

                if (!ki.getIznajmljivanje().getListaStavkiIznajmljivanja().isEmpty()) {
                    StavkaIznajmljivanja stavka = ki.getIznajmljivanje().getListaStavkiIznajmljivanja().get(0);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
                    ki.getjTextFieldVremeOd().setText(stavka.getVremeOd().format(formatter));
                    ki.getjTextFieldVremeDo().setText(stavka.getVremeDo().format(formatter));
                    ki.getjComboBoxBicikla().setSelectedItem(stavka.getBicikla());
                }

                ki.getjTextFieldVremeOd().setEnabled(false);
                ki.getjTextFieldVremeDo().setEnabled(false);
                ki.getjComboBoxBicikla().setEnabled(false);
                ki.getjComboBoxTipBicikle().setEnabled(false);
                ki.getjComboBoxProdavci().setEnabled(false);
                ki.getjComboBoxKupac().setEnabled(false);
                ki.getjButtonDodajStavku().setVisible(false);
                ki.getjButtonSacuvaj().setVisible(false);
                ki.getjButtonZapamti().setVisible(false);
                ki.getjButtonOdustani().setVisible(false);
                ki.getjButtonObrisiStavku().setVisible(false);
                ki.getjButtonPrikazi().setVisible(false);
                ki.getjButtonPromeni().setVisible(true);
                ki.getjTextFieldUkupanIznos().setEditable(false);
            }
        });

        ki.TipBicikleAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tip = (String) ki.getjComboBoxTipBicikle().getSelectedItem();
                popuniBiciklePoTipu(tip);
            }
        });

        ki.DodajStavkuAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bicikla bicikla = null;
                LocalDateTime vremeOd = null;
                LocalDateTime vremeDo = null;

                try {
                    bicikla = (Bicikla) ki.getjComboBoxBicikla().getSelectedItem();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
                    vremeOd = LocalDateTime.parse(ki.getjTextFieldVremeOd().getText().trim(), formatter);
                    vremeDo = LocalDateTime.parse(ki.getjTextFieldVremeDo().getText().trim(), formatter);

                    if (vremeOd.isAfter(vremeDo) || vremeOd.isEqual(vremeDo)) {
                        JOptionPane.showMessageDialog(ki, "Vreme početka mora biti pre vremena završetka.", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (vremeOd.isAfter(LocalDateTime.now()) || vremeDo.isAfter(LocalDateTime.now())) {
                        JOptionPane.showMessageDialog(ki, "Vreme iznajmljivanja ne može biti u budućnosti.", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ki, "Greška pri unosu bicikle ili vremena.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (bicikla == null) {
                    JOptionPane.showMessageDialog(ki, "Niste izabrali biciklu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                long trajanje = Duration.between(vremeOd, vremeDo).toHours();
                int brojDana = (int) (trajanje / 24);
                int brojSati = (int) (trajanje % 24);

                ModelTabeleStavkaIznajmljivanja mt = (ModelTabeleStavkaIznajmljivanja) ki.getjTableStavkeIznajmljivanja().getModel();
                int idStavke = mt.getStavke().size() + 1;

                StavkaIznajmljivanja stavka = new StavkaIznajmljivanja(
                        bicikla, idStavke, 0.0, 0.0,
                        vremeOd, vremeDo, brojSati, brojDana, null);

                if (mt.getStavke().contains(stavka)) {
                    JOptionPane.showMessageDialog(ki, "Ova stavka je već dodata.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                } else {
                    mt.dodajElement(stavka);
                }

                double ukupno = 0;
                for (StavkaIznajmljivanja s : mt.getStavke()) {
                    ukupno += s.getIznos();
                }
                ki.getjTextFieldUkupanIznos().setText(String.valueOf(Math.round(ukupno * 100.0) / 100.0));
            }
        });

        ki.ObrisiStavkuAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = ki.getjTableStavkeIznajmljivanja().getSelectedRow();
                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(ki, "Morate izabrati stavku koju želite da obrišete.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ModelTabeleStavkaIznajmljivanja mt = (ModelTabeleStavkaIznajmljivanja) ki.getjTableStavkeIznajmljivanja().getModel();
                mt.getStavke().remove(selektovaniRed);
                mt.refresujPodatke();

                int noviId = 1;
                for (StavkaIznajmljivanja s : mt.getStavke()) {
                    s.setIdStavkaIznajmljivanja(noviId++);
                }

                double ukupno = 0;
                for (StavkaIznajmljivanja s : mt.getStavke()) {
                    ukupno += s.getIznos();
                }
                ki.getjTextFieldUkupanIznos().setText(String.valueOf(Math.round(ukupno * 100.0) / 100.0));
            }
        });

        pi.VratiListuSvihIznajmljivanjaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Iznajmljivanje> lista = Komunikacija.getInstance().vratiListuSvaIznajmljivanja();
                if (lista == null || lista.isEmpty()) {
                    JOptionPane.showMessageDialog(pi, "Sistem ne može da nađe nijedno iznajmljivanje.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ModelTabeleIznajmljivanje mt = (ModelTabeleIznajmljivanje) pi.getjTableIznajmljivanja().getModel();
                mt.setLista(lista);
                mt.refresujPodatke();
            }
        });

        pi.VratiListuIznajmljivanjaSaUslovomAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Iznajmljivanje> listaIznajmljivanja = new ArrayList<>();
                int ID = Integer.MIN_VALUE;
                String prodavac = null;
                String kupac = null;

                try {
                    if (!pi.getjTextFieldID().getText().trim().isEmpty()) {
                        ID = Integer.parseInt(pi.getjTextFieldID().getText().trim());
                    }
                    if (!pi.getjTextFieldProdavac().getText().trim().isEmpty()) {
                        prodavac = pi.getjTextFieldProdavac().getText().trim();
                    }
                    if (!pi.getjTextFieldKupac().getText().trim().isEmpty()) {
                        kupac = pi.getjTextFieldKupac().getText().trim();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(pi, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Za biciklu koristimo ComboBox umesto TextField
                String tipBicikle = (String) pi.getjComboBoxBicikla().getSelectedItem();
                boolean biciklaSelektovana = pi.getjRadioButtonBicikla().isSelected();

                if (ID == Integer.MIN_VALUE && prodavac == null && kupac == null && !biciklaSelektovana) {
                    JOptionPane.showMessageDialog(pi, "Morate uneti bar jedan kriterijum.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (ID != Integer.MIN_VALUE) {
                    List<Iznajmljivanje> lista = Komunikacija.getInstance().vratiIznajmljivanjaPoID(ID);
                    if (lista != null) {
                        for (Iznajmljivanje i : lista) {
                            if (!listaIznajmljivanja.contains(i)) listaIznajmljivanja.add(i);
                        }
                    }
                }

                if (prodavac != null) {
                    if (prodavac.strip().split(" ").length >= 3) {
                        JOptionPane.showMessageDialog(pi, "Možete uneti samo ime i prezime za prodavca.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    List<Iznajmljivanje> lista = Komunikacija.getInstance().vratiIznajmljivanjaPoImenuProdavca(prodavac);
                    if (lista != null) {
                        for (Iznajmljivanje i : lista) {
                            if (!listaIznajmljivanja.contains(i)) listaIznajmljivanja.add(i);
                        }
                    }
                }

                if (kupac != null) {
                    if (kupac.strip().split(" ").length >= 3) {
                        JOptionPane.showMessageDialog(pi, "Možete uneti samo ime i prezime za kupca.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    List<Iznajmljivanje> lista = Komunikacija.getInstance().vratiIznajmljivanjaPoImenuKupca(kupac);
                    if (lista != null) {
                        for (Iznajmljivanje i : lista) {
                            if (!listaIznajmljivanja.contains(i)) listaIznajmljivanja.add(i);
                        }
                    }
                }

                if (biciklaSelektovana) {
                    Bicikla b = kreirajPrazanObjekatPoTipu(tipBicikle);
                    if (b != null) {
                        List<Iznajmljivanje> lista = Komunikacija.getInstance().vratiIznajmljivanjaPoTipuBicikle(b);
                        if (lista != null) {
                            for (Iznajmljivanje i : lista) {
                                if (!listaIznajmljivanja.contains(i)) listaIznajmljivanja.add(i);
                            }
                        }
                    }
                }

                if (listaIznajmljivanja.isEmpty()) {
                    JOptionPane.showMessageDialog(pi, "Sistem ne može da nađe iznajmljivanja po zadatim kriterijumima.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ModelTabeleIznajmljivanje mt = (ModelTabeleIznajmljivanje) pi.getjTableIznajmljivanja().getModel();
                mt.setLista(listaIznajmljivanja);
                mt.refresujPodatke();
            }
        });

        pi.PrikaziIznajmljivanjeAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = pi.getjTableIznajmljivanja().getSelectedRow();
                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(pi, "Morate izabrati iznajmljivanje iz tabele.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ModelTabeleIznajmljivanje mt = (ModelTabeleIznajmljivanje) pi.getjTableIznajmljivanja().getModel();
                Iznajmljivanje iznajmljivanje = mt.getLista().get(selektovaniRed);
                Iznajmljivanje iznajmljivanjeDetalji = Komunikacija.getInstance().pretraziIznajmljivanje(iznajmljivanje);
                if (iznajmljivanjeDetalji == null) {
                    JOptionPane.showMessageDialog(pi, "Sistem ne može da nađe iznajmljivanje.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                pi.dispose();
                try {
                    Koordinator.getInstance().otvoriKreirajIznajmljivanje(iznajmljivanjeDetalji);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pi, "Sistem ne može da prikaže iznajmljivanje.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        pi.RadioIDAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pi.getjTextFieldID().setText("");
                pi.getjTextFieldProdavac().setText("");
                pi.getjTextFieldKupac().setText("");
                pi.getjTextFieldID().setEditable(true);
                pi.getjTextFieldProdavac().setEditable(false);
                pi.getjTextFieldKupac().setEditable(false);
                pi.getjComboBoxBicikla().setEnabled(false);
            }
        });

        pi.RadioProdavacAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pi.getjTextFieldID().setText("");
                pi.getjTextFieldProdavac().setText("");
                pi.getjTextFieldKupac().setText("");
                pi.getjTextFieldID().setEditable(false);
                pi.getjTextFieldProdavac().setEditable(true);
                pi.getjTextFieldKupac().setEditable(false);
                pi.getjComboBoxBicikla().setEnabled(false);
            }
        });

        pi.RadioKupacAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pi.getjTextFieldID().setText("");
                pi.getjTextFieldProdavac().setText("");
                pi.getjTextFieldKupac().setText("");
                pi.getjTextFieldID().setEditable(false);
                pi.getjTextFieldProdavac().setEditable(false);
                pi.getjTextFieldKupac().setEditable(true);
                pi.getjComboBoxBicikla().setEnabled(false);
            }
        });

        pi.RadioBiciklaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pi.getjTextFieldID().setText("");
                pi.getjTextFieldProdavac().setText("");
                pi.getjTextFieldKupac().setText("");
                pi.getjTextFieldID().setEditable(false);
                pi.getjTextFieldProdavac().setEditable(false);
                pi.getjTextFieldKupac().setEditable(false);
                pi.getjComboBoxBicikla().setEnabled(true);
            }
        });
    }

    private Bicikla kreirajPrazanObjekatPoTipu(String tip) {
        return switch (tip) {
            case "Za odrasle" -> new BiciklaZaOdrasle();
            case "Za decu" -> new BiciklaZaDecu();
            case "Sa riksom" -> new BiciklaSaRiksom();
            default -> null;
        };
    }

    private void popuniBiciklePoTipu(String tip) {
        Bicikla uslov = kreirajPrazanObjekatPoTipu(tip);
        if (uslov == null) return;
        List<Bicikla> lista = Komunikacija.getInstance().vratiListuBiciklaBicikla(uslov);
        ki.getjComboBoxBicikla().removeAllItems();
        if (lista != null) {
            for (Bicikla b : lista) {
                ki.getjComboBoxBicikla().addItem(b);
            }
        }
    }

    public void otvoriFormuKreirajIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        if (iznajmljivanje == null) {
            ki.getjComboBoxKupac().setSelectedItem(null);
            ki.getjComboBoxProdavci().setSelectedItem(null);
            ki.getjComboBoxBicikla().setSelectedItem(null);
            ki.getjTextFieldUkupanIznos().setEditable(false);
            ki.getjTextFieldUkupanIznos().setText("0.0");

            popuniKupce();
            popuniProdavce();
            // Bicikle se pune tek kada korisnik izabere tip
            ki.getjComboBoxBicikla().removeAllItems();

            popuniTabeluStavki(null);
            ki.getjTextFieldVremeOd().setText("");
            ki.getjTextFieldVremeDo().setText("");

            ki.getjButtonPrikazi().setVisible(false);
            ki.getjButtonPromeni().setVisible(false);
            ki.getjButtonSacuvaj().setVisible(false);
            ki.getjButtonZapamti().setVisible(true);
            ki.getjButtonDodajStavku().setVisible(true);
            ki.getjButtonOdustani().setVisible(true);
            ki.getjButtonObrisiStavku().setVisible(true);
            ki.setVisible(true);

        } else {
            popuniKupce();
            popuniProdavce();
            ki.getjComboBoxBicikla().removeAllItems();

            ki.getjComboBoxKupac().setSelectedItem(iznajmljivanje.getKupac());
            ki.getjComboBoxProdavci().setSelectedItem(iznajmljivanje.getProdavac());
            ki.getjTextFieldUkupanIznos().setEditable(false);
            ki.getjTextFieldUkupanIznos().setText(String.valueOf(iznajmljivanje.getUkupanIznos()));

            ki.getjComboBoxKupac().setEnabled(false);
            ki.getjComboBoxProdavci().setEnabled(false);
            ki.getjComboBoxBicikla().setEnabled(false);
            ki.getjComboBoxTipBicikle().setEnabled(false);
            ki.getjTextFieldVremeOd().setEditable(false);
            ki.getjTextFieldVremeDo().setEditable(false);

            ki.getjButtonDodajStavku().setVisible(false);
            ki.getjButtonSacuvaj().setVisible(false);
            ki.getjButtonZapamti().setVisible(false);
            ki.getjButtonOdustani().setVisible(false);
            ki.getjButtonObrisiStavku().setVisible(false);
            ki.getjButtonPromeni().setVisible(true);
            ki.getjButtonPrikazi().setVisible(false);

            List<StavkaIznajmljivanja> listaStavki = iznajmljivanje.getListaStavkiIznajmljivanja();
            popuniTabeluStavki(listaStavki);

            if (!listaStavki.isEmpty()) {
                StavkaIznajmljivanja prva = listaStavki.get(0);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
                ki.getjTextFieldVremeOd().setText(prva.getVremeOd().format(formatter));
                ki.getjTextFieldVremeDo().setText(prva.getVremeDo().format(formatter));
            }

            JOptionPane.showMessageDialog(ki, "Sistem je našao iznajmljivanje.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            ki.setVisible(true);
        }
    }

    private void popuniKupce() {
        List<Kupac> lista = Komunikacija.getInstance().vratiListuSviKupci();
        ki.getjComboBoxKupac().removeAllItems();
        if (lista != null) {
            for (Kupac k : lista) ki.getjComboBoxKupac().addItem(k);
        }
    }

    private void popuniProdavce() {
        List<Prodavac> lista = Komunikacija.getInstance().vratiListuSviProdavci();
        ki.getjComboBoxProdavci().removeAllItems();
        if (lista != null) {
            for (Prodavac p : lista) ki.getjComboBoxProdavci().addItem(p);
        }
    }

    private void popuniTabeluStavki(List<StavkaIznajmljivanja> stavke) {
        List<StavkaIznajmljivanja> lista = stavke != null ? stavke : new ArrayList<>();
        ModelTabeleStavkaIznajmljivanja modelTabele = new ModelTabeleStavkaIznajmljivanja(lista);
        ki.getjTableStavkeIznajmljivanja().setModel(modelTabele);
    }

    public void otvoriFormuPretraziIznajmljivanje() {
        pi.getjRadioButtonID().setSelected(true);
        pi.getjTextFieldID().setEditable(true);
        pi.getjTextFieldProdavac().setEditable(false);
        pi.getjTextFieldKupac().setEditable(false);
        pi.getjComboBoxBicikla().setEnabled(false);

        ButtonGroup group = new ButtonGroup();
        group.add(pi.getjRadioButtonID());
        group.add(pi.getjRadioButtonProdavac());
        group.add(pi.getjRadioButtonKupac());
        group.add(pi.getjRadioButtonBicikla());

        popuniTabeluIznajmljivanja();
        pi.setVisible(true);
    }

    private void popuniTabeluIznajmljivanja() {
        ModelTabeleIznajmljivanje modelTabele = new ModelTabeleIznajmljivanje(new ArrayList<>());
        pi.getjTableIznajmljivanja().setModel(modelTabele);
    }
}