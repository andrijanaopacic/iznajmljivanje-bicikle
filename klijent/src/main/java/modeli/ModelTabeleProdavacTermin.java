/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.ProdavacTermin;

/**
 *
 * @author HP
 */
public class ModelTabeleProdavacTermin extends AbstractTableModel {
     private List<ProdavacTermin> termini = new ArrayList<>();
    private String[] kolone = {"Naziv", "Smena", "Datum dežurstva"};

    public List<ProdavacTermin> getTermini() {
        return termini;
    }

    public void setTermini(List<ProdavacTermin> termini) {
        this.termini = termini;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public ModelTabeleProdavacTermin(List<ProdavacTermin> termini) {
        this.termini = termini;
    }

    @Override
    public int getRowCount() {
        if (termini.isEmpty()) {
            return 0;
        } else {
            return termini.size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String datumNovi = null;
        ProdavacTermin termin = termini.get(rowIndex);
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
            datumNovi = termin.getDatumDezurstva().format(format);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Greška prilikom obrade datuma", "Greška", JOptionPane.ERROR_MESSAGE);
        }

        switch (columnIndex) {
            case 0:
                return termin.getTermin().getNaziv();
            case 1:
                return termin.getSmena();
            case 2:
                return datumNovi;
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void refresujPodatke() {
        fireTableDataChanged();
    }

    public void dodajElement(ProdavacTermin pt) {
        termini.add(pt);
        fireTableDataChanged();
    }
}
